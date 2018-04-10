package trabalho2_si;

import java.util.Arrays;

//import java.util.ArrayList;

public class Administrador
{
	// Adicionar albuns
	public void addNewAlbum(Loja loja, Album album)
	{
		String nome = album.getNome();
		int unidades = album.getUnidades();

		if (visualizarAlbumNome(loja, nome) != null)
		{
			// caso já exista
			System.out.printf("O album que pretende adicionar já existe e foram adicionadas " + unidades + "unidades.");
			album.setUnidades(album.getUnidades() + unidades);
			loja.atualizaUnidades(album);
		}
		else
		{
			// Caso não exista, é adicionado à lista de albuns
			loja.adicionaAlbum(album);
			System.out.println("Album adicionado com sucesso!");
		}

	}

	// Fazer alterações no preço dos albuns (com histórico)
	public void updateAlbumPrice(Loja loja, Album album, int price)
	{
		if (album != null)
		// se existe
		{
			System.out.println("O preço do album foi atualizado com sucesso.");
			System.out.println("O album que custava " + album.getPrice() + "custa agora " + price);
			album.setPrice(price);
		}
		else
		// caso o album não exista
		{
			System.out.println("O album que pretende alterar o preço não existe.");
		}
	}

	// Remover um album
	public void eliminaAlbum(Loja loja, Album album, int unidades)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			if (loja.getLista_albuns().get(i).equals(album))
			{
				if (album.getUnidades() == unidades)
				{
					loja.removeAlbum(album);
				}

				else if (album.getUnidades() > unidades)
				{
					album.setUnidades(album.getUnidades() - unidades);
					loja.atualizaUnidades(album);
				}
				else
				{
					System.out.println("O album que pretende remover não existe.");
				}

			}
		}
	}

	// Pesquisar albuns por nome
	public Album visualizarAlbumNome(Loja loja, String nome)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			// acrescentei o LowerCase() para as comparações
			if (loja.getLista_albuns().get(i).getNome().toLowerCase().equals(nome.toLowerCase()))
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}

	// Pesquisar albuns por grupo
	public Album visualizarAlbumGrupo(Loja loja, String grupo)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			// acrescentei o LowerCase() para as comparações
			if (loja.getLista_albuns().get(i).getGrupo().toLowerCase().equals(grupo.toLowerCase()))
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}

	// Pesquisar albuns por Musicas
	public Album visualizarAlbumMusicas(Loja loja, String musica)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			String[] musicas = loja.getLista_albuns().get(i).getMusicas();
			for (int j = 0; j < loja.getLista_albuns().size(); j++)
			{
				// acrescentei o LowerCase() para as comparações
				if (musicas[j].toLowerCase().equals(musica.toLowerCase()))
				{
					return loja.getLista_albuns().get(i);
				}
			}
		}
		return null;
	}

	// Pesquisar albuns por genero
	public Album visualizarAlbumGenero(Loja loja, String genero)
	{
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			// acrescentei o LowerCase() para as comparações
			if (loja.getLista_albuns().get(i).getGenero().toLowerCase().equals(genero.toLowerCase()))
			{
				return loja.getLista_albuns().get(i);
			}
		}
		return null;
	}

	// Ver todos os albuns
	public void listaAlbuns(Loja loja)
	{
		System.out.printf("Lista dos albuns: \n\n");
		for (int i = 0; i < loja.getLista_albuns().size(); i++)
		{
			System.out.printf(
					"Album: %s  \nGrupo: %s  \nMusicas: %s  \nPreço: %s  \nGénero: %s \nUnidades em stock: %s \n\n",
					loja.getLista_albuns().get(i).getNome(), loja.getLista_albuns().get(i).getGrupo(),
					Arrays.toString(loja.getLista_albuns().get(i).getMusicas()),
					loja.getLista_albuns().get(i).getPrice(), loja.getLista_albuns().get(i).getGenero(),
					loja.getLista_albuns().get(i).getUnidades());
		}
	}
}