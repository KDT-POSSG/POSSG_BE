package possg.com.a.dto;

public class DeliveryCount {

	private int beforeOrder;
	private int afterOrder;
	private int Delivering;
	
	
	public DeliveryCount() {
	}

	public DeliveryCount(int beforeOrder, int afterOrder, int delivering) {
		super();
		this.beforeOrder = beforeOrder;
		this.afterOrder = afterOrder;
		Delivering = delivering;
	}

	public int getBeforeOrder() {
		return beforeOrder;
	}

	public void setBeforeOrder(int beforeOrder) {
		this.beforeOrder = beforeOrder;
	}

	public int getAfterOrder() {
		return afterOrder;
	}

	public void setAfterOrder(int afterOrder) {
		this.afterOrder = afterOrder;
	}

	public int getDelivering() {
		return Delivering;
	}

	public void setDelivering(int delivering) {
		Delivering = delivering;
	}

	@Override
	public String toString() {
		return "DeliveryCount [beforeOrder=" + beforeOrder + ", afterOrder=" + afterOrder + ", Delivering=" + Delivering
				+ "]";
	}
		
}
