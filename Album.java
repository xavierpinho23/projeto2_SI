package trabalho2_si;

public class Album
{
	private String nome;
	private String grupo;
	private String[] musicas;
	private String genero;
	private double price;
	private int unidades;
	
	//Construtor para definir as propriedades do Album
	public Album(String nome, String grupo, String[] musicas, String genero, double price, int unidades)
	{
		this.nome = nome;
		this.grupo = grupo;
		this.musicas = musicas;
		this.price = price;
		this.genero = genero;
		this.unidades = unidades;
	}
	//M�todo to get para aceder ao G�nero do Album
	public String getGenero()
	{
		return genero;
	}

	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	//M�todo to get para aceder �s Unidades do Album
	public int getUnidades()
	{
		return unidades;
	}

	public void setUnidades(int unidades)
	{
		this.unidades = unidades;
	}
	//M�todo to get para aceder ao Pre�o do Album
	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
	//M�todo to get para aceder ao Nome do Album
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	//M�todo to get para aceder ao Grupo do Album
	public String getGrupo()
	{
		return grupo;
	}

	public void setGrupo(String grupo)
	{
		this.grupo = grupo;
	}
	//M�todo to get para aceder �s M�sicas do Album
	public String[] getMusicas()
	{
		return musicas;
	}

	public void setMusicas(String[] musicas)
	{
		this.musicas = musicas;
	}
	//M�todo que define a apresenta��o do Album
	public String toString()
	{
		return "Album: "       + nome     + "\n" + 
				"Grupo: "      + grupo    + "\n" + 
				"Musicas: "    + musicas  + "\n" + 
				"Genero: "     + genero   + "\n" +
				"Pre�o: "      + price    + "\n" + 
				"Quantidade: " + unidades + "\n";
	}

}