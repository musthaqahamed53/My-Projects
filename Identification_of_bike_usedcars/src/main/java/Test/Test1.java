package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageClass.HomePage;
import PageClass.Honda_bikes;
import PageClass.Upcoming_bikes;
import PageClass.Used_Cars;
import Utils.ExtentReportManager;


public class Test1 extends BaseTestClass{
	HomePage hp;// = new HomePage(driver);
	Honda_bikes hb ;
	Upcoming_bikes ub ;
	Used_Cars uc;// = new Used_Cars(driver);
	//ExtentReports report; //ExtentTest logger;
	
	@Test
	public void test1() throws IOException {
		logger = report.createTest("Identify bike and used car details");
		/*Base_class bc = new Base_class();
		bc.invokebrowser("chrome");*/
		
		//hp = openWebURL();
		//hp.signin();
		hp=GetWebDriver();
	    ub = hp.clickbikelink();

		hb= ub.chooseHonda();
		hb.getDetails();
		try {
			hb.openExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uc = hb.clickusedcars();
		try {
			uc.gettop5car();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hp= uc.clickHome();
		hp.clickGoogle();
	}
	/*public HomePage openWebURL() {
		driver.get("https://www.zigwheels.com/");*/
		

}
