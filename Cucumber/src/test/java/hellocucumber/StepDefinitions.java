package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinitions {

    private ChromeDriver driver;
    private WebDriverWait wait;
    String chrome_path = "C:\\Users\\Lior\\Desktop\\Semester 5\\SQE\\2023-mbt-08-41\\Selenium\\chromedriver.exe";

    // opens the browser and navigates to the website under test
    @Given("User is on Home Page")
    public void userInHomePage() {
        // initialize the Chrome driver
        System.setProperty("webdriver.chrome.driver", chrome_path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        // navigate to the website
        driver.get("http://localhost/opencart/");
        // set the window position
        driver.manage().window().setPosition(new Point(5, 5));
        // set the window size to full screen
        driver.manage().window().maximize();
    }

    // user logs in with the given email and password
    @When("User is logged in with {string} and {string}")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        // find the my account link and click it
        driver.findElement(By.xpath("/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/a[@class='dropdown-toggle']/span[@class='d-none d-md-inline']")).click();
        // find the login link and click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/ul[@class='dropdown-menu dropdown-menu-right show']/li[2]/a[@class='dropdown-item']"))).click();
        // find the email input and fill it with the given email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(email);
        // find the password input and fill it with the given password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        // find the login button and click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='form-login']/div/button[1]"))).click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // user searches a product and adds it to the cart
    @And("User adds a {string} to cart")
    public void userAddsProduct(String product) {
        // wait till page loads and find the search bar and fill product name
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/input[@class='form-control form-control-lg']")));
        searchInput.sendKeys(product);
        // find the search button and click it
        WebElement searchButton = driver.findElement(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/button[@class='btn btn-light btn-lg']/i[@class='fa-solid fa-magnifying-glass']"));
        searchButton.click();
        // find add to cart button and scroll to it
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@id='product-list']/div[@class='col mb-3'][1]/div[@class='product-thumb']/div[@class='content']/form/div[@class='button-group']/button[1]")));
        // scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        // wait till the page loads and find the product add to cart button
        addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@id='product-list']/div[@class='col mb-3'][1]/div[@class='product-thumb']/div[@class='content']/form/div[@class='button-group']/button[1]")));
        // Click on the element
        addToCartButton.click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // check that product was added to the cart
    @Then("{string} successfully added to the cart")
    public void productInCart(String product) {
        // scroll back to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        // wait till the page loads and find the cart button and click it
        WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/button[@class='btn btn-lg btn-inverse btn-block dropdown-toggle']")));
        cartButton.click();
        // wait for cart to load and find the product name
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/table[@class='table table-striped mb-2']/tbody/tr/td[@class='text-start']/a")));
        // check that the product name is the same as the given product
        assert(productName.getText().equals(product));
    }

    // user goes to checkout page
    @And("User navigate to checkout page")
    public void userOnCheckout() {
        // find the checkout button and click it
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/div/p[@class='text-end']/a[2]/strong")));
        checkoutButton.click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check that the user is on the checkout page
        assert(driver.getCurrentUrl().equals("http://localhost/opencart/index.php?route=checkout/checkout&language=en-gb"));
    }

    @After
    public void tearDown() {
        // remove the products from the cart
        WebElement cartInformation = driver.findElement(By.xpath("//*[@id='header-cart']/div[1]/button[1]"));
        cartInformation.click();
        WebElement removeButton = driver.findElement(By.xpath("//*[@id='header-cart']/div/ul/li/table/tbody/tr/td/form/button/i[1]"));
        removeButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // close the browser
        driver.quit();
    }


}
