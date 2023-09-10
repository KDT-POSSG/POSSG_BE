package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.DeliveryDto;
import possg.com.a.dto.DeliveryListDto;
import possg.com.a.dto.DeliveryParam;

@Mapper
@Repository
public interface DeliveryDao {
	
	int convAddDelivery(ConvenienceDto dto);
	
	int callAddDelivery(DeliveryDto dto);
	
	
	List<DeliveryDto> allDeliveryList(String ref);
	
	List<DeliveryDto> selectDelivery(DeliveryDto dto);
	
	int insertDeliveryList(DeliveryListDto dto);
	
	int updateDelivery(DeliveryDto dto);
	
	int deleteDelivery(DeliveryDto dto);
	
	CustomerDto selectCustomer(int userId);
	
	
	
	int getDeliveryCount(DeliveryParam param);
	
	List<DeliveryDto> getAllDeliveryOrderList();
	List<DeliveryDto> getRefDeliveryOrderList(String delRef);

}
