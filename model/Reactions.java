package model;


public class Reactions {
	protected int reactionId;
	protected Drugs drugA;
	protected Drugs drugB;
	protected String drugName;
	protected String description;
	
	// all-fields constructor
	public Reactions(int reactionId, Drugs drugA, Drugs drugB, String drugName, String description) {
		this.reactionId = reactionId;
		this.drugA = drugA;
		this.drugB = drugB;
		this.drugName = drugName;
		this.description = description;
	}
	
	// PK-only constructor
	public Reactions(int reactionId) {
		this.reactionId = reactionId;
	}

	// no-PK constructor
	public Reactions(Drugs drugA, Drugs drugB, String drugName, String description) {
		super();
		this.drugA = drugA;
		this.drugB = drugB;
		this.drugName = drugName;
		this.description = description;
	}
	
	public int getReactionId() {
		return reactionId;
	}

	public void setReactionId(int reactionId) {
		this.reactionId = reactionId;
	}

	public Drugs getDrugA() {
		return drugA;
	}

	public void setDrugA(Drugs drugA) {
		this.drugA = drugA;
	}

	public Drugs getDrugB() {
		return drugB;
	}

	public void setDrugB(Drugs drugB) {
		this.drugB = drugB;
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