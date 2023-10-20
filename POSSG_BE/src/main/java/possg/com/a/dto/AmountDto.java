package possg.com.a.dto;

public class AmountDto {
	private int amount;
	
	public AmountDto() {
		
	}

	public AmountDto(int amount) {
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
