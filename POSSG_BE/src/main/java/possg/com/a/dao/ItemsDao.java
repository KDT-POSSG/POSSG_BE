package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.ItemsDto;

@Mapper
@Repository
public interface ItemsDao {
	
	int addItems(ItemsDto dto);
	List<ItemsDto> searchItems(String receiptId);
}
