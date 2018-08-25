package br.imd.BuscaSaude;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class Servidor.
 */
public class Servidor 
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws RemoteException the remote exception
	 * @throws MalformedURLException the malformed URL exception
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		// Execução do modulo de referencia remota (RMI Registry)
		LocateRegistry.createRegistry(1099);
		
		// Instanciacao do servene
		Services services = new Services();
		
		// Registro do servente no modulo de referencia remota (RMI Registry)
		Naming.rebind("rmi://localhost/Services", services);
		System.out.println("Servidor pronto e registrado no RMI Registry.");
	}
}