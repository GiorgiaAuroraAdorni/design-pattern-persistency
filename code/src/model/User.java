package model;

import java.sql.*;

public class User {

	private String name;
	private String username;
	private String password;
	private String email;
	private User bestFriend;
	private Address address;

	private static String selectWhere = "SELECT * " + "FROM users WHERE ";
	private final static String insert = "INSERT INTO users (username, name, email, address, password, bestFriend) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";
//	private final static String delete = "DELETE FROM Users WHERE id == ";


//	Constructor
	public User(String username, String name, String email, Address address, String password, User bestFriend) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.bestFriend = bestFriend;
		this.address = address;
	}

//	Getters and Setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User getBestFriend() {
		return bestFriend;
	}
	
	public void setBestFriend(User bestFriend) {
		this.bestFriend = bestFriend;
	}

	public Address getAddress() { return address; }

	public void setAddress(Address address) { this.address = address; }

//	search
	public static User findByUsername(String username, Database db) {
		String query = selectWhere + "username == \"" + username + "\" ;";
		return find(query, db);
	}

	public static User findByName(String name, Database db) {
		String query = selectWhere + "name == \"" + name + "\" ;";
		return find(query, db);
	}

	public static User findByEmail(String email, Database db) {
		String query = selectWhere + "email == \"" + email + "\" ;";
		return find(query, db);
	}

	public static User findByAddress(Address address, Database db) {
		String query = selectWhere + "address == \"" + address + "\" ;";
		return find(query, db);
	}

	public static User findByBestFriend(String bestFriend, Database db) {
		String query = selectWhere + "bestFriend == \"" + bestFriend + "\" ;";
		return find(query, db);
	}

//	find
	private static User find(String query, Database db) {
		try (Connection conn = DriverManager.getConnection(db.url)){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				Address address = Address.findByStreetAddress(rs.getString("address"), db);
				User bestFriend = new User(rs.getString("username"),
						rs.getString("name"),
						rs.getString("email"),
						address,
						rs.getString("password"),
						null);

				User user = new User(rs.getString("username"),
						rs.getString("name"),
						rs.getString("email"),
						address,
						rs.getString("password"),
						bestFriend);
				rs.close();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	create
	public static boolean create(User user, Database db) {
		String bestFriend ;
		if(user.getBestFriend().equals("")) {
			bestFriend = null;
		}
		else {
			bestFriend = "\"" + user.getBestFriend() + "\"";
		}
		try (Connection conn = DriverManager.getConnection(db.url)) {
			Address address = Address.findByStreetAddress(user.getAddress().getStreetAddress(), db);
			if(address == null) {
				address = new Address(user.getAddress().getStreetAddress());
				Address.insert(address, db);
			}

			String query = insert +
					"\"" + user.getUsername() + "\", " +
					"\"" + user.getName() + "\", " +
					"\"" + user.getEmail() + "\", " +
					"\"" + address.getStreetAddress() + "\", " +
					"\"" + user.getPassword() + "\", " +
					bestFriend + "); ";
			Statement stmt = conn.createStatement();
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
