package trabalho2_si;

import java.util.ArrayList;
import java.util.Arrays;

public class Loja
{
	public static void main(String[] args)
	{
        
        String musicas1[] = {"Cigaro", "BYOB"};
        String musicas2[] = {"olaola", "oleole"};
        String albuns_cliente1[] = {"yeeee","yoooo"};

        
        Album album1 = new Album("Toxicity","SOAD",musicas1,"Rock",5,10);
        Administrador admin = new Administrador("admin1","123"); 
        Cliente cliente = new Cliente("cliente1","321", 10,albuns_cliente1);
        
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Administrador> administradores = new ArrayList<Administrador>();
        ArrayList<Album> lista_albuns = new ArrayList<Album>();
        //Menu menu = new Menu();
        
        lista_albuns.add(album1);
        administradores.add(admin);
        clientes.add(cliente);
        
 
        //Adicionar Albuns � loja atrav�s do administrador
        admin.addNewAlbum(lista_albuns,"Mesmerize","SOAD",musicas2,"Metal",4,9);
        
        //Colocar o album na lista de albuns
        admin.listaAlbuns(lista_albuns);
        
        //Testar setters and getters
        System.out.println("Grupo: " + album1.getGrupo());
        System.out.println("Genero: " + album1.getGenero());
        System.out.println("Musicas: " + Arrays.deepToString(album1.getMusicas()));
        System.out.println("Nome: "+ album1.getNome());
        System.out.println("Pre�o: "+ album1.getPrice());
        System.out.println("Unidades: "+ album1.getUnidades());

        //Remover o album da lista de albuns atrav�s do admin
        admin.removeAlbum(lista_albuns, album1);
	
        //Cliente
        cliente.findAlbunsName("Mesmerize");
        cliente.findAlbunsGroup("SOAD");
        cliente.findAlbunsGenero("Rock");
        
        
        
        
	}

}
