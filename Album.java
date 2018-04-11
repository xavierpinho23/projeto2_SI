package trabalho2_si;

import java.util.ArrayList;

public class Album
{
	private String nome;
	private String grupo;
	private String[] musicas;
	private String genero;
	private double price;
	private int unidadesVendidas;
	private int unidadesCarrinho;
	private int unidadesDisponiveis;
	private int ID;
	private ArrayList<Double> historicoPreços = new ArrayList<Double>();
	
	//Construtor para definir as propriedades do Album
	public Album(String nome, String grupo, String[] musicas, String genero, double price, int unidadesDisponiveis)
	{
		this.nome = nome;
		this.grupo = grupo;
		this.musicas = musicas;
		this.price = price;
		this.genero = genero;
		this.unidadesDisponiveis = unidadesDisponiveis;
	}
	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	public int getUnidadesVendidas()
	{
		return unidadesVendidas;
	}
	public void setUnidadesVendidas(int unidadesVendidas)
	{
		this.unidadesVendidas = unidadesVendidas;
	}
	public int getUnidadesCarrinho()
	{
		return unidadesCarrinho;
	}
	public void setUnidadesCarrinho(int unidadesCarrinho)
	{
		this.unidadesCarrinho = unidadesCarrinho;
	}
	public int getUnidadesDisponiveis()
	{
		return unidadesDisponiveis;
	}
	public void setUnidadesDisponiveis(int unidadesDisponiveis)
	{
		this.unidadesDisponiveis = unidadesDisponiveis;
	}
	//Método para adicionar o novo preço de um Album ao Historico de Preços
	public void addHistoricoPreços(double price)
	{
		historicoPreços.add(price);
	}
	//Método para aceder ao Histórico de Preços
	public ArrayList<Double> getHistoricoPreços()
	{
		return historicoPreços;
	}
	//Método to get para aceder ao Género do Album
	public String getGenero()
	{
		return genero;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	//Método to get para aceder ao Preço do Album
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	//Método to get para aceder ao Nome do Album
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	//Método to get para aceder ao Grupo do Album
	public String getGrupo()
	{
		return grupo;
	}
	public void setGrupo(String grupo)
	{
		this.grupo = grupo;
	}
	//Método to get para aceder às Músicas do Album
	public String[] getMusicas()
	{
		return musicas;
	}
	public void setMusicas(String[] musicas)
	{
		this.musicas = musicas;
	}
	//Método que define a apresentação do Album
	public String toString()
	{
		return "Album: "         + nome     		+ "\n" + 
			   "Grupo: "         + grupo   		    + "\n" + 
			   "Musicas: "       + musicas  		+ "\n" + 
			   "Genero: "        + genero  		    + "\n" +
			   "Preço: "         + price    		+ "\n" +
			   "Preços antigo: " + historicoPreços  + "\n" + 
			   "Unidades disponiveis: "   + getUnidadesDisponiveis()	+ "\n" +
			   "Unidades vendidas: " 	  + getUnidadesVendidas()       + "\n";
	}
}