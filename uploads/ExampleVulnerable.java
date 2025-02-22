import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.Base64;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ExampleVulnerable {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // ⚠ SQL Injection Vulnerability
        System.out.print("Enter user ID: ");
        String userInput = scanner.nextLine(); // Simulating user input

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + userInput); // 🚨 SQL Injection

        // Print results (for debugging, but also leaks sensitive info)
        while (rs.next()) {
            System.out.println("User: " + rs.getString("username") + ", Password: " + rs.getString("password"));
        }

        // ⚠ Hardcoded Credentials
        String password = "admin123"; // 🚨 Hardcoded password
        System.out.println("Admin password: " + password);

        // ⚠ Insecure File Handling - Arbitrary File Read
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file)); // 🚨 Can read any file
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        // ⚠ Arbitrary Command Execution
        System.out.print("Enter command to execute: ");
        String command = scanner.nextLine();
        Process process = Runtime.getRuntime().exec(command); // 🚨 Unrestricted command execution
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output;
        while ((output = reader.readLine()) != null) {
            System.out.println(output);
        }

        // ⚠ Weak Encryption (SHA-1, Base64 Encoding instead of hashing properly)
        System.out.print("Enter password to encrypt: ");
        String weakPassword = scanner.nextLine();
        String encrypted = Base64.getEncoder().encodeToString(weakPassword.getBytes()); // 🚨 Base64 is not encryption
        System.out.println("Weakly encrypted password: " + encrypted);

        // ⚠ Cross-Site Scripting (XSS) - Printing unsanitized input
        System.out.print("Enter a message: ");
        String userMessage = scanner.nextLine();
        System.out.println("User input: " + userMessage); // 🚨 No sanitization, vulnerable to XSS if printed on web

        // ⚠ Unrestricted Deserialization
        System.out.print("Enter serialized object file path: ");
        String serializedFile = scanner.nextLine();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(serializedFile));
        Object obj = objectInputStream.readObject(); // 🚨 Potential deserialization attack
        System.out.println("Deserialized object: " + obj);

        // ⚠ JavaScript Execution via ScriptEngine
        System.out.print("Enter JavaScript code to execute: ");
        String jsCode = scanner.nextLine();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            engine.eval(jsCode); // 🚨 Can execute arbitrary JavaScript
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
