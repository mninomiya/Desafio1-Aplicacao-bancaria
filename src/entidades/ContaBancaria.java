package entidades;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import servicos.ServicoConta;

public class ContaBancaria {
	private String numeroDaConta;
	private String numeroAgencia;
	private double saldo;
	private double limite;
	private String tipoConta;
	private ServicoConta servicoConta = new ServicoConta();
	private List<Transacao> historicoTransacao = new ArrayList<>();
	private String conta;
	private int idCliente;
	
	public ContaBancaria(String numeroDaConta, String numeroAgencia, double saldo, double limite, String tipoConta,
			int idCliente) {
		this.numeroDaConta = numeroDaConta;
		this.numeroAgencia = numeroAgencia;
		this.saldo = saldo;
		this.limite = limite;
		this.tipoConta = tipoConta;
		this.idCliente = idCliente;

	}

	public ContaBancaria() {
		this.idCliente = 0;
		this.numeroDaConta = "";
		this.numeroAgencia = "";
		this.saldo = 0;
		this.limite = 0;
		this.tipoConta = "";
	}

	public String verContaBancaria(String numeroDaConta){
		String informacoesConta;
		
		informacoesConta = servicoConta.verConta(numeroDaConta);
		
		return informacoesConta;
	}
	
	public void criarConta(Cliente cliente, ContaBancaria conta){
			
		 servicoConta.criarConta(cliente, conta);
		
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
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public String gerarNumero() {
		
		numeroDaConta = servicoConta.gerarNumero();
		return numeroDaConta;
	}
	
	public void deposito(String conta, double valor, int tipo) {
	        if (valor > 0) {
	            servicoConta.atualizaConta(conta, valor, tipo);
	            Transacao transacao = new Transacao(valor, conta, conta, "deposito");
	            System.out.println(transacao.getContaDestino());
	            historicoTransacao.add(transacao);
	        } else {
	            System.out.println("Quantidade inválida para o depósito");
	    }
	}
	public void saque(String conta, double valor, int tipo) {
			this.conta = verContaBancaria(conta);
			String[] parts = this.conta.split(";");
			this.saldo = Double.parseDouble(parts[2]);
			this.limite = Double.parseDouble(parts[3]);
	    if (valor > 0 && saldo + limite >= valor) {
	        servicoConta.atualizaConta(conta, valor, tipo);
	        if(saldo<0) {
	        	alterarLimite(conta, limite-valor, 6);
	        }
	        Transacao transacao = new Transacao(valor, conta, conta, "saque");
            historicoTransacao.add(transacao);
	    } else {
	    		System.out.println("Saldo insuficiente para a realização do saque.");
	    		
	    }
	}
	public void alterarLimite(String conta, double novoLimite, int tipo) {
		this.conta = verContaBancaria(conta);
		String[] parts = this.conta.split(";");
		this.saldo = Double.parseDouble(parts[2]);
	    if (novoLimite >= 0 && saldo > 0) {
	    	servicoConta.atualizaConta(conta, novoLimite, tipo);
	    } else {
	        System.out.println("Não foi possivel alterar seu limite!");
	        System.out.println("    - Verifique se o saldo é positivo");
	        System.out.println("    - Verifique se o valor digitado é positivo");
	    }
	}
	
	public void transferencia(String conta, double valor, String contaDestino) {
		this.conta = verContaBancaria(conta);
		String[] parts = this.conta.split(";");
		this.saldo = Double.parseDouble(parts[2]);
		this.limite = Double.parseDouble(parts[3]);
		LocalTime horaAtual = LocalTime.now();

        // Define os limites do intervalo de horas desejado
        LocalTime horaInicio = LocalTime.of(22, 0); // 23h
        LocalTime horaFim = LocalTime.of(6, 0);//06h
        
		if(valor>200 && horaAtual.isAfter(horaInicio) && horaAtual.isBefore(horaFim)) {		
			System.out.println("Valor inserido é maior que o valor permitido para esse horario");
		} else {
			if (valor > 0 && saldo + limite >= valor) { 	
		        servicoConta.atualizaConta(conta, valor, 3);
		        servicoConta.atualizaConta(contaDestino, valor, 2);
		        Transacao transacao = new Transacao(valor, conta, contaDestino, "transferencia");
	            historicoTransacao.add(transacao);
		    } else {
		    	System.out.println("Saldo insuficiente ou valor inválido para a transferência.");
		    }
		}
	}
	
	public void exportTransactionHistory() {
		String nomeArquivo = "historicoTransacoes.csv";
		String caminhoDownloads = System.getProperty("user.home") + "\\Downloads\\" + nomeArquivo;
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoDownloads))) {
            writer.println("Conta Origem, Conta Destino, Valor, Operação,  Data e Hora");
            for (Transacao transacao : historicoTransacao) {
            	writer.println(transacao.getContaOrigem() + "," + transacao.getContaDestino() + "," + transacao.getValor() + "," + transacao.getOperacao() + "," + transacao.getDataHora());
            }
            System.out.println();
            System.out.println("Transações foram exportadas para a pasta de Downloads com sucesso! Nome do arquivo gerado: " + nomeArquivo);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	

}