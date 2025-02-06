package driver;

import config.Config;
import io.github.bonigarcia.wdm.*;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class DriverFactory {
    private final String GRID_URL = Config.GRID_HUB;
    private final boolean HEADLESS = Config.IS_HEADLESS;

    public WebDriver createLocalDriver(String browser) {
        DriverManagerType type = DriverManagerType.valueOf(browser.toUpperCase());
        WebDriverManager.getInstance(type).setup();

        return switch (type) {
            case CHROME -> new ChromeDriver(getChromeOptions());
            case FIREFOX -> new FirefoxDriver(getFirefoxOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    public WebDriver createRemoteDriver(String browser) {
        try {
            return new RemoteWebDriver(new URL(GRID_URL), getOptions(browser));
        } catch (Exception e) {
            throw new RuntimeException("Grid connection failed: " + GRID_URL, e);
        }
    }

    private MutableCapabilities getOptions(String browser) {
        return switch (browser.toUpperCase()) {
            case "CHROME" -> getChromeOptions();
            case "FIREFOX" -> getFirefoxOptions();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=" + (HEADLESS ? "new" : "false"),
                "--no-sandbox",
                "--disable-gpu",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080"
        );
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(HEADLESS ? "--headless" : "");
        return options;
    }
}
