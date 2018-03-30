package trabalho2_si;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
	
	public void LogInAdministrador(Administrador admin,ArrayList<Administrador> administradores, ArrayList<Album> lista_albuns)
	{
		//Aqui é onde o ADMIN executa as suas ações! 
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		while (opcao!=6)
		{
			System.out.printf("Bem Vindo à Vinyl Records Lda. administrador %s ! \n", admin.getUsername());
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Adicionar um Album");
			System.out.printf("[2] -> Remover um Album");
			System.out.printf("[3] -> Ver a lista de Albuns");
			System.out.printf("[4] -> Mudar o preço de um Album");
			System.out.printf("[5] -> Visiualizar estatísticas");
			System.out.printf("[6] -> Terminar sessão");
			
			if(opcao == 1)
			{
				//Adicionar um Album
				System.out.printf("Introduza o Album que quer adicionar.");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				System.out.printf("Grupo: ");
				String grupo = input.nextLine();
				System.out.printf("Quantas músicas tem o album?: ");
				int tamanho = input.nextInt();
				String[] musicas = new String[tamanho];
				for (int i = 0; i < musicas.length; i++)
				{
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: ");
				String genero = input.nextLine();
				System.out.printf("Preço: ");
				int price = input.nextInt();
				System.out.printf("Unidades: ");
				int unidades = input.nextInt();
				boolean disponivel = true;
				//Tornar o input num objeto Album
				Album album = new Album(nome,grupo, musicas, genero,price, unidades, disponivel);
				
				//Verificar se o album já existe
				if (admin.visualizarAlbumNome(lista_albuns, nome) != null)
				{
					System.out.printf("O album %s já existe!", nome);
					continue;
				}
				else
				{
					//Caso não exista, é adicionado à lista de albuns
					admin.addNewAlbum(lista_albuns, nome, grupo, musicas, genero, price, unidades, disponivel);
					System.out.println("Album adicionado com sucesso!");
				}
				
			}
			if(opcao == 2)
			{
				//Remover um Album
				//Apenas é necessario o nome para remover o album (?)
				System.out.printf("Introduza o Album que quer remover.");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				/*System.out.printf("Grupo: ");
				String grupo = input.nextLine();
				System.out.printf("Quantas músicas tem o album?: ");
				int tamanho = input.nextInt();
				String[] musicas = new String[tamanho];
				for (int i = 0; i < musicas.length; i++)
				{
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: ");
				String genero = input.nextLine();
				System.out.printf("Preço: ");
				int price = input.nextInt();
				System.out.printf("Unidades: ");
				int unidades = input.nextInt();*/
				
				//Verificar se o album existe
				if (admin.visualizarAlbumNome(lista_albuns, nome) != null)
				{
					boolean disponivel = true;
					//Em vez de null tem de ser album, mas nao está a dar :/
					admin.removeAlbum(lista_albuns , null, nome);
				}
				else
				{
					System.out.printf("O Album %s que pretende remover não existe.", nome);
				}
				
			}
			if(opcao == 3)
			{
				//Ver a lista de albuns
				admin.listaAlbuns(lista_albuns);
			}
			}
			if(opcao == 4)
			{
				//Mudar o preço de um album
				System.out.printf("Qual o album que pretende alterar o preço?");
				String nome = input.nextLine();
				System.out.printf("Que preço deverá custar?");
				int price = input.nextInt();
				//Em vez de null tem de ser album, mas nao está a dar :/
				admin.updateAlbumPrice(null, nome, price);
			}
			if(opcao == 5)
			{
				;
			}
			
		}

	
	public void LogInCliente(Cliente cliente, ArrayList<Cliente> clientes, ArrayList<Album> lista_albuns, ArrayList<Album> lista_albuns_cliente)
	{
		//Aqui é onde o CLIENTE executa as suas ações!
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		while (opcao!=8)
		{
			System.out.printf("Bem Vindo à Vinyl Records Lda. cliente %s ! \n", cliente.getUsername());
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Comprar um Album");
			System.out.printf("[2] -> Ver saldo");
			System.out.printf("[3] -> Ver a lista de Albuns");
			System.out.printf("[4] -> Ver lista de Albuns pessoal");
			System.out.printf("[5] -> Procurar Album por nome");
			System.out.printf("[6] -> Procurar Album por género");
			System.out.printf("[7] -> Procurar Album por grupo");
			System.out.printf("[8] -> Terminar sessão");

			if (opcao == 1)
			{
				//Ver se o album existe
				System.out.println("Que album pretende comprar?");
				String nome = input.nextLine();
				if (cliente.findAlbunsName(nome) != null)
				{
					cliente.compraAlbum(lista_albuns,null, nome);
					cliente.lista_albuns_cliente.add(null);

					//tambem tem de se retirar o album à lista de albuns
				}
			}
			if (opcao == 2)
			{
				System.out.printf("O seu saldo é: " + cliente.getSaldo());
			}
			if (opcao == 3)
			{
				System.out.printf("Lista de Albuns: \n", cliente.getLista_albuns());
			}
			if (opcao == 4)
			{
				System.out.printf("Lista de Albuns pessoal: \n", cliente.getLista_albuns_cliente());

			}
			if (opcao == 5)
			{
				System.out.printf("Introduza o nome do Album que procura: ");
				String nome = input.nextLine();
				cliente.findAlbunsName(nome);
				
			}
			if (opcao == 6)
			{
				System.out.printf("Introduza o género do Album que procura: ");
				String genero = input.nextLine();
				cliente.findAlbunsGenero(genero);				
			}
			if (opcao == 7)
			{
				System.out.printf("Introduza o grupo do Album que procura: ");
				String grupo = input.nextLine();
				cliente.findAlbunsGroup(grupo);
			}
			
		}
		
		input.close();

	}
}
