package possg.com.a.dto;

public class ProductDto {
    private int productSeq;
    private int convSeq;
    private int categoryId;
    private String productName;
    private String productRomanName;
    private String productTranslationName;
    private int price;
    private int priceOrigin;
    private int priceDiscount;
    private int stockQuantity;
    private String expirationDate;
    private int expirationFlag=0;
    private double discountRate;
    private int promotionInfo;
    private String barcode;
    private String imgUrl;
    private int totalStock;
    private int amount=0;
    private String callDate;
    private int stockLimit=0;

    // 생성자
    public ProductDto() {}
    
    public ProductDto(String productName, int convSeq) {
    	super();
    	this.productName = productName;
    	this.convSeq = convSeq;
    }
    
    public ProductDto(int productSeq, int amount) {
    	this.productSeq = productSeq;
    	this.amount = amount;
    }

	public ProductDto(int productSeq, int convSeq, int categoryId, String productName, String productRomanName,
			int price, int priceOrigin, int priceDiscount, int stockQuantity, String expirationDate, int expirationFlag,
			double discountRate, int promotionInfo, String barcode, String imgUrl) {
		super();
		this.productSeq = productSeq;
		this.convSeq = convSeq;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productRomanName = productRomanName;
		this.price = price;
		this.priceOrigin = priceOrigin;
		this.priceDiscount = priceDiscount;
		this.stockQuantity = stockQuantity;
		this.expirationDate = expirationDate;
		this.expirationFlag = expirationFlag;
		this.discountRate = discountRate;
		this.promotionInfo = promotionInfo;
		this.barcode = barcode;
		this.imgUrl = imgUrl;
		
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductRomanName() {
		return productRomanName;
	}

	public void setProductRomanName(String productRomanName) {
		this.productRomanName = productRomanName;
	}

	public String getProductTranslationName() {
		return productTranslationName;
	}

	public void setProductTranslationName(String productTranslationName) {
		this.productTranslationName = productTranslationName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPriceOrigin() {
		return priceOrigin;
	}

	public void setPriceOrigin(int priceOrigin) {
		this.priceOrigin = priceOrigin;
	}

	public int getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(int priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getExpirationFlag() {
		return expirationFlag;
	}

	public void setExpirationFlag(int expirationFlag) {
		this.expirationFlag = expirationFlag;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public int getPromotionInfo() {
		return promotionInfo;
	}

	public void setPromotionInfo(int promotionInfo) {
		this.promotionInfo = promotionInfo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public int getStockLimit() {
		return stockLimit;
	}

	public void setStockLimit(int stockLimit) {
		this.stockLimit = stockLimit;
	}

	@Override
	public String toString() {
		return "ProductDto [productSeq=" + productSeq + ", convSeq=" + convSeq + ", categoryId=" + categoryId
				+ ", productName=" + productName + ", productRomanName=" + productRomanName
				+ ", productTranslationName=" + productTranslationName + ", price=" + price + ", priceOrigin=" + priceOrigin + ", priceDiscount="
				+ priceDiscount + ", stockQuantity=" + stockQuantity + ", expirationDate=" + expirationDate + ", expirationFlag=" + expirationFlag
				+ ", discountRate=" + discountRate + ", promotionInfo=" + promotionInfo + ", barcode=" + barcode
				+ ", imgUrl=" + imgUrl  + ", amount=" + amount + "]";
	}

}