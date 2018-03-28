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
	public void addNewAlbum (ArrayList<Albuns> lista_albuns,String nome, String grupo, String[] musicas, int price)
	{
		//criar um album
		Albuns album = new Albuns(nome, grupo, musicas, price);
		//Adicionar album � lista
		lista_albuns.add(album);
	}
	
	//Fazer altera��es no pre�o dos albuns (com hist�rico)
	public void updateAlbumPrice(Albuns lista_albuns, String nome,String grupo, String[] musicas, int price)
	{
		Albuns.setPrice(price);
	}
	//Remover um album (se ainda n�o vendido)
	public void removeAlbum(Albuns lista_albuns, String nome,String grupo, String[] musicas, int price)
	{
		if ()
		{
			lista_albuns.remove(album);			
		}
		else
		{
			System.out.println("N�o � poss�vel remover o album porque j� foi vendido.")
		}
	}
	

}