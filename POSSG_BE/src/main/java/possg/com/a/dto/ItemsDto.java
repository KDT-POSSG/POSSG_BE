package possg.com.a.dto;

public class ItemsDto {
	private String receiptId;
	private int itemId;
	private String itemName;
	private int qty;
	private int price;
	
	public ItemsDto() {
		
	}

	public ItemsDto(String receiptId, int itemId, String itemName, int qty, int price) {
		super();
		this.receiptId = receiptId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.qty = qty;
		this.price = price;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemsDto [receiptId=" + receiptId + ", itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty
				+ ", price=" + price + "]";
	}
	
	
}
