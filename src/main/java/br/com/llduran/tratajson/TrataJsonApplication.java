package br.com.llduran.tratajson;

import br.com.llduran.tratajson.model.Compra;
import br.com.llduran.tratajson.model.CompraChave;
import br.com.llduran.tratajson.model.CompraFinalizada;
import br.com.llduran.tratajson.util.FileHandler;
import br.com.llduran.tratajson.util.ListManager;
import br.com.llduran.tratajson.util.ObjectManipulation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class TrataJsonApplication implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(TrataJsonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("=========================================================================================================================================================");

		// Monta objeto CompraFinalizada
		System.out.println("Monta objeto CompraFinalizada");
		CompraFinalizada  compraFinalizada = montaObjetoCompra();
		System.out.println(compraFinalizada);
		System.out.println("=========================================================================================================================================================");

		// Serializa Objeto
		System.out.println("Serializa Objeto");
		String jsonFinalizado = ObjectManipulation.serializa(compraFinalizada);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");

		// Desserializa JSON em Objeto
		System.out.println("Desserializa JSON em Objeto");
		CompraFinalizada  compraFinalizadaDesserializada = ObjectManipulation.desserializa(jsonFinalizado);
		System.out.println(compraFinalizadaDesserializada);
		System.out.println("=========================================================================================================================================================");

		// Consome arquivos JSON
		System.out.println("Consome arquivos JSON");
		List<CompraFinalizada> compraFinalizadaList = consomeJson();

		// Serializa lista
		System.out.println("Serializa lista");
		jsonFinalizado = ObjectManipulation.serializa(compraFinalizadaList);

		// Salva JSON
		System.out.println("Salva JSON");
		FileHandler.writeStream("D:\\GitHub\\TrataJson\\CompraFinalizadaFinal.json", jsonFinalizado);
		System.out.println("=========================================================================================================================================================");

		// Desserializa Array JSON em uma Lista Genérica
		System.out.println("Desserializa Array JSON em uma Lista Genérica");
		List<CompraFinalizada> compraFinalizadas = (List<CompraFinalizada>)ObjectManipulation.consomeArrayGenerics(jsonFinalizado, CompraFinalizada.class);
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

	private List<CompraFinalizada> consomeJson() throws IOException
	{
		String fileContent;

		try
		{
			// Lê arquivo de Array JSON de Compras e Desserializa em uma Lista de Compras
			System.out.println("Lê arquivo de Array JSON de Compras e Desserializa em uma Lista de Compras");
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Compra.json");
			List<Compra> compraList = (List<Compra>) ObjectManipulation.consomeArray(fileContent, "Compra");
			System.out.println(compraList);
			System.out.println("=========================================================================================================================================================");

			// Lê arquivo de Array JSON de Chaves de Compras e Desserializa em uma Lista de Chaves de Compras
			System.out.println("Lê arquivo de Array JSON de Chaves de Compras e Desserializa em uma Lista de Chaves de Compras");
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraChave.json");
			List<CompraChave> compraChaveList = (List<CompraChave>) ObjectManipulation.consomeArray(fileContent, "CompraChave");
			System.out.println(compraChaveList);
			System.out.println("=========================================================================================================================================================");

			// Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas
			System.out.println("Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas");
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraFinalizada.json");
			List<CompraFinalizada> compraFinalizadaList = (List<CompraFinalizada>) ObjectManipulation.consomeArray(fileContent, "CompraFinalizada");
			System.out.println(compraFinalizadaList);
			System.out.println("=========================================================================================================================================================");

			// Monta Lista de Compras Finalizadas utilizando dados das 3 listas de objeto
			System.out.println("Monta Lista de Compras Finalizadas utilizando dados das 3 listas de objeto");
			compraFinalizadaList = ListManager.combinaListas(compraList, compraChaveList, compraFinalizadaList);
			System.out.println(compraFinalizadaList);
			System.out.println("=========================================================================================================================================================");

			return compraFinalizadaList;
		}
		catch (IOException e)
		{
			throw new IOException("Erro de processamento", e);
		}
	}
}
