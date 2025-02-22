import java.sql.*;
import java.io.*;

public class ExampleVulnerable {
    public static void main(String[] args) throws Exception {
        String userInput = "1234"; // Simulating user input

        // ⚠ SQL Injection Vulnerability
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + userInput);

        // ⚠ Hardcoded Credentials
        String password = "admin123";

        // ⚠ Insecure File Handling
        File file = new File("/etc/passwd");
        System.out.println("File path: " + file.getAbsolutePath());
    }
}
