package possg.com.a.dto;

public class CostParam {
	private int delRef;			// delivery ref	팔린 시간
	private int delTotalPrice; // delivery 등 배달 가격
	private int paymentPrice;	//payment 매장가격
	private String branchName; //conv	지점명
	private int convSeq;  
	private int choice;  // day week month
	
	public CostParam() {
	}
	
	public CostParam(int delRef, int delTotalPrice,
			int paymentPrice, String branchName, int convSeq,
			int choice) {
		super();
		this.delRef = delRef;
		this.delTotalPrice = delTotalPrice;
		this.paymentPrice = paymentPrice;
		this.branchName = branchName;
		this.convSeq = convSeq;
		this.choice = choice;
	}

	
	public int getDelRef() {
		return delRef;
	}

	public void setDelRef(int delRef) {
		this.delRef = delRef;
	}

	public int getDelTotalPrice() {
		return delTotalPrice;
	}

	public void setDelTotalPrice(int delTotalPrice) {
		this.delTotalPrice = delTotalPrice;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "CostParam [delRef=" + delRef + ", delTotalPrice=" + delTotalPrice + ", paymentPrice=" + paymentPrice
				+ ", branchName=" + branchName + ", convSeq=" + convSeq + ", choice=" + choice + "]";
	}

	

}


