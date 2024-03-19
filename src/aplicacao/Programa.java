package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Cliente;
import entidades.ContaBancaria;
import servicos.ServicoExcecao;


public class Programa {	 

	public static void main(String[] args) {
		
		String sair = "";
		Scanner sc = new Scanner(System.in);
		int contagem = 1;
		int tipo = 0;
		int operacao = 0;
		double valor = 0;
		ContaBancaria contaBancaria = new ContaBancaria();
		String conta = "";
		String contaDestino = "";
		int idCliente = 0;
		Cliente cliente = new Cliente();
		String dadosConta="";
		List<Object> dados = new ArrayList<>();
		Date data = new Date();
		String temp = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		UI.limparTela();
		UI.bemVindoTela();
		
			 
		try {
			 while(tipo == 0) {
				tipo = UI.doLogin(sc);
		        if (contagem > 3) {
	        		System.out.println("Usuário ou senha incorretos. Encerrando o programa.");
	        		System.exit(1);
	        	} else {
	        		System.out.println("Usuário ou senha incorretos. Você só tem mais "+ (3 - contagem) +" tentativas.");
	        		contagem++;
	        	}
			}
        	UI.limparTela();
            System.out.println("Login bem-sucedido!");
            UI.bemVindoTela();
            operacao = UI.menuPrincipal(sc, tipo);
            while(sair != "sair") {	
				switch(operacao) {
		  		  case 1:
		  			  //Ver dados da conta
		              while(conta.isBlank()) {
		              	conta = UI.digitaConta(sc, false);
		              	if (contaBancaria.verContaBancaria(conta).isBlank())
		  	  			  {
		              		conta = "";
		              		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
		              		UI.limparTela();
		  	  			  }            	
		              }		           
		  			  UI.limparTela();
		  			  dadosConta = contaBancaria.verContaBancaria(conta);
		  			  idCliente = Integer.valueOf(dadosConta.split(";")[5]);
		  			  UI.mostrarConta(dadosConta, cliente.verCliente(idCliente)); 
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 2:
		  			  // Deposito
		              while(conta.isBlank()) {
		              	conta = UI.digitaConta(sc, false);
		              	if (contaBancaria.verContaBancaria(conta).isBlank())
		  	  			  {
		              		conta = "";
		              		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
		              		UI.limparTela();
		  	  			  }            	
		              }
		  			  UI.limparTela();
		  			  valor = UI.deposito(sc);
		  			  UI.limparTela();
		  			  contaBancaria.deposito(conta, valor, operacao);
		  			  dadosConta = contaBancaria.verContaBancaria(conta);
		  			  idCliente = Integer.valueOf(dadosConta.split(";")[5]);

		  			  UI.mostrarConta(dadosConta, cliente.verCliente(idCliente));
		  			  operacao = UI.questaoMenu(sc);
		  			  
		  		    break;
		  		  case 3:
		  			  // Saque
			  			while(conta.isBlank()) {
			            	conta = UI.digitaConta(sc, false);
			            	if (contaBancaria.verContaBancaria(conta).isBlank())
				  			  {
			            		conta = "";
			            		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
			            		UI.limparTela();
				  			  }            	
			            }
		  			  UI.limparTela();
		  			  valor = UI.saque(sc);
		  			  UI.limparTela();
		  			  contaBancaria.saque(conta, valor, operacao);
		  			  dadosConta = contaBancaria.verContaBancaria(conta);
		  			  idCliente = Integer.valueOf(dadosConta.split(";")[5]);
		  			  UI.mostrarConta(dadosConta, cliente.verCliente(idCliente));
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 4:
		  			  // Transferência
			  			while(conta.isBlank()) {
			            	conta = UI.digitaConta(sc, false);
			            	if (contaBancaria.verContaBancaria(conta).isBlank())
				  			  {
			            		conta = "";
			            		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
			            		UI.limparTela();
				  			  }            	
			            }
		  			  UI.limparTela();
		  			  contaDestino = "";
		  			  contagem = 1;
		  			  while(contaDestino.isBlank()) {
		  				contaDestino = UI.digitaConta(sc, true);
						if (contaBancaria.verContaBancaria(contaDestino).isBlank())
						  {
							contaDestino = "";
							System.out.println("Nenhuma conta destino encontrada. Verifique o número da conta destino");
							UI.limparTela();
						  } 
						if(contagem > 5)
						{
							break;
						}
						contagem++;
		  			  }
		  			  if (!(contagem > 5) )
		  			  {
		  				 valor = UI.transferencia(sc); 
		  				 UI.limparTela();
		  				 contaBancaria.transferencia(conta, valor, contaDestino);
		  				 System.out.println();
		  				 System.out.println("Dados da conta origem");
		  				 dadosConta = contaBancaria.verContaBancaria(conta);
			  			 idCliente = Integer.valueOf(dadosConta.split(";")[5]);
		  				 UI.mostrarConta(dadosConta, cliente.verCliente(idCliente));
		  				 System.out.println();
		  				 System.out.println("Dados da conta destino");
		  				 dadosConta = contaBancaria.verContaBancaria(contaDestino);
			  			 int idClienteDestino = Integer.valueOf(dadosConta.split(";")[5]);
		  				 UI.mostrarConta(dadosConta, cliente.verCliente(idClienteDestino));
		  			  }
		  			  else
		  			  {
		  				System.out.println("Conta inválida repita a operação");
		  			  }
		  			 
		  			  operacao = UI.questaoMenu(sc);
		  			  
		  		    break;
		  		  case 5:
						// Criar conta bancária
						UI.limparTela();
						dados = UI.cadastrarCliente(sc);
		  			 
		  			  	String nome = String.valueOf(dados.get(0));		  			  	
						idCliente = (int)(data.getTime());
						String dataNasc = String.valueOf(dados.get(1));
						
						String cpf = String.valueOf(dados.get(2));
						String endereco = String.valueOf(dados.get(3));
						int numero = Integer.parseInt((String)dados.get(4));
						String complemento = String.valueOf(dados.get(5));
		  			
		  			
						cliente = new Cliente(nome, idCliente, dataNasc, cpf, endereco, numero, complemento);
						String numeroDaConta; 
						String numeroAgencia;
		  			  	double saldo;
		  			  	double limite;
		  			  	String tipoConta;
		  			  	dados = UI.cadastrarConta(sc);
		  			  	
		  			  	numeroDaConta = contaBancaria.gerarNumero();
		  			  	numeroAgencia = String.valueOf(dados.get(0));
		  			  	temp = String.valueOf(dados.get(1)).replace(",", ".");
		  			  	saldo = Double.parseDouble(temp);
		  			  	temp = String.valueOf(dados.get(2)).replace(",", ".");
		  			  	limite = Double.parseDouble(temp);
		  			  	tipoConta = String.valueOf(dados.get(3));
		  			  	contaBancaria = new ContaBancaria(numeroDaConta, numeroAgencia, saldo, limite, tipoConta, idCliente);
		  			  	contaBancaria.criarConta(cliente, contaBancaria);

		  			  	UI.mostrarConta(contaBancaria.verContaBancaria(numeroDaConta), cliente.verCliente(idCliente));
		  			  	
		  			  	operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 6:
		  			  // Alterar limite do cartão
		  			while(conta.isBlank()) {
		            	conta = UI.digitaConta(sc, false);
		            	if (contaBancaria.verContaBancaria(conta).isBlank())
			  			  {
		            		conta = "";
		            		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
		            		UI.limparTela();
			  			  }            	
		            }
		  			  UI.limparTela();
		  			  valor = UI.alterarLimite(sc);
		  			  contaBancaria.alterarLimite(conta, valor, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta), cliente.verCliente(idCliente));
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 7:
		  			  // Exportar dados de transações
			  			while(conta.isBlank()) {
			            	conta = UI.digitaConta(sc, false);
			            	if (contaBancaria.verContaBancaria(conta).isBlank())
				  			  {
			            		conta = "";
			            		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
			            		UI.limparTela();
				  			  }            	
			            }
		  			  UI.limparTela();
		  			  contaBancaria.exportTransactionHistory();
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 8:
		  			  // Voltar ao menu
		  			  UI.limparTela();
		  			  operacao = UI.menuPrincipal(sc, tipo);
		  		    break;
		  		  default:
		  			  // Sair
		  			  System.exit(1);
	            }
            }    
		} 
		catch(ServicoExcecao e) {
			System.out.println(e.getMessage());
			//System.exit(1);    
		}	
		
		sc.close();
	}
}
