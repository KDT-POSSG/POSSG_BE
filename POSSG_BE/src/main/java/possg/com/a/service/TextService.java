package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.TextDao;

@Service
@Transactional
public class TextService {
	
	@Autowired
	TextDao dao;

}
