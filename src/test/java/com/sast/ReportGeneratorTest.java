package com.sast;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {

    @Test
    void testGenerateReport() throws IOException {
        String filePath = "reports/test_report.html";
        List<String> vulnerabilities = List.of("Hardcoded Password", "Insecure File Handling");

        // Generate report
        ReportGenerator.generateReport(vulnerabilities, filePath);

        // ✅ Wait for file creation
        Path reportPath = Path.of(filePath);
        assertTrue(Files.exists(reportPath), "Report file should be created");

        // ✅ Read report content
        String reportContent = Files.readString(reportPath);

        // ✅ Check if expected vulnerabilities exist
        assertAll("Vulnerabilities check",
                () -> assertTrue(reportContent.contains("Hardcoded Password"), "❌ Missing: Hardcoded Password"),
                () -> assertTrue(reportContent.contains("Insecure File Handling"), "❌ Missing: Insecure File Handling")
        );

        // ✅ Check if the expected title is present
        assertTrue(reportContent.contains("<h2>Security Scan Results</h2>"), "❌ Missing expected title in report");
    }
}
