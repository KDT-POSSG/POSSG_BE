package possg.com.a.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.CostService;
import possg.com.a.util.TokenCreate;
/*
@RestController
public class CostController {

	@Autowired
	CostService service;
	

	private final TokenCreate tokenCreate;
	
	@Autowired
	public CostController(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
	@PostMapping("addcost")
	public String addcost(CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController addcost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		
		int convSeq = claim.get("convSeq", Integer.class);
		
		dto.setConvSeq(convSeq);
		
		System.out.println(dto);
		int count = service.addcost(dto);
		System.out.println(count);
		if(count != 0) {
			return "YES";
		}		
		return "NO";
	}
	
	@PostMapping("updatecost")
	public String updatecost(CostDto dto, @RequestHeader("accessToken") String tokenHeader) {
		System.out.println("CostController updatecost " + new Date());
		
		Claims claim = tokenParser(tokenHeader);
		 
	        int convSeq = claim.get("convSeq", Integer.class);	
	        
	        System.out.println(convSeq);
		 	 
		 dto.setConvSeq(convSeq);

		System.out.println(dto);
		
		int count = service.updatecost(dto);
		
		if(count != 0) {
			return "YES";
		}
		return "NO";		
	}
	*/
	/*
	 // 이거 db수정 해야함 민규님 db수정 후 배포 전까지 존버
	@GetMapping("selectSales")
	public List<CostParam> selectSales(CostParam param, @RequestHeader("accessToken") String accessToken) { //List<Map<String, Object>>
		System.out.println("CostController selectSales " + new Date());
		
		Claims claim = tokenParser(accessToken);
		
		String branchName = claim.get("branchName", String.class);
		  int convSeq = claim.get("convSeq", Integer.class);
		  
		  ProductDto product = service.paymentProductName(convSeq);
		  
		param.setConvSeq(convSeq);
		param.setBranchName(branchName);
		param.setProductSeq(product.getProductSeq());
		
		List<CostParam> list = service.selectSales(param);
		
		if(list != null) {
			return list;
		}
		return null;
	}
	
	*/
	
	
	
	
	
	
	
	
	//-------------------------------------- 함수 로직 ----------------------------------------------------
	/*
	
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
*/