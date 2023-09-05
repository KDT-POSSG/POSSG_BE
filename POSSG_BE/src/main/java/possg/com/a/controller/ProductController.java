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
	
	// 상품 목록 획득
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
	
	// 새로운 상품을 추가
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
		// 해당 상품의 총 재고량을 가져옴
		int totalStock = service.getTotalStock(productDto.getProductName());
		// 임시로 재고 제한을 3으로 설정
		stockLimit = 3; // 임시 제한
		// 총 재고가 재고 제한보다 작은 경우
		if (totalStock < stockLimit) {
			// 발주 상품 정보를 설정
			CallProductConvDto insertCallDto = new CallProductConvDto(0,
			convDto.getUserId(), productDto.getProductSeq(), 0, convDto.getRepresentativeName(),
			convDto.getBranchName(), productDto.getPriceDiscount(), new Date().toString(),
			productDto.getProductName(), "0", 0);
			// 발주 상품 정보를 추가
			int count = service.addCallProductConv(insertCallDto);
			System.out.println(count);
			// 추가가 성공적으로 이루어진 경우
			if(count > 0) {
				return "YES";
			}
		  }
		// 재고가 충분한 경우
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
		// 발주 상품 정보 설정
		// user_id, product_seq, amount, rp_name, b_name, price, call_date, product_name, call_ref, call_status
		CallProductConvDto insertCallDto = new CallProductConvDto(0,
				convDto.getUserId(), insertProductDto.getProductSeq(), amount, convDto.getRepresentativeName(),
				convDto.getBranchName(), insertProductDto.getPriceDiscount()*amount, 
				formattedDate.toString(), insertProductDto.getProductName(), "0", 0);
		// 발주 상품 정보를 데이터베이스에 추가
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
		
		// 발주 신청 전 모든 상품 리스트 정보 획득
		//List<CallProductConvDto> refTemp = service.getRefCallProductConvList(convDto.getCallRef());
		
		// 발주 신청 전 상품중 해당 상품명 정보 획득 (call_status == 0, product_name == 매개변수 상품명)
		List<CallProductConvDto> nameTemp = service.findCallProductConvName(convDto.getProductName());
		System.out.println("nameTemp: " + nameTemp);
		// 상품명이 중복되거나 없는 경우 에러 처리
		if (nameTemp.size() > 1 || nameTemp.isEmpty()) {
			System.out.println("Product duplication error!!" + new Date());
			return "NO";
		}
		// 발주 수정할 상품 정보 획득
		ProductDto productTemp = new ProductDto(convDto.getProductName());
		List<ProductDto> productDto = service.findProductName(productTemp);
		
		// 해당 상품 가격
		int productPrice = productDto.get(0).getPriceDiscount();
		
		// 해당 상품 총 가격 set
		convDto.setPrice(productPrice * convDto.getAmount());
		
		// 발주 사항 업데이트
		int count = service.updateCallProductConv(convDto);
		System.out.println("발주 상품 목록 수정 성공");
		// 업데이트 성공
		if(count > 0) {
			// callRef가 '0'이 아닌 경우 발주 주문 목록도 업데이트
			if (!convDto.getCallRef().equals("0")) {
				// 발주 주문 목록 수정
				CallProductConvOrderListDto orderDto = 
						service.getRefConvOrderList(convDto.getCallRef());
				System.out.println("getRefConvOrderList: " + orderDto.toString());
				// 총 가격을 업데이트 (기존 총 가격 - 수정된 총 가격)
				int totalPrice = orderDto.getCallTotalPrice() 
						- (nameTemp.get(0).getPrice() - convDto.getPrice()); // 기존 총 가격 - 수정된 총 가격 
				// 발주 주문 목록을 업데이트
				orderDto.setCallTotalPrice(totalPrice);
				int countOrder = service.updateConvOrderList(orderDto);
				if (countOrder > 0) {
					System.out.println("발주 주문 목록 수정 성공");
					return "YES";
				}
			}
			return "YES";
		}
		return "NO";
	}
	
	/* 고객 발주 */
	
	// 고객 발주 상품 리스트에 추가
	@PostMapping("addCallProductCustomer")
	public String addCallProductCustomer(ProductDto productDto, CustomerDto customerDto, ConvenienceDto convDto) {
		System.out.println("ProductController addCallProductCustomer() " + new Date());
		// 상품명을 찾아 객체에 할당
		ProductDto insertProductDto = findProductName(productDto).get(0);
		
		// 고객을 대상으로 발주 상품 정보 설정
	    // customer_seq, product_seq, amount, customer_name, b_name, price, call_date, product_name
		CallProductCustomerDto insertCallDto = new CallProductCustomerDto(0,
				customerDto.getCustomerSeq(), insertProductDto.getProductSeq(), 0, customerDto.getCustomerName(),
				convDto.getBranchName(), insertProductDto.getPriceDiscount(), new Date().toString(),
				insertProductDto.getProductName());
		// 발주 상품 정보를 데이터베이스에 추가
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
	
	// 발주 주문 추가
	// input: String remark
	@PostMapping("addConvOrderList")
	public String addConvOrderList(@RequestBody String remark) {
		System.out.println("ProductController addConvOrderList() " + new Date());
	    
		// call_status가 '0'인 발주 상품 목록을 가져옴
		List<CallProductConvDto> callList = service.getRefCallProductConvList("0");
	    
		// 비고(remark)이 null인 경우 빈 문자열로 설정
	    if (remark == null) {
	    	remark = "";
	    }
	    
	    // 발주 목록 묶음 (call_ref) 생성 로직
	    // 주문 날짜(yyyyMMddHHmmss)
	    String callRef = ProductUtil.generateCallRef();
	    // 발주 등록 시간
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(new Date());
		
	    // 발주 상품 수 및 총 가격 계산
	    int totalProduct = callList.size();
	    int totalPrice = 0;
	    for (CallProductConvDto item : callList) {
	        totalPrice += item.getPrice();
	    }
	    
	    // call_product_conv_order_list에 추가
	    // call_status, call_stock, call_total_price, call_remark
	    CallProductConvOrderListDto orderListDto = 
	    		new CallProductConvOrderListDto(0, callRef, formattedDate, 
	    										1, totalProduct, totalPrice, remark);
	    // 발주 목록을 데이터베이스에 추가
	    int count = service.addConvOrderList(orderListDto);
	    System.out.println("ProductController addConvOrderList() count: " + count);
	    // 추가가 성공적으로 이루어진 경우
	    if (count > 0) {
	    	// 발주 상품 목록의 call_ref를 업데이트
	    	int callCount = service.updateRefCallProductConv(callRef);
	    	System.out.println("ProductController addConvOrderList() callCount: " + callCount);
	    	
	    	// 업데이트가 성공적으로 이루어진 경우
	    	if(callCount > 0) {
	    		return "YES";
	    	}
	    }
	    return "NO";
	}
	
	
	// 점주 발주 주문 삭제
	// 입력 call_ref에 해당하는 발주 상품 목록 및 발주 주문 목록의 call_status = -1 할당
	@PostMapping("deleteConvOrderList")
	public String deleteConvOrderList(@RequestBody String callRef) {
		System.out.println("ProductController deleteConvOrderList() " + new Date());
		System.out.println(callRef);
		//callRef="202309051811";
		int count = service.deleteCallRefProductConv(callRef);
		System.out.println("ProductController deleteConvOrderList() count: " + count);
		if (count > 0) {
			
			int orderCount = service.deleteConvOrderList(callRef);
			System.out.println("ProductController deleteConvOrderList() orderCount: " + count);
			if (orderCount > 0) {
		        return "YES";
		    }
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

















