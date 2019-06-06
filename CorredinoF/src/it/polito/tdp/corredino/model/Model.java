package it.polito.tdp.corredino.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corredino.db.ListinoDAO;

public class Model {
	
	ListinoDAO dao;

	
	List<Product> allP;
	//List<Product> allPr;
	List<Categories> categories= new ArrayList<Categories>();
	List<Product> best;
	float totBest;
	float tot;
	Map<Product,Integer> candidata;
	List<Product> listaC;
	Map<Categories,Integer> contatore;
	List<Corredino> allRes;
	List<ProdottoQuantita> quantiProdotti;
	Map<String,Product> idMapProd;

	
	
	public Model() {
		dao = new ListinoDAO();
		categories=dao.getAllCat();
	}
	
	public List<Product> calcola(float budget, String season) {
		
		allRes=new ArrayList<>();
		best= new ArrayList<>();
		totBest=Float.MAX_VALUE;
		tot=0;
		candidata=new HashMap<Product,Integer>();
		listaC =new ArrayList<>();
		contatore=new HashMap<>();
		quantiProdotti= new LinkedList<>();
		idMapProd=new HashMap<>();
		
		
		allP =new ArrayList<>();
	//	allPr =new ArrayList<>();
		for(Categories c: categories) {
			allP.addAll(dao.getAllProductCat(season, c));
			contatore.put(c, 0);
		}
		
		for(Product p: allP)
			for(int i=p.getCategory().getMin();i<p.getCategory().getMax();i++) {
				quantiProdotti.add(new ProdottoQuantita(p,i));
				idMapProd.put(p.getId(), new Product(p));
			}
		
		this.prova(candidata,budget,0,tot);
		System.out.println(allRes.toString());
		return best;
	}

	private void prova(Map<Product, Integer> candidata, float budget, int L, float tot) {

		//allPr= new ArrayList<>(allP);
		//allPr.remove(listaC);
		
		
//		if(L==allP.size())
//			return;
		float margine = (float)(0.1*budget);
		if(budget-tot<margine && isCompleta(candidata)) {
			Map<Product,Integer> candidata2= new HashMap<Product,Integer>(candidata);
			allRes.add(new Corredino(candidata2,tot));
			if(tot<totBest) {
				totBest=tot;
				best=new ArrayList<>(listaC);
			}
			return;
		}
		
		
			while (L<quantiProdotti.size()) {
				ProdottoQuantita pq = quantiProdotti.get(L);
				Product pt = new Product(pq.getP());
				if(!listaC.contains(pt)) {
			if(contatore.get(pq.getP().getCategory())<pq.getP().getCategory().getMax()) {
				
				float prova = (tot+pq.getQ()*pq.getP().getPrice());
				if(pq.getQ()+contatore.get(pq.getP().getCategory())<pq.getP().getCategory().getMax() && prova<budget) {
					candidata.put(pt, pq.getQ());
					listaC.add(pt);
					int i = contatore.get(pq.getP().getCategory());
					contatore.remove(pt.getCategory());
					contatore.put(pt.getCategory(),i+pq.getQ() );
					//System.out.println(candidata.toString());
					tot += pt.getPrice()*pq.getQ();
				prova(candidata,budget,L+1,tot);
				//System.out.println(candidata.toString());
				
				tot -= pt.getPrice()*pq.getQ();
				candidata.remove(pt);
				L=quantiProdotti.indexOf(pq)+1;
				listaC.remove(pt);
					}else L++;
				}}}
			
			
		}

	private List<ProdottoQuantita> ProdottoQuantita(List<ProdottoQuantita> quantiProdotti2) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isCompleta(Map<Product, Integer> candidata2) {
		//if(contatore.size()==categories.size()) {
		int i=0;
		for(Categories c: categories)
			for(Categories c2: this.categories) {
				if(contatore.get(c)>0&& contatore.get(c2)>0 && !c.equals(c2))
				if(contatore.get(c).intValue()/contatore.get(c2).intValue()<1.4*(c.getProporzione()/c2.getProporzione())&& contatore.get(c).intValue()/contatore.get(c2).intValue()>0.6*(c.getProporzione()/c2.getProporzione()) )
					i++;}
				if(i==contatore.size())
					return true;
			
		return false;
	}

