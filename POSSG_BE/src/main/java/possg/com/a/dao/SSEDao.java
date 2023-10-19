package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.ProductDto;

@Mapper
@Repository
public interface SSEDao {
	List<ProductDto> checkExpiredProduct();
}
