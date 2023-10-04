package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductConvParam;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;

@Mapper
@Repository
public interface ProductDao {

	String healthcheck();
	
	List<ProductDto> productList(ProductParam param);
	List<ProductDto> getAllProduct(ProductParam param);
	int updateProductRomanName(ProductDto dto);
	int getProductTotalNumber(ProductParam param);
	
	int addProduct(ProductDto dto);
	
	List<ProductDto> findProductName(ProductDto dto);
	List<ProductDto> findProductBarcode(ProductDto dto);
	
	int getTotalStock(String name);
	int addCallProductConv(ProductDto dto);
	int updateCallProductConv(CallProductConvDto convDto);
	
	List<ProductDto> getAllProductStock(ProductParam param);
	
	int addCallProductCustomer(CallProductCustomerDto cusotmerDto);
	
	ConvenienceDto getConvenienceInfo(String branchName);
	List<ConvenienceDto> getAllConvenience();
	
	List<CallProductConvDto> getAllCallProductConvList(CallProductConvParam param);
	List<CallProductConvDto> getRefCallProductConvList(CallProductConvDto convDto);
	CallProductConvDto getSeqCallProductConv(CallProductConvDto convDto);
	List<CallProductConvDto> findCallProductConvName(CallProductConvDto convDto);
	int getCallProductTotalNumber(CallProductConvDto convDto);
	int updateRefCallProductConv(CallProductConvOrderListDto orderDto);
	int cancelCallRefProductConv(CallProductConvOrderListDto orderDto);
	int deleteCallRefProductConv(CallProductConvOrderListDto orderDto);
	int deleteCallProduct(CallProductConvDto callDto);
	
	List<CallProductConvOrderListDto> getAllConvOrderList(CallProductConvParam param);
	CallProductConvOrderListDto getRefConvOrderList(CallProductConvDto convDto);
	int addConvOrderList(CallProductConvOrderListDto orderDto);
	int updateConvOrderList(CallProductConvOrderListDto orderDto);
	int cancelConvOrderList(CallProductConvOrderListDto orderDto);
	int deleteConvOrderList(CallProductConvOrderListDto orderDto);
	int updateCallToOrderList(CallProductConvOrderListDto orderDto);

	int updateProductOriginPrice(ProductDto dto);
}
