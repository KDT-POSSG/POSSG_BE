package possg.com.a.dto;

public class AccountNumDto {
	private int accountNumSeq;
	private String accountCode;
	private int codeStatus;
	
	public AccountNumDto() {
	}

	public AccountNumDto(int accountNumSeq, String accountCode, int codeStatus) {
		super();
		this.accountNumSeq = accountNumSeq;
		this.accountCode = accountCode;
		this.codeStatus = codeStatus;
	}

	public int getAccountNumSeq() {
		return accountNumSeq;
	}

	public void setAccountNumSeq(int accountNumSeq) {
		this.accountNumSeq = accountNumSeq;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public int getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}

	@Override
	public String toString() {
		return "AccountNumDto [accountNumSeq=" + accountNumSeq + ", accountCode=" + accountCode + ", codeStatus="
				+ codeStatus + "]";
	}

}
