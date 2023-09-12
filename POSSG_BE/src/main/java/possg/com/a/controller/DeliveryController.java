package possg.com.a.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import possg.com.a.dto.DeliveryListDto;
import possg.com.a.dto.DeliveryParam;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.DeliveryService;
import possg.com.a.service.ProductService;
import possg.com.a.util.SecurityConfig;

@RestController
public class DeliveryController {

	@Autowired
	DeliveryService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
	@Autowired
	ProductService productService;
	
	
	// 점주 배달 추가
	@PostMapping("convAddDelivery")
	public String convAddDelivery(ConvenienceDto dto, @CookieValue("accessToken") String accessToken) {
		System.out.println("DeliveryController convAddDelivery " + new Date());
		
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
	
	// 배달 장바구니에 추가
	@PostMapping("callAddDelivery")
	public String callAddDelivery(DeliveryDto dto) {
		System.out.println("DeliveryController callAddDelivery " + new Date());
		
		// 갯수에 따라 가격 맞춰서 넣기			
		ProductDto product = new ProductDto();
		
		product.setProductName(dto.getProductName());
		
		List<ProductDto> prodto = productService.findProductName(product);
		
		dto.setPrice(dto.getQuantity() * prodto.get(0).getPrice());
		
		// 성공 실패
		int count = service.callAddDelivery(dto);						
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 배달 장바구니 목록 가져오기
	
	
	
	// 배달 주문
	@PostMapping("insertDeliveryList")
	public String insertDeliveryList(DeliveryListDto dto) {
		System.out.println("DeliveryController callAddDelivery " + new Date());
			
		DeliveryDto delivery = new DeliveryDto();
		
		delivery.setRef(dto.getDelRef());
		
		delivery.setUserId(dto.getUserId());

		List<DeliveryDto> deli = service.selectDelivery(delivery);
		int price = 0;
		int quantity = 0;
		for(DeliveryDto forDto :deli) {
			price = price + forDto.getPrice();
			quantity = quantity + forDto.getQuantity();		
		}		
		
		 // 현재 날짜와 시간 얻기
        LocalDateTime currentDateTime = LocalDateTime.now();        
        // 날짜와 시간을 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        formattedDateTime = formattedDateTime.replaceAll("[^\\d]", "");
        
        dto.setDelTotalPrice(price);
		dto.setDelTotalNumber(quantity);
		dto.setDelRef(formattedDateTime);
		
		int count = service.insertDeliveryList(dto);
		
		delivery.setRef(formattedDateTime);
		service.updateDelivery(delivery);

		if(count != 0) {
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
