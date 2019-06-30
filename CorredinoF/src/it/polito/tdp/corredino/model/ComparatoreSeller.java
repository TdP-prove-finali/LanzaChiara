package it.polito.tdp.corredino.model;

import java.util.Comparator;

public class ComparatoreSeller implements Comparator<CorredinoSeller>{

	@Override
	public int compare(CorredinoSeller cs1, CorredinoSeller cs2) {
		
		return (int) (cs2.getIncomeTot()*100-cs1.getIncomeTot()*100);
	}

}



