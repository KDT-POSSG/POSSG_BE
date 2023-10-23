package possg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.ItemsDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.ItemsService;
import possg.com.a.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class ItemsController {
	
	@Autowired
	ItemsService service;
	
	@Autowired
	ProductService service2;
	
	@PostMapping("addItems")
	public String addItems(@RequestBody List<ItemsDto> items) {
		System.out.println("ItemsController addItems " + new Date());
		int check = 0; // 성공 체크 변수
		
		String str = ""; // 메시지
		
		for (int i = 0; i < items.size(); i++) {
			int count = 0;
			int count_big = 0;
			
			ItemsDto dto = (ItemsDto)items.get(i);
			count = service.addItems(dto);
			// System.out.println(dto.toString());
			
			// 찾은 아이템
			ProductDto findDto = service2.findProductSeq(dto.getItemId());
			
			// 상품 재고 수량도 변경
			int stock_calc = dto.getQty(); 
			int productSeq = dto.getItemId();
			
			ProductDto dto2 = new ProductDto(productSeq, stock_calc);
			
			// 결제 이후 수량 
			stock_calc = findDto.getStockQuantity() - Math.abs(stock_calc);
			
			
			// 남을 재고가 0개 이상일 경우 = 결제 가능
			if(stock_calc >= 0) {
				dto2.setStockQuantity(stock_calc);
				count_big = service2.updateProductStock(dto2);
			}
			
			
			if(count_big > 0 && count > 0) {
				check += 1;
			}
			
		}
		
		if (check == items.size()) {
			return "YES";
		}
		
		else {
			return "NO";
		}
	};
}
