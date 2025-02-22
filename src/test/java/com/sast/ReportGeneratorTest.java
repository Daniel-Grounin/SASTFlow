package com.sast;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTest {
    @Test
    public void testGenerateReport() throws IOException {
        String testReportContent = "Test Report Content";
        ReportGenerator.generateReport(testReportContent);

        Path reportPath = Paths.get("reports/scan_results.html");
        assertTrue(Files.exists(reportPath), "Report file should be created");

        String content = Files.readString(reportPath);
        assertTrue(content.contains("Test Report Content"), "Report should contain the test data");
    }
}
