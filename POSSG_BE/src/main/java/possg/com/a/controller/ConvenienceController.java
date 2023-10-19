package possg.com.a.controller;

import java.security.MessageDigest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.service.ConvenienceService;
import possg.com.a.util.TokenCreate;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") //CROS 설정
public class ConvenienceController {

	@Autowired
	ConvenienceService service;
	
	private final TokenCreate tokenCreate;
	
	@Autowired
	public ConvenienceController(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
	@Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

	 @Value("${naver-cloud-sms.secretKey}")
	    private String secretKey;
	 
	 @Value("${naver-cloud-sms.serviceId}")
	    private String serviceId;
	 
	 @Value("${naver-cloud-sms.senderPhone}")
	    private String senderPhone;
	 
	 public long verificationCodeGenerationTime;	// 인증번호 보낼 시점 시간 저장

	 // 폐점#
	 @PostMapping("updateCodeStatus")
	 public String updateCodeStatus(@RequestHeader("accessToken") String accessToken) {                                                                        
		 System.out.println("ConvenienceController updateCodeStatus() " + new Date());
		 
		 String userId = tokenParser(accessToken);
		 
		 ConvenienceDto conv = new ConvenienceDto();

		 conv.setUserId(userId);
		 
		 int count = service.updateCodeStatus(conv);
		 
		 if(count != 0) {
			 return "YES";
		 }
		 return "NO";
	 }

	 // 내 정보 변경#
	 @PostMapping("updateMypage")
	 public String updateMypage(@RequestBody ConvenienceDto conv , @RequestHeader("accessToken") String accessToken) {
		 System.out.println("ConvenienceController updateMypage() " + new Date());
		 
		 String userId = tokenParser(accessToken);
		 
		 conv.setUserId(userId);
		 
		 int count = service.updateMypage(conv); 
		 
		 if(count != 0) {
			 return "YES";
		 }		 
		 return "NO";
	 }
	 
	 // 내정보 보여주기#
	 @GetMapping("myPage")
	    public ConvenienceDto mypage(@RequestHeader("accessToken") String tokenHeader) {
	        // "Bearer " 문자열을 제거하여 실제 토큰을 추출
	        String accessToken = tokenHeader.replace("Bearer ", "");

	        // JWT 토큰 검증
	        	JwtParser jwtParser = Jwts.parserBuilder()
		    		    .setSigningKey(tokenCreate.securityKey)
		    		    .build();

	            Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();

	            // 사용자 ID 추출
	            String userId = claims.get("userId", String.class);
	            
	            System.out.println(userId);

	            // 사용자 정보를 서비스에서 가져오기
	            ConvenienceDto user = service.mypage(userId);
	            System.out.println(user);
	            
	            if(user == null) {
		        	   return null;
		           }
	            // 사용자 정보 반환
	            return user;	          
	    }

	// 내 정보 변경에서 비밀번호 변경#
		 @PostMapping("changePassword")
		 public String changePassword(@RequestBody ConvenienceDto userDto, @RequestHeader("accessToken") String accessToken) {
			 System.out.println("ConvenienceController changePassword() " + new Date());
			   	   
			   if(userDto != null) {
				   
				   String userId = tokenParser(accessToken);
				   System.out.println(userDto);
				   
				   ConvenienceDto user = service.changePassword(userId);
				   System.out.println(user.getPwd());
				   String hashNewUser = sha256(userDto.getNewPwd());
				   String hashUser = sha256(userDto.getPwd());	
				   System.out.println(hashNewUser);
				   System.out.println(hashUser);
				   
				   if(!hashUser.equals(user.getPwd()) || hashNewUser.equals(user.getPwd())) {
					   return "NO";
				   }			   

				   userDto.setPwd(userDto.getNewPwd());
				   userDto.setUserId(userId);
				   
				   service.findPassword(userDto);
				   return "YES";
			   }	  		   
			   return "NO";
			}
		 
		 
		 
    // 비밀번호 해시화 (SHA-256 사용) 
	public static String sha256(String pw) { try {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); byte[] hash =
		md.digest(pw.getBytes("UTF-8")); StringBuffer hexString = new StringBuffer();
	 
	 	for (int i = 0; i < hash.length; i++) { String hex = Integer.toHexString(0xff
	 		& hash[i]); if (hex.length() == 1) { hexString.append('0'); }
	 		hexString.append(hex); } return hexString.toString(); } catch (Exception e) {
		 return ""; 
	 	}
	
	 	}
 	//customerSeq 추출하는 로직
	public String tokenParser(String accessToken) {
		
		// "Bearer " 문자열을 제거하여 실제 토큰을 추출
	    String access = accessToken.replace("Bearer ", "");

	    // JWT 토큰 검증
	    	JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(tokenCreate.securityKey)
	    		    .build();

	        Claims claims = jwtParser.parseClaimsJws(access).getBody();

	        // 사용자 ID 추출
	        String userId = claims.get("userId", String.class);
	        
	        return userId;
	}	
	

}
