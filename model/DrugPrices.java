package model;


public class DrugPrices {
	protected int priceId;
	protected String description;
	protected String currency;
	protected double cost;
	protected String unit;
	protected Drugs drug;
	
	// all-fields constructor
	public DrugPrices(int priceId, String description, String currency, double cost, String unit, Drugs drug) {
		this.priceId = priceId;
		this.description = description;
		this.currency = currency;
		this.cost = cost;
		this.unit = unit;
		this.drug = drug;
	}
	
	// PK-only constructor
	public DrugPrices(int priceId) {
		this.priceId = priceId;
	}

	// no-PK constructor
	public DrugPrices(String description, String currency, double cost, String unit, Drugs drug) {
		this.description = description;
		this.currency = currency;
		this.cost = cost;
		this.unit = unit;
		this.drug = drug;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Drugs getDrug() {
		return drug;
	}

	public void setDrug(Drugs drug) {
		this.drug = drug;
	}
}