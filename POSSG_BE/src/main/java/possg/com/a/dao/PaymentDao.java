package possg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import possg.com.a.dto.EmployeeDto;
import possg.com.a.dto.PaymentDto;
import possg.com.a.dto.PaymentParam;

@Mapper
@Repository
public interface PaymentDao {
	
	int addpayment(PaymentDto dto);
	int cancelpayment(String receiptId);
	List<PaymentParam> paymentlist(int convSeq);
}
