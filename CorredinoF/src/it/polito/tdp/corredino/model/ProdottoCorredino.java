package it.polito.tdp.corredino.model;

public class ProdottoCorredino {

	private String name;
	private int quantita;
	private Double costo;
	private Double sellerIncome;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Double getSellerIncome() {
		return sellerIncome.doubleValue();
	}
	public void setSellerIncome(Double sellerIncome) {
		this.sellerIncome = sellerIncome;
	}
	public ProdottoCorredino(String name, int quantita, Double costo, Double sellerIncome) {
		super();
		this.name = name;
		this.quantita = quantita;
		this.costo = costo;
		this.sellerIncome = sellerIncome;
	}
	
	
	//mi serve solo per stampare una riga con il totale del corredino
	public ProdottoCorredino(double tot) {
		this.name="Costo totale:";
		this.costo=tot;
	}
	
	//questo per stampare una riga con totale e quanto quadagna il venditore dopo ogin combinazione
	public ProdottoCorredino(double tot, double incomeTot) {
		this.name="Tot (NB Seller price= quanto quadagni";
		this.quantita=(int) ((tot/incomeTot)*100);
		this.costo=tot;
		
		this.sellerIncome=incomeTot;
	}


	

}
