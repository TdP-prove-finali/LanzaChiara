/**
 * Sample Skeleton for 'Corredino.fxml' Controller Class
 */

package it.polito.tdp.corredino;


import it.polito.tdp.corredino.model.CorredinoSeller;
import it.polito.tdp.corredino.model.Model;
import it.polito.tdp.corredino.model.ProdottoCorredino;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CorredinoController {
	
	Model model;
	
    @FXML // fx:id="txtResult"
    private TextField txtResult; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtBdg"
    private TextField txtBdg; // Value injected by FXMLLoader

    @FXML // fx:id="buttonMin"
    private Button buttonMin; // Value injected by FXMLLoader
    
    @FXML // fx:id="season"
    private ComboBox<String> season; // Value injected by FXMLLoader

    @FXML // fx:id="btnMaxC"
    private Button btnMaxC; // Value injected by FXMLLoader

    @FXML // fx:id="btnTutti"
    private Button btnTutti; // Value injected by FXMLLoader

    @FXML // fx:id="btnMaxIncome"
    private Button btnMaxIncome; // Value injected by FXMLLoader

    @FXML // fx:id="btnMaxItem"
    private Button btnMaxItem; // Value injected by FXMLLoader

    @FXML // fx:id="btnTuttiv2"
    private Button btnTuttiv2; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<ProdottoCorredino> table; // Value injected by FXMLLoader

    @FXML // fx:id="product"
    private TableColumn<ProdottoCorredino, String> product; // Value injected by FXMLLoader

    @FXML // fx:id="qnt"
    private TableColumn<ProdottoCorredino, Integer> qnt; // Value injected by FXMLLoader

    @FXML // fx:id="price"
    private TableColumn<ProdottoCorredino, Double> price; // Value injected by FXMLLoader

    @FXML // fx:id="sellerPrice"
    private TableColumn<ProdottoCorredino, Double> sellerPrice; // Value injected by FXMLLoader

    @FXML // fx:id="combinazioni"
    private TextField combinazioni; // Value injected by FXMLLoader

    @FXML
    void CalcolaMaxC(ActionEvent event) {
    	txtResult.clear();
    	table.getItems().clear();
    	combinazioni.clear();
    	for(ProdottoCorredino pq: model.getMaxC().getP())
    	this.table.getItems().add(pq);
    	this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
    	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
    	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
    	this.sellerPrice.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("sellerIncome"));
    	
    	this.txtResult.appendText("Corredino con "+model.categorie()+" categorie diverse:");
    }

    @FXML
    void CalcolaMaxIncome(ActionEvent event) {
    	txtResult.clear();
    	table.getItems().clear();
    	combinazioni.clear();
    	for(ProdottoCorredino pq: model.getMaxIncome().getP())
    	this.table.getItems().add(pq);
    	this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
    	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
    	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
    	this.sellerPrice.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("sellerIncome"));
    	
    	txtResult.appendText("Con questa combinazione guadagni "+model.getMaxIncome().getIncomeTot());
    }

    @FXML
    void CalcolaMin(ActionEvent event) {
    	txtResult.clear();
    	table.getItems().clear();
    	combinazioni.clear();
    	for(ProdottoCorredino pq: model.getBest().getP()) {
    	this.table.getItems().add(pq);
    	this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
    	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
    	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
    	this.sellerPrice.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("sellerIncome"));
    	}
    	
    	txtResult.appendText("Con quest combinazione spendi solo "+model.getBest().getTot());
    }
    

    @FXML
    void clientInit(ActionEvent event) throws CloneNotSupportedException {
    	btnMaxIncome.setDisable(true);
		btnMaxItem.setDisable(true);
		btnTuttiv2.setDisable(true);
    	this.sellerPrice.setVisible(false);
    	this.table.setVisible(true);
    	combinazioni.clear();
    	table.getItems().clear();
    	if(txtBdg.getText().isEmpty()) {
    		txtResult.appendText("Inserisci un budget\n");
    		return;}
    	if(season.getValue()==null) {
    		txtResult.appendText("Inserisci la stagione in cui nascerà il bambino\n");
    		return;}
    	if(Integer.parseInt(txtBdg.getText())<100) {
    		txtResult.appendText("Inserisci un budget reale (Minimo 100 euro)\n");
    		return;}
    	if(!txtBdg.getText().isEmpty() && season.getValue()!=null) {
    		String seas="";
    		if(season.getValue().equals("Autunno/Primavera")) {
    			seas="Primavera";
    		} else
    			seas=season.getValue();
    		txtResult.clear();
    		buttonMin.setDisable(false);
        	btnMaxC.setDisable(false);
        	btnTutti.setDisable(false);
    		model.calcola(Integer.parseInt(txtBdg.getText()), seas);
    		model.getAll();
    		}
    		

    }

    @FXML
    void sellerInit(ActionEvent event) throws CloneNotSupportedException {
    	buttonMin.setDisable(true);
		btnMaxC.setDisable(true);
		btnTutti.setDisable(true);
    	this.sellerPrice.setVisible(true);
    	this.table.setVisible(true);
    	combinazioni.clear();
    	table.getItems().clear();
    	if(txtBdg.getText().isEmpty()) {
    		txtResult.appendText("Inserisci un budget\n");
    		return;}
    	if(season.getValue()==null) {
    		txtResult.appendText("Inserisci la stagione in cui nascerà il bambino\n");
    		return;}
    	if(Integer.parseInt(txtBdg.getText())<100) {
    		txtResult.appendText("Inserisci un budget reale (Minimo 100 euro)\n");
    		return;}
    	if(!txtBdg.getText().isEmpty() && season.getValue()!=null) {
    		String seas="";
    		if(season.getValue().equals("Autunno/Primavera")) {
    			seas="Primavera";
    		} else
    			seas=season.getValue();
    		txtResult.clear();
    		btnMaxIncome.setDisable(false);
        	btnMaxItem.setDisable(false);
        	btnTuttiv2.setDisable(false);
    		model.calcola(Integer.parseInt(txtBdg.getText()), seas);
    		model.getAllSeller();}
    }

    @FXML
    void MostraTutti(ActionEvent event)  {
    	table.getItems().clear();
    	txtResult.clear();
    	combinazioni.clear();
    	if(model.getAll().isEmpty()) {
    		txtResult.appendText("Non ci sono combinazioni con il budget da te impostato.");
    		return;
    	}
    	if(model.getAll().size()==1) {
    		txtResult.appendText("C'è solo una combinazione con il budget da te impostato.");
    		
    	} else {
    		txtResult.appendText("Ci sono "+model.getAll().size()+" combinazioni disponibili.");
    	}
    	
    	for(CorredinoSeller cs: model.getAll()) {
    		for(ProdottoCorredino pq: cs.getP()) {
    		this.table.getItems().addAll(pq);
    		this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
        	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
        	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
    		}
    		
    		this.table.getItems().addAll(new ProdottoCorredino(cs.getTot()));
    		this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
        	
    		this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
        	
    		this.table.getItems().add(null);
    		this.table.getItems().add(null);
    	}	
    }

    

    @FXML
    void MostraTuttiv2(ActionEvent event) {
    	txtResult.clear();
    	table.getItems().clear();
    	combinazioni.clear();
    	for(CorredinoSeller cs: model.getAllSeller()) {
    		for(ProdottoCorredino pq: cs.getP()) {
    			this.table.getItems().addAll(pq);
		    	this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
		    	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
		    	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
		    	this.sellerPrice.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("sellerIncome"));
    		}
    		this.table.getItems().addAll(new ProdottoCorredino(cs.getTot(), (Double)cs.getIncomeTot()));
    		//this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
        	
    		//this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
        	
    		this.table.getItems().add(null);
    		this.table.getItems().add(null);
    		
    	}
    	if(model.getAllSeller().isEmpty()) {
    		txtResult.appendText("Non ci sono combinazioni con il budget da te impostato.");
    		return;
    	}
    	if(model.getAllSeller().size()==1) {
    		txtResult.appendText("C'è solo una combinazione con il budget da te impostato.");
    		
    	} else {
    		txtResult.appendText("Ci sono "+model.getAll().size()+" combinazioni disponibili.");
    	}
    }

 

    @FXML
    void calcolaMaxItem(ActionEvent event) {
    	txtResult.clear();
    	table.getItems().clear();
    	combinazioni.clear();
    	for(ProdottoCorredino pq: model.getMaxItem().getP())
    	this.table.getItems().add(pq);
    	this.product.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,String>("name"));
    	this.qnt.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Integer>("quantita"));
    	this.price.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("costo"));
    	this.sellerPrice.setCellValueFactory(new PropertyValueFactory<ProdottoCorredino,Double>("sellerIncome"));
    	txtResult.appendText("Con "+model.maxItN()+" prodotti venduti.");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtBdg != null : "fx:id=\"txtBdg\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert buttonMin != null : "fx:id=\"buttonMin\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnMaxC != null : "fx:id=\"btnMaxC\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnTutti != null : "fx:id=\"btnTutti\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnMaxIncome != null : "fx:id=\"btnMaxIncome\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnMaxItem != null : "fx:id=\"btnMaxItem\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnTuttiv2 != null : "fx:id=\"btnTuttiv2\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert season != null : "fx:id=\"season\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert product != null : "fx:id=\"product\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert qnt != null : "fx:id=\"qnt\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert sellerPrice != null : "fx:id=\"sellerPrice\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert combinazioni != null : "fx:id=\"combinazioni\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Corredino.fxml'.";
        
    }

	public void setModel(Model model) {
		this.model=model;
		//aggiungo la stagione Autuno/PRimavera anche se sul db c'è solo pprimavera per completezza, prima di iniziare a lavorare però lo modifico opportunamente
		season.getItems().addAll("Inverno", "Autunno/Primavera", "Estate");
		buttonMin.setDisable(true);
		btnMaxC.setDisable(true);
		btnTutti.setDisable(true);
		btnMaxIncome.setDisable(true);
		btnMaxItem.setDisable(true);
		btnTuttiv2.setDisable(true);
		
		table.setVisible(false);
		
		
	}
}
