package possg.com.a.dto;

public class CustomerTokenDto {
	private int seq;
	private String refresh;
	private String customerId;
	
	public CustomerTokenDto() {
	}

	public CustomerTokenDto(int seq, String refresh, String customerId) {
		super();
		this.seq = seq;
		this.refresh = refresh;
		this.customerId = customerId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerTokenDto [seq=" + seq + ", refresh=" + refresh + ", customerId=" + customerId + "]";
	}
	
	

}
