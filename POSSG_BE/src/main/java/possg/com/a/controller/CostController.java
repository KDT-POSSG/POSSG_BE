package possg.com.a.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public String addcost(@RequestBody CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
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
	public String updateCost(@RequestBody CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
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
        String branchName = claim.get("branchName", String.class);
		int convSeq = claim.get("convSeq", Integer.class);
		param.setConvSeq(convSeq);
		param.setCostYear(formattedYear);
		param.setCostMonth(formattedMonth);
		param.setBranchName(branchName);
		
		System.out.println(param);
		
		List<CostDto> lossList = service.selectCostList(param);
		List<Integer> orderPrice = service.selectOrderPrice(param);
		List<CostParam> payment = service.getPaymentPrice(param);
		List<CostParam> list = service.getDeliveryPrice(param);
		
		for(int i = 0; i< payment.size(); i++) {
			String ref = payment.get(i).getRef();
			LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        payment.get(i).setRef(convertedRef);
		}
		
		int totalOrderPrice = 0;
		for(int item : orderPrice) {
			totalOrderPrice = totalOrderPrice + item;
		}
		
		 // 월별 비교
        if(param.getChoice() == 1) {      
        	
        	CostDto loss = service.selectCost(param);
        	
        	Map<String, Object> map = new HashMap<>();
        	int totalLoss = 0;
	        if(loss.getCostMonth() == formattedMonth) {
	        	
	        	// 지출금액
	        	totalLoss = loss.getTotalLaborCost() + loss.getElectricityBill() + loss.getGasBill() + loss.getRent() 
	        	+ loss.getSecurityMaintenanceFee() + loss.getTotalLaborCost() + loss.getWaterBill() + totalOrderPrice;	        		 			
	        }	                	        
	        //매출
	        int totalPrice = 0;
			for (CostParam item : payment) {
			    String ref = item.getRef();
			    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
			        totalPrice = totalPrice + item.getPaymentPrice();
			    }
			}	
			for (CostParam item : list) {
			    String ref = item.getRef();
			    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
			    	totalPrice = totalPrice + item.getPrice();
			    }			   
			}			
			
			// 최종 손익
			int profit= totalPrice - totalLoss;	
			
			map.put("totalLoss", totalLoss);
			map.put("totalPrice", totalPrice);
			map.put("profit", profit);
	        
	        // 순수익
	        return map;	       
        }
		// 총 지출 금액
        // 년별 비교
        int totalLoss = 0;
        for(CostDto item : lossList) {
        	
        	totalLoss = totalLoss + item.getTotalLaborCost() + item.getElectricityBill() + item.getGasBill() + item.getRent() 
        	+ item.getSecurityMaintenanceFee() + item.getWaterBill() + item.getTotalLaborCost();
        }
        totalLoss = totalLoss + totalOrderPrice;
        
        int totalPrice = 0;
		for (CostParam item : payment) {		
		    String ref = item.getRef();
		    System.out.println(ref);
		    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
		        totalPrice = totalPrice + item.getPaymentPrice();
		    }
		}	
		for (CostParam item : list) {
		    String ref = item.getRef();
		    System.out.println(ref);
		    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
		    	totalPrice = totalPrice + item.getPrice();
		    }			   
		}
		
		int profit= totalPrice - totalLoss;
		Map<String, Object> map = new HashMap<>();
		map.put("totalLoss", totalLoss);
		map.put("totalPrice", totalPrice);
		map.put("profit", profit);
		
		return map;
		
	}

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
			
			// 일별
			if(param.getChoice() == 0) {
				int totalPrice = 0;
				
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8))) {		       
				        totalPrice = totalPrice + item.getPaymentPrice();
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8))) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    }
				}
				return totalPrice;
			}
			// 월별
			if(param.getChoice() == 1) {
				int totalPrice = 0;
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
				        totalPrice = totalPrice + item.getPaymentPrice();
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6))) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    }
				   
				}
				return totalPrice;
			}
			
			//년별			
			if(param.getChoice() == 2) {
				int totalPrice = 0;
				for (CostParam item : payment) {
				    String ref = item.getRef();
				    System.out.println(ref);
				    
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
				        totalPrice = totalPrice + item.getPaymentPrice();
				    }
				}	
				for (CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4))) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    }
				   
				}
				return totalPrice;
			}
		}

		return 0;
	}
	
	// 브랜드별 매출
	@GetMapping("brandSales")
	public List<Map<String, Object>> brandSales(CostParam param, @RequestHeader("accessToken") String accessToken){
		System.out.println("CostController brandSales " + new Date());
		
		Claims claim = tokenParser(accessToken);		
		String branchName = claim.get("branchName", String.class);
		int convSeq = claim.get("convSeq", Integer.class);
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        LocalDate localDate = LocalDate.parse(param.getDate(), inputFormatter);       
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = localDate.format(outputFormatter);
		
        System.out.println(formattedDate);
		param.setConvSeq(convSeq);
		param.setBranchName(branchName);
		
		List<CostParam> payment = service.getPaymentPrice(param);	
		List<CostParam> list = service.getDeliveryPrice(param);
		System.out.println(list);
		System.out.println(payment);
		for(int i = 0; i< payment.size(); i++) {
			String ref = payment.get(i).getRef();
			LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        payment.get(i).setRef(convertedRef);
		}
		
		
		List<Map<String, Object>> brandSalesList = new ArrayList<>();
	    Map<String, Integer> brandSalesMap = new HashMap<>();	    	    		
		//월별			
		if (param.getChoice() == 1) {
			for (CostParam item : payment) {
			    String ref = item.getRef();
			    Pattern pattern = Pattern.compile("([^)]+)\\)");
			    String productName = item.getProductName();
			    Matcher matcher = pattern.matcher(productName);
			    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && matcher.find()) {
			        String extractedString = matcher.group(1);
			        int price = item.getPaymentPrice();
			        brandSalesMap.put(extractedString, brandSalesMap.getOrDefault(extractedString, 0) + price);
			    }
			}
	        for (CostParam item : list) {
	            String ref = item.getRef();
	            Pattern pattern = Pattern.compile("([^)]+)\\)");
	            String productName = item.getProductName();
	            Matcher matcher = pattern.matcher(productName);
	            if (ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && matcher.find()) {
	                String extractedString = matcher.group(1);
	                int price = item.getPrice();
	                brandSalesMap.put(extractedString, brandSalesMap.getOrDefault(extractedString, 0) + price);
	            }
	        }
	    }
		
		//년별			
		if (param.getChoice() == 0) {
			for (CostParam item : payment) {
			    String ref = item.getRef();
			    Pattern pattern = Pattern.compile("([^)]+)\\)");
			    String productName = item.getProductName();
			    Matcher matcher = pattern.matcher(productName);
			    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && matcher.find()) {
			        String extractedString = matcher.group(1);
			        int price = item.getPaymentPrice();
			        brandSalesMap.put(extractedString, brandSalesMap.getOrDefault(extractedString, 0) + price);
			    }
			}
	        for (CostParam item : list) {
	            String ref = item.getRef();
	            Pattern pattern = Pattern.compile("([^)]+)\\)");
	            String productName = item.getProductName();
	            Matcher matcher = pattern.matcher(productName);
	            if (ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && matcher.find()) {
	                String extractedString = matcher.group(1);
	                int price = item.getPrice();
	                brandSalesMap.put(extractedString, brandSalesMap.getOrDefault(extractedString, 0) + price);
	            }
	        }
	    }
		
		
		for (Map.Entry<String, Integer> entry : brandSalesMap.entrySet()) {
	        Map<String, Object> brandSales = new HashMap<>();
	        brandSales.put("brand", entry.getKey());
	        brandSales.put("price", entry.getValue());
	        brandSalesList.add(brandSales);
	    }
  
		return brandSalesList;
	}
	
	//시간대별 판매 통계
	@GetMapping("HourlySales")
	public Map<String, List<Map<String, Object>>> HourlySales(CostParam param, @RequestHeader("accessToken") String accessToken) {
		System.out.println("CostController HourlySales " + new Date());
		
		Claims claim = tokenParser(accessToken);		
		String branchName = claim.get("branchName", String.class);
		int convSeq = claim.get("convSeq", Integer.class);
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일HH시mm분");
		LocalDateTime localDateTime = LocalDateTime.parse(param.getDate(), inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formattedDate = localDateTime.format(outputFormatter);
		
        System.out.println(formattedDate);
		param.setConvSeq(convSeq);
		param.setBranchName(branchName);
		
		List<CostParam> delivery = service.getDeliveryPrice(param);
		List<CostParam> payment = service.getPaymentPrice(param);
		
		for(int i = 0; i< payment.size(); i++) {
			String ref = payment.get(i).getRef();
			LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        payment.get(i).setRef(convertedRef);
		}		

	    Map<String, Map<String, Object>> hourlySalesData = new HashMap<>();

		for (CostParam item : payment) {
	        String ref = item.getRef();
	        String productName = item.getProductName();

	        if (formattedDate.substring(0, 6).equals(ref.substring(0, 6))) {
	            int count = item.getCount();
	            int price = item.getPaymentPrice();

	            Map<String, Object> salesItem; 
	            if (hourlySalesData.containsKey(productName)) { // productName중복체크
	                salesItem = hourlySalesData.get(productName);
	                int storedPrice = (int) salesItem.get("price");
	                int storedCount = (int) salesItem.get("count");
	                salesItem.put("price", storedPrice + price);
	                salesItem.put("count", storedCount + count);
	            } else {
	                salesItem = new HashMap<>();
	                salesItem.put("productName", productName);
	                salesItem.put("price", price);
	                salesItem.put("count", count);
	                hourlySalesData.put(productName, salesItem);
	            }
	        }
	    }
		
		for (CostParam item : delivery) {
	        String ref = item.getRef();
	        String productName = item.getProductName();

	        if (formattedDate.substring(0, 6).equals(ref.substring(0, 6))) {
	            int count = item.getCount();
	            int price = item.getPrice();

	            
	            Map<String, Object> salesItem;
	            if (hourlySalesData.containsKey(productName)) { // productName중복체크
	                salesItem = hourlySalesData.get(productName);
	                int storedPrice = (int) salesItem.get("price");
	                int storedCount = (int) salesItem.get("count");
	                salesItem.put("price", storedPrice + price);
	                salesItem.put("count", storedCount + count);
	            } else {
	                salesItem = new HashMap<>();
	                salesItem.put("productName", productName);
	                salesItem.put("price", price);
	                salesItem.put("count", count);
	                hourlySalesData.put(productName, salesItem);
	            }
	        }
	    }

		Map<String, List<Map<String, Object>>> result = new HashMap<>();
	    for (Map.Entry<String, Map<String, Object>> entry : hourlySalesData.entrySet()) {
	        Map<String, Object> salesItem = entry.getValue();
	        String hour = formattedDate.substring(8, 10);

	        if (!result.containsKey(hour)) {
	            result.put(hour, new ArrayList<>());
	        }

	        result.get(hour).add(salesItem);
	    }
	

	    return result;
	    	    
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
