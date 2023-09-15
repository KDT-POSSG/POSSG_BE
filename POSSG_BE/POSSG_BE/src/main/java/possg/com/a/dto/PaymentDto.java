package possg.com.a.dto;

public class PaymentDto {
	private String receiptId; // 결제 고유번호
    private int userSeq; // customer 테이블에서 참조
    private int productSeq; // product 테이블에서 참조
    private int convSeq;  // 편의점 고유번호
    private String pg; // 결제사
    private String method; // 결제 방법 (카카오페이, 네이버페이, 실물카드, 깊티 ...)
    private String discountInfo; // 할인 정보
    private int price; // 가격
    private int count; // 수량
    private String purchasedAt; // 결제시간
    private String receiptUrl; // 결제 영수증 URL
    private int ref; // 결제 묶음
    private String cardNum; // 카드 번호
    private String cardCompany; // 카드 회사
    private String del; // 결제 취소 여부 (결제완료, 결제취소..)
    
    public PaymentDto() {
	}
    
	public PaymentDto(String receiptId, int userSeq, int productSeq, int convSeq, String pg, String method,
			String discountInfo, int price, int count, String purchasedAt, String receiptUrl, int ref, String cardNum,
			String cardCompany, String del) {
		super();
		this.receiptId = receiptId;
		this.userSeq = userSeq;
		this.productSeq = productSeq;
		this.convSeq = convSeq;
		this.pg = pg;
		this.method = method;
		this.discountInfo = discountInfo;
		this.price = price;
		this.count = count;
		this.purchasedAt = purchasedAt;
		this.receiptUrl = receiptUrl;
		this.ref = ref;
		this.cardNum = cardNum;
		this.cardCompany = cardCompany;
		this.del = del;
	}

	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	
	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
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
	public String getDiscountInfo() {
		return discountInfo;
	}
	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPurchasedAt() {
		return purchasedAt;
	}
	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	public String getReceiptUrl() {
		return receiptUrl;
	}
	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardCompany() {
		return cardCompany;
	}

	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}

	public String getDel() {
		return del;
	}



	public void setDel(String del) {
		this.del = del;
	}


	@Override
	public String toString() {
		return "PaymentDto [receiptId=" + receiptId + ", userSeq=" + userSeq + ", productSeq=" + productSeq + ", pg="
				+ pg + ", method=" + method + ", discountInfo=" + discountInfo + ", price=" + price + ", count=" + count
				+ ", purchasedAt=" + purchasedAt + ", receiptUrl=" + receiptUrl + ", ref=" + ref + ", cardNum="
				+ cardNum + ", cardCompany=" + cardCompany + ", del=" + del + "]";
	}
	
}
