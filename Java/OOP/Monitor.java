package oop.ex;

public class Monitor {
	
	private String name;
	private int price;
	private int inch;
	
	public Monitor() {
	}
	
	public Monitor(String name, int price, int inch) {
		super();
		this.name = name;
		this.price = price;
		this.inch = inch;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getInch() {
		return inch;
	}
	public void setInch(int inch) {
		this.inch = inch;
	}
	
	
}
