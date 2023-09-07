package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.DeliveryDao;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.DeliveryDto;
import possg.com.a.dto.DeliveryParam;

@Service
@Transactional
public class DeliveryService {

	@Autowired
	DeliveryDao dao;
	
	public int convAddDelivery(ConvenienceDto dto) {
		return dao.convAddDelivery(dto);
	}
	
	public int callAddDelivery(DeliveryDto dto) {
		return dao.callAddDelivery(dto);
	}
	
	public List<DeliveryDto> allDeliveryList(String ref) {
		return dao.allDeliveryList(ref);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getDeliveryCount(DeliveryParam param) {
		return dao.getDeliveryCount(param);
	}
	
	public List<DeliveryDto> getAllDeliveryOrderList(){
		return dao.getAllDeliveryOrderList();
	}
	
	
	public List<DeliveryDto> getRefDeliveryOrderList(String delRef){
		return dao.getRefDeliveryOrderList(delRef);
	}
	
}
