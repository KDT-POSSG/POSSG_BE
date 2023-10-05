package possg.com.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.PointParam;

@Mapper
@Repository
public interface PointDao {
	int newPoint(String phoneNumber);
	int checkPoint(String phoneNumber);
	int addPoint(PointParam param);
	int searchPoint(String phoneNumber);
	int usePoint(PointParam param);
}
