package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.PaymentDao;
import possg.com.a.dto.PaymentDto;

@Service
@Transactional // 트랜잭션단위 - 오류 발생시 모두 롤백
public class PaymentService {
	
	@Autowired
	PaymentDao dao;
	
	public int addkakaopayment(PaymentDto dto) {
		return dao.addkakaopayment(dto);
	}
	
	public int cancelkakaopayment(int seq) {
		return dao.cancelkakaopayment(seq);
	}
}
