package possg.com.a.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductConvParam;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.NutritionDto;
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
	List<ProductDto> getProductSeqAndTotalStock(ProductDto dto);
	int updateProductStock(ProductDto dto);
	List<ProductDto> findStockName(ProductDto dto);
	int deleteProduct(ProductDto dto);
	int deleteProductRegiInfo(ProductDto dto);
	
	int addProduct(ProductDto dto);
	
	ProductDto findProductSeq(int seq); 
	List<ProductDto> findProductName(ProductDto dto);
	List<ProductDto> findProductBarcode(ProductDto dto);
	List<ProductDto> findAllProductBarcode(ProductDto dto);
	
	int getTotalStock(String name);
	int addCallProductConv(ProductDto dto);
	int updateCallProductConv(CallProductConvDto convDto);
	
	List<ProductDto> getAllProductStock(ProductParam param);
	
	int addCallProductCustomer(CallProductCustomerDto cusotmerDto);
	
	ConvenienceDto getConvenienceInfo(String branchName);
	List<ConvenienceDto> getAllConvenience();
	
	List<CallProductConvDto> getAllCallProductConvList(CallProductConvParam param);
	List<CallProductConvDto> getRefCallProductConvList(CallProductConvDto convDto);
	CallProductConvParam getCallProductTotalInfo(CallProductConvDto convDto);
	CallProductConvDto getSeqCallProductConv(CallProductConvDto convDto);
	List<CallProductConvDto> findCallProductConvName(CallProductConvDto convDto);
	int getCallProductTotalNumber(CallProductConvDto convDto);
	int updateRefCallProductConv(CallProductConvOrderListDto orderDto);
	int cancelCallRefProductConv(CallProductConvOrderListDto orderDto);
	int statusUpdateCallRefProductConv(CallProductConvOrderListDto orderDto);
	int deleteCallProductConv(CallProductConvDto convDto);
	int completeCallRefProductConv(CallProductConvDto convDto);
	
	List<CallProductConvOrderListDto> getAllConvOrderList(CallProductConvParam param);
	CallProductConvOrderListDto getRefConvOrderList(CallProductConvDto convDto);
	int addConvOrderList(CallProductConvOrderListDto orderDto);
	int updateConvOrderList(CallProductConvOrderListDto orderDto);
	int cancelConvOrderList(CallProductConvOrderListDto orderDto);
	int statusUpdateConvOrderList(CallProductConvOrderListDto orderDto);
	int updateCallToOrderList(CallProductConvOrderListDto orderDto);

	int statusUpdateConvOrderAndProduct(CallProductConvOrderListDto orderDto);
	int completeConvOrderAndProduct(CallProductConvOrderListDto orderDto);
	int updateProductOriginPrice(ProductDto dto);
	
	int getOrderListTotalNumber(CallProductConvParam param);
	
	int updateExpirationFlagAuto();
	int updateProductExpirationFlag(ProductDto dto);
	
	int addNutritionInfo(NutritionDto nutDto);
	NutritionDto getNutritionInfo(NutritionDto seqDto);

}
