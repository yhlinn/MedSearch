package model;


public class StockedItems {
	protected int stockId;
	protected DrugPrices drugPrice;
	protected Pharmacies pharmacy;
	protected boolean inStock;
	protected int quantity;
	
	// all-fields constructor
	public StockedItems(int stockId, DrugPrices drugPrice, Pharmacies pharmacy, boolean inStock, int quantity) {
		this.stockId = stockId;
		this.drugPrice = drugPrice;
		this.pharmacy = pharmacy;
		this.inStock = inStock;
		this.quantity = quantity;
	}

	// PK-only constructor
	public StockedItems(int stockId) {
		this.stockId = stockId;
	}

	// no-PK constructor
	public StockedItems(DrugPrices drugPrice, Pharmacies pharmacy, boolean inStock, int quantity) {
		this.drugPrice = drugPrice;
		this.pharmacy = pharmacy;
		this.inStock = inStock;
		this.quantity = quantity;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public DrugPrices getDrugPrice() {
		return drugPrice;
	}

	public void setDrugPrice(DrugPrices drugPrice) {
		this.drugPrice = drugPrice;
	}

	public Pharmacies getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacies pharmacy) {
		this.pharmacy = pharmacy;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}