package possg.com.a.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.Cancel;
import possg.com.a.dto.ItemsDto;
import possg.com.a.dto.PaymentDto;
import possg.com.a.dto.PaymentParam;
import possg.com.a.service.ItemsService;
import possg.com.a.service.PaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@Autowired
	ItemsService service2;
	
	// 결제 정보 추가
	@PostMapping("addpayment")
	public String addpayment(@RequestBody PaymentDto dto) { 
		System.out.println("PaymentController addpayment " + new Date());
		
		System.out.println(dto.toString());
		
		int count = service.addpayment(dto);
		if(count > 0) {
			return "YES";
		}
		return "NO";
				
	}
	
	// 결제 취소 
	@PostMapping("cancelpayment")
	public String cancelpayment(@RequestParam String receiptId) {
		System.out.println("PaymentController cancelpayment " + new Date());
		
		String check = "";
		
		try {
			
			// REST API KEY, PRIVATE KEY
		    Bootpay bootpay = new Bootpay("64f673d8e57a7e001bbb128d", "0qYZjYwZDh9zx12dOn9gbQlkcSP2VsdLkkKJHTs3+BE=");
		    HashMap<String, Object> token = bootpay.getAccessToken();

		    System.out.println("토큰: " + token.get("access_token")); // 토큰 확인
		    
		    // 토큰 에러 안나려면 꼭 허가 IP를 넣어줘야함 --> FIREWALL_BLOCKED때문에
		    if(token.get("error_code") != null) { 
		    	check = (String)token.get("error_code");
		    	return check;
		    }
		    
		    Cancel cancel = new Cancel();
		    cancel.receiptId = receiptId;
		    cancel.cancelUsername = "관리자";
		    cancel.cancelMessage = "테스트 결제 취소";

		    HashMap<String, Object> res = bootpay.receiptCancel(cancel);
		    if(res.get("error_code") == null) { //success
		        System.out.println("receiptCancel success: " + res);
		        
		        int count = service.cancelpayment(receiptId);
				if(count > 0) {
					check = "YES";
				}
				else{
					check = "CANCEL YES BUT UPDATE NO";
				}
				
		    } 
		    
		    else {
		        System.out.println("receiptCancel false: " + res);
		        check = "ERROR CANCEL";
		    }
		} 
		
		catch (Exception e) {
		    e.printStackTrace();
		}
		
		return check;
				
	}
	
	// 전체 결제 목록
	@GetMapping("paymentlist")
	public Map<String, Object> paymentlist(@RequestParam int convSeq){
		System.out.println("PaymentController paymentlist " + new Date());
		
		List<PaymentParam> list = service.paymentlist(convSeq);
		
		int count = service.getallpayment(convSeq);
		
		// 결제정보, 상세목록정보
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", count);
		map.put("list", list);
		
		return map;
	};
	
	// 단일 결제 정보
	@GetMapping("paymentOneList")
	public Map<String, Object> paymentOneList(@RequestParam String receiptId) {
		System.out.println("PaymentController paymentOneList " + new Date());
		
		PaymentParam param = service.paymentOneList(receiptId);
		System.out.println(param.toString());
		
		List<ItemsDto> list = service2.searchItems(receiptId);
		System.out.println(list.toString());
		
		// 결제정보, 상세목록정보
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", param);
		map.put("list", list);
		
		return map;
	};
	
	// 단일 영수증 번호 결제 정보
	@GetMapping("paymentNumList")
	public Map<String, Object> paymentNumList(@RequestParam String receiptId){
		System.out.println("PaymentController paymentNumList " + new Date());
		
		List<PaymentParam> list = service.paymentNumList(receiptId);
		
		int count = list.size();
		//System.out.println(count);
		
		// 결제정보, 상세목록정보
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", count);
		map.put("list", list);
		
		return map;
	};
	
	
}
