package it.polito.tdp.corredino.model;

public class Product {
	
	private String id;
	private String name;
	private Categories category;
	private float price;
	private String season;
	private float sellerPrice;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public float getSellerPrice() {
		return sellerPrice;
	}
	public void setSellerPrice(float sellerPrice) {
		this.sellerPrice = sellerPrice;
	}
	public Product(String id, String name, Categories category, float price, String season, float sellerPrice) {
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
	
	
	public Product(String id, String name, float price, String season, float sellerPrice) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.season = season;
		this.sellerPrice = sellerPrice;
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
