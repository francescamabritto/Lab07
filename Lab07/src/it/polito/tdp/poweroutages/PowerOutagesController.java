package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController{
	
	Model model = new Model();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="CmbNERC"
    private ComboBox<String> CmbNERC; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="btnWCA"
    private Button btnWCA; // Value injected by FXMLLoader

    @FXML
    
    // fare controlli
    void doSelectNERC(ActionEvent event) {
    		Nerc nercSelezionato = new Nerc();
    		for(Nerc n: model.getNercList()) {
    			if(n.getValue().compareTo(CmbNERC.getValue())==0) {
    				nercSelezionato = n;
    			}
    		}
    }

    @FXML
    void doWorstCaseAnalysis(ActionEvent event) {
    	String yearsStr = txtYears.getText();
    	String hoursStr = txtHours.getText();
    	if(yearsStr == null || yearsStr.isEmpty() || hoursStr==null || hoursStr.isEmpty()) {
    		this.txtResult.appendText("Campi vuoti. Inserire tutti i valori necessari. \n");
    		return;
    	}
    	try {
	    		int anni = Integer.parseInt(yearsStr);  		
	    		int ore = Integer.parseInt(hoursStr);
	    	
	    		List<PowerOutages> res = new ArrayList<>(model.trovaSequenza(CmbNERC.getValue(), anni, ore));
	    		txtResult.appendText(res.toString());
    	} catch(NumberFormatException e) {
    			this.txtResult.appendText("Inserire un valore corretto per ora e anno");
    			return;
    		}    		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert CmbNERC != null : "fx:id=\"CmbNERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnWCA != null : "fx:id=\"btnWCA\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		List<String> listaNerc = model.getListaNerc();
		this.CmbNERC.getItems().setAll(listaNerc);
	}
    
    
}

