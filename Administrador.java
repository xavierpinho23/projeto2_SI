package trabalho2_si;

import java.util.ArrayList;

public class Administrador
{
	private String username;
	private String password;
	
	public Administrador(String username, String password)
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
	
	
	//Adicionar albuns
	public void addNewAlbum (ArrayList<Album> lista_albuns,String nome, String grupo, String[] musicas, int price, int unidades)
	{
		//criar um album
		Album album = new Album(nome, grupo, musicas, price, unidades);
		//Adicionar album à lista
		lista_albuns.add(album);
	}
	
	//Fazer alterações no preço dos albuns (com histórico)
	public void updateAlbumPrice(Album lista_albuns, String nome,String grupo, String[] musicas, int price, int unidades)
	{
		Album.setPrice(price);
	}
	//Remover um album (se ainda não vendido)
	public void removeAlbum(Album lista_albuns, String nome,String grupo, String[] musicas, int price, int unidades)
	{
		if ()
		{
			lista_albuns.remove(Album);			
		}
		else
		{
			System.out.println("Não é possível remover o album porque já foi vendido.")
		}
	}
	

}