	//controllo le proporzioni
	private boolean controlla(Map<Categories, Integer> contatore) {
		for(Categories c: categories)
			for(Categories c2: this.categories) {
				if(contatore.containsKey(c)&&contatore.containsKey(c2) && !c.equals(c2) && contatore.get(c)>0 && contatore.get(c2)>0)
				if(!(contatore.get(c).intValue()/contatore.get(c2).intValue()<1.4*(c.getProporzione()/c2.getProporzione())&& contatore.get(c).intValue()/contatore.get(c2).intValue()>0.6*(c.getProporzione()/c2.getProporzione()) ))
					return false;
			}
			
		return true;
	}
	
}


		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	List<List<Product>> allRes = new ArrayList<>();
//	List<Product> candidata;
//	List<Product> best= new ArrayList<>();
//	float totBest=Float.MAX_VALUE;
//	List<String> cat= new ArrayList<>();
//	Map<String,Integer> controllore = new HashMap<>();
//	//Map<String, List<Product>> categories = new HashMap<String,List<Product>>();
//	float tot =0;
//	List<Product> rimanenti; //new ArrayList<>();
//	//List<Product> scartati = new ArrayList<>();

	
//	public List<Product> tutine() {
//		dao.getAllProduct();
//		tutina = dao.getAll("tutina");
//		return tutina;
//		
//	}
	
