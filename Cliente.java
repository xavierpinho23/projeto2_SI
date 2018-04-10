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
	private ArrayList<Album> carrinho = new ArrayList<Album>(); // carrinho de compras
	Scanner input = new Scanner(System.in);

	public Cliente(String username, String password)
	{
		// aqui n�o ^ deveria ter o saldo e a divida?
		this.username = username;
		this.password = password;
		this.saldo = 100;
		this.divida = 0;
	}

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

	public ArrayList<Album> getLista_albuns(Loja loja)
	{
		return loja.getLista_albuns();
	}

	public ArrayList<Album> getLista_albuns_cliente()
	{
		return lista_albuns_cliente;
	}

	public void addLista_albuns_cliente(Album album)
	{
		this.lista_albuns_cliente.add(album);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

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
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
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

	public void finalizarCompra(Loja loja)
	{
		// valor em compras dentro do carrinho
		if (carrinho.isEmpty())
		{
			System.out.println("N�o existe nada no carrinho.");
			return;
		}
		for (int i = 0; i < carrinho.size(); i++)
		{
			this.divida = divida + getCarrinho().get(i).getPrice() * getCarrinho().get(i).getUnidades();
		}

		if (getSaldo() >= divida)
		{
			for (int i = 0; i < loja.getLista_albuns().size(); i++)
			{
				for (int j = 0; j < getCarrinho().size(); j++)
				{
					// acrescentei o LowerCase() para as compara��es
					if (getCarrinho().get(j).getNome().toLowerCase()
							.equals(loja.getLista_albuns().get(i).getNome().toLowerCase()))
					{
						if (getCarrinho().get(j).getUnidades() == loja.getLista_albuns().get(i).getUnidades())
						{
							loja.removeAlbum(loja.getLista_albuns().get(i)); // Remove o Album da loja
							loja.addLista_vendidos(getCarrinho().get(j)); // Adiciona � lista de albuns vendidos
							addLista_albuns_cliente(getCarrinho().get(j)); // adiciona � lista pessoal
							removeCarrinho(getCarrinho().get(j)); // remove do carrinho
							setSaldo(getSaldo() - divida);

							// System.out.println("�lbum [ " + getCarrinho().get(j) + " ] comprado com sucesso.");
						}

						else if (getCarrinho().get(j).getUnidades() < loja.getLista_albuns().get(i).getUnidades())
						{
							System.out.println("1� else if");

							Album album = loja.getLista_albuns().get(i);

							album.setUnidades(album.getUnidades() - getCarrinho().get(j).getUnidades()); // atualiza
																											// unidades
																											// do album
																											// na loja

							loja.atualizaUnidades(album);
							loja.addLista_vendidos(getCarrinho().get(j)); // Adiciona � lista de albuns vendidos
							addLista_albuns_cliente(getCarrinho().get(j)); // adiciona � lista pessoal
							removeCarrinho(getCarrinho().get(j)); // remove do carrinho
							setSaldo(getSaldo() - divida);

							// System.out.println("�lbum [ " + getCarrinho().get(j) + " ] comprado com sucesso.");
						}
					}
				}
			}
		}
	}

	// Pesquisar albuns por nome
	public void visualizarAlbumNome(Loja loja, String nome)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n\n");
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			// acrescentei o LowerCase() para as compara��es
			if (loja.getLista_albuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
						Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
						loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
						loja.getLista_albuns().get(i).getUnidades(), (i + 1));
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

				Album album = loja.getLista_albuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);

			}

		}
	}

	// Pesquisar albuns por grupo
	public void visualizarAlbumGrupo(Loja loja, String grupo)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			// acrescentei o LowerCase() para as compara��es
			if (loja.getLista_albuns().get(i).getGrupo().toLowerCase().equals(grupo.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
						Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
						loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
						loja.getLista_albuns().get(i).getUnidades(), (i + 1));
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

				Album album = loja.getLista_albuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}

		}
	}

	// Pesquisar albuns por Musicas
	public void visualizarAlbumMusicas(Loja loja, String musica)
	{
		boolean existe = false;

		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			String[] musicas = loja.getLista_albuns().get(i).getMusicas();
			for (int j = 0; j < loja.getLista_albuns().size(); j++)
			{
				// acrescentei o LowerCase() para as compara��es
				if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
				{
					System.out.printf(
							"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
							loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
							Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
							loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
							loja.getLista_albuns().get(i).getUnidades(), (i + 1));
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

				Album album = loja.getLista_albuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}
		}
	}

	// Pesquisar albuns por genero
	public void visualizarAlbumGenero(Loja loja, String genero)
	{
		boolean existe = false;

		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).getGenero().toLowerCase().equals(genero.toLowerCase()))
			{
				System.out.printf(
						"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
						loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
						Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
						loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
						loja.getLista_albuns().get(i).getUnidades(), i + 1);
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

				Album album = loja.getLista_albuns().get(ID - 1);
				adicionaAlbum(loja, album, unidades);
			}

		}
	}

	public void listaAlbuns(Loja loja)
	// Ver todos os albuns
	{
		System.out.printf("Lista dos albuns: \n");
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			System.out.printf(
					"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s \nG�nero: %s \nUnidades em stock: %s \nID: %s \n",
					loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
					Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
					loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
					loja.getLista_albuns().get(i).getUnidades(), (i + 1));

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

			Album album = loja.getLista_albuns().get(ID - 1);
			adicionaAlbum(loja, album, unidades);

		}
	}

	public String carrinhoAspeto()
	{
		// Apresenta��o do carrinho de compras
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

	public String historicoAspeto()
	// Apresenta��o do Hist�rico de compras
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

	public String toString()
	// Apresenta��o do Cliente
	{
		return "Username: " + getUsername() + "\n" + "Password: " + getPassword() + "\n" + "Saldo: " + getSaldo() + "\n"
				+ "Carrinho: " + carrinhoAspeto() + "\n" + "Hist�rico de compras:" + historicoAspeto() + "\n";
	}
}
