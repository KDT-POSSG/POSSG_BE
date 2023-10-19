package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.CustomerTokenDto;
import possg.com.a.dto.DeliveryDto;

@Mapper
@Repository
public interface CustomerDao {

	int addCustomer(CustomerDto dto);
	
	CustomerDto getCustomer(CustomerDto dto);
	
	int updateLocation(CustomerDto dto);
	
	int addWebCustomer(CustomerDto dto);
	
	int existingCustomers(CustomerDto dto);
	
	CustomerDto customerLogin(CustomerDto dto);
	
	int customerRefresh(CustomerTokenDto dto);
	
	String customerSelectToken(CustomerTokenDto dto);
	
	int deleteRefresh(CustomerTokenDto dto);
	
	int deleteCustomer(CustomerDto dto);
	
	int customerDeliveryBranchName(CustomerDto dto);
	
	int deliveryDelete(int customerSeq);
	
	int deleteCustomerToken();
}
