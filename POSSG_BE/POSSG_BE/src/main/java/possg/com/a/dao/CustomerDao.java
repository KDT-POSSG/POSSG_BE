package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CustomerDto;

@Mapper
@Repository
public interface CustomerDao {

	int addCustomer(CustomerDto dto);
	
	CustomerDto getCustomer(CustomerDto dto);
}
