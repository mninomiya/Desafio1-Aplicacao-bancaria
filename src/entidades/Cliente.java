package entidades;

import java.time.LocalDate;

public class Cliente {
	
	String nome;
	int idCliente;
	LocalDate dataNascimento;
	
	public Cliente(String nome, int idCliente, LocalDate dataNascimento) {
		this.nome = nome;
		this.idCliente = idCliente;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	


}
