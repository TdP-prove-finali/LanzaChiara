package it.polito.tdp.corredino.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.polito.tdp.corredino.db.ListinoDAO;

public class Model {
	
	private ListinoDAO dao;
	
	private List<Product> allP; //tutti i prodotti presenti nel database
	private List<Categories> categories= new ArrayList<Categories>(); //tutte le categorie presenti nel db
	//uso liste di interi perchè indicheranno la quantita del prodotto corrispondente nella lista all'indice in caso
	private List<Integer> parziale;
	//stessa cosa per le quantita delle categorie, la corrispondenza è diretta con la categoria della lista categories avente lo stesso indice
	private List<Integer> quantitaCat;
	private List<List<Integer>> combinazioni;
	private List<Integer> best;
	private double totBest;
	private double min;
	//lista di corredini
	private List<CorredinoSeller> ris;
	//corredino con massimo num di categorie
	private CorredinoSeller maxItC= new CorredinoSeller();
	//numero di categorie presenti
	private int maxCat;
	//num di prodotti per carredino
	private int item=0;
	//corredino con massimo num di prodotti 
	private CorredinoSeller maxIt=new CorredinoSeller();
	//num prodotti nel corredino che li massimizza 
	private int maxItem=0;
	
	//tolleranza sara' quanto posssono variare le proporzioni, 
	//aggiungo un set per dare modo di modificarlo esternamente 
	private double tolleranza;
	//margine sara' quanto tollero che vari il budget inserito NB:possibile modificarlo
	private double marg;
	private ComparatoreSeller compS=new ComparatoreSeller();
	private ComparatoreCliente compC = new ComparatoreCliente();
	


	public Model() {
		dao= new ListinoDAO();
		
		//prendo dal dao il costo minimo dei prodotti (mi serve per un controllo nella ricorsione, per evitare calcoli inutili)
		min=dao.getmin();
		
		//inizializzo tolleranza e margine qui 
		this.tolleranza=0.1;
		
		this.marg=0.05;
		
	}
	
	
	public void calcola(double budget, String season){
		categories=dao.getAllCat(season);
		combinazioni=new ArrayList<>();
		best=new ArrayList<>();
		totBest=Double.MAX_VALUE;
		quantitaCat=new ArrayList<>();
		double tot=0;
		parziale=new ArrayList<>();
		allP=new ArrayList<>();
		for(Categories c: categories) {
			//aggiungo in ordine di categoria tutti i prodotti, stesso ordine avranno le liste di categorie 
			allP.addAll(dao.getAllProductCat(season, c));
			//inizializzo a 0 le quantita per ogni categoria
			this.quantitaCat.add(0);
		}
		
		for(int i=0;i<allP.size();i++) {
			parziale.add(0);
		}
		this.prova(0, 0, tot, budget);
	}
	
	
	
