package trabalho2_si;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente
{
	private String username;
	private String password;
	private double saldo;
	private double divida;
	public ArrayList<Album> lista_albuns_cliente = new ArrayList<Album>(); //albuns já comprados pelo cliente
    private ArrayList<Album> carrinho = new ArrayList<Album>(); //carrinho de compras
    //private ArrayList<Album> lista_albuns = new ArrayList<Album>(); //Todos os albuns
    Scanner input = new Scanner(System.in);

	public Cliente(String username, String password)
	{
		this.username = username;
        this.password = password;
        this.saldo = 100;
        this.divida = 0;
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
	public void addLista_albuns_cliente(Album album )
	{
		this.lista_albuns_cliente.add(album);
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

	public double getSaldo()
	{
		return saldo;
	}

	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}
		
	//adiciona albuns ao carrinho
	public void adicionaAlbum(Loja loja ,Album album, int unidades) 
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
			{
				if(album.getUnidades() == unidades)
				{
					addCarrinho(album);
				}
				
				else if(album.getUnidades() > unidades)
				{
					Album album1 = album;
					album1.setUnidades(unidades);
 					addCarrinho(album1);
 					System.out.println("Foram adicionadas " + unidades + " ao carrinho");
				}
				else
				{
					System.out.println("Não é possível adicionar o album ao carrinho.");
				}	
				
			}
		}
	}
	
	public void finalizarCompra(Loja loja)
	{		
		//valor em compras dentro do carrinho
		for (int i = 0; i < carrinho.size(); i++)
		{
			this.divida = divida + getCarrinho().get(i).getPrice();
		}
		
		if (getSaldo() >= divida)
		{
			setSaldo(getSaldo() - divida);
			
			for (int i = 0; i < loja.getLista_albuns().size(); i++)
			{
				for (int j = 0; j < getCarrinho().size(); j++)
				{
					//acrescentei o LowerCase() para as comparações
				if (loja.getLista_albuns().get(i).getNome().toLowerCase().equals(getCarrinho().get(j).getNome().toLowerCase()))
				{
					if (getCarrinho().get(j).getUnidades() == loja.getLista_albuns().get(i).getUnidades())
					{
						loja.removeAlbum(loja.getLista_albuns().get(i));	//Remove o Album da loja
						loja.addLista_vendidos(getCarrinho().get(j));		//Adiciona à lista de albuns vendidos
						addLista_albuns_cliente(getCarrinho().get(j)); 		//adiciona à lista pessoal
						removeCarrinho(getCarrinho().get(j)); 				//remove do carrinho
						
						System.out.println("Álbum [ " + getCarrinho().get(j) + " ] comprado com sucesso.");
					}
					
					else if(getCarrinho().get(j).getUnidades() < loja.getLista_albuns().get(i).getUnidades())
					{
						Album album = loja.getLista_albuns().get(i);
					
						album.setUnidades(album.getUnidades() - getCarrinho().get(j).getUnidades()); //atualiza unidades do album na loja
						
						loja.atualizaUnidades(album);
						loja.addLista_vendidos(getCarrinho().get(j));		//Adiciona à lista de albuns vendidos
						addLista_albuns_cliente(getCarrinho().get(j)); 		//adiciona à lista pessoal
						removeCarrinho(getCarrinho().get(j)); 				//remove do carrinho
						
						System.out.println("Álbum [ " + getCarrinho().get(j) + " ] comprado com sucesso.");

					}
				}					
				}
			}	
		}	
	}
	//Pesquisar albuns por nome
		public void visualizarAlbumNome (Loja loja, String nome)
		{
			boolean existe = false;
			
			System.out.printf("Lista dos albuns: \n");
	        for (int i =0; i< loja.getLista_albuns().size(); i++)
	        {
				//acrescentei o LowerCase() para as comparações
	        	if(loja.getLista_albuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
	        	{
	        	System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n ID: %s \n", 
	        			loja.getLista_albuns().get(i).getNome(),
	        			loja.getLista_albuns().get(i).getGrupo(),
	        			loja.getLista_albuns().get(i).getMusicas(),
	        			loja.getLista_albuns().get(i).getPrice(),
	        			loja.getLista_albuns().get(i).getGenero(),
	        			loja.getLista_albuns().get(i).getUnidades(),
	        			i+1);
	        	existe = true;
	        	}		
	        }
	        
	        if (existe == false)
	        {
	        	System.out.println("Não existe nenhum Album com esse nome");
	        }
	        
	        if (existe == true)
	        {
	        	System.out.println("Deseja comprar algum Álbum? [1] -> Sim");
	        	int opcao = input.nextInt();
	        	input.nextLine();
	        
	        	if (opcao == 1)
	        	{
	        		System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
	        		int ID = input.nextInt();
	        		input.nextLine();
	        	
	        		System.out.println("Indique o numero de unidades que deseja comprar:");
	        		int unidades = input.nextInt();
	        		input.nextLine();
	        	
	        		Album album = loja.getLista_albuns().get(ID-1);
	        		adicionaAlbum(loja, album, unidades);      	 	
	        	}

	        }
		}
		//Pesquisar albuns por grupo
		public void visualizarAlbumGrupo (Loja loja, String grupo)
		{
			boolean existe = false;
		
			System.out.printf("Lista dos albuns: \n");
			for (int i =0; i< loja.getLista_albuns().size(); i++)
			{
				//acrescentei o LowerCase() para as comparações
				if(loja.getLista_albuns().get(i).getNome().toLowerCase().equals(grupo.toLowerCase()))
				{
					System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n ID: %s \n", 
							loja.getLista_albuns().get(i).getNome(),
							loja.getLista_albuns().get(i).getGrupo(),
							loja.getLista_albuns().get(i).getMusicas(),
							loja.getLista_albuns().get(i).getPrice(),
							loja.getLista_albuns().get(i).getGenero(),
							loja.getLista_albuns().get(i).getUnidades(),
							i+1);
					existe = true;
				}
						
			}
			if (existe == false)
		       {
		           System.out.println("Não existe nenhum Album desse grupo.");
		       }
        
			if (existe == true)
			{
				System.out.println("Deseja comprar algum Álbum? [1] -> Sim");
				int opcao = input.nextInt();
				input.nextLine();
        
				if (opcao == 1)
				{
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					int ID = input.nextInt();
					input.nextLine();
	        	
	        		System.out.println("Indique o numero de unidades que deseja comprar:");
	        		int unidades = input.nextInt();
	        		input.nextLine();
	        	
	        		Album album = loja.getLista_albuns().get(ID-1);
	        		adicionaAlbum(loja, album, unidades);      	 	
				}

			}
		}
		//Pesquisar albuns por Musicas
		public void visualizarAlbumMusicas (Loja loja,String musica)
		{
			boolean existe = false;
			
			for (int i = 0; i<loja.getLista_albuns().size(); i++)
			{
				String[] musicas = loja.getLista_albuns().get(i).getMusicas();
				for (int j = 0; j<loja.getLista_albuns().size(); j++) 
				{
					//acrescentei o LowerCase() para as comparações
					if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
					{
					System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n ID: %s \n", 
							loja.getLista_albuns().get(i).getNome(),
							loja.getLista_albuns().get(i).getGrupo(),
							loja.getLista_albuns().get(i).getMusicas(),
							loja.getLista_albuns().get(i).getPrice(),
							loja.getLista_albuns().get(i).getGenero(),
							loja.getLista_albuns().get(i).getUnidades(),
							i+1);
					existe = true;
					}
				}
			}
			if (existe == false)
			{
				System.out.println("Não existe a música que procura.");
		    }
				
			if (existe == true)
			{
				System.out.println("Deseja comprar algum Álbum? [1] -> Sim");
				int opcao = input.nextInt();
				input.nextLine();
	        
				if (opcao == 1)
				{
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					int ID = input.nextInt();
					input.nextLine();
		        	
		        	System.out.println("Indique o numero de unidades que deseja comprar:");
		        	int unidades = input.nextInt();
		        		input.nextLine();
		        	
		        	Album album = loja.getLista_albuns().get(ID-1);
		        	adicionaAlbum(loja, album, unidades);      	 	
				}
			}
		}
		//Pesquisar albuns por genero
		public void visualizarAlbumGenero (Loja loja,  String genero)
		{
			boolean existe = false;
			
			System.out.printf("Lista dos albuns: \n");
			for (int i =0; i< loja.getLista_albuns().size(); i++)
			{
				if(loja.getLista_albuns().get(i).getNome().toLowerCase().equals(genero.toLowerCase()))
				{
					System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n ID: %s \n", 
							loja.getLista_albuns().get(i).getNome(),
							loja.getLista_albuns().get(i).getGrupo(),
							loja.getLista_albuns().get(i).getMusicas(),
							loja.getLista_albuns().get(i).getPrice(),
							loja.getLista_albuns().get(i).getGenero(),
							loja.getLista_albuns().get(i).getUnidades(),
							i+1);
					existe = true;
				}
				else
				{
					System.out.println("Não existe nenhum Album do género que procura.");
				}		
			}
        
			if (existe)
			{
				System.out.println("Deseja comprar algum Álbum? [1] -> Sim");
				int opcao = input.nextInt();
				input.nextLine();
        
				if (opcao == 1)
				{
					System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
					int ID = input.nextInt();
					input.nextLine();
	        	
	        		System.out.println("Indique o numero de unidades que deseja comprar:");
	        		int unidades = input.nextInt();
	        		input.nextLine();
	        	
	        		Album album = loja.getLista_albuns().get(ID-1);
	        		adicionaAlbum(loja, album, unidades);      	 	
				}

			}
		}
		//Ver todos os albuns
		public void listaAlbuns(Loja loja)
		{
	        System.out.printf("Lista dos albuns: \n");
	        for (int i =0; i< loja.getLista_albuns().size(); i++)
	        {
	        	System.out.printf("Album: %s  \n Grupo: %s  \n Musicas: %s  \n Preço: %s  \n Unidades em stock: %s \n ID: %s \n", 
	        			loja.getLista_albuns().get(i).getNome(),
	        			loja.getLista_albuns().get(i).getGrupo(),
	        			loja.getLista_albuns().get(i).getMusicas(),
	        			loja.getLista_albuns().get(i).getPrice(),
	        			loja.getLista_albuns().get(i).getGenero(),
	        			loja.getLista_albuns().get(i).getUnidades(),
	        			i+1);
	        			
	        }
	        System.out.println("Deseja comprar algum Álbum? [1] -> Sim");
	        int opcao = input.nextInt();
	        input.nextLine();
	        
	        if (opcao == 1)
	        {
	        	System.out.println("Indique o ID do Album que pretende adicionar ao carrinho.");
	        	int ID = input.nextInt();
	        	input.nextLine();
	        	
	        	System.out.println("Indique o numero de unidades que deseja comprar:");
	        	int unidades = input.nextInt();
	        	input.nextLine();
	        	
	        	Album album = loja.getLista_albuns().get(ID-1);
	        	adicionaAlbum(loja, album, unidades);
	        	
	        	
	        }

	   }
		
		public String toString()
		//Apresentação do Cliente
		{
			return  "Username: " + getUsername() + "\n" + 
					"Password: " + getPassword() + "\n" +
					"Saldo: " + getSaldo() + "\n" +
					"Carrinho: " + getCarrinho() + "\n" +
					"Histórico de compras:" + getLista_albuns_cliente();
		}
	}

