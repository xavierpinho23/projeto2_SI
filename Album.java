package trabalho2_si;

public class Album
{
	private String nome;
	private String grupo;
	private String [] musicas;
	private String genero;
	private int price;
	private int unidades;
	
	public Album(String nome, String grupo, String[] musicas, String genero,int price, int unidades)
	{
		this.nome = nome;
		this.grupo = grupo;
		this.musicas = musicas;
		this.price = price;
		this.unidades = unidades;
		this.genero = genero;
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
		return "Album: " + 
				"Grupo: " + grupo + 
				"Musicas: " +  musicas +
				"Genero: " + genero +
				"Preço: " + price +
				"Quantidade: " + unidades;
	}
}