	public void prova(int indexP, int indexC, double tot, double budget) {

		double margine= this.marg*budget;
		if(indexP>parziale.size()-1) {
			if(budget-tot<margine && this.isCompleta(parziale)) {
				List<Integer> add= new ArrayList<Integer>(parziale); 
				combinazioni.add(add);
				if(tot<totBest) {
					totBest=tot;
					best=new ArrayList<>(parziale);
				}
			}
			return;
		}
		
		//inizializzo indexCAtt perche se non lo setto esco con il return
		int indexCAtt=-1;
		//cerco quale categoria di prodotto sto testando, per inviarla come dato nella ricorsione 
		//(serve per il testing intermedio delle proporzioni)
		//NB: indexC e del prodotto precedente e serve per il test
		if(!(indexP>parziale.size()-1)) {
			indexCAtt= categories.indexOf(allP.get(indexP).getCategory());
		}
		
		//controllo ce lìindice della categoria sia diverso per poter fare un controllo delle categorie 
		//fin ora complete e bloccare in anticipo eventuali soluzioni inadatte
		//indexCAtt deve essere >=1 perche per poter fare il confronto servono almeno due categorie(0,1) 
		//NB:sono in ordine anche se e' inutile passare la lista parziale perhè tanto è stata dichiarata 
		//globalmente, indexCAtt è necessario
		if(indexCAtt!=indexC && indexCAtt>=1) {
			if(!this.controllaProp(parziale, indexCAtt))
				return;
		}

		//capisco quanti prodotti posso aggiungere in base al massimo della categoria, 
		//ma tengo conto anche di quelli gia inseriti per quella categoria
		int max=categories.get(indexCAtt).getMax()-this.quantitaCat.get(indexCAtt);
		
		//quanti ne posso aggiugere ancora senza sforare il budget?
		int max2= (int) ((int)(budget-tot)/allP.get(indexP).getPrice());

		if(max2<max)
			max=max2;
		
		//controllo se posso aggiungere ancora o è inutile 
		if(budget-tot<min)
			return;
		
		
		//vera e propria ricorsione
		for(int q=0;q<max;q++) {

			//aggiungo la quantita di prodotti alla lista di quantita dei prodotti
			this.parziale.set(indexP, q);
			//aggiorno tot
			tot += allP.get(indexP).getPrice()*q;
			//aggiorno la quantita della categoria in corso
			int att= this.quantitaCat.get(indexCAtt);
			this.quantitaCat.set(indexCAtt, att+q);
			if(indexP==35)
				System.out.println("Uffa");
			if(indexP<allP.size())
			this.prova(indexP+1, indexCAtt, tot, budget);
			
			//ritorno alle condizioni precedenti
			
			this.quantitaCat.set(indexCAtt, att);
			tot -= allP.get(indexP).getPrice()*q;	
		}
		
	}
	
	
	private boolean isCompleta(List<Integer> parziale) {
		
		int i=0;
		//catNum sara l'indice della categoria numeratore delle proporzioni
		int catNum=-1;
		//valNum invece il valore proporzione della categoria 
		double valNum=-1;
		for(Categories c: categories) {
			if(c.getMin()>0 && quantitaCat.get(categories.indexOf(c))!=0) {
				catNum=categories.indexOf(c);
				valNum=c.getProporzione();
				break;
			}
		}

		if(catNum==-1 || valNum==-1)
			return false;
		
		for(int index=0; index<quantitaCat.size(); index++) {
			if(index!=catNum) {
				if(categories.get(index).getMin()==0) {
					if(quantitaCat.get(index)==0 || categories.get(index).getMax()==1 || 
							((quantitaCat.get(catNum) / quantitaCat.get(index)) < ((1 + this.tolleranza) * valNum / categories.get(index).getProporzione()) 
						  && (quantitaCat.get(catNum) / quantitaCat.get(index)) > ((1 - this.tolleranza) * valNum / categories.get(index).getProporzione()))) {
						i++;
					}
				} else {
					double a=quantitaCat.get(index);
					double b=quantitaCat.get(catNum);
					double c=categories.get(index).getProporzione();
					if(a!=0 && ((b/a)<(1+this.tolleranza)*valNum/c && (b/a)>(1-this.tolleranza)*valNum/c)) {
					i++;
					}
				}
			}
		}
		
		if(i==categories.size() - 1) {
			return true;
		}

		return false;
	}



