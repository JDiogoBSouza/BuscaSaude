package br.imd.BuscaSaude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe UnidadeSaude
 * Responsável por representar uma unidade de saúde.
 */

@SuppressWarnings("serial")
public class UnidadeSaude implements Serializable {
	
	private String nome;
	private String endereco;
	private String bairro;
	private int id;
	private ArrayList<String> especialidades;

	
	/**
	 * Método Construtor Com todos os parametros da classe.
	 *
	 * @param nome: Nome da unidade de saúde.
	 * @param endereco: Endereço da unidade de saúde.
	 * @param bairro: Bairro da unidade de saúde.
	 * @param id: id da unidade de saúde.
	 * @param especialidades: Especialidades presentes na unidade de saúde.
	 */
	public UnidadeSaude(String nome, String endereco, String bairro, int id, ArrayList<String> especialidades) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.id = id;
		this.especialidades = especialidades;
	}
	
	
	/**
	 * Método Construtor sem ID da classe.
	 *
	 * @param nome: Nome da unidade de saúde.
	 * @param endereco: Endereço da unidade de saúde.
	 * @param bairro: Bairro da unidade de saúde.
	 * @param id: id da unidade de saúde.
	 * @param especialidades: Especialidades presentes na unidade de saúde.
	 */
	public UnidadeSaude(String nome, String endereco, String bairro, ArrayList<String> especialidades) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.especialidades = especialidades;
	}
	
	/**
	 * Método Construtor Padrão da classe.
	 *
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
	 * Método get do atributo nome
	 *
	 * @return Retorna o valor atual do atributo nome.
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
	/**
	 * Método get do atributo endereco
	 *
	 * @return Retorna o valor atual do atributo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}


	/**
	 * Método set do atributo endereco.
	 *
	 * @param endereco: Novo valor do atributo endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	/**
	 * Método get do atributo bairro.
	 *
	 * @return Retorna o valor atual do atributo bairro.
	 */
	public String getBairro() {
		return bairro;
	}


	/**
	/**
	 * Método get do atributo id
	 *
	 * @return Retorna o valor atual do atributo id.
	 */
	public int getId() {
		return id;
	}


	/**
	 * Método set do atributo id.
	 *
	 * @param endereco: Novo valor do atributo id.
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Método set do atributo bairro.
	 *
	 * @param endereco: Novo valor do atributo bairro.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Método get do atributo especialidades.
	 *
	 * @return Retorna o valor atual do atributo especialidades.
	 */
	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}


	/**
	 * Método set do atributo especialidades.
	 *
	 * @param endereco: Novo valor do atributo especialidades.
	 */
	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}
	
	/**
	 * Método para adicionar uma nova especialidade à unidade de saúde.
	 *
	 * @param especialidade: Nova especialidade a ser inserida no atributo ArrayList especialidades.
	 */
	public void addEspecialidade(String especialidade){
		especialidades.add(especialidade);
	}
	
	/**
	 * Método para juntar todas as informações da unidade de saúde em uma string.
	 *
	 * @return Retorna uma string contendo todos os atributos da classe.
	 */
	@Override
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
