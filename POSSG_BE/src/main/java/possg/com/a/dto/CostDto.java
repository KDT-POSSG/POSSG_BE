package possg.com.a.dto;

public class CostDto {
    private int costSeq;
    private int rent;
    private int waterBill;
    private int electricityBill;
    private int gasBill;
    private int totalLaborCost;
    private int securityMaintenanceFee;
    private int convSeq;
    private int costYear;
    private int costMonth;

    // 생성자
    public CostDto() {}

    public CostDto(int costSeq, int rent, int waterBill, int electricityBill, int gasBill, int totalLaborCost, int securityMaintenanceFee, int convSeq, int costYear, int costMonth) {
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

    
    // Getter, Setter
    
    public int getCostSeq() {
		return costSeq;
	}

	public void setCostSeq(int costSeq) {
		this.costSeq = costSeq;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getWaterBill() {
		return waterBill;
	}

	public void setWaterBill(int waterBill) {
		this.waterBill = waterBill;
	}

	public int getElectricityBill() {
		return electricityBill;
	}

	public void setElectricityBill(int electricityBill) {
		this.electricityBill = electricityBill;
	}

	public int getGasBill() {
		return gasBill;
	}

	public void setGasBill(int gasBill) {
		this.gasBill = gasBill;
	}

	public int getTotalLaborCost() {
		return totalLaborCost;
	}

	public void setTotalLaborCost(int totalLaborCost) {
		this.totalLaborCost = totalLaborCost;
	}

	public int getSecurityMaintenanceFee() {
		return securityMaintenanceFee;
	}

	public void setSecurityMaintenanceFee(int securityMaintenanceFee) {
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

	// toString()
    @Override
    public String toString() {
        return "CostDto{" +
                "costSeq=" + costSeq +
                ", rent=" + rent +
                ", waterBill=" + waterBill +
                ", electricityBill=" + electricityBill +
                ", gasBill=" + gasBill +
                ", totalLaborCost=" + totalLaborCost +
                ", securityMaintenanceFee=" + securityMaintenanceFee +
                ", convSeq=" + convSeq +
                ", costYear=" + costYear +
                ", costMonth=" + costMonth +
                '}';
    }
}
