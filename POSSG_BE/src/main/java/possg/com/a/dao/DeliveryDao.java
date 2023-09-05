package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.DeliveryDto;

@Mapper
@Repository
public interface DeliveryDao {
	
	int convAddDelivery(ConvenienceDto dto);
	
	int callAddDelivery(DeliveryDto dto);

}
