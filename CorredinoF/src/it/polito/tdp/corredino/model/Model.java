package it.polito.tdp.corredino.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corredino.db.ListinoDAO;

public class Model {
	
	ListinoDAO dao;
	
	List<Product> allP =new ArrayList<>();
	List<List<Product>> allRes = new ArrayList<>();
	List<Product> candidata = new ArrayList<>();
	List<Product> best= new ArrayList<>();
	float totBest=Float.MAX_VALUE;
	List<String> cat= new ArrayList<>();
	Map<String,Integer> controllore = new HashMap<>();
	Map<String, List<Product>> categories = new HashMap<String,List<Product>>();
	float tot =0;

	
	public Model() {
		dao = new ListinoDAO();
	}
	
//	public List<Product> tutine() {
//		dao.getAllProduct();
//		tutina = dao.getAll("tutina");
//		return tutina;
//		
//	}
	
	public List<Product> calcola(float budget) {
		allP=dao.getAllProduct();
		cat= dao.getAllCat();
		for (String s:cat) {
			categories.put(s, dao.getAll(s));
			controllore.put(s, 0);
		}
		
		prova(candidata,0, budget);
		
		return best;
		
	}
	

private void prova(List<Product> candidata, int L, float budget) {
		if(this.isCompleta(candidata) && tot<budget) {
			allRes.add(candidata);
			if(tot<totBest && budget-tot<50) {
				best= new ArrayList<>(candidata);
				totBest=tot;
			}
	}else if(!(L==dao.totProdotti())) {
		for(Product p: allP) {
			if(!candidata.contains(p))
			if(aggiuntaValida(p,candidata, budget)) {
				candidata.add(p);
				prova(candidata, L+1, budget);
				candidata.remove(candidata.size()-1);
			} 
			
			//come faccio a bloccare se ho raggiunto il budget ma non è completa?
		}
	}
	
}

private boolean aggiuntaValida(Product p, List<Product> candidata2, float budget) {
	for (String c: cat) {
		int i=0;
		if(!candidata2.isEmpty())
		for(Product pt: candidata2) {
			if(c.equals(pt.getCategory())) {
				i++;
			}	
		}
		controllore.replace(c, i);
	}
	
	//la quantità di body, ghettine, calzini e tutine deve essere la stessa, massimo 5 minimo 2
	if(p.getCategory()==("Body"))
	if (controllore.get("Body")>5) {
		return false;
	}
	if(p.getCategory()==("Ghettina"))
	if(controllore.get("Ghettina")>5){
		return false;
	}
	if(p.getCategory()==("Calzini"))
	if(controllore.get("Calzini")>5) {
		return false;
	}
	if(p.getCategory()==("Tutina"))
		if(controllore.get("Tutina")>5) {
			return false;
		}
	
	
	//bastano due lenzuolini per culla
	if(p.getCategory()=="Lenzuolino culla") //&& controllore.containsKey("Lenzuolino culla"))
			if(controllore.get("Lenzuolino culla")>2)
		return false;
	
	//bastano due copertine per culla
	if(p.getCategory()=="Copertina culla")// && controllore.containsKey("Copertina culla"))
			if(controllore.get("Copertina culla")>2)
		return false;
	
	if(p.getCategory()=="Copri materasso")// && controllore.containsKey("Copri materasso"))
			if(controllore.get("Copri materasso")>2)
		return false;
	
	if(p.getCategory()=="Cuscino culla")// &&  controllore.containsKey("Cuscino culla"))
			if(controllore.get("Cuscino culla")>1)
		return false;
	
	if(p.getCategory()=="Materasso culla")// && controllore.containsKey("Materasso culla"))
			if(controllore.get("Materasso culla")>1)
		return false;
	
	if(p.getCategory()=="Piumotto culla")// && controllore.containsKey("Piumotto culla"))
			if(controllore.get("Piumotto culla")>2)
		return false;
	
	if(p.getCategory()=="Cuscino culla")// && controllore.containsKey("Cuscino culla"))
			if(controllore.get("Cuscino culla")>1)
		return false;
	
	if(p.getCategory()=="Bavetta")// && controllore.containsKey("Bavetta"))
			if(controllore.get("Bavetta")>10)
		return false;
	
	if(p.getCategory()=="Cuffia")// && controllore.containsKey("Cuffia"))
			if(controllore.get("Cuffia")>0)
		return false;
	
	if(p.getCategory()=="Vestitino ")// && controllore.containsKey("Vestitino "))
			if(controllore.get("Vestitino ")>3)
		return false;
	
	if(p.getCategory()=="Lenzuolino lettino")// && controllore.containsKey("Lenzuolino lettino"))
			if(controllore.get("Lenzuolino lettino")>2)
		return false;
	
	//bastano due copertine per lettino
	if(p.getCategory()=="Copertina lettino")// && controllore.containsKey("Copertina lettino"))
			if(controllore.get("Copertina lettino")>2)
		return false;
	
	if(p.getCategory()=="Fascia ombellicale")// && controllore.containsKey("Fascia ombellicale"))
			if(controllore.get("Fascia ombellicale")>1)
		return false;
	
	
	tot=p.getPrice();
	if(!candidata2.isEmpty()) {
	for (Product pt: candidata2)
		tot +=pt.getPrice();
	}
	if(tot>budget)
		return false;
	
	return true;
}

private boolean isCompleta(List<Product> candidata2) {
	
	if(controllore.get("Materasso culla")==1 )
		if(controllore.get("Cuscino culla")!=1 ||controllore.get("Copri materasso culla")<1)
			return false;
	if(controllore.get("Fascia ombellicale")<1 || controllore.get("Lenzuolino culla")<1|| controllore.get("Copertina culla")<1)
		return false;
	
	if(!(controllore.get("Body")==controllore.get("Tutina") && controllore.get("Body")==controllore.get("Ghettina") && controllore.get("Body")==controllore.get("Calzini") && controllore.get("Calzini")>2))
		return false;
		
	return true;
}

public String getBest(){
	if(!best.isEmpty())
		return best.toString()+"\n"+ totBest;
	return null;
}

}
