package possg.com.a.dto;

// 포인트 적립, 차감시 쓰는 param
public class PointParam {
	private String phoneNumber;
    private int point;
    private String pwd;
    
    public PointParam() {
    	
    }

	public PointParam(String phoneNumber, int point, String pwd) {
		super();
		this.phoneNumber = phoneNumber;
		this.point = point;
		this.pwd = pwd;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "PointParam [phoneNumber=" + phoneNumber + ", point=" + point + ", pwd=" + pwd + "]";
	}
    
	
}
