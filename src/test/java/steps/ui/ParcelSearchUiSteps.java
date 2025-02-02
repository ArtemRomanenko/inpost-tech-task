package steps.ui;

import driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParcelSearchUiSteps {

    private final WebDriver driver = DriverManager.getWebDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, 5);
    private final PropertyReader reader = new PropertyReader();
    private final String homePage = reader.getProperty("home.page");

    private final By acceptCookieButton = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private final By searchField = By.xpath("//input[@name='number']");
    private final By parcelStatus = By.xpath("//div[@class='single--status--block -active']");


    @Given("User navigate to InPost website")
    public void userIsNavigateToInPostWebsite() {
        driver.get(homePage);
    }

    @And("User close Cookie banner")
    public void userCloseCookieBanner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookieButton)).click();
    }

    @When("User search for parcel number {string}")
    public void userSearchForParcelNumber(String parcelNumber) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        searchBox.sendKeys(parcelNumber);
        searchBox.submit();
    }

    @Then("The status should be {string}")
    public void theStatusShouldBe(String expectedStatus) {
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(parcelStatus));
        assertTrue(statusElement.getText().contains(expectedStatus));
    }
}
