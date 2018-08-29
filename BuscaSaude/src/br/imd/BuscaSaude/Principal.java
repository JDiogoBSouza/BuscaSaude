package br.imd.BuscaSaude;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Application   {

	private Stage primeiro;
	private VBox tlPrincipal;
	
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws InvocationTargetException {
		primeiro = primaryStage;
		primeiro.setTitle("Cliente");
		primeiro.setResizable(false);
		initPrincipal();
	}

	public static void main(String[] args) {
		launch(args);		
	}
	
	/**
	 * Método responsável pela inicialização da interface gráfica.
	 * @throws InvocationTargetException
	 */
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
