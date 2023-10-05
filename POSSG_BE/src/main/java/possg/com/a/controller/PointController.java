package possg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.PointParam;
import possg.com.a.service.PointService;

@RestController
public class PointController {
	
	@Autowired
	PointService service;
	
	@PostMapping("newPoint")
	public String newPoint(@RequestParam String phoneNumber) {
		System.out.println("PointController newPoint " + new Date());
		
		// 이미 가입 되어있는지 체크
		int check = service.checkPoint(phoneNumber);
		if(check > 0) {
			return "ALREADY CHECK";
		}
		
		// 가입 안되어있으면 추가
		int count = service.newPoint(phoneNumber);
		
		if(count > 0) {
			return "YES";
		}
		return "NO";
	};
	
	@PostMapping("addPoint")
	public String addPoint(@RequestBody PointParam param) {
		System.out.println("PointController addPoint " + new Date());
		
		// 이미 가입 되어있는지 체크
		int check = service.checkPoint(param.getPhoneNumber());
		if(check > 0) {
			// 가입되어 있어야 포인트 추가 가능
			int count = service.addPoint(param);
			
			if(count > 0) {
				return "YES";
			}
			
		}
		
		return "NO";
	};
	
	@GetMapping("searchPoint")
	public int searchPoint(@RequestParam String phoneNumber) {
		System.out.println("PointController searchPoint " + new Date());
		
		// 이미 가입 되어있는지 체크
		int check = service.checkPoint(phoneNumber);
		if (check <= 0) {
			return -1;
		}
		
		int remainPoint = service.searchPoint(phoneNumber);
		return remainPoint;
	}
	
	@PostMapping("usePoint")
	public String usePoint(@RequestBody PointParam param) {
		System.out.println("PointController addPoint " + new Date());
		
		// 이미 가입 되어있는지 체크
		int check = service.checkPoint(param.getPhoneNumber());
		if (check <= 0) {
			return "NO REGISTER";
		}
		
		// 포인트 사용량이 잔여 포인트보다 많은지 체크 
		int remainPoint = service.searchPoint(param.getPhoneNumber());
		if (param.getPoint() > remainPoint) {
			return "INSUFFICIENT POINT";
		}
		
		// 위 조건을 모두 통과하면 사용 가능
		int count = service.usePoint(param);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	};
	
	
}
