package possg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.SettlementDto;
import possg.com.a.dto.SettlementParam;
import possg.com.a.service.SettlementService;

@RestController
//@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class SettlementController {
	
	@Autowired
	SettlementService service;
	
	// 시재 정보 저장
	@PostMapping("addsettlement")
	public String addsettlement(@RequestBody SettlementDto dto) {
		System.out.println("SettlementController addsettlement " + new Date());
		
		int count = service.addsettlement(dto);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	}
	
	// 편의점 시재 정보 조회
	@GetMapping("settlementlist")
	public Map<String, Object> settlementlist(@RequestParam int convSeq){
		System.out.println("SettlementController settlementlist " + new Date());
		
		// 조회 목록
		List<SettlementParam> list = service.settlementlist(convSeq);
		
		// 조회 목록 갯수
		int count = service.getallsettlement(convSeq);
		
		// 여러가지 데이터 --> map으로
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("settlement", list);
		map.put("cnt", count);
		
		return map;
	}
	
	
}
