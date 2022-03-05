package br.com.llduran.tratajson.util;

import br.com.llduran.tratajson.model.Compra;
import br.com.llduran.tratajson.model.CompraChave;
import br.com.llduran.tratajson.model.CompraFinalizada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

public class ObjectManipulation
{
	public static CompraFinalizada desserializa(String json) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

		CompraFinalizada compraFinalizadaDTO = mapper.readValue(json, CompraFinalizada.class);
		return compraFinalizadaDTO;
	}

	public static String serializa(CompraFinalizada compraFinalizada) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		String jsonFinalizado = mapper.writeValueAsString(compraFinalizada);
		return jsonFinalizado;
	}
	public static String serializa(List<CompraFinalizada> comprasFinalizadas) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		String jsonFinalizado = mapper.writeValueAsString(comprasFinalizadas);
		return jsonFinalizado;
	}

	public static List<?> consomeArray(String json, String classe) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

		List<?> lista = new ArrayList<>();
		switch (classe)
		{
		case "Compra":
			lista = mapper.readValue(json, new TypeReference<List<Compra>>(){});
			break;
		case "CompraChave":
			lista = mapper.readValue(json, new TypeReference<List<CompraChave>>(){});
			break;
		case "CompraFinalizada":
			lista = mapper.readValue(json, new TypeReference<List<CompraFinalizada>>(){});
			break;
		}
		return lista;
	}
}
