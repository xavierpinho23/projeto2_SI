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
		String nome = album.getNome();
		int unidades = album.getUnidades();
		
		if (visualizarAlbumNome(loja, nome) != null)
		{					
			//caso já exista 
			System.out.printf("O album que pretende adicionar já existe e foram adicionadas " + unidades + "unidades.");
			album.setUnidades(album.getUnidades() + unidades);
			loja.atualizaUnidades(album);
		}
		else
		{
			//Caso não exista, é adicionado à lista de albuns
			loja.adicionaAlbum(album);
			System.out.println("Album adicionado com sucesso!");
		}
	
	}
	
	//Fazer alterações no preço dos albuns (com histórico)
	public void updateAlbumPrice(Loja loja, Album album, int price)
	{
		if (album != null)
			//se existe
		{
			System.out.println("O preço do album foi atualizado com sucesso.");
			System.out.println("O album que custava " + album.getPrice() + "custa agora " + price);
			album.setPrice(price);
		}
		else
		//caso o album não exista
		{
			System.out.println("O album que pretende alterar o preço não existe.");
		}
	}
	
	//Remover um album
	public void eliminaAlbum(Loja loja ,Album album, int unidades) 
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
			{
				if(album.getUnidades() == unidades)
				{
					loja.removeAlbum(album);
				}
				
				else if(album.getUnidades() > unidades)
				{
					album.setUnidades(album.getUnidades()-unidades);
					loja.atualizaUnidades(album);
				}
				else
				{
					System.out.println("O album que pretende remover não existe.");
				}
				
			}
		}
	}
	//Pesquisar albuns por nome
	public Album visualizarAlbumNome (Loja loja, String nome)
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
	public Album visualizarAlbumGrupo (Loja loja, String grupo)
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
	public Album visualizarAlbumMusicas (Loja loja, String[] musicas)
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
	public Album visualizarAlbumGenero (Loja loja,  String genero)
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