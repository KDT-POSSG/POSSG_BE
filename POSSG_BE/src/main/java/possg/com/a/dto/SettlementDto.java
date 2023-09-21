package possg.com.a.dto;

public class SettlementDto {
	private int seq;		// 정산 고유번호
	private int convSeq;	// 편의점 고유번호
	private String rdate;	// 기록 날짜
	private int cash;		// 정산 금액
	private String memo;	// 특이사항 메모
	
	public SettlementDto () {
		
	}

	public SettlementDto(int seq, int convSeq, String rdate, int cash, String memo) {
		super();
		this.seq = seq;
		this.convSeq = convSeq;
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

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
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
		return "SettlementDto [seq=" + seq + ", convSeq=" + convSeq + ", rdate=" + rdate + ", cash=" + cash + ", memo="
				+ memo + "]";
	}

	
}
