package it.polito.tdp.corredino.model;

public class Product {
	
	private String id;
	private String name;
	private Categories category;
	private double price;
	private String season;
	private double sellerPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public double getSellerPrice() {
		return sellerPrice;
	}
	public void setSellerPrice(double sellerPrice) {
		this.sellerPrice = sellerPrice;
	}
	public Product(String id, String name, Categories category, double price, String season, double sellerPrice) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.season = season;
		this.sellerPrice = sellerPrice;
	}
	public Product(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.category = p.getCategory();
		this.price = p.getPrice();
		this.season = p.getSeason();
		this.sellerPrice = p.getSellerPrice();
	}
	
	
	public Product(String id, String name, double d, String season, double e) {
		this.id = id;
		this.name = name;
		this.price = d;
		this.season = season;
		this.sellerPrice = e;
	}
	@Override
	public String toString() {
		return "Product name=" + name + ", category=" + category + ", price=" + price + ", sellerPrice=" + sellerPrice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	
	
	

}
