package possg.com.a.dto;

public class ConvenienceBookmarkDto {
    private int bookmarkSeq;
    private String bookmarkName;
    private int convSeq;

    // 생성자
    public ConvenienceBookmarkDto() {}

    public ConvenienceBookmarkDto(int bookmarkSeq, String bookmarkName, int convSeq) {
        this.bookmarkSeq = bookmarkSeq;
        this.bookmarkName = bookmarkName;
        this.convSeq = convSeq;
    }

    // Getter, Setter    
    public int getBookmarkSeq() {
		return bookmarkSeq;
	}

	public void setBookmarkSeq(int bookmarkSeq) {
		this.bookmarkSeq = bookmarkSeq;
	}

	public String getBookmarkName() {
		return bookmarkName;
	}

	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	// toString()
    @Override
    public String toString() {
        return "ConvenienceBookmarkDto{" +
                "bookmarkSeq=" + bookmarkSeq +
                ", bookmarkName='" + bookmarkName + '\'' +
                ", convSeq=" + convSeq +
                '}';
    }
}
