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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.CostChangeTypeDto;
import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
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
	public String addCost(@RequestBody CostChangeTypeDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController addCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		if(dto != null) {		
			int convSeq = claim.get("convSeq", Integer.class);		
			
			CostDto change = new CostDto();
			change.setConvSeq(convSeq);
			change.setElectricityBill(Integer.parseInt(dto.getElectricityBill().replace(",", "")));
			change.setGasBill(Integer.parseInt(dto.getGasBill().replace(",", "")));
			change.setRent(Integer.parseInt(dto.getRent().replace(",", "")));
			change.setSecurityMaintenanceFee(Integer.parseInt(dto.getSecurityMaintenanceFee().replace(",", "")));
			change.setTotalLaborCost(Integer.parseInt(dto.getTotalLaborCost().replace(",", "")));
			change.setWaterBill(Integer.parseInt(dto.getWaterBill().replace(",", "")));
			change.setCostMonth(dto.getCostMonth());
			change.setCostYear(dto.getCostYear());
		
			CostParam cost = new CostParam();
			
			cost.setConvSeq(convSeq);
			cost.setCostMonth(dto.getCostMonth());
			cost.setCostYear(dto.getCostYear());
			
			CostDto auth = service.selectCost(cost);
			
			if(auth == null) {
				System.out.println(dto);
				int count = service.addCost(change);
				System.out.println(count);
				if(count != 0) {
					return "YES";
				}	
			}
			if(auth != null) {
				int count =service.updateCost(change);
				System.out.println("authnotnull" + count);
				if(count != 0) {
					return "YES";
				}
			}
		}
		return "NO";
	}
	
	@PostMapping("updateCost")
	public String updateCost(@RequestBody CostChangeTypeDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController updateCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		if(dto !=null) {			
	        int convSeq = claim.get("convSeq", Integer.class);				 	 
			
			CostDto change = new CostDto();
			change.setConvSeq(convSeq);
			change.setElectricityBill(Integer.parseInt(dto.getElectricityBill().replace(",", "")));
			change.setGasBill(Integer.parseInt(dto.getGasBill().replace(",", "")));
			change.setRent(Integer.parseInt(dto.getRent().replace(",", "")));
			change.setSecurityMaintenanceFee(Integer.parseInt(dto.getSecurityMaintenanceFee().replace(",", "")));
			change.setTotalLaborCost(Integer.parseInt(dto.getTotalLaborCost().replace(",", "")));
			change.setWaterBill(Integer.parseInt(dto.getWaterBill().replace(",", "")));
			change.setCostMonth(dto.getCostMonth());
			change.setCostYear(dto.getCostYear());
				
			int count = service.updateCost(change);
			
			if(count != 0) {
				return "YES";
			}
		}
		return "NO";		
	}
	
	@GetMapping("selectCost")
	public ResponseEntity<?> selectCost(@RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController selectCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);		
		int convSeq = claim.get("convSeq", Integer.class);		
		CostParam param = new CostParam();						
		LocalDate currentDate = LocalDate.now();     
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDateTime = currentDate.format(formatter);
        int formattedYear = Integer.parseInt(formattedDateTime.substring(0, 4));
        int formattedMonth = Integer.parseInt(formattedDateTime.substring(4, 6));
        
        param.setConvSeq(convSeq);
        param.setCostYear(formattedYear);
        param.setCostMonth(formattedMonth);
        
        CostDto cost = service.selectCost(param);
        System.out.println(cost);
        
        if(cost != null) {
        	return ResponseEntity.ok(cost);
        }
                
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO");
	}
	
	@PostMapping("callSelectCost")
	public ResponseEntity<?> callSelectCost(@RequestBody CostParam param,@RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController callSelectCost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
        int convSeq = claim.get("convSeq", Integer.class);

        param.setConvSeq(convSeq);
        
        CostDto cost = service.selectCost(param);
        
        if(cost != null) {
        	return ResponseEntity.ok(cost);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO");
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
        	int totalLoss = 0;
        	CostDto loss = service.selectCost(param);
        	if(loss == null) {
        		totalLoss = 0;
        	}
        	
        	Map<String, Object> map = new HashMap<>();
        	
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
			map.put("date", param.getDate());
			
			if(totalLoss == 0 || totalPrice == 0) {		
				Map<String, Object> emptyMap = new HashMap<>();
				return emptyMap;
			}
	        
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
		map.put("date", param.getDate());
		
		if(totalLoss == 0 || totalPrice == 0) {		
			Map<String, Object> emptyMap = new HashMap<>();
			return emptyMap;
		}
		
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
	
	@GetMapping("HourlySales")
	public Map<String, List<Map<String, Object>>> HourlySales(CostParam param, @RequestHeader("accessToken") String accessToken) {
	    System.out.println("CostController HourlySales " + new Date());

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

	    List<CostParam> delivery = service.getDeliveryPrice(param);
	    List<CostParam> payment = service.getPaymentPrice(param);

	    for (int i = 0; i < payment.size(); i++) {
	        String ref = payment.get(i).getRef();
	        LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        payment.get(i).setRef(convertedRef);
	    }

	    Map<String, List<Map<String, Object>>> result = new HashMap<>();
	    
	    for (CostParam item : payment) {
	        String ref = item.getRef();
	        String productName = item.getProductName();
	        String hour = ref.substring(8, 10); // ref에서 시간대 추출 (예: "10" 시간대)
	        System.out.println(hour);
	        if(formattedDate.contains(ref.substring(0,8))) {
	        		        
		        if (!result.containsKey(hour)) {
		            result.put(hour, new ArrayList<>());
		        }
	
		        // 시간대별로만 되어있음,,, 년도 월 일 시간 별로 분류 해야함,,,,,, 개에바
		        List<Map<String, Object>> hourlyDataList = result.get(hour);
		        Map<String, Object> salesItem = findSalesItemByProductName(hourlyDataList, productName);
	
		        if (salesItem != null) {
		            int count = item.getCount();
		            int price = item.getPaymentPrice();
	
		            int storedPrice = (int) salesItem.get("price");
		            int storedCount = (int) salesItem.get("count");
		            salesItem.put("price", storedPrice + price);
		            salesItem.put("count", storedCount + count);
		        } else {
		            salesItem = new HashMap<>();
		            salesItem.put("productName", productName);
		            salesItem.put("price", item.getPaymentPrice());
		            salesItem.put("count", item.getCount());
		            hourlyDataList.add(salesItem);
		        }
	        }
	    }

	    for (CostParam item : delivery) {
	        String ref = item.getRef();
	        String productName = item.getProductName();
	        String hour = ref.substring(8, 10); // ref에서 시간대 추출 (예: "10" 시간대)
	        System.out.println(hour);
	        if(formattedDate.contains(ref.substring(0,8))) {
	        	
	        
		        if (!result.containsKey(hour)) {
		            result.put(hour, new ArrayList<>());
		        }
	
		        // 시간대 별로 데이터를 그룹화
		        List<Map<String, Object>> hourlyDataList = result.get(hour);
		        Map<String, Object> salesItem = findSalesItemByProductName(hourlyDataList, productName);
	
		        if (salesItem != null) {
		            int count = item.getCount();
		            int price = item.getPrice();
	
		            int storedPrice = (int) salesItem.get("price");
		            int storedCount = (int) salesItem.get("count");
		            salesItem.put("price", storedPrice + price);
		            salesItem.put("count", storedCount + count);
		        } else {
		            salesItem = new HashMap<>();
		            salesItem.put("productName", productName);
		            salesItem.put("price", item.getPrice());
		            salesItem.put("count", item.getCount());
		            hourlyDataList.add(salesItem);
		        }
	        }
	    }

	    return result;
	}
	
	@GetMapping("bestSalesProduct")
	public Map<String, Object> bestSalesProduct(CostParam param, @RequestHeader("accessToken") String accessToken){
		System.out.println("CostController HourlySales " + new Date());
		
		Claims claim = tokenParser(accessToken);
		String branchName = claim.get("branchName", String.class);
	    int convSeq = claim.get("convSeq", Integer.class);
	    
	    param.setBranchName(branchName);
	    param.setConvSeq(convSeq);
	    
	    System.out.println(param);
	    
	    List<CostParam> delivery = service.bestDeliverySalesProduct(param);
	    List<CostParam> payment = service.bestPaymentSalesProduct(param);
	    
	    Map<String, Object> productMap = new HashMap<>();
	    List<Map<String, Object>> deliveryItem = new ArrayList<>();
	    List<Map<String, Object>> paymentItem = new ArrayList<>();
	    for(CostParam item : delivery) {	    	
    	    Map<String, Object> deliveryMap = new HashMap<>();
		    deliveryMap.put("price", item.getPrice());
		    deliveryMap.put("count", item.getCount());
		    deliveryMap.put("productName", item.getProductName());
		    deliveryItem.add(deliveryMap);
	    }
	    
	    for(CostParam item : payment) {	    
	    	Map<String, Object> paymentMap = new HashMap<>();
	        paymentMap.put("price", item.getPrice());
	        paymentMap.put("count", item.getCount());
	        paymentMap.put("productName", item.getProductName());
	        paymentItem.add(paymentMap);
	    }
	    productMap.put("delivery", deliveryItem);
	    productMap.put("payment", paymentItem);
	    
		return productMap;
	}

		
	//-------------------------------------- 함수 로직 ----------------------------------------------------
	// productName에 해당하는 판매 항목을 찾는 유틸리티 함수
	private Map<String, Object> findSalesItemByProductName(List<Map<String, Object>> dataList, String productName) {
	    for (Map<String, Object> item : dataList) {
	        if (productName.equals(item.get("productName"))) {
	            return item;
	        }
	    }
	    return null;
	}
	
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
