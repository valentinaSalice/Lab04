package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menu;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox check;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	int matricola=0;
    	try {
    		
    		matricola=Integer.parseInt(txtMatricola.getText().trim());
    		if(matricola==0)
    			txtResult.appendText("Inserisci matricola\n");
    		else {
    			
            	Studente s=model.getStudente(matricola);
            	if(s!=null) {
            		
                	List<Corso> corsi=model.informazioniStudente(matricola);
                	if(corsi==null)
                		txtResult.setText("Studente non iscritto a nessun corso");
                	else {
                		
                		for(Corso c: corsi)
                			txtResult.appendText(c.toString()+"\n");
                	}
            		
            	}
            	
            	else
            		txtResult.setText("Matricola non presente");
    			
    			
    		}

    	}catch(NumberFormatException nfe) {
    		
    		txtResult.appendText("Formato matricola non valido\n");
    	}

    		
  

    	
 
    	
    	

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtResult.clear();
    	String nomeCorso=menu.getValue();
    	
    	if(nomeCorso==null) {
    		
    		txtResult.setText("Selezionare un corso!");
    	}
    	
    	else {
    		
        	if(nomeCorso.compareTo("")==0)
        		txtResult.setText("Nessun corso selezionato! Selezionare un corso");
        	
        	else {
        		
         	List<Studente> iscritti =model.informazioniCorso(nomeCorso);
        	
        	for(Studente s: iscritti ) {
        		
        		txtResult.appendText(s.toString()+"\n");
        	}   		
        		
        	}
    		
    		
    		
    	}
 


    	
    	

    }

    @FXML
    void doChoice(ActionEvent event) {
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doNomeCognome(ActionEvent event) {
    
    	txtResult.clear();
    	int matricola=0;
    	try {
    		
    		matricola=Integer.parseInt(txtMatricola.getText().trim());
        	if(matricola==0)
        		txtResult.setText("Inserire la matricola di uno studente");
        	else {
        		
            	Studente s=model.getStudente(matricola);
            	if(s!=null) {
            		
                	txtNome.setText(s.getNome());
                	txtCognome.setText(s.getCognome());
                	String nomeCorso=menu.getValue();
                	if( nomeCorso!=null ) {
                		
                		if(nomeCorso.compareTo("")!=0) {
                			
                    		boolean iscritto=model.iscrittoAlCorso(matricola, nomeCorso);
                    		if(iscritto==true)
                    			txtResult.appendText("Lo studente è iscritto al corso\n");
                    		else
                    			txtResult.appendText("Lo studente non è iscritto al corso\n");
                			
                		}

                	}
            		
            	}
            	else
            		txtResult.setText("Studente non presente");
        		
        		
        	}
    		
    	}catch(NumberFormatException nfe) {
    		
    		txtResult.appendText("Formato matricola errato!");
    	}
    	
    	



    	

    	

    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	

    }

    @FXML
    void initialize() {
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		menu.getItems().add("");
		for(Corso c: model.getCorsi())
		   menu.getItems().add(c.getNome());
		

		
			
		
		
		
		
	}
}
