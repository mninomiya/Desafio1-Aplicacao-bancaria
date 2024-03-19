package entidades;

import java.util.Date;
import servicos.ServicoCliente;
import servicos.ServicoConta;

public class Cliente {
	
	String nome;
	int idCliente;
	String dataNascimento;
	private String cpf;
	private String endereco;
	private int numero;
	private String complemento;
	private ServicoCliente servicoCliente = new ServicoCliente();
	
	public Cliente(String nome, int idClient, String dataNasc, String cpf, String endereco, int numero, String complemento) {
		this.nome = nome;
		this.idCliente = idClient;
		this.dataNascimento = dataNasc;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public Cliente() {
		this.nome = "";
		this.idCliente = 0;
		this.dataNascimento = "";
		this.cpf = "";
		this.endereco = "";
		this.numero = 0;
		this.complemento = "";
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

	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public String verCliente(int idCliente){
		String informacoesCliente;
		
		informacoesCliente = servicoCliente.verCliente(idCliente);
		
		return informacoesCliente;
	}

}
