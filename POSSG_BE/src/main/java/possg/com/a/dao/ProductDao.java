package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;

@Mapper
@Repository
public interface ProductDao {

	List<ProductDto> productList(ProductParam param);
	int getAllProduct(ProductParam param);
	
	int addProduct(ProductDto dto);
	
	List<ProductDto> findProductName(ProductDto dto);
	
	int getTotalStock(String name);
	int addCallProductConv(CallProductConvDto convDto);
	int updateCallProductConv(CallProductConvDto convDto);
	
	int addCallProductCustomer(CallProductCustomerDto cusotmerDto);
	
	ConvenienceDto getConvenienceInfo(String branchName);
	List<ConvenienceDto> getAllConvenience();
	
	List<CallProductConvDto> getAllCallProductConvList();
	List<CallProductConvDto> getRefCallProductConvList(String callRef);
	CallProductConvDto getSeqCallProductConv(int callSeq);
	List<CallProductConvDto> findCallProductConvName(String name);
	int updateRefCallProductConv(String callRef);
	int deleteCallRefProductConv(String callRef);
	
	List<CallProductConvOrderListDto> getAllConvOrderList();
	CallProductConvOrderListDto getRefConvOrderList(String callRef);
	int addConvOrderList(CallProductConvOrderListDto orderDto);
	int updateConvOrderList(CallProductConvOrderListDto orderDto);
	int deleteConvOrderList(String callRef);
	
	
	
	

}
