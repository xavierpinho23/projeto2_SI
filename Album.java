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
	private ArrayList<Double> historicoPre�os = new ArrayList<Double>();
	
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
	//M�todo para adicionar o novo pre�o de um Album ao Historico de Pre�os
	public void addHistoricoPre�os(double price)
	{
		historicoPre�os.add(price);
	}
	//M�todo para aceder ao Hist�rico de Pre�os
	public ArrayList<Double> getHistoricoPre�os()
	{
		return historicoPre�os;
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
		return "Album: "         + nome     		+ "\n" + 
			   "Grupo: "         + grupo   		    + "\n" + 
			   "Musicas: "       + musicas  		+ "\n" + 
			   "Genero: "        + genero  		    + "\n" +
			   "Pre�o: "         + price    		+ "\n" +
			   "Pre�os antigo: " + historicoPre�os  + "\n" + 
			   "Unidades disponiveis: "   + getUnidadesDisponiveis()	+ "\n" +
			   "Unidades vendidas: " 	  + getUnidadesVendidas()       + "\n";
	}
}