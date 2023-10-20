package possg.com.a.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
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
		public ResponseEntity<?> refreshAccessToken(
		    @RequestHeader("accessToken") String accessToken, 
		    HttpServletRequest request,
		    HttpServletResponse response) {
	
		    System.out.println("ConvenienceController refresh() " + new Date());
	
		    String refreshToken = request.getHeader("refreshToken");
		    System.out.println(accessToken);
		    System.out.println(refreshToken);
		    
		    if(accessToken == null || refreshToken == null) {
		        System.out.println("refresh fail");
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		    }
		    
		    String userId;
		    try {
		        userId = tokenCreate.getuserIdFromToken(accessToken);
		    }
		    catch (ExpiredJwtException e) {
		        userId = tokenCreate.getuserIdFromToken(refreshToken);
		    }
		    catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		    }
	
		    if(userId == null) {
		        System.out.println("넌 유저가 아니다");
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		    }
	
		    ConvenienceDto userDto = convService.changePassword(userId);
		    List<TokenDto> userToken = convService.selectToken(refreshToken);
	
		    if(userToken == null) {
		        System.out.println("유효한 refresh토큰이 없습니다.");
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		    }
	
		    String newAccessToken = tokenCreate.generateJwtToken(userDto);
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("accessToken", newAccessToken);
	
		    return ResponseEntity.ok().headers(headers).body("REFRESH_YES");
		}

		
		@GetMapping("errorRedirect")
		public ResponseEntity<?> errorRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
			System.out.println("tokenController errorRedirect " + new Date());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		@RequestMapping(value = "/afterLogout", method = { RequestMethod.GET ,RequestMethod.POST })
		public String afterLogout() {
			
		    return "redirect:/login";
		}
	
	
}
