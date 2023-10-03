package possg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ProductDao;
import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductConvParam;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.ProductDto;
import possg.com.a.dto.ProductParam;


@Service
@Transactional
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public String healthcheck() {
		return dao.healthcheck();
	}
	// Product
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
	public List<ProductDto> findProductBarcode(ProductDto dto) {
		return dao.findProductBarcode(dto);
	}
	public int getTotalStock(String name) {
		return dao.getTotalStock(name);
	}
	
	// CallProductConv
	public int addCallProductConv(ProductDto dto) {
		return dao.addCallProductConv(dto);
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
	
	public List<CallProductConvDto> getAllCallProductConvList(CallProductConvParam param){
		return dao.getAllCallProductConvList(param);
	}
	public List<CallProductConvDto> getRefCallProductConvList(CallProductConvDto convDto){
		return dao.getRefCallProductConvList(convDto);
	}
	public int getCallProductTotalPrice(CallProductConvDto convDto) {
		return dao.getCallProductTotalPrice(convDto);
	}
	public int getCallProductTotalAmount(CallProductConvDto convDto) {
		return dao.getCallProductTotalAmount(convDto);
	}

	public CallProductConvDto getSeqCallProductConv(CallProductConvDto convDto){
		return dao.getSeqCallProductConv(convDto);
	}
	public List<CallProductConvDto> findCallProductConvName(CallProductConvDto convDto){
		return dao.findCallProductConvName(convDto);
	}
	public int getCallProductTotalNumber(CallProductConvDto convDto) {
		return dao.getCallProductTotalNumber(convDto);
	}
	public int updateRefCallProductConv(CallProductConvOrderListDto orderDto) {
		return dao.updateRefCallProductConv(orderDto);
	}
	public int cancelCallRefProductConv(CallProductConvOrderListDto orderDto) {
		return dao.cancelCallRefProductConv(orderDto);
	}
	public int deleteCallRefProductConv(CallProductConvOrderListDto orderDto) {
		return dao.deleteCallRefProductConv(orderDto);
	}
	public int deleteCallProduct(CallProductConvDto callDto) {
		return dao.deleteCallProduct(callDto);
	}
	
	// OrderList
	public List<CallProductConvOrderListDto> getAllConvOrderList(CallProductConvParam param){
		return dao.getAllConvOrderList(param);
	}
	public CallProductConvOrderListDto getRefConvOrderList(CallProductConvDto convDto){
		return dao.getRefConvOrderList(convDto);
	}
	public int addConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.addConvOrderList(orderDto);
	}
	public int updateConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.updateConvOrderList(orderDto);
	}
	public int cancelConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.cancelConvOrderList(orderDto);
	}
	public int deleteConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.deleteConvOrderList(orderDto);
	}
	
	
	public int getOrderListTotalNumber(CallProductConvParam param) {
		return dao.getOrderListTotalNumber(param);
	}
}