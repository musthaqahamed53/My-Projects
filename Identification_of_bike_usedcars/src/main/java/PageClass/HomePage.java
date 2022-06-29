package PageClass;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.CaptureScreenShot;

public class HomePage extends Base_class {
	
	//WebDriver driver; public ExtentTest logger;
	
	public HomePage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	
	}
	@FindBy(xpath="//*[@id='headerNewNavWrap']/nav/div/ul/li[3]/a")
	WebElement bikeslink;
	@FindBy(xpath="//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/ul/li[5]/a")
	WebElement upcomingbikes;
	
	public Upcoming_bikes clickbikelink() throws IOException {
		Upcoming_bikes u_b = new Upcoming_bikes(driver,logger);
		Actions action = new Actions(this.driver);
		action.moveToElement(bikeslink).build().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		upcomingbikes.click();
		CaptureScreenShot.captureScreen(driver,"click_upcoming_bikes");
		logger.log(Status.PASS,"Upcoming bikes clicked");
		//Upcoming_bikes u_b = new Upcoming_bikes(driver,logger);
		
		PageFactory.initElements(driver,u_b);			
		return u_b;
		//return PageFactory.initElements(driver,Upcoming_bikes.class);
	}
	@FindBy(xpath="//*[@id='forum_login_title_lg']") WebElement Signin;
	@FindBy(xpath="//*[@id='googleSignIn']") WebElement googleacc;
	@FindBy(xpath="//*[@id='identifierId']") WebElement mailid;
	@FindBy(xpath="//*[@id='password']/div[1]/div/div[1]/input") WebElement pwd;
	@FindBy(xpath="//*[@id='identifierNext']/div/button") WebElement next;
	public void clickGoogle() throws IOException {
		String parent = driver.getWindowHandle();
		Signin.click();
		//logger.log(Status.PASS, "Signin link is clicked");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CaptureScreenShot.captureScreen(driver,"click_google");
		googleacc.click();
		Set<String> multiple = driver.getWindowHandles();
		Iterator<String> I1 = multiple.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				mailid.sendKeys("Team6@gmail.com");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CaptureScreenShot.captureScreen(driver,"enter_mailid");
				next.click();
				System.out.println(driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/span/div/div")).getText());
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);}
			driver.switchTo().window(parent);

}	}


}
