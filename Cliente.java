package trabalho2_si;

import java.util.ArrayList;

public class Cliente
{
	private String username;
	private String password;
	private int saldo;
    protected ArrayList<Album> lista_albuns_cliente = new ArrayList<Album>(); //albuns já comprados pelo cliente
    private ArrayList<Album> carrinho = new ArrayList<Album>(); //carrinho de compras
    //private ArrayList<Album> lista_albuns = new ArrayList<Album>(); //Todos os albuns

	public Cliente(String username, String password, int saldo, ArrayList<Album> lista_albuns_cliente)
	{
		this.username = username;
        this.password = password;
        this.saldo = saldo;
        this.lista_albuns_cliente = lista_albuns_cliente;
	}
	public ArrayList<Album> getCarrinho()
	{
		return carrinho;
	}
	public void addCarrinho(Album album )
	{
		this.carrinho.add(album);
	}
	public void removeCarrinho(Album album)
	{
		for (int i = 0; i < carrinho.size() ; i++)
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
	public void setLista_albuns_cliente(ArrayList<Album> lista_albuns_cliente)
	{
		this.lista_albuns_cliente = lista_albuns_cliente;
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
		
	//adiciona albuns ao carrinho
	public void adicionaAlbum(Loja loja ,Album album) 
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
			{
				if(album.getUnidades() == 1)
				{
					loja.removeAlbum(album);
					addCarrinho(album);
				}
				
			else if(album.getUnidades() > 1)
			{
				album.setUnidades(album.getUnidades()-1);
				addCarrinho(album);
			}
			else
			{
				System.out.println("Não é possível remover o album porque já não existe.");
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

