import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import static java.lang.Math.random;

class Log{
    WebDriver d=new ChromeDriver();
    String username="divyash07"+random();
    void login(){
        d.get("https://opensource-demo.orangehrmlive.com/");
        d.manage().window().maximize();
        WebElement a=d.findElement(By.name("txtUsername"));
        a.sendKeys("Admin");
        d.findElement(By.id("txtPassword")).sendKeys("admin123");
        d.findElement(By.id("btnLogin")).click();
    }
    void add_user(){
        d.findElement(By.linkText("PIM")).click();
        d.findElement(By.linkText("Add Employee")).click();
        d.findElement(By.id("firstName")).sendKeys("Divyash");
        d.findElement(By.id("lastName")).sendKeys("Singh");
        d.findElement(By.id("chkLogin")).click();
        d.findElement(By.id("user_name")).sendKeys(username);
        d.findElement(By.id("user_password")).sendKeys("Divyash70");
        d.findElement(By.id("re_password")).sendKeys("Divyash70");
        d.findElement(By.id("btnSave")).click();
    }
    void verify_user(){
        d.findElement(By.linkText("Admin")).click();
        d.findElement(By.id("menu_admin_UserManagement")).click();
        d.findElement(By.id("searchSystemUser_userName")).sendKeys(username);
        d.findElement(By.id("searchBtn")).click();
        String expectedName = "Divyash Singh";
        WebElement actualName = d.findElement(By.xpath("//td[contains(text(),'Divyash Singh')]"));
        System.out.println(actualName.getText());
        Assert.assertEquals("Name is not added successfully",expectedName,actualName.getText());
    }
    void recruit() throws InterruptedException {
        d.findElement(By.linkText("Recruitment")).click();
        d.findElement(By.linkText("Candidates")).click();
        Select drag = new Select(d.findElement(By.id("candidateSearch_jobTitle")));
        drag.selectByValue("25");
        Thread.sleep(3000);
        drag = new Select(d.findElement(By.id("candidateSearch_jobVacancy")));
        drag.selectByVisibleText("Payroll Administrator");
        Thread.sleep(3000);
        drag = new Select(d.findElement(By.id("candidateSearch_hiringManager")));
        drag.selectByValue("7");
        Thread.sleep(3000);
        d.findElement(By.id("candidateSearch_fromDate")).clear();
        //d.findElement(By.id("candidateSearch_fromDate")).sendKeys("2022-02-01");
        for(int y=2022;y<2023;y++){
            for (int m = 3; m <= 12; m++) {
                for (int da = 1; da <= 31; da++) {
                    d.findElement(By.id("candidateSearch_fromDate")).sendKeys("y-m-da");
                    Thread.sleep(1000);
                }
            }
        }
    }
    void delete_user(){
        d.findElement(By.linkText("PIM")).click();
        d.findElement(By.linkText("Employee List")).click();
        d.findElement(By.id("empsearch_id")).sendKeys("");
        d.findElement(By.id("searchBtn")).click();
        d.findElement(By.name("chkSelectRow[]")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        d.findElement(By.id("btnDelete")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        d.findElement(By.id("dialogDeleteBtn")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
    }
    void logout(){
        d.findElement(By.xpath("//a[@id='welcome']")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        d.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }
}


public class Hrms1 extends Log {
    public static void main(String[] args) throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        Log l=new Log();
        l.login();
        //l.add_user();
        Thread.sleep(3000);
        //l.verify_user();
        l.recruit();
        l.d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        //l.logout();
    }
}