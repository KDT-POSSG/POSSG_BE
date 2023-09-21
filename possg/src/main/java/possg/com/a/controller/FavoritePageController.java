package possg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.FavoritePageDto;
import possg.com.a.dto.FavoritePageParam;
import possg.com.a.service.FavoritePageService;

@RestController
@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class FavoritePageController {
	
	@Autowired
	FavoritePageService service;
	
	@PostMapping("addFavoritePage")
	public String addFavoritePage(@RequestBody FavoritePageParam param) {
		System.out.println("FavoritePageController addFavoritePage " + new Date());
		
		int check = 0; // 업데이트 체크할 변수
		String msg = ""; // 전달할 메시지
		
		int reset = service.resetFavoritePage(param.getConvSeq());
		if(reset > 0) {
			msg += "RESET YES";
		}
		else{
			msg += "RESET NO";
		}
		
		List<Integer> seqList = param.getSeqList(); // 받은 즐겨찾기 번호 리스트
		
		for (int i = 0; i < seqList.size(); i++) {
			FavoritePageDto dto = new FavoritePageDto(seqList.get(i), param.getConvSeq(), msg, msg);
			int count = service.addFavoritePage(dto);
			
			if(count > 0) {
				check += 1;
			}
		}
		
		// 갯수랑 맞으면
		if(check == seqList.size()) {
			return msg += " ADD YES";
		}
		// 안맞으면
		else {
			return msg += (" ADD NO" + check +"개 추가");
		}
		
	};
	
	@GetMapping("favoritePageList")
	public List<FavoritePageDto> favoritePageList(@RequestParam int convSeq){
		System.out.println("FavoritePageController favoritePageList " + new Date());
		
		List<FavoritePageDto> list = service.favoritePageList(convSeq);
		
		return list;
	}
	
}
