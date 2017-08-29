package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
		
		private static final long serialVersionUID = 298836286386798387L;
		
		private int orderNumber;
		private LocalDate orderDate;
		private String userName;
		private String productName;
		private int quantity;
		private int order_id;
	 
	 public Order(){
		 
	 };
	
	public Order(int orderNumber, LocalDate orderDate, String userName,
			String productName, int quantity, int order_id) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.userName = userName;
		this.productName = productName;
		this.quantity = quantity;
		this.order_id = order_id;
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

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	
	
}
