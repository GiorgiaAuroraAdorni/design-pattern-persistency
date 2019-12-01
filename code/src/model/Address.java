package model;

import java.sql.*;

public class Address {

    private String streetAddress;
//    private String city;
//    private String region;
//    private String postalCode;

    private static String selectWhere = "SELECT * " + "FROM addresses WHERE ";

    public Address(String streetAddress) {
        this.streetAddress = streetAddress;
//        this.city = city;
//        this.region = region;
//        this.postalCode = postalCode;
    }

    public static void insert(Address address, Database db) {
    }

//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String locality) {
//        this.city = city;
//    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

//    search
    public static Address findByStreetAddress(String streetAddress, Database db) {
        String query = selectWhere + "streetaddress == \"" + streetAddress + "\" ;";
        return find(query, db);
    }

//    find
    private static Address find(String query, Database db) {
        try (Connection conn = DriverManager.getConnection(db.url)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                Address user = new Address(rs.getString("streetAddress"));
                rs.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
