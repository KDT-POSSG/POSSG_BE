package possg.com.a.dto;

public class amountDto {
	private int amount;
	
	public amountDto() {
		
	}

	public amountDto(int amount) {
		super();
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "amountDto [amount=" + amount + "]";
	}
	
	
}
