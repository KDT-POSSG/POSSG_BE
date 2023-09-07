package possg.com.a.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.DeliveryDto;
import possg.com.a.dto.DeliveryParam;
import possg.com.a.service.DeliveryService;
import possg.com.a.util.SecurityConfig;

@RestController
public class DeliveryController {

	@Autowired
	DeliveryService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
	
	// 점주 배달 추가
	@PostMapping("convAddDelivery")
	public String convAddDelivery(ConvenienceDto dto, @CookieValue("accessToken") String accessToken) {
		System.out.println("DeliveryController addcustomer " + new Date());
		
		//토큰 파싱
				accessToken = accessToken.replace("Bearer ", "");
				 
				 JwtParser jwtParser = Jwts.parserBuilder()
			    		    .setSigningKey(securityConfig.securityKey)
			    		    .build();
			    	
			        Claims refreshClaims = jwtParser.parseClaimsJws(accessToken).getBody();
				 
			        int convSeq = refreshClaims.get("convSeq", Integer.class);		 
				 	 
				 dto.setConvSeq(convSeq);
				 
				 int count = service.convAddDelivery(dto);
		
		if(count !=  0) {
			return "YES";
		}		
		return "NO";
	}


	// 배달목록 누르면 detail 페이지 상세보기
	@GetMapping("allDeliveryList")
	public List<DeliveryDto> allDelivery(@RequestParam String ref) {
		System.out.println("DeliveryController allDeliveryList " + new Date());
		
		List<DeliveryDto> delivery = service.allDeliveryList(ref);		
		
		System.out.println(delivery);			
		return delivery;	
	}
	
	

/*
	@GetMapping("allDeliveryList")
	public Map<String, Object>  allDelivery(DeliveryDto param) {
		System.out.println("DeliveryController allDeliveryList " + new Date());
		
		List<DeliveryDto> delivery = service.allDeliveryList(param);
		
		System.out.println(delivery);
		
		int count = service.getDeliveryCount(param);
		
		//총페이지 수
		int page = count/10;
		
		if((count % 10) > 0) { 
			page = page + 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delivery", delivery);
		map.put("page", page);
		map.put("pageNumber", param.getPageNumber());
		map.put("cnt", count);
				
		return map;	
	}
	
	
*/	
	
	
	// 배달 상품 리스트
	@PostMapping("getAllDeliveryOrderList")
	public List<DeliveryDto> getAllDeliveryOrderList(){
		System.out.println("DeliveryController getAllDeliveryOrderList() " + new Date());
		
		List<DeliveryDto> list = service.getAllDeliveryOrderList();
		
		return list;
	}
	
	
	/*@PostMapping("getRefDeliveryOrderList")
	public String getRefDeliveryOrderList(DeliveryDto dto) {
		System.out.println("DeliveryController getRefDeliveryOrderList() " + new Date());
		
		
		if(!dto.getRef().equals("0")) {
			
		}*/
	
	
	
	
	
}
