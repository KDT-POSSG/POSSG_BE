package possg.com.a.dto;

public class PaymentDto {
	private String receiptId; 		// 결제 고유번호
    private int userSeq; 			// customer 테이블에서 참조
    private int convSeq;  			// 편의점 고유번호
    private String pg; 				// 결제사
    private String method; 			// 결제 방법 (카카오페이, 네이버페이, 실물카드, 깊티 ...)
    private String discountInfo; 	// 할인 정보
    private int originalPrice; 		// 할인 전 가격
    private int price; 				// 가격
    private String purchasedAt; 	// 결제시간
    private String receiptUrl;		// 결제 영수증 URL
    private String cardNum; 		// 카드 번호
    private String cardCompany; 	// 카드 회사
    private String del; 			// 결제 취소 여부 (결제완료, 결제취소..)
    private String ptPhoneNum; 		// 포인트 사용 전화번호
    private int usePoint;			// 포인트 사용량
    private int earnedPoint;		// 포인트 적립량
    
    public PaymentDto() {
	}

	


	public PaymentDto(String receiptId, int userSeq, int convSeq, String pg, String method, String discountInfo,
			int originalPrice, int price, String purchasedAt, String receiptUrl, String cardNum, String cardCompany,
			String del, String ptPhoneNum, int usePoint, int earnedPoint) {
		super();
		this.receiptId = receiptId;
		this.userSeq = userSeq;
		this.convSeq = convSeq;
		this.pg = pg;
		this.method = method;
		this.discountInfo = discountInfo;
		this.originalPrice = originalPrice;
		this.price = price;
		this.purchasedAt = purchasedAt;
		this.receiptUrl = receiptUrl;
		this.cardNum = cardNum;
		this.cardCompany = cardCompany;
		this.del = del;
		this.ptPhoneNum = ptPhoneNum;
		this.usePoint = usePoint;
		this.earnedPoint = earnedPoint;
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
	
	

	public int getOriginalPrice() {
		return originalPrice;
	}


	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
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
	
	
	public int getEarnedPoint() {
		return earnedPoint;
	}


	public void setEarnedPoint(int earnedPoint) {
		this.earnedPoint = earnedPoint;
	}




	@Override
	public String toString() {
		return "PaymentDto [receiptId=" + receiptId + ", userSeq=" + userSeq + ", convSeq=" + convSeq + ", pg=" + pg
				+ ", method=" + method + ", discountInfo=" + discountInfo + ", originalPrice=" + originalPrice
				+ ", price=" + price + ", purchasedAt=" + purchasedAt + ", receiptUrl=" + receiptUrl + ", cardNum="
				+ cardNum + ", cardCompany=" + cardCompany + ", del=" + del + ", ptPhoneNum=" + ptPhoneNum
				+ ", usePoint=" + usePoint + ", earnedPoint=" + earnedPoint + "]";
	}
}
