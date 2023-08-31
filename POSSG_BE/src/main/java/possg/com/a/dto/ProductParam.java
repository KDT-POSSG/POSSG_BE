package possg.com.a.dto;

import java.io.Serializable;

public class ProductParam implements Serializable{
	
	private String choice;	// 카테고리(제목/내용/작성자)
	private String search;	// 검색어
	private int pageNumber;
	private int seq;
	
	private int minPrice; // 최소 가격
	private int maxPrice; // 최대 가격
	private int promotionInfo; // 프로모션 정보
	private int categoryId; // 1: 행사상품, 2: 신선식품
	
	private String sortOrder;

	public ProductParam() {
	}

	public ProductParam(int seq, int pageNumber) {
		super();
		this.seq = seq;
		this.pageNumber = pageNumber;
	}
	
	public ProductParam(String choice, String search, int pageNumber) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
	}
	
	public ProductParam(String choice, String search, int pageNumber, int minPrice, int maxPrice, int promotionInfo, String sortOrder, int categoryId) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.promotionInfo = promotionInfo;
		this.sortOrder = sortOrder;
		this.categoryId = categoryId;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getPromotionInfo() {
		return promotionInfo;
	}

	public void setPromotionInfo(int promotionInfo) {
		this.promotionInfo = promotionInfo;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductParam [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + ", minPrice= " + minPrice + ", maxPrice= " + maxPrice + ", promotionInfo= " + promotionInfo + ", sortOrder= " + sortOrder + ", categoryId= " + categoryId + "]";
	}	
	
}
