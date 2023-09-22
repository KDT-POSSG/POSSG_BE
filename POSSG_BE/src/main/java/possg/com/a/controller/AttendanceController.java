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

import possg.com.a.dto.AttendanceDto;
import possg.com.a.dto.AttendanceParam;
import possg.com.a.service.AttendanceService;

@RestController
@CrossOrigin(origins = "http://localhost:9200") // 프론트엔드 CORS 허용
public class AttendanceController {
	
	@Autowired
	AttendanceService service;
	
	@PostMapping("attendance")
	public String attendance(@RequestBody AttendanceDto dto) {
		System.out.println("AttendanceController attendance " + new Date());
		
		int emp_seq = dto.getEmployeeSeq(); // 직원 번호
		
		AttendanceParam param = service.attendanceCheck(emp_seq);
		//System.out.println(param.toString());
		
		// 이미 출근해 있는 경우 
		if (param.getLeaveWork() == null && param.getAttendance() != null) {
			return "AlREADY CHECK";
		}
		
		int count = service.attendance(dto);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	};
	
	@PostMapping("leavework")
	public String leavework(@RequestParam int employeeSeq) {
		System.out.println("AttendanceController attendance " + new Date());
		
		AttendanceParam param = service.attendanceCheck(employeeSeq);
		//System.out.println(param.toString());
		
		// 이미 퇴근해 있는 경우 
		if (param.getLeaveWork() != null) {
			return "AlREADY CHECK";
		}
		
		int count = service.leavework(employeeSeq);
		
		if(count > 0) {
			return "YES";
		}
		
		return "NO";
	};
	
	@GetMapping("selectAllAttendance")
	public List<AttendanceParam> selectAllAttendance(@RequestParam int convSeq){
		System.out.println("AttendanceController selectAllAttendance " + new Date());
		
		List<AttendanceParam> list = service.selectAllAttendance(convSeq);
		
		return list;
	};
	
	@GetMapping("selectOneAttendance")
	public List<AttendanceParam> selectOneAttendance(@RequestParam int employeeSeq){
		System.out.println("AttendanceController selectOneAttendance " + new Date());
		
		List<AttendanceParam> list = service.selectOneAttendance(employeeSeq);
		
		return list;
	};
}
