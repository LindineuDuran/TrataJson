package br.com.llduran.tratajson.service;

import br.com.llduran.tratajson.model.Compra;
import br.com.llduran.tratajson.model.CompraChave;
import br.com.llduran.tratajson.model.CompraFinalizada;
import br.com.llduran.tratajson.util.FileHandler;
import br.com.llduran.tratajson.util.ListManager;
import br.com.llduran.tratajson.util.ObjectManipulation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CompraService
{
	public void serializaDesserializaCompra() throws JsonProcessingException
	{
		System.out.println("=========================================================================================================================================================");

		// Monta objeto CompraFinalizada
		System.out.println("Monta objeto CompraFinalizada");
		CompraFinalizada compraFinalizada = montaObjetoCompra();
		System.out.println(compraFinalizada);
		System.out.println("=========================================================================================================================================================");

		// Serializa Objeto
		System.out.println("Serializa Objeto do tipo CompraFinalizada");
		String jsonFinalizado = ObjectManipulation.serializa(compraFinalizada);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");

		// Desserializa JSON em Objeto
		System.out.println("Desserializa JSON em Objeto");
		CompraFinalizada  compraFinalizadaDesserializada = (CompraFinalizada) ObjectManipulation.desserializa(jsonFinalizado, CompraFinalizada.class);
		System.out.println(compraFinalizadaDesserializada);
		System.out.println("=========================================================================================================================================================");
	}

	public List<CompraFinalizada> consomeJsonDadosCompra() throws IOException
	{
		try
		{
			// Lê arquivo de Array JSON de Compras e Desserializa em uma Lista de Compras
			System.out.println("Lê arquivo de Array JSON de Compras e Desserializa em uma Lista de Compras");
			String fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Compra.json");
			List<Compra> compraList = (List<Compra>) ObjectManipulation.desserializaArray(fileContent, Compra.class);
			System.out.println(compraList);
			System.out.println("=========================================================================================================================================================");

			// Lê arquivo de Array JSON de Chaves de Compras e Desserializa em uma Lista de Chaves de Compras
			System.out.println("Lê arquivo de Array JSON de Chaves de Compras e Desserializa em uma Lista de Chaves de Compras");
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraChave.json");
			List<CompraChave> compraChaveList = (List<CompraChave>) ObjectManipulation.desserializaArray(fileContent, CompraChave.class);
			System.out.println(compraChaveList);
			System.out.println("=========================================================================================================================================================");

			// Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas
			System.out.println("Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas");
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraFinalizada.json");
			List<CompraFinalizada> compraFinalizadaList = (List<CompraFinalizada>) ObjectManipulation.desserializaArray(fileContent, CompraFinalizada.class);
			System.out.println(compraFinalizadaList);
			System.out.println("=========================================================================================================================================================");

			// Monta Lista de Compras Finalizadas utilizando dados das 3 listas de objeto
			System.out.println("Monta Lista de Compras Finalizadas utilizando dados das 3 listas de objeto");
			compraFinalizadaList = ListManager.combinaListas(compraList, compraChaveList, compraFinalizadaList);
			System.out.println(compraFinalizadaList);
			System.out.println("=========================================================================================================================================================");

			return compraFinalizadaList;
		}
		catch (IOException | ClassNotFoundException e)
		{
			throw new IOException("Erro de processamento", e);
		}
	}

	public void serializaDesserializaListaObjetos(List<CompraFinalizada> compraFinalizadaList)
			throws JsonProcessingException, ClassNotFoundException
	{
		// Serializa lista
		System.out.println("Serializa lista");
		String jsonFinalizado = ObjectManipulation.serializa((List<CompraFinalizada>) compraFinalizadaList);

		// Salva JSON
		System.out.println("Salva JSON");
		FileHandler.writeStream("D:\\GitHub\\TrataJson\\CompraFinalizadaFinal.json", jsonFinalizado);
		System.out.println("=========================================================================================================================================================");

		// Desserializa Array JSON em uma Lista Genérica
		System.out.println("Desserializa Array JSON em uma Lista Genérica");
		List<CompraFinalizada> compraFinalizadas = (List<CompraFinalizada>)ObjectManipulation.desserializaArray(jsonFinalizado, CompraFinalizada.class);
		System.out.println(compraFinalizadas);
		System.out.println("=========================================================================================================================================================");
	}

	private CompraFinalizada montaObjetoCompra()
	{
		// Cria uma Compra
		Compra compra = new Compra();
		compra.setCodigoPassagem(8437);
		compra.setNroCartao("630458 7554793371");
		compra.setCodigoSegurancaCartao(638);
		compra.setValorPassagem("R$66,45");

		LocalDate dataViagem = LocalDate.parse("24/01/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		compra.setDataViagem(dataViagem);

		// Cria uma CompraChave
		CompraChave compraChave = new CompraChave();
		compraChave.setChave("DBA18CD0-A11A-5E7D-D64E-6601093D4D27");
		compraChave.setCompra(compra);

		// Cria uma CompraFinalizada
		CompraFinalizada  compraFinalizada = new CompraFinalizada();
		compraFinalizada.setCompraChave(compraChave);
		compraFinalizada.setMensagem("Pagamento registrado com sucesso!");
		compraFinalizada.setPagamentoOK(true);

		return compraFinalizada;
	}
}
