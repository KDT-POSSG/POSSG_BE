package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.PaymentDao;
import possg.com.a.dto.PaymentDto;
import possg.com.a.dto.PaymentParam;

@Service
@Transactional // 트랜잭션단위 - 오류 발생시 모두 롤백
public class PaymentService {
	
	@Autowired
	PaymentDao dao;
	
	public int addpayment(PaymentDto dto) {
		return dao.addpayment(dto);
	}
	
	public int cancelpayment(String receiptId) {
		return dao.cancelpayment(receiptId);
	};
	
	public List<PaymentParam> paymentlist(int convSeq){
		return dao.paymentlist(convSeq);
	};
}