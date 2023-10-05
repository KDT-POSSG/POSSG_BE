package possg.com.a.dto;

// 포인트 적립, 차감시 쓰는 param
public class PointParam {
	private String phoneNumber;
    private int point;
    
    public PointParam() {
    	
    }

	public PointParam(String phoneNumber, int point) {
		super();
		this.phoneNumber = phoneNumber;
		this.point = point;
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

	@Override
	public String toString() {
		return "PointParam [phoneNumber=" + phoneNumber + ", point=" + point + "]";
	}
    
    
}
