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
	List<ProductDto> getAllProduct(ProductParam param);
	int updateProductRomanName(ProductDto dto);
	int getProductTotalNumber(ProductParam param);
	
	int addProduct(ProductDto dto);
	
	List<ProductDto> findProductName(ProductDto dto);
	
	int getTotalStock(String name);
	int addCallProductConv(ProductDto dto);
	int updateCallProductConv(CallProductConvDto convDto);
	
	List<ProductDto> getAllProductStock(ProductParam param);
	
	int addCallProductCustomer(CallProductCustomerDto cusotmerDto);
	
	ConvenienceDto getConvenienceInfo(String branchName);
	List<ConvenienceDto> getAllConvenience();
	
	List<CallProductConvDto> getAllCallProductConvList(CallProductConvDto convDto);/////////////////////
	List<CallProductConvDto> getRefCallProductConvList(String callRef);
	CallProductConvDto getSeqCallProductConv(int callSeq);
	List<CallProductConvDto> findCallProductConvName(String name);
	int updateRefCallProductConv(String callRef);
	int cancelCallRefProductConv(CallProductConvOrderListDto orderDto);
	int deleteCallRefProductConv(String callRef);
	int deleteCallProduct(CallProductConvDto callDto);
	
	List<CallProductConvOrderListDto> getAllConvOrderList(CallProductConvDto convDto);
	CallProductConvOrderListDto getRefConvOrderList(CallProductConvDto convDto);
	int addConvOrderList(CallProductConvOrderListDto orderDto);
	int updateConvOrderList(CallProductConvOrderListDto orderDto);
	int cancelConvOrderList(CallProductConvOrderListDto orderDto);
	int deleteConvOrderList(String callRef);

}
