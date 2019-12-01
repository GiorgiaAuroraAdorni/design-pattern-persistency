package model;

import java.sql.*;

public class Database {

    String url;
    private static String fileName;
    private static Database instance = null;

    public static Database getInstance() {
        if (instance == null) {
            Database db = new Database();
            System.out.print(System.getProperty("user.dir"));
            db.createNewDatabase("Database.db");
            Database.createNewTable(db);
            instance = db;
            return db;
        } else {
            return instance;
        }
    }

    private void createNewDatabase(String fileName) {
        Database.fileName = fileName;

        String url = "jdbc:sqlite:" + fileName;
        Connection c = null;
        Statement stmt = null;

        try {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("Opened database successfully");

                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewTable(Database db) {
        // SQLite connection string
        String url = "jdbc:sqlite:";

        // SQL statement for creating a new table
        String users = "CREATE TABLE IF NOT EXISTS users (\n"
                + " username TEXT PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " address text NOT NULL,\n"
                + " password text NOT NULL,\n"
                + " bestFriend text,\n"
                + "    FOREIGN KEY(bestFriend) REFERENCES users(username) ON DELETE SET NULL,\n"
                + "    FOREIGN KEY(address) REFERENCES addresses(streetAddress) ON DELETE SET NULL);";

        String addresses = "CREATE TABLE IF NOT EXISTS addresses (\n"
                + " streetAddress TEXT PRIMARY KEY);";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(users);
            System.out.println("Created table " + "\n" + users);
            stmt.execute(addresses);
            System.out.println("Created table " + "\n" + addresses);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
