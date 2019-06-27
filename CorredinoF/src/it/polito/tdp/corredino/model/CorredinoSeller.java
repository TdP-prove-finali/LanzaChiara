package it.polito.tdp.corredino.model;

import java.util.LinkedList;
import java.util.List;

public class CorredinoSeller implements Comparable<CorredinoSeller>{
	
	private List<ProdottoCorredino> p;
	private double tot;
	private double incomeTot;

	
	





	public CorredinoSeller(List<ProdottoCorredino> p, double tot, double incomeTot) {
		super();
		this.p = p;
		this.tot = tot;
		this.incomeTot = incomeTot;
	}








	public double getIncomeTot() {
		return incomeTot;
	}








	public void setIncomeTot(double incomeTot) {
		this.incomeTot = incomeTot;
	}








	public CorredinoSeller(double incomeTot) {
		super();
		this.incomeTot = incomeTot;
	}








	public CorredinoSeller(List<ProdottoCorredino> p, double tot) {
		super();
		this.p = p;
		this.tot = tot;
	}








	public CorredinoSeller() {
		this.p = new LinkedList<ProdottoCorredino>();
		this.tot = 0;
	}








	public List<ProdottoCorredino> getP() {
		return p;
	}








	public void addP(ProdottoCorredino p) {
		this.p.add(p);
	}








	public double getTot() {
		return tot;
	}








	public void setTot(Double tot) {
		this.tot =tot;
	}








	@Override
	public int compareTo(CorredinoSeller c) {
		double tot1=0.0;
		double tot2=0.0;
		for(ProdottoCorredino pt: p)
			tot1+=pt.getSellerIncome();
		for(ProdottoCorredino ct: c.getP())
			tot2+=ct.getSellerIncome();
		if(tot1==0 & tot2==0) {
			return (int) ((this.tot*100)-(c.tot*100));
		}
		return (int) ((tot1*100)-(tot2*100));
	}




	
	
	

}
