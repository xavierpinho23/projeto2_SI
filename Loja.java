package trabalho2_si;

import java.util.ArrayList;

public class Loja
{
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	public ArrayList<Album> listaAlbuns = new ArrayList<Album>();
	public ArrayList<Album> listaAlbunsVendidos = new ArrayList<Album>();

	//Método para aceder à Lista de Clientes
	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}

	public void addClientes(Cliente cliente)
	{
		this.clientes.add(cliente);
	}
	//Método para aceder à Lista de Administradores
	public ArrayList<Administrador> getAdministradores()
	{
		return administradores;
	}

	public void addAdministradores(Administrador admin)
	{
		this.administradores.add(admin);
	}
	//Método para aceder à Lista de Albuns
	public ArrayList<Album> getListaAlbuns()
	{
		return listaAlbuns;
	}

	public void setListaAlbuns(ArrayList<Album> listaAlbuns)
	{
		this.listaAlbuns = listaAlbuns;
	}
	//Método para aceder à Lista de Albuns Vendidos
	public ArrayList<Album> getListaAlbunsVendidos()
	{
		return listaAlbunsVendidos;
	}

	public double dinheiroGastoTotal()
	{
		double dinheiroGasto = 0;

		for (int i = 0; i < listaAlbunsVendidos.size(); i++)
		{
			dinheiroGasto = dinheiroGasto + getListaAlbunsVendidos().get(i).getPrice() * getListaAlbunsVendidos().get(i).getUnidades();
		}
		return dinheiroGasto;
	}
	//Método para a Estatistica de dinheiro gasto por Género de Album
	public void dinheiroGastoGenero()
	{
		ArrayList<Double> dinheiroGenero = new ArrayList<Double>();
		ArrayList<String> generos = new ArrayList<String>();

		double preço = 0;
		//Se a Lista de Albuns Vendidos estiver vazia
		if (listaAlbunsVendidos.isEmpty())
		{
			System.out.println("Ainda não foram vendidos quaisquer Albums.");
			return;
		}
		if (dinheiroGenero.isEmpty())
		{
			generos.add(getListaAlbunsVendidos().get(0).getGenero());
			preço = getListaAlbunsVendidos().get(0).getUnidades() * getListaAlbunsVendidos().get(0).getPrice();
			dinheiroGenero.add(preço);
		}
		for (int i = 1; i < getListaAlbunsVendidos().size(); i++)
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getListaAlbunsVendidos().get(i).getGenero()))
				{
					preço = dinheiroGenero.get(j)
							+ getListaAlbunsVendidos().get(j).getUnidades() * getListaAlbunsVendidos().get(j).getPrice();
					dinheiroGenero.remove(j);
					dinheiroGenero.add(j, preço);
				}
				else
				{
					generos.add(j, getListaAlbunsVendidos().get(i).getGenero());
					preço = getListaAlbunsVendidos().get(j).getUnidades() * getListaAlbunsVendidos().get(j).getPrice();
					dinheiroGenero.add(preço);
					break;
				}
			}
		}
		for (int i = 0; i < dinheiroGenero.size(); i++)
		{
			System.out.printf("Foi gasto %s € em Albums do género %s .\n", dinheiroGenero.get(i), generos.get(i));
		}
	}
	//Método para fazer a contagem de Albums na Loja
	public int albunsStock()
	{
		int sum = 0;
		for (int i = 0; i < getListaAlbuns().size(); i++)
		{
			sum = sum + getListaAlbuns().get(i).getUnidades();
		}
		return sum;
	}
	//Método para fazer a contagem de Albums na Loja por Género
	public void albunsStockGenero()
	{
		ArrayList<String> generos = new ArrayList<String>();
		ArrayList<Integer> contador = new ArrayList<Integer>();
		
		//Se generos estiver vazio
		if (generos.isEmpty())
		{
			generos.add(getListaAlbuns().get(0).getGenero());
			contador.add(getListaAlbuns().get(0).getUnidades());
		}
		//Se generos não estiver vazio
		for (int i = 1; i < getListaAlbuns().size(); i++)
		//Começa i = 1 porque ja correu o outro la em cima
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getListaAlbuns().get(i).getGenero()))
				{
					// contar o numero de albums por cada género caso o género ja exista em generos
					int valor = contador.get(j) + getListaAlbuns().get(i).getUnidades();
					contador.remove(j);
					contador.add(j, valor);
				}
				else
				{
					// contar o numero de albums por genero quando o genero ainda nao existe em generos
					generos.add(j, getListaAlbuns().get(i).getGenero());
					contador.add(j, getListaAlbuns().get(i).getUnidades());
					break;
				}
			}
		}
		for (int i = 0; i < generos.size(); i++)
		{
			System.out.printf("Existem %s unidades do género %s .\n", contador.get(i), generos.get(i));
		}
	}
	//Método para adicionar à Lista de Albums Vendidos
	public void addListaAlbumsVendidos(Album album)
	{
		this.listaAlbunsVendidos.add(album);
	}
	//Método para remover um Album da Lista de Albums
	public void removeAlbum(Album album)
	{
		for (int i = 0; i < getListaAlbuns().size(); i++)
		{
			if (getListaAlbuns().get(i).equals(album))
			{
				listaAlbuns.remove(i);
			}
		}
	}
	//Método para adicionar um Album à Lista de Albums
	public void adicionaAlbum(Album album)
	{
		listaAlbuns.add(album);
	}
	//Método para atualizar as Unidades de um Album
	public void atualizaUnidades(Album album)
	{
		for (int i = 0; i < getListaAlbuns().size(); i++)
		{
			String nome = album.getNome();
			if (getListaAlbuns().get(i).getNome().equals(nome))
			{
				listaAlbuns.remove(i);
				listaAlbuns.add(i, album);
			}
		}
	}
}