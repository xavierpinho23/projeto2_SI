package trabalho2_si;

import java.util.Scanner;

public class Menu
{
	
	public static void main(String[] args)
	{
		
	  Loja loja = new Loja();
	  
		String musicas1[] = {"Cigaro", "BYOB"};
	    Album album1 = new Album("Toxicity","SOAD",musicas1,"Rock",5,10);
	    Administrador admin1 = new Administrador("admin1","123"); 
	    Cliente cliente1 = new Cliente("cliente1","321");
	    loja.lista_albuns.add(album1);
	    loja.administradores.add(admin1);
	    loja.clientes.add(cliente1);
	    //loja.lista_albuns_cliente.add(album1);
	  
      
		
		System.out.println("Bem Vindo!");
		
		int opcao = 0;
		int escolha = 0;
		Scanner input = new Scanner(System.in);
		
		while (opcao!=1 && opcao!= 2 && opcao != 3)
		{
			System.out.println("Escolha 1 para Registo ou 2 para LogIn ou 3 para sair.");
			opcao = input.nextInt();
			input.nextLine();
			if (opcao!=1 && opcao!= 2 && opcao != 3)
			{
				System.out.println("Input Inválido.");
			}
		}
		
		while (opcao == 1)
			//Registo
		{
			 System.out.println("Escolha 1 para Administrador e 2 para Cliente.");
			 escolha = input.nextInt();
			 input.nextLine();
			 //While
			 if (escolha == 1)
				 //Registo Administrador
			 {
				 System.out.println("Introduza o username: ");
				 String username = input.nextLine();
				 
				 System.out.println("Introduza o password: ");
				 String password = input.nextLine();
				 

				 for (int i = 0; i < loja.getAdministradores().size() ; i++)
				 {
					 if (loja.getAdministradores().get(i).getUsername().equals(username))
					 {
						 System.out.println("O username já existe.Insira dados novamente.");
					 }
					 else
					 {
						 Administrador admin = new Administrador(username, password);
						 loja.addAdministradores(admin);
						 
						 System.out.println("Registo bem sucedido");
						 escolha = 0;
					 }
				 }
			 }
			 //while
			 if (escolha == 2)
				 //Registo Cliente
			 {
				 System.out.println("Introduza o username: ");
				 String username = input.nextLine();
				 
				 System.out.println("Introduza o password: ");
				 String password = input.nextLine();
				 

				 for (int i = 0; i < loja.getClientes().size() ; i++)
				 {
					 if (loja.getClientes().get(i).getUsername().equals(username))
					 {
						 System.out.println("O username já existe. Insira dados novamente");
					 }
					 else
					 {
						 Cliente cliente = new Cliente(username, password);
						 loja.addClientes(cliente);
						 System.out.println("Registo bem sucedido.");
						 opcao = 0;
					 }
				 }
			 }
			 if (escolha!= 1 || escolha!= 2)
			 {
				 System.out.println("Escolha uma opção válida.");
			}
			 
			 
		 }
		//while
		if (opcao == 2)
			//Log In
		{
			 System.out.println("Introduza o username: ");
			 String username = input.nextLine();
			 
			 System.out.println("Introduza o password: ");
			 String password = input.nextLine();
					 
			 for (int i = 0; i < loja.getClientes().size() ; i++)
			 {
				 if (loja.getClientes().get(i).getUsername().equals(username) && loja.getClientes().get(i).getPassword().equals(password))
				 {
					 System.out.println("Bem vindo cliente: " + username);
					 Cliente cliente = loja.getClientes().get(i);
					 LogInCliente(loja, cliente);
				 }
				 else if (loja.getAdministradores().get(i).getUsername().equals(username) && loja.getAdministradores().get(i).getPassword().equals(password))
				 {
					 System.out.println("Bem vindo administrador: " + username);
					 Administrador admin = loja.getAdministradores().get(i);
					 LogInAdministrador(loja, admin);
				 }
			}
		}		
		
	}	
	public static void LogInAdministrador(Loja loja,Administrador admin)
	{
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Bem Vindo à Vinyl Records Lda. administrador %s ! \n", admin.getUsername());

		while (opcao!=6)
		{
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Adicionar um Album");
			System.out.printf("[2] -> Remover um Album");
			System.out.printf("[3] -> Ver a lista de Albuns");
			System.out.printf("[4] -> Mudar o preço de um Album");
			System.out.printf("[5] -> Visiualizar estatísticas");
			System.out.printf("[6] -> Terminar sessão");
			
			opcao = input.nextInt();
			input.nextLine();
			
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
					System.out.printf("Música " + i+1 + ":");
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: ");
				String genero = input.nextLine();
				System.out.printf("Preço: ");
				int price = input.nextInt();
				System.out.printf("Unidades: ");
				int unidades = input.nextInt();
				
				//Tornar o input num objeto Album
				Album album = new Album(nome,grupo, musicas, genero,price, unidades);
				
				//Verificar se o album existe e adicionar
				admin.addNewAlbum(loja, album);
				
				opcao = 0;
				
			}
			if(opcao == 2)
			//Remover um Album
			{
				System.out.printf("Introduza o Album que quer remover.");
				System.out.printf("Nome: ");
				String nome = input.nextLine();
				System.out.printf("Quantidade de unidades que deseja remover?");
				int unidades = input.nextInt();
				input.nextLine();
				
				Album album = admin.visualizarAlbumNome(loja, nome);

				admin.eliminaAlbum(loja, album, unidades);
			}
			
			if(opcao == 3)
			//Ver a lista de todos os albuns
			{
				admin.listaAlbuns(loja);
			}
			if(opcao == 4)
				//Mudar o preço de um album
			{
				System.out.println("Qual o nome do album que pretende alterar o preço?");
				String nome = input.nextLine();
				System.out.println("Que preço deverá custar?");
				int price = input.nextInt();
				
				Album album = admin.visualizarAlbumNome(loja, nome);
				admin.updateAlbumPrice(loja, album, price);
			}
			if(opcao == 5)
			{
				System.out.println("Estatísticas:");
			}
			
		}
	}

	public static void LogInCliente(Loja loja, Cliente cliente)
	{
		//Aqui é onde o CLIENTE executa as suas ações!
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Bem Vindo à Vinyl Records Lda. cliente %s ! \n", cliente.getUsername());

		while (opcao!=8)
		{
			System.out.printf("Escolha uma das seguintes opções: \n");
			System.out.printf("[1] -> Ver Lista de Albums");
			System.out.printf("[2] -> Procurar Album por nome");
			System.out.printf("[3] -> Procurar Album por género");
			System.out.printf("[4] -> Procurar Album por grupo");
			System.out.printf("[5] -> Procurar Album por música");
			System.out.printf("[6] -> Ver dados da conta");
			System.out.printf("[7] -> Finalizar compra");
			System.out.printf("[8] -> Terminar sessão");
			
			opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			//Ver Lista de Albums
			{
				cliente.listaAlbuns(loja);
			}
			if (opcao == 2)
			//Procurar Album por nome
			{
				System.out.println("Escreva o nome do álbum que pretende procurar.");
				String nome = input.nextLine();
				int j = 0;
				
		        cliente.visualizarAlbumNome(loja, nome);
			}
			if (opcao == 3)
			//Procurar Album por género
			{
				System.out.printf("Escreva o género do álbum que pretende procurar.");
				String genero = input.nextLine();
				
				cliente.visualizarAlbumGenero(loja, genero);
			}
			if (opcao == 4)
			//Procurar Album por grupo
			{
				System.out.printf("Escreva o nome do grupo que pretende procurar.");
				String grupo = input.nextLine();
				
				cliente.visualizarAlbumGrupo(loja, grupo);
			}
			if (opcao == 5)
			//Procurar Album por musica
			{
				System.out.println("Introduza uma música do Album que procura: ");
				String musica = input.nextLine();
				
				cliente.visualizarAlbumMusicas(loja, musica);
			}
			if (opcao == 6)
			//Visualizar dados de conta
			{
				System.out.println(cliente.toString());	
			}
			if (opcao == 7)
			{
				cliente.finalizarCompra(loja);				
			}
		}
		
		input.close();

	}
}