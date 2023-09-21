package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.FavoritePageDto;

@Mapper
@Repository
public interface FavoritePageDao {
	
	int addFavoritePage(FavoritePageDto dto);
	int resetFavoritePage(int convSeq);
	List<FavoritePageDto> favoritePageList(int convSeq);
}