	private boolean controllaProp(List<Integer> parziale, int indexCAtt) {
		//il controllo lo faccio tra la categoria su indexCatt e la precedente, 
		//se il suo minimo è diverso da 0, altrimenti con una delle precedenti 
		
		for(int i =0;i<indexCAtt;i++) {
			for(int j=0;j<indexCAtt;j++) {
				if(i!=j) {
				//per facilitare debug ho salvato in delle variabli
				double qi_Min=categories.get(i).getMin();
				double qj_Min=categories.get(j).getMin();
				double qi_Att=quantitaCat.get(i);
				double qj_Att=quantitaCat.get(j);
				double prop_i=categories.get(i).getProporzione();
				double prop_j=categories.get(j).getProporzione();
				if(qi_Min!=0 && qj_Min!=0)
					if(qi_Att>0 && qj_Att>0)
						if(!(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) &&
								((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
							return false;
				if((qi_Min==0 || categories.get(i).getMax()==1)  && qj_Min!=0 && qj_Att!=0)
					if(qi_Att!=0 && !(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) && 
							((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
						return false;
				if((qj_Min==0 || categories.get(j).getMax()==1)  && qi_Min!=0 && qi_Att!=0)
					if(qj_Att!=0 && !(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) && 
							((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
						return false;
			}}
		}
		return true;
	}

	
	public void getAll() {
		
		CorredinoSeller b = new CorredinoSeller();
		maxCat=0;
		double tot=0;
		int att=0;
		double income=0;
		ris= new LinkedList<>();
		int indexCatVecchia=0;
		for(List<Integer> li : combinazioni) {
			//risfrutto la variabile che utilizzo in modalità seller anche se non hanno lo stesso scopo 
			item=0;
			tot=0;
			att=0;
			income=0;
			b = new CorredinoSeller();
			for(int i=0;i <li.size();i++) {
				if(li.get(i)!=0) {
					ProdottoCorredino temp= new ProdottoCorredino(allP.get(i).getName(),li.get(i),(double)allP.get(i).getPrice(),(double)allP.get(i).getSellerPrice());
					b.addP(temp);
					item +=li.get(i);
					income += (double)(allP.get(i).getPrice()-allP.get(i).getSellerPrice())*li.get(i);
					tot += temp.getQuantita()*temp.getCosto();
					if(i!=0)
					//contatore di categorie nel corredino
					if(!allP.get(i).getCategory().equals(allP.get(indexCatVecchia).getCategory())) {
						att++;
						indexCatVecchia=i;}
				}
			}
			//operazione necessaria, non capisco perchè mi usciva un valore con non arrotondato
			b.setTot((Math.round( tot * Math.pow( 10, 2 ) )/Math.pow( 10, 2 )));
			b.setTotProdotti(item);
			b.setIncomeTot((Math.round( income * Math.pow( 10, 2 ) )/Math.pow( 10, 2 )));
			ris.add(b);
			if (att>maxCat) {
				maxCat=att;
				maxItC = new CorredinoSeller(b.getP(),(double)b.getTot());}
		
			if(item>maxItem) {
				maxItem=item;
				maxIt= new CorredinoSeller(b.getP(),(double)b.getTot(),b.getIncomeTot());
			}
		
		}
		
		
	}
	
	public CorredinoSeller  getMaxC() {
		return maxItC;
	}
	public int categorie() {
		return this.maxCat;
	}
	
	public List<CorredinoSeller> returnAll(){
		Collections.sort(ris, compC);
		return ris;
	}
	
	public CorredinoSeller getBest(){
		CorredinoSeller b = new CorredinoSeller();
		Double tot=0.0;
		for(int i=0;i <best.size();i++) {
			if(best.get(i)!=0) {
				ProdottoCorredino temp= new ProdottoCorredino(allP.get(i).getName(),best.get(i),(double)allP.get(i).getPrice(),0.0);
				b.addP(temp);
				tot += allP.get(i).getPrice()*best.get(i);
		}}
		b.setTot((Math.round( tot * Math.pow( 10, 2 ) )/Math.pow( 10, 2 )));
		return b;
	}
	
	
	
	
	public List<CorredinoSeller> returnAllSeller() {
		Collections.sort(ris,compS);
		return ris;
	}
	
	public CorredinoSeller getMaxIncome() {
		Collections.sort(ris,compS);
		return ris.get(0);
	}
	
	public CorredinoSeller getMaxItem() {
		
		return maxIt;
	}
	
	public int maxItN() {
		return maxItem;
	}

	
	public void setMarg(double margine) {
		this.marg = margine;
	}

	public void setTolleranza(double intervallo) {
		this.tolleranza = intervallo;
	}


}
