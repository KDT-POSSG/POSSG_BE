package possg.com.a.dto;

public class SmsDto {

	private int seq;
	private int smsNum;
	
	
	public SmsDto() {
	}


	public SmsDto(int seq, int smsNum) {
		super();
		this.seq = seq;
		this.smsNum = smsNum;
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


	@Override
	public String toString() {
		return "SmsDto [seq=" + seq + ", smsNum=" + smsNum + "]";
	}
	
	
	
}
