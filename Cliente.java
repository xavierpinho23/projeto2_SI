package trabalho2_si;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cliente
{
	private String username;
	private String password;
	private double saldo;
	private double divida;
	private ArrayList<Album> lista_albuns_cliente = new ArrayList<Album>(); //Lista de Albuns comprador pelo Cliente
	private ArrayList<Album> carrinho = new ArrayList<Album>(); 		    //Carrinho de Compras do Cliente
	
	Scanner input = new Scanner(System.in);

	//Construtor para definir as propriedades do Cliente
	public Cliente(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.saldo = 100;
		this.divida = 0;
	}
	//Método to get para aceder ao Carrinho do Cliente
	public ArrayList<Album> getCarrinho()
	{
		return carrinho;
	}
	//Método para adicionar um Album ao Carrinho de Compras
	public void addCarrinho(Album album)
	{
		this.carrinho.add(album);
	}
	//Método para remover um Album ao Carrinho de Compras
	public void removeCarrinho(Album album)
	{
		for (int i = 0; i < carrinho.size(); i++)
		{
			if (carrinho.get(i).equals(album))
			{
				carrinho.remove(i);
			}
		}
	}
	//Método to get para aceder à Lista de Albuns da Loja
	public ArrayList<Album> getLista_albuns(Loja loja)
	{
		return loja.getListaAlbuns();
	}
	//Método to get para aceder à Lista de Albuns do Cliente
	public ArrayList<Album> getLista_albuns_cliente()
	{
		return lista_albuns_cliente;
	}
	//Método para adicionar um Album à Lista de Albuns do Cliente
	public void addLista_albuns_cliente(Album album)
	{
		this.lista_albuns_cliente.add(album);
	}
	//Método to get para aceder ao Username do Cliente
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	//Método to get para aceder à Password do Cliente
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	//Método to get para aceder ao Saldo do Cliente
	public double getSaldo()
	{
		return saldo;
	}
	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}
	//Método para adicionar Albums ao Carrinho
	public void adicionaAlbum(Loja loja, Album album, int unidades)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).equals(album))
			{
				if (album.getUnidadesDisponiveis() >= unidades)
				{
					album.setUnidadesCarrinho(unidades);
					addCarrinho(album);
					System.out.println("Foram adicionadas " + unidades + " ao carrinho");
				}
				else
				{
					System.out.println("Não é possível adicionar o album ao carrinho.");
				}
			}
		}
	}
	//Método para retirar Albums ao Carrinho
		public void retiraAlbum(Loja loja, String nome, int unidades)
		{
			boolean existe = false;
			
			if (getCarrinho().isEmpty())
			{
				System.out.println("O Carrinho encontra-se vazio.");
				return;
			}
			for (int i = 0; i < getCarrinho().size(); i++)
			{
			    if (getCarrinho().get(i).getNome().toLowerCase().equals(nome))
				{
			    	existe = true;
					if (getCarrinho().get(i).getUnidadesCarrinho() > unidades)
					{
						getCarrinho().get(i).setUnidadesCarrinho(getCarrinho().get(i).getUnidadesCarrinho() - unidades);
						System.out.println("Foram removidas " + unidades + " do Album " + getCarrinho().get(i).getNome() + " ao carrinho.");
					}
					else if (getCarrinho().get(i).getUnidadesCarrinho() == unidades)
					{
						removeCarrinho(getCarrinho().get(i));
						System.out.println("O Album foi removido do carrinho com sucesso.");
					}
					else
					{
						System.out.println("Não é possível remover o Album ao carrinho porque não existem unidades suficientes.");
					}
					return;
				}
				
			}
			if (existe == false)
			{
				System.out.println("O Album não existe no Carrinho.");
			}
		}
	//Método para efetuar a compra que já se encontrava no Carrinho do Cliente
	public void finalizarCompra(Loja loja)
	{
		//Se o Carrinho estiver vazio
		if (carrinho.isEmpty())
		{
			System.out.println("Não existe nada no carrinho.");
			return;
		}
		this.divida = 0; //para levar a divida a zero antes de cada compra
		//Se o Carrinho não estiver vazio
		for (int i = 0; i < carrinho.size(); i++)
		{
			this.divida = divida + getCarrinho().get(i).getPrice() * getCarrinho().get(i).getUnidadesCarrinho();
		}
		//Só permite a compra se o Cliente tiver Saldo suficiente
		if (getSaldo() >= divida)
		{
			for (int i = 0; i < loja.getListaAlbuns().size(); i++)
			{
				for (int j = 0; j < getCarrinho().size(); j++)
				{
					if (getCarrinho().get(j).getNome().toLowerCase().equals(loja.getListaAlbuns().get(i).getNome().toLowerCase()))
					{
						//Quando o cliente compra um número de unidades superior às existem na Loja
						if (getCarrinho().get(j).getUnidadesCarrinho() > loja.getListaAlbuns().get(i).getUnidadesDisponiveis())
						{
							System.out.println("Não há unidades Disponiveis suficientes.");
						}
						else
						{
							System.out.println("Compra efetuada com sucesso!\n");
							loja.getListaAlbuns().get(i).setUnidadesDisponiveis(loja.getListaAlbuns().get(i).getUnidadesDisponiveis() - getCarrinho().get(j).getUnidadesCarrinho()); //Retirar as Unidades do Carrinho às Unidades Disponíveis
							getCarrinho().get(j).setUnidadesVendidas(getCarrinho().get(j).getUnidadesCarrinho());  	//As Unidades Vendidas passam a ser as que estavam no Carrinho
							getCarrinho().get(j).setUnidadesCarrinho(0);                 //Limpar carrinho
							loja.addListaAlbumsVendidos(getCarrinho().get(j)); 			 //Adiciona o Album à lista de albuns vendidos
							addLista_albuns_cliente(getCarrinho().get(j));               //Adiciona o Album à lista pessoal
							setSaldo(getSaldo() - divida);								 //Definir o novo Saldo
						
							if (getCarrinho().get(j).getUnidadesCarrinho() == loja.getListaAlbuns().get(i).getUnidadesDisponiveis())
							{
								loja.removeAlbum(loja.getListaAlbuns().get(i)); 		//Remove o Album da Loja		
							}
							removeCarrinho(getCarrinho().get(j)); 						 //Remove o Album do carrinho

						}
					}
				}
			}
		}
		else
		{
			System.out.println("Não tem saldo suficiente para concretizar a compra.\n");
		}
	}
	//Método para pesquisar Album por Nome na Loja
	public void visualizarAlbumNome(Loja loja, String nome)
	{
		boolean existe = false;

		System.out.printf("===================LISTA DE ALBUMS=================== \n\n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
			{
				System.out.printf(
						"Album:             %s  " +
					    "\nGrupo:             %s  " + 
						"\nMusicas:           %s  " + 
					    "\nPreço:             %s€ " + 
						"\nGénero:            %s "  + 
					    "\nUnidades em stock: %s "  + 
						"\nID:                %s \n\n",
						loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
						Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
						loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
						loja.getListaAlbuns().get(i).getUnidadesDisponiveis(),
						loja.getListaAlbuns().get(i).getID());
				existe = true;
			}
		}
		if (existe == false)
		{
			System.out.println("Não existe nenhum Album com esse nome. \n");
		}
		while (existe == true)
		{
			System.out.println("Deseja comprar algum Álbum?");
			System.out.println("[1] -> Sim");
			System.out.println("[2] -> Não");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Deseja comprar algum Álbum?");
				System.out.println("[1] -> Sim");
				System.out.println("[2] -> Não");
				input.next();
			}
			int opcao = input.nextInt();
			input.nextLine();
			
			while( opcao!=1 && opcao!=2 )
			{
				System.out.println("Input não válido. \n");
				break;
			}

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					input.next();
				}
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o numero de unidades que deseja comprar:");
					input.next();
				}
				int unidades = input.nextInt();
				
				input.nextLine();
				//Se o ID existir 
				if (ID <= loja.getListaAlbuns().size())
				{
					Album album = loja.getListaAlbuns().get(ID - 1);
					adicionaAlbum(loja, album, unidades);
				}
				else
				{
					System.out.println("O ID que introduziu não existe. \n");
				}

			}
			if (opcao == 2)
			{
				return;
			}

		}
	}
	//Método para pesquisar Album por Grupo na Loja
	public void visualizarAlbumGrupo(Loja loja, String grupo)
	{
		boolean existe = false;

		System.out.printf("===================LISTA DE ALBUMS=================== \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getGrupo().toLowerCase().equals(grupo.toLowerCase()))
			{
				System.out.printf(
						"Album:             %s  "   +
						"\nGrupo:             %s  " + 
						"\nMusicas:           %s  " + 
						"\nPreço:             %s€ " + 
						"\nGénero:            %s "  + 
						"\nUnidades em stock: %s "  + 
						"\nID:                %s \n\n",
								loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
								Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
								loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
								loja.getListaAlbuns().get(i).getUnidadesDisponiveis(),
								loja.getListaAlbuns().get(i).getID());
						
				existe = true;
			}
		}
		if (existe == false)
		{
			System.out.println("Não existe nenhum Album desse grupo.\n");
		}
		while (existe == true)
		{
			System.out.println("Deseja comprar algum Álbum?");
			System.out.println("[1] -> Sim");
			System.out.println("[2] -> Não");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Deseja comprar algum Álbum?");
				System.out.println("[1] -> Sim");
				System.out.println("[2] -> Não");
				input.next();
			}
			int opcao = input.nextInt();
			input.nextLine();
			while( opcao!=1 && opcao!=2 )
			{
				System.out.println("Input não válido. \n");
				break;
			}

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					input.next();
				}
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o numero de unidades que deseja comprar:");
					input.next();
				}
				int unidades = input.nextInt();
				input.nextLine();
				if (ID <= loja.getListaAlbuns().size())
				{
					Album album = loja.getListaAlbuns().get(ID - 1);
					adicionaAlbum(loja, album, unidades);
				}
				
				else
				{
					System.out.println("O ID que introduziu não existe. \n");
				}
			}
			if (opcao == 2)
			{
				return;
			}
		}
	}
	//Método para pesquisar Album por Música na Loja
	public void visualizarAlbumMusicas(Loja loja, String musica)
	{
		boolean existe = false;
		System.out.println("===================LISTA DE ALBUMS=================== \n");

		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			String[] musicas = loja.getListaAlbuns().get(i).getMusicas();
			for (int j = 0; j < loja.getListaAlbuns().size(); j++)
			{
				if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
				{
					System.out.printf(
							"Album:             %s  "   +
							"\nGrupo:             %s  " + 
							"\nMusicas:           %s  " + 
							"\nPreço:             %s€ " + 
							"\nGénero:            %s "  + 
							"\nUnidades em stock: %s "  + 
							"\nID:                %s \n\n",
									loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
									Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
									loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
									loja.getListaAlbuns().get(i).getUnidadesDisponiveis(),
									loja.getListaAlbuns().get(i).getID());
					existe = true;
				}
			}
		}
		if (existe == false)
		{
			System.out.println("Não existe a música que procura.\n");
		}
		while (existe == true)
		{
			System.out.println("Deseja comprar algum Álbum?");
			System.out.println("[1] -> Sim");
			System.out.println("[2] -> Não");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Deseja comprar algum Álbum?");
				System.out.println("[1] -> Sim");
				System.out.println("[2] -> Não");
				input.next();
			}
			int opcao = input.nextInt();
			input.nextLine();
			while( opcao!=1 && opcao!=2 )
			{
				System.out.println("Input não válido. \n");
				break;
			}

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					input.next();
				}
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o numero de unidades que deseja comprar:");
					input.next();
				}
				int unidades = input.nextInt();
				input.nextLine();

				if (ID <= loja.getListaAlbuns().size())
				{
					Album album = loja.getListaAlbuns().get(ID - 1);
					adicionaAlbum(loja, album, unidades);
				}
				
				else
				{
					System.out.println("O ID que introduziu não existe. \n");
				}
			}
			if (opcao == 2)
			{
				return;
			}
		}
	}
	//Método para pesquisar Album por Género na Loja
	public void visualizarAlbumGenero(Loja loja, String genero)
	{
		boolean existe = false;

		System.out.println("===================LISTA DE ALBUMS=================== \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getGenero().toLowerCase().equals(genero.toLowerCase()))
			{
				System.out.printf(
						"Album:             %s  "   +
					    "\nGrupo:             %s  " + 
					    "\nMusicas:           %s  " + 
					    "\nPreço:             %s€ " + 
						"\nGénero:            %s "  + 
						"\nUnidades em stock: %s "  + 
						"\nID:                %s \n\n",
								loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
								Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
								loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
								loja.getListaAlbuns().get(i).getUnidadesDisponiveis(),
								loja.getListaAlbuns().get(i).getID());
				existe = true;
			}
		}
		if (existe == false)
		{
			System.out.println("Não existe nenhum Album do género que procura.\n");
		}
		while (existe == true)
		{
			System.out.println("Deseja comprar algum Álbum?");
			System.out.println("[1] -> Sim");
			System.out.println("[2] -> Não");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Deseja comprar algum Álbum?");
				System.out.println("[1] -> Sim");
				System.out.println("[2] -> Não");
				input.next();
			}
			
			int opcao = input.nextInt();
			input.nextLine();
			while( opcao!=1 && opcao!=2 )
			{
				System.out.println("Input não válido. \n");
				break;
			}

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					input.next();
				}
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				while (!input.hasNextInt() )
				{
					System.out.println("Input não válido.\n");
					System.out.println("Indique o numero de unidades que deseja comprar:");
					input.next();
				}
				int unidades = input.nextInt();
				input.nextLine();

				if (ID <= loja.getListaAlbuns().size())
				{
					Album album = loja.getListaAlbuns().get(ID - 1);
					adicionaAlbum(loja, album, unidades);
				}
				
				else
				{
					System.out.println("O ID que introduziu não existe. \n");
				}
			}
			if (opcao == 2)
			{
				return;
			}
		}
	}
	//Método para ver a Lista de todos os Albuns da Loja
	public void listaAlbuns(Loja loja)
	{
		System.out.println("===================LISTA DE ALBUMS=================== \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			System.out.printf(
					"Album:             %s  " +
				    "\nGrupo:             %s  " + 
					"\nMusicas:           %s  " + 
				    "\nPreço:             %s€ " + 
					"\nGénero:            %s "  + 
				    "\nUnidades em stock: %s "  + 
					"\nID:                %s \n\n",
					loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
					Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
					loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
					loja.getListaAlbuns().get(i).getUnidadesDisponiveis(),
					loja.getListaAlbuns().get(i).getID());
		}
		System.out.println("Deseja comprar algum Álbum?");
		System.out.println("[1] -> Sim");
		System.out.println("[2] -> Não");
		while (!input.hasNextInt() )
		{
			System.out.println("Input não válido.\n");
			System.out.println("Deseja comprar algum Álbum?");
			System.out.println("[1] -> Sim");
			System.out.println("[2] -> Não");
			input.next();
		}
		
		int opcao = input.nextInt();
		input.nextLine();
		while( opcao!=1 && opcao!=2 )
		{
			System.out.println("Input não válido. \n");
			break;
		}

		if (opcao == 1)
		{
			System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				input.next();
			}
			int ID = input.nextInt();
			input.nextLine();

			System.out.println("Indique o numero de unidades que deseja comprar:");
			while (!input.hasNextInt() )
			{
				System.out.println("Input não válido.\n");
				System.out.println("Indique o numero de unidades que deseja comprar:");
				input.next();
			}
			int unidades = input.nextInt();
			input.nextLine();

			if (ID <= loja.getListaAlbuns().size())
			{
				Album album = loja.getListaAlbuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}
			
			else
			{
				System.out.println("O ID que introduziu não existe. \n");
			}
		}
		if (opcao == 2)
		{
			return;
		}
	}
	//Método para a Apresentação do Carrinho
	public String carrinhoAspeto()
	{
		String[] carrinho = new String[getCarrinho().size()];

		for (int i = 0; i < getCarrinho().size(); i++)
		{
			carrinho[i] = "\nAlbum:                " + getCarrinho().get(i).getNome() + "\n" + 
						  "Grupo:                " + getCarrinho().get(i).getGrupo() + "\n" + 
					      "Musicas:              " + Arrays.toString(getCarrinho().get(i).getMusicas()) + "\n" + 
					      "Preço:                " + Double.toString(getCarrinho().get(i).getPrice()) + "€\n" + 
					      "Género:               " + getCarrinho().get(i).getGenero() + "\n" + 
					      "Unidades no carrinho: " + Integer.toString(getCarrinho().get(i).getUnidadesCarrinho()) + "\n" +
					      "ID:                   " + getCarrinho().get(i).getID() + "\n";
		}
		return Arrays.toString(carrinho);
	}
	//Método para a Apresentação do Histórico de compras
	public String historicoAspeto()
	{
		String[] carrinhoHistorico = new String[getLista_albuns_cliente().size()];

		for (int i = 0; i < getLista_albuns_cliente().size(); i++)
		{
			carrinhoHistorico[i] = "\nAlbum:                 " + getLista_albuns_cliente().get(i).getNome() + "\n" + 
									"Grupo:                  " + getLista_albuns_cliente().get(i).getGrupo() + "\n" + 
									"Musicas:                " + Arrays.toString(getLista_albuns_cliente().get(i).getMusicas()) + "\n" +
									"Preço:                  " + Double.toString(getLista_albuns_cliente().get(i).getPrice()) + "€\n" +
									"Género:                 " + getLista_albuns_cliente().get(i).getGenero() + "\n" +
									"Unidades compradas:     " + Integer.toString(getLista_albuns_cliente().get(i).getUnidadesVendidas()) + "\n" +
									"ID:                     " + getLista_albuns_cliente().get(i).getID() + "\n";
		}
		return Arrays.toString(carrinhoHistorico);
	}
	//Método para a Apresentação do Cliente
	public String toString()
	{
		return "===================DADOS DE CONTA=================== \n\n" +
			   "Username:            " + getUsername()     + "\n" + 
			   "Password:            " + getPassword()     + "\n" + 
			   "Saldo:               " + getSaldo()        + "€\n" +
			   "===================Carrinho========================= \n" + 
			   carrinhoAspeto()  + "\n" + 
			   "===================Histórico de compras============= \n" + 
			   historicoAspeto() + "\n";
	}
}