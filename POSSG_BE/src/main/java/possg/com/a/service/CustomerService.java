package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.CustomerDao;
import possg.com.a.dto.CustomerDto;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerDao dao;
	
	public int addCustomer(CustomerDto dto) {
		return dao.addCustomer(dto);
	}
	
	public CustomerDto getCustomer(CustomerDto dto) {
		return dao.getCustomer(dto);
	}
	
}
