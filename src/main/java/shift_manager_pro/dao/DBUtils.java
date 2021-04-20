package shift_manager_pro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    /**
     * URL of the database to connect to. Must be same as db.url property in pom.xml
     */
    public static final String DB_DEFAULT_URI = "jdbc:h2:file:./target/donadoni";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                System.getProperty(DB_URL,DB_DEFAULT_URI),
                System.getProperty(DB_USER,"sa"),
                System.getProperty(DB_PASSWORD,""));
    }

}
