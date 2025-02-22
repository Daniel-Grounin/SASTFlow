package com.sast;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class ReportGenerator {
    public static void generateReport(List<String> vulnerabilities) throws IOException {
        Path reportPath = Paths.get("reports/scan_results.html");

        // Ensure the "reports" directory exists
        Files.createDirectories(reportPath.getParent());

        // Start HTML structure
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>SAST Report</title>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }")
                .append(".container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }")
                .append("h2 { color: #2c3e50; text-align: center; }")
                .append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }")
                .append("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }")
                .append("th { background-color: #34495e; color: white; }")
                .append(".high { background-color: #e74c3c; color: white; }")
                .append(".medium { background-color: #f39c12; color: white; }")
                .append(".low { background-color: #2ecc71; color: white; }")
                .append("</style></head><body>")
                .append("<div class='container'>")
                .append("<h2>Security Scan Results</h2>")
                .append("<table>")
                .append("<tr><th>Issue</th><th>Severity</th></tr>");

        // Add vulnerabilities dynamically
        for (String vulnerability : vulnerabilities) {
            String severityClass = getSeverityClass(vulnerability);
            html.append("<tr><td>").append(vulnerability).append("</td>")
                    .append("<td class='").append(severityClass).append("'>")
                    .append(severityClass.toUpperCase()).append("</td></tr>");
        }

        // Close HTML
        html.append("</table></div></body></html>");

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
