import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriven {
   void launch(String url) throws IOException, InterruptedException {
        String uname,pass;
        WebDriver d=new ChromeDriver();
        d.manage().window().maximize();
        String p=System.getProperty("user.dir")+"/Test/Login.xlsx";
        System.out.println(p);
        FileInputStream f=new FileInputStream(p);
        XSSFWorkbook wb= new XSSFWorkbook(f);
        XSSFSheet sheet= wb.getSheet("Login_details");
        System.out.println(sheet.getLastRowNum());
        d.get(url);
		for(int i=1;i<=sheet.getLastRowNum();i++) {

            uname=sheet.getRow(i).getCell(0).getStringCellValue();
			d.findElement(By.name("txtUsername")).sendKeys(uname);
			pass=sheet.getRow(i).getCell(1).getStringCellValue();
	        d.findElement(By.id("txtPassword")).sendKeys(pass);
	        d.findElement(By.id("btnLogin")).click();
            String expected="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
            FileOutputStream o = new FileOutputStream(p);
            XSSFCell cell = sheet.getRow(i).createCell(2);
            String actualname = d.getCurrentUrl();
            if(expected.equals(actualname)){
                cell.getRow().createCell(2).setCellValue("pass");
            }else{
                cell.getRow().createCell(2).setCellValue("fail");
            }
            wb.write(o);
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        DataDriven a=new DataDriven();
        String u="https://opensource-demo.orangehrmlive.com/";
        a.launch(u);
    }
}