//	public List<Product> calcola(float budget, String season) {
//		candidata = new ArrayList<>();
//		if (season.equals("Autunno/Primavera"))
//				season = "Privavera";
//		allP=dao.getAllProduct(season);
//		cat= dao.getAllCat();
//		for (String s:cat) {
//		//	categories.put(s, dao.getAll(s));
//			controllore.put(s, 0);
//		}
//		
//		prova(candidata,0, budget);
//		
//		return best;
//		
//	}
//	
//
//private void prova(List<Product> candidata, int L, float budget) {
//		
//		if(this.isCompleta(candidata) && tot<budget && budget-tot<50 && !candidata.isEmpty()) {
//			allRes.add(candidata); //clone
//			System.out.println(candidata.toString());
//			if(tot<totBest) {// && budget-tot<50) {
//				System.out.println(candidata.toString());
//				best= new ArrayList<>(candidata);
//				totBest=tot;
//			}
//	} else if(!(L==dao.totProdotti())) {
////		rimanenti = new ArrayList<>(allP);
////		rimanenti.removeAll(candidata); 
////		//rimanenti.removeAll(scartati);
//		for(Product p: rimanenti) {
//			if(aggiuntaValida(p,candidata, budget)) {
//				candidata.add(p);
//				prova(candidata, L+1, budget);
//				candidata.remove(candidata.size()-1);
//			}//  else scartati.add(p);
//			
//			//come faccio a bloccare se ho raggiunto il budget ma non è completa?
//		}
//	}
//	
//}
//
//private boolean aggiuntaValida(Product p, List<Product> candidata2, float budget) {
//	for (String c: cat) {
//		int i=0;
//		if(candidata2.size()!=0)
//		for(Product pt: candidata2) {
//			if(c.equals(pt.getCategory())) {
//				i++;
//			}	
//		}
//		controllore.remove(c);
//		controllore.put(c, i);
//	}
//	
//	//la quantità di body, ghettine, calzini e tutine deve essere la stessa, massimo 5 minimo 2
//	if(p.getCategory().equals(("Body")))
//	if (controllore.get("Body")>5) {
//		return false;
//	}
//	if(p.getCategory().equals(("Ghettina")))
//	if(controllore.get("Ghettina")>5){
//		return false;
//	}
//	if(p.getCategory().equals(("Calzini")))
//	if(controllore.get("Calzini")>5) {
//		return false;
//	}
//	if(p.getCategory().equals(("Tutina")))
//		if(controllore.get("Tutina")>5) {
//			return false;
//		}
//	
//	
//	//bastano due lenzuolini per culla
//	if(p.getCategory().equals("Lenzuolino culla")) //&& controllore.containsKey("Lenzuolino culla"))
//			if(controllore.get("Lenzuolino culla")>2)
//		return false;
//	
//	//bastano due copertine per culla
//	if(p.getCategory().equals("Copertina culla"))// && controllore.containsKey("Copertina culla"))
//			if(controllore.get("Copertina culla")>2)
//		return false;
//	
//	if(p.getCategory().equals("Copri materasso"))// && controllore.containsKey("Copri materasso"))
//			if(controllore.get("Copri materasso")>1)
//		return false;
//	
//	if(p.getCategory().equals("Cuscino culla"))// &&  controllore.containsKey("Cuscino culla"))
//			if(controllore.get("Cuscino culla")>1)
//		return false;
//	
//	if(p.getCategory().equals("Materasso culla"))// && controllore.containsKey("Materasso culla"))
//			if(controllore.get("Materasso culla")>1)
//		return false;
//	
//	if(p.getCategory().equals("Piumotto culla"))// && controllore.containsKey("Piumotto culla"))
//			if(controllore.get("Piumotto culla")>2)
//		return false;
//	
//	if(p.getCategory().equals("Cuscino culla"))// && controllore.containsKey("Cuscino culla"))
//			if(controllore.get("Cuscino culla")>0)
//		return false;
//	
//	if(p.getCategory().equals("Bavetta"))// && controllore.containsKey("Bavetta"))
//			if(controllore.get("Bavetta")>10)
//		return false;
//	
//	if(p.getCategory().equals("Cuffia"))// && controllore.containsKey("Cuffia"))
//			if(controllore.get("Cuffia")>0)
//		return false;
//	
//	if(p.getCategory().equals("Vestitino "))// && controllore.containsKey("Vestitino "))
//			if(controllore.get("Vestitino ")>3)
//		return false;
//	
//	if(p.getCategory().equals("Lenzuolino lettino"))// && controllore.containsKey("Lenzuolino lettino"))
//			if(controllore.get("Lenzuolino lettino")>2)
//		return false;
//	
//	//bastano due copertine per lettino
//	if(p.getCategory().equals("Copertina lettino"))// && controllore.containsKey("Copertina lettino"))
//			if(controllore.get("Copertina lettino")>2)
//		return false;
//	
//	if(p.getCategory().equals("Fascia ombellicale"))// && controllore.containsKey("Fascia ombellicale"))
//			if(controllore.get("Fascia ombellicale")>1)
//		return false;
//	
//	
//	tot=p.getPrice();
//	if(!candidata2.isEmpty()) {
//	for (Product pt: candidata2)
//		tot +=pt.getPrice();
//	}
//	if(tot>budget)
//		return false;
//	
//	return true;
//}
//
//private boolean isCompleta(List<Product> candidata2) {
//	
//	if(controllore.get("Materasso culla")==1 )
//		if(controllore.get("Cuscino culla")!=1 ||controllore.get("Copri materasso culla")<1)
//			return false;
//	if(controllore.get("Fascia ombellicale")<1 || controllore.get("Lenzuolino culla")<1|| controllore.get("Copertina culla")<1)
//		return false;
//	
//	if(!(controllore.get("Body")==controllore.get("Tutina") &&controllore.get("Body")>1)  || controllore.get("Ghettina")<2)
//		return false;
//
//		
//	return true;
//}
//
//public String getBest(){
//	if(!best.isEmpty())
//		return best.toString()+"\n"+ totBest;
//	return null;
//}
//
//
//
//}

