import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.Scanner;
import static java.lang.Math.random;

class Logi{
    WebDriver d=new ChromeDriver();

    Select drag;

    void login(String url,String username,String pass){
        d.get(url);
        d.manage().window().maximize();
        WebElement a=d.findElement(By.name("txtUsername"));
        a.sendKeys(username);
        d.findElement(By.id("txtPassword")).sendKeys(pass);
        d.findElement(By.id("btnLogin")).click();
    }
    void logout(){
        d.findElement(By.xpath("//a[@id='welcome']")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        d.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }
    void add_user(String fname,String lname,String uname,String pass){
        d.findElement(By.linkText("PIM")).click();
        d.findElement(By.linkText("Add Employee")).click();
        d.findElement(By.id("firstName")).sendKeys(fname);
        d.findElement(By.id("lastName")).sendKeys(lname);
        d.findElement(By.id("chkLogin")).click();
        d.findElement(By.id("user_name")).sendKeys(uname);
        d.findElement(By.id("user_password")).sendKeys(pass);
        d.findElement(By.id("re_password")).sendKeys(pass);
        d.findElement(By.id("btnSave")).click();
        System.out.println("username is: "+uname);
    }
    void verify_user(String uname){
        d.findElement(By.linkText("Admin")).click();
        d.findElement(By.id("menu_admin_UserManagement")).click();
        d.findElement(By.id("searchSystemUser_userName")).sendKeys(uname);
        d.findElement(By.id("searchBtn")).click();
        String expectedName = "Divyash Singh";
        WebElement actualName = d.findElement(By.xpath("//td[contains(text(),'Divyash Singh')]"));
        System.out.println(actualName.getText());
        Assert.assertEquals("Name is not added successfully",expectedName,actualName.getText());
    }
    void recruit(String date,String dat) throws InterruptedException {
        d.findElement(By.linkText("Recruitment")).click();
        d.findElement(By.linkText("Candidates")).click();
        drag= new Select(d.findElement(By.id("candidateSearch_jobTitle")));
        drag.selectByValue("7");
        Thread.sleep(3000);
        drag = new Select(d.findElement(By.id("candidateSearch_jobVacancy")));
        drag.selectByValue("1");
        Thread.sleep(3000);
        drag = new Select(d.findElement(By.id("candidateSearch_hiringManager")));
        drag.selectByValue("2");
        Thread.sleep(2000);
        d.findElement(By.id("candidateSearch_fromDate")).clear();
        d.findElement(By.id("candidateSearch_fromDate")).sendKeys(date);
        d.findElement(By.id("candidateSearch_toDate")).clear();
        d.findElement(By.id("candidateSearch_toDate")).sendKeys(dat);
        Thread.sleep(2000);
        d.findElement(By.id("btnSrch")).click();
    }
    void delete_user(String uname){
        d.findElement(By.linkText("PIM")).click();
        d.findElement(By.linkText("Employee List")).click();
        d.findElement(By.id("empsearch_id")).sendKeys("uid");
        d.findElement(By.id("searchBtn")).click();
        d.findElement(By.name("chkSelectRow[]")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        d.findElement(By.id("btnDelete")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        d.findElement(By.id("dialogDeleteBtn")).click();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
    }
}


public class Hrms1 extends Logi {
    public static void main(String[] args) throws InterruptedException{
        String username="admin";
        String pass="admin123";
        String fname="divyash",lname="Singh",password="Divyash70";
        String uname="divyash07"+random();
        String url="https://opensource-demo.orangehrmlive.com/";
        //System.out.println("Enter search from date as YYYY-MM-DD");
        //Scanner obj=new Scanner(System.in);
        //String date=obj.nextLine();
        //System.out.println("Enter search to date as YYYY-MM-DD");
        //String dat=obj.nextLine();
        WebDriverManager.chromedriver().setup();
        Logi l=new Logi();
        l.login(url,username,pass);
        l.add_user(fname,lname,uname,password);
        Thread.sleep(3000);
        l.verify_user(uname);
        //l.delete(uname);
        //l.recruit(date,dat);
        //l.logout();
    }
}