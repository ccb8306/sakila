package sakila.vo;

public class Rental {
	private int rentalId;
	private String rentalDate;
	private int inventoryId;
	private int cusotomerId;
	private String returnDate;
	private int staffId;
	private String customerName;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getCusotomerId() {
		return cusotomerId;
	}
	public void setCusotomerId(int cusotomerId) {
		this.cusotomerId = cusotomerId;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	// 반납 예정일 계산용
	private String returnDueDate;
	public String getReturnDueDate() {
		return returnDueDate;
	}
	public void setReturnDueDate(String returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
}
