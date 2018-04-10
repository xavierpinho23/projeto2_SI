package trabalho2_si;

import java.util.Scanner;

public class Menu
{

	public static void main(String[] args)
	{

		Loja loja = new Loja();

		String musicas1[] = { "Cigaro", "BYOB" };
		String musicas2[] = { "olaola", "oleole" };

		Album album1 = new Album("Toxicity", "SOAD", musicas1, "Rock", 5, 10);
		Album album2 = new Album("Mesmerize", "SOAD", musicas2, "Metal", 4, 9);
		Administrador admin = new Administrador();
		Cliente cliente1 = new Cliente("cliente1", "321");
		loja.lista_albuns.add(album1);
		loja.lista_albuns.add(album2);
		loja.administradores.add(admin);
		loja.clientes.add(cliente1);
		cliente1.addLista_albuns_cliente(album1);
		cliente1.addLista_albuns_cliente(album2);

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
				System.out.println("Input Inválido.");
			}

			while (decisao == 1)
			// Cliente
			{
				System.out.println("Escolha 1 para Registo e 2 para LogIn.");
				escolha = input.nextInt();
				input.nextLine();

				while (escolha == 1)
				// Registo Cliente
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
						System.out.println("O username já foi escolhido.");
					}
					else
					{
						Cliente cliente = new Cliente(username, password);
						loja.addClientes(cliente);

						System.out.println("Registo bem sucedido");
						LogInCliente(loja, cliente);
						decisao = 0;
						escolha = 0;
					}
				}
				while (escolha == 2)
				// LogIn Cliente
				{
					System.out.println("Introduza o username: ");
					String username = input.nextLine();

					System.out.println("Introduza o password: ");
					String password = input.nextLine();

					for (int i = 0; i < loja.getClientes().size(); i++)
					{
						if (loja.getClientes().get(i).getUsername().equals(username)
								&& loja.getClientes().get(i).getPassword().equals(password))
						{
							System.out.println("Bem vindo cliente: " + username);
							Cliente cliente = loja.getClientes().get(i);
							LogInCliente(loja, cliente);
						}
						else
						{
							System.out.println("O username/password que introduziu estão errados.");
						}
					}
				}
			}
			while (decisao == 2)
			// Administrador
			{
				LogInAdministrador(loja, admin);
				decisao = 0;

			}
		}
		input.close();
	}

	public static void LogInAdministrador(Loja loja, Administrador admin)
	{
		// Menu do Administrador
		int opcao = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("Bem Vindo à Vinyl Records Lda. Administrador! \n");

		while (opcao != 6)
		{
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Adicionar um Album \n");
			System.out.printf("[2] -> Remover um Album \n");
			System.out.printf("[3] -> Ver a lista de Albuns \n");
			System.out.printf("[4] -> Mudar o preço de um Album \n");
			System.out.printf("[5] -> Visiualizar estatísticas \n");
			System.out.printf("[6] -> Terminar sessão \n");

			opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			{
				// Adicionar um Album
				System.out.printf("Introduza o Album que quer adicionar. \n");
				System.out.printf("Nome: \n");
				String nome = input.nextLine();
				System.out.printf("Grupo: ");
				String grupo = input.nextLine();
				System.out.printf("\nQuantas músicas tem o album?: \n");
				int tamanho = input.nextInt();
				input.nextLine();
				String[] musicas = new String[tamanho];
				for (int i = 0; i < tamanho; i++)
				{
					System.out.println("Música " + (i + 1) + ": \n");
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: \n");
				String genero = input.nextLine();
				System.out.printf("Preço: \n");
				int price = input.nextInt();
				System.out.printf("Unidades: \n");
				int unidades = input.nextInt();

				// Tornar o input num objeto Album
				Album album = new Album(nome, grupo, musicas, genero, price, unidades);

				// Verificar se o album existe e adicionar
				admin.addNewAlbum(loja, album);

			}
			if (opcao == 2)
			// Remover um Album
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

			if (opcao == 3)
			// Ver a lista de todos os albuns
			{
				admin.listaAlbuns(loja);
			}
			if (opcao == 4)
			// Mudar o preço de um album
			{
				System.out.println("Qual o nome do album que pretende alterar o preço?");
				String nome = input.nextLine();
				System.out.println("Que preço deverá custar?");
				int price = input.nextInt();

				Album album = admin.visualizarAlbumNome(loja, nome);
				admin.updateAlbumPrice(loja, album, price);
			}
			if (opcao == 5)
			{
				System.out.println("Estatísticas: \n");
				System.out.println("Total de dinheiro gasto na loja: " + loja.dinheiroGastoTotal() + "€.\n");
				System.out.println("Número total de discos em stock: " + loja.albunsStock() + ". \n");
				loja.albunsStockGenero();
				loja.dinheiroGastoGenero();

			}
			if (opcao == 6)
			{
				return;
			}
			input.close();
		}

	}

	public static void LogInCliente(Loja loja, Cliente cliente)
	{
		// Menu do Cliente
		int opcao = 0;
		Scanner input = new Scanner(System.in);

		System.out.printf("Bem Vindo à Vinyl Records Lda. cliente %s ! \n", cliente.getUsername());

		while (opcao != 8)
		{
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Ver Lista de Albums \n");
			System.out.printf("[2] -> Procurar Album por nome \n");
			System.out.printf("[3] -> Procurar Album por género \n");
			System.out.printf("[4] -> Procurar Album por grupo \n");
			System.out.printf("[5] -> Procurar Album por música \n");
			System.out.printf("[6] -> Ver dados da conta \n");
			System.out.printf("[7] -> Finalizar compra \n");
			System.out.printf("[8] -> Terminar sessão \n");

			opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			// Ver Lista de Albums
			{
				cliente.listaAlbuns(loja);
			}
			if (opcao == 2)
			// Procurar Album por nome
			{
				System.out.println("Escreva o nome do álbum que pretende procurar:");
				String nome = input.nextLine();

				cliente.visualizarAlbumNome(loja, nome);
			}
			if (opcao == 3)
			// Procurar Album por género
			{
				System.out.printf("Escreva o género do álbum que pretende procurar:");
				String genero = input.nextLine();

				cliente.visualizarAlbumGenero(loja, genero);
			}
			if (opcao == 4)
			// Procurar Album por grupo
			{
				System.out.printf("Escreva o nome do grupo que pretende procurar:");
				String grupo = input.nextLine();

				cliente.visualizarAlbumGrupo(loja, grupo);
			}
			if (opcao == 5)
			// Procurar Album por musica
			{
				System.out.println("Introduza uma música do Album que procura:");
				String musica = input.nextLine();

				cliente.visualizarAlbumMusicas(loja, musica);
			}
			if (opcao == 6)
			// Visualizar dados de conta
			{
				System.out.println(cliente.toString());
			}
			if (opcao == 7)
			{
				cliente.finalizarCompra(loja);
			}
			if (opcao == 8)
			{
				return;
			}
			input.close();
		}
	}

}
