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
	public void addNewAlbum (ArrayList<Album> lista_albuns, Album album)
	{
		//Jorge: Corrigi, porque pedias argumentos nome, genero, etc... quando não precisas
		// porque defines sempre o album antes de o adicionar.
		//Adicionar album à lista
		lista_albuns.add(album);
	}
	
	//Fazer alterações no preço dos albuns (com histórico)
	public void updateAlbumPrice(Album album, String nome, int price)
	{
		album.setPrice(price);
	}
	//Remover um album (se ainda não vendido)
	public void removeAlbum(ArrayList<Album> lista_albuns, Album album, String nome)
	{
		//Alterei aqui, porque se houver mais que um album, não se retira da lista
		//Apenas se reduz o número em -1.
		if (album.getUnidades() ==1)
		{
			lista_albuns.remove(album);			
		}
		else if (album.getUnidades()>1) {
			album.setUnidades(album.getUnidades()-1);
		}
		else
		{
			System.out.println("Não é possível remover o album porque já foi vendido.");
		}
	}
	//Pesquisar albuns por nome
	public Album visualizarAlbumNome (ArrayList<Album> lista_albuns, String nome)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if(lista_albuns.get(i).getNome() == nome) 
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por grupo
	public Album visualizarAlbumGrupo (ArrayList<Album> lista_albuns, String grupo)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if(lista_albuns.get(i).getGrupo() == grupo) 
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por Musicas
	public Album visualizarAlbumMusicas (ArrayList<Album> lista_albuns,  String[] musicas)
	{
		for (int i = 0; i<lista_albuns.size(); i++)
		{
			if(lista_albuns.get(i).getMusicas() == musicas) 
			{
				return lista_albuns.get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por genero
	public Album visualizarAlbumGenero (ArrayList<Album> lista_albuns,  String genero)
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
	//Ver todos os albuns
	public void listaAlbuns(ArrayList<Album> lista_albuns)
	{
        System.out.printf("Lista dos albuns:");
        for (int i =0; i< lista_albuns.size(); i++)
        {
        	System.out.printf("Album: %s Grupo: %s Musicas: %s Preço: %s  Unidades em stock: %s \n", lista_albuns.get(i).getNome(),lista_albuns.get(i).getGrupo(),lista_albuns.get(i).getMusicas(),lista_albuns.get(i).getPrice(),lista_albuns.get(i).getUnidades());
        }
	}

}
