package br.com.llduran.tratajson;

import br.com.llduran.tratajson.model.Compra;
import br.com.llduran.tratajson.model.CompraChave;
import br.com.llduran.tratajson.model.CompraFinalizada;
import br.com.llduran.tratajson.service.CompraService;
import br.com.llduran.tratajson.service.ObjetosVariadosService;
import br.com.llduran.tratajson.util.FileHandler;
import br.com.llduran.tratajson.util.ListManager;
import br.com.llduran.tratajson.util.ObjectManipulation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	CompraService compraService;

	@Autowired
	ObjetosVariadosService objetosVariadosService;

	public static void main(String[] args)
	{
		SpringApplication.run(TrataJsonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		testaObjetosCompra();

		testaObjetosVariados();
	}

	private void testaObjetosCompra() throws IOException, ClassNotFoundException
	{
		/*
		Monta objeto CompraFinalizada
		Serializa Objeto
		Desserializa JSON em Objeto
		*/
		compraService.serializaDesserializaCompra();

		// Consome arquivos JSON
		System.out.println("Consome arquivos JSON");
		List<CompraFinalizada> compraFinalizadaList = compraService.consomeJsonDadosCompra();

		/*
		Serializa lista
		Salva JSON
		Desserializa Array JSON em uma Lista Genérica
		*/
		compraService.serializaDesserializaListaObjetos(compraFinalizadaList);
	}

	private void testaObjetosVariados() throws IOException, ClassNotFoundException
	{
		objetosVariadosService.serializaFilme();
		objetosVariadosService.consomeDadosJsonFilme();

		objetosVariadosService.serializaFuncionario();
		objetosVariadosService.consomeDadosJsonFuncionario();

		objetosVariadosService.serializaPessoa();
		objetosVariadosService.consomeDadosJsonPessoa();
	}
}
