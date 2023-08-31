package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ProductDao;
import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;


@Service
@Transactional
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public List<ProductDto> productList(ProductParam param){
		return dao.productList(param);
	}
	
	public int getAllProduct(ProductParam param) {
		return dao.getAllProduct(param);
	}
	
	public int productWrite(ProductDto dto) {
		return dao.productWrite(dto);
	}
	
	public String productNameFind(String name) {
		return dao.productNameFind(name);
	}
	
	public int getTotalStock(String name) {
		return dao.getTotalStock(name);
	}
	
	public int callProductConvAdd(CallProductConvDto dto) {
		return dao.callProductConvAdd(dto);
	}
	
}
