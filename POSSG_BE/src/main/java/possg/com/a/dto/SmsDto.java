package possg.com.a.dto;

public class SmsDto {

	private int seq;
	private int smsNum;
	private String createAt;
	private String phoneNumber;
	private int count;
	
	public SmsDto(int seq, int smsNum, String createAt, String phoneNumber, int count) {
		super();
		this.seq = seq;
		this.smsNum = smsNum;
		this.createAt = createAt;
		this.phoneNumber = phoneNumber;
		this.count = count;
	}

	public SmsDto() {
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getSmsNum() {
		return smsNum;
	}


	public void setSmsNum(int smsNum) {
		this.smsNum = smsNum;
	}


	public String getCreateAt() {
		return createAt;
	}


	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "SmsDto [seq=" + seq + ", smsNum=" + smsNum + ", createAt=" + createAt + ", phoneNumber=" + phoneNumber
				+ ", count=" + count + "]";
	}

}
