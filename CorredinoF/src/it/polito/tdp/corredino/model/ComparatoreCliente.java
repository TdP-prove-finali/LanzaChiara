package it.polito.tdp.corredino.model;

import java.util.Comparator;

public class ComparatoreCliente implements Comparator<CorredinoSeller>{

	public int compare(CorredinoSeller cs1, CorredinoSeller cs2) {
			
			return (int) (cs1.getTot()*100-cs2.getTot()*100);
		}

}
