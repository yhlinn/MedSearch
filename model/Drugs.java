package model;


public class Drugs {
	protected String drugId;
	protected String drugName;
	protected String description;
	
	// all-fields constructor
	public Drugs(String drugId, String drugName, String description) {
		this.drugId = drugId;
		this.drugName = drugName;
		this.description = description;
	}
	
	// PK-only constructor
	public Drugs(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}