package aplicacao;

import java.util.Scanner;

import entidades.ContaBancaria;
import servicos.ServicoExcecao;


public class Programa {	 

	public static void main(String[] args) {
		
		String sair = "";
		Scanner sc = new Scanner(System.in);
		int contagem = 1;
		int tipo=0;
		int operacao;
		double quantidade;
		ContaBancaria contaBancaria = new ContaBancaria();
		String conta;
		
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
            conta = UI.buscaConta(sc);
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
		  			  quantidade = UI.deposito(sc);
		  			  contaBancaria.deposito(conta, quantidade, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  			  operacao = UI.questaoMenu(sc);
		  			  
		  		    break;
		  		  case 3:
		  			  // Saque
		  			  UI.limparTela();
		  			  quantidade = UI.saque(sc);
		  			  contaBancaria.saque(conta, quantidade, operacao);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  			  operacao = UI.questaoMenu(sc);
		  		    break;
		  		  case 4:
		  			  // Transferência
		  			  UI.limparTela();
		  			  operacao = UI.questaoMenu(sc);
		  			  UI.mostrarConta(contaBancaria.verContaBancaria(conta));
		  		    break;
		  		  case 5:
		  			  // Alterar dados da conta
		  			  UI.limparTela();
		  			  UI.questaoMenu(sc);
		  		    break;
		  		  case 6:
		  			  // Alterar limite do cartão
		  			  UI.limparTela();
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
