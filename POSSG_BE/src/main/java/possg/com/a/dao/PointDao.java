package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.PointDto;
import possg.com.a.dto.PointParam;

@Mapper
@Repository
public interface PointDao {
	int newPoint(PointDto dto);
	int checkPoint(String phoneNumber);
	int addPoint(PointParam param);
	PointDto searchPoint(PointParam param);
	int usePoint(PointParam param);
	int checkNumPoint(String phoneNumber);
}
