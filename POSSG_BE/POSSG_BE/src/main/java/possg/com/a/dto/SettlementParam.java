package possg.com.a.dto;

public class SettlementParam {
	private int seq;
	private String convName;
	private String rdate;
	private int cash;
	private String memo;
	
	public SettlementParam() {
		
	}

	public SettlementParam(int seq, String convName, String rdate, int cash, String memo) {
		super();
		this.seq = seq;
		this.convName = convName;
		this.rdate = rdate;
		this.cash = cash;
		this.memo = memo;
	}



	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getConvName() {
		return convName;
	}

	public void setConvName(String convName) {
		this.convName = convName;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "SettlementParam [seq=" + seq + ", convName=" + convName + ", rdate=" + rdate + ", cash=" + cash
				+ ", memo=" + memo + "]";
	}

}
