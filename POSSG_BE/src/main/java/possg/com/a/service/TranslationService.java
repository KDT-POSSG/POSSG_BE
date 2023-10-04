package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.TranslationDao;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;

@Service
@Transactional
public class TranslationService {

	@Autowired
	TranslationDao dao;
	
	public List<ProductDto> getAllProduct() {
		return dao.getAllProduct();
	}
	
	public int updateProductRomanName(ProductDto dto) {
		return dao.updateProductRomanName(dto);
	}
	
}