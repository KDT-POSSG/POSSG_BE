package possg.com.a.dto;

public class CallProductConvDto {
    private int callSeq;
    private String userId;
    private int productSeq;
    private int amount;
    private String rpName;
    private String bName;
    private int price;
    private String callDate;
    private String productName;
    private String callRef;
    private int callStatus;

    // 생성자
    public CallProductConvDto() {}

	public CallProductConvDto(int callSeq, String userId, int productSeq, int amount, String rpName, String bName,
			int price, String callDate, String productName, String callRef, int callStatus) {
		super();
		this.callSeq = callSeq;
		this.userId = userId;
		this.productSeq = productSeq;
		this.amount = amount;
		this.rpName = rpName;
		this.bName = bName;
		this.price = price;
		this.callDate = callDate;
		this.productName = productName;
		this.callRef = callRef;
		this.callStatus = callStatus;
	}

	public int getCallSeq() {
		return callSeq;
	}

	public void setCallSeq(int callSeq) {
		this.callSeq = callSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "CallProductConvDto [callSeq=" + callSeq + ", userId=" + userId + ", productSeq=" + productSeq
				+ ", amount=" + amount + ", rpName=" + rpName + ", bName=" + bName + ", price=" + price + ", callDate="
				+ callDate + ", productName=" + productName + ", callRef=" + callRef + ", callStatus=" + callStatus
				+ "]";
	}

	
    
}