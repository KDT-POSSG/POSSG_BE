package possg.com.a.dto;

import java.util.List;

// convSeq와 배열
public class FavoritePageParam {
	private int convSeq;
	private List<Integer> seqList;
	private String pageName;
	private String favoriteEnable;
	
	public FavoritePageParam() {
		
	}

	public FavoritePageParam(int convSeq, List<Integer> seqList, String pageName, String favoriteEnable) {
		super();
		this.convSeq = convSeq;
		this.seqList = seqList;
		this.pageName = pageName;
		this.favoriteEnable = favoriteEnable;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public List<Integer> getSeqList() {
		return seqList;
	}

	public void setSeqList(List<Integer> seqList) {
		this.seqList = seqList;
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
		return "FavoritePageParam [convSeq=" + convSeq + ", seqList=" + seqList + ", pageName=" + pageName
				+ ", favoriteEnable=" + favoriteEnable + "]";
	}
	
	
}
