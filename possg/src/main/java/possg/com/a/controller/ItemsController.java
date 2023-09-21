package possg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.ItemsDto;
import possg.com.a.service.ItemsService;

@RestController
@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class ItemsController {
	
	@Autowired
	ItemsService service;
	
	@PostMapping("addItems")
	public String addItems(@RequestBody List<ItemsDto> items) {
		System.out.println("ItemsController addItems " + new Date());
		int check = 0; // 성공 체크 변수
		
		for (int i = 0; i < items.size(); i++) {
			int count = 0;
			ItemsDto dto = (ItemsDto)items.get(i);
			System.out.println(dto.toString());
			count = service.addItems(dto);
		
			if(count > 0) {
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
