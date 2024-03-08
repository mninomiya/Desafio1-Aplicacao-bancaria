package entidades;

import java.util.ArrayList;
import java.util.List;

import servicos.ServicoLogin;
import servicos.ServicoConta;

public class ContaBancaria {
	private String numeroDaConta;
	private String numeroAgencia;
	private double saldo;
	private double limite;
	private String tipoConta;
	ServicoConta sevicoConta = new ServicoConta();
	
	
	
	public ContaBancaria () {
		this.numeroDaConta = numeroDaConta;
        this.numeroAgencia = numeroAgencia;
        this.saldo = saldo;
        this.limite = limite;
        this.tipoConta = tipoConta;
	}
	
	public String verContaBancaria(String numeroDaConta){
		String informacoesConta;
		
		informacoesConta = sevicoConta.verConta(numeroDaConta);
		
		return informacoesConta;
	}
	

	public String getNumeroDaConta() {
		return numeroDaConta;
	}


	public String getNumeroAgencia() {
		return numeroAgencia;
	}


	public double getSaldo() {
		return saldo;
	}


	public double getLimite() {
		return limite;
	}


	public String getTipoConta() {
		return tipoConta;
	}

}