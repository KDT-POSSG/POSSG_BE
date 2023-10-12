package possg.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import net.crizin.KoreanCharacter;
import net.crizin.KoreanRomanizer;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductConvParam;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;
import possg.com.a.dto.amountDto;
import possg.com.a.service.ProductService;
import possg.com.a.service.TranslationService;
import possg.com.a.util.NaverCloudUtil;
import possg.com.a.util.ProductUtil;
import possg.com.a.util.SecurityConfig;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	TranslationController controller;
	
	@GetMapping("healthcheck")
	public String healthcheck() {
		System.out.println("ProductController healthcheck " + new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(new Date());
		return formattedDate;
	}
	
	public String responseMessage;
	
	// 상품 목록 획득
	@GetMapping("productList")
	public Map<String, Object> productList(ProductParam param){ //Map<String, Object> //List<ProductDto>
		System.out.println("ProductController ProductList " + new Date());
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("ProductParam= " + param);
		
		List<ProductDto> list = service.productList(param);
		if (list.isEmpty()) {
			map.put("ProductList", new ProductDto[0]);
			map.put("pageProduct", 0);
			map.put("cnt", 0); // react 중 pagination 사용시 활용
			return map;
		}
		System.out.println("ProductList= " + list.get(0));
		
		int cnt = 0;
		if (param.getCountry() != 0) {
			for(ProductDto dto : list) {
				System.out.println("in for ProductDto" + dto);
				String temp = controller.translationProductName(dto.getProductName(), param.getCountry());
				System.out.println("trans" + temp);
				dto.setProductTranslationName(temp);
				if (cnt > 3) {break;}
				cnt ++;
			}
		}
		
		System.out.println("ProductList= " + list);
		// 상품의 총 수
		int count = service.getProductTotalNumber(param);
		if (count == 0) {
			return map;
		}
		// 상품의 총 수가 한 페이지에 출력할 상품 수 보다 많으면 모든 상품을 출력
		if (param.getPageSize() > count) {
			param.setPageSize(count); 
		}
		int pageProduct = count / param.getPageSize();
		if((count % param.getPageSize()) > 0) {
			pageProduct = pageProduct + 1;
		}
		
		map.put("ProductList", list);
		map.put("pageProduct", pageProduct);
		map.put("cnt", count); // react 중 pagination 사용시 활용
		return map;
	}
	
	// 새로운 상품을 추가
	// 1: 행사X, 2: 세일, 3: 덤증정, 4: 1+1, 5: 2+1, 6: 1+2, 7: 2+2
	// conv_seq, category_id, product_name,product_roman_name, price, price_origin, price_discount, 
	// stock_quantity, expiration_date, expiration_flag, discount_rate, promotion_info, barcode, img_url
	@PostMapping("addProduct")
	public String addProduct(@RequestBody ProductDto dto) {
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
	
	// 바코드로 상품 검색
	// input: String barcode, int convSeq
	@GetMapping("findProductBarcode")
	public ProductDto findProductBarcode(ProductDto dto){
		System.out.println("ProductController findProductBarcode() " + new Date());
		System.out.println("dto= " + dto);
		List<ProductDto> list = service.findProductBarcode(dto);
		if(list.isEmpty() || list == null) {
			System.out.println("list= " + list);
			return null;
		}
		ProductDto resultDto = list.get(0);
		System.out.println("result= " + resultDto);
		
		return resultDto;
		
	}

	// input: int amount, String productName, int convSeq, String expirationDate
	@PostMapping("buyProduct")
	public String buyProduct(@RequestBody ProductDto dto) {
		System.out.println(dto);
		System.out.println("ProductController buyProduct() " + new Date());
		List<ProductDto> tempDto = service.getProductSeqAndTotalStock(dto);
		if(tempDto.isEmpty()) {
			System.out.println("상품이 없습니다." + tempDto.toString());
			return "NO";
		}

		if(tempDto.get(0).getTotalStock() < dto.getAmount()) {
			System.out.println("재고가 부족합니다." + tempDto.get(0).getTotalStock());
			return "NO";
		}
		
		int stock_calc = dto.getAmount(); 
		for(int i=0; i < tempDto.size(); i++) {
			stock_calc = tempDto.get(i).getStockQuantity() - Math.abs(stock_calc);
			if(stock_calc >= 0) {
				tempDto.get(i).setStockQuantity(stock_calc);
				int count_big = service.updateProductStock(tempDto.get(i));
				if(count_big>0) {
					System.out.println("update 성공");
					/*
					int count_del = service.deleteProductRegiInfo(dto);
					if(count_del > 0) {
						System.out.println("delete 성공");
					}
					*/
					return "YES";
				}
			}
		}
		if(tempDto.get(0).getTotalStock() >= dto.getAmount()) {
			System.out.println("전산오류 상품입니다. 해당 상품을 카운터에서 수거하고, 다른 상품을 가져와주세요");
		}
		return "NO";
	}
	
	
	/* #### 재고 관리 및 발주 #### */
	/* 재고 관리 목록 */
	@GetMapping("getAllProductStock")
	public Map<String, Object> getAllProductStock(ProductParam param){
		System.out.println("ProductController getAllProductStock() " + new Date());
		// DB에서 상품 정보를 가져옴
		List<ProductDto> list = service.productList(param); 
		
		// 최종 결과를 저장할 리스트
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        // 개별 상품 정보를 저장할 맵
        LinkedHashMap<String, Object> productMap = null;
        
        // 상세 상품 정보를 저장할 리스트
        List<Map<String, Object>> productDetails = null;
        /*
        // 이전에 처리한 상품의 이름을 저장할 변수
        String prevProductName = ""; 
        
        // 총 재고량을 저장할 변수
        int totalStock = 0;
        */
        // 모든 상품 정보를 순회
	    for (ProductDto dto : list) {
	    	
	    	List<ProductDto> nameDtoList = service.findProductName(dto);
	    	
	    	productMap = new LinkedHashMap<>();
            productDetails = new ArrayList<>();
            
            // 상품명
	    	productMap.put("product_name", dto.getProductName());
	    	// 상품 img 주소
            productMap.put("img_url", dto.getImgUrl());
            // 총 재고량
            productMap.put("totalStock", dto.getStockQuantity());
            System.out.println("productMap: " + productMap);
            
            for (ProductDto nameDto : nameDtoList) {
            	// 상세 정보를 저장할 맵을 생성 (상품고유번호, 상품명, 재고, 유통기한, 가격, 카테고리, 할인정보, 할인율
            	Map<String, Object> detail = new HashMap<>();
            	detail.put("product_seq", nameDto.getProductSeq());
            	detail.put("conv_seq", nameDto.getConvSeq());
                detail.put("product_name", nameDto.getProductName());
                detail.put("stock", nameDto.getStockQuantity());
                detail.put("expiration_date", nameDto.getExpirationDate());
                detail.put("expiration_flag", nameDto.getExpirationFlag());
                detail.put("price", nameDto.getPrice());
                detail.put("category", nameDto.getCategoryId());
                detail.put("promotion_info", nameDto.getPromotionInfo());
                detail.put("discount_rate", nameDto.getDiscountRate());
                
                productMap.put("details", productDetails);
            	// 상세 정보를 리스트에 추가
                productDetails.add(detail);
            }
            resultList.add(productMap);
            System.out.println("productDetails: " + productDetails);
	    }
	    
    	// 상품의 총 수
 		int count = service.getProductTotalNumber(param);
 		// 상품의 총 수가 한 페이지에 출력할 상품 수 보다 많으면 모든 상품을 출력
 		if (param.getPageSize() > count) {
 			param.setPageSize(count); 
 		}
 		int pageProduct = count / param.getPageSize();
 		if((count % param.getPageSize()) > 0) {
 			pageProduct = pageProduct + 1;
 		}

 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("ProductList", resultList);
 		map.put("pageProduct", pageProduct);
 		map.put("cnt", count);
	    
		return map;
	}
	
	/* 발주 대기 */
	// 발주 대기 목록 획득
	// input: int convSeq
	@GetMapping("getAllCallProductConvList")
	public Map<String,Object> getAllCallProductConvList(CallProductConvParam param) {
		System.out.println("ProductController getAllCallProductConvList() " + new Date());
		Map<String, Object> map = new HashMap<String, Object>();

		CallProductConvDto tempDto = new CallProductConvDto("0", param.getConvSeq());
		List<CallProductConvDto> dtoList = service.getRefCallProductConvList(tempDto);
		if (dtoList.isEmpty()) {
			System.out.println("발주 대기 상품 없음" + dtoList);
			map.put("convList", new CallProductConvDto[0]);
			map.put("price", 0); // 총 가격
			map.put("priceOrigin", 0);
			map.put("amount", 0); // 총 수량
			map.put("product", 0); // 총 종류 수량
			return map;
		}

		System.out.println("발주 대기 목록:" + dtoList.toString());
		
		// 상품의 총 수량, 종류 수량, 판매가, 원가
		CallProductConvParam totalParam = service.getCallProductTotalInfo(tempDto);
		
		map.put("convList", dtoList);
		map.put("price", totalParam.getTotalPrice()); // 총 가격
		map.put("priceOrigin", totalParam.getTotalPriceOrigin()); // 원가 총 가격
		map.put("amount", totalParam.getTotalAmount()); // 총 수량
		map.put("product", totalParam.getTotalProduct()); // 총 종류 수량
		return map;
	}
	
	// input: String callRef, int convSeq
	@GetMapping("getRefCallProductConvList")
	public Map<String,Object> getRefCallProductConvList(CallProductConvDto convDto) {
		System.out.println("ProductController getRefCallProductConvList() " + new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		List<CallProductConvDto> dtoList = service.getRefCallProductConvList(convDto);
		System.out.println("발주 상세 내역:" + convDto.toString());
		if (convDto.getCallRef() == null) {
			System.out.println("ref 없음");
			map.put("convList", new CallProductConvDto[0]);
			map.put("price", 0); // 총 가격
			map.put("priceOrigin", 0);
			map.put("amount", 0); // 총 수량
			map.put("product", 0); // 총 종류 수량
			return map;
		}
		
		// 상품의 총 수량, 종류 수량, 판매가, 원가
		CallProductConvParam totalParam = service.getCallProductTotalInfo(convDto);
		
		map.put("convList", dtoList);
		map.put("price", totalParam.getTotalPrice()); // 총 가격
		map.put("priceOrigin", totalParam.getTotalPriceOrigin()); // 원가 총 가격
		map.put("amount", totalParam.getTotalAmount()); // 총 수량
		map.put("product", totalParam.getTotalProduct()); // 총 종류 수량
		return map;
	}

	// 재고 소진 시 자동 발주 시스템
	// input
	// int stockLimit: 발주 장바구니에 자동으로 등록되는 갯수의 경계값
	// ProductDto: int convSeq, String productName, int productSeq, int priceDiscount, int stockLimit
	@PostMapping("addCallProductConvAuto")
	public String addCallProductConvAuto(@RequestBody ProductDto productDto) {//@RequestHeader("accessToken") String tokenHeader
		System.out.println("ProductController addCallProductConvAuto() " + new Date());
		
		// 주문 갯수
		int amount = 3;
		
		// 해당 상품의 총 재고량을 가져옴
		int totalStock = service.getTotalStock(productDto.getProductName());
		// 임시로 재고 제한을 3으로 설정
		productDto.setStockLimit(3); // 임시 제한
		// 총 재고가 재고 제한보다 작은 경우
		if (totalStock < productDto.getStockLimit()) {
			
			// 장바구니 등록 시간
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = sdf.format(new Date());
			
			// 발주 상품 정보를 설정
			List<ProductDto> temp = findProductName(productDto);
			if(temp.isEmpty()) {
				System.out.println("일치하는 상품이 없음: " + temp);
				return "NO";
			}
			ProductDto insertProductDto = temp.get(0);
			insertProductDto.setCallDate(formattedDate);
			insertProductDto.setAmount(amount);
			System.out.println(insertProductDto);

			// 발주 상품 정보를 추가
			// input: int convSeq, int productSeq, int price, String callDate, String productName, String imgUrl
			int count = service.addCallProductConv(insertProductDto);
			System.out.println(count);
			// 추가가 성공적으로 이루어진 경우
			if(count > 0) {
				return "YES";
			}
		  }
		// 재고가 충분한 경우
		return "NO";
	}
	// stock 반영 코드 만들어야함
	// 점주 발주 대기 리스트에 추가
	// input
	// ProductDto: int convSeq, String productName, int price, int priceOrigin, String imgUrl
	@PostMapping("addCallProductConv")
	public String addCallProductConv(@RequestBody ProductDto productDto) {// @RequestBody Map<String, Object> payload, @RequestBody int amount
		System.out.println("ProductController addCallProductConv() " + new Date());
		System.out.println("productDto= " + productDto);
		// 장바구니 등록 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(new Date());
		
		// 상품 수량이 입력되지 않은 상태면 수량 1 할당
		if (productDto.getAmount() == 0 ) {
			productDto.setAmount(1);
	    }
		// 객체 입력하여 상품명 전달
		List<ProductDto> temp = findProductName(productDto);
		ProductDto insertProductDto = new ProductDto();
		if(temp.isEmpty()) {
			responseMessage = "동일한 상품 없음: " + temp;
			System.out.println(responseMessage);
			return responseMessage;
		}
		
		insertProductDto = temp.get(0);
		CallProductConvDto tempConvDto = new CallProductConvDto(insertProductDto.getProductName(), insertProductDto.getConvSeq(), 0); 
		List<CallProductConvDto> compareConvDto = service.findCallProductConvName(tempConvDto);
		if (!compareConvDto.isEmpty()) {
			CallProductConvDto insertConvDto = compareConvDto.get(0);
			insertConvDto.setAmount(insertConvDto.getAmount() + productDto.getAmount());
			insertConvDto.setPrice(productDto.getPrice());
			insertConvDto.setPriceOrigin(productDto.getPriceOrigin());
			int count = service.updateCallProductConv(insertConvDto);
			if(count > 0) {
				return responseMessage="YES";
			}
		}
		
		System.out.println("insertProductDto= " + insertProductDto);
		insertProductDto.setCallDate(formattedDate);
		insertProductDto.setAmount(productDto.getAmount());
		
		System.out.println(insertProductDto);
		// 발주 상품 정보 설정
		// product_seq, conv_seq, category_id, product_seq, amount, rp_name, b_name, price, price_origin, call_date, product_name, call_ref, call_status, img_url
		// 발주 상품 정보를 데이터베이스에 추가
		int count = service.addCallProductConv(insertProductDto);
		System.out.println(count);
		if(count > 0) {
			return responseMessage="YES";
		}
		return responseMessage="NO";
	}
	
	// 발주 대기 상품 리스트 업데이트
	// input: convSeq, productName, amount, price, priceOrigin, callRef
	@PostMapping("updateCallProductConv")
	public String updateCallProductConv(@RequestBody CallProductConvDto convDto) {
		System.out.println("ProductController updateCallProductConv() " + new Date());
		System.out.println("CallProductConvDto: " + convDto);
		// 발주 대기중 모든 상품 리스트 정보 획득
		//List<CallProductConvDto> refTemp = service.getRefCallProductConvList(convDto.getCallRef());
		
		// 발주 대기 상품중 해당 상품명 정보 획득 (call_status == 0, product_name == 매개변수 상품명)
		// input: String productName, int convSeq
		List<CallProductConvDto> nameTemp = service.findCallProductConvName(convDto);
		System.out.println("nameTemp: " + nameTemp);
		
		// 상품명이 중복되거나 없는 경우 에러 처리
		if (nameTemp.size() > 1 || nameTemp.isEmpty()) {
			responseMessage = "Product duplication error!!" + new Date();
			System.out.println(responseMessage);
			return responseMessage;
		}
		CallProductConvDto crntConvDto = nameTemp.get(0);
		
		if (convDto.getAmount()<1) {
			responseMessage = "구매 수량을 한 개 이상이 되도록 다시 설정해주세요.";
			System.out.println(responseMessage);
			return responseMessage;
		}
		
		// 발주 수정할 상품 정보 획득
		ProductDto productTemp = new ProductDto(convDto.getProductName(), convDto.getConvSeq());
		List<ProductDto> productDto = service.findProductName(productTemp);
		System.out.println("fincProductName: " + productDto);
		// 해당 상품 가격
		int productPrice = productDto.get(0).getPriceOrigin();
		convDto.setPrice(productPrice);
		
		// 해당 상품 총 가격 set
		//convDto.setPrice(productPrice * convDto.getAmount());
		
		// 발주 사항 업데이트
		// input: int amount, int price, String productName, int convSeq
		int count = service.updateCallProductConv(convDto);
		System.out.println("발주 상품 목록 수정 성공");
		// 업데이트 성공
		if(count > 0) {
			// callRef가 '0'이 아닌 경우 발주 주문 목록도 업데이트
			if (!crntConvDto.getCallRef().equals("0")) {
	
				// 발주 주문 목록 수정
				CallProductConvOrderListDto orderDto = 
						service.getRefConvOrderList(convDto);
				System.out.println("getRefConvOrderList: " + orderDto.toString());
				// 총 가격을 업데이트 (기존 총 가격 - 수정된 총 가격)
				int totalPrice = orderDto.getCallTotalPrice() 
						- (crntConvDto.getPriceOrigin() - (productPrice * (crntConvDto.getAmount() + convDto.getAmount()))); // 기존 총 가격 - 수정된 총 가격 
				// 발주 주문 목록을 업데이트
				orderDto.setCallTotalPrice(totalPrice);
				int countOrder = service.updateConvOrderList(orderDto);
				if (countOrder > 0) {
					System.out.println("발주 주문 목록 수정 성공");
					return responseMessage="YES";
				}
			}
			return responseMessage="YES";
		}
		return responseMessage="NO";
	}

	// 발주 대기 상품 삭제
	// input: String callRef, List<String> nameList, int convSeq
	// 상품 삭제 시 주문 목록 업데이트 기능 추가
	@PostMapping("deleteCallProductConv")
	public String deleteCallProductConv(@RequestBody CallProductConvDto convDto) {
		System.out.println("ProductController deleteCallProduct() " + new Date());
		if (convDto.getNameList().isEmpty()) {
			System.out.println("NameList가 없음" + convDto.toString());
			return "NO";
		}
		for(String name : convDto.getNameList()) {
			convDto.setProductName(name);
			int count = service.deleteCallProductConv(convDto);
			if(count == 0) {
				return "NO";
			}
		}
		if (!convDto.getCallRef().equals("0")) {
			List<CallProductConvDto> callList = service.getRefCallProductConvList(convDto);
		    if (callList.isEmpty()) {
		    	System.out.println("동일한 ref 상품이 없음");
		    	return "YES";
		    }
			// 비고(remark)이 null인 경우 빈 문자열로 설정
		    if (convDto.getRemark() == null) {
		    	convDto.setRemark("");
		    }
	
		    // 발주 상품 수 및 총 가격 계산
		    int totalProduct = callList.size();
		    int totalPrice = 0;
		    for (CallProductConvDto item : callList) {
		        totalPrice += item.getPrice()*item.getAmount();
		    }
		    
		    // 동일한 ref의 call_product_conv_order_list
		    CallProductConvOrderListDto tempDto = new CallProductConvOrderListDto(
	    				convDto.getConvSeq(), convDto.getCallRef(), totalProduct, totalPrice);
			int countOrder = service.updateCallToOrderList(tempDto);
			if(countOrder == 0) {
				return "NO";
			}
		}
		return "YES";
	}
	
	// input: int expirationFlag, String productSeq
	@PostMapping("updateProductExpirationFlag")
	public String updateProductExpirationFlag(List<ProductDto> dtoList) {
		System.out.println("ProductController updateProductExpirationFlag()" + new Date());
		
		for(ProductDto tempDto : dtoList) {
			int count = service.updateProductExpirationFlag(dtoList);
			if(count == 0) {
				System.out.println("업데이트 실패");
				return "NO";
			}
		}
		System.out.println("업데이트 성공");
		return "YES";
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
	
	/* 발주 */
	
	// 발주 리스트 획득
	// input: int convSeq, int pageNumber, int pageSize
	@GetMapping("getAllConvOrderList")
	public Map<String, Object> getAllConvOrderList(CallProductConvParam param) {
		System.out.println("ProductController getAllConvOrderList() " + new Date());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CallProductConvOrderListDto> dtoList = service.getAllConvOrderList(param);
		
		int count = service.getOrderListTotalNumber(param);

		if (count == 0) {
			return map;
		}
		// 상품의 총 수가 한 페이지에 출력할 상품 수 보다 많으면 모든 상품을 출력
		if (param.getPageSize() > count) {
			if (count == 0) {
				param.setPageSize(1);
			}else {
				param.setPageSize(count);
			}
		}

		int pageProduct = count / param.getPageSize();

		if((count % param.getPageSize()) > 0) {
			pageProduct = pageProduct + 1;
		}
		
		map.put("orderList", dtoList);
		map.put("pageProduct", pageProduct);
		map.put("cnt", count); // react 중 pagination 사용시 활용
		return map;
		
	}
	
	// ref 참조 발주 리스트 획득
	// input: String conRef, int convSeq, int pageNumber, int pageSize
	@GetMapping("getRefConvOrderList")
	public CallProductConvOrderListDto getRefConvOrderList(CallProductConvDto convDto ) {
		System.out.println("ProductController getRefConvOrderList() " + new Date());
		System.out.println(convDto);
		return service.getRefConvOrderList(convDto);
	}

	// 발주 추가
	// input: String remark, int convSeq
	@PostMapping("addConvOrderList")
	public String addConvOrderList(@RequestBody CallProductConvDto convDto) {
		System.out.println("ProductController addConvOrderList() " + new Date());
	    
		convDto.setCallRef("0");
		// call_status가 '0'인 발주 상품 목록을 가져옴
		List<CallProductConvDto> callList = service.getRefCallProductConvList(convDto);
		
		if (callList.isEmpty()) {
	    	System.out.println("발주 대기중인 상품이 없음 " + callList);
	    	return "NO";
	    }
		
		// 비고(remark)이 null인 경우 빈 문자열로 설정
	    if (convDto.getRemark() == null) {
	    	convDto.setRemark("");
	    }
	    // user_id 추출
	    int convSeq = callList.get(0).getConvSeq();
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
	        totalPrice += item.getPriceOrigin()*item.getAmount();
	    }
	    
	    // call_product_conv_order_list에 추가
	    // seq, conv_seq, call_ref, call_date, call_status, call_total_number, call_total_price, call_remark
	    CallProductConvOrderListDto orderListDto = 
	    		new CallProductConvOrderListDto(0, convSeq, callRef, formattedDate, 
	    										1, totalProduct, totalPrice, convDto.getRemark());
	    // 발주 목록을 데이터베이스에 추가
	    int count = service.addConvOrderList(orderListDto);
	    System.out.println("ProductController addConvOrderList() count: " + count);
	    // 추가가 성공적으로 이루어진 경우
	    if (count > 0) {
	    	// 발주 상품 목록의 call_ref를 업데이트
	    	int callCount = service.updateRefCallProductConv(orderListDto);
	    	System.out.println("ProductController addConvOrderList() callCount: " + callCount);
	    	
	    	// 업데이트가 성공적으로 이루어진 경우
	    	if(callCount > 0) {
	    		return "YES";
	    	}
	    }
	    return "NO";
	}
	
	// 점주 발주 취소
	// input: int convSeq, String callRef
	@PostMapping("cancelConvOrderList")
	public String cancelConvOrderList(@RequestBody CallProductConvOrderListDto orderDto) {
		System.out.println("ProductController cancelConvOrderList() " + new Date());
		
		int count = service.cancelCallRefProductConv(orderDto);
		System.out.println("ProductController cancelConvOrderList() count: " + count);
		if (count > 0) {
			int orderCount = service.cancelConvOrderList(orderDto);
			System.out.println("ProductController cancelConvOrderList() orderCount: " + count);
			if (orderCount > 0) {
		        return "YES";
		    }
	    } 
		
		return "NO";
	}
	
	// 점주 발주 상품 배송 상태 업데이트
	// input: int callRef, int convSeq
	@PostMapping("statusUpdateConvOrderAndProduct")
	public String statusUpdateConvOrderAndProduct(@RequestBody CallProductConvOrderListDto orderDto) {
		System.out.println("ProductController statusUpdateConvOrderAndProduct() " + new Date());
		System.out.println(orderDto.getCallRef());

		int count = service.statusUpdateConvOrderAndProduct(orderDto);
		System.out.println("orderCount: " + count);
		if (count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 배송 완료 후 발주 기록 처리
	// input: String callRef, int convSeq
	@PostMapping("completeConvOrderAndProduct")
	public String completeConvOrderAndProduct(@RequestBody CallProductConvOrderListDto orderDto) {
		System.out.println("ProductController completeConvOrderAndProduct() " + new Date());
		System.out.println(orderDto.getCallRef());

		int count = service.completeConvOrderAndProduct(orderDto);
		System.out.println("orderCount: " + count);
		if (count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	// 발주 목록 삭제
	// input: String callRef, int convSeq
	@PostMapping("deleteConvOrderList")
	public String deleteConvOrderList(@RequestBody CallProductConvOrderListDto orderDto) {
		System.out.println("ProductController deleteConvOrderList() " + new Date());
		
		int count = service.cancelConvOrderList(orderDto);
		System.out.println("count: " + count);
		if (count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	@Scheduled(fixedRate = 1*(60*60*1000)/*시*/ + 0*(60*1000)/*분*/ + 0*(1000)/*초*/) // 일정 시간마다 자동 실행
	@PostMapping("updateExpirationFlagAuto")
	public String updateExpirationDateFlag() {
		System.out.println("ProductController updateExpirationDateFlag() " + new Date());
		int count = service.updateExpirationFlagAuto();
		if (count > 0) {
			return "YES";
		}
		
		return "NO";
	}
	
	
	/* 편의점 */
	// 점포명으로 편의점 검색
	@PostMapping("getConvenienceInfo")
	public ConvenienceDto getConvenienceInfo(@RequestBody String branchName) {
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
	
		
	// 상품 원가 할당
	@GetMapping("updateProductOriginPrice")
	public String updateProductOriginPrice(ProductParam param) {
		System.out.println("ProductController updateProductRomanName() " + new Date());
		
		List<ProductDto> productList = service.getAllProduct(param);
		for(ProductDto dto : productList) {
			
			System.out.println("productDto: " + dto.toString());
			int tempPrice = dto.getPrice();
			double price = tempPrice * 0.7;
			int roundedPrice = (int) Math.ceil(price);
			
			dto.setPriceOrigin(roundedPrice);
			System.out.println("roundedPrice: " + dto.getPriceOrigin());
			service.updateProductOriginPrice(dto);
		}

		return "YES";
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


/*
//이전 상품 이름과 현재 상품 이름이 다르면
if (!prevProductName.equals(dto.getProductName())) {
	
	// 이전 상품 정보가 있으면 결과 리스트에 추가
 if (productMap != null) {
     productMap.put("totalStock", totalStock);//dto.getTotalStock()
     productMap.put("details", productDetails);
     resultList.add(productMap);
 }
 
 // 새로운 상품 정보를 저장할 맵과 리스트를 초기화
 productMap = new LinkedHashMap<>();
 productDetails = new ArrayList<>();
 totalStock = 0;
 // 상품 이름을 맵에 저장
 productMap.put("product_name", dto.getProductName());
 productMap.put("img_url", dto.getImgUrl());
 
}
//상세 정보를 저장할 맵을 생성 (상품고유번호, 상품명, 재고, 유통기한, 가격, 카테고리, 할인정보, 할인율
Map<String, Object> detail = new HashMap<>();
detail.put("product_seq", dto.getProductSeq());
detail.put("product_name", dto.getProductName());
detail.put("stock", dto.getStockQuantity());
detail.put("expiration_date", dto.getExpirationDate());
detail.put("price", dto.getPrice());
detail.put("category", dto.getCategoryId());
detail.put("promotion_info", dto.getPromotionInfo());
detail.put("discount_rate", dto.getDiscountRate());
//상세 정보를 리스트에 추가
productDetails.add(detail);
//총 재고량을 업데이트
totalStock = dto.getTotalStock();

//이전 상품 이름을 업데이트
prevProductName = dto.getProductName(); // 이전 product_name 업데이트
}
//마지막 상품 정보를 결과 리스트에 추가
if (productMap != null) {
productMap.put("totalStock", totalStock);
productMap.put("details", productDetails);
resultList.add(productMap);
}
*/














