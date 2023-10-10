package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.EmployeeDao;
import possg.com.a.dto.EmployeeDto;
import possg.com.a.dto.EmployeeParam;
import possg.com.a.dto.SettlementDto;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	EmployeeDao dao;
	
	public int addemployee(EmployeeDto dto) {
		return dao.addemployee(dto);
	}
	
	public int updateEmployee(EmployeeDto dto) {
		return dao.updateEmployee(dto);
	};

	public List<EmployeeDto> findemployee(EmployeeDto dto){
		return dao.findemployee(dto);
	};
	
	public List<EmployeeDto> findallemployee(int convSeq){
		return dao.findallemployee(convSeq);
	};
	
	public int terminateEmployee(int employeeSeq) {
		return dao.terminateEmployee(employeeSeq);
	};
	
	public int getallemployee(int convSeq) {
		return dao.getallemployee(convSeq);
	};
	
	public EmployeeParam findNumEmployee(int employeeSeq) {
		return dao.findNumEmployee(employeeSeq);
	};
}
