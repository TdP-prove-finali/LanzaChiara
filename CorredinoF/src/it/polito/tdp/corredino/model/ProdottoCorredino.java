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
	public ProdottoCorredino(int totProdotti,Double tot) {
		this.name="Totale n prod e costo:";
		//in quantita metto il totale dei prodotti acquistabili
		this.quantita=totProdotti;
		//in costo il totale del corredino
		this.costo=tot;
	}
	
	//questo per stampare una riga con totale e quanto quadagna il venditore dopo ogni combinazione
	public ProdottoCorredino(int totProdotti, Double tot, Double incomeTot) {
		this.name="Totale";
		this.costo=tot;
		this.quantita=totProdotti;
		this.sellerIncome=incomeTot;
	}


	

}
