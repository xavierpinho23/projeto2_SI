package trabalho2_si;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
	
	public void main(String[] args)
	{
		Loja loja = new Loja();
		
		System.out.println("Bem Vindo!");
		
		int opcao = 0;
		int escolha = 0;
		Scanner input = new Scanner(System.in);
		
		while (opcao!=1 || opcao!= 2 || opcao != 3)
		{
			System.out.println("Escolha 1 para Registo ou 2 para LogIn ou 3 para sair.");
			opcao = input.nextInt();
			input.nextLine();
			if (opcao!=1 || opcao!= 2 || opcao != 3)
			{
				System.out.println("Input Inv�lido.");
			}
		}
		
		while (opcao == 1)
			//Registo
		{
			 System.out.println("Escolha 1 para Administrador e 2 para Cliente.");
			 escolha = input.nextInt();
			 input.nextLine();
			 
			 while (escolha == 1)
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
						 System.out.println("O username j� existe.Insira dados novamente.");
					 }
					 else
					 {
						 Administrador admin = new Administrador(username, password);
						 loja.addAdministradores(admin);
						 
						 System.out.println("Registo bem sucedido");
						 opcao = 0;
					 }
				 }
			 }
			 while (escolha == 2)
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
						 System.out.println("O username j� existe. Insira dados novamente");
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
				 System.out.println("Escolha uma op��o v�lida.");
			}
			 
			 
		 }
		while (opcao == 2)
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
	public void LogInAdministrador(Loja loja,Administrador admin)
	{
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Bem Vindo � Vinyl Records Lda. administrador %s ! \n", admin.getUsername());

		while (opcao!=6)
		{
			System.out.printf("Escolha uma das seguintes op��es: \n");
			System.out.printf("[1] -> Adicionar um Album");
			System.out.printf("[2] -> Remover um Album");
			System.out.printf("[3] -> Ver a lista de Albuns");
			System.out.printf("[4] -> Mudar o pre�o de um Album");
			System.out.printf("[5] -> Visiualizar estat�sticas");
			System.out.printf("[6] -> Terminar sess�o");
			
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
				System.out.printf("Quantas m�sicas tem o album?: ");
				int tamanho = input.nextInt();
				String[] musicas = new String[tamanho];
				for (int i = 0; i < musicas.length; i++)
				{
					System.out.printf("M�sica " + i+1 + ":");
					musicas[i] = input.nextLine();
				}
				System.out.printf("Genero: ");
				String genero = input.nextLine();
				System.out.printf("Pre�o: ");
				int price = input.nextInt();
				System.out.printf("Unidades: ");
				int unidades = input.nextInt();
				
				//Tornar o input num objeto Album
				Album album = new Album(loja, nome,grupo, musicas, genero,price, unidades);
				
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
				//Mudar o pre�o de um album
			{
				System.out.println("Qual o nome do album que pretende alterar o pre�o?");
				String nome = input.nextLine();
				System.out.println("Que pre�o dever� custar?");
				int price = input.nextInt();
				
				Album album = admin.visualizarAlbumNome(loja, nome);
				admin.updateAlbumPrice(loja, album, price);
			}
			if(opcao == 5)
			{
				System.out.println("Estat�sticas:");
			}
			
		}
	}

	public void LogInCliente(Loja loja, Cliente cliente)
	{
		//Aqui � onde o CLIENTE executa as suas a��es!
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Bem Vindo � Vinyl Records Lda. cliente %s ! \n", cliente.getUsername());

		while (opcao!=6)
		{
			System.out.printf("Escolha uma das seguintes op��es: \n");
			System.out.printf("[1] -> Ver Lista de Albums");
			System.out.printf("[2] -> Procurar Album por nome");
			System.out.printf("[3] -> Procurar Album por g�nero");
			System.out.printf("[4] -> Procurar Album por grupo");
			System.out.printf("[5] -> Ver dados da conta");
			System.out.printf("[6] -> Finalizar compra");
			System.out.printf("[7] -> Terminar sess�o");

			if (opcao == 1)
			//Comprar album
			{
				System.out.println("Que album pretende comprar?");
				String nome = input.nextLine();
				
				Album album = cliente.visualizarAlbumNome(loja, nome);
				
			}
			if (opcao == 2)
			//Ver saldo
			{
				System.out.printf("O seu saldo �: " + cliente.getSaldo());
			}
			if (opcao == 3)
			//Ver a lista de todos os albuns
			{
				System.out.printf("Lista de Albuns: \n", loja.getLista_albuns());
			}
			if (opcao == 4)
			//Ver lista de albuns pessoal
			{
				System.out.printf("Lista de Albuns pessoal: \n", cliente.getLista_albuns_cliente());

			}
			if (opcao == 5)
			//Procurar album por nome
			{
				System.out.println("Introduza o nome do Album que procura: ");
				String nome = input.nextLine();
				cliente.visualizarAlbumNome( loja,  nome);
				
			}
			if (opcao == 6)
			//Procurar album por g�nerp
			{
				System.out.printf("Introduza o g�nero do Album que procura: ");
				String genero = input.nextLine();
				cliente.visualizarAlbumGenero(loja,genero);				
			}
			if (opcao == 7)
			//Procurar album por banda
			{
				System.out.printf("Introduza o grupo do Album que procura: ");
				String grupo = input.nextLine();
				cliente.visualizarAlbumGrupo(loja, grupo);
			}
			
		}
		
		input.close();

	}
}