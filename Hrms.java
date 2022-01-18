import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hrms {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chrome);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        WebElement a = driver.findElement(By.name("txtUsername"));
        a.sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.linkText("Add Employee")).click();
        driver.findElement(By.id("firstName")).sendKeys("Divyash");
        driver.findElement(By.id("lastName")).sendKeys("Singh");
        driver.findElement(By.id("chkLogin")).click();
        driver.findElement(By.id("user_name")).sendKeys("divyash07");
        driver.findElement(By.id("user_password")).sendKeys("Divyash70");
        driver.findElement(By.id("re_password")).sendKeys("Divyash70");
        driver.findElement(By.id("btnSave")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Admin")).click();
        driver.findElement(By.id("menu_admin_UserManagement")).click();
        driver.findElement(By.id("searchSystemUser_userName")).sendKeys("divyash07");
        driver.findElement(By.id("searchBtn")).click();
        String expectedName = "Divyash Singh";
        WebElement actualName = driver.findElement(By.xpath("//td[contains(text(),'Divyash Singh')]"));
        System.out.println(actualName.getText());
        Assert.assertEquals("Name is not added successfully",expectedName,actualName.getText());

        }
}