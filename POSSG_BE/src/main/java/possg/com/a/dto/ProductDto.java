package possg.com.a.dto;

public class ProductDto {
    private int productSeq;
    private int categoryId;
    private String productName;
    private String productRomanName;
    private String productTranslationName;
    private int price;
    private int priceDiscount;
    private int stockQuantity;
    private String expirationDate;
    private double discountRate;
    private int promotionInfo;
    private String barcode;
    private String imgUrl;
    
    private int totalStock;

    // 생성자
    public ProductDto() {}
    
    public ProductDto(String productName) {
    	super();
    	this.productName = productName;
    }
    
	

	public ProductDto(int productSeq, int categoryId, String productName, String productRomanName,
			String productTranslationName, int price, int priceDiscount, int stockQuantity, String expirationDate,
			double discountRate, int promotionInfo, String barcode, String imgUrl, int totalStock) {
		super();
		this.productSeq = productSeq;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productRomanName = productRomanName;
		this.productTranslationName = productTranslationName;
		this.price = price;
		this.priceDiscount = priceDiscount;
		this.stockQuantity = stockQuantity;
		this.expirationDate = expirationDate;
		this.discountRate = discountRate;
		this.promotionInfo = promotionInfo;
		this.barcode = barcode;
		this.imgUrl = imgUrl;
		this.totalStock = totalStock;
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "ProductDto [productSeq=" + productSeq + ", categoryId=" + categoryId + ", productName=" + productName
				+ ", productRomanName=" + productRomanName + ", productTranslationName=" + productTranslationName
				+ ", price=" + price + ", priceDiscount=" + priceDiscount + ", stockQuantity=" + stockQuantity
				+ ", expirationDate=" + expirationDate + ", discountRate=" + discountRate + ", promotionInfo="
				+ promotionInfo + ", barcode=" + barcode + ", imgUrl=" + imgUrl + ", totalStock=" + totalStock
				+ "]";
	}

}
