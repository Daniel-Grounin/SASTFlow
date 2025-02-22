package com.sast;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "uploads/ExampleVulnerable.java";  // Change to your uploaded file

        // Read file
        String fileContent = FileScanner.scanFile(filePath);

        // Scan for vulnerabilities (Now returns List<String>)
        List<String> vulnerabilities = VulnerabilityChecker.checkVulnerabilities(fileContent);

        // Display results
        vulnerabilities.forEach(System.out::println); // Print each vulnerability on a new line

        // Save report
        ReportGenerator.generateReport(vulnerabilities);
    }
}
