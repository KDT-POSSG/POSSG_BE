package possg.com.a.dto;

// 결제 조회용
public class PaymentParam {
	private String customerSeq;
	private String customerName;
	private String branchName;
	private String representativeName;
	private String receiptId;
	private String pg;
	private String method;
	private int price;
	private String purchasedAt;
	private String receiptUrl;
	private String cardCompany;
	private String cardNum;
	private String del;
	private String ptPhoneNum; 		
    private int usePoint;		
	
	public PaymentParam() {
		
	}
	

	public PaymentParam(String customerSeq, String customerName, String branchName, String representativeName,
			String receiptId, String pg, String method, int price, String purchasedAt, String receiptUrl,
			String cardCompany, String cardNum, String del, String ptPhoneNum, int usePoint) {
		super();
		this.customerSeq = customerSeq;
		this.customerName = customerName;
		this.branchName = branchName;
		this.representativeName = representativeName;
		this.receiptId = receiptId;
		this.pg = pg;
		this.method = method;
		this.price = price;
		this.purchasedAt = purchasedAt;
		this.receiptUrl = receiptUrl;
		this.cardCompany = cardCompany;
		this.cardNum = cardNum;
		this.del = del;
		this.ptPhoneNum = ptPhoneNum;
		this.usePoint = usePoint;
	}


	public String getCustomerSeq() {
		return customerSeq;
	}

	public void setCustomerSeq(String customerSeq) {
		this.customerSeq = customerSeq;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getPg() {
		return pg;
	}

	public void setPg(String pg) {
		this.pg = pg;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPurchasedAt() {
		return purchasedAt;
	}

	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}

	public String getCardCompany() {
		return cardCompany;
	}

	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}
	

	public String getReceiptUrl() {
		return receiptUrl;
	}


	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}


	public String getPtPhoneNum() {
		return ptPhoneNum;
	}


	public void setPtPhoneNum(String ptPhoneNum) {
		this.ptPhoneNum = ptPhoneNum;
	}


	public int getUsePoint() {
		return usePoint;
	}


	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}


	@Override
	public String toString() {
		return "PaymentParam [customerSeq=" + customerSeq + ", customerName=" + customerName + ", branchName="
				+ branchName + ", representativeName=" + representativeName + ", receiptId=" + receiptId + ", pg=" + pg
				+ ", method=" + method + ", price=" + price + ", purchasedAt=" + purchasedAt + ", receiptUrl="
				+ receiptUrl + ", cardCompany=" + cardCompany + ", cardNum=" + cardNum + ", del=" + del
				+ ", ptPhoneNum=" + ptPhoneNum + ", usePoint=" + usePoint + "]";
	}
	
}
