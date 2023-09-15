package possg.com.a.dto;

import java.util.List;

public class SmsRequestDto {

	private String type ="SMS"; 
	private String contentType="COMM";
	private String countryCode="82";
	private String from="01024892132";
	private String content="인증번호를 입력해주세요 234234 ";
	private List<MessageDto> messages;
	
	public SmsRequestDto() {
	}
	
	
	public SmsRequestDto(String type, String contentType, String countryCode, String from, String content,
			List<MessageDto> messages) {
		super();
		this.type = type;
		this.contentType = contentType;
		this.countryCode = countryCode;
		this.from = from;
		this.content = content;
		this.messages = messages;
	}


	



	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public String getContentType() {
		return contentType;
	}





	public void setContentType(String contentType) {
		this.contentType = contentType;
	}





	public String getCountryCode() {
		return countryCode;
	}





	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}





	public String getFrom() {
		return from;
	}





	public void setFrom(String from) {
		this.from = from;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public List<MessageDto> getMessages() {
		return messages;
	}





	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}






	public static class Builder {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<MessageDto> messages;

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder setFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setMessages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }

        public SmsRequestDto build() {
            SmsRequestDto smsRequestDto = new SmsRequestDto();
            smsRequestDto.type = this.type;
            smsRequestDto.contentType = this.contentType;
            smsRequestDto.countryCode = this.countryCode;
            smsRequestDto.from = this.from;
            smsRequestDto.content = this.content;
            smsRequestDto.messages = this.messages;
            return smsRequestDto;
        }

    }
	

	@Override
	public String toString() {
		return "SmsRequestDto [type=" + type + ", contentType=" + contentType + ", countryCode=" + countryCode
				+ ", from=" + from + ", content=" + content + ", messages=" + messages + "]";
	}
	
	
	
}
