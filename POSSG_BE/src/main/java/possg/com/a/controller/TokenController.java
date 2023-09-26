package possg.com.a.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.TokenDto;
import possg.com.a.service.ConvenienceService;
import possg.com.a.util.TokenCreate;
/*
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
	
		@PostMapping("refresh")
		public ResponseEntity<?> refreshAccessToken(@RequestHeader("accessToken")String accessToken) {		
			System.out.println("ConvenienceController refresh() " + new Date());
		   
		    	
		    	if(accessToken != null) {
		        // Refresh Token을 파싱하여 유효성 검사	    	
		    	JwtParser jwtParser = Jwts.parserBuilder()
		    		    .setSigningKey(tokenCreate.securityKey) // 여기서 secretKey는 생성한 시크릿 키입니다.
		    		    .build();
		    	System.out.println(jwtParser);
		    	// userId 파싱
		        Claims refreshClaims = jwtParser.parseClaimsJws(accessToken).getBody();	        	        
		        String userId = refreshClaims.get("userId", String.class);
		        
		        // 유효기간 파싱
		        Date expirationDate = refreshClaims.getExpiration();        
		        Date date = new Date();
		        
		        // 유저가 아님 
		        if(userId == null) {
		        	System.out.println("넌 유저가 아니다");
		        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		        }	        	        	        	        

		        ConvenienceDto userDto = convService.mypage(userId); // 사용자 정보 가져오기 등
		        List<TokenDto> userToken = convService.selectToken(userId);
		        
		        System.out.println(userToken);
		        
		        // 로그인 아이디랑 일치하는 토큰이 없는거
		        if(userToken == null) {
		        	System.out.println("유효한 refresh토큰이 없습니다.");
		        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		        }
		        	        
		        // 새로운 Access Token 발급
		        String newAccessToken = tokenCreate.generateJwtToken(userDto);
		       
		        Map<String, String> tokens = new HashMap<>();

		        tokens.put("accessToken", newAccessToken);
		        
		     // 토큰 기간이 만료됨
		        if(expirationDate.before(date)) {
		        	System.out.println("토큰이 만료됨");
		        	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		        }

		        return ResponseEntity.ok(tokens);
		    } 
		    	
		    	System.out.println("refresh fail");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();		    
		}
		
		@GetMapping("errorRedirect")
		public ResponseEntity<?> errorRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
			System.out.println("tokenController errorRedirect " + new Date());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
	
}
*/
