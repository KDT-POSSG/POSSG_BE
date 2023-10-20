package possg.com.a.dto;

public class NutritionDto {
	private int productSeq;
	private int nutritionSeq;
	private String servingSize;
	private String servingSizeAmount;
	private String calorie;
	private String calorieAmount;
	private String carbohydrate;
	private String carbohydrateAmount;
	private String protein;
	private String proteinAmount;
	private String fat;
	private String fatAmount;
	private String cholesterol;
	private String cholesterolAmount;
	private String sodium;
	private String sodiumAmount;
	private String dietaryFiber;
	private String dietaryFiberAmount;
	private String potassium;
	private String potassiumAmount;
	
	public NutritionDto(){}

	public NutritionDto(int nutritionSeq, int productSeq, String servingSize, String servingSizeAmount, String calorie,
			String calorieAmount, String carbohydrate, String carbohydrateAmount, String protein, String proteinAmount,
			String fat, String fatAmount, String cholesterol, String cholesterolAmount, String sodium,
			String sodiumAmount, String dietaryFiber, String dietaryFiberAmount, String potassium,
			String potassiumAmount) {
		super();
		this.productSeq = productSeq;
		this.nutritionSeq = nutritionSeq;
		this.servingSize = servingSize;
		this.servingSizeAmount = servingSizeAmount;
		this.calorie = calorie;
		this.calorieAmount = calorieAmount;
		this.carbohydrate = carbohydrate;
		this.carbohydrateAmount = carbohydrateAmount;
		this.protein = protein;
		this.proteinAmount = proteinAmount;
		this.fat = fat;
		this.fatAmount = fatAmount;
		this.cholesterol = cholesterol;
		this.cholesterolAmount = cholesterolAmount;
		this.sodium = sodium;
		this.sodiumAmount = sodiumAmount;
		this.dietaryFiber = dietaryFiber;
		this.dietaryFiberAmount = dietaryFiberAmount;
		this.potassium = potassium;
		this.potassiumAmount = potassiumAmount;
	}

	public int getNutritionSeq() {
		return nutritionSeq;
	}

	public void setNutritionSeq(int nutritionSeq) {
		this.nutritionSeq = nutritionSeq;
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	
	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public String getServingSizeAmount() {
		return servingSizeAmount;
	}

	public void setServingSizeAmount(String servingSizeAmount) {
		this.servingSizeAmount = servingSizeAmount;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getCalorieAmount() {
		return calorieAmount;
	}

	public void setCalorieAmount(String calorieAmount) {
		this.calorieAmount = calorieAmount;
	}

	public String getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(String carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public String getCarbohydrateAmount() {
		return carbohydrateAmount;
	}

	public void setCarbohydrateAmount(String carbohydrateAmount) {
		this.carbohydrateAmount = carbohydrateAmount;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getProteinAmount() {
		return proteinAmount;
	}

	public void setProteinAmount(String proteinAmount) {
		this.proteinAmount = proteinAmount;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getFatAmount() {
		return fatAmount;
	}

	public void setFatAmount(String fatAmount) {
		this.fatAmount = fatAmount;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getCholesterolAmount() {
		return cholesterolAmount;
	}

	public void setCholesterolAmount(String cholesterolAmount) {
		this.cholesterolAmount = cholesterolAmount;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public String getSodiumAmount() {
		return sodiumAmount;
	}

	public void setSodiumAmount(String sodiumAmount) {
		this.sodiumAmount = sodiumAmount;
	}

	public String getDietaryFiber() {
		return dietaryFiber;
	}

	public void setDietaryFiber(String dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}

	public String getDietaryFiberAmount() {
		return dietaryFiberAmount;
	}

	public void setDietaryFiberAmount(String dietaryFiberAmount) {
		this.dietaryFiberAmount = dietaryFiberAmount;
	}

	public String getPotassium() {
		return potassium;
	}

	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}

	public String getPotassiumAmount() {
		return potassiumAmount;
	}

	public void setPotassiumAmount(String potassiumAmount) {
		this.potassiumAmount = potassiumAmount;
	}

	@Override
	public String toString() {
		return "NutritionDto [nutritionSeq=" + nutritionSeq + ", productSeq=" + productSeq + ", servingSize="
				+ servingSize + ", servingSizeAmount=" + servingSizeAmount + ", calorie=" + calorie + ", calorieAmount="
				+ calorieAmount + ", carbohydrate=" + carbohydrate + ", carbohydrateAmount=" + carbohydrateAmount
				+ ", protein=" + protein + ", proteinAmount=" + proteinAmount + ", fat=" + fat + ", fatAmount="
				+ fatAmount + ", cholesterol=" + cholesterol + ", cholesterolAmount=" + cholesterolAmount + ", sodium="
				+ sodium + ", sodiumAmount=" + sodiumAmount + ", dietaryFiber=" + dietaryFiber + ", dietaryFiberAmount="
				+ dietaryFiberAmount + ", potassium=" + potassium + ", potassiumAmount=" + potassiumAmount + "]";
	}
	
	
	
}
