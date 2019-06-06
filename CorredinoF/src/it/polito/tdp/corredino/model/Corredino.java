package it.polito.tdp.corredino.model;

import java.util.List;
import java.util.Map;

public class Corredino {
	
	private Map<Product,Integer> corredino;
	private float tot;
	public Corredino(Map<Product,Integer> corredino, float tot) {
		super();
		this.corredino = corredino;
		this.tot = tot;
	}
	public Map<Product,Integer> getCorredino() {
		return corredino;
	}
	public void setCorredino(Map<Product,Integer> corredino) {
		this.corredino = corredino;
	}
	public float getTot() {
		return tot;
	}
	public void setTot(float tot) {
		this.tot = tot;
	}
	@Override
	public String toString() {
		return "Corredino [corredino=" + corredino.keySet() + ", tot=" + tot + "]";
	}
	
	

}
