package com.sast;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class ReportGenerator {

    public static void generateReport(List<String> vulnerabilities, String filePath) throws IOException {
        Path reportPath = Paths.get(filePath);
        Path parentDir = reportPath.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // Start HTML structure
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>SAST Report</title>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 20px; }")
                .append("h2 { color: #2c3e50; }")
                .append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }")
                .append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }")
                .append("th { background-color: #34495e; color: white; }")
                .append(".high { background-color: #e74c3c; color: white; }")
                .append(".medium { background-color: #f39c12; color: white; }")
                .append(".low { background-color: #2ecc71; color: white; }")
                .append("</style></head><body>")
                .append("<h2>Security Scan Results</h2>")
                .append("<table>")
                .append("<tr><th>Issue</th><th>Severity</th></tr>");

        // Add vulnerabilities
        for (String vulnerability : vulnerabilities) {
            String severityClass = getSeverityClass(vulnerability);
            html.append("<tr><td>").append(vulnerability).append("</td>")
                    .append("<td class='").append(severityClass).append("'>")
                    .append(severityClass.toUpperCase()).append("</td></tr>");
        }

        // Close HTML
        html.append("</table></body></html>");

        // Write to file
        Files.write(reportPath, html.toString().getBytes());
        System.out.println("✔ Report saved: " + reportPath.toAbsolutePath());
    }

    // Determine severity class
    private static String getSeverityClass(String vulnerability) {
        if (vulnerability.toLowerCase().contains("hardcoded password")) return "high";
        if (vulnerability.toLowerCase().contains("insecure file handling")) return "medium";
        return "low"; // Default to low severity
    }
}
