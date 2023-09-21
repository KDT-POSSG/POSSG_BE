package possg.com.a.dto;

public class FavoritePageDto {
	private int seq;
	private int convSeq;
	private String pageName;
	private String favoriteEnable;
	
	public FavoritePageDto() {
		
	}

	public FavoritePageDto(int seq, int convSeq, String pageName, String favoriteEnable) {
		super();
		this.seq = seq;
		this.convSeq = convSeq;
		this.pageName = pageName;
		this.favoriteEnable = favoriteEnable;
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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFavoriteEnable() {
		return favoriteEnable;
	}

	public void setFavoriteEnable(String favoriteEnable) {
		this.favoriteEnable = favoriteEnable;
	}

	@Override
	public String toString() {
		return "FavoritePageDto [seq=" + seq + ", convSeq=" + convSeq + ", pageName=" + pageName + ", favoriteEnable="
				+ favoriteEnable + "]";
	}
	
	
}
