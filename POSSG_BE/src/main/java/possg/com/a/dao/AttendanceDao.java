package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.AttendanceDto;
import possg.com.a.dto.AttendanceParam;

@Mapper
@Repository
public interface AttendanceDao {
	
	int attendance(AttendanceDto dto);
	int leavework(int employeeSeq);
	List<AttendanceParam> selectAllAttendance(int convSeq);
	List<AttendanceParam> selectOneAttendance(int employeeSeq);
	AttendanceParam attendanceCheck(int employeeSeq);
	int getAllAttendance(int employeeSeq);
}
