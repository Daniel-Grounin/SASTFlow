package com.sast;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumReportTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run without GUI
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=/tmp/chrome-profile");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Debugging output
        System.out.println("Chrome version: " + ((HasCapabilities) driver).getCapabilities().getBrowserVersion());
    }

    @Test
    public void testScanResultsDisplayed() {
        // Load the test scan results file
        File reportFile = new File("reports/scan_results.html");
        String reportPath = reportFile.getAbsoluteFile().toURI().toString();
        System.out.println("Loading report from: " + reportPath);

        driver.get(reportPath);

        // Wait until the body tag is loaded
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // Get the page content
        String pageText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Page Content:\n" + pageText);

        // Validate vulnerabilities appear
        assertAll("Vulnerabilities check",
                () -> assertTrue(pageText.contains("Hardcoded Password Found"), "❌ Missing: Hardcoded Password"),
                () -> assertTrue(pageText.contains("Insecure File Handling"), "❌ Missing: Insecure File Handling")
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
