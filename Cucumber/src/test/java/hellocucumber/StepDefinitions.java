package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepDefinitions {

    // set webdriver chrome path
    private String driver_path = "C:\\Users\\Lior\\Desktop\\Semester 5\\SQE\\2023-mbt-08-41\\Selenium\\chromedriver.exe";
    private String driver_name = "webdriver.chrome.driver";
    private ChromeDriver driver;
    private WebDriverWait wait;

    // $$*TODO* explain what this step does$$
    @Given("User is on home page")
    public void userIsOnHomePage() {
        System.setProperty(driver_name, driver_path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/opencart/");
        driver.manage().window().setPosition(new Point(700, 5));
    }

    // $$*TODO* explain what this step does$$
    @When("User is logged in with {string} and {string}")
    public void allStepDefinitionsAreImplemented() {
    }

    @And("User adds a {string} to cart")
    public void userAddsAProductToCart(String product) {
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
    }

    // $$*TODO* explain what this step does$$
    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

}
