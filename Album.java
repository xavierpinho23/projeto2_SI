package trabalho2_si;

public class Album
{
	private String nome;
	private String grupo;
	private String [] musicas;
	private String genero;
	private int price;
	private int unidades;
	private boolean disponivel;
	
	public Album(String nome, String grupo, String[] musicas, String genero,int price, int unidades, boolean disponivel)
	{
		this.nome = nome;
		this.grupo = grupo;
		this.musicas = musicas;
		this.price = price;
		this.unidades = unidades;
		this.genero = genero;
		this.disponivel = disponivel;
	}
	public boolean isDisponivel()
	{
		return disponivel;
	}
	public void setDisponivel(boolean disponivel)
	{
		this.disponivel = disponivel;
	}
	public String getGenero()
	{
		return genero;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	public int getUnidades()
	{
		return unidades;
	}
	public void setUnidades(int unidades)
	{
		this.unidades = unidades;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getGrupo()
	{
		return grupo;
	}
	public void setGrupo(String grupo)
	{
		this.grupo = grupo;
	}
	public String[] getMusicas()
	{
		return musicas;
	}
	public void setMusicas(String[] musicas)
	{
		this.musicas = musicas;
	}
	
	public String toString()
	{
		//Apresenta��o do Album
		return "Album: " + 
				"Grupo: " + grupo + 
				"Musicas: " +  musicas +
				"Genero: " + genero +
				"Pre�o: " + price +
				"Quantidade: " + unidades +
				"Dispon�vel: " + disponivel;
	}
	
	
	
	
	
	
	
	
}