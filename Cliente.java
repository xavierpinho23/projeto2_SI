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
	public ArrayList<Album> lista_albuns_cliente = new ArrayList<Album>(); // albuns j� comprados pelo cliente
	private ArrayList<Album> carrinho = new ArrayList<Album>(); 		   // carrinho de compras
	
	Scanner input = new Scanner(System.in);

	public Cliente(String username, String password)
	{
		// aqui n�o ^ deveria ter o saldo e a divida?
		this.username = username;
		this.password = password;
		this.saldo = 100;
		this.divida = 0;
	}
	//M�todo to get para aceder ao Carrinho do Cliente
	public ArrayList<Album> getCarrinho()
	{
		return carrinho;
	}

	public void addCarrinho(Album album)
	{
		this.carrinho.add(album);
	}

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
	//M�todo to get para aceder � Lista de Albuns da Loja
	public ArrayList<Album> getLista_albuns(Loja loja)
	{
		return loja.getListaAlbuns();
	}
	//M�todo to get para aceder � Lista de Albuns do Cliente
	public ArrayList<Album> getLista_albuns_cliente()
	{
		return lista_albuns_cliente;
	}

	public void addLista_albuns_cliente(Album album)
	{
		this.lista_albuns_cliente.add(album);
	}
	//M�todo to get para aceder ao Username do Cliente
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	//M�todo to get para aceder � Password do Cliente
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	//M�todo to get para aceder ao Saldo do Cliente
	public double getSaldo()
	{
		return saldo;
	}

	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}

	// adiciona albuns ao carrinho
	public void adicionaAlbum(Loja loja, Album album, int unidades)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).equals(album))
			{
				if (album.getUnidades() == unidades)
				{
					addCarrinho(album);
				}

				else if (album.getUnidades() > unidades)
				{
					Album album1 = album;
					album1.setUnidades(unidades);
					addCarrinho(album1);
					System.out.println("Foram adicionadas " + unidades + " ao carrinho");
				}
				else
				{
					System.out.println("N�o � poss�vel adicionar o album ao carrinho.");
				}

			}
		}
	}
	//M�todo para efetuar a compra que j� se encontrava no Carrinho do Cliente
	public void finalizarCompra(Loja loja)
	{
		//Se o Carrinho estiver vazio
		if (carrinho.isEmpty())
		{
			System.out.println("N�o existe nada no carrinho.");
			return;
		}
		//Se o Carrinho n�o estiver vazio
		for (int i = 0; i < carrinho.size(); i++)
		{
			this.divida = divida + getCarrinho().get(i).getPrice() * getCarrinho().get(i).getUnidades();
		}
		//S� permite a compra se o Cliente tiver Saldo suficiente
		if (getSaldo() >= divida)
		{
			for (int i = 0; i < loja.getListaAlbuns().size(); i++)
			{
				for (int j = 0; j < getCarrinho().size(); j++)
				{
					if (getCarrinho().get(j).getNome().toLowerCase().equals(loja.getListaAlbuns().get(i).getNome().toLowerCase()))
					{
						//Quando o cliente compra o mesmo n�mero de unidades que existe na Loja
						if (getCarrinho().get(j).getUnidades() == loja.getListaAlbuns().get(i).getUnidades())
						{
							loja.removeAlbum(loja.getListaAlbuns().get(i)); //Remove o Album da loja
							loja.addListaAlbumsVendidos(getCarrinho().get(j));    //Adiciona � lista de albuns vendidos
							addLista_albuns_cliente(getCarrinho().get(j));   //Adiciona � lista pessoal
							removeCarrinho(getCarrinho().get(j));            //Remove do carrinho
							setSaldo(getSaldo() - divida);					 //Definir o novo Saldo
						}
						//Quando o cliente compra um n�mero de unidades inferior ao que h� na Loja
						else if (getCarrinho().get(j).getUnidades() < loja.getListaAlbuns().get(i).getUnidades())
						{
							Album album = loja.getListaAlbuns().get(i); //ISTO ACHO QUE � ESCUSADO
							album.setUnidades(album.getUnidades() - getCarrinho().get(j).getUnidades()); //Atualiza as Unidades do Album																					
							loja.atualizaUnidades(album); 												 //Atualiza as Unidades do Album na Loja
							loja.addListaAlbumsVendidos(getCarrinho().get(j)); 								 //Adiciona o Album � lista de albuns vendidos
							addLista_albuns_cliente(getCarrinho().get(j));                               //Adiciona o Album � lista pessoal
							removeCarrinho(getCarrinho().get(j)); 										 //Remove o Album do carrinho
							setSaldo(getSaldo() - divida);												 //Definir o novo Saldo
						}
					}
				}
			}
		}
	}

	//M�todo para pesquisar Album por Nome na Loja
	public void visualizarAlbumNome(Loja loja, String nome)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n\n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
						Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
						loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
						loja.getListaAlbuns().get(i).getUnidades(), (i + 1));
				existe = true;
			}
		}

		if (existe == false)
		{
			System.out.println("N�o existe nenhum Album com esse nome");
		}

		if (existe == true)
		{
			System.out.println("Deseja comprar algum �lbum? [1] -> Sim");
			int opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				int unidades = input.nextInt();
				input.nextLine();

				Album album = loja.getListaAlbuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);

			}

		}
	}

	//M�todo para pesquisar Album por Grupo na Loja
	public void visualizarAlbumGrupo(Loja loja, String grupo)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getGrupo().toLowerCase().equals(grupo.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
						Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
						loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
						loja.getListaAlbuns().get(i).getUnidades(), (i + 1));
				existe = true;
			}

		}
		if (existe == false)
		{
			System.out.println("N�o existe nenhum Album desse grupo.");
		}
		if (existe == true)
		{
			System.out.println("Deseja comprar algum �lbum? [1] -> Sim");
			int opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				int unidades = input.nextInt();
				input.nextLine();

				Album album = loja.getListaAlbuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}
		}
	}
	//M�todo para pesquisar Album por M�sica na Loja
	public void visualizarAlbumMusicas(Loja loja, String musica)
	{
		boolean existe = false;

		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			String[] musicas = loja.getListaAlbuns().get(i).getMusicas();
			for (int j = 0; j < loja.getListaAlbuns().size(); j++)
			{
				if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
				{
					System.out.printf(
							"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
							loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
							Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
							loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
							loja.getListaAlbuns().get(i).getUnidades(), (i + 1));
					existe = true;
				}
			}
		}
		if (existe == false)
		{
			System.out.println("N�o existe a m�sica que procura.");
		}
		if (existe == true)
		{
			System.out.println("Deseja comprar algum �lbum? [1] -> Sim");
			int opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				int unidades = input.nextInt();
				input.nextLine();

				Album album = loja.getListaAlbuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}
		}
	}
	//M�todo para pesquisar Album por G�nero na Loja
	public void visualizarAlbumGenero(Loja loja, String genero)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getGenero().toLowerCase().equals(genero.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
						Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
						loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
						loja.getListaAlbuns().get(i).getUnidades(), i + 1);
				existe = true;
			}
			else
			{
				System.out.println("N�o existe nenhum Album do g�nero que procura.");
			}
		}
		if (existe)
		{
			System.out.println("Deseja comprar algum �lbum? [1] -> Sim");
			int opcao = input.nextInt();
			input.nextLine();

			if (opcao == 1)
			{
				System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
				int ID = input.nextInt();
				input.nextLine();

				System.out.println("Indique o numero de unidades que deseja comprar:");
				int unidades = input.nextInt();
				input.nextLine();

				Album album = loja.getListaAlbuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}
		}
	}
	//M�todo para ver a Lista de todos os Albuns da Loja
	public void listaAlbuns(Loja loja)
	{
		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			System.out.printf(
					"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
					loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
					Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
					loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
					loja.getListaAlbuns().get(i).getUnidades(), (i + 1));
		}
		System.out.println("Deseja comprar algum �lbum? [1] -> Sim");
		int opcao = input.nextInt();
		input.nextLine();

		if (opcao == 1)
		{
			System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
			int ID = input.nextInt();
			input.nextLine();

			System.out.println("Indique o numero de unidades que deseja comprar:");
			int unidades = input.nextInt();
			input.nextLine();

			Album album = loja.getListaAlbuns().get(ID - 1);
			adicionaAlbum(loja, album, unidades);
		}
	}
	//M�todo para a Apresenta��o do Carrinho
	public String carrinhoAspeto()
	{
		String[] carrinho = new String[getCarrinho().size()];

		for (int i = 0; i < getCarrinho().size(); i++)
		{
			carrinho[i] = "Album: " + getCarrinho().get(i).getNome() + "\n" + "Grupo: "
					+ getCarrinho().get(i).getGrupo() + "\n" + "Musicas: "
					+ Arrays.toString(getCarrinho().get(i).getMusicas()) + "\n" + "Pre�o: "
					+ Double.toString(getCarrinho().get(i).getPrice()) + "\n" + "G�nero: "
					+ getCarrinho().get(i).getGenero() + "\n" + "Unidades em stock: "
					+ Integer.toString(getCarrinho().get(i).getUnidades()) + "\n";
		}
		return Arrays.toString(carrinho);
	}
	//M�todo para a Apresenta��o do Hist�rico de compras
	public String historicoAspeto()
	{
		String[] carrinhoHistorico = new String[getLista_albuns_cliente().size()];

		for (int i = 0; i < getLista_albuns_cliente().size(); i++)
		{
			carrinhoHistorico[i] = "Album: " + getLista_albuns_cliente().get(i).getNome() + "\n" + "Grupo: "
					+ getLista_albuns_cliente().get(i).getGrupo() + "\n" + "Musicas: "
					+ Arrays.toString(getLista_albuns_cliente().get(i).getMusicas()) + "\n" + "Pre�o: "
					+ Double.toString(getLista_albuns_cliente().get(i).getPrice()) + "\n" + "G�nero: "
					+ getLista_albuns_cliente().get(i).getGenero() + "\n" + "Unidades em stock: "
					+ Integer.toString(getLista_albuns_cliente().get(i).getUnidades()) + "\n";
		}
		return Arrays.toString(carrinhoHistorico);
	}
	//M�todo para a Apresenta��o do Cliente
	public String toString()
	{
		return "Username: " + getUsername() + "\n" + 
			   "Password: " + getPassword() + "\n" + 
			   "Saldo: " + getSaldo() + "\n" +
			   "Carrinho: " + carrinhoAspeto() + "\n" + 
			   "Hist�rico de compras:" + historicoAspeto() + "\n";
	}
}