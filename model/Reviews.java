package model;


public class Reviews {
	protected int reviewId;
	protected Customers customer;
	protected String content;
	protected Drugs drug;
	
	// all-fields constructor
	public Reviews(int reviewId, Customers customer, String content, Drugs drug) {
		this.reviewId = reviewId;
		this.customer = customer;
		this.content = content;
		this.drug = drug;
	}

	// PK-only constructor
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	// no-PK constructor
	public Reviews(Customers customer, String content, Drugs drug) {
		this.customer = customer;
		this.content = content;
		this.drug = drug;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Drugs getDrug() {
		return drug;
	}

	public void setDrug(Drugs drug) {
		this.drug = drug;
	}
}