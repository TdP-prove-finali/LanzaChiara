package it.polito.tdp.corredino.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import it.polito.tdp.corredino.db.ListinoDAO;

public class Modeld {
	
	private ListinoDAO dao;
	
	private List<Product> allP;
	private List<Categories> categories= new ArrayList<Categories>();
	//uso liste di interi perchè indicheranno la quantita del prodotto corrispondente nella lista all'indice in caso
	private List<Integer> parziale;
	//stessa cosa per le quantita delle categorie, la corrispondenza è diretta con la categoria della lista categories avente lo stesso indice
	private List<Integer> quantitaCat;
	private List<List<Integer>> combinazioni;
	private List<Integer> best;
	private double totBest;
	
	private double tolleranza;
	
	private double marg;
	int flag=0;
	int f=0;
	
	//margine sara' quanto tollero che vari il budget inserito
	public void setMarg(double margine) {
		this.marg = margine;
	}

	//tolleranza sara' quanto posssono variare le proporzioni, aggiungo un set per dare modo di modificarlo esternamente
	public void setTolleranza(double intervallo) {
		this.tolleranza = intervallo;
	}

	public Modeld() {
		dao= new ListinoDAO();
		
		//inizializzo tolleranza e margine qui 
		this.tolleranza=0.5;
		
		this.marg=0.15;
		
	}
	
	public List<Integer> calcola(double budget, String season){
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
		
//		final int TEST_TOTAL = 33;
//		int[] test = {2,0,0,2,0,4,0,0,0,2,0,4,0,0,2,0,4,0,0,4,0,0,2,0,4,0,2,0,6,0,0,6,0,6,0,8,0,0,0};
//		parziale= Arrays.stream(test).boxed().collect(Collectors.toList());
//		int[] test2 = {2,2,4,2,4,2,4,4,2,4,2,6,6,6,8};
//		this.quantitaCat=  Arrays.stream(test2).boxed().collect(Collectors.toList());
//		isCompleta(parziale);
		
		return best;
	}
	
	public void prova(int indexP, int indexC, double tot, double budget) {
		
		//inizializzo indexCAtt perche se non lo setto esco con il return
		int indexCAtt=-1;
		//cerco quale categoria di prodotto sto testando, per inviarla come dato nella ricorsione (serve per il testing intermedio delle proporzioni)
		//NB: indexC e del prodotto precedente e serve per il test
		if(!(indexP>parziale.size()-1)) {
			indexCAtt= categories.indexOf(allP.get(indexP).getCategory());
		}


		double margine= this.marg*budget;
		if(indexP>parziale.size()-1) {
			if(budget-tot<margine && this.isCompleta(parziale)) {
				//E' NECESSARIO CREARE UNA NUOVA LISTA??
				List<Integer> add= new ArrayList<Integer>(parziale); 
				combinazioni.add(add);
				if(tot<totBest) {
					totBest=tot;
					best=new ArrayList<>(parziale);
				}
			}
			return;
		}
		
		
		//controllo ce lìindice della categoria sia diverso per poter fare un controllo delle categorie fin ora complete e bloccare in anticipo eventuali soluzioni inadatte
				//indexCAtt deve essere >=1 perche per poter fare il confronto servono almeno due categorie(0,1) NB:sono in ordine
				//anche se e' inutile passare la lista parziale perhè tanto è stata dichiarata globalmente, indexCAtt è necessario
				if(indexCAtt!=indexC && indexCAtt>=1) {
					if(!this.controllaProp(parziale, indexCAtt))
						return;
				}

		//capisco quanti prodotti posso aggiungere in base al massimo della categoria, ma tengo conto anche di quelli gia inseriti per quella categoria
		int max=categories.get(indexCAtt).getMax()-this.quantitaCat.get(indexCAtt);
		
		//quanti ne posso aggiugere ancora senza sforare il budget?
		int max2= (int) ((int)(budget-tot)/allP.get(indexP).getPrice());

		if(max2<max)
			max=max2;
		
		
		for(int q=0;q<max;q++) {

			//aggiungo la quantita di prodotti alla lista di quantita dei prodotti
			this.parziale.set(indexP, q);
			//System.out.println(parziale.toString());
			//aggiorno tot
			tot += allP.get(indexP).getPrice()*q;
			//aggiorno la quantita della categoria in corso
			int att= this.quantitaCat.get(indexCAtt);
			this.quantitaCat.set(indexCAtt, att+q);
			if(indexP==35)
				System.out.println("Uffa");
			if(indexP<allP.size())
			this.prova(indexP+1, indexCAtt, tot, budget);
			
			//System.out.println(parziale.toString());
			
			//ritorno alle condizioni precedenti
			
			this.quantitaCat.set(indexCAtt, att);
			tot -= allP.get(indexP).getPrice()*q;
			
			
		}
		
	}


