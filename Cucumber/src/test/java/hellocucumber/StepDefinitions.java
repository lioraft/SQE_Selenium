package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    // save old price for the second scenario
    private int oldPrice;
    // chrome driver for the admin and user
    private ChromeDriver UserDriver;
    private ChromeDriver AdminDriver;
    // wait for the elements to load on both drivers
    private WebDriverWait UserWait;
    private WebDriverWait AdminWait;
    // path to the chrome driver
    String chrome_path = "C:\\Users\\Lior\\Desktop\\Semester 5\\SQE\\2023-mbt-08-41\\Selenium\\chromedriver.exe";

    /*** first scenario - user checks out a product ***/

    // opens the browser and navigates to the website under test
    @Given("User is on Home Page")
    public void userInHomePage() {
        // initialize the Chrome driver
        System.setProperty("webdriver.chrome.driver", chrome_path);
        this.UserDriver = new ChromeDriver();
        this.UserWait = new WebDriverWait(UserDriver, Duration.ofSeconds(40));
        // navigate to the website
        UserDriver.get("http://localhost/opencart/");
        // set the window position
        UserDriver.manage().window().setPosition(new Point(5, 5));
        // set the window size to full screen
        UserDriver.manage().window().maximize();
    }

    // user logs in with the given email and password
    @When("User is logged in with {string} and {string}")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        // find the my account link and click it
        UserDriver.findElement(By.xpath("/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/a[@class='dropdown-toggle']/span[@class='d-none d-md-inline']")).click();
        // find the login link and click it
        UserWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/ul[@class='dropdown-menu dropdown-menu-right show']/li[2]/a[@class='dropdown-item']"))).click();
        // find the email input and fill it with the given email
        UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(email);
        // find the password input and fill it with the given password
        UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        // find the login button and click it
        UserWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='form-login']/div/button[1]"))).click();
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
        WebElement searchInput = UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/input[@class='form-control form-control-lg']")));
        searchInput.sendKeys(product);
        // find the search button and click it
        WebElement searchButton = UserDriver.findElement(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/button[@class='btn btn-light btn-lg']/i[@class='fa-solid fa-magnifying-glass']"));
        searchButton.click();
        // find add to cart button and scroll to it
        WebElement addToCartButton = UserWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@id='product-list']/div[@class='col mb-3'][1]/div[@class='product-thumb']/div[@class='content']/form/div[@class='button-group']/button[1]")));
        // scroll to the element
        ((JavascriptExecutor) UserDriver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        // wait till the page loads and find the product add to cart button
        addToCartButton = UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@id='product-list']/div[@class='col mb-3'][1]/div[@class='product-thumb']/div[@class='content']/form/div[@class='button-group']/button[1]")));
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
        ((JavascriptExecutor) UserDriver).executeScript("window.scrollTo(0, 0)");
        // wait till the page loads and find the cart button and click it
        WebElement cartButton = UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/button[@class='btn btn-lg btn-inverse btn-block dropdown-toggle']")));
        cartButton.click();
        // wait for cart to load and find the product name
        WebElement productName = UserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/table[@class='table table-striped mb-2']/tbody/tr/td[@class='text-start']/a")));
        // check that the product name is the same as the given product
        assert(productName.getText().equals(product));
    }

    // user goes to checkout page
    @And("User navigate to checkout page")
    public void userOnCheckout() {
        // find the checkout button and click it
        WebElement checkoutButton = UserWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/div/p[@class='text-end']/a[2]/strong")));
        checkoutButton.click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check that the user is on the checkout page
        assert(UserDriver.getCurrentUrl().equals("http://localhost/opencart/index.php?route=checkout/checkout&language=en-gb"));
    }


    /*** second scenario - admin changes a product's price ***/

    // opens the browser and navigates to the admin dashboard
    @Given("Admin is on Admin Dashboard Page")
    public void adminInDashboard() {
        // initialize the Chrome driver
        System.setProperty("webdriver.chrome.driver", chrome_path);
        this.AdminDriver = new ChromeDriver();
        this.AdminWait = new WebDriverWait(AdminDriver, Duration.ofSeconds(40));
        // navigate to the website admin page
        AdminDriver.get("http://localhost/opencart/adminn/index.php?route=common/login");
        // set the window position
        AdminDriver.manage().window().setPosition(new Point(5, 5));
        // set the window size to full screen
        AdminDriver.manage().window().maximize();
    }

    // user logs in with the given email and password
    @When("Admin is logged in with {string} and {string}")
    public void adminIsLoggedInWithUsernameAndPassword(String username, String password) {
        // find the username input and fill it with the given username
        AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-username']"))).sendKeys(username);
        // find the password input and fill it with the given password
        AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        // find the login button and click it
        WebElement login = AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='row justify-content-sm-center']/div[@class='col-sm-10 col-md-8 col-lg-5']/div[@class='card']/div[@class='card-body']/form[@id='form-login']/div[@class='text-end']/button[@class='btn btn-primary']")));
        login.click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // admin navigates to the catalog page
    @And("Admin navigates to Catalog")
    public void adminOnCatalog() {
        // find the catalog button and click it
        WebElement catalogButton = AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-catalog']/a[@class='parent collapsed']")));
        catalogButton.click();
    }

    // admin navigates to the product page
    @And("Admin clicks on Products")
    public void adminClicksProducts() {
        // wait for products button to be visible
        WebElement productsButton = AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-catalog']/ul[@id='collapse-1']/li[2]/a")));
        // click on products button
        productsButton.click();
    }

    // admin navigates to the product
    @And("Admin clicks on Edit of a specific {string}")
    public void adminEditsProduct(String product) {
        // wait for filter sidebar to be visible, and then send the product name to the matching field
        AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-name']"))).sendKeys(product);
        // find the filter button and click it
        AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='button-filter']"))).click();
        // wait a few seconds for the page to load
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait for the product to be visible and click on the edit button
        AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='row']/div[@class='col col-lg-9 col-md-12']/div[@class='card']/div[@id='product']/form[@id='form-product']/div[@class='table-responsive']/table[@class='table table-bordered table-hover']/tbody/tr/td[@class='text-end'][3]/div[@class='btn-group']/a[@class='btn btn-primary']"))).click();
    }

    // admin navigates to data tab
    @And("Admin navigates to Data tab")
    public void adminClicksData() {
        // wait for the data tab to be visible and click it
        AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='card']/div[@class='card-body']/form[@id='form-product']/ul[@class='nav nav-tabs']/li[@class='nav-item'][2]/a[@class='nav-link']")));
        // find the filter button and click it
        AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='card']/div[@class='card-body']/form[@id='form-product']/ul[@class='nav nav-tabs']/li[@class='nav-item'][2]/a[@class='nav-link']"))).click();
    }

    // scroll to reach price field and change it
    @And("Admin changes the price to {string}")
    public void adminChangesPrice(String price) {
        // scroll until the price field is visible
        WebElement priceField = AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-price']")));
        ((JavascriptExecutor) AdminDriver).executeScript("arguments[0].scrollIntoView(true);", priceField);
        // save the old price
        oldPrice = Integer.parseInt(priceField.getAttribute("value"));
        // clear the price field
        priceField.clear();
        // fill the price field with the new price
        priceField.sendKeys(price);
    }

    // admin saves the changes
    @And("Admin clicks on Save")
    public void adminSavesChanges() {
        // scroll back up to the top of the page
        ((JavascriptExecutor) AdminDriver).executeScript("window.scrollTo(0, 0)");
        // find the save button and click it
        AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='page-header']/div[@class='container-fluid']/div[@class='float-end']/button[@class='btn btn-primary']"))).click();
    }

    // check that the price was changed
    @Then("Price successfully changed to {string}")
    public void priceChanged(String price) {
        // wait for the success message to be visible
        AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@id='container']/div[@id='alert']/div[@class='alert alert-success alert-dismissible']")));
        // scroll until the price field is visible
        WebElement priceField = AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-price']")));
        ((JavascriptExecutor) AdminDriver).executeScript("arguments[0].scrollIntoView(true);", priceField);
        // check that the price field contains the new price
        assert(priceField.getAttribute("value").equals(price));
    }

    /*
    TO FIX
    // after all scenarios are done
    @After
    public void tearDown() {
        // remove the products from the cart of user driver
        WebElement cartInformation = UserDriver.findElement(By.xpath("//*[@id='header-cart']/div[1]/button[1]"));
        cartInformation.click();
        WebElement removeButton = UserDriver.findElement(By.xpath("//*[@id='header-cart']/div/ul/li/table/tbody/tr/td/form/button/i[1]"));
        removeButton.click();
        // change the price back to the original price on admin's driver
        WebElement priceField = AdminWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-price']")));
        priceField.clear();
        priceField.sendKeys(String.valueOf(oldPrice));
        AdminWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@class='page-header']/div[@class='container-fluid']/div[@class='float-end']/button[@class='btn btn-primary']"))).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // close the browsers
        UserDriver.quit();
        AdminDriver.quit();
    }
     */
}
