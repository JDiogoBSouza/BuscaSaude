package br.imd.BuscaSaude;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Control implements Initializable {
	
	@FXML 
	TabPane painel;
	
	/** -------- COMPONENTES DO PAINEL CADASTRO / ATUALIZAR / EXCLUIR ---------------- */
	@FXML
	TextField nome_field_c;
	@FXML
	TextField address_field_c;
	@FXML
	TextField city_fileld_c;
	@FXML
	TextField skills_field_c;
	@FXML
	ListView<UnidadeSaude> listCadastro;
	@FXML
	Button listarCadastro;
	/**---------------------------------------------------------  */
	
	/** ---------- COMPONENTES DO PAINEL BUSCAR ---------------- */
	ToggleGroup toggleGroup;
	
	@FXML
	RadioButton radio_nome;
	@FXML
	RadioButton radio_bairro;
	@FXML
	RadioButton radio_endereco;
	@FXML
	RadioButton radio_especialidade;
	@FXML
	Button buscaUnidade;
	@FXML
	ListView<UnidadeSaude> listBuscar;
	@FXML
	TextField busca_field;
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
		
		toggleGroup = new ToggleGroup();
		
		radio_nome.setToggleGroup(toggleGroup);
		radio_bairro.setToggleGroup(toggleGroup);
		radio_endereco.setToggleGroup(toggleGroup);
		radio_especialidade.setToggleGroup(toggleGroup);
		
		listCadastro.setOnMouseClicked(event -> {
            
			if( ! (listCadastro.getItems().isEmpty()) ) {
				
				UnidadeSaude selectedItem = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();

				nome_field_c.setText( selectedItem.getNome() );
		    	address_field_c.setText( selectedItem.getEndereco() );
		    	city_fileld_c.setText( selectedItem.getBairro() );
		    	skills_field_c.setText( selectedItem.getEspecialidades().get(0) );
			}
            	
        });
	}
	
	@FXML
	private void cadastrarUnidade(ActionEvent event){
	    
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
	    	 listarCadastro.fire();
	    	 
	    	 
		} catch (Exception e) {
		    new Alert(Alert.AlertType.ERROR, "ERRO DE EXECUÇÃO" ).showAndWait();
		}
	    
	}
		
	@FXML
	private void atualizarUnidade(ActionEvent event) {
		
		UnidadeSaude selectedUnidade;
		
		if( ! (listCadastro.getItems().isEmpty()) ) {
			
			selectedUnidade = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();
							
			selectedUnidade.setNome( nome_field_c.getText() );
			selectedUnidade.setEndereco( address_field_c.getText() );
			selectedUnidade.setBairro( city_fileld_c.getText() );
			
			ArrayList<String> especialidades = new ArrayList<>();
			especialidades.add(skills_field_c.getText());
			
			selectedUnidade.setEspecialidades( especialidades );
			
			try {
		    	stub.atualizar(selectedUnidade);
		    	
		    	nome_field_c.setText("");
		    	address_field_c.setText("");
		    	city_fileld_c.setText("");
		    	skills_field_c.setText("");
		    	
		    	 new Alert(Alert.AlertType.INFORMATION, " Unidade Atualizada ").showAndWait();
		    	 
		    	 listarCadastro.fire();
		    	 
			} catch (Exception e) {
				// Button was clicked, do something…
			    new Alert(Alert.AlertType.ERROR, "ERRO DE EXECUÇÃO" ).showAndWait();
			}
			
			nome_field_c.setText("");
	    	address_field_c.setText("");
	    	city_fileld_c.setText("");
	    	skills_field_c.setText("");
		}
			
		
	      
	}
	
	
	@FXML
	private void excluirUnidade(ActionEvent event){
		
		UnidadeSaude selectedUnidade;
		
		if( ! (listCadastro.getItems().isEmpty()) ) {
			
			selectedUnidade = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();
			
			try{
				stub.excluir( selectedUnidade.getId() );
				listarCadastro.fire();
			}
			catch( RemoteException e ){
				
			}
		}
	      
	}
	
	@FXML
	private void listar(ActionEvent event)
	{		
		ArrayList<UnidadeSaude> unidades;
		
		try
		{
			unidades = stub.getUnidades();
			
			listCadastro.getItems().clear();
			
			for (UnidadeSaude unidade : unidades){
				listCadastro.getItems().add(unidade);
	        }
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@FXML
	private void buscarUnidade(ActionEvent event){
	    // Button was clicked, do something…
		int selecao = 1;
		
		if( radio_nome.isSelected() ){
			selecao = 1;
		}
		else if( radio_endereco.isSelected() ) {
			selecao = 2;
		}
		else if( radio_bairro.isSelected() ) {
			selecao = 3;
		}
		else if( radio_especialidade.isSelected() ) {
			selecao = 4;
		}
		
		String busca = busca_field.getText();
		
		ArrayList<UnidadeSaude> unidades;
		
		try
		{
			unidades = stub.buscar(selecao, busca );
			
			listBuscar.getItems().clear();

			for (UnidadeSaude unidade : unidades){
				listBuscar.getItems().add(unidade);
	        }
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
