package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.SettlementDto;
import possg.com.a.dto.SettlementParam;


@Mapper
@Repository
public interface SettlementDao {
	
	int addsettlement(SettlementDto dto);
	List<SettlementParam> settlementlist(int convSeq);
	int getallsettlement(int convSeq);
}
