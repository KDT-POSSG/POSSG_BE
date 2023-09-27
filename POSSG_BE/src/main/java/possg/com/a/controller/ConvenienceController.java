package possg.com.a.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.MessageDto;
import possg.com.a.dto.SmsRequestDto;
import possg.com.a.dto.SmsResponseDto;
import possg.com.a.dto.TokenDto;
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
/*	
	
	 // 아이디 유무#
	@PostMapping("idcheck")
	public String idcheck(String userId) {
		System.out.println("ConvenienceController idcheck " + new Date());
		
		System.out.println(userId);
		int count = service.idcheck(userId);
		System.out.println(count);
		if (count == 0) {
			return "YES";
		}

		return "NO";
	}
	
	//로그인#
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody ConvenienceDto conv) {
		System.out.println("ConvenienceController login() " + new Date());
	    ConvenienceDto dto = service.login(conv);    

	    if (dto != null) {
	        // Access Token 생성
	        String accessToken = securityConfig.generateJwtToken(dto);

	        // Refresh Token 생성
	        String refreshToken = securityConfig.generateRefreshToken(dto);
	        System.out.println(refreshToken);
	        
	        TokenDto token = new TokenDto();
	      
	        token.setUserId(conv.getUserId());
	        token.setRefresh(refreshToken);
	        
	        int refresh = service.insertToken(token);
	        
	        ConvenienceDto requestDto = new ConvenienceDto();
	        
	        requestDto.setBranchName(dto.getBranchName());
	        requestDto.setConvSeq(dto.getConvSeq());
	        
	        System.out.println(requestDto);
	        
	        if(refresh == 0) {
	        	ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
	        }
     
	        // HTTP 요청 헤더 설정
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("accessToken", accessToken);	           	

	        return ResponseEntity.ok().headers(headers).body(requestDto);
	    }
	    System.out.println("login fail");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
	}
	*/
	// 로그아웃
//	@GetMapping("logout")
//	public String logout(@RequestHeader("accessToken") String accessToken) {
//		System.out.println("ConvenienceController logout() " + new Date());
//		
//		String userId= tokenParser(accessToken);
//		
//		int count = service.logout(userId);
//		
//		if(count != 0) {
//			return "YES";
//		}
//		return "NO";
//	}

	/*
	
	// 회원가입#
	 @PostMapping("addUser") 
	 public String adduser(@RequestBody ConvenienceDto conv) {
	 System.out.println("ConvenienceController adduser() " + new Date());
	 
	 Date currentTime = new Date();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String formattedTime = dateFormat.format(currentTime);
     
     conv.setRegistrationDate(formattedTime);
	 
	 String originPwd = conv.getPwd();
	 
	 String hashPwd = sha256(originPwd);
	 
	 conv.setPwd(hashPwd); 
	 
	 int count1 = service.updateCodeStatus(conv);
	 int count = service.adduser(conv);
	 if(count == 1 && count1 != 0){  		 
		 return "YES"; 
	 } 
	 return "NO"; 
	 }
*/
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
	 
	/* 
	 // 아이디 찾기#
	 @PostMapping("findId")
		public ResponseEntity<?> findId(@RequestParam(value="representativeName", required=false) String representativeName,
		                                @RequestParam(value="phoneNumber", required=false) String phoneNumber) {
		 
		 System.out.println("ConvenienceController findId() " + new Date());
		    Map<String, String> response = new HashMap<>();
		    
		    // 폰번호랑 이메일 둘 다 안적으면 안됨
		    if (representativeName == null || phoneNumber == null) {
		        response.put("errorMessage", "이메일 주소와 전화번호 둘 다 제공해주세요.");
		        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    }

		    // 폰번호랑 이메일 둘다 있어야 찾아짐
		    ConvenienceDto user = service.findUserByAddressAndPhoneNumber(representativeName, phoneNumber);
		    
		    if (user != null) {
		        response.put("user_id", user.getUserId());
		        return new ResponseEntity<>(response, HttpStatus.OK);
		    } else {
		        response.put("errorMessage", "해당 정보로 가입된 아이디가 없습니다.");
		        return new ResponseEntity<>(response, HttpStatus.OK);
		    }
		}
*/
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
				   
				   ConvenienceDto user = service.changePassword(userId);
   
				   String hashUser = sha256(user.getNewPwd());
				   		
				   if(userDto.getPwd() != hashUser && userDto.getPwd() == userDto.getNewPwd()) {
					   return "NO";
				   }
				   

				   userDto.setPwd(userDto.getNewPwd());
				   userDto.setUserId(userId);
				   
				   service.findPassword(userDto);
				   return "YES";
			   }	  		   
			   return "NO";
			}

		 
		 //----------------------------- 여기서 부터는 함수 생성 로직입니다-------------------------
	/*
	
	//문자 보내기
	public SmsResponseDto sendSmsForSmsCert(MessageDto dto, int number) throws Exception {
	    String time = Long.toString(System.currentTimeMillis());
	    System.out.println("test1~~~~~~~~~~~~~~~~~~~~");
	    
	    int veri = number;
	    
		String message = "POSSG 본인확인 인증번호 입니다 ["+ veri + "]인증번호를 입력해 주세요";
	
	    List<MessageDto> smsMessageList = new ArrayList<>();
	    MessageDto smsMessage = new MessageDto(dto.getTo(), message);
	    smsMessageList.add(smsMessage);
	    
	    SmsRequestDto smsRequest = new SmsRequestDto();
        smsRequest.setMessages(smsMessageList);

        // json 형태로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequest);
	    
	    System.out.println("test2~~~~~~~~~~~~~~~~~~~~");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("x-ncp-apigw-timestamp", time);
	    headers.set("x-ncp-iam-access-key", accessKey);
	    headers.set("x-ncp-apigw-signature-v2", getSignature(time));
	    System.out.println("test3~~~~~");

	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

	    SmsResponseDto smsResponse = restTemplate.postForObject(
	            "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages",
	            body,
	            SmsResponseDto.class
	    );

	    System.out.println(smsResponse + "controller");
	    return smsResponse;
	}
	
	
	
	//인증번호 생성
		public static int number() {
				 
				 Random random = new Random();
			        int min = 100000;
			        int max = 999999;
			        int verificationCode = random.nextInt(max - min + 1) + min;	        
			        
			       int verificationCodes = verificationCode;
			        
			     return verificationCodes; 
			 }
 
 // 문자 알고리즘 암호화
 private String getSignature(String time) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + serviceId + "/messages";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(time)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

        return encodeBase64String;
    } 
 */
		 
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
