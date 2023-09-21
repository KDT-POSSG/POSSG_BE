package possg.com.a.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import possg.com.a.dto.MessageDto;
import possg.com.a.dto.SmsRequestDto;
import possg.com.a.dto.SmsResponseDto;

@Component
public class VerificationCode {
	
	@Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

	 @Value("${naver-cloud-sms.secretKey}")
	    private String secretKey;
	 
	 @Value("${naver-cloud-sms.serviceId}")
	    private String serviceId;
	 
	 @Value("${naver-cloud-sms.senderPhone}")
	    private String senderPhone;
	
	public long verificationCodeGenerationTime;	// 인증번호 보낼 시점 시간 저장
	public int verificationCodes;
		
	
	public SmsResponseDto sendSmsForSmsCert(MessageDto dto, HttpSession session) throws Exception {
	    String time = Long.toString(System.currentTimeMillis());
	    System.out.println("test1~~~~~~~~~~~~~~~~~~~~");
	    
	    number();
	    
	    session.setAttribute("verificationCode", verificationCodes);	    
	    
		String message = "POSSG 본인확인 인증번호 입니다 ["+ verificationCodes + "]인증번호를 입력해 주세요";
	
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
		public void number() {
				 
				 Random random = new Random();
			        int min = 100000;
			        int max = 999999;
			        int verificationCode = random.nextInt(max - min + 1) + min;	        
			        
			        verificationCodes = verificationCode;
			 }
 

 
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
	 
	

}
