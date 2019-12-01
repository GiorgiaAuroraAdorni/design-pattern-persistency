package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String username;
    private String password;
    private String email;
    private User bestFriend;
    private Address address;

    private static String selectWhere = "SELECT * " + "FROM users WHERE ";
    private final static String insertStatement = "INSERT INTO users (username, name, email, address, password, bestFriend) " + "VALUES(?, ?, ?, ?, ?, ?)";
    private final static String updateStatement = "UPDATE users SET ";
    private final static String findAll = "SELECT *  FROM users";
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    //    load
    protected static User load(ResultSet rs) throws SQLException{
        String username = rs.getString("username");
    // Chaeck for cached objects
        String name = rs.getString("name");
        String email = rs.getString("email");
//        String address = rs.getString("address");
        String password = rs.getString("password");
//        String bestFriend = rs.getString("bestFriend");

        User result = new User(username, name, email, null, password, null);
        return result;
    }

    //	find
    private static User find(String query, Database db) {
        try (Connection conn = DriverManager.getConnection(db.url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
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

    //	create
    public static boolean create(User user, Database db) {
        String bestFriend;
        if (user.getBestFriend().equals("")) {
            bestFriend = null;
        } else {
            bestFriend = "\"" + user.getBestFriend() + "\"";
        }
        try (Connection conn = DriverManager.getConnection(db.url)) {
            Address address = Address.findByStreetAddress(user.getAddress().getStreetAddress(), db);
            if (address == null) {
                address = new Address(user.getAddress().getStreetAddress());
                Address.insert(address, db);
            }

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

    //    update
//    public static void update(String id, String[] param, Database db) {
//        Address address = Address.findByStreetAddress(param[1], db);
//        if(address == null) {
//            address = new Address(param[1]);
//            Address.insert(address, db);
//        }
//        if(param[3] != null) {
//            param[3] = "\"" + param[3] + "\"";
//        }
//        try (Connection conn = DriverManager.getConnection(db.url)){
//            String query = update +
//                    " id = \"" + param[4] + "\"," +
//                    " name = \"" + param[0] + "\"," +
//                    " address = \"" + address.getName() + "\"," +
//                    " password = \"" + param[2] + "\"," +
//                    " bestfriend = " + param[3] + "" +
//                    " WHERE id == \"" + id + "\" ;";
//
//
//            PreparedStatement stm = conn.prepareStatement(query);
//
//            stm.executeUpdate();
//            stm.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//  delete
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
