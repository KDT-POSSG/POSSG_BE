package possg.com.a.dto;

public class TokenDto {
	private int seq;
	private String refresh;
	private String userId;
	
	public TokenDto() {
	}

	public TokenDto(int seq, String refresh, String userId) {
		super();
		this.seq = seq;
		this.refresh = refresh;
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TokenDto [seq=" + seq + ", refresh=" + refresh + ", userId=" + userId + "]";
	}

	
	
}
