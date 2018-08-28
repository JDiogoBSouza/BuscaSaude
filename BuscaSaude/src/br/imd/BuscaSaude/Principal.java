package br.imd.BuscaSaude;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Application   {

	private Stage primeiro;
	private VBox tlPrincipal;
	
	
	@Override
	public void start(Stage primaryStage) throws InvocationTargetException {
		primeiro = primaryStage;
		primeiro.setTitle("Cliente");
		initPrincipal();
	}

	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	private void initPrincipal() throws InvocationTargetException {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principal.class.getResource("TelaPrincipal.fxml"));
			tlPrincipal = (VBox) loader.load();
			
			//mostra a scene contendo o layout
			Scene scene = new Scene(tlPrincipal);
			primeiro.setScene(scene);
			primeiro.show();
			
		}
		catch (Exception e) {

		    // generic exception handling
		    e.printStackTrace();
		}
		
	}
	
	
}
