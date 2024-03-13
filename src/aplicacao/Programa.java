package aplicacao;

import java.util.Scanner;

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
		  			  // Alterar dados da conta
		  			  UI.limparTela();
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
