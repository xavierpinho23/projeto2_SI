package trabalho2_si;

import java.util.ArrayList;

public class Loja
{
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	private ArrayList<Album> lista_albuns = new ArrayList<Album>();
	private ArrayList<Album> lista_vendidos = new ArrayList<Album>();
	
	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes)
	{
		this.clientes = clientes;
	}
	public ArrayList<Administrador> getAdministradores()
	{
		return administradores;
	}
	public void setAdministradores(ArrayList<Administrador> administradores)
	{
		this.administradores = administradores;
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
	public void setLista_vendidos(ArrayList<Album> lista_vendidos)
	{
		this.lista_vendidos = lista_vendidos;
	}
	
	public void removeAlbum (Album album)
	{
		for (int i = 0; i < getLista_albuns().size(); i++)
		{
			if (getLista_albuns().get(i).equals(album))
			{
				lista_albuns.remove(i);
			}
		}
	}
	public void adicionaAlbum (Album album)
	{
		for (int i = 0; i < getLista_albuns().size(); i++)
		{
			if (getLista_albuns().get(i).equals(album))
			{
				lista_albuns.add(album);
			}
		}
	}
    
    

    //Menu menu = new Menu();
        
//        String musicas1[] = {"Cigaro", "BYOB"};
//        String musicas2[] = {"olaola", "oleole"};
//        //String albuns_cliente1[] = {"yeeee","yoooo"};
//        
//
//        Album album1 = new Album("Toxicity","SOAD",musicas1,"Rock",5,10,true);
//        Administrador admin = new Administrador("admin1","123"); 
//        Cliente cliente = new Cliente("cliente1","321", 10,null);
//        
//        lista_albuns.add(album1);
//        administradores.add(admin);
//        clientes.add(cliente);
//        lista_albuns_cliente.add(album1);
//        
//         //Adicionar Albuns à loja através do administrador
//        admin.addNewAlbum(lista_albuns,"Mesmerize","SOAD",musicas2,"Metal",4,9,true);
//        
//        //Ver a lista de albuns
//        admin.listaAlbuns(lista_albuns);
//        
//        //Testar setters and getters
//        System.out.println("Grupo: " + album1.getGrupo());
//        System.out.println("Genero: " + album1.getGenero());
//        //System.out.println("Musicas: " + Arrays.deepToString(album1.getMusicas()));
//        //System.out.println("Musicas: " + album1.getMusica);
//
//        System.out.println("Nome: "+ album1.getNome());
//        System.out.println("Preço: "+ album1.getPrice());
//        System.out.println("Unidades: "+ album1.getUnidades());
//
//        //Remover o album da lista de albuns através do admin
//        admin.removeAlbum(lista_albuns, album1,"Toxicity");
//        //System.out.println("blabla" + lista_albuns);
//
//        //Cliente
//        System.out.println(cliente.findAlbunsName("Mesmerize"));
//        System.out.println(cliente.findAlbunsGroup("SOAD"));
//        System.out.println(cliente.findAlbunsGenero("Rock"));
//        System.out.println(cliente.getSaldo());
//        System.out.println(cliente.getLista_albuns_cliente());
//        System.out.println(cliente.getPassword());
//        System.out.println(cliente.getUsername());
	}
