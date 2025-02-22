package com.sast;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "uploads/ExampleVulnerable.java";  // Change to your uploaded file

        // Read file
        String fileContent = FileScanner.scanFile(filePath);

        // Scan for vulnerabilities
        List<String> scanReport = VulnerabilityChecker.checkVulnerabilities(fileContent); // ✅ Already returns List<String>

        // Display results
        scanReport.forEach(System.out::println);

        // Save report
        ReportGenerator.generateReport(scanReport, "reports/scan_results.html");
    }
}
