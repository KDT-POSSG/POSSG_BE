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
	public String updatecost(CostDto dto, @CookieValue("accessToken") String accessToken) {
		System.out.println("ConvenienceController updatecost " + new Date());
		
		accessToken = accessToken.replace("Bearer ", "");
		 
		 JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(securityConfig.securityKey)
	    		    .build();
	    	
	        Claims refreshClaims = jwtParser.parseClaimsJws(accessToken).getBody();
		 
	        int convSeq = refreshClaims.get("convSeq", Integer.class);		 
		 	 
		 dto.setConvSeq(convSeq);

		System.out.println(dto);
		
		int count = service.updatecost(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";		
	}
	
	
}
