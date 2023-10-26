package possg.com.a.dto;

public class DeliveryCount {

	private int beforeOrder;
	private int afterOrder;
	private int delivering;
	private int count;
	
	
	public DeliveryCount() {
	}

	public DeliveryCount(int beforeOrder, int afterOrder, int delivering, int count) {
		super();
		this.beforeOrder = beforeOrder;
		this.afterOrder = afterOrder;
		this.delivering = delivering;
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
		return delivering;
	}

	public void setDelivering(int delivering) {
		this.delivering = delivering;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DeliveryCount [beforeOrder=" + beforeOrder + ", afterOrder=" + afterOrder + ", delivering=" + delivering
				+ ", count=" + count + "]";
	}
		
}
