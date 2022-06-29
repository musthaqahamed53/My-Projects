package Utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//import com.mongodb.MapReduceCommand.OutputType;

public class CaptureScreenShot {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");
	static Date date = new Date();
	//static String d = date.toString();
	
	
	//@SuppressWarnings("static-access")
	public static void captureScreen(WebDriver driver, String name) throws IOException{
		File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils fileUtils = new FileUtils();
		FileUtils.copyFile(Screenshot, new File(System.getProperty("user.dir")+"\\UITILS\\screenshot\\" +name+dateFormat.format(date)+".jpg"));
	}
		
		

}
