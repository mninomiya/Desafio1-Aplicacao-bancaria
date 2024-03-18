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
		Cliente cliente;
		List<Object> dados = new ArrayList<>();
		Date data = new Date();
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
            while(conta.isBlank()) {
            	conta = UI.buscaConta(sc);
            	if (contaBancaria.verContaBancaria(conta).isBlank())
	  			  {
            		conta = "";
            		System.out.println("Nenhuma conta encontrada. Verifique o número da conta");
            		UI.limparTela();
	  			  }            	
            }
            
            UI.limparTela();
            UI.bemVindoTela();
            UI.mostrarConta(contaBancaria.verContaBancaria(conta));
            operacao = UI.menuPrincipal(sc, tipo);
            while(sair != "sair") {	
				switch(operacao) {
		  		  case 1:
		  			  //Ver dados da conta
		  			  UI.limparTela();
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta)); 
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 2:
		  			  // Deposito
		  			  UI.limparTela();
		  			  valor = UI.deposito(sc);
		  			  UI.limparTela();
		  			  contaBancaria.deposito(conta, valor, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  			  operacao = UI.questaoMenu(sc);
		  			  
		  		    break;
		  		  case 3:
		  			  // Saque
		  			  UI.limparTela();
		  			  valor = UI.saque(sc);
		  			  UI.limparTela();
		  			  contaBancaria.saque(conta, valor, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 4:
		  			  // Transferência
		  			  UI.limparTela();
		  			  contaDestino = "";
		  			  contagem = 1;
		  			  while(contaDestino.isBlank()) {
		  				contaDestino = UI.buscaConta(sc);
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
		  				 UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  				 System.out.println();
		  				 System.out.println("Dados da conta destino");
		  				 UI.mostrarConta(contaBancaria.verContaBancaria(contaDestino));
		  			  }
		  			  else
		  			  {
		  				System.out.println("Conta Inválida repita a operação");
		  			  }
		  			 
		  			  operacao = UI.questaoMenu(sc);
		  			  
		  		    break;
		  		  case 5:
						// Criar conta bancária
						UI.limparTela();
						dados = UI.cadastrarCliente(sc);
		  			 
		  			  	String nome = String.valueOf(dados.get(0));		  			  	
						int idCliente = (int)(data.getTime());
						Date dataNasc = new Date();
						try {
							dataNasc = sdf.parse((String)dados.get(1));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		  			  	UI.cadastrarConta(sc);
		  			  	numeroDaConta = 
		  			  	numeroAgencia = String.valueOf(dados.get(0));
		  			  	saldo = Double.parseDouble((String)dados.get(1));
		  			  	limite = Double.parseDouble((String)dados.get(2));
		  			  	tipoConta = String.valueOf(dados.get(3));
		  			  	ContaBancaria criarContaBancaria = new ContaBancaria(numeroDaConta, numeroAgencia, saldo, limite, tipoConta, idCliente);
		  			  	UI.questaoMenu(sc);
		  		    break;
		  		  case 6:
		  			  // Alterar limite do cartão
		  			  UI.limparTela();
		  			  valor = UI.alterarLimite(sc);
		  			  contaBancaria.alterarLimite(conta, valor, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 7:
		  			  // Exportar dados de transações
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
