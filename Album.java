package trabalho2_si;

public class Album
{
	private String nome;
	private String grupo;
	private String [] musicas;
	private String genero;
	private double price;
	private int unidades;

	public Album(String nome, String grupo, String[] musicas, String genero,double price, int unidades)
	{
		this.nome = nome;
		this.grupo = grupo;
		this.musicas = musicas;
		this.price = price;
		this.genero = genero;
		this.unidades = unidades;
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
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
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
		//Apresentação do Album
		return "Album: " + nome + "\n" + 
				"Grupo: " + grupo + "\n" +
				"Musicas: " + musicas + "\n" +
				"Genero: " + genero + "\n" +
				"Preço: " + price + "\n" + 
				"Quantidade: " + unidades +"\n";
	}

}