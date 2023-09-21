package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CostDto;

@Mapper
@Repository
public interface CostDao {
	
	int addcost(CostDto dto);
	
	int updatecost(CostDto dto);

}
