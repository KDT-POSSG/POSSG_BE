package possg.com.a.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import possg.com.a.dto.DeliveryCount;
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
		System.out.println(dto);
		
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
		int totalLoss = 0;
		 // 월별 비교
        if(param.getChoice() == 1) {      
        	
        	Map<String, Object> map = new HashMap<>();   
        	CostDto loss = service.selectCost(param);
        	
        	if(loss == null) {
        		totalLoss = 0;
        	}
        	if(loss != null) {       		        	      	
        	     	
		        if(loss.getCostMonth() == formattedMonth) {		        	
		        	// 지출금액
		        	totalLoss = loss.getTotalLaborCost() + loss.getElectricityBill() + loss.getGasBill() + loss.getRent() 
		        	+ loss.getSecurityMaintenanceFee() + loss.getTotalLaborCost() + loss.getWaterBill() + totalOrderPrice;	        		 			
		        }
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
		
		if(totalLoss == 0 && totalPrice == 0) {		
			Map<String, Object> emptyMap = new HashMap<>();
			return emptyMap;
		}
		
		return map;
		
		
	}
*/
	// 년별 월별 일별 매출
	@GetMapping("selectSales")
	public Map<String, Object> selectSales(CostParam param, @RequestHeader("accessToken") String accessToken) { //List<Map<String, Object>>
		System.out.println("CostController selectSales " + new Date());
		
		Claims claim = tokenParser(accessToken);

		if(param != null) {					
			String branchName = claim.get("branchName", String.class);
			int convSeq = claim.get("convSeq", Integer.class);
		 
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
	        LocalDate localDate = LocalDate.parse(param.getDate(), inputFormatter);       
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String formattedDate = localDate.format(outputFormatter);
	        int formattedYear = Integer.parseInt(formattedDate.substring(0, 4));
	        int formattedMonth = Integer.parseInt(formattedDate.substring(4, 6));
	        
	        param.setCostYear(formattedYear);
			param.setCostMonth(formattedMonth);
			param.setConvSeq(convSeq);
			param.setBranchName(branchName);
			
			LocalDate previousDay = localDate.minusDays(1);
			String eveDay = previousDay.format(outputFormatter);
	        System.out.println(eveDay);
	        LocalDate lastMonth = localDate.minusMonths(1);
	        String previousMonth = lastMonth.format(outputFormatter);
	        LocalDate lastYear = localDate.minusYears(1);
	        String previousYear = lastYear.format(outputFormatter);

	        
	        param.setPreMonth(Integer.parseInt(previousMonth.substring(4, 6)));	        	        
        	param.setPreYear(Integer.parseInt(previousYear.substring(0, 4)));	        	
	      

			List<CostDto> lossList = service.selectCostList(param);
			List<CostParam> orderPrice = service.selectOrderPrice(param);		
			List<CostParam> payment = service.paymentPrice(param);	
			List<CostParam> list = service.getDeliveryPrice(param);		
			
			for(int i = 0; i< payment.size(); i++) {
				String ref = payment.get(i).getRef();
				LocalDateTime dateTime = LocalDateTime.parse(ref, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		        String convertedRef = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		        payment.get(i).setRef(convertedRef);
			}
			
			Map<String, Object> map = new HashMap<>();
			int totalLoss = 0;
			int totalPrice = 0;				
			int totalCount = 0;
			int refundPrice = 0;
			int refundCount = 0;
			int notDiscount = 0;
			int discountCount = 0;
			int previousPrice = 0;
			int previousLoss = 0;
			
			if(param.getChoice() == 0) {
				//발주지출
				for(CostParam item : orderPrice) {				
					if(item.getRef().substring(0, 8).equals(formattedDate)) {
						totalLoss = totalLoss + item.getPrice();
					}			
					if(item.getRef().substring(0, 8).equals(eveDay)) {
						previousLoss = previousLoss + item.getPrice();
						System.out.println(previousLoss);
					}			
				}
				//일별지출
				for(CostParam item : payment) {
				    String ref = item.getRef();
				    String del = "결제 완료";	
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8)) && item.getDel().equals(del)){		       
				        totalPrice = totalPrice + item.getPrice();
				        notDiscount = notDiscount + item.getNotDiscount();
				        totalCount = totalCount + 1;	
				        System.out.println(totalPrice);
				        //할인 갯수
				        if(item.getPrice() != item.getNotDiscount()) {
				        	discountCount = discountCount + 1; 
				        }
				    }
				    //전일 매출
				    if(eveDay.equals(ref.substring(0, 8)) && item.getDel().equals(del)) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    //환불 매출 건수
				    if(ref.substring(0, 6).equals(eveDay) && !item.getDel().equals(del)) {
					   refundPrice = refundPrice + item.getPrice();
					   refundCount = refundCount + 1;
				    }
				}
				
				for(CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 8).equals(formattedDate.substring(0, 8)) && item.getStatus() != -1) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    	notDiscount = notDiscount + item.getNotDiscount();
				    	totalCount = totalCount + 1;
				    	System.out.println(totalPrice);
				    	//할인 갯수
				    	if(item.getPrice() != item.getNotDiscount()) {
				    		discountCount = discountCount + 1;
				    	}
				    }
				    //전일 매출
				    if(eveDay.equals(ref.substring(0, 8)) && item.getStatus() != -1) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    // 환불 매출 건수
				    if(ref.substring(0, 8).equals(formattedDate.substring(0, 8)) && item.getStatus() == -1) {
						   refundPrice = refundPrice + item.getPrice();
						   refundCount = refundCount + 1;
				    }
				}				
				 notDiscount = notDiscount - totalPrice; 
			        //매출 상승률
			        double increaseRate = 0;
			        if(previousPrice > 0) {
			        	increaseRate = ((double)(totalPrice - previousPrice) / previousPrice) * 100;
				        increaseRate = Math.round(increaseRate * 100.0) / 100.0;
			        }
			        
			        double lossIncreaseRate = 0;
			        if(previousLoss > 0) {
			        	lossIncreaseRate = ((double)(totalLoss - previousLoss) / previousLoss) * 100;
			        	lossIncreaseRate = Math.round(lossIncreaseRate * 100.0) / 100.0;
			        }
			        
			        int profit= totalPrice - totalLoss;
			        int preProfit = previousPrice - previousLoss;
			        
			        map.put("totalPrice", totalPrice); // 총매출
					map.put("totalLoss", totalLoss);   // 총지출
					map.put("totalCount", totalCount); // 총판매갯수
					map.put("refundPrice", refundPrice); // 환불가격
					map.put("refundCount",refundCount);  // 환불갯수
					map.put("discountPrice", notDiscount);// 할인가격
					map.put("discountCount", discountCount);//할인갯수
					map.put("increaseRate", increaseRate);  // 상승률
					map.put("previousPrice", previousPrice);// 전일매출
					map.put("previousLoss", previousLoss);  // 전일지출
					map.put("profit", profit); // 손익
					map.put("preProfit", preProfit); // 전달손익
					
					return map;				
			}
									
			// 월별
			if(param.getChoice() == 1) {	
				//발주지출
				for(CostParam item : orderPrice) {				
					if(item.getRef().substring(0, 6).equals(formattedDate)) {
						totalLoss = totalLoss + item.getPrice();
					}			
					if(item.getRef().substring(0, 6).equals(previousMonth.substring(0, 6))) {
						previousLoss = previousLoss + item.getPrice();
					}				
				}
				
				//매출 건수 
				for(CostParam item : payment) {
				    String ref = item.getRef();
				    String del = "결제 완료";	
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && item.getDel().equals(del)){		       
				        totalPrice = totalPrice + item.getPrice();
				        notDiscount = notDiscount + item.getNotDiscount();
				        totalCount = totalCount + 1;				        
				        //할인 갯수
				        if(item.getPrice() != item.getNotDiscount()) {
				        	discountCount = discountCount + 1; 
				        }
				    }
				    //전달 매출
				    if(previousMonth.substring(0, 6).equals(ref.substring(0, 6)) && item.getDel().equals(del)) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    //환불 매출 건수
				    if(ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && !item.getDel().equals(del)) {
					   refundPrice = refundPrice + item.getPrice();
					   refundCount = refundCount + 1;
				    }
				}
				
				for(CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && item.getStatus() != -1) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    	notDiscount = notDiscount + item.getNotDiscount();
				    	totalCount = totalCount + 1;
				    	//할인 갯수
				    	if(item.getPrice() != item.getNotDiscount()) {
				    		discountCount = discountCount + 1;
				    	}
				    }
				    //전달 매출
				    if(previousMonth.substring(0, 6).equals(ref.substring(0, 6)) && item.getStatus() != -1) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    // 환불 매출 건수
				    if(ref.substring(0, 6).equals(formattedDate.substring(0, 6)) && item.getStatus() == -1) {
						   refundPrice = refundPrice + item.getPrice();
						   refundCount = refundCount + 1;
				    }
				}
				
				//지출
				
				for(CostDto item : lossList) {					
					if(item.getCostMonth() == formattedMonth && item.getCostYear() == formattedYear) {				
						totalLoss = item.getTotalLaborCost() + item.getElectricityBill() + item.getGasBill() + item.getRent() 
			        	+ item.getSecurityMaintenanceFee() + item.getTotalLaborCost() + item.getWaterBill() + totalLoss;			
					}
					
					//전달 지출
					if(item.getCostMonth() == Integer.parseInt(previousMonth.substring(4, 6)) && item.getCostYear() == formattedYear) {
						previousLoss = item.getTotalLaborCost() + item.getElectricityBill() + item.getGasBill() + item.getRent() 
			        	+ item.getSecurityMaintenanceFee() + item.getTotalLaborCost() + item.getWaterBill() + previousLoss;
					}
				}
  
		        notDiscount = notDiscount - totalPrice; 
		        //매출 상승률
		        double increaseRate = 0;
		        if(previousPrice > 0) {
		        	increaseRate = ((double)(totalPrice - previousPrice) / previousPrice) * 100;
			        increaseRate = Math.round(increaseRate * 100.0) / 100.0;
		        }
		        
		        double lossIncreaseRate = 0;
		        if(previousLoss > 0) {
		        	lossIncreaseRate = ((double)(totalLoss - previousLoss) / previousLoss) * 100;
		        	lossIncreaseRate = Math.round(lossIncreaseRate * 100.0) / 100.0;
		        }
        
		        int profit= totalPrice - totalLoss;
		        int preProfit = previousPrice - previousLoss;
		        
		        // 손익 상승률
		        double preIncreaseRate = 0;
		        if (preProfit > 0) {
		            preIncreaseRate = ((double)(profit - preProfit) / preProfit) * 100;
		            preIncreaseRate = Math.round(preIncreaseRate * 100.0) / 100.0;
		        }
		        System.out.println(previousPrice);
				map.put("totalPrice", totalPrice); // 총매출
				map.put("totalLoss", totalLoss);   // 총지출
				map.put("totalCount", totalCount); // 총판매갯수
				map.put("refundPrice", refundPrice); // 환불가격
				map.put("refundCount",refundCount);  // 환불갯수
				map.put("discountPrice", notDiscount);// 할인가격
				map.put("discountCount", discountCount);//할인갯수
				map.put("increaseRate", increaseRate);  // 상승률
				map.put("previousPrice", previousPrice);// 전달매출
				map.put("previousLoss", previousLoss);  // 전달지출
				map.put("lossIncreaseRate", lossIncreaseRate); //지출상승률
				map.put("profit", profit); // 손익
				map.put("preProfit", preProfit); // 전달손익
				map.put("preIncreaseRate", preIncreaseRate); // 손익상승률
								
				return map;
			}
			
			//년별			
			if(param.getChoice() == 2) {
				//발주지출
				for(CostParam item : orderPrice) {				
					if(item.getRef().substring(0, 4).equals(formattedDate.substring(0, 4))) {
						totalLoss = totalLoss + item.getPrice();
					}
					if(item.getRef().substring(0, 4).equals(previousYear.substring(0, 4))) {
						previousLoss = previousLoss + item.getPrice();
					}			
				}
				// 매장 매출
				for(CostParam item : payment) {
				    String ref = item.getRef();
				    String del = "결제 완료";	
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && item.getDel().equals(del)){		       
				        totalPrice = totalPrice + item.getPrice();
				        notDiscount = notDiscount + item.getNotDiscount();
				        totalCount = totalCount + 1;				        
				        //할인 갯수
				        if(item.getPrice() != item.getNotDiscount()) {
				        	discountCount = discountCount + 1; 
				        }
				    }
				    //전년 매출
				    if(Integer.parseInt(previousYear.substring(0, 4)) == Integer.parseInt(ref.substring(0, 4)) && item.getDel().equals(del)) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    //환불 매출 건수
				    if(ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && !item.getDel().equals(del)) {
					   refundPrice = refundPrice + item.getPrice();
					   refundCount = refundCount + 1;
				    }
				}
				
				for(CostParam item : list) {
				    String ref = item.getRef();
				    if (ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && item.getStatus() != -1) {		       
				    	totalPrice = totalPrice + item.getPrice();
				    	notDiscount = notDiscount + item.getNotDiscount();
				    	totalCount = totalCount + 1;
				    	//할인 갯수
				    	if(item.getPrice() != item.getNotDiscount()) {
				    		discountCount = discountCount + 1;
				    	}
				    }
				    //전년 매출
				    if(Integer.parseInt(previousYear.substring(0, 4)) == Integer.parseInt(ref.substring(0, 4)) && item.getStatus() != -1) {
				    	previousPrice = previousPrice + item.getPrice();
				    }
				    // 환불 매출 건수
				    if(ref.substring(0, 4).equals(formattedDate.substring(0, 4)) && item.getStatus() == -1) {
						   refundPrice = refundPrice + item.getPrice();
						   refundCount = refundCount + 1;
				    }
				}

		        // 지출
		        for(CostDto item : lossList) {
		        	
					if(item.getCostYear() == formattedYear) {				
						totalLoss = item.getTotalLaborCost() + item.getElectricityBill() + item.getGasBill() + item.getRent() 
			        	+ item.getSecurityMaintenanceFee() + item.getTotalLaborCost() + item.getWaterBill() + totalLoss;			
					}
					
					//전달 지출
					if(item.getCostYear() == Integer.parseInt(previousMonth.substring(0, 4))) {
						previousLoss = item.getTotalLaborCost() + item.getElectricityBill() + item.getGasBill() + item.getRent() 
			        	+ item.getSecurityMaintenanceFee() + item.getTotalLaborCost() + item.getWaterBill() + previousLoss;
					}
				}
						        
		        notDiscount = notDiscount - totalPrice; 
		        //매출 상승률
		        double increaseRate = 0;
		        if(previousPrice > 0) {
		        	increaseRate = ((double)(totalPrice - previousPrice) / previousPrice) * 100;
			        increaseRate = Math.round(increaseRate * 100.0) / 100.0;
		        }
		        
		        double lossIncreaseRate = 0;
		        if(previousLoss > 0) {
		        	lossIncreaseRate = ((double)(totalLoss - previousLoss) / previousLoss) * 100;
		        	lossIncreaseRate = Math.round(lossIncreaseRate * 100.0) / 100.0;
		        }
        
		        int profit= totalPrice - totalLoss;
		        int preProfit = previousPrice - previousLoss;
		        
		        // 손익 상승률
		        double preIncreaseRate = 0;
		        if(previousLoss > 0) {
		        	preIncreaseRate = ((double)(profit - preProfit) / preProfit) * 100;
		        	preIncreaseRate = Math.round(preIncreaseRate * 100.0) / 100.0;
		        }
		        
				map.put("totalPrice", totalPrice); // 총매출
				map.put("totalLoss", totalLoss);   // 총지출
				map.put("totalCount", totalCount); // 총판매갯수
				map.put("refundPrice", refundPrice); // 환불가격
				map.put("refundCount",refundCount);  // 환불갯수
				map.put("discountPrice", notDiscount);// 할인가격
				map.put("discountCount", discountCount);//할인갯수
				map.put("increaseRate", increaseRate);  // 상승률
				map.put("previousPrice", previousPrice);// 전년매출
				map.put("previousLoss", previousLoss);  // 전년지출
				map.put("lossIncreaseRate", lossIncreaseRate); //지출상승률
				map.put("profit", profit); // 손익
				map.put("preProfit", preProfit); // 전년손익
				map.put("preIncreaseRate", preIncreaseRate); // 손익상승률

				return map;
			}
		}
		Map<String, Object> emptyMap = new HashMap<>();

		return emptyMap;
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
	    Map<String, Integer> brandSalesMap = new LinkedHashMap<>();	    	    		
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

	    Map<String, List<Map<String, Object>>> result = new TreeMap<>();
	    
	    for (CostParam item : payment) {
	        String ref = item.getRef();
	        String productName = item.getProductName();
	        String hour = ref.substring(8, 10); // ref에서 시간대 추출 (예: "10" 시간대)
	        System.out.println(hour);
	        if(formattedDate.equals(ref.substring(0,8))) {
	        		        
		        if (!result.containsKey(hour)) {
		            result.put(hour, new ArrayList<>());
		        }
	
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
