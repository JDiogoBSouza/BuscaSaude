package br.imd.BuscaSaude;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class Control implements Initializable {

	
	/** -------- COMPONENTES DO PAINEL CADASTRO ---------------- */
	@FXML
	TextField nome_field_c;
	@FXML
	TextField address_field_c;
	@FXML
	TextField city_fileld_c;
	@FXML
	TextField skills_field_c;
	/**---------------------------------------------------------  */
	
	/** ---------- COMPONENTES DO PAINEL ATUALIZAR--------------- */
	@FXML
	TextField nome_field_A;
	@FXML
	TextField address_field_A;	
	@FXML
	TextField city_fileld_A;
	@FXML
	TextField skills_field_A;
	/**---------------------------------------------------------  */
	
	/** ---------- COMPONENTES DO PAINEL EXCLUIR---------------- */
	@FXML
	TextField nome_field_E;
	@FXML
	TextField address_field_E;	
	@FXML
	TextField city_fileld_E;
	@FXML
	TextField skills_field_E;
	/**---------------------------------------------------------  */
	
	
	
	public Control() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
	}
	
	@FXML
	private void adicionarUnidade(ActionEvent event) {
	    // Button was clicked, do something…		
	    new Alert(Alert.AlertType.ERROR, nome_field_c.getText() ).showAndWait();
	    
	    
	    
	}
		
	@FXML
	private void atualizar_unidade(ActionEvent event) {
	    // Button was clicked, do something…
		
	    new Alert(Alert.AlertType.ERROR, nome_field_A.getText() ).showAndWait();
	      
	}
	
	
	@FXML
	private void excluirUnidade(ActionEvent event) {
	    // Button was clicked, do something…
		
	    new Alert(Alert.AlertType.ERROR, nome_field_E.getText() ).showAndWait();
	      
	}

	@FXML
	private void buscarUnidade(ActionEvent event) {
	    // Button was clicked, do something…
		
	    
	      
	}
	
}
