package com.sast;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "uploads/ExampleVulnerable.java";  // Change to your uploaded file

        // Read file
        String fileContent = FileScanner.scanFile(filePath);

        // Scan for vulnerabilities
        String scanReport = VulnerabilityChecker.checkVulnerabilities(fileContent);

        // Display results
        System.out.println(scanReport);

        // Convert report to a List<String> (each line as a separate entry)
        List<String> vulnerabilities = Arrays.asList(scanReport.split("\n"));

        // Save report
        ReportGenerator.generateReport(vulnerabilities);
    }
}
