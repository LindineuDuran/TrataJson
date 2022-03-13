package br.com.llduran.tratajson.service;

import br.com.llduran.tratajson.model.Filme;
import br.com.llduran.tratajson.model.Funcionario;
import br.com.llduran.tratajson.model.Pessoa;
import br.com.llduran.tratajson.util.FileHandler;
import br.com.llduran.tratajson.util.ObjectManipulation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ObjetosVariadosService
{
	public void serializaFilme() throws JsonProcessingException
	{
		Filme filme = new Filme();
		filme.setId(139);
		filme.setTitulo("Pede Foundation");
		filme.setDescricao("Aliquam gravida mauris ut mi. Duis risus odio, auctor vitae");
		filme.setAnoLancamento("2022");
		filme.setDuracao("0:55");
		filme.setTextoClassificacao("sit amet massa");

		// Serializa Objeto
		System.out.println("Serializa Objeto do tipo Filme");
		String jsonFinalizado = ObjectManipulation.serializa(filme);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");
	}

	public List<Filme> consomeDadosJsonFilme() throws IOException, ClassNotFoundException
	{
		// Lê arquivo de Array JSON de Filmes e Desserializa em uma Lista de Filmes
		System.out.println("Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas");
		String fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Filme.json");
		List<Filme> filmeList = (List<Filme>) ObjectManipulation.desserializaArray(fileContent, Filme.class);
		System.out.println(filmeList);
		System.out.println("=========================================================================================================================================================");

		return filmeList;
	}

	public void serializaFuncionario() throws JsonProcessingException
	{
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin("Wynne");
		funcionario.setPassword("VCF43WYM2OL");

		// Serializa Objeto
		System.out.println("Serializa Objeto do tipo Funcionario");
		String jsonFinalizado = ObjectManipulation.serializa(funcionario);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");
	}

	public List<Funcionario> consomeDadosJsonFuncionario() throws IOException, ClassNotFoundException
	{
		// Lê arquivo de Array JSON de Funcionarios e Desserializa em uma Lista de Funcionarios
		System.out.println("Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas");
		String fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Funcionario.json");
		List<Funcionario> funcionariosList = (List<Funcionario>) ObjectManipulation.desserializaArray(fileContent, Funcionario.class);
		System.out.println(funcionariosList);
		System.out.println("=========================================================================================================================================================");

		return funcionariosList;
	}

	public void serializaPessoa() throws JsonProcessingException
	{
		Pessoa pessoa = new Pessoa();
		pessoa.setId(13);
		pessoa.setPrimeiroNome("Joy");
		pessoa.setUltimoNome("Serrano");
		pessoa.setEmail("cras.interdum.nunc@hotmail.couk");
		pessoa.setAtivo(false);

		// Serializa Objeto
		System.out.println("Serializa Objeto do tipo Pessoa");
		String jsonFinalizado = ObjectManipulation.serializa(pessoa);
		System.out.println(jsonFinalizado);
		System.out.println("=========================================================================================================================================================");
	}

	public List<Pessoa> consomeDadosJsonPessoa() throws IOException, ClassNotFoundException
	{
		// Lê arquivo de Array JSON de Pessoas e Desserializa em uma Lista de Pessoas
		System.out.println("Lê arquivo de Array JSON de Compras Finalizadas e Desserializa em uma Lista de Compras Finalizadas");
		String fileContent = FileHandler.readFile("D:\\GitHub\\TrataJson\\Pessoa.json");
		List<Pessoa> pessoasList = (List<Pessoa>) ObjectManipulation.desserializaArray(fileContent, Pessoa.class);
		System.out.println(pessoasList);
		System.out.println("=========================================================================================================================================================");

		return pessoasList;
	}
}
