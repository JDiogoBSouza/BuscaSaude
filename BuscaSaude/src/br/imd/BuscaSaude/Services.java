package br.imd.BuscaSaude;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class Services.
 */
public class Services extends UnicastRemoteObject implements IServices {

	/** The unidades. */
	private ArrayList<UnidadeSaude> unidades;
	
	/** The id count. */
	private int idCount;


	/**
	 * Instantiates a new services.
	 *
	 * @throws RemoteException the remote exception
	 */
	protected Services() throws RemoteException {
		super();
		unidades = new ArrayList<>();
		idCount = 0;
	}

	/* (non-Javadoc)
	 * @see br.imd.BuscaSaude.IServices#cadastrar(br.imd.BuscaSaude.UnidadeSaude)
	 */
	@Override
	public void cadastrar(UnidadeSaude unidadeSaude) throws RemoteException {
		unidadeSaude.setId(idCount);
		unidades.add(unidadeSaude);
		System.out.println("Nova Unidade Cadastrada: \n" + unidadeSaude.toString() );
		idCount++;
	}

	/* (non-Javadoc)
	 * @see br.imd.BuscaSaude.IServices#atualizar(br.imd.BuscaSaude.UnidadeSaude)
	 */
	@Override
	public void atualizar(UnidadeSaude unidadeSaude) throws RemoteException {
		UnidadeSaude unidadeSaudeIterator = null;
		Iterator<UnidadeSaude> inter1 = unidades.iterator();
		
		while (inter1.hasNext()) {
			
			unidadeSaudeIterator = (UnidadeSaude) inter1.next();
			
			
			if(unidadeSaude.getId() == unidadeSaudeIterator.getId() ) {
				int index = unidades.indexOf(unidadeSaudeIterator);
				unidades.set(index, unidadeSaude);
			}
			
			
		}
	}

	/* (non-Javadoc)
	 * @see br.imd.BuscaSaude.IServices#excluir(int)
	 */
	@Override
	public void excluir( int id ) throws RemoteException {
		UnidadeSaude unidadeSaudeIterator = null;
		Iterator<UnidadeSaude> inter1 = unidades.iterator();
		
		while (inter1.hasNext()) {
			
			unidadeSaudeIterator = (UnidadeSaude) inter1.next();
			
			
			if( id == unidadeSaudeIterator.getId() ) {
				int index = unidades.indexOf(unidadeSaudeIterator);
				unidades.remove(index);
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.imd.BuscaSaude.IServices#buscar(int, java.lang.String)
	 */
	@Override
	public ArrayList<UnidadeSaude> buscar(int criterio, String busca) throws RemoteException {
		
		UnidadeSaude unidadeSaudeIterator = null;
		String EspContentList;
		ArrayList<UnidadeSaude> returnList = new ArrayList<>();
		Iterator<UnidadeSaude> inter1 = unidades.iterator();
		
		
		switch (criterio) {
			case 1:
				while (inter1.hasNext()) {
					
					unidadeSaudeIterator = (UnidadeSaude) inter1.next();
							
					if( unidadeSaudeIterator.getNome().toLowerCase().contains(busca.toLowerCase()) ) {
						returnList.add(unidadeSaudeIterator);
					}		
				}
			break;
			case 2:
				while (inter1.hasNext()) {	
					unidadeSaudeIterator = (UnidadeSaude) inter1.next();
						
					if( unidadeSaudeIterator.getEndereco().toLowerCase().contains(busca.toLowerCase()) ) {
						returnList.add(unidadeSaudeIterator);
					}		
				}
			break;
			case 3:
				while (inter1.hasNext()) {	
					unidadeSaudeIterator = (UnidadeSaude) inter1.next();
						
					if( unidadeSaudeIterator.getBairro().toLowerCase().contains(busca.toLowerCase())  ) {
						returnList.add(unidadeSaudeIterator);
					}		
				}
			break;
			case 4:
				while (inter1.hasNext()) {	
					unidadeSaudeIterator = (UnidadeSaude) inter1.next();
					
					ArrayList<String> listaEspecialidades = unidadeSaudeIterator.getEspecialidades();
					
					Iterator<String> interListaEspecialidades = listaEspecialidades.iterator();
					while (interListaEspecialidades.hasNext()) {
						EspContentList = interListaEspecialidades.next();
						
						if(EspContentList.toLowerCase().contains(busca.toLowerCase())) {
							returnList.add(unidadeSaudeIterator);
							break;
						}
						
					}		
				}
			break;
			default:
			
			
		}
		
		return returnList;		
		
	}
	
	/* (non-Javadoc)
	 * @see br.imd.BuscaSaude.IServices#getUnidades()
	 */
	public ArrayList<UnidadeSaude> getUnidades() throws RemoteException {
		return this.unidades;
	}

}
