package br.com.llduran.tratajson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compra
{
	@JsonProperty("codigo_passagem")
	private int codigoPassagem;

	@JsonProperty("nro_cartao")
	private String nroCartao;

	@JsonProperty("codigo_seguranca_cartao")
	private int codigoSegurancaCartao;

	@JsonProperty("valor_passagem")
	private String valorPassagem;

	@JsonProperty("data_viagem")
	private LocalDate dataViagem;
}
