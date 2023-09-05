//package posgg.com.a.bootpay;
//
//import java.util.HashMap;
//
//import kr.co.bootpay.Bootpay;
//
//public class test {
//	Bootpay bootpay = new Bootpay("[ REST API Application ID ]", "[ PRIVATE_KEY ]");
//
//	try {
//	    HashMap res = bootpay.getAccessToken();
//	    
//	    if(res.get("error_code") == null) { //success
//	        System.out.println("goGetToken success: " + res);
//	    } 
//	    
//	    else {
//	        System.out.println("goGetToken false: " + res);
//	    }
//	} 
//	
//	catch (Exception e) {
//	    e.printStackTrace();
//	}
//}
