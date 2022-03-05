package br.com.llduran.tratajson.util;

import br.com.llduran.tratajson.model.Compra;
import br.com.llduran.tratajson.model.CompraChave;
import br.com.llduran.tratajson.model.CompraFinalizada;

import java.util.List;

public class ListManager
{
	public static List<CompraFinalizada> combinaListas(List<Compra> compras, List<CompraChave> chavesCompras, List<CompraFinalizada> comprasFinalizadas)
	{
		int qtdCompras = compras.size();

		if(chavesCompras.size() == qtdCompras && comprasFinalizadas.size() == qtdCompras)
		{
			int i = 0;
			while(i < compras.size())
			{
				chavesCompras.get(i).setCompra(compras.get(i));
				comprasFinalizadas.get(i).setCompraChave(chavesCompras.get(i));
				i++;
			}
		}

		return comprasFinalizadas;
	}
}
