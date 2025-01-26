
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdateResume {

    @Test(description = "Automation Test case for Naukri.com")
    public void UpdateResume() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.naukri.com/nlogin/login?err=1");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
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
        System.out.println("Process completed");
        driver.quit();
    }
}
