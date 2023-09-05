package possg.com.a.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static Date StringToDate(String str) {
	    
	        // SimpleDateFormat을 사용하여 문자열을 Date로 파싱
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsedDate = new Date();
	        
	        try {
	            parsedDate = dateFormat.parse(str);
	            System.out.println("파싱된 날짜: " + parsedDate);
	            
	            return parsedDate;
	        } 
	        
	        catch (ParseException e) {
	            e.printStackTrace();
	            System.err.println("날짜 파싱 오류: " + e.getMessage());
	        }
	        
			return parsedDate;
	 }
	
}
