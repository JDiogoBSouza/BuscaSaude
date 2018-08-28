package br.imd.BuscaSaude;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Control implements Initializable {
	
	@FXML 
	TabPane painel;
	
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
	@FXML
	Button listarAtualizar;
	@FXML
	ListView listAtualizar;
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
	@FXML
	Button listarExcluir;
	@FXML
	ListView listExcluir;
	/**---------------------------------------------------------  */
	
	
	IServices stub;
	
	
	
	
	
	
	public Control() throws MalformedURLException, RemoteException, NotBoundException {
		
		/* Busca no modulo de comunicacao remota (RMI Registry).
		 * Retorna-se uma referencia de objeto para o stub de servidor, 
		 * atraves do qual e possivel realizar a invocacao de metodos remotos */
		stub = (IServices) Naming.lookup("rmi://localhost/Services");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	private void adicionarUnidade(ActionEvent event) throws RemoteException {
	    
		String nome = nome_field_c.getText();
		String endereco = address_field_c.getText();
		String bairro = city_fileld_c.getText();
		ArrayList<String> especialidades = new ArrayList<>();
		especialidades.add(skills_field_c.getText());
		
		UnidadeSaude unidadeSaude = new UnidadeSaude(nome, endereco, bairro, especialidades);
		
	    try {
	    	stub.cadastrar(unidadeSaude);
	    	
	    	nome_field_c.setText("");
	    	address_field_c.setText("");
	    	city_fileld_c.setText("");
	    	skills_field_c.setText("");
	    	
	    	 new Alert(Alert.AlertType.INFORMATION, " Unidade Cadatrada ").showAndWait();
		} catch (Exception e) {
			// Button was clicked, do something…
		    new Alert(Alert.AlertType.ERROR, "ERRO DE EXECUÇÃO" ).showAndWait();
		}
	    
	}
		
	@FXML
	private void atualizar_unidade(ActionEvent event) {
		
		nome_field_A.setText("");
    	address_field_A.setText("");
    	city_fileld_A.setText("");
    	skills_field_A.setText("");
		
	    new Alert(Alert.AlertType.ERROR, nome_field_A.getText() ).showAndWait();
	      
	}
	
	
	@FXML
	private void excluirUnidade(ActionEvent event) {
	    // Button was clicked, do something…
		
	    new Alert(Alert.AlertType.ERROR, nome_field_E.getText() ).showAndWait();
	      
	}
	
	@FXML
	private void listar(ActionEvent event) throws RemoteException
	{		
		ArrayList<UnidadeSaude> unidades = stub.getUnidades();
		
		if( event.getSource() == listarAtualizar )
		{
			listAtualizar.getItems().clear();
			
			for (UnidadeSaude unidade : unidades)
	        {
				listAtualizar.getItems().add(unidade);
	        }
		}
		else
		{
			listExcluir.getItems().clear();
			
			for (UnidadeSaude unidade : unidades)
	        {
				listExcluir.getItems().add(unidade);
	        }
		}
	}

	@FXML
	private void buscarUnidade(ActionEvent event) {
	    // Button was clicked, do something…
		    
	}
	
}
