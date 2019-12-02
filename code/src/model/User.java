package model;

import org.sqlite.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String username;
    private String password;
    private String email;
    private String bestFriend;
    private Address address;

    private static String selectWhere = "SELECT * " + "FROM users WHERE ";
    private final static String insertStatement = "INSERT INTO users (username, name, email, address, password, bestFriend) " + "VALUES(?, ?, ?, ?, ?, ?)";
    private final static String updateStatement = "UPDATE users SET name=?, email=?, address=?, password=?, bestFriend=? WHERE username=?";
    private final static String findAll = "SELECT *  FROM users";
	private final static String deleteStatement = "DELETE FROM users WHERE username=?";

    //	Constructor
    public User(String username, String name, String email, Address address, String password, String bestFriend) {
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

    public String getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(String bestFriend) {
        this.bestFriend = bestFriend;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //    load
    protected static User load(ResultSet rs) throws SQLException{
        String username = rs.getString("username");

        String name = rs.getString("name");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String password = rs.getString("password");
        String bestFriend = rs.getString("bestFriend");

        Address a = new Address(address);

        User result = new User(username, name, email, a, password, bestFriend);
        return result;
    }

    //	create
    public static boolean create(User user, Database db) {
        String bestFriend;
        if (user.getBestFriend().equals("")) {
            bestFriend = null;
        } else {
            bestFriend = user.getBestFriend();
        }
        try (Connection conn = DriverManager.getConnection(db.url)) {
            Address address = user.getAddress();

            PreparedStatement stm = conn.prepareStatement(insertStatement);

            stm.setString(1, user.getUsername());
            stm.setString(2, user.getName());
            stm.setString(3, user.getEmail());
            stm.setString(4, address.getStreetAddress());
            stm.setString(5, user.getPassword());
            stm.setString(6, bestFriend);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

//    search
    private static User find(String query, Database db) {
        try (Connection conn = DriverManager.getConnection(db.url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                Address address = new Address(rs.getString("address"));
                User user = new User(rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("email"),
                        address,
                        rs.getString("password"),
                        rs.getString("bestFriend"));
                rs.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll(Database db) {
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.url)) {
            PreparedStatement stm = conn.prepareStatement(findAll);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                User user = load(results);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static List<User> findAllByQuery(String query, Database db, Object... queryParams) {
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.url); PreparedStatement stm = conn.prepareStatement(query)){

            for (int i = 0; i < queryParams.length; i++) {
                stm.setObject(i + 1, queryParams[i]);
            }

            ResultSet results = stm.executeQuery();

            while (results.next()) {
                User user = load(results);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static User findByUsername(String username, Database db) {
        String query = selectWhere + "username == \"" + username + "\" ;";
        return find(query, db);
    }

    public static List<User> findAllByName(String name, Database db) {
        String query = selectWhere + "name = ?";
        return findAllByQuery(query, db, name);
    }

    public static List<User> findAllByEmail(String email, Database db) {
        String query = selectWhere + "email = ?";
        return findAllByQuery(query, db, email);
    }

    public static List<User> findAllByAddress(String address, Database db) {
        String query = selectWhere + "address = ?";
        return findAllByQuery(query, db, address);
    }

    public static List<User> findAllByBestFriend(String bestFriend, Database db) {
        String query = selectWhere + "bestFriend = ?";
        return findAllByQuery(query, db, bestFriend);
    }

    //    update
    public static boolean update(User user, Database db) {
        Address address = user.getAddress();
        try (Connection conn = DriverManager.getConnection(db.url)){

            PreparedStatement stm = conn.prepareStatement(updateStatement);

            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, address.getStreetAddress());
            stm.setString(4, user.getPassword());
            stm.setString(5, user.getBestFriend());
            stm.setString(6, user.getUsername());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

//      delete
    public static boolean delete(User user, Database db) {
        try (Connection conn = DriverManager.getConnection(db.url)){

            PreparedStatement stm = conn.prepareStatement(deleteStatement);
            stm.setString(1, user.getUsername());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
