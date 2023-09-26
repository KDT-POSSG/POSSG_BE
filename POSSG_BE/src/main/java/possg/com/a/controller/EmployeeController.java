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

import possg.com.a.dto.EmployeeDto;
import possg.com.a.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 CORS 허용
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping("addemployee")
	public String addemployee(@RequestBody EmployeeDto dto) {
		System.out.println("EmployeeController addemployee " + new Date());

		int count = service.addemployee(dto);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	}
	
	@PostMapping("updateEmployee")
	public String updateEmployee(@RequestBody EmployeeDto dto) {
		System.out.println("EmployeeController updateEmployee " + new Date());
		
		int count = service.updateEmployee(dto);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	}
	
	@GetMapping("findemployee")
	public List<EmployeeDto> findemployee(@RequestBody EmployeeDto dto) {
		System.out.println("EmployeeController findemployee " + new Date());
		
		List<EmployeeDto> list = service.findemployee(dto);
		
		return list;
	}
	
	@GetMapping("findallemployee")
	public List<EmployeeDto> findallemployee(@RequestParam int convSeq){
		System.out.println("EmployeeController findallemployee " + new Date());
		
		List<EmployeeDto> list = service.findallemployee(convSeq);
		
		return list;
	}
	
	@PostMapping("terminateEmployee")
	public String terminateEmployee(@RequestParam int employeeSeq) {
		System.out.println("EmployeeController terminateEmployee " + new Date());
		
		int count = service.terminateEmployee(employeeSeq);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	}
	
}