	private boolean controllaProp(List<Integer> parziale, int indexCAtt) {
		//il controllo lo faccio tra la categoria su indexCatt e la precedente, se il suo minimo è diverso da 0, altrimenti con una delle precedenti 
		
		for(int i =0;i<indexCAtt;i++) {
			for(int j=0;j<indexCAtt;j++) {
				if(i!=j) {
				double qi_Min=categories.get(i).getMin();
				double qj_Min=categories.get(j).getMin();
				double qi_Att=quantitaCat.get(i);
				double qj_Att=quantitaCat.get(j);
				double prop_i=categories.get(i).getProporzione();
				double prop_j=categories.get(j).getProporzione();
				if(qi_Min!=0 && qj_Min!=0)
					if(qi_Att>0 && qj_Att>0)
						if(!(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) && ((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
							return false;
				if((qi_Min==0 || categories.get(i).getMax()==1)  && qj_Min!=0 && qj_Att!=0)
					if(qi_Att!=0 && !(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) && ((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
						return false;
				if((qj_Min==0 || categories.get(j).getMax()==1)  && qi_Min!=0 && qi_Att!=0)
					if(qj_Att!=0 && !(((double)(qi_Att/qj_Att))<(1+this.tolleranza)*(prop_i/prop_j) && ((double)(qi_Att/qj_Att))>(1-this.tolleranza)*(prop_i/prop_j)))
						return false;
			}}
		}
		return true;
	}

	private boolean isCompleta(List<Integer> parziale) {
		
		int i=0;
		//vorrei prendere la prima categoria, in elenco per fare i rapporti (uso sempre la stessa al numeratore per fare solo nCategorie-1 controlli)
		//ma non so se quella categoria ha come minimo 0 (o meglio lo so per certo, ma pensando in astratto non lo posso sapere)

		//mi tocca iterare sulle categorie  per trovarne una con minimo maggiore di 0
		//catNum sara l'indice della categoria numeratore delle proporzioni
		int catNum=-1;
		//valNum invece il valore proporzione della categoria 
		double valNum=-1;
		for(Categories c: categories) {
			if(c.getMin()>0 && quantitaCat.get(categories.indexOf(c))!=0) {
				catNum=categories.indexOf(c);
				valNum=c.getProporzione();
				//il break nel for va bene??
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
			//System.out.println("Buono");
			return true;
		}

		return false;
	}

	List<Corredino> ris;
	public void getAll(){
		
		ris= new ArrayList<>();
		for(List<Integer> li : combinazioni) {
			String ret="";
			int tot=0;
			for(int i=0;i <li.size();i++) {
				if(li.get(i)!=0)
					ret += allP.get(i).getName()+" "+li.get(i)+"\n";
				tot += allP.get(i).getPrice()*li.get(i);
			}
			ris.add(new Corredino(ret, tot));
		}
		
		Collections.sort(ris);
		
	}
	
	public List<Corredino> returnAll(){
		return ris;
	}
	
	public String getBest(){
		String ret="";
		int tot=0;
		for(int i=0;i <best.size();i++) {
			if(best.get(i)!=0)
				ret += allP.get(i).getName()+" "+best.get(i)+"\n";
			tot += allP.get(i).getPrice()*best.get(i);
		}
		Corredino b = new Corredino(ret,tot);
		return b.toString();
	}
	

	List<CorredinoSeller> res;
	int maxItem=0;
	CorredinoSeller maxIt=new CorredinoSeller();
	
	public void getAllSeller() {
		
		int att=0;
		res= new ArrayList<>();
		for(List<Integer> li : combinazioni) {
			String ret="";
			double tot=0;
			double income=0;
			att=0;
			for(int i=0;i <li.size();i++) {
				if(li.get(i)!=0)
					ret += allP.get(i).getName()+" "+li.get(i)+"\n";
				tot += allP.get(i).getPrice()*li.get(i);
				income += (allP.get(i).getPrice()-allP.get(i).getSellerPrice())*li.get(i);
				att +=li.get(i);
			}
			
			res.add(new CorredinoSeller(ret, tot, income));
			
			if(att>maxItem) {
				maxItem=att;
				maxIt= new CorredinoSeller(ret,tot,income);
			}
		}
		
		Collections.sort(res);
		
	}
	
	public String returnAllSeller() {
		return res.toString();
	}
	
	public String getMaxIncome() {
		return res.get(res.size()-1).toString();
	}
	
	public String getMaxItem() {
		
		return maxIt.toString()+" con "+maxItem+" prodotti venduti.";
	}



}
