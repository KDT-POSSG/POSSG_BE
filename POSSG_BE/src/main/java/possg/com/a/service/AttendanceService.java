package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.AttendanceDao;
import possg.com.a.dto.AttendanceDto;
import possg.com.a.dto.AttendanceParam;

@Service
@Transactional
public class AttendanceService {
	
	@Autowired
	AttendanceDao dao;
	
	public int attendance(AttendanceDto dto) {
		return dao.attendance(dto);
	};
	
	public int leavework(int employeeSeq) {
		return dao.leavework(employeeSeq);
	};
	
	public List<AttendanceParam> selectAllAttendance(int convSeq){
		return dao.selectAllAttendance(convSeq);
	};
	
	public List<AttendanceParam> selectOneAttendance(int employeeSeq){
		return dao.selectOneAttendance(employeeSeq);
	};
	
	public AttendanceParam attendanceCheck(int employeeSeq) {
		return dao.attendanceCheck(employeeSeq);
	};
}
