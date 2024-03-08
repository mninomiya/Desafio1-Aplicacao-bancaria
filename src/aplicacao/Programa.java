package aplicacao;

import java.util.Scanner;

import entidades.ContaBancaria;
import servicos.ServicoExcecao;


public class Programa {	 

	public static void main(String[] args) {
		
		String sair = "";
		Scanner sc = new Scanner(System.in);
		int contagem = 1;
		int tipo;
		int operacao;
		ContaBancaria contaBancaria = new ContaBancaria();
		String conta;
		
		UI.limparTela();
		UI.bemVindoTela();
		
		
		while(sair != "sair") {	 
			try {
				
				tipo = UI.doLogin(sc);
		        if (tipo != 0) {
		        	UI.limparTela();
		            System.out.println("Login bem-sucedido!");
		            conta = UI.buscaConta(sc);
		            operacao = UI.menuPrincipal(sc, tipo);
		            switch(operacao) {
		  		  case 1:
		  			  //Ver dados da conta
		  			  UI.limparTela();
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));  
		  		    break;
		  		  case 2:
		  			  // Deposito
		  			 
		  		    break;
		  		  case 3:
		  			  // Saque
		  			  
		  		    break;
		  		  case 4:
		  			  // Transferência
		  			  
		  		    break;
		  		  case 5:
		  			  // Alterar dados da conta
		  			 
		  		    break;
		  		  case 6:
		  			  // Alterar limite do cartão
		  			 
		  		    break;
		  		  case 7:
		  			  // Exportar dados de transações
		  			 
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
		            System.out.println("Operação: " + operacao);
		            
		        } else if (contagem == 3) {
		        		System.out.println("Usuário ou senha incorretos. Encerrando o programa.");
		        		System.exit(1);
		        	} else {
		        		System.out.println("Usuário ou senha incorretos. Você só tem mais "+ (3 - contagem) +" tentativas.");
		        		contagem++;
		        	}
		        	
		        
		        
				
			} 
			catch(ServicoExcecao e) {
				System.out.println(e.getMessage());
				//System.exit(1);    
			}	
		}
		sc.close();
	}
}
