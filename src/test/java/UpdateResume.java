
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdateResume {

    @Test(description = "Automation Test case for Naukri.com")
    public void UpdateResume() throws InterruptedException, IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.google.com/nlogin/login?err=1");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("headless_screenshot.png"));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='usernameField']")));

        driver.findElement(By.xpath("//*[@id='usernameField']")).sendKeys("kandpaltanuj25@gmail.com");
        driver.findElement(By.xpath("//*[@id='passwordField']")).sendKeys("Kandpaltanuj25@@");

        driver.findElement(By.xpath("(//*[@type='submit'])[1]")).click();
        w.until(ExpectedConditions.titleContains("Home | Mynaukri"));

        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nI-gNb-drawer__bars']")));
        w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='nI-gNb-drawer__bars']"))));
        driver.findElement(By.xpath("//div[@class='nI-gNb-drawer__bars']")).click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nI-gNb-info__sub-link']")));
        driver.findElement(By.xpath("//a[@class='nI-gNb-info__sub-link']")).click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.className("dummyUpload")));
        // Locate the hidden file input element
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

        // Make the hidden input visible using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);

        // Upload the file using sendKeys
        fileInput.sendKeys(System.getProperty("user.dir") +"\\src\\main\\resources\\Tanuj_Kandpal_Resume.pdf");

        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Resume has been successfully uploaded.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Resume has been successfully uploaded.')]")).isDisplayed());

        Thread.sleep(3000);
        System.out.println("Resume Updated Successfully");
        driver.quit();
    }
}
