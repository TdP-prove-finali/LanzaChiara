package it.polito.tdp.corredino.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corredino.db.ListinoDAO;

public class ListaProdotti {
	
	ListinoDAO dao = new ListinoDAO();
	
	
	
	List<Product> tutina = dao.getAll("tutina");
	List<Product> ghettina = dao.getAll("ghettina");
	List<Product> calzini = dao.getAll("calzini");
	List<Product> cuffia = dao.getAll("cuffia");
	List<Product> fasciaOmbellicale =dao.getAll("fascia ombellicale");;
	List<Product> lenzulinoCulla = dao.getAll("lenzuolino culla");
	List<Product> copertinaCulla = dao.getAll("copertina culla");
	List<Product> copriMCulla =dao.getAll("copri materasso culla");
	List<Product> cuscinoCulla = dao.getAll("cuscino culla");
	List<Product> materassoCulla = dao.getAll("materasso culla");
	List<Product> piumottoCulla = dao.getAll("piumotto culla");
	List<Product> bavetta = dao.getAll("bavetta");
	List<Product> body = dao.getAll("body");
	List<Product> vestitino = dao.getAll("vestitino ");
	List<Product> lenzuolinoLettino = dao.getAll("lenzuolino lettino");
	List<Product> copertinaLettino = dao.getAll("copertina lettino");
}

