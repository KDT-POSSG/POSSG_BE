package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.SettlementDao;
import possg.com.a.dto.SettlementDto;
import possg.com.a.dto.SettlementParam;

@Service
@Transactional
public class SettlementService {
	
	@Autowired
	SettlementDao dao;
	
	public int addsettlement(SettlementDto dto) {
		return dao.addsettlement(dto);
	}
	
	public List<SettlementParam> settlementlist(int convSeq){
		return dao.settlementlist(convSeq);
	}
	
	public int getallsettlement(int convSeq) {
		return dao.getallsettlement(convSeq);
	}
}
