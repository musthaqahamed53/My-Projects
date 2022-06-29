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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.CaptureScreenShot;

public class Honda_bikes extends Base_class{
	private XSSFWorkbook workbook = null;
	private XSSFSheet ws = null; XSSFCell cell = null; XSSFRow row = null;
	private String path=System.getProperty("user.dir")+"\\UITILS\\ZIGWHEELS.xlsx";
	private FileInputStream input = null;
	private FileOutputStream out = null; 
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");
	static Date date = new Date();
	List<String> bikeName=new ArrayList<String>();
	List<String>bikePrice = new ArrayList<String>(); 
	List<List>Datalist = new ArrayList<List>();List<String>bikeDate = new ArrayList<String>();
	public Honda_bikes(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}	
	
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']") WebElement viewmore;
	@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']") List<WebElement> bikename;
	@FindBy(xpath="//div[@class='b fnt-15']") List<WebElement> bikeprice;
	@FindBy(xpath="//div[@class='clr-try fnt-14']") List<WebElement>bikedate;
	@FindBy(xpath="//*[@id='headerNewNavWrap']/nav/div/ul/li[6]/a") WebElement usedcar;
	@FindBy(xpath="//*[@id='headerNewNavWrap']/nav/div/ul/li[6]/ul/li/div[2]/ul/li[5]/a")WebElement chennai;
	

	public void getDetails() {
		/*Actions action = new Actions(driver);
		action.moveToElement(viewmore).build().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewmore.click();*/
		System.out.println("Details of HONDA bikes under 4 Lakhs which are expected to launch ");
	    for(int x=0; x<bikename.size();x++) {
	    	String price = bikeprice.get(x).getText().replaceAll("Rs.","").replaceAll("Lakh","");	    	
	    	if(price.equals("")) {
	    	}else {
		    	float p = Float.parseFloat(price);
		    	
		    	if(p<4.0) {
		    		String date = bikedate.get(x).getText().substring(14);
		    		bikeName.add(bikename.get(x).getText());
		    		bikePrice.add(bikeprice.get(x).getText());
		    		bikeDate.add(date);
		    		System.out.println(bikename.get(x).getText()+" " + bikeprice.get(x).getText()+" "+ date);	    		
		    		} 		    	
	    	}
	    	
} logger.log(Status.PASS, "Upcoming Bike details gathered"); 	    
  
	    }
	public void openExcel() throws IOException {
		Datalist.add(bikeName);
		Datalist.add(bikePrice);
		Datalist.add(bikeDate);
		File f = new File(path);
		input = new FileInputStream(f);	
		workbook = new XSSFWorkbook(input);
		ws = workbook.createSheet("HONDA-DETAILS-3"+dateFormat.format(date));
		for (int i=0; i<bikeName.size();i++) {
			row= ws.createRow(i);
			for(int j=0;j<Datalist.size();j++) {
				cell = row.createCell(j); StringBuilder sb = new StringBuilder();
				List<List>v=Datalist.get(j);
				//System.out.println(v.get(i));
				sb.append(v.get(i));
				cell.setCellValue(sb.toString());	
				}}
		out = new FileOutputStream(f);
		workbook.write(out);
		workbook.close();
		logger.log(Status.PASS, "Upcoming Bike details entered in EXCEL-Sheet");
		}	
	public Used_Cars clickusedcars() throws IOException {
		Actions action = new Actions(driver);
		action.moveToElement(usedcar).build().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CaptureScreenShot.captureScreen(driver,"click_usedcars");
		logger.log(Status.PASS,"Used cars is clicked");
		chennai.click();
		/*Alert alt = driver.switchTo().alert();
		alt.dismiss();	*/
		logger.log(Status.PASS, "Used cars in chennai is selected");
	    Used_Cars u_c = new Used_Cars(driver,logger);
	    PageFactory.initElements(driver, u_c);
	    return u_c;
		//return PageFactory.initElements(driver,Used_Cars.class);
		
	}

}
