package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageClass.HomePage;
import Utils.ExtentReportManager;

public class BaseTestClass {
	public static WebDriver driver; 
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;
	public HomePage GetWebDriver() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.zigwheels.com/");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		HomePage HP = new HomePage(driver,logger);
		PageFactory.initElements(driver, HP);
		return HP;
		
	}
	@AfterClass
	public void closebrowser() {
		report.flush();
		driver.close();
		
	}

}
