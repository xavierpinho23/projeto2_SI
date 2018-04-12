package trabalho2_si;

import java.util.Scanner;

public class Menu
{
	public static void main(String[] args)
	{
		Loja loja = new Loja();		//Inicializar a Loja
		
		//Criação de 2 Arrays com músicas para adicionar aos Albums
		String musicas1[] = { "Cigaro", "BYOB"};
		String musicas2[] = { "ola", "ole"};
		
		Album album1 = new Album("Toxicity", "SOAD", musicas1, "Rock", 5, 20); 		//Criação de 1 um Album
		Album album2 = new Album("Mesmerize", "SOAD", musicas2, "Metal", 4,30);		//Criação de outro Album
		album1.setID(1);
		album2.setID(2);
		
		Administrador admin = new Administrador();			//Criação do Administrador
		Cliente cliente1 = new Cliente("cliente1", "321");	//Criação de um Cliente
		
		loja.adicionaAlbum(album1); 		//Adicionar o album1 à Lista de Albuns
		loja.adicionaAlbum(album2); 		//Adicionar o album2 à Lista de Albuns
		loja.addClientes(cliente1); 		//Adicionar o cliente1 à Lista de Clientes

		int decisao = 0;
		int escolha = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("=========================\\BEM-VINDO//========================= \n\n");
		
		while (decisao != 1 && decisao != 2 && decisao != 3)
		{
			System.out.println("Escolha 1 para Cliente ou 2 para Administrador ou 3 para sair.");
			
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Escolha 1 para Cliente ou 2 para Administrador ou 3 para sair.");
				input.next();
			}
			decisao = input.nextInt();
			input.nextLine();
			
			while( (decisao!=1 && decisao!=2 && decisao!=3))
			{
				System.out.println("Input não válido. \n");
				break;
			}
			//Cliente
			while (decisao == 1)		
			{
				System.out.println("Escolha 1 para Registo e 2 para LogIn.");
				
				while (!input.hasNextInt() && escolha!=1 && escolha!=2)
				{
					System.out.println("Input Inválido.");
					decisao = 0;
					System.out.println("Escolha 1 para Registo e 2 para LogIn.");
					input.next();
				}
				escolha = input.nextInt();
				input.nextLine();
						
				//Registo
				while (escolha == 1)
				{
					boolean exists = false;
					System.out.println("Introduza o username: ");
					String username = input.nextLine();

					System.out.println("Introduza o password: ");
					String password = input.nextLine();

					for (int i = 0; i < loja.getClientes().size(); i++)
					{
						if (loja.getClientes().get(i).getUsername().equals(username))
						{
							exists = true;
						}
					}
					if (exists)
					{
						System.out.println("O username já foi escolhido.");
						decisao = 0;
					}
					else
					{
						Cliente cliente = new Cliente(username, password);
						loja.addClientes(cliente);

						System.out.println("Registo bem sucedido.");
						LogInCliente(loja, cliente);	
						decisao = 0; 	//Faz com que volte ao Menu Inicial
						escolha = 0;
					}
				}
				boolean existe = false;
				//LogIn
				while (escolha == 2)
				{
					System.out.println("Introduza o username: ");
					String username = input.nextLine();

					System.out.println("Introduza o password: ");
					String password = input.nextLine();

					for (int i = 0; i < loja.getClientes().size(); i++)
					{
						if (loja.getClientes().get(i).getUsername().equals(username) && loja.getClientes().get(i).getPassword().equals(password))
						{
							existe = true;
							Cliente cliente = loja.getClientes().get(i);
							LogInCliente(loja, cliente); //Envia para o Menu do Cliente
							decisao = 0;
							escolha = 0;
							break;
						}
						else
						{
							existe = false;							
						}
					}
					if (!existe)
					{
						System.out.println("O username/password que introduziu estão errados.");
						decisao = 0;
						escolha = 0;
					}
				}
			}
			//Administrador
			while (decisao == 2)
			{
				LogInAdministrador(loja, admin); //Envia para o Menu do Administrador
				decisao = 0; 					 //Faz com que volte ao Menu Inicial
			}
		}
		//input.close();
	}
	//Menu do Administrador
	public static void LogInAdministrador(Loja loja, Administrador admin)
	{
		System.out.println("Bem Vindo à Vinyl Records Lda. Administrador! \n\n");

		int opcao = 0;
		Scanner input = new Scanner(System.in);

		while (opcao != 6)
		{
			System.out.printf("Escolha uma das seguintes opções: \n\n");
			System.out.printf("[1] -> Adicionar um Album \n");
			System.out.printf("[2] -> Remover um Album \n");
			System.out.printf("[3] -> Ver a lista de Albuns \n");
			System.out.printf("[4] -> Mudar o preço de um Album \n");
			System.out.printf("[5] -> Visualizar estatísticas \n");
			System.out.printf("[6] -> Terminar sessão \n");
			
			while(!input.hasNextInt())
			{
				System.out.println("Input Inválido. \n");
				input.next();
				System.out.printf("Escolha uma das seguintes opções: \n\n");
				System.out.printf("[1] -> Adicionar um Album \n");
				System.out.printf("[2] -> Remover um Album \n");
				System.out.printf("[3] -> Ver a lista de Albuns \n");
				System.out.printf("[4] -> Mudar o preço de um Album \n");
				System.out.printf("[5] -> Visualizar estatísticas \n");
				System.out.printf("[6] -> Terminar sessão \n");
			}
			opcao = input.nextInt();
			input.nextLine();
			
			//Adicionar Album
			if (opcao == 1)
			{
				System.out.printf("Introduza o Album que quer adicionar. \n");
				System.out.printf("Nome: \n");
				String nome = input.nextLine();
				System.out.printf("Grupo: ");
				String grupo = input.nextLine();
				System.out.printf("\nQuantas músicas tem o album?: \n");
				
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Quantas músicas tem o album?: \n");
					input.next();
				}
				String Tamanho = input.nextLine();
				int tamanho = Integer.parseInt(Tamanho);
				
				String[] musicas = new String[tamanho];
				for (int i = 0; i < tamanho; i++)
				{
					System.out.println("Música " + (i + 1) + ": \n");
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: \n");
				String genero = input.nextLine();
				System.out.printf("Preço: \n");
				while (!input.hasNextDouble() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Preço: \n");
					input.next();
				}
				double price = input.nextInt();
				System.out.printf("Unidades: \n");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Unidades: \n");
					input.next();
				}
				int unidades = input.nextInt();

				Album album = new Album(nome, grupo, musicas, genero, price, unidades);
				
				admin.addNewAlbum(loja, album); //Verificar se o Album existe e adicioná-lo à Lista de Albuns
			}
			//Remover um Album
			if (opcao == 2)
			{
				System.out.printf("Introduza o Album que quer remover.");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				System.out.printf("Quantidade de unidades que deseja remover?");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Quantidade de unidades que deseja remover? \n");
					input.next();
				}
				String Unidades = input.nextLine();
				int unidades = Integer.parseInt(Unidades);

				Album album = admin.visualizarAlbumNome(loja, nome);

				admin.eliminaAlbum(loja, album, unidades);
			}
			//Ver a Lista de Albuns
			if (opcao == 3)
			{
				admin.listaAlbuns(loja);
			}
			//Mudar o Preço de um Album
			if (opcao == 4)
			{
				System.out.println("Qual o nome do album que pretende alterar o preço?");
				String nome = input.nextLine();
				System.out.println("Que preço deverá custar?");
				while (!input.hasNextDouble() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Preço: \n");
					input.next();
				}				
				
				String Price = input.nextLine();
				double price = Integer.parseInt(Price);

				Album album = admin.visualizarAlbumNome(loja, nome);
				admin.updateAlbumPrice(loja, album, price);
			}
			//Estatísticas
			if (opcao == 5)
			{
				System.out.println("=================\\Estatísticas//================= \n");
				System.out.println("Total de dinheiro gasto na loja: " + loja.dinheiroGastoTotal() + "€.");
				System.out.println(loja.dinheiroGastoGenero());
				System.out.println("Número total de discos em stock: " + loja.albunsStock() + ".");
				System.out.println(loja.albunsStockGenero());

			}
			//Terminar Sessão
			if (opcao == 6)
			{
				return;
			}
			//input.close();
		}
	}
	//Menu do Cliente
	public static void LogInCliente(Loja loja, Cliente cliente)
	{
		int opcao = 0;
		Scanner input = new Scanner(System.in);

		System.out.printf("Bem Vindo à Vinyl Records Lda. cliente %s ! \n\n", cliente.getUsername());

		while (opcao != 9)
		{
			System.out.printf("Escolha uma das seguintes opções: \n\n");
			System.out.printf("[1] -> Ver Lista de Albums \n");
			System.out.printf("[2] -> Procurar Album por nome \n");
			System.out.printf("[3] -> Procurar Album por género \n");
			System.out.printf("[4] -> Procurar Album por grupo \n");
			System.out.printf("[5] -> Procurar Album por música \n");
			System.out.printf("[6] -> Ver dados da conta \n");
			System.out.printf("[7] -> Remover Albums do carrinho \n");
			System.out.printf("[8] -> Finalizar compra \n");
			System.out.printf("[9] -> Terminar sessão \n");

			while(!input.hasNextInt())
			{
				System.out.println("Input Inválido. \n");
				input.next();
				System.out.printf("Escolha uma das seguintes opções: \n\n");
				System.out.printf("[1] -> Ver Lista de Albums \n");
				System.out.printf("[2] -> Procurar Album por nome \n");
				System.out.printf("[3] -> Procurar Album por género \n");
				System.out.printf("[4] -> Procurar Album por grupo \n");
				System.out.printf("[5] -> Procurar Album por música \n");
				System.out.printf("[6] -> Ver dados da conta \n");
				System.out.printf("[7] -> Remover Albums do carrinho \n");
				System.out.printf("[8] -> Finalizar compra \n");
				System.out.printf("[9] -> Terminar sessão \n");
			}
			opcao = input.nextInt();
			input.nextLine();

			
			//Ver a Lista de Albuns
			if (opcao == 1)
			{
				cliente.listaAlbuns(loja);
			}
			//Procurar Album por Nome
			if (opcao == 2)
			{
				System.out.println("Escreva o nome do álbum que pretende procurar:");
				String nome = input.nextLine();

				cliente.visualizarAlbumNome(loja, nome);
			}
			//Procurar Album por Género
			if (opcao == 3)
			{
				System.out.printf("Escreva o género do álbum que pretende procurar:");
				String genero = input.nextLine();

				cliente.visualizarAlbumGenero(loja, genero);
			}
			// Procurar Album por Grupo
			if (opcao == 4)
			{
				System.out.printf("Escreva o nome do grupo que pretende procurar:");
				String grupo = input.nextLine();

				cliente.visualizarAlbumGrupo(loja, grupo);
			}
			//Procurar Album por Música
			if (opcao == 5)
			{
				System.out.println("Introduza uma música do Album que procura:");
				String musica = input.nextLine();

				cliente.visualizarAlbumMusicas(loja, musica);
			}
			//Visualizar Dados de Conta
			if (opcao == 6)
			{
				System.out.println(cliente.toString());
			}
			if (opcao == 7)
			{
				System.out.printf("Introduza o Nome do Album que quer remover.");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				nome = nome.toLowerCase();
				System.out.printf("Quantidade de unidades que deseja remover?");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Quantidade de unidades que deseja remover? \n");
					input.next();
				}
				String Unidades = input.nextLine();
				int unidades =  Integer.parseInt(Unidades); //converter string para inteiro
				
				cliente.retiraAlbum(loja, nome, unidades);
			}
			//Finalizar a Compra
			if (opcao == 8)
			{
				cliente.finalizarCompra(loja);
			}
			//Terminar Sessão
			if (opcao == 9)
			{
				cliente.getCarrinho().clear();
				return;
			}
			//input.close();
		}
	}
}