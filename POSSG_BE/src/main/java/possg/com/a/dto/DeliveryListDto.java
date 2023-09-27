package possg.com.a.dto;

public class DeliveryListDto {
	private int seq;
    private String delRef;
    private String delDate;
    private int delStatus;
    private int delTotalNumber;
    private int delTotalPrice;
    private String delRemark;
    private int userId;
    
    public DeliveryListDto() {
	}
      
	public DeliveryListDto(int seq, String delRef, String delDate, int delStatus, int delTotalNumber, int delTotalPrice,
			String delRemark, int userId) {
		super();
		this.seq = seq;
		this.delRef = delRef;
		this.delDate = delDate;
		this.delStatus = delStatus;
		this.delTotalNumber = delTotalNumber;
		this.delTotalPrice = delTotalPrice;
		this.delRemark = delRemark;
		this.userId = userId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDelRef() {
		return delRef;
	}

	public void setDelRef(String delRef) {
		this.delRef = delRef;
	}

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getDelTotalNumber() {
		return delTotalNumber;
	}

	public void setDelTotalNumber(int delTotalNumber) {
		this.delTotalNumber = delTotalNumber;
	}

	public int getDelTotalPrice() {
		return delTotalPrice;
	}

	public void setDelTotalPrice(int delTotalPrice) {
		this.delTotalPrice = delTotalPrice;
	}

	public String getDelRemark() {
		return delRemark;
	}

	public void setDelRemark(String delRemark) {
		this.delRemark = delRemark;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "DeliveryListDto [seq=" + seq + ", delRef=" + delRef + ", delDate=" + delDate + ", delStatus="
				+ delStatus + ", delTotalNumber=" + delTotalNumber + ", delTotalPrice=" + delTotalPrice + ", delRemark="
				+ delRemark + ", userId=" + userId + "]";
	}

	

}
