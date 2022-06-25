/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	txtResult.clear();
    	Integer anno= boxAnno.getValue();
    	if(anno!=null) {
    		String risultato=this.model.creaGrafo(anno);
        	txtResult.setText(risultato);	
        	String vicini = this.model.getVicini();
        	txtResult.appendText(vicini);
    	}else {
    		txtResult.setText("Scegliere un anno prima di creare il grafo!");
    		return;
    	}
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	Integer anno= boxAnno.getValue();
    	Integer mese= boxMese.getValue();
    	Integer giorno= boxGiorno.getValue();
    	if(anno==null || mese==null || giorno==null) {
    		txtResult.setText("Inserisci tutti i valori cojone!");
    		return;
    	}
    	try {
    		int num= Integer.parseInt(txtN.getText());
    		if(num<1 || num>10)
    			throw new NumberFormatException();
    		int ris= this.model.simula(num, giorno, mese, anno);
    		txtResult.setText("Simulazione effettuata con "+num+" agent!\n");
    		txtResult.appendText("Crimini mal gestiti nel giorno "+giorno+"/"+mese+"/"+anno+" sono: "+ris);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserisci un valore numerico intero tra 1 e 10, cojone!");
    		return;
    	}
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(int i=2014; i<2018; i++)
    		boxAnno.getItems().add(i);
    	for(int i=1;i<=12;i++) {
    		boxMese.getItems().add(i);
    	}
    	for(int i=1;i<=31;i++) {
    		boxGiorno.getItems().add(i);
    	}
    }
}
