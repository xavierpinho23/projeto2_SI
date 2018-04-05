package trabalho2_si;

//import java.util.ArrayList;

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
	public void addNewAlbum (Loja loja, Album album)
	{
		//Adicionar album à lista de albuns na Loja
		loja.adicionaAlbum(album);
	}
	
	//Fazer alterações no preço dos albuns (com histórico)
	public void updateAlbumPrice(Loja loja, Album album, int price)
	{
		album.setPrice(price);
	}
	
	//Remover um album
	public void eliminaAlbum(Loja loja ,Album album) 
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
			{
				if(album.getUnidades() == 1)
				{
					loja.removeAlbum(album);
				}
				
				else if(album.getUnidades() > 1)
				{
					album.setUnidades(album.getUnidades()-1);
				}
				else
				{
					System.out.println("O album que pretende remover não existe.");
				}
				
			}
		}
	}
	//Pesquisar albuns por nome
	public Album visualizarAlbumNome (Loja loja, Album album, String nome)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if(loja.getLista_albuns().get(i).getNome().equals(nome)) 
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por grupo
	public Album visualizarAlbumGrupo (Loja loja, Album album, String grupo)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if(loja.getLista_albuns().get(i).getGrupo().equals(grupo)) 
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por Musicas
	public Album visualizarAlbumMusicas (Loja loja, Album album, String[] musicas)
	{
		for (int i = 0; i<loja.getLista_albuns().size(); i++)
		{
			if(loja.getLista_albuns().get(i).getMusicas().equals(musicas)) 
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}
	//Pesquisar albuns por genero
	public Album visualizarAlbumGenero (Loja loja, Album album,  String genero)
	{
		for (int i = 0; i<loja.getLista_albuns().size(); i++)
		{
			if(loja.getLista_albuns().get(i).getGenero().equals(genero)) 
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}
	//Ver todos os albuns
	public void listaAlbuns(Loja loja)
	{
        System.out.printf("Lista dos albuns: \n");
        for (int i =0; i< loja.getLista_albuns().size(); i++)
        {
        	System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n", 
        			loja.getLista_albuns().get(i).getNome(),
        			loja.getLista_albuns().get(i).getGrupo(),
        			loja.getLista_albuns().get(i).getMusicas(),
        			loja.getLista_albuns().get(i).getPrice(),
        			loja.getLista_albuns().get(i).getGenero(),
        			loja.getLista_albuns().get(i).getUnidades());
        }
	}
}