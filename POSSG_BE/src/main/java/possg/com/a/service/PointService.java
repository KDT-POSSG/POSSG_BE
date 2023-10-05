package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.PointDao;
import possg.com.a.dto.PointParam;

@Service
@Transactional
public class PointService {
	
	@Autowired
	PointDao dao;
	
	public int newPoint(String phoneNumber) {
		return dao.newPoint(phoneNumber);
	};
	
	public int checkPoint(String phoneNumber) {
		return dao.checkPoint(phoneNumber);
	};
	
	public int addPoint(PointParam param) {
		return dao.addPoint(param);
	};
	
	public int searchPoint(String phoneNumber) {
		return dao.searchPoint(phoneNumber);
	};
	
	public int usePoint(PointParam param) {
		return dao.usePoint(param);
	};
}
