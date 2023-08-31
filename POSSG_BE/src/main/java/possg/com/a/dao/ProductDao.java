package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;

@Mapper
@Repository
public interface ProductDao {

	List<ProductDto> productList(ProductParam param);
	int getAllProduct(ProductParam param);
	
	int productWrite(ProductDto dto);
	
	String productNameFind(String name);
	
	int getTotalStock(String name);
	int callProductConvAdd(CallProductConvDto dto);

}
