package model;


public class OnOrder {
	protected int orderId;
	protected DrugPrices drugPrices;
	protected Pharmacies pharmacy;
	protected int quantity;
	
	// all-fields constructor
	public OnOrder(int orderId, DrugPrices drugPrices, Pharmacies pharmacy, int quantity) {
		this.orderId = orderId;
		this.drugPrices = drugPrices;
		this.pharmacy = pharmacy;
		this.quantity = quantity;
	}

	// PK-only constructor
	public OnOrder(int orderId) {
		this.orderId = orderId;
	}

	// no-PK constructor
	public OnOrder(DrugPrices drugPrices, Pharmacies pharmacy, int quantity) {
		this.drugPrices = drugPrices;
		this.pharmacy = pharmacy;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public DrugPrices getDrugPrices() {
		return drugPrices;
	}

	public void setDrugPrices(DrugPrices drugPrices) {
		this.drugPrices = drugPrices;
	}

	public Pharmacies getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacies pharmacy) {
		this.pharmacy = pharmacy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}