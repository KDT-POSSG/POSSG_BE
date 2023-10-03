package possg.com.a.dto;

import java.util.List;

public class CallProductConvDto {
    private int callSeq;
    private int convSeq;
    private int productSeq;
    private int amount;
    private String rpName;
    private String bName;
    private int price;
    private String callDate;
    private String productName;
    private String callRef;
    private int callStatus;
    private String imgUrl;
    
    private int pageNumber = 0;
    private int pageSize = 20;
    
    private String remark;
    
    private List<String> nameList;

    // 생성자
    public CallProductConvDto() {}
    
    public CallProductConvDto(String callRef, int convSeq) {
    	this.callRef = callRef;
    	this.convSeq = convSeq;
    }


	public CallProductConvDto(int callSeq, int convSeq, int productSeq, int amount, String rpName, String bName,
			int price, String callDate, String productName, String callRef, int callStatus, String imgUrl) {
		super();
		this.callSeq = callSeq;
		this.convSeq = convSeq;
		this.productSeq = productSeq;
		this.amount = amount;
		this.rpName = rpName;
		this.bName = bName;
		this.price = price;
		this.callDate = callDate;
		this.productName = productName;
		this.callRef = callRef;
		this.callStatus = callStatus;
		this.imgUrl = imgUrl;
	}
	
	public CallProductConvDto(int callSeq, int convSeq, int productSeq, int amount, String rpName, String bName,
			int price, String callDate, String productName, String callRef, int callStatus, String imgUrl, String remark) {
		super();
		this.callSeq = callSeq;
		this.convSeq = convSeq;
		this.productSeq = productSeq;
		this.amount = amount;
		this.rpName = rpName;
		this.bName = bName;
		this.price = price;
		this.callDate = callDate;
		this.productName = productName;
		this.callRef = callRef;
		this.callStatus = callStatus;
		this.imgUrl = imgUrl;
		this.remark = remark;
	}


	public int getCallSeq() {
		return callSeq;
	}

	public void setCallSeq(int callSeq) {
		this.callSeq = callSeq;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRpName() {
		return rpName;
	}

	public void setRpName(String rpName) {
		this.rpName = rpName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCallRef() {
		return callRef;
	}

	public void setCallRef(String callRef) {
		this.callRef = callRef;
	}

	public int getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	@Override
	public String toString() {
		return "CallProductConvDto [callSeq=" + callSeq + ", convSeq=" + convSeq + ", productSeq=" + productSeq
				+ ", amount=" + amount + ", rpName=" + rpName + ", bName=" + bName + ", price=" + price + ", callDate="
				+ callDate + ", productName=" + productName + ", callRef=" + callRef + ", callStatus=" + callStatus
				+ ", imgUrl=" + imgUrl + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", remark=" + remark
				+ "]";
	}
}
