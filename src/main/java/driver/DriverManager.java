package driver;

import config.Config;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class DriverManager {

    private DriverManager() {}

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final String BROWSER = Config.BROWSER;
    private static final boolean REMOTE = Config.IS_REMOTE;

    public static void setBrowser() {
        if (webDriver.get() == null) {
            DriverFactory factory = new DriverFactory();
            WebDriver driver = REMOTE
                    ? factory.createRemoteDriver(BROWSER)
                    : factory.createLocalDriver(BROWSER);

            webDriver.set(driver);
            log.info("WebDriver initialized: {}", driver);
        }
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void quitDriver() {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
            webDriver.remove();
            log.info("WebDriver quit and removed from ThreadLocal");
        }
    }
}
