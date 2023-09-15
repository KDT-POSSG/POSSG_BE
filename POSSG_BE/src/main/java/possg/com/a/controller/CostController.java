package possg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.CostDto;
import possg.com.a.service.CostService;
import possg.com.a.util.SecurityConfig;

@RestController
public class CostController {

	@Autowired
	CostService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
	
	@PostMapping("addcost")
	public String addcost(CostDto dto) {
		System.out.println("ConvenienceController addcost " + new Date());
		
		System.out.println(dto);
		int count = service.addcost(dto);
		System.out.println(count);
		if(count != 0) {
			return "YES";
		}		
		return "NO";
	}
	
	@PostMapping("updatecost")
	public String updatecost(CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("ConvenienceController updatecost " + new Date());
		
		Claims cliam = tokenParser(tokenHeader);
		 
	        int convSeq = cliam.get("convSeq", Integer.class);	
	        
	        System.out.println(convSeq);
		 	 
		 dto.setConvSeq(convSeq);

		System.out.println(dto);
		
		int count = service.updatecost(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";		
	}
	
	
	//토큰 추출하는 로직
	public Claims tokenParser(String tokenHeader) {
		
		// "Bearer " 문자열을 제거하여 실제 토큰을 추출
	    String access = tokenHeader.replace("Bearer ", "");

	    // JWT 토큰 검증
	    	JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(securityConfig.securityKey)
	    		    .build();

	        Claims claims = jwtParser.parseClaimsJws(access).getBody();
	        
	        return claims;
	}	
	
}
