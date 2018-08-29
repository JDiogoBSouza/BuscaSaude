package br.imd.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface IServices.
 * Representa os servi�os que v�o estar implementados no servidor, e que v�o poder ser
 * invocados a partir do cliente.
 */
public interface IServices extends Remote {

	/**
	 * Assinatura do M�todo cadastrar do servidor.
	 *
	 * @param unidadeSaude: Unidade de sa�de a ser cadastrada no servidor.
	 * @throws RemoteException: Exce��o que pode ser lan�ada caso haja algum erro na execu��o
	 * remota.
	 */
	public void cadastrar(UnidadeSaude unidadeSaude ) throws RemoteException;

	/**
	 * Assinatura do M�todo atualizar do servidor.
	 *
	 * @param unidadeSaude: Unidade de sa�de a ser atualizada no servidor.
	 * @throws RemoteException: Exce��o que pode ser lan�ada caso haja algum erro na execu��o
	 * remota.
	 */
	public void atualizar(UnidadeSaude unidadeSaude) throws RemoteException;
	

	/**
	 * Assinatura do M�todo excluir do servidor.
	 *
	 * @param id: ID da unidade a ser excluida do servidor.
	 * @throws RemoteException: Exce��o que pode ser lan�ada caso haja algum erro na execu��o
	 * remota.
	 */
	public void excluir( int id) throws RemoteException;

	/**
	 * Assinatura do M�todo Buscar do servidor.
	 *
	 * @param criterio: Identifica��o do criterio da busca, 1 - Busca por Nome, 2- Busca por
	 * Endere�o, 3 - Busca por Bairro, 4 - Busca por Especialidade.
	 * @param busca: Valor a ser buscado no servidor.
	 * @return Retorna um arrayList com as unidades que satisfazem os crit�rios da busca, podendo
	 * ser vazio caso n�o seja encontrado nenhuma.
	 * @throws RemoteException: Exce��o que pode ser lan�ada caso haja algum erro na execu��o
	 */
	public ArrayList<UnidadeSaude> buscar(int criterio, String busca) throws RemoteException;
	
	/**
	 * Assinatura do M�todo getUnidades do servidor.
	 *
	 * @return Retorna todas as unidades cadastradas no servidor.
	 * @throws RemoteException: Exce��o que pode ser lan�ada caso haja algum erro na execu��o
	 */
	public ArrayList<UnidadeSaude> getUnidades() throws RemoteException;
	
	
}


