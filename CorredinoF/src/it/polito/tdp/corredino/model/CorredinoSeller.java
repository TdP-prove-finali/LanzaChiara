package it.polito.tdp.corredino.model;

public class CorredinoSeller implements Comparable<CorredinoSeller>{
	
	private String corredino;
	private double tot;
	private double sellerIncome;
	
	

	public String getCorredino() {
		return corredino;
	}



	public void setCorredino(String corredino) {
		this.corredino = corredino;
	}



	public double getTot() {
		return tot;
	}



	public void setTot(double tot) {
		this.tot = tot;
	}



	public double getSellerIncome() {
		return sellerIncome;
	}



	public void setSellerIncome(double sellerIncome) {
		this.sellerIncome = sellerIncome;
	}



	public CorredinoSeller(String corredino, double tot, double sellerIncome) {
		super();
		this.corredino = corredino;
		this.tot = tot;
		this.sellerIncome = sellerIncome;
	}



	public CorredinoSeller() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public int compareTo(CorredinoSeller c) {
		// moltiplico per 100 poichè sono double e voglio un ordine preciso che si perderebe con il cast ad int 
		return (int) ((this.sellerIncome)*100-(c.sellerIncome)*100);
	}



	@Override
	public String toString() {
		return  corredino + ", il cliente spende: " + (Math.round(tot * 100.0) / 100.0) + ", tu guadagni: " + (Math.round(sellerIncome * 100.0) / 100.0) + "\n";
	}
	
	

}
