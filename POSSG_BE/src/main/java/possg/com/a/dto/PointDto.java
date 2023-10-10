package possg.com.a.dto;

public class PointDto {
    private int ptSeq;
    private String phoneNumber;
    private int totalPoint;
    private String pwd;
    
    // 생성자
    public PointDto() {}

	

	public PointDto(int ptSeq, String phoneNumber, int totalPoint, String pwd) {
		super();
		this.ptSeq = ptSeq;
		this.phoneNumber = phoneNumber;
		this.totalPoint = totalPoint;
		this.pwd = pwd;
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

	
	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	@Override
	public String toString() {
		return "PointDto [ptSeq=" + ptSeq + ", phoneNumber=" + phoneNumber + ", totalPoint=" + totalPoint + ", pwd="
				+ pwd + "]";
	}

	
    
}
