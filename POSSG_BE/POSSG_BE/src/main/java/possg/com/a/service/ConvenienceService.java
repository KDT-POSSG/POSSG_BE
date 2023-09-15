package possg.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ConvenienceDao;
import possg.com.a.dto.ConvenienceDto;


@Service
@Transactional
public class ConvenienceService {
	
	@Autowired
	ConvenienceDao dao;
	
	@Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

	 @Value("${naver-cloud-sms.secretKey}")
	    private String secretKey;
	 
	 @Value("${naver-cloud-sms.serviceId}")
	    private String serviceId;
	 
	 @Value("${naver-cloud-sms.senderPhone}")
	    private String senderPhone;

	//아이디 중복체크
	public int idcheck(String userId) {
		return dao.idcheck(userId);
	}
	
	//회원가입
	public int adduser(ConvenienceDto dto) {
		return dao.adduser(dto);
	}
	
	//로그인
	public ConvenienceDto login(ConvenienceDto dto) {
		return dao.login(dto);
	}
	
	//마이페이지수정
	public int updateMypage(ConvenienceDto dto) {
		return dao.updateMypage(dto);
	}

	
	//아이디찾기
	public ConvenienceDto findUserByAddressAndPhoneNumber(String representativeName, String phoneNumber) {
	    return dao.findUserByAddressAndPhoneNumber(representativeName, phoneNumber);
	}

	
	
	// 비밀번호 업데이트
	public void changePassword(ConvenienceDto dto) {
	    // newPassword 필드의 값이 null이 아닌 경우에만 password 필드로 복사
	    if (dto.getNewPwd() != null) {
	        dto.setPwd(dto.getNewPwd());
	    }
	    dao.changePassword(dto);
	}
	
	// 비밀번호 찾기
	public ConvenienceDto findUserByAddressAndUserId(String phoneNumber, String userId) {
	    return dao.findUserByAddressAndUserId(phoneNumber, userId);
	}
	
	//마이페이지
	public ConvenienceDto mypage(String userId) {
		return dao.mypage(userId);
	}
	
	//키 체크
	public int keycheck(String convKey) {
		return dao.keycheck(convKey);
	}
	
	public int updateCodeStatus(ConvenienceDto dto) {
		return dao.updateCodeStatus(dto);
	}

}