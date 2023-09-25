package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.ProductDto;

@Mapper
@Repository
public interface CostDao {
	
	int addcost(CostDto dto);
	
	int updatecost(CostDto dto);
	
	List<CostParam> selectSales(CostParam param);
	
	ProductDto paymentProductName(int convSeq);

}
