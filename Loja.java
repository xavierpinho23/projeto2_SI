package trabalho2_si;

import java.util.ArrayList;

public class Loja
{
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	public ArrayList<Album> listaAlbuns = new ArrayList<Album>();
	public ArrayList<Album> listaAlbunsVendidos = new ArrayList<Album>();

	//M�todo para aceder � Lista de Clientes
	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}

	public void addClientes(Cliente cliente)
	{
		this.clientes.add(cliente);
	}
	//M�todo para aceder � Lista de Administradores
	public ArrayList<Administrador> getAdministradores()
	{
		return administradores;
	}

	public void addAdministradores(Administrador admin)
	{
		this.administradores.add(admin);
	}
	//M�todo para aceder � Lista de Albuns
	public ArrayList<Album> getListaAlbuns()
	{
		return listaAlbuns;
	}

	public void setListaAlbuns(ArrayList<Album> listaAlbuns)
	{
		this.listaAlbuns = listaAlbuns;
	}
	//M�todo para aceder � Lista de Albuns Vendidos
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
	//M�todo para a Estatistica de dinheiro gasto por G�nero de Album
	public void dinheiroGastoGenero()
	{
		ArrayList<Double> dinheiroGenero = new ArrayList<Double>();
		ArrayList<String> generos = new ArrayList<String>();

		double pre�o = 0;
		//Se a Lista de Albuns Vendidos estiver vazia
		if (listaAlbunsVendidos.isEmpty())
		{
			System.out.println("Ainda n�o foram vendidos quaisquer Albums.");
			return;
		}
		if (dinheiroGenero.isEmpty())
		{
			generos.add(getListaAlbunsVendidos().get(0).getGenero());
			pre�o = getListaAlbunsVendidos().get(0).getUnidades() * getListaAlbunsVendidos().get(0).getPrice();
			dinheiroGenero.add(pre�o);
		}
		for (int i = 1; i < getListaAlbunsVendidos().size(); i++)
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getListaAlbunsVendidos().get(i).getGenero()))
				{
					pre�o = dinheiroGenero.get(j)
							+ getListaAlbunsVendidos().get(j).getUnidades() * getListaAlbunsVendidos().get(j).getPrice();
					dinheiroGenero.remove(j);
					dinheiroGenero.add(j, pre�o);
				}
				else
				{
					generos.add(j, getListaAlbunsVendidos().get(i).getGenero());
					pre�o = getListaAlbunsVendidos().get(j).getUnidades() * getListaAlbunsVendidos().get(j).getPrice();
					dinheiroGenero.add(pre�o);
					break;
				}
			}
		}
		for (int i = 0; i < dinheiroGenero.size(); i++)
		{
			System.out.printf("Foi gasto %s � em Albums do g�nero %s .\n", dinheiroGenero.get(i), generos.get(i));
		}
	}
	//M�todo para fazer a contagem de Albums na Loja
	public int albunsStock()
	{
		int sum = 0;
		for (int i = 0; i < getListaAlbuns().size(); i++)
		{
			sum = sum + getListaAlbuns().get(i).getUnidades();
		}
		return sum;
	}
	//M�todo para fazer a contagem de Albums na Loja por G�nero
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
		//Se generos n�o estiver vazio
		for (int i = 1; i < getListaAlbuns().size(); i++)
		//Come�a i = 1 porque ja correu o outro la em cima
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getListaAlbuns().get(i).getGenero()))
				{
					// contar o numero de albums por cada g�nero caso o g�nero ja exista em generos
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
			System.out.printf("Existem %s unidades do g�nero %s .\n", contador.get(i), generos.get(i));
		}
	}
	//M�todo para adicionar � Lista de Albums Vendidos
	public void addListaAlbumsVendidos(Album album)
	{
		this.listaAlbunsVendidos.add(album);
	}
	//M�todo para remover um Album da Lista de Albums
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
	//M�todo para adicionar um Album � Lista de Albums
	public void adicionaAlbum(Album album)
	{
		listaAlbuns.add(album);
	}
	//M�todo para atualizar as Unidades de um Album
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