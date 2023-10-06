package possg.com.a.dto;

public class CostChangeTypeDto {
 	private int costSeq;
    private String rent;
    private String waterBill;
    private String electricityBill;
    private String gasBill;
    private String totalLaborCost;
    private String securityMaintenanceFee;
    private int convSeq;
    private int costYear;
    private int costMonth;
    
    public CostChangeTypeDto() {
	}

	public CostChangeTypeDto(int costSeq, String rent, String waterBill, String electricityBill, String gasBill,
			String totalLaborCost, String securityMaintenanceFee, int convSeq, int costYear, int costMonth) {
		super();
		this.costSeq = costSeq;
		this.rent = rent;
		this.waterBill = waterBill;
		this.electricityBill = electricityBill;
		this.gasBill = gasBill;
		this.totalLaborCost = totalLaborCost;
		this.securityMaintenanceFee = securityMaintenanceFee;
		this.convSeq = convSeq;
		this.costYear = costYear;
		this.costMonth = costMonth;
	}

	public int getCostSeq() {
		return costSeq;
	}

	public void setCostSeq(int costSeq) {
		this.costSeq = costSeq;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getWaterBill() {
		return waterBill;
	}

	public void setWaterBill(String waterBill) {
		this.waterBill = waterBill;
	}

	public String getElectricityBill() {
		return electricityBill;
	}

	public void setElectricityBill(String electricityBill) {
		this.electricityBill = electricityBill;
	}

	public String getGasBill() {
		return gasBill;
	}

	public void setGasBill(String gasBill) {
		this.gasBill = gasBill;
	}

	public String getTotalLaborCost() {
		return totalLaborCost;
	}

	public void setTotalLaborCost(String totalLaborCost) {
		this.totalLaborCost = totalLaborCost;
	}

	public String getSecurityMaintenanceFee() {
		return securityMaintenanceFee;
	}

	public void setSecurityMaintenanceFee(String securityMaintenanceFee) {
		this.securityMaintenanceFee = securityMaintenanceFee;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getCostYear() {
		return costYear;
	}

	public void setCostYear(int costYear) {
		this.costYear = costYear;
	}

	public int getCostMonth() {
		return costMonth;
	}

	public void setCostMonth(int costMonth) {
		this.costMonth = costMonth;
	}

	@Override
	public String toString() {
		return "CostChangeTypeDto [costSeq=" + costSeq + ", rent=" + rent + ", waterBill=" + waterBill
				+ ", electricityBill=" + electricityBill + ", gasBill=" + gasBill + ", totalLaborCost=" + totalLaborCost
				+ ", securityMaintenanceFee=" + securityMaintenanceFee + ", convSeq=" + convSeq + ", costYear="
				+ costYear + ", costMonth=" + costMonth + "]";
	}
    
}
