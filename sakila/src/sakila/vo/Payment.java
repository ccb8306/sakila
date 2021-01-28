package sakila.vo;

public class Payment {
	private int paymentId;
	private int customerId;
	private int staffId;
	private int rental_id;
	private double amount;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getRental_id() {
		return rental_id;
	}
	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
