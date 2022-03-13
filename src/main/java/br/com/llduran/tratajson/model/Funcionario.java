package br.com.llduran.tratajson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Funcionario
{
	@JsonProperty("login")
	private String login;

	@JsonProperty("password")
	private String password;
}
