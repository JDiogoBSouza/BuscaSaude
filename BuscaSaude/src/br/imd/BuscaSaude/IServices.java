package br.imd.BuscaSaude;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServices.
 */
public interface IServices extends Remote {

	/**
	 * Cadastrar.
	 *
	 * @param unidadeSaude the unidade saude
	 * @throws RemoteException the remote exception
	 */
	public void cadastrar(UnidadeSaude unidadeSaude ) throws RemoteException;
	
	/**
	 * Atualizar.
	 *
	 * @param unidadeSaude the unidade saude
	 * @throws RemoteException the remote exception
	 */
	public void atualizar(UnidadeSaude unidadeSaude) throws RemoteException;
	
	/**
	 * Excluir.
	 *
	 * @param id the id
	 * @throws RemoteException the remote exception
	 */
	public void excluir( int id) throws RemoteException;

	/**
	 * Buscar.
	 *
	 * @param criterio the criterio
	 * @param busca the busca
	 * @return the array list
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<UnidadeSaude> buscar(int criterio, String busca) throws RemoteException;
	
	/**
	 * Gets the unidades.
	 *
	 * @return the unidades
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<UnidadeSaude> getUnidades() throws RemoteException;
	
	
}


