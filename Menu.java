package trabalho2_si;

import java.util.Scanner;

public class Menu
{
	public static void main(String[] args)
	{
		Loja loja = new Loja();		//Inicialiar a Loja
		
		//Cria��o de 2 Arrays com m�sicas para adicionar aos Albums
		String musicas1[] = { "Cigaro", "BYOB" };
		String musicas2[] = { "olaola", "oleole" };
		
		Album album1 = new Album("Toxicity", "SOAD", musicas1, "Rock", 5, 10); 		//Cria��o de 1 um Album
		Album album2 = new Album("Mesmerize", "SOAD", musicas2, "Metal", 4, 9);		//Cria��o de outro Album
		
		Administrador admin = new Administrador();			//Cria��o do Administrador
		Cliente cliente1 = new Cliente("cliente1", "321");	//Cria��o de um Cliente
		
		loja.listaAlbuns.add(album1); 		//Adicionar o album1 � Lista de Albuns
		loja.listaAlbuns.add(album2); 		//Adicionar o album2 � Lista de Albuns
		loja.administradores.add(admin);	//Adicionar o Administrador � Lista de Administradores
		loja.clientes.add(cliente1);		//Adicionar o cliente1 � Lista de Clientes

		int decisao = 0;
		int escolha = 0;
		
		Scanner input = new Scanner(System.in);

		while (decisao != 1 && decisao != 2 && decisao != 3)
		{
			System.out.println("Bem Vindo! \n");
			System.out.println("Escolha 1 para Cliente ou 2 para Administrador ou 3 para sair.");
			decisao = input.nextInt();
			input.nextLine();
			if (decisao != 1 && decisao != 2 && decisao != 3)
			{
				System.out.println("Input Inv�lido.");
			}
			
			//Cliente
			while (decisao == 1)		
			{
				System.out.println("Escolha 1 para Registo e 2 para LogIn.");
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

					for (int i = 0; i < loja.getAdministradores().size(); i++)
					{
						if (loja.getClientes().get(i).getUsername().equals(username))
						{
							exists = true;
						}
					}
					if (exists)
					{
						System.out.println("O username j� foi escolhido.");
					}
					else
					{
						Cliente cliente = new Cliente(username, password);
						loja.addClientes(cliente);

						System.out.println("Registo bem sucedido");
						LogInCliente(loja, cliente);	
						decisao = 0; 	//Faz com que volte ao Menu Inicial
						escolha = 0;
					}
				}
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
							System.out.println("Bem vindo cliente: " + username);
							Cliente cliente = loja.getClientes().get(i);
							LogInCliente(loja, cliente); //Envia para o Menu do Cliente
						}
						else
						{
							System.out.println("O username/password que introduziu est�o errados.");
						}
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
		input.close();
	}
	//Menu do Administrador
	public static void LogInAdministrador(Loja loja, Administrador admin)
	{
		int opcao = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("Bem Vindo � Vinyl Records Lda. Administrador! \n");

		while (opcao != 6)
		{
			System.out.printf("Escolha uma das seguintes op��es: \n");
			System.out.printf("[1] -> Adicionar um Album \n");
			System.out.printf("[2] -> Remover um Album \n");
			System.out.printf("[3] -> Ver a lista de Albuns \n");
			System.out.printf("[4] -> Mudar o pre�o de um Album \n");
			System.out.printf("[5] -> Visiualizar estat�sticas \n");
			System.out.printf("[6] -> Terminar sess�o \n");

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
				System.out.printf("\nQuantas m�sicas tem o album?: \n");
				int tamanho = input.nextInt();
				input.nextLine();
				String[] musicas = new String[tamanho];
				for (int i = 0; i < tamanho; i++)
				{
					System.out.println("M�sica " + (i + 1) + ": \n");
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: \n");
				String genero = input.nextLine();
				System.out.printf("Pre�o: \n");
				int price = input.nextInt();
				System.out.printf("Unidades: \n");
				int unidades = input.nextInt();

				Album album = new Album(nome, grupo, musicas, genero, price, unidades);
				
				admin.addNewAlbum(loja, album); //Verificar se o Album existe e adicion�-lo � Lista de Albuns
			}
			//Remover um Album
			if (opcao == 2)
			{
				System.out.printf("Introduza o Album que quer remover. \n");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				System.out.printf("Quantidade de unidades que deseja remover? \n");
				int unidades = input.nextInt();
				input.nextLine();

				Album album = admin.visualizarAlbumNome(loja, nome);

				admin.eliminaAlbum(loja, album, unidades);
			}
			//Ver a Lista de Albuns
			if (opcao == 3)
			{
				admin.listaAlbuns(loja);
			}
			//Mudar o Pre�o de um Album
			if (opcao == 4)
			{
				System.out.println("Qual o nome do album que pretende alterar o pre�o?");
				String nome = input.nextLine();
				System.out.println("Que pre�o dever� custar?");
				int price = input.nextInt();

				Album album = admin.visualizarAlbumNome(loja, nome);
				admin.updateAlbumPrice(loja, album, price);
			}
			//Estat�sticas
			if (opcao == 5)
			{
				System.out.println("Estat�sticas: \n");
				System.out.println("Total de dinheiro gasto na loja: " + loja.dinheiroGastoTotal() + "�.\n");
				System.out.println("N�mero total de discos em stock: " + loja.albunsStock() + ". \n");
				loja.albunsStockGenero();
				loja.dinheiroGastoGenero();

			}
			//Terminar Sess�o
			if (opcao == 6)
			{
				return;
			}
			input.close();
		}
	}
	//Menu do Cliente
	public static void LogInCliente(Loja loja, Cliente cliente)
	{
		int opcao = 0;
		Scanner input = new Scanner(System.in);

		System.out.printf("Bem Vindo � Vinyl Records Lda. cliente %s ! \n", cliente.getUsername());

		while (opcao != 8)
		{
			System.out.printf("Escolha uma das seguintes op��es: \n");
			System.out.printf("[1] -> Ver Lista de Albums \n");
			System.out.printf("[2] -> Procurar Album por nome \n");
			System.out.printf("[3] -> Procurar Album por g�nero \n");
			System.out.printf("[4] -> Procurar Album por grupo \n");
			System.out.printf("[5] -> Procurar Album por m�sica \n");
			System.out.printf("[6] -> Ver dados da conta \n");
			System.out.printf("[7] -> Finalizar compra \n");
			System.out.printf("[8] -> Terminar sess�o \n");

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
				System.out.println("Escreva o nome do �lbum que pretende procurar:");
				String nome = input.nextLine();

				cliente.visualizarAlbumNome(loja, nome);
			}
			//Procurar Album por G�nero
			if (opcao == 3)
			{
				System.out.printf("Escreva o g�nero do �lbum que pretende procurar:");
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
			//Procurar Album por M�sica
			if (opcao == 5)
			{
				System.out.println("Introduza uma m�sica do Album que procura:");
				String musica = input.nextLine();

				cliente.visualizarAlbumMusicas(loja, musica);
			}
			//Visualizar Dados de Conta
			if (opcao == 6)
			{
				System.out.println(cliente.toString());
			}
			//Finalizar a Compra
			if (opcao == 7)
			{
				cliente.finalizarCompra(loja);
			}
			//Terminar Sess�o
			if (opcao == 8)
			{
				return;
			}
			input.close();
		}
	}
}
