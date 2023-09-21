package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.FavoritePageDao;
import possg.com.a.dto.FavoritePageDto;

@Service
@Transactional
public class FavoritePageService {
	
	@Autowired
	FavoritePageDao dao;
	
	public int addFavoritePage(FavoritePageDto dto) {
		return dao.addFavoritePage(dto);
	};
	
	public int resetFavoritePage(int convSeq) {
		return dao.resetFavoritePage(convSeq);
	};
	
	public List<FavoritePageDto> favoritePageList(int convSeq){
		return dao.favoritePageList(convSeq);
	}
}
