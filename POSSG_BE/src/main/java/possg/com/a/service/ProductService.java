package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ProductDao;
import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
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
	public List<ProductDto> getAllProduct(ProductParam param) {
		return dao.getAllProduct(param);
	}
	public int updateProductRomanName(ProductDto dto) {
		return dao.updateProductRomanName(dto);
	}
	public int getProductTotalNumber(ProductParam param) {
		return dao.getProductTotalNumber(param);
	}
	public int addProduct(ProductDto dto) {
		return dao.addProduct(dto);
	}
	public List<ProductDto> findProductName(ProductDto dto) {
		return dao.findProductName(dto);
	}
	
	public int getTotalStock(String name) {
		return dao.getTotalStock(name);
	}
	
	public int addCallProductConv(CallProductConvDto convDto) {
		return dao.addCallProductConv(convDto);
	}
	public int updateCallProductConv(CallProductConvDto convDto) {
		return dao.updateCallProductConv(convDto);
	}
	
	public int addCallProductCustomer(CallProductCustomerDto customerDto) {
		return dao.addCallProductCustomer(customerDto);
	}
	
	public List<ProductDto> getAllProductStock(ProductParam param){
		return dao.getAllProductStock(param);
	}
	
	public ConvenienceDto getConvenienceInfo(String branchName) {
		return dao.getConvenienceInfo(branchName);
	}
	public List<ConvenienceDto> getAllConvenience() {
		return dao.getAllConvenience();
	}
	
	public List<CallProductConvDto> getAllCallProductConvList(){
		return dao.getAllCallProductConvList();
	}
	public List<CallProductConvDto> getRefCallProductConvList(String callRef){
		return dao.getRefCallProductConvList(callRef);
	}
	public CallProductConvDto getSeqCallProductConv(int callSeq){
		return dao.getSeqCallProductConv(callSeq);
	}
	public List<CallProductConvDto> findCallProductConvName(String name){
		return dao.findCallProductConvName(name);
	}
	public int updateRefCallProductConv(String callRef) {
		return dao.updateRefCallProductConv(callRef);
	}
	public int cancelCallRefProductConv(String callRef) {
		return dao.cancelCallRefProductConv(callRef);
	}
	public int deleteCallRefProductConv(String callRef) {
		return dao.deleteCallRefProductConv(callRef);
	}
	public int deleteCallProduct(CallProductConvDto callDto) {
		return dao.deleteCallProduct(callDto);
	}
	
	public List<CallProductConvOrderListDto> getAllConvOrderList(){
		return dao.getAllConvOrderList();
	}
	public CallProductConvOrderListDto getRefConvOrderList(String callRef){
		return dao.getRefConvOrderList(callRef);
	}
	public int addConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.addConvOrderList(orderDto);
	}
	public int updateConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.updateConvOrderList(orderDto);
	}
	public int cancelConvOrderList(String callRef) {
		return dao.cancelConvOrderList(callRef);
	}
	public int deleteConvOrderList(String callRef) {
		return dao.deleteConvOrderList(callRef);
	}
}
