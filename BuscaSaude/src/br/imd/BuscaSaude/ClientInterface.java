package br.imd.BuscaSaude;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientInterface.
 */
public class ClientInterface 
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws RemoteException the remote exception
	 * @throws NotBoundException the not bound exception
	 * @throws MalformedURLException the malformed URL exception
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
	
		/* Busca no modulo de comunicacao remota (RMI Registry).
		 * Retorna-se uma referencia de objeto para o stub de servidor, 
		 * atraves do qual e possivel realizar a invocacao de metodos remotos */
		
		IServices stub = (IServices) Naming.lookup("rmi://localhost/Services");
	
		// Opcao escolhida pelo usuario
		Scanner input = new Scanner(System.in);
		int choice = 0;
		
		int a, b; 		// Argumentos aos metodos a serem invocados
		int result; 	// Resultado da operacao
		int id;
		
		try{
			
			// Interacao com o usuario, que escolhe a operacao a ser executada
			while (true){
				System.out.print("\nBusca Saude");
				System.out.print("\n\n1-Cadastrar\n2-Atualizar\n3-Excluir\n4-Buscar\n5-Sair");
				System.out.print("\n\nEscolha uma opcao: ");
				
				choice = input.nextInt();
				input.nextLine(); 
				
				switch (choice){
				
					case 1: // Cadastrar
						System.out.print("\nCadastrar Unidade de Saude");
						
						String dados;
						
						// Guardar informacoes dos atributos da Unidade de Saude
						
						stub.cadastrar( criarUnidade(0) );
						System.out.println("Unidade de Saude Inserida com sucesso ");
						
						
						break;
						
					case 2: // Atualizar
						System.out.print("\nAtualizar Unidade de Saude");
						
						// Imprimir Unidades de Saude disponiveis
						ArrayList<UnidadeSaude> UnidadesDisponiveis = stub.getUnidades();
						imprimirLista(UnidadesDisponiveis);
						
						// Escolher uma das opcoes
						
						System.out.println("Digite o ID da Unidade que deseja atualizar:");

						id = input.nextInt();
						input.nextLine();
						
						// Realizar alteracao
						stub.atualizar( criarUnidade(id) );
						
						break;
						
					case 3: // Excluir
						System.out.print("\nExcluir Unidade de Saude");
						System.out.print("\nDigite as informacoes da Unidade: ");

						// Listar Unidades de Saude disponiveis
						imprimirLista( stub.getUnidades() );
						
						// Escolher uma das opcoes
						
						System.out.println("Digite o ID da Unidade que deseja excluir:");

						id = input.nextInt();
						input.nextLine();
						
						// Realizar Exclusao
						stub.excluir(id );
						
						
						break;
						
					case 4: // Buscar
						
						int criterio = 1;
						
						while( criterio != 6 ){
							
							System.out.print("\nBuscar Unidades de Saude");

							// Exibir opcoes de criterios de busca
							System.out.print("\n\nCriterios de Busca");
							System.out.print("\n1-Nome\n2-Endereco\n3-Bairro\n4-Especialidades\n5-Listar Todos\n6-Voltar");
							System.out.print("\n\nEscolha um criterio de busca: ");

							// Escolher uma das opcoes
							
							criterio = input.nextInt();
							input.nextLine(); 
							String busca;
							ArrayList<UnidadeSaude> resultadoBusca;
							
							switch( criterio ){
							
								case 1: // Busca por nome
									
									System.out.println("Busca por Nome.");
									
									System.out.print("\nDigite o Nome: ");
									busca = input.nextLine();
									
									resultadoBusca = stub.buscar(1, busca);
									System.out.println("Resultado da Busca: ");
									imprimirLista(resultadoBusca);
									
								break;
								
								case 2: // Busca por endereco
									System.out.println("Busca por Endereco.");

									System.out.print("\nDigite o Endereco: ");
									busca = input.nextLine();
									
									resultadoBusca = stub.buscar(2, busca);
									System.out.println("Resultado da Busca: ");
									imprimirLista(resultadoBusca);
								break;
								
								case 3: // Busca por bairro
									System.out.println("Busca por Bairro.");	

									System.out.print("\nDigite o Bairro: ");
									busca = input.nextLine();
									
									resultadoBusca = stub.buscar(3, busca);	
									
									System.out.println("Resultado da Busca: ");
									imprimirLista(resultadoBusca);					
								break;

								case 4: // Busca por especialidade
									System.out.println("Busca por Especialidades.");

									System.out.print("\nDigite a Especialidade: ");
									busca = input.nextLine();
									
									resultadoBusca = stub.buscar(4, busca);
									System.out.println("Resultado da Busca: ");
									imprimirLista(resultadoBusca);
								break;
								
								case 5: // Listar Todos
									System.out.println("Unidades de Saude Cadastradas: ");
									imprimirLista( stub.getUnidades() );
								break;
								
								case 6:	// Sair
									System.out.println("Voltando para o menu inicial.");
								break;
								
								default:
									System.out.println("Opcao invalida. Por favor, tente novamente.");
							}
							
						}
				break;
							
				case 5:	// Sair
							System.out.println("O programa sera encerrado.");
						return;
						
						default:
							System.out.println("Opcao invalida. Por favor, tente novamente.");
				}						
			}
		}
		catch (Exception e){
			
			e.printStackTrace();
		}
		
		input.close();
	}
	
	/**
	 * Imprimir lista.
	 *
	 * @param lista the lista
	 */
	static void imprimirLista(ArrayList<UnidadeSaude> lista){
		
		if( lista.isEmpty() ) {
			//System.out.println("Lista Vazia");
			return;
		}
		
		Iterator<UnidadeSaude> inter1 = lista.iterator();
		
		while (inter1.hasNext()) {
			
			System.out.println( inter1.next().toString() );
			
		}
	}
	
	/**
	 * Criar unidade.
	 *
	 * @param id the id
	 * @return the unidade saude
	 */
	static UnidadeSaude criarUnidade(int id) {
		
		UnidadeSaude unidade = new UnidadeSaude();

		Scanner input = new Scanner(System.in);
		String dados;
		
		unidade.setId(id);
		
		System.out.print("\nDigite as informacoes da Unidade: ");
		
		System.out.print("\nNome: ");
		dados = input.nextLine();
		unidade.setNome(dados);
		
		System.out.print("Endereco: ");
		dados = input.nextLine();
		unidade.setEndereco(dados);
		
		System.out.print("Bairro: ");
		dados = input.nextLine();
		unidade.setBairro(dados);
								
		while( true ){
			
			System.out.print("Especialidade: ");
			dados = input.nextLine();
			unidade.addEspecialidade(dados);

			System.out.println("\nDeseja adicionar mais uma especialidade ?");
			System.out.println("\n1- Sim\n2- Nao");

			int escolha = input.nextInt();
			input.nextLine(); 
			
			if( escolha != 1 ){
				
				break;
			}
			
		}
		
		return unidade;
	}
}
