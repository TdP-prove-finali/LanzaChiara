package it.polito.tdp.corredino.model;

import java.util.List;
import java.util.Map;

public class Corredino implements Comparable<Corredino>{
	
private String corredino;
private double tot;

public Corredino(String corredino, double tot) {
	super();
	this.corredino = corredino;
	this.tot = tot;
}

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

@Override
public String toString() {
	return corredino + ", tot=" + tot + "\n\n";
}

@Override
public int compareTo(Corredino c) {
	// moltiplico per 100 poichè sono double e voglio un ordine preciso che si perderebe con il cast ad int 
	return (int) ((this.tot*100)-(c.tot*100));
}

	
	

}
