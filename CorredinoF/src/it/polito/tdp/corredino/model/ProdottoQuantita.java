package it.polito.tdp.corredino.model;

public class ProdottoQuantita {

	private Product p;
	private int q;
	
	public ProdottoQuantita(Product p, int q) {
		super();
		this.p = p;
		this.q = q;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}
	
}
