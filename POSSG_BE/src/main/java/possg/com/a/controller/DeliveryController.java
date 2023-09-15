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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
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
	
	// 배달 장바구니에 추가
	@PostMapping("callAddDelivery")
	public String callAddDelivery(DeliveryDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("DeliveryController callAddDelivery " + new Date());
		
		if(tokenHeader == null) {
			return null;
		}
		
		int customerSeq = tokenParser(tokenHeader);
		
		CustomerDto userId = service.selectCustomer(customerSeq);
		
		// 갯수에 따라 가격 맞춰서 넣기			
		ProductDto product = new ProductDto();
		
		product.setProductName(dto.getProductName());
		
		List<ProductDto> prodto = productService.findProductName(product);
		
		dto.setPrice(dto.getQuantity() * prodto.get(0).getPrice());
		dto.setUserId(customerSeq);
		dto.setLocation(userId.getLocation());
		dto.setBranchName(userId.getBranchName());
		
		
		System.out.println(dto);
		// 성공 실패
		int count = service.callAddDelivery(dto);						
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 주문전 배달 장바구니 목록 보여주기
	@GetMapping("selectDelivery")
	public List<DeliveryDto>selectDelivery(@RequestHeader("USTK") String tokenHeader) {
		System.out.println("DeliveryController selectDelivery " + new Date());
		
		if(tokenHeader == null) {
			return null;
		}
		
		int customerSeq = tokenParser(tokenHeader);
		
		DeliveryDto newDeli = new DeliveryDto();
		
		newDeli.setUserId(customerSeq);
		
		List<DeliveryDto> deli = service.selectDelivery(newDeli);
		
		System.out.println(deli);
		
		return deli;
	}
	
	// 
	// 배달 주문하기 
	@PostMapping("insertDeliveryList")
	public String insertDeliveryList(DeliveryListDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("DeliveryController callAddDelivery " + new Date());
		
		if(tokenHeader == null) {
			return "NO";
		}
		
		int customerSeq = tokenParser(tokenHeader);
			
		DeliveryDto delivery = new DeliveryDto();
		
		delivery.setUserId(customerSeq);

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
        
        dto.setUserId(delivery.getUserId());
        dto.setDelTotalPrice(price);
		dto.setDelTotalNumber(quantity);
		dto.setDelRef(formattedDateTime);
		
		if(dto.getDelTotalNumber() == 0 || dto.getDelTotalPrice() == 0) {
			System.out.println("물건이 안담겨 있음");
			return "NO";
		}
		
		int count = service.insertDeliveryList(dto);
		
		delivery.setRef(formattedDateTime);
		service.updateDelivery(delivery);

		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	// -----------------------------------여기서 부터는 점주 배달 기능입니다 ----------------------------------------------
	
	// 점주 배달 추가
		@PostMapping("convAddDelivery")
		public String convAddDelivery(ConvenienceDto dto, @RequestHeader("accessToken") String accessToken) {
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
	/*
		// convenience token에서 branch_name을 추출해서 delivery테이블에 branch_name 같고 order_status != 0 인 목록을 가져와서 ref같은거 끼리 묶어서 보여주기
		@GetMapping("convenienceDeliveryList")
		public List<DeliveryDto> convenienceDeliveryList(@RequestHeader("accessToken") String tokenHeader){
			System.out.println("DeliveryController convenienceDeliveryList " + new Date());
			
			// "Bearer " 문자열을 제거하여 실제 토큰을 추출
		    String accessToken = tokenHeader.replace("Bearer ", "");

		    // JWT 토큰 검증
		    	JwtParser jwtParser = Jwts.parserBuilder()
		    		    .setSigningKey(securityConfig.securityKey)
		    		    .build();

		        Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();

		        // 지점명 추출
		        String branchName = claims.get("branchName", String.class);
	
		}
		*/

		// 배달목록 누르면 detail 페이지 상세보기
		@GetMapping("allDeliveryList")
		public List<DeliveryDto> allDelivery(@RequestParam String ref) {
			System.out.println("DeliveryController allDeliveryList " + new Date());
			
			List<DeliveryDto> delivery = service.allDeliveryList(ref);		
			
			System.out.println(delivery);			
			return delivery;	
		}
		
		// 장바구니 삭제
		@PostMapping("deleteDelivery")
		public String deleteDelivery(DeliveryDto dto) {
			System.out.println("DeliveryController deleteDelivery " + new Date());
			
			int count = service.deleteDelivery(dto);
			
			if(count != 0) {
				return "YES";
			}
			return "NO";
		}
	
	
	/*
	// 배달 상품 리스트
	@PostMapping("getAllDeliveryOrderList")
	public List<DeliveryDto> getAllDeliveryOrderList(){
		System.out.println("DeliveryController getAllDeliveryOrderList() " + new Date());
		
		List<DeliveryDto> list = service.getAllDeliveryOrderList();
		
		return list;
	}
	*/
		
	// customerSeq 추출하는 로직
	public int tokenParser(String tokenHeader) {
		
		// "Bearer " 문자열을 제거하여 실제 토큰을 추출
	    String accessToken = tokenHeader.replace("Bearer ", "");

	    // JWT 토큰 검증
	    	JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(securityConfig.securityKey)
	    		    .build();

	        Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();

	        // 사용자 ID 추출
	        int customerSeq = claims.get("customerSeq", Integer.class);
	        
	        return customerSeq;
	}	
	
	
}
