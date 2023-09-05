package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.DeliveryDao;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.DeliveryDto;

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
}
