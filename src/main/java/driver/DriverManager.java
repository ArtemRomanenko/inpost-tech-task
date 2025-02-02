package driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

@Slf4j
public class DriverManager {

    private DriverManager() {}

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final PropertyReader propertyReader = new PropertyReader();
    private static final String BROWSER = System.getProperty("browser", propertyReader.getProperty("browser"));
    private static final Boolean REMOTE = Boolean.parseBoolean(System.getProperty("remote", propertyReader.getProperty("remote")));
    private static final Boolean HEADLESS = Boolean.parseBoolean(propertyReader.getProperty("headless"));

    public static void setBrowser() {
        log.info("Getting WebDriver: {}", webDriver.get());
        WebDriver driver = null;
        if (webDriver.get() == null) {
            driver = REMOTE ? new DriverFactory().getRemoteInstance(BROWSER, HEADLESS) :
                    new DriverFactory().getLocalInstance(BROWSER, HEADLESS);
        }
        webDriver.set(driver);
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void quitDriver() {
        if (null != getWebDriver()) {
            getWebDriver().quit();
            webDriver.remove();
        }
    }
}
