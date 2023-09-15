package possg.com.a.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.CustomerTokenDto;
import possg.com.a.dto.DeliveryDto;
import possg.com.a.service.CustomerService;
import possg.com.a.service.DeliveryService;
import possg.com.a.util.SecurityConfig;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@Autowired
	SecurityConfig securityConfig;
	
	 int startingNumber = 000000; // 초기 숫자 설정
     int numberOfIncrements = 1; // 몇 번 증가시킬지 설정
     
     @Autowired
     DeliveryService deliservice;
	
     // 편의점에서 간편 가입#
	@PostMapping("addCustomer")
	public String addcustomer(CustomerDto dto, @RequestHeader("accessToken") String accessToken) {
		System.out.println("CustomerController addcustomer " + new Date());
		
		System.out.println(dto);
		
		 Claims claim = tokenParser(accessToken);	
		 
	     int convSeq = claim.get("convSeq", Integer.class);		 
		 	 
		 dto.setConvSeq(convSeq);		
		 
		 // 이름을 따로 받지 않으니 일단 anonymous로 이름 지정
		if(dto.getCustomerName() == null) {
			dto.setCustomerName("anonymous");
		}				
		// 휴대폰 번호랑 비밀번호만 받으니 그 휴대폰 번호가 db에 같은 값이 없으면 user + (점포번호) + 무작위난수로 저장
		CustomerDto cus = service.getCustomer(dto); // cus가 null이면 db에 없다는 거니까
		if(cus == null) {
			
			dto.setCustomerId("user-" + dto.getConvSeq() + "-" + generateUUID());
			
			int count = service.addCustomer(dto);
			if(dto.getPhoneNumber() != null && dto.getPinNumber() != 0 && count != 0) {
				System.out.println(dto);
				return "YES";
			}		
		}		
		return "NO";
	}
	
	// 웹에서 고객가입#
	@PostMapping("addWebCustomer")
	public String addWebCustomer(CustomerDto dto) {
		System.out.println("CustomerController addWebCustomer " + new Date());
			
		if(dto.getPwd() == null) {
			return "NO";
		}		
		CustomerDto cus = service.getCustomer(dto);
		System.out.println(cus);
		
		String phoneNum = cus.getPhoneNumber();
		
		System.out.println(phoneNum);
		// 기존 고객일 경우 
		if(phoneNum != null) {
			int updateCount = service.existingCustomers(dto);
			System.out.println(updateCount);
			if(updateCount != 0) {
				return "UPDATE_YES";
			}		
		}
		//신규 고객일 경우
		int count = service.addWebCustomer(dto);
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	// 고객로그인
	@PostMapping("customerLogin")
	public ResponseEntity<?> customerLogin(CustomerDto dto) {
		System.out.println("CustomerController customerLogin " + new Date());
		CustomerDto customer = service.customerLogin(dto); 
		System.out.println(customer);
		if (dto != null) {
			String accessToken = securityConfig.generateCustomerToken(customer);
			
			String refreshToken = securityConfig.generateCustomerRefreshToken(customer);
			
			CustomerTokenDto token = new CustomerTokenDto();
			token.setRefresh(refreshToken);
			token.setCustomerId(customer.getCustomerId());
			System.out.println(token);
			int count = service.customerRefresh(token);
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
	
	// 로그아웃 access는 프론트에서 지우고
	@PostMapping("deleteRefresh")
	public String deleteRefresh(CustomerTokenDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("CustomerController deleteRefresh " + new Date());
		
			Claims claim = tokenParser(tokenHeader);			 			 	 

            // 사용자 ID 추출
            String customerId = claim.get("customerId", String.class);

            dto.setCustomerId(customerId);
		
		int count = service.deleteRefresh(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}

	// 개인 웹로그인 시 주소 찍기 ------------------------------토큰확인하고 전화번호 확인-------------------------------------
	@PostMapping("updateLocation")
	public String updateLocation(CustomerDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("CustomerController updateLocation " + new Date());
		
		if(tokenHeader == null && dto == null) {
			return "NO";
		}
		
		Claims claim = tokenParser(tokenHeader);			 			 	 

        // 사용자 ID 추출
        String customerId = claim.get("customerId", String.class);      
            
            dto.setCustomerId(customerId);           
            System.out.println(dto);
		int count = service.updateLocation(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	//주문할 점포 선택
	@PostMapping("customerDeliveryBranchName")
	public String customerDeliveryBranchName(CustomerDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("CustomerController customerDeliveryBranchName " + new Date());
		
		if(dto == null) {
			return "NO";
		}
		
		Claims claim = tokenParser(tokenHeader);			 			 	 

        // 사용자 ID 추출
        String customerId = claim.get("customerId", String.class);
            int customerSeq = claim.get("customerSeq", Integer.class);
            
            DeliveryDto deliSeq = new DeliveryDto();
            
            deliSeq.setUserId(customerSeq);
            
            List<DeliveryDto> deli = deliservice.selectDelivery(deliSeq);
           
            // list가 비여있지않고 branchname이 일치하지않을경우
            if(!deli.isEmpty()) {          	
            
	    		/* 장바구니에 상품이 있을 경우 장바구니 초기화하고 다른 편의점 상품을 새로 담아야함	*/
	    		if(dto.getBranchName() !=  deli.get(0).getBranchName() ) {
	    			
	    			service.deliveryDelete(customerSeq);
	    		}
            }
            dto.setCustomerId(customerId);
		
		int count = service.customerDeliveryBranchName(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 회원탈퇴
	@PostMapping("deleteCustomer")
	public String deleteCustomer(CustomerDto dto, @RequestHeader("USTK") String tokenHeader) {
		System.out.println("CustomerController deleteCustomer " + new Date());
		
		Claims claim = tokenParser(tokenHeader);			 			 	 

        // 사용자 ID 추출
        String customerId = claim.get("customerId", String.class);
        
        dto.setCustomerId(customerId);
		
		int count = service.deleteCustomer(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";
	}
	
	
	// 혹시나 시간남으면 고객이 배달시킨 목록도 표기 9월 16일 쯤 하지 않을까
	
	
	// 고유 번호생성
	public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
	
	//customerSeq 추출하는 로직
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
