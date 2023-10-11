package possg.com.a.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.TokenDto;
import possg.com.a.service.ConvenienceService;
import possg.com.a.util.TokenCreate;

@RestController
@RequestMapping("tokenController")
public class TokenController {

	private final TokenCreate tokenCreate;
	
	
	@Autowired
	public TokenController(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
	@Autowired
	ConvenienceService convService;
	
		@RequestMapping(value = "refresh", method = {RequestMethod.GET, RequestMethod.POST})
		public ResponseEntity<?> refreshAccessToken(@RequestHeader("accessToken")String accessToken, HttpServletRequest request) {		
			System.out.println("ConvenienceController refresh() " + new Date());
			
			String refreshToken = request.getHeader("refreshToken");
		   		    	
		    	if(accessToken != null && refreshToken != null) {   	
		    	
		        Claims refreshClaims;	        	        
		        String userId;
		        
		        try {
		            refreshClaims = tokenCreate.getClaims(accessToken);	  
		            userId = refreshClaims.get("userId", String.class);
		        } catch (ExpiredJwtException e) {
		            
		            userId = e.getClaims().get("userId", String.class);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		        }
		        		        
		        // 유저가 아님 
		        if(userId == null) {
		        	System.out.println("넌 유저가 아니다");
		        	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		        }	        	        	        	        

		        ConvenienceDto userDto = convService.mypage(userId);
		        List<TokenDto> userToken = convService.selectToken(refreshToken);
		        
		        System.out.println(userToken);
		        
		        // 로그인 아이디랑 일치하는 토큰이 없는거
		        if(userToken == null) {
		        	System.out.println("유효한 refresh토큰이 없습니다.");
		        	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		        }
		        	        
		        // 새로운 Access Token 발급
		        String newAccessToken = tokenCreate.generateJwtToken(userDto);		       

		        HttpHeaders headers = new HttpHeaders();
		        headers.add("accessToken", newAccessToken);

		        return ResponseEntity.ok().headers(headers).body("YES");
		    } 	    	
		    	System.out.println("refresh fail");
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();		    
		}
		
		@GetMapping("errorRedirect")
		public ResponseEntity<?> errorRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
			System.out.println("tokenController errorRedirect " + new Date());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		@RequestMapping(value = "/afterLogout", method = RequestMethod.GET)
		public String afterLogout() {

		    return "redirect:/login";
		}
	
	
}
