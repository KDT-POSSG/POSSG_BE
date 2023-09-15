package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.CostDao;
import possg.com.a.dto.CostDto;

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

}
