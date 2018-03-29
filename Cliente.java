package trabalho2_si;

import java.util.ArrayList;

public class Cliente
{
	private String username;
	private String password;
	private int saldo;
	//albuns já comprados pelo cliente
    private ArrayList<Album> lista_albuns_cliente = new ArrayList<Album>();
    //Todos os albuns
    private ArrayList<Album> lista_albuns = new ArrayList<Album>();

	public Cliente(String username, String password, int saldo, String[] lista_albuns)
	{
		this.username = username;
        this.password = password;
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

	public int getSaldo()
	{
		return saldo;
	}

	public void setSaldo(int saldo)
	{
		this.saldo = saldo;
	}

	public ArrayList<Album> getlista_albuns_cliente() 
	{
		return lista_albuns_cliente;
	}
	
	//procurar album por nome
	public Album findAlbunsName(String nome)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if (lista_albuns.get(i).getNome() == nome)
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	
	//procurar album por grupo
	public Album findAlbunsGroup(String grupo)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if (lista_albuns.get(i).getGrupo() == grupo)
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	
	//procurar album por musicas
	public Album findAlbunsMusicas(String [] musicas)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if (lista_albuns.get(i).getMusicas() == musicas)
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por genero
	public Album findAlbunsGenero (String genero)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if(lista_albuns.get(i).getGenero() == genero) 
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	
	//Comprar albuns
	public void compraAlbumPorNome(ArrayList<Album> lista_albuns, Album album) 
	{
		if (album.getUnidades() > 0)
		{
			lista_albuns.remove(album);
			lista_albuns_cliente.add(album);
		}
		else
		{
			System.out.println("Não é possível remover o album porque já não existe.");
		}
	}

}