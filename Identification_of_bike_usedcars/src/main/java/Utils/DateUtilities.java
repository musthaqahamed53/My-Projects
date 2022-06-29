package Utils;

import java.util.Date;

public class DateUtilities {
	public static String getTimeStamp(){
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}

}

