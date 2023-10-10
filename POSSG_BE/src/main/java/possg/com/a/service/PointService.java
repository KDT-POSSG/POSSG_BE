package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.PointDao;
import possg.com.a.dto.PointDto;
import possg.com.a.dto.PointParam;

@Service
@Transactional
public class PointService {
	
	@Autowired
	PointDao dao;
	
	public int newPoint(PointDto dto) {
		return dao.newPoint(dto);
	};
	
	public int checkPoint(String phoneNumber) {
		return dao.checkPoint(phoneNumber);
	};
	
	public int addPoint(PointParam param) {
		return dao.addPoint(param);
	};
	
	public PointDto searchPoint(PointParam param) {
		return dao.searchPoint(param);
	};
	
	public int usePoint(PointParam param) {
		return dao.usePoint(param);
	};
}
