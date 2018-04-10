package trabalho2_si;

import java.util.ArrayList;

public class Loja
{
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	public ArrayList<Album> lista_albuns = new ArrayList<Album>();
	public ArrayList<Album> lista_vendidos = new ArrayList<Album>();

	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}

	public void addClientes(Cliente cliente)
	{
		this.clientes.add(cliente);
	}

	public ArrayList<Administrador> getAdministradores()
	{
		return administradores;
	}

	public void addAdministradores(Administrador admin)
	{
		this.administradores.add(admin);
	}

	public ArrayList<Album> getLista_albuns()
	{
		return lista_albuns;
	}

	public void setLista_albuns(ArrayList<Album> lista_albuns)
	{
		this.lista_albuns = lista_albuns;
	}

	public ArrayList<Album> getLista_vendidos()
	{
		return lista_vendidos;
	}

	public double dinheiroGastoTotal()
	{
		double dinheiroGasto = 0;

		for (int i = 0; i < lista_vendidos.size(); i++)
		{
			dinheiroGasto = dinheiroGasto
					+ getLista_vendidos().get(i).getPrice() * getLista_vendidos().get(i).getUnidades();
		}
		return dinheiroGasto;
	}

	public void dinheiroGastoGenero()
	// estatistica de dinheiro gasto por genero de albums
	{
		ArrayList<Double> dinheiroGenero = new ArrayList<Double>();
		ArrayList<String> generos = new ArrayList<String>();

		double pre�o = 0;

		if (lista_vendidos.isEmpty())
		{
			System.out.println("Ainda n�o foram vendidos quaisquer Albums.");
			return;
		}
		if (dinheiroGenero.isEmpty())
		{
			generos.add(getLista_vendidos().get(0).getGenero());
			pre�o = getLista_vendidos().get(0).getUnidades() * getLista_vendidos().get(0).getPrice();
			dinheiroGenero.add(pre�o);
		}

		for (int i = 1; i < getLista_vendidos().size(); i++)
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getLista_vendidos().get(i).getGenero()))
				{
					pre�o = dinheiroGenero.get(j)
							+ getLista_vendidos().get(j).getUnidades() * getLista_vendidos().get(j).getPrice();
					dinheiroGenero.remove(j);
					dinheiroGenero.add(j, pre�o);
				}
				else
				{
					generos.add(j, getLista_vendidos().get(i).getGenero());
					pre�o = getLista_vendidos().get(j).getUnidades() * getLista_vendidos().get(j).getPrice();
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

	public int albunsStock()
	{
		int sum = 0;
		for (int i = 0; i < getLista_albuns().size(); i++)
		{
			sum = sum + getLista_albuns().get(i).getUnidades();
		}
		return sum;
	}

	public void albunsStockGenero()
	{
		ArrayList<String> generos = new ArrayList<String>();
		ArrayList<Integer> contador = new ArrayList<Integer>();

		if (generos.isEmpty())
		// generos vazio
		{
			generos.add(getLista_albuns().get(0).getGenero());
			contador.add(getLista_albuns().get(0).getUnidades());
		}

		// se o generos n�o estiver vazio
		for (int i = 1; i < getLista_albuns().size(); i++)
		// i = 1 porque ja correu o outro la em cima
		{
			for (int j = 0; j < generos.size(); j++)
			{
				if (generos.get(j).equals(getLista_albuns().get(i).getGenero()))
				{
					// contar o numero de albums por cada g�nero caso o g�nero ja exista em generos
					int valor = contador.get(j) + getLista_albuns().get(i).getUnidades();
					contador.remove(j);
					contador.add(j, valor);
				}
				else
				{
					// contar o numero de albums por genero quando o genero ainda nao existe em generos
					generos.add(j, getLista_albuns().get(i).getGenero());
					contador.add(j, getLista_albuns().get(i).getUnidades());
					break;
				}
			}
		}
		for (int i = 0; i < generos.size(); i++)
		{
			System.out.printf("Existem %s unidades do g�nero %s .\n", contador.get(i), generos.get(i));
		}
	}

	public void addLista_vendidos(Album album)
	{
		this.lista_vendidos.add(album);
	}

	public void removeAlbum(Album album)
	{
		for (int i = 0; i < getLista_albuns().size(); i++)
		{
			if (getLista_albuns().get(i).equals(album))
			{
				lista_albuns.remove(i);
			}
		}
	}

	public void adicionaAlbum(Album album)
	{
		lista_albuns.add(album);
	}

	public void atualizaUnidades(Album album)
	{
		for (int i = 0; i < getLista_albuns().size(); i++)
		{
			String nome = album.getNome();
			if (getLista_albuns().get(i).getNome().equals(nome))
			{
				lista_albuns.remove(i);
				lista_albuns.add(i, album);
			}
		}
	}
}
