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
	private ServicoConta servicoConta = new ServicoConta();
	private List<Transacao> historicoTransacao;
	private String conta;
	
	
	
	public ContaBancaria () {
		this.numeroDaConta = numeroDaConta;
        this.numeroAgencia = numeroAgencia;
        this.saldo = saldo;
        this.limite = limite;
        this.tipoConta = tipoConta;
	}
	
	public String verContaBancaria(String numeroDaConta){
		String informacoesConta;
		
		informacoesConta = servicoConta.verConta(numeroDaConta);
		
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
	
	public void deposito(String conta, double quantidade, int tipo) {
	        if (quantidade > 0) {
	           // saldo += quantidade;
	            servicoConta.atualizaConta(conta, quantidade, tipo);
	            Transacao transacao = new Transacao(quantidade, this, this);
	           // historicoTransacao.add(transacao);
	        } else {
	            System.out.println("Quantidade inválida para o depósito");
	    }
	}
	public void saque(String conta, double quantidade, int tipo) {
			this.conta = verContaBancaria(conta);
			String[] parts = this.conta.split(";");
			this.saldo = Double.parseDouble(parts[2]);
			this.limite = Double.parseDouble(parts[3]);
	    if (quantidade > 0 && saldo + limite >= quantidade) {
	       // saldo -= quantidade;
	        servicoConta.atualizaConta(conta, quantidade, tipo);
	        Transacao transacao = new Transacao(quantidade, this, this);
	        //historicoTransacao.add(transacao);
	    } else {
	        System.out.println("Saldo insuficiente para a realização do saque");
	    }
	}
	public void changeLimit(double newLimit) {
	    if (newLimit >= 0) {
	        limite = newLimit;
	    } else {
	        System.out.println("Invalid limit value.");
	    }
	}
	
	public void transferencia(double quantidade, ContaBancaria contaDestino) {
	    if (quantidade > 0 && saldo + limite >= quantidade) {
	        saldo -= quantidade;
	        contaDestino.deposito("",quantidade, 0);
	        Transacao transacao = new Transacao(quantidade, this, contaDestino);
	        historicoTransacao.add(transacao);
	    } else {
	        System.out.println("Insufficient balance or invalid amount for transfer.");
	    }
	}
	
	public void exportTransactionHistory() {
	    // Implementação para exportar o histórico de transações, por exemplo, para um arquivo CSV.
	}

}