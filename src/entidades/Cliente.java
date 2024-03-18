package entidades;

import java.time.LocalDate;
import java.util.Date;

public class Cliente {
	
	String nome;
	int idCliente;
	Date dataNascimento;
	private String cpf;
	private String endereco;
	private int numero;
	private String complemento;
	
	public Cliente(String nome, int idClient, Date dataNasc, String cpf, String endereco, int numero, String complemento) {
		this.nome = nome;
		this.idCliente = idClient;
		this.dataNascimento = dataNasc;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public int getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	


}
