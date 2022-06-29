package PageClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.CaptureScreenShot;

public class Used_Cars extends Base_class{
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");
	static Date date = new Date();
	private XSSFWorkbook workbook = null;
	private XSSFSheet ws = null; XSSFCell cell = null; XSSFRow row = null;
	private String path=System.getProperty("user.dir")+"\\UITILS\\ZIGWHEELS.xlsx";
	private FileInputStream input = null;
	private FileOutputStream out = null; 
	public Used_Cars(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	@FindBy(xpath="//*[@id='seoMore']") WebElement readmore;
	@FindBy(xpath="/html/body/div[10]/div[1]/ol/li[1]/a") WebElement homepage;
	List<String>top5car = new ArrayList<String>();
	public void gettop5car() throws IOException{
		readmore.click();
		CaptureScreenShot.captureScreen(driver,"top brands of used cars");
		System.out.println("\nTop Brand of Used cars in Chennai");
		for(int i=1;i<6;i++) {
			top5car.add(driver.findElement(By.xpath("//div[@id='models-table']/table/tbody/tr["+i+"]/td[1]")).getText());
		}
		File f = new File(path);
			input = new FileInputStream(f);
		workbook = new XSSFWorkbook(input);
		ws = workbook.createSheet("Used car details"+dateFormat.format(date));
		for(int j=0; j<5;j++) {
			row= ws.createRow(j);
			StringBuilder sb =  new StringBuilder();
			sb.append(top5car.get(j));
			row.createCell(0).setCellValue(sb.toString());
			System.out.println((j+1)+" "+ sb.toString());
			
			
		}
		out = new FileOutputStream(f);
		workbook.write(out);
		workbook.close();
		logger.log(Status.PASS, "Top 5 Used car details entered in EXCEL-sheet");
		
	}
	public HomePage clickHome() {
		homepage.click();
		logger.log(Status.PASS, "Returned to homepage");
	    HomePage h_p = new HomePage(driver,logger);
		PageFactory.initElements(driver,h_p);
		return h_p;
		//return PageFactory.initElements(driver,HomePage.class);
		
	}

}
