package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenCartActuator {
    private final static String CHROME_DRIVER_PATH = "Selenium/chromedriver.exe";

    private ChromeDriver driver;
    private WebDriverWait wait;

    // function that opens the browser and navigates to the website
    public void openHomePage() {
        // initialize objects of driver and wait
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // open the browser and navigate to the website
        driver.get("http://localhost/opencartsite/");

        // set the position of the browser window
        driver.manage().window().setPosition(new Point(700, 5));
    }

    public void user_is_logged_in_with_and(String email, String password) {
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='form-login']/div/button[1]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void userAddAProductToCart(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys(product);
        driver.findElement(By.xpath("//*[@id='search']/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='product-list']/div/div/div/a/img[1]")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
