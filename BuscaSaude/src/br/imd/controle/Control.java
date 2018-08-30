package br.imd.controle;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.imd.modelo.IServices;
import br.imd.modelo.UnidadeSaude;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;

public class Control implements Initializable {
	
	@FXML 
	TabPane painel;
	
	// -------- COMPONENTES DO PAINEL CADASTRO / ATUALIZAR / EXCLUIR ----------------
	
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
	
	// --------------------------------------------------------- 
	
	// ---------- COMPONENTES DO PAINEL BUSCAR ---------------- 
	
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
	
	//--------------------------------------------------------- 
	
	
	IServices stub;
	
	
	/**
	 * Método construtor da classe controladora dos elementos da interface gráfica
	 */
	public Control() {
		
		/* Busca no modulo de comunicacao remota (RMI Registry).
		 * Retorna-se uma referencia de objeto para o stub de servidor, 
		 * atraves do qual e possivel realizar a invocacao de metodos remotos */
		
		try{
			stub = (IServices) Naming.lookup("rmi://localhost/Services");
		}
		catch (ConnectException e) {
			exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( MalformedURLException e ) {
			exibirDialogo(true, "Atenção", "Falha na conexão", "Endereço do Servidor RMI Inválido ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( NotBoundException e ) {
			exibirDialogo(true, "Atenção", "Falha no Servidor", "Erro de Bind ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( RemoteException e ) {
			exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
			System.exit(-1);
		}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		toggleGroup = new ToggleGroup();
		
		radio_nome.setToggleGroup(toggleGroup);
		radio_bairro.setToggleGroup(toggleGroup);
		radio_endereco.setToggleGroup(toggleGroup);
		radio_especialidade.setToggleGroup(toggleGroup);
		
		listCadastro.setOnMouseClicked(event -> {
			
			UnidadeSaude selectedItem = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();

			if( selectedItem != null ) {
				
				nome_field_c.setText( selectedItem.getNome() );
		    	address_field_c.setText( selectedItem.getEndereco() );
		    	city_fileld_c.setText( selectedItem.getBairro() );
		    	
		    	ArrayList<String> especialidades = selectedItem.getEspecialidades();		
		    	String esp = "";
				
				for (String especialidade : especialidades) {
					 esp+= especialidade+";";
				}
		    	skills_field_c.setText( esp.substring(0, esp.length()-1) );
			}
            	
        });
	}
	
	/**
	 * Método para cadastrar uma unidade de saúde no servidor utilizando o stub.
	 * @param event Referencia para o criador do evento a ser tratado.
	 */
	@FXML
	private void cadastrarUnidade(ActionEvent event){
	    
		String nome = nome_field_c.getText();
		String endereco = address_field_c.getText();
		String bairro = city_fileld_c.getText();
		ArrayList<String> especialidades = new ArrayList<>();
		
		String especialidade = skills_field_c.getText();
		
		String[] textoSeparado = especialidade.split(";");
		
		for (int i = 0 ; i < textoSeparado.length ; i++) {
			especialidades.add(textoSeparado[i]);
		}
		
		UnidadeSaude unidadeSaude = new UnidadeSaude(nome, endereco, bairro, especialidades);
		
	    try {
	    	
	    	if( validarCampos() ) {
	    		
	    		stub.cadastrar(unidadeSaude);
		    	
		    	nome_field_c.setText("");
		    	address_field_c.setText("");
		    	city_fileld_c.setText("");
		    	skills_field_c.setText("");
		    	
		    	listarCadastro.fire();
		    	exibirDialogo(false, "Atenção", "Operação Concluida", "Unidade Cadastrada com Sucesso !");
	    		
	    	} 
	    	else
	    	{
	    		exibirDialogo(false, "Atenção", "Operação não Realizada", "Todos os Campos devem ser preenchidos!");
	    	}
	    	 
		} 
	    catch (ConnectException e) {
			exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( RemoteException e ) {
			exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
			System.exit(-1);
		}
	    
	}
	
	/**
	 * Método responsável por criar uma caixa de dialogo.
	 * @param error Define se é um dialogo de erro ou informativo.
	 * @param titulo Titulo a ser exibido no dialogo.
	 * @param cabecalho Cabeçalho do dialogo.
	 * @param mensagem Mensagem a ser exibida.
	 */
	private void exibirDialogo(boolean error, String titulo, String cabecalho, String mensagem){
		Alert dialogo;
		
		if( error  ){
			dialogo = new Alert(Alert.AlertType.ERROR);
		}
		else{
			dialogo = new Alert(Alert.AlertType.INFORMATION);
		}

		dialogo.setTitle(titulo);
		dialogo.setHeaderText(cabecalho);
        dialogo.setContentText(mensagem);
        dialogo.showAndWait();
		
	}
	
	/**
	 * Função para validar os campos de cadastro/atualização, para não permitir deixa-los em
	 * branco ou com apenas espaços.
	 * @return Retorna true se os campos contiverem algum valor, false caso estejam em branco
	 * ou com apenas espaços.
	 */
	private boolean validarCampos(){
		
		if( nome_field_c.getText().trim().isEmpty() ) {
			return false;
		}
    	
		if( address_field_c.getText().trim().isEmpty() ) {
			return false;
		}
				
		if( city_fileld_c.getText().trim().isEmpty() ) {
			return false;
		}
				
		if( skills_field_c.getText().trim().isEmpty() ) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método para atualizar as informações de uma unidade de saúde no servidor utilizando o stub.
	 * @param event Referencia para o criador do evento a ser tratado.
	 */
	@FXML
	private void atualizarUnidade(ActionEvent event) {
		
		UnidadeSaude selectedUnidade = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();;
		
		if( selectedUnidade != null ) {
							
			selectedUnidade.setNome( nome_field_c.getText() );
			selectedUnidade.setEndereco( address_field_c.getText() );
			selectedUnidade.setBairro( city_fileld_c.getText() );
			
			ArrayList<String> especialidades = new ArrayList<>();
			
			String especialidade = skills_field_c.getText();
			
			String[] textoSeparado = especialidade.split(";");
			
			for (int i = 0 ; i < textoSeparado.length ; i++) {
				especialidades.add(textoSeparado[i]);
			}
			
			selectedUnidade.setEspecialidades( especialidades );
			
			try {
				
				if( validarCampos() ) {
		    		
			    	stub.atualizar(selectedUnidade);
			    	
			    	nome_field_c.setText("");
			    	address_field_c.setText("");
			    	city_fileld_c.setText("");
			    	skills_field_c.setText("");
	
			    	listarCadastro.fire();			
			    	
			    	exibirDialogo(false, "Atenção", "Operação Concluida", "Unidade Atualizada com Sucesso !");
				}
				else{
					exibirDialogo(false, "Atenção", "Operação não Realizada", "Todos os Campos devem ser preenchidos!");
				}
				
			} 
		    catch (ConnectException e) {
				exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
				System.exit(-1);
			}
			catch( RemoteException e ) {
				exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
				System.exit(-1);
			}
		}
			
		
	      
	}
	
	/**
	 * Método para excluir uma unidade de saúde do servidor utilizando o stub.
	 * @param event Referencia para o criador do evento a ser tratado.
	 */
	@FXML
	private void excluirUnidade(ActionEvent event){
		
		UnidadeSaude selectedUnidade = (UnidadeSaude) listCadastro.getSelectionModel().getSelectedItem();
		
		if( selectedUnidade != null ) {
			
			
			try{
				stub.excluir( selectedUnidade.getId() );
				
		    	nome_field_c.setText("");
		    	address_field_c.setText("");
		    	city_fileld_c.setText("");
		    	skills_field_c.setText("");
		    	
		    	listarCadastro.fire();
		    	exibirDialogo(false, "Atenção", "Operação Concluida", "Unidade excluida com Sucesso !");
			} 
		    catch (ConnectException e) {
				exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
				System.exit(-1);
			}
			catch( RemoteException e ) {
				exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
				System.exit(-1);
			}
		}
	      
	}
	
	
	
	/**
	 * Método para popular o listView da interface gráfica com todas as unidades de saúde
	 * armazenadas no servidor.
	 * @param event Referencia para o criador do evento a ser tratado.
	 */
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
			
		} 
	    catch (ConnectException e) {
			exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( RemoteException e ) {
			exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		
		
	}
	
	/**
	 * Método para realizar a busca de uma unidade de saúde no servidor por diferentes
	 * critérios utilizando o stub.
	 * @param event Referencia para o criador do evento a ser tratado.
	 */
	@FXML
	private void buscarUnidade(ActionEvent event){
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
			
		} 
	    catch (ConnectException e) {
			exibirDialogo(true, "Atenção", "Falha na conexão", "Não foi possivel se conectar ao Servidor RMI ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		catch( RemoteException e ) {
			exibirDialogo(true, "Atenção", "Falha no Servidor", "Ocorreu um erro na execução remota solicidada ! O Programa Será Encerrado !");
			System.exit(-1);
		}
		
		
	}
	
}
