package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.CustomerDao;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.CustomerTokenDto;
import possg.com.a.dto.DeliveryDto;

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
	
	public int updateLocation(CustomerDto dto) {
		return dao.updateLocation(dto);
	}
	
	public int addWebCustomer(CustomerDto dto) {
		return dao.addWebCustomer(dto);
	}	
	
	public int existingCustomers(CustomerDto dto) {
		return dao.existingCustomers(dto);
	}
	
	public CustomerDto customerLogin(CustomerDto dto) {
		return dao.customerLogin(dto);
	}
	
	public int customerRefresh(CustomerTokenDto dto) {
		return dao.customerRefresh(dto);
	}
	
	public String customerSelectToken(CustomerTokenDto dto) {
		return dao.customerSelectToken(dto);
	}
	
	public int deleteRefresh(CustomerTokenDto dto) {
		return dao.deleteRefresh(dto);
	}
	
	public int deleteCustomer(CustomerDto dto) {
		return dao.deleteCustomer(dto);
	}
	
	public int customerDeliveryBranchName(CustomerDto dto) {
		return dao.customerDeliveryBranchName(dto);
	}
	
	public int deliveryDelete(int customerSeq) {
		return dao.deliveryDelete(customerSeq);
	}
}
