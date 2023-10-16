package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.CostDao;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.DeliveryCount;
import possg.com.a.dto.ProductDto;

@Service
@Transactional
public class CostService {
	
	@Autowired
	CostDao dao;
	
	public int addCost(CostDto dto) {
		return dao.addCost(dto);
	}
	
	public int updateCost(CostDto dto) {
		return dao.updateCost(dto);
	}

	public List<CostParam> getDeliveryPrice(CostParam param) {
		return dao.getDeliveryPrice(param);
	}
	
	public ProductDto paymentProductName(int convSeq) {
		return dao.paymentProductName(convSeq);
	}
	
	public List<CostParam> getPaymentPrice(CostParam param) {
		return dao.getPaymentPrice(param);
	}

	public CostDto selectCost(CostParam param) {
		return dao.selectCost(param);
	}
	
	public List<CostDto> selectCostList(CostParam param) {
		return dao.selectCostList(param);
	}
	
	public List<Integer> selectOrderPrice(CostParam param) {
		return dao.selectOrderPrice(param);
	}
	
	public List<CostParam> bestDeliverySalesProduct(CostParam param) {
		return dao.bestDeliverySalesProduct(param);
	}
	
	public List<CostParam> bestPaymentSalesProduct(CostParam param) {
		return dao.bestPaymentSalesProduct(param);
	}
	

}
