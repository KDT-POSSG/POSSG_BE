package possg.com.a.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.CostService;
import possg.com.a.util.TokenCreate;

@RestController
public class CostController {

	@Autowired
	CostService service;
	

	private final TokenCreate tokenCreate;
	
	@Autowired
	public CostController(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
	@PostMapping("addCost")
	public String addcost(CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController addCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		
		int convSeq = claim.get("convSeq", Integer.class);
		
		dto.setConvSeq(convSeq);
		
		System.out.println(dto);
		int count = service.addCost(dto);
		System.out.println(count);
		if(count != 0) {
			return "YES";
		}		
		return "NO";
	}
	
	@PostMapping("updateCost")
	public String updateCost(CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController updateCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		 
        int convSeq = claim.get("convSeq", Integer.class);	
		 	 
		dto.setConvSeq(convSeq);

		System.out.println(dto);
		
		int count = service.updateCost(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";		
	}
	/*
	// 손익계산
	@GetMapping("profitAndLoss")
	public Map<String, Object> profitAndLoss(CostParam param,@RequestHeader("accessToken") String tokenHeader){
		System.out.println("CostController profitAndLoss " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        LocalDate localDate = LocalDate.parse(param.getDate(), inputFormatter);       
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = localDate.format(outputFormatter);
        
        int formattedYear = Integer.parseInt(formattedDate.substring(0, 4));
        int formattedMonth = Integer.parseInt(formattedDate.substring(4, 6));
        
		int convSeq = claim.get("convSeq", Integer.class);
		param.setConvSeq(convSeq);
		param.setCostYear(formattedYear);
		param.setCostYear(formattedMonth);
		
		CostDto loss = service.selectCost(param);
		
	
		List<Integer> orderPrice = service.selectOrderPrice(param);
		
		System.out.println(orderPrice);
		
		int totalOrderPrice = 0;
		for(int item : orderPrice) {
			totalOrderPrice = totalOrderPrice + item;
		}
		
		 // 월별 비교
        if(param.getChoice() == 1) {      	
        	
	        if(loss.getCostMonth() == formattedMonth) {
	        	
	        	int totalLoss = loss.getTotalLaborCost() + loss.getElectricityBill() + loss.getGasBill() + loss.getRent() + loss.getSecurityMaintenanceFee() + loss.getWaterBill() + totalOrderPrice;
	        	
	        	Map<String, Object> map = new HashMap<>();
	 			map.put("totalLoss", totalLoss);
	 			map.put(tokenHeader, map);
	        }
	        
	       
        }
		
		// 총 지출 금액
		int totalLoss = loss.getTotalLaborCost() + loss.getElectricityBill() + loss.getGasBill() + loss.getRent() + loss.getSecurityMaintenanceFee() + loss.getWaterBill() + totalOrderPrice;
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalLoss", totalLoss);
		map.put(tokenHeader, map);
		
		return map;
		
	}
*/
	// 년별 월별 일별 매출
	@GetMapping("selectSales")
	public int selectSales(CostParam param, @RequestHeader("accessToken") String accessToken) { //List<Map<String, Object>>
		System.out.println("CostController selectSales " + new Date());
		
		Claims claim = tokenParser(accessToken);

		if(param != null) {					
			String branchName = claim.get("branchName", String.class);
			int convSeq = claim.get("convSeq", Integer.class);
		 
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
	        LocalDate localDate = LocalDate.parse(param.getDate(), inputFormatter);       
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String formattedDate = localDate.format(outputFormatter);	        
		  
	        System.out.println(formattedDate);
	        System.out.println(param.getChoice());
			param.setConvSeq(convSeq);
			param.setBranchName(branchName);
			
			List<CostParam> payment = service.getPaymentPrice(param);	
			List<CostParam> list = service.getDeliveryPrice(param);
			
			for(int i = 0; i< payment.size(); i++) {
				String ref = payment.get(i).getRef();
				LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		        payment.get(i).setRef(convertedRef);
			}
			
			System.out.println(payment.get(0).getRef());
			// 일별
			if(param.getChoice() == 0) {
				System.out.println("choice == 0");
				int totalPaymentPrice = 0;
				
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8))) {		       
				        totalPaymentPrice = totalPaymentPrice + item.getPrice();
				        System.out.println(totalPaymentPrice);
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8))) {		       
				    	totalPaymentPrice = totalPaymentPrice + item.getPrice();
				    	System.out.println(totalPaymentPrice);
				    }
				}
				return totalPaymentPrice;
			}
			// 월별
			if(param.getChoice() == 1) {
				System.out.println("choice == 1");
				int totalPaymentPrice = 0;
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
				        totalPaymentPrice = totalPaymentPrice + item.getPrice();
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
				    	totalPaymentPrice = totalPaymentPrice + item.getPrice();
				    }
				   
				}
				return totalPaymentPrice;
			}
			
			System.out.println(formattedDate);
			// 년별
			System.out.println(param.getChoice());
			
			if(param.getChoice() == 2) {
				System.out.println("choice == 2");
				int totalPaymentPrice = 0;
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    System.out.println(ref);
				    
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
				        totalPaymentPrice = totalPaymentPrice + item.getPrice();
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
				    	totalPaymentPrice = totalPaymentPrice + item.getPrice();
				    }
				   
				}
				System.out.println(list);
				return totalPaymentPrice;
			}
		}

		return 0;
	}
	
	
	
	//-------------------------------------- 함수 로직 ----------------------------------------------------
	
	
	//토큰 추출하는 로직
	public Claims tokenParser(String tokenHeader) {
		
		// "Bearer " 문자열을 제거하여 실제 토큰을 추출
	    String access = tokenHeader.replace("Bearer ", "");

	    // JWT 토큰 검증
	    	JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(tokenCreate.securityKey)
	    		    .build();

	        Claims claims = jwtParser.parseClaimsJws(access).getBody();
	        
	        return claims;
	}	
	
}
