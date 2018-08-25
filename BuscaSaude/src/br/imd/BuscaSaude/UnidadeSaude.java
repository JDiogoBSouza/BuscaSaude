package br.imd.BuscaSaude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class UnidadeSaude.
 */
public class UnidadeSaude implements Serializable {
	
	/** The nome. */
	private String nome;
	
	/** The endereco. */
	private String endereco;
	
	/** The bairro. */
	private String bairro;
	
	/** The id. */
	private int id;
	
	/** The especialidades. */
	private ArrayList<String> especialidades;

	
	/**
	 * Instantiates a new unidade saude.
	 *
	 * @param nome the nome
	 * @param endereco the endereco
	 * @param bairro the bairro
	 * @param id the id
	 * @param especialidades the especialidades
	 */
	public UnidadeSaude(String nome, String endereco, String bairro, int id, ArrayList<String> especialidades) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.id = id;
		this.especialidades = especialidades;
	}
	
	/**
	 * Instantiates a new unidade saude.
	 */
	public UnidadeSaude()
	{
		this.nome = "";
		this.endereco = "";
		this.bairro = "";
		this.id = -1;
		this.especialidades = new ArrayList<String>();
	}

	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}



	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}


	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the especialidades.
	 *
	 * @return the especialidades
	 */
	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}


	/**
	 * Sets the especialidades.
	 *
	 * @param especialidades the new especialidades
	 */
	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}
	
	/**
	 * Adds the especialidade.
	 *
	 * @param especialidade the especialidade
	 */
	public void addEspecialidade(String especialidade){
		especialidades.add(especialidade);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		String atributos = "";

		atributos += "ID: " + this.id + "\n";
		atributos += "Nome: " + this.nome + "\n";
		atributos += "Endereco: " + this.endereco + "\n";
		atributos += "Bairro: " + this.bairro + "\n";
		atributos += "Especialidades: ";
		
		Iterator<String> inter1 = especialidades.iterator();
		
		boolean primeiro = true;
		
		while (inter1.hasNext()){
			
			if( primeiro ){
				atributos += inter1.next();
				primeiro = false;
			}
			else
				atributos +=  ", " + inter1.next();
			
		}
		
		return atributos;
	}

}
