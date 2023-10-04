package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ItemsDao;
import possg.com.a.dto.ItemsDto;

@Service
@Transactional
public class ItemsService {
	
	@Autowired
	ItemsDao dao;
	
	public int addItems(ItemsDto dto) {
		return dao.addItems(dto);
	};
	
	public List<ItemsDto> searchItems(String receiptId){
		return dao.searchItems(receiptId);
	};
}
