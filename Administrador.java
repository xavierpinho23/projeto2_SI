package trabalho2_si;

import java.util.Arrays;

//import java.util.ArrayList;

public class Administrador
{
	//M�todo para adicionar Albums � Loja
	public void addNewAlbum(Loja loja, Album album)
	{
		String nome = album.getNome();
		int unidades = album.getUnidades();

		if (visualizarAlbumNome(loja, nome) != null)
		{
			// caso j� exista
			System.out.printf("O album que pretende adicionar j� existe e foram adicionadas " + unidades + "unidades.");
			album.setUnidades(album.getUnidades() + unidades);
			loja.atualizaUnidades(album);
		}
		else
		{
			// Caso n�o exista, � adicionado � lista de albuns
			loja.adicionaAlbum(album);
			System.out.println("Album adicionado com sucesso!");
		}

	}
	//M�todo para alterar o Pre�o de um Album (com hist�rico)
	public void updateAlbumPrice(Loja loja, Album album, int price)
	{	
		//Se o Album existe
		if (album != null)
		{
			System.out.println("O pre�o do album foi atualizado com sucesso.");
			System.out.println("O album que custava " + album.getPrice() + "custa agora " + price);
			album.setPrice(price);
		}
		else
		//Se o Album n�o existe
		{
			System.out.println("O album que pretende alterar o pre�o n�o existe.");
		}
	}

	//M�todo para Remover um Album da Loja
	public void eliminaAlbum(Loja loja, Album album, int unidades)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).equals(album))
			{
				//Se as Unidades que se pretende remover s�o iguais �s unidades da Loja o Album � removido complemamente
				if (album.getUnidades() == unidades)
				{
					loja.removeAlbum(album);
				}
				//Se as Unidades que se pretende remover s�o inferiores �s que existem na Loja o Album permanece na Loja mas com um menos Unidades 

				else if (album.getUnidades() > unidades)
				{
					album.setUnidades(album.getUnidades() - unidades);
					loja.atualizaUnidades(album);
				}
				//Se o Album n�o existe na Loja
				else
				{
					System.out.println("O album que pretende remover n�o existe.");
				}

			}
		}
	}
	//M�todo para pesquisar Album por Nome na Loja
	public Album visualizarAlbumNome(Loja loja, String nome)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
			{
				return loja.getListaAlbuns().get(i);
			}
		}
		return null;
	}
	//M�todo para pesquisar Album por Grupo na Loja
	public Album visualizarAlbumGrupo(Loja loja, String grupo)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			// acrescentei o LowerCase() para as compara��es
			if (loja.getListaAlbuns().get(i).getGrupo().toLowerCase().equals(grupo.toLowerCase()))
			{
				return loja.getListaAlbuns().get(i);
			}
		}
		return null;
	}
	//M�todo para pesquisar Album por M�sica na Loja
	public Album visualizarAlbumMusicas(Loja loja, String musica)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			String[] musicas = loja.getListaAlbuns().get(i).getMusicas();
			for (int j = 0; j < loja.getListaAlbuns().size(); j++)
			{
				if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
				{
					return loja.getListaAlbuns().get(i);
				}
			}
		}
		return null;
	}
	//M�todo para pesquisar Album por G�nero na Loja
	public Album visualizarAlbumGenero(Loja loja, String genero)
	{
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			if (loja.getListaAlbuns().get(i).getGenero().toLowerCase().equals(genero.toLowerCase()))
			{
				return loja.getListaAlbuns().get(i);
			}
		}
		return null;
	}
	//M�todo para pesquisar todos os Albums da Loja
	public void listaAlbuns(Loja loja)
	{
		System.out.printf("Lista dos albuns: \n\n");
		for (int i = 0; i < loja.getListaAlbuns().size(); i++)
		{
			System.out.printf(
					"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPre�o: %s  \nG�nero: %s \nUnidades em stock: %s \n\n",
					loja.getListaAlbuns().get(i).getNome(), loja.getListaAlbuns().get(i).getGrupo(),
					Arrays.toString(loja.getListaAlbuns().get(i).getMusicas()),
					loja.getListaAlbuns().get(i).getPrice(), loja.getListaAlbuns().get(i).getGenero(),
					loja.getListaAlbuns().get(i).getUnidades());
		}
	}
}