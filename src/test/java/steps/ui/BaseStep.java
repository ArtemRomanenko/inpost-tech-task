package steps.ui;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class BaseStep {

    @Before(value = "@UI")
    public void initBrowser() {
        DriverManager.setBrowser();
    }

    @After(value = "@UI")
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Scenario failed! Taking screenshot...");
            captureScreenshot();
        }
        DriverManager.quitDriver();
    }

    private void captureScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(
                    "Screenshot on Failure " + getCurrentTimeAsString(),
                    "image/png",
                    "png",
                    screenshot
            );
            log.info("Screenshot captured ({} bytes)", screenshot.length);
        } catch (Exception e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
