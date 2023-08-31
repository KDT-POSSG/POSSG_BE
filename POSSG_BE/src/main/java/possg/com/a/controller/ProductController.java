package possg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;
import possg.com.a.service.ProductService;
import util.ProductUtil;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("productList")
	public List<ProductDto> productList(ProductParam param){ //Map<String, Object> //List<ProductDto>
		System.out.println("ProductController ProductList " + new Date());
		System.out.println("ProductParam= " + param);
		List<ProductDto> list = service.productList(param);
		System.out.println("ProductList= " + list);
		/*
		//글의 총 수
		int count = service.getAllProduct(param);
		int pageProduct = count / 12;
		if((count % 12) > 0) {
			pageProduct = pageProduct + 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProductList", list);
		map.put("pageProduct", pageProduct);
		//map.put("pageNumber", param.getPageNumber());
		map.put("cnt", count); // react 중 pagination 사용시 활용
		return map;
		*/
		return list;
	}
	
	// 1: 행사X, 2: 세일, 3: 덤증정, 4: 1+1, 5: 2+1, 6: 1+2, 7: 2+2
	@PostMapping("productWrite")
	public String productWrite(ProductDto dto) {
		System.out.println("ProductController ProductWrite " + new Date());
		
		int count = service.productWrite(dto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 상품 상세정보 필요시 조치 방안
	// 1. 영양정보 검색 후 수작업 https://www.fatsecret.kr/%EC%B9%BC%EB%A1%9C%EB%A6%AC-%EC%98%81%EC%96%91%EC%86%8C
	// 2. 크롤링 페이지 다른 주소로 변경 >> https://emile.emarteveryday.co.kr/
	
	//@Scheduled(fixedRate = 0*(60*60*1000)/*시*/ + 50*(60*1000)/*분*/ + 0*(1000)/*초*/) // 일정 시간마다 자동 크롤링 기능
	@GetMapping("productScrap")
	public void productScrap() throws Exception {
        List<ProductDto> ProductList = ProductUtil.productScrap(service);  // 이마트24에서 상품을 스크랩
        
        for (ProductDto dto : ProductList) {
        	System.out.println("ProductController productScrap() " + new Date());
        	productWrite(dto);
        }  
    }
	
	// input
	// stockLimit: 발주 장바구니에 자동으로 등록되는 갯수의 경계값
	// ProductDto: productName, productSeq, priceDiscount
	// CallProductConvDto: userId, rpName, bName 
	public String callProductConvAddAuto(ProductDto productDto, CallProductConvDto callDto, int stockLimit) {
		System.out.println("ProductController callProductConvAddAuto() " + new Date());
		
		int totalStock = service.getTotalStock(productDto.getProductName());
		
		stockLimit = 3; // 임시 제한
		
		if (totalStock < stockLimit) {

			CallProductConvDto insertCallDto = new CallProductConvDto(0,
			callDto.getUserId(), productDto.getProductSeq(), 0, callDto.getRpName(),
			callDto.getbName(), productDto.getPriceDiscount(), new Date().toString(),
			productDto.getProductName());
			
			int count = service.callProductConvAdd(insertCallDto);
			System.out.println(count);
			if(count > 0) {
				return "YES";
			}
			return "NO";
		  }
		
		return "NO";
	}

	// input
	// ProductDto: productName, productSeq, priceDiscount
	// CallProductConvDto: userId, rpName, bName 
	@PostMapping("callProductConvAdd")
	public String callProductConvAdd(ProductDto productDto, CallProductConvDto callDto) {
		System.out.println("ProductController callProductConvAdd() " + new Date());
		
		CallProductConvDto insertCallDto = new CallProductConvDto(0,
		callDto.getUserId(), productDto.getProductSeq(), 0, callDto.getRpName(),
		callDto.getbName(), productDto.getPriceDiscount(), new Date().toString(),
		productDto.getProductName());
		
		int count = service.callProductConvAdd(insertCallDto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
}


















