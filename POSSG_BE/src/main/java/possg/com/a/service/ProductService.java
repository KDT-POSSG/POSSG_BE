package possg.com.a.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.ProductDao;
import possg.com.a.dto.CallProductConvDto;
import possg.com.a.dto.CallProductConvOrderListDto;
import possg.com.a.dto.CallProductConvParam;
import possg.com.a.dto.CallProductCustomerDto;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.NutritionDto;
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
	
	//Product
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
	public List<ProductDto> getProductSeqAndTotalStock(ProductDto dto){
		return dao.getProductSeqAndTotalStock(dto);
	}
	public int updateProductStock(ProductDto dto) {
		return dao.updateProductStock(dto);
	}
	public List<ProductDto> findStockName(ProductDto dto){
		return dao.findStockName(dto);
	}
	public int deleteProduct(ProductDto dto) {
		return dao.deleteProduct(dto);
	}
	public int deleteProductRegiInfo(ProductDto dto) {
		return dao.deleteProductRegiInfo(dto);
	}
	public int addProduct(ProductDto dto) {
		return dao.addProduct(dto);
	}
	public ProductDto findProductSeq(int seq) {
		return dao.findProductSeq(seq);
	}
	public List<ProductDto> findProductName(ProductDto dto) {
		return dao.findProductName(dto);
	}
	public List<ProductDto> findProductBarcode(ProductDto dto) {
		return dao.findProductBarcode(dto);
	}
	public List<ProductDto> findAllProductBarcode(ProductDto dto) {
		return dao.findAllProductBarcode(dto);
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
	public CallProductConvParam getCallProductTotalInfo(CallProductConvDto convDto) {
		return dao.getCallProductTotalInfo(convDto);
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
	public int statusUpdateCallRefProductConv(CallProductConvOrderListDto orderDto) {
		return dao.statusUpdateCallRefProductConv(orderDto);
	}
	public int deleteCallProductConv(CallProductConvDto callDto) {
		return dao.deleteCallProductConv(callDto);
	}
	public int completeCallRefProductConv(CallProductConvDto convDto) {
		return dao.completeCallRefProductConv(convDto);
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
	public int statusUpdateConvOrderList(CallProductConvOrderListDto orderDto) {
		return dao.statusUpdateConvOrderList(orderDto);
	}
	public int updateCallToOrderList(CallProductConvOrderListDto orderDto) {
		return dao.updateCallToOrderList(orderDto);
	}
	
	public int statusUpdateConvOrderAndProduct(CallProductConvOrderListDto orderDto) {
		return dao.statusUpdateConvOrderAndProduct(orderDto);
	}
	public int completeConvOrderAndProduct(CallProductConvOrderListDto orderDto) {
		return dao.completeConvOrderAndProduct(orderDto);
	}
	
	public int updateProductOriginPrice(ProductDto dto) {
		return dao.updateProductOriginPrice(dto);
	}
	
	public int getOrderListTotalNumber(CallProductConvParam param) {
		return dao.getOrderListTotalNumber(param);
	}
	
	public int updateExpirationFlagAuto() {
		return dao.updateExpirationFlagAuto();
	}
	
	public int updateProductExpirationFlag(ProductDto dto) {
		return dao.updateProductExpirationFlag(dto);
	}
	
	public int addNutritionInfo(NutritionDto nutDto) {
		return dao.addNutritionInfo(nutDto);
	}
	public NutritionDto getNutritionInfo(NutritionDto seqDto){
		return dao.getNutritionInfo(seqDto);
	}
	public ProductDto findProductSeq(int seq) {
		return dao.findProductSeq(seq);
	}
}