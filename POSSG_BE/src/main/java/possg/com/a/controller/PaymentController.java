package possg.com.a.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import possg.com.a.dto.PaymentDto;
import possg.com.a.service.PaymentService;
/*
@RestController
//@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	// 결제 정보 추가
	@PostMapping("addkakaopayment")
	public String addKakaopayment(@RequestBody PaymentDto dto) { 
		System.out.println("PaymentController addpayment " + new Date());
		
		System.out.println(dto.toString());
		
		int count = service.addkakaopayment(dto);
		if(count > 0) {
			return "YES";
		}
		return "NO";
				
	}
	
	// 카카오 결제 취소 
	@PostMapping("cancelkaopayment")
	public String cancelKakaopayment(@RequestBody int seq) {
		System.out.println("PaymentController cancelkakaopayment " + new Date());
		
		int count = service.cancelkakaopayment(seq);
		if(count > 0) {
			return "YES";
		}
		return "NO";
				
	}
	
	
	// 결제정보
//    public PaymentDto getPayInfo(String token, String mId) { 
//        String buyer_name = "";
//        String buyer_phone = "";
//        String member_email = "";
//        String buyer_addrStr = "";
//        String buyer_postcode = "";
//        String buyer_addr = "";
//        String paid_at = "";
//        String buy_product_name = "";
//        String buyer_buyid = "";
//        String buyer_merid = "";
//        String amount = "";
//        String buyer_card_num = "";
//        String buyer_pay_ok = "";
//        long buyer_pay_price = 0L;
//        long paid_atLong = 0L;
//        long unixTime = 0L;
//        Date date = null;
//        
//        HttpClient client = HttpClientBuilder.create().build(); 
//        HttpGet get = new HttpGet("http:localhost:3000/" + mId + "/paid"); 
//        get.setHeader("Authorization", token); 
//        try { 
//            HttpResponse res = client.execute(get);
//            ObjectMapper mapper = new ObjectMapper(); 
//            String body = EntityUtils.toString(res.getEntity()); 
//            JsonNode rootNode = mapper.readTree(body); 
//            JsonNode resNode = rootNode.get("response"); 
//            System.out.println("wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww resNode: "+resNode);
//            //amount = resNode.get("amount").asText(); 
//            buyer_name = resNode.get("buyer_name").asText(); 
//            buyer_phone = resNode.get("buyer_tel").asText(); 
//            member_email = resNode.get("buyer_email").asText(); 
//            
//            buyer_addrStr = resNode.get("buyer_addr").asText(); 
//            buyer_postcode = resNode.get("buyer_postcode").asText(); 
//            buyer_addr = buyer_addrStr+" "+buyer_postcode; //주소에 우편번호 합치기
//            
//            paid_at = resNode.get("paid_at").asText(); //결제시간
//            buy_product_name = resNode.get("name").asText(); 
//            buyer_buyid = resNode.get("imp_uid").asText(); 
//            buyer_merid = resNode.get("merchant_uid").asText(); 
//            amount = resNode.get("amount").asText(); 
//            buyer_card_num = resNode.get("apply_num").asText(); 
//            buyer_pay_ok = resNode.get("status").asText(); 
//             
//            
//        } catch (Exception e) { 
//            e.printStackTrace(); 
//        } 
//        
//        buyer_pay_price = Long.parseLong(amount);
//        
//        // 카드 결제 시간 - 형식 바꾸기
//        paid_atLong = Long.parseLong(paid_at);
//        unixTime = paid_atLong * 1000;
//        date = new Date(unixTime);
//        
//        // 형식 바꾸기
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9")); // GMT(그리니치 표준시 +9 시가 한국의 표준시
//        String buy_date = sdf.format(date);
//        System.out.println("++++++++++++++++++++++++++++++++++++import date: "+buy_date);
//        
//        PaymentDto dto = new PaymentDto();
//        
//        return dto;
//    }

}
*/
