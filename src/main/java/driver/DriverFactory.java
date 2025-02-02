package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class DriverFactory {

    private final PropertyReader reader = new PropertyReader();
    private final String GRID_URL = reader.getProperty("grid.hub");

    public WebDriver getLocalInstance(String browser, boolean headless) {
        WebDriver driver = null;
        try {
            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            WebDriverManager.getInstance(driverManagerType).setup();

            if (driverManagerType == DriverManagerType.CHROME) {
                ChromeOptions options = (ChromeOptions) defaultChromeOptions(headless);
                driver = new ChromeDriver(options);
            } else if (driverManagerType == DriverManagerType.FIREFOX) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
            }

        } catch (Exception e) {
            log.error("Problem during driver instantiation for browser: {}", browser, e);
        }
        return driver;
    }

    public WebDriver getRemoteInstance(String browser, boolean headless) {
        try {
            return new RemoteWebDriver(new URL(GRID_URL), getRemoteDriver(browser, headless));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private MutableCapabilities getRemoteDriver(String browser, boolean headless) {
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());

        return switch (driverManagerType) {
            case CHROME -> defaultChromeOptions(headless);
            case FIREFOX -> new FirefoxOptions();
            default -> throw new IllegalArgumentException(browser + " is unsupported");
        };
    }

    private MutableCapabilities defaultChromeOptions(boolean headless) {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments(
                "start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*");
        if (headless) {
            capabilities.addArguments("--headless=new");
        }
        capabilities.addArguments("--window-size=1920,1080");
        return capabilities;
    }
}
