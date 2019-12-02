package model;

public class Address {

    private String streetAddress;

    private static String selectWhere = "SELECT * " + "FROM addresses WHERE ";

    public Address(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
