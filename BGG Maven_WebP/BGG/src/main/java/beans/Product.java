package beans;

import java.io.Serializable;

//Geri, 08.21 : Java bean for store products.

public class Product implements Serializable {
	

	private static final long serialVersionUID = -7932463630518208353L;
	
	private String name;
	private double price;
	private int quantity;
	
	public Product(){
		
	};
	
	public Product(String name, double price, int quantity ) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
		if (getQuantity() != product.getQuantity()) return false;
		return getName().equals(product.getName());
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		long temp = Double.doubleToLongBits(getPrice());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getQuantity();
		return result;
	}
}

