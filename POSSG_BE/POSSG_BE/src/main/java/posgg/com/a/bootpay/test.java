package posgg.com.a.bootpay;

import java.util.HashMap;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.Cancel;

public class test {
	public static String cancle() {
		try {
		    Bootpay bootpay = new Bootpay("64f673d8e57a7e001bbb128d", "0qYZjYwZDh9zx12dOn9gbQlkcSP2VsdLkkKJHTs3+BE=\r\n"
		    		+ "");
		    HashMap token = bootpay.getAccessToken();
		    if(token.get("error_code") != null) { //failed
		        return "yes";
		    }
		    Cancel cancel = new Cancel();
		    cancel.receiptId = "64f7d4bce57a7e001fbe2cc7";
		    cancel.cancelUsername = "관리자";
		    cancel.cancelMessage = "테스트 결제";

		    HashMap res = bootpay.receiptCancel(cancel);
		    if(res.get("error_code") == null) { //success
		        System.out.println("receiptCancel success: " + res);
		    } else {
		        System.out.println("receiptCancel false: " + res);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return "YES";
	}

}
