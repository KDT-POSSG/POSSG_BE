package possg.com.a.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;
import possg.com.a.dto.amountDto;
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
	@PostMapping("addProduct")
	public String addProduct(ProductDto dto) {
		System.out.println("ProductController addProduct " + new Date());
		
		int count = service.addProduct(dto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 상품명으로 상품 검색
	// input: String productName
	@GetMapping("findProductName")
	public List<ProductDto> findProductName(ProductDto dto) {
		System.out.println("ProductController findProductName() " + new Date());
		
		List<ProductDto> list = service.findProductName(dto);
		
		return list;
	}
	
	/* #### 재고 관리 및 발주 #### */
	
	/* 점주 발주 */
	// 발주 상품 리스트 획득
	@PostMapping("getAllCallProductConvList")
	public List<CallProductConvDto> getAllCallProductConvList() {
		System.out.println("ProductController getAllCallProductConvList() " + new Date());
		List<CallProductConvDto> dtoList = service.getAllCallProductConvList();
		
		return dtoList;
	}

	// 재고 소진 시 자동 발주 시스템
	// input
	// int stockLimit: 발주 장바구니에 자동으로 등록되는 갯수의 경계값
	// ProductDto: String productName, int productSeq, int priceDiscount
	// CallProductConvDto: String userId, String rpName, String bName 
	@PostMapping("addCallProductConvAuto")
	public String addCallProductConvAuto(ProductDto productDto, ConvenienceDto convDto, int stockLimit) {
		System.out.println("ProductController addCallProductConvAuto() " + new Date());
		
		int totalStock = service.getTotalStock(productDto.getProductName());
		
		stockLimit = 3; // 임시 제한
		
		if (totalStock < stockLimit) {

			CallProductConvDto insertCallDto = new CallProductConvDto(0,
			convDto.getUserId(), productDto.getProductSeq(), 0, convDto.getRepresentativeName(),
			convDto.getBranchName(), productDto.getPriceDiscount(), new Date().toString(),
			productDto.getProductName(), "0", 0);
			
			int count = service.addCallProductConv(insertCallDto);
			System.out.println(count);
			if(count > 0) {
				return "YES";
			}
			return "NO";
		  }
		
		return "NO";
	}
		
	// 점주 발주 상품 리스트에 추가
	// input
	// ProductDto: productName
	// CallProductConvDto: userId, rpName, bName 
	@PostMapping("addCallProductConv")
	public String addCallProductConv(ProductDto productDto, ConvenienceDto convDto, @RequestBody int amount) {// @RequestBody Map<String, Object> payload
		System.out.println("ProductController addCallProductConv() " + new Date());
		/*
		ProductDto productDto = new ObjectMapper().convertValue(payload.get("productDto"), ProductDto.class);
	    ConvenienceDto convDto = new ObjectMapper().convertValue(payload.get("convDto"), ConvenienceDto.class);
	    amountDto amountDto = new ObjectMapper().convertValue(payload.get("amountDto"), amountDto.class);

	    if (amountDto.getAmount() == 0 ) {
	    	amountDto.setAmount(1);
	    }
	    
		ProductDto insertProductDto = findProductName(productDto).get(0);
		*/
		// 장바구니 등록 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(new Date());
		
		// 상품 수량이 입력되지 않은 상태면 수량 1 할당
		if (amount == 0 ) {
	    	amount = 1;
	    }
		// 객체 입력하여 상품명 전달
		ProductDto insertProductDto = findProductName(productDto).get(0);
		System.out.println(insertProductDto);
		// user_id, product_seq, amount, rp_name, b_name, price, call_date, product_name, call_ref, call_status
		CallProductConvDto insertCallDto = new CallProductConvDto(0,
				convDto.getUserId(), insertProductDto.getProductSeq(), amount, convDto.getRepresentativeName(),
				convDto.getBranchName(), insertProductDto.getPriceDiscount()*amount, 
				formattedDate.toString(), insertProductDto.getProductName(), "0", 0);
		
		int count = service.addCallProductConv(insertCallDto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 점주 발주 상품 리스트 업데이트
	// input: productName, amount, priceDiscount, callRef
	@PostMapping("updateCallProductConv")
	public String updateCallProductConv(CallProductConvDto convDto) {
		System.out.println("ProductController updateCallProductConv() " + new Date());
		
		// 발주 신청전 모든 상품 리스트 정보 획득
		List<CallProductConvDto> refTemp = service.getRefCallProductConvList(convDto.getCallRef());
		
		// 발주 수정할 상품 정보 획득
		ProductDto productTemp = new ProductDto(convDto.getProductName());
		List<ProductDto> productDto = service.findProductName(productTemp);
		if (productDto.size() > 1) {
			System.out.println("Product duplication error!!" + new Date());
			return "NO";
		}
		
		// 해당 상품 가격
		int productPrice = productDto.get(0).getPriceDiscount();
		
		// 해당 상품 총 가격 set
		convDto.setPrice(productPrice * convDto.getAmount());
		
		// 발주 사항 업데이트
		int count = service.updateCallProductConv(convDto);
		System.out.println("발주 상품 목록 수정 성공");
		// 업데이트 성공
		if(count > 0) {
			// 발주 주문 목록 수정
			CallProductConvOrderListDto orderDto = 
					service.getRefConvOrderList(convDto.getCallRef());
										
			int totalPrice = orderDto.getCallTotalPrice() 
					- (refTemp.get(0).getPrice() - convDto.getPrice()); // 기존 총 가격 - 수정된 총 가격 
		    			
			orderDto.setCallTotalPrice(totalPrice);
			int countOrder = service.updateConvOrderList(orderDto);
			if (countOrder > 0) {
				System.out.println("발주 주문 목록 수정 성공");
				return "YES";
			}
		}
		return "NO";
	}
	
	/* 고객 발주 */
	
	// 고객 발주 상품 리스트에 추가
	@PostMapping("addCallProductCustomer")
	public String addCallProductCustomer(ProductDto productDto, CustomerDto customerDto, ConvenienceDto convDto) {
		System.out.println("ProductController addCallProductCustomer() " + new Date());
		
		ProductDto insertProductDto = findProductName(productDto).get(0);
		
		CallProductCustomerDto insertCallDto = new CallProductCustomerDto(0,
				customerDto.getCustomerSeq(), insertProductDto.getProductSeq(), 0, customerDto.getCustomerName(),
				convDto.getBranchName(), insertProductDto.getPriceDiscount(), new Date().toString(),
				insertProductDto.getProductName());
		
		int count = service.addCallProductCustomer(insertCallDto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	/* 발주 주문 */
	
	// 발주 주문 리스트 획득
	@PostMapping("getAllConvOrderList")
	public List<CallProductConvOrderListDto> getAllConvOrderList() {
		System.out.println("ProductController getAllConvOrderList() " + new Date());
		
		return service.getAllConvOrderList();
	}
	
	// 발주 주문 올리기
	// input: String remark
	@PostMapping("addConvOrderList")
	public String addConvOrderList(String remark) {
		System.out.println("ProductController addConvOrderList() " + new Date());
	    List<CallProductConvDto> callList = service.getRefCallProductConvList("0");
	    // 발주 목록 묶음 (call_ref) 생성 로직
	    // 주문 날짜(yyyyMMddHHmmss)
	    String callRef = ProductUtil.generateCallRef();
	    
	    // 발주 상품 수 및 총 가격 계산
	    int totalProduct = callList.size();
	    int totalPrice = 0;
	    for (CallProductConvDto item : callList) {
	        totalPrice += item.getPrice();
	    }
	    
	    // call_product_conv_order_list에 추가
	    // call_status, call_stock, call_total_price, call_remark
	    CallProductConvOrderListDto orderListDto = 
	    		// 
	    		new CallProductConvOrderListDto(0, callRef, new Date().toString(), 
	    										1, totalProduct, totalPrice, remark);
	    int count = service.addConvOrderList(orderListDto);
	    
	    if (count > 0) {
	        return "YES";
	    }
	    return "NO";
	}
	
	
	// 점주 발주 주문 삭제
	// input: int callRef
	@PostMapping("deleteConvOrderList")
	public String deleteConvOrderList(CallProductConvOrderListDto orderDto) {
		System.out.println("ProductController deleteConvOrderList() " + new Date());
		
		int count = service.deleteConvOrderList(orderDto);
		if (count > 0) {
	        return "YES";
	    }
		return "NO";
	}
	
	
	/* 편의점 */
	// 점포명으로 편의점 검색
	@PostMapping("getConvenienceInfo")
	public ConvenienceDto getConvenienceInfo(String branchName) {
		System.out.println("ProductController getConvenienceInfo() " + new Date());
		
		ConvenienceDto dto = service.getConvenienceInfo(branchName);
		
		return dto;
	}
	
	// 모든 편의점 정보 획득
	@PostMapping("getAllConvenience")
	public List<ConvenienceDto> getAllConvenience() {
		System.out.println("ProductController getAllConvenience() " + new Date());
		List<ConvenienceDto> dtoList = service.getAllConvenience();
		
		return dtoList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 데모 상품 크롤링
	
	// 상품 상세정보 필요시 조치 방안
	// 1. 영양정보 검색 후 수작업 https://www.fatsecret.kr/%EC%B9%BC%EB%A1%9C%EB%A6%AC-%EC%98%81%EC%96%91%EC%86%8C
	// 2. 크롤링 페이지 다른 주소로 변경 >> https://emile.emarteveryday.co.kr/
	
	//@Scheduled(fixedRate = 0*(60*60*1000)/*시*/ + 50*(60*1000)/*분*/ + 0*(1000)/*초*/) // 일정 시간마다 자동 크롤링 기능
	@GetMapping("productScrap")
	public void productScrap() throws Exception {
        List<ProductDto> ProductList = ProductUtil.productScrap(service);  // 이마트24에서 상품을 스크랩
        
        for (ProductDto dto : ProductList) {
        	System.out.println("ProductController productScrap() " + new Date());
        	addProduct(dto);
        }  
    }

}

















