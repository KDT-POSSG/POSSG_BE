package possg.com.a.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.CustomerTokenDto;
import possg.com.a.dto.MessageDto;
import possg.com.a.dto.SmsRequestDto;
import possg.com.a.dto.SmsResponseDto;
import possg.com.a.dto.TokenDto;
import possg.com.a.service.ConvenienceService;
import possg.com.a.service.CustomerService;
import possg.com.a.util.TokenCreate;

@RequestMapping("NoSecurityZoneController")
@RestController
public class NoSecurityZoneController {
	
	@Autowired
	ConvenienceService service;
	
	@Autowired
	CustomerService cusService;
	
	private final TokenCreate tokenCreate;
	
	@Autowired
    public NoSecurityZoneController(TokenCreate tokenCreate) {
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
	 
	 public long verificationCodeGenerationTime;
	
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
	        String accessToken = tokenCreate.generateJwtToken(dto);

	        // Refresh Token 생성
	        String refreshToken = tokenCreate.generateRefreshToken(dto);
	        
	        TokenDto token = new TokenDto();
	      
	        token.setUserId(conv.getUserId());
	        token.setRefresh(refreshToken);
	        
	        int refresh = service.insertToken(token);
	        
	        ConvenienceDto requestDto = new ConvenienceDto();
	        
	        requestDto.setBranchName(dto.getBranchName());
	        requestDto.setConvSeq(dto.getConvSeq());	   
	        
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
	
	
	// 회원가입#
	@PostMapping("addUser") 
	public String adduser(@RequestBody ConvenienceDto conv) {
		System.out.println("ConvenienceController adduser() " + new Date());
		System.out.println(conv);
		Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedTime = dateFormat.format(currentTime);
    
    conv.setRegistrationDate(formattedTime);
	 
	 String originPwd = conv.getPwd();
	 
	 String hashPwd = sha256(originPwd);
	 
	 conv.setPwd(hashPwd); 
	 
	 
	 System.out.println(conv);
	 int count = service.adduser(conv);
	 
	 if(count == 0) {
		 return "NO";
	 }	 
	 if(count != 0){  
		 int count1 = service.updateCodeStatus(conv);
		 if(count1 != 0) {
			 return "NO";
		 }
		 
		 return "YES"; 
	 } 
	 return "NO"; 
	 }
	 
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
	 
	// 본사 인증키 확인#
	 @PostMapping("keyCheck")
	 public String keycheck(String convKey) {
		 System.out.println("ConvenienceController keycheck() " + new Date());	 
		 
		 System.out.println(convKey);
		 		 
		 int count = service.keycheck(convKey);
		 System.out.println(count);
		 if(count != 0) {
			 return "YES";
		 }	 
		 return "NO";
	 }
	 
	 
	 // 비밀번호 찾기 sms 보내기#
	@PostMapping("send")
    public ResponseEntity<?> sendSms(@RequestBody MessageDto messageDto) throws Exception {
	 System.out.println("ConvenienceController sendSms() " + new Date());
	 String temp = messageDto.getTo();
	
	 System.out.println(temp);
	 		 
	 ConvenienceDto conv = service.mypage(messageDto.getContent());
	 String phoneNum = conv.getPhoneNumber();	
    int veri = number();

	 if(messageDto.getContent().equals(conv.getUserId()) && phoneNum.equals(temp)) {
		 verificationCodeGenerationTime = System.currentTimeMillis();
            SmsResponseDto response = sendSmsForSmsCert(messageDto, veri);
            
            
            service.insertSms(veri);
            
            return ResponseEntity.ok(response);   
	 }
	 return ResponseEntity.badRequest().body("SMS 전송 실패");
    }
 
	 // 회원가입용 sms 보내기#
 @PostMapping("regiSend")
    public ResponseEntity<?> regisend(@RequestBody MessageDto messageDto) throws Exception {
	 System.out.println("ConvenienceController sendSms() " + new Date());
	 
	 int veri = number();
	 
	 	service.insertSms(veri);
	 
		 verificationCodeGenerationTime = System.currentTimeMillis();
		 	System.out.println("send time" + verificationCodeGenerationTime);
            SmsResponseDto response = sendSmsForSmsCert(messageDto, veri);  
            return ResponseEntity.ok(response);   
    }
 

	 // sms 확인하기#
	 @PostMapping("Authentication")
	 public String Authentication(@RequestParam int CodeNumber) {		 
		 System.out.println("ConvenienceController Authentication() " + new Date());
		 
		 // 코드 넘버 확인하고 db랑 비교 후 맞으면 yes 틀리면 no
		 System.out.println(CodeNumber);
		 
		 int smsNum = service.selectSms(CodeNumber);
		 
		 System.out.println(smsNum);
		 
		 if(smsNum == 0) {
			 System.out.println("db에 일치하는 인증번호가 없습니다.");
			 return "NO";
		 }	 
	
		 long currentTime = System.currentTimeMillis();
		 System.out.println(currentTime);
		 
		 System.out.println(verificationCodeGenerationTime);
		  
		 if(currentTime - verificationCodeGenerationTime <= 300000 && smsNum == 1) {			
			 				 
			 service.deleteSms(CodeNumber);		 
			 
				 return "YES";
			 
		 }	
		 return "NO";
	 }
	 
	 // 문자인증 하면 비밀번호 변경#
	 @PostMapping("findPassword")
	 public String findPassword(@RequestBody ConvenienceDto userDto) {
		 System.out.println("ConvenienceController changePassword() " + new Date());
		   	   
		   if(userDto != null) {
	   
			   // 비밀번호 변경
			   String hashedPassword = sha256(userDto.getNewPwd());
			   userDto.setPwd(hashedPassword);
			   
			   service.findPassword(userDto);
			   return "YES";
		   }	  		   
		   return "NO";
		}
	 
		 
		 //------------------------------------------------------- 고객 API ---------------------------------------------------------

			// 웹에서 고객가입#
			@PostMapping("addWebCustomer")
			public String addWebCustomer(CustomerDto dto) {
				System.out.println("CustomerController addWebCustomer " + new Date());
					
				if(dto.getPwd() == null) {
					return "NO";
				}		
				CustomerDto cus = cusService.getCustomer(dto);
				System.out.println(cus);
				
				String phoneNum = cus.getPhoneNumber();
				
				System.out.println(phoneNum);
				// 기존 고객일 경우 
				if(phoneNum != null) {
					int updateCount = cusService.existingCustomers(dto);
					System.out.println(updateCount);
					if(updateCount != 0) {
						return "UPDATE_YES";
					}		
				}
				//신규 고객일 경우
				int count = cusService.addWebCustomer(dto);
				if(count != 0) {
					return "YES";
				}
				return "NO";
			}
		// 고객로그인
		@PostMapping("customerLogin")
		public ResponseEntity<?> customerLogin(CustomerDto dto) {
			System.out.println("CustomerController customerLogin " + new Date());
			CustomerDto customer = cusService.customerLogin(dto); 
			System.out.println(customer);
			if (dto != null) {
				String accessToken = tokenCreate.generateCustomerToken(customer);
				
				String refreshToken = tokenCreate.generateCustomerRefreshToken(customer);
				
				CustomerTokenDto token = new CustomerTokenDto();
				token.setRefresh(refreshToken);
				token.setCustomerId(customer.getCustomerId());
				System.out.println(token);
				int count = cusService.customerRefresh(token);
				System.out.println(count);
				if(count == 0) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
				}
				
				HttpHeaders headers = new HttpHeaders();
		        headers.add("USTK", accessToken);
		        
		        return ResponseEntity.ok().headers(headers).body("YES");
			}
			System.out.println("login fail");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
		}
		
		
			
		//--------------------------------------------------- 공용함수 ----------------------------------------------------	
		 
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
