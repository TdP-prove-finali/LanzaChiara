/**
 * Sample Skeleton for 'Corredino.fxml' Controller Class
 */

package it.polito.tdp.corredino;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.corredino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;

public class CorredinoController {
	
	Model model;

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

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void CalcolaMaxC(ActionEvent event) {

    }

    @FXML
    void CalcolaMaxIncome(ActionEvent event) {

    }

    @FXML
    void CalcolaMin(ActionEvent event) {

    }

    @FXML
    void MostraTutti(ActionEvent event) {
    	if(txtBdg.getText().isEmpty())
    		txtResult.appendText("Inserisci un budget\n");
    	if(season.getValue()==null)
    		txtResult.appendText("Inserisci la stagione in cui nascerà il bambino\n");
    	if(Integer.parseInt(txtBdg.getText())<100)
    		txtResult.appendText("Inserisci un budget reale (Minimo 100 euro)\n");
    	if(!txtBdg.getText().isEmpty() && season.getValue()!=null) {
    	model.calcola(Integer.parseInt(txtBdg.getText()), season.getValue());
   // 	txtResult.appendText(model.getBest());
    	}

    }

    @FXML
    void MostraTuttiv2(ActionEvent event) {

    }

    @FXML
    void Reset(ActionEvent event) {

    }

    @FXML
    void Result(MouseEvent event) {

    }

    @FXML
    void calcolaMaxItem(ActionEvent event) {

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
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Corredino.fxml'.";
        assert season != null : "fx:id=\"season\" was not injected: check your FXML file 'Corredino.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		season.getItems().addAll("Inverno", "Autunno/Primavera", "Estate");
		buttonMin.setDisable(true);
		btnMaxC.setDisable(true);
		btnTutti.setDisable(true);
		btnMaxIncome.setDisable(true);
		btnMaxItem.setDisable(true);
		btnTuttiv2.setDisable(true);
		
		
		
	}
}
