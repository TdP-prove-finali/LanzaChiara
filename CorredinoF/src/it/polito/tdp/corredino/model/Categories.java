package it.polito.tdp.corredino.model;

public class Categories {
	
	private String categoria;
	private int min;
	private int max;
	private double proporzione;
	public Categories(String categoria, int min, int max, double proporzione) {
		super();
		this.categoria = categoria;
		this.min = min;
		this.max = max;
		this.proporzione = proporzione;
	}
	public Categories(Categories c) {
		this.categoria = c.categoria;
		this.min = c.min;
		this.max = c.max;
		this.proporzione = c.proporzione;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public double getProporzione() {
		return proporzione;
	}
	public void setProporzione(double proporzione) {
		this.proporzione = proporzione;
	}
	@Override
	public String toString() {
		return "Categories [categoria=" + categoria + ", min=" + min + ", max=" + max + ", proporzione=" + proporzione
				+ "]";
	}
	
	
	

}
