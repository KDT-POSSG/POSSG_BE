package possg.com.a.dto;

public class DeliveryCount {

	private int status1;
	private int status2;
	private int status3;
	
	
	public DeliveryCount() {
	}

	public DeliveryCount(int status1, int status2, int status3) {
		super();
		this.status1 = status1;
		this.status2 = status2;
		this.status3 = status3;
	}

	public int getStatus1() {
		return status1;
	}

	public void setStatus1(int status1) {
		this.status1 = status1;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

	public int getStatus3() {
		return status3;
	}

	public void setStatus3(int status3) {
		this.status3 = status3;
	}

	@Override
	public String toString() {
		return "DeliveryCount [status1=" + status1 + ", status2=" + status2 + ", status3=" + status3 + "]";
	}
		
}
