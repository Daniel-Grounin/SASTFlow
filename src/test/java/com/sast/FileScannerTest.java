package com.sast;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileScannerTest {
    @Test
    public void testScanFile() throws IOException {
        String filePath = "uploads/ExampleVulnerable.java";
        String content = FileScanner.scanFile(filePath);

        assertNotNull(content, "File content should not be null");
        assertTrue(content.contains("public class ExampleVulnerable"), "File should contain class definition");
    }
}
