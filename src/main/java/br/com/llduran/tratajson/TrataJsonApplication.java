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
		CompraFinalizada  compraFinalizada = montaObjetoCompra();
		System.out.println(compraFinalizada);
		System.out.println("=========================================================================================================================================================");

		// Serializa Objeto
		String jsonFinalizado = ObjectManipulation.serializa(compraFinalizada);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");

		// Desserializa Json em Objeto
		CompraFinalizada  compraFinalizadaNova = ObjectManipulation.desserializa(jsonFinalizado);
		System.out.println(compraFinalizadaNova);
		System.out.println("=========================================================================================================================================================");

		// Consome arquivos JSON
		List<CompraFinalizada> compraFinalizadaList = consomeJson();

		// Serializa lista
		jsonFinalizado = ObjectManipulation.serializa(compraFinalizadaList);

		// Salva Json
		FileHandler.writeStream("D:\\GitHub\\TrataJson\\CompraFinalizadaFinal.json", jsonFinalizado);
		String teste = "";
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
			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Compra.json");
			List<Compra> compraList = (List<Compra>) ObjectManipulation.consomeArray(fileContent, "Compra");
			System.out.println(compraList);
			System.out.println("=========================================================================================================================================================");

			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraChave.json");
			List<CompraChave> compraChaveList = (List<CompraChave>) ObjectManipulation.consomeArray(fileContent, "CompraChave");
			System.out.println(compraChaveList);
			System.out.println("=========================================================================================================================================================");

			fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\CompraFinalizada.json");
			List<CompraFinalizada> compraFinalizadaList = (List<CompraFinalizada>) ObjectManipulation.consomeArray(fileContent, "CompraFinalizada");
			System.out.println(compraFinalizadaList);
			System.out.println("=========================================================================================================================================================");

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
