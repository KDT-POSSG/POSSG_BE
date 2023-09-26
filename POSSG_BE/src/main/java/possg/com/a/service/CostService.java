package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.CostDao;
import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.ProductDto;

@Service
@Transactional
public class CostService {
	
	@Autowired
	CostDao dao;
	
	public int addcost(CostDto dto) {
		return dao.addcost(dto);
	}
	
	public int updatecost(CostDto dto) {
		return dao.updatecost(dto);
	}

	public List<CostParam> selectSales(CostParam param) {
		return dao.selectSales(param);
	}
	
	public ProductDto paymentProductName(int convSeq) {
		return dao.paymentProductName(convSeq);
	}
}
