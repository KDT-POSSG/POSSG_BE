package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.DeliveryDao;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.dto.DeliveryCount;
import possg.com.a.dto.DeliveryDto;

import possg.com.a.dto.DeliveryJoinDto;

import possg.com.a.dto.DeliveryListDto;
import possg.com.a.dto.DeliveryParam;
import possg.com.a.dto.ProductDto;

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
	
	public List<DeliveryJoinDto> allDeliveryList(DeliveryDto dto) {
		return dao.allDeliveryList(dto);
	}
	
	public List<DeliveryDto> selectDelivery(DeliveryDto dto) {
		return dao.selectDelivery(dto);
	}
	
	public int insertDeliveryList(DeliveryListDto dto) {
		return dao.insertDeliveryList(dto);
	}
	
	public int updateDelivery(DeliveryDto dto) {
		return dao.updateDelivery(dto);
	}
	
	public int deleteDelivery(DeliveryDto dto) {
		return dao.deleteDelivery(dto);
	}
	
	public CustomerDto selectCustomer(int userId) {
		return dao.selectCustomer(userId);
	}
	
	public List<DeliveryJoinDto> convenienceDeliveryList(DeliveryParam param) {
		return dao.convenienceDeliveryList(param);
	}
	
	public int updateCountDelivery(DeliveryDto dto) {
		return dao.updateCountDelivery(dto);
	}
		
	public int getDeliveryCount(DeliveryParam param) {
		return dao.getDeliveryCount(param);
	}
	
	public int statusUpdate(DeliveryJoinDto dto) {
		return dao.statusUpdate(dto);
	}
	
	public ConvenienceDto getDeliveryStatus(String userId) {
		return dao.getDeliveryStatus(userId);
	}
	
	public int refuseDelivery(String ref) {
		return dao.refuseDelivery(ref);
	}
	
	public DeliveryCount allDeliveryCount(DeliveryParam param) {
		return dao.allDeliveryCount(param);
	}
	
	public List<ProductDto> findProduct(ProductDto dto) {
		return dao.findProduct(dto);
	}

}
