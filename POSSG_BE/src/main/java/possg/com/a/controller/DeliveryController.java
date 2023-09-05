package possg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.DeliveryDto;
import possg.com.a.service.DeliveryService;
import possg.com.a.util.SecurityConfig;

@RestController
public class DeliveryController {

	@Autowired
	DeliveryService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
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
	
	@PostMapping("callAddDelivery")
	public String callAddDelivery(DeliveryDto dto) {
		System.out.println("DeliveryController addcustomer " + new Date());
		
		int count = service.callAddDelivery(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	
	
	
	
}
