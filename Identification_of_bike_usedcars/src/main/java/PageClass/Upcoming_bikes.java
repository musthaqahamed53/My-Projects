package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Upcoming_bikes extends Base_class{
	//WebDriver driver; public ExtentTest logger;
	public Upcoming_bikes(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	@FindBy(xpath="//select[@id='makeId']")
	WebElement select_drop;
	public Honda_bikes chooseHonda() {
		Select s = new Select(select_drop);
		s.selectByVisibleText("Honda");
		logger.log(Status.PASS,"Brand Honda is selected");
		Honda_bikes h_b = new Honda_bikes(driver,logger);
		PageFactory.initElements(driver, h_b);
		return h_b;
		//return PageFactory.initElements(driver,Honda_bikes.class);
	}


}
