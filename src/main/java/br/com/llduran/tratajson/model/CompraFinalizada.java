package br.com.llduran.tratajson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraFinalizada
{
	@JsonProperty("compra_chave")
	private CompraChave compraChave;

	@JsonProperty("mensagem")
	private String mensagem;

	@JsonProperty("pagamento_ok")
	private boolean pagamentoOK;
}
