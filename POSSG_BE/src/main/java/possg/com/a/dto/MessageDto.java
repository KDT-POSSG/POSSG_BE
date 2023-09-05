package possg.com.a.dto;

public class MessageDto {
	
	private String to;
	private String content;
	
	public MessageDto() {
	}

	public MessageDto(String to, String content) {
		super();
		this.to = to;
		this.content = content;

	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	@Override
	public String toString() {
		return "MessageDto [to=" + to + ", content=" + content + "]";
	}

	
	

}
