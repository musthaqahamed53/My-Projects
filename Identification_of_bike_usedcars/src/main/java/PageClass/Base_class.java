package PageClass;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Test.BaseTestClass;
import Utils.ExtentReportManager;

public class Base_class extends BaseTestClass{
	//WebDriver driver;
	
	//public static ExtentTest logger;
	//logger = report.createTest("Identify bike and used car details");
	public Base_class(WebDriver driver,ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	

}
