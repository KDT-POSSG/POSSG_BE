package possg.com.a.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.CustomerDto;
import possg.com.a.service.CustomerService;
import possg.com.a.util.SecurityConfig;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
	 int startingNumber = 000000; // 초기 숫자 설정
     int numberOfIncrements = 1; // 몇 번 증가시킬지 설정
	
	@PostMapping("addcustomer")
	public String addcustomer(CustomerDto dto, @CookieValue("accessToken") String accessToken) {
		System.out.println("CustomerController addcustomer " + new Date());
		
		System.out.println(dto);
		
		//토큰 파싱
		accessToken = accessToken.replace("Bearer ", "");
		 
		 JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(securityConfig.securityKey)
	    		    .build();
	    	
	        Claims refreshClaims = jwtParser.parseClaimsJws(accessToken).getBody();
		 
	        int convSeq = refreshClaims.get("convSeq", Integer.class);		 
		 	 
		 dto.setConvSeq(convSeq);
		
		 
		 // 이름을 따로 받지 않으니 일단 anonymous로 이름 지정
		if(dto.getCustomerName() == null) {
			dto.setCustomerName("anonymous");
		}
		
		
		// 휴대폰 번호랑 비밀번호만 받으니 그 휴대폰 번호가 db에 같은 값이 없으면 user + (점포번호) + 무작위난수로 저장
		CustomerDto cus = service.getcustomer(dto); // cus가 null이면 db에 없다는 거니까
		if(cus == null) {
			
			dto.setCustomerId("user-" + dto.getConvSeq() + "-" + generateUUID());
			
			int count = service.addcustomer(dto);
			if(dto.getPhoneNumber() != null && dto.getPinNumber() != 0 && count != 0) {
				System.out.println(dto);
				return "YES";
			}
			
		}
		
		return "NO";
	}
	
	public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

}
