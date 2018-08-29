package br.imd.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface IServices.
 * Representa os serviços que vão estar implementados no servidor, e que vão poder ser
 * invocados a partir do cliente.
 */
public interface IServices extends Remote {

	/**
	 * Assinatura do Método cadastrar do servidor.
	 *
	 * @param unidadeSaude: Unidade de saúde a ser cadastrada no servidor.
	 * @throws RemoteException: Exceção que pode ser lançada caso haja algum erro na execução
	 * remota.
	 */
	public void cadastrar(UnidadeSaude unidadeSaude ) throws RemoteException;

	/**
	 * Assinatura do Método atualizar do servidor.
	 *
	 * @param unidadeSaude: Unidade de saúde a ser atualizada no servidor.
	 * @throws RemoteException: Exceção que pode ser lançada caso haja algum erro na execução
	 * remota.
	 */
	public void atualizar(UnidadeSaude unidadeSaude) throws RemoteException;
	

	/**
	 * Assinatura do Método excluir do servidor.
	 *
	 * @param id: ID da unidade a ser excluida do servidor.
	 * @throws RemoteException: Exceção que pode ser lançada caso haja algum erro na execução
	 * remota.
	 */
	public void excluir( int id) throws RemoteException;

	/**
	 * Assinatura do Método Buscar do servidor.
	 *
	 * @param criterio: Identificação do criterio da busca, 1 - Busca por Nome, 2- Busca por
	 * Endereço, 3 - Busca por Bairro, 4 - Busca por Especialidade.
	 * @param busca: Valor a ser buscado no servidor.
	 * @return Retorna um arrayList com as unidades que satisfazem os critérios da busca, podendo
	 * ser vazio caso não seja encontrado nenhuma.
	 * @throws RemoteException: Exceção que pode ser lançada caso haja algum erro na execução
	 */
	public ArrayList<UnidadeSaude> buscar(int criterio, String busca) throws RemoteException;
	
	/**
	 * Assinatura do Método getUnidades do servidor.
	 *
	 * @return Retorna todas as unidades cadastradas no servidor.
	 * @throws RemoteException: Exceção que pode ser lançada caso haja algum erro na execução
	 */
	public ArrayList<UnidadeSaude> getUnidades() throws RemoteException;
	
	
}


