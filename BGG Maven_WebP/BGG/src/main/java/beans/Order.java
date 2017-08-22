package beans;

import java.time.LocalDate;

public class Order {
	private int orderNumber;
	private LocalDate orderDate;
	private String userName;
	private String productName;
	private int quantity;
	
	public Order(int orderNumber, LocalDate orderDate, String userName,
			String productName, int quantity) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.userName = userName;
		this.productName = productName;
		this.quantity = quantity;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
