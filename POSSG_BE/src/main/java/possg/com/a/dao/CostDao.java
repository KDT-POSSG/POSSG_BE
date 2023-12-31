package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CostDto;
import possg.com.a.dto.CostParam;
import possg.com.a.dto.PaymentDto;
import possg.com.a.dto.ProductDto;

@Mapper
@Repository
public interface CostDao {
	
	int addCost(CostDto dto);
	
	int updateCost(CostDto dto);
	
	List<CostParam> getDeliveryPrice(CostParam param);
	
	ProductDto paymentProductName(int convSeq);
	
	List<CostParam> getPaymentPrice(CostParam param);
	
	CostDto selectCost(CostParam param);
	
	List<CostDto> selectCostList(CostParam param);
	
	List<CostParam> selectOrderPrice(CostParam param);
	
	List<CostParam> bestDeliverySalesProduct(CostParam param);
	
	List<CostParam> bestPaymentSalesProduct(CostParam param);
	List<CostParam> paymentPrice(CostParam param);
	
	List<PaymentDto> cardOrCash(CostParam param);
	
	List<CostDto> selectYear(CostParam param);

}
