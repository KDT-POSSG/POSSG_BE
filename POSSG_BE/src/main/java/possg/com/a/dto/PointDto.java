package possg.com.a.dto;

public class PointDto {
    private int ptSeq;
    private String phoneNumber;
    private int totalPoint;
    
    // 생성자
    public PointDto() {}

	public PointDto(int ptSeq, String phoneNumber, int totalPoint) {
		super();
		this.ptSeq = ptSeq;
		this.phoneNumber = phoneNumber;
		this.totalPoint = totalPoint;
	}

	public int getPtSeq() {
		return ptSeq;
	}

	public void setPtSeq(int ptSeq) {
		this.ptSeq = ptSeq;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	@Override
	public String toString() {
		return "PointDto [ptSeq=" + ptSeq + ", phoneNumber=" + phoneNumber + ", totalPoint=" + totalPoint + "]";
	}
    
    
}
