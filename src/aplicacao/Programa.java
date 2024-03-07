package aplicacao;

import java.util.Scanner;

import servicos.ServicoExcecao;
import servicos.ServicoLogin;

public class Programa {	 

	public static void main(String[] args) {
		
		String sair = "";
		Scanner sc = new Scanner(System.in);
		int contagem = 1;
		int tipo;
		int operacao;
		
		UI.limparTela();
		UI.bemVindoTela();
		
		while(sair != "sair") {	 
			try {
				
				tipo = UI.doLogin(sc);
		        if (tipo != 0) {
		        	UI.limparTela();
		            System.out.println("Login bem-sucedido!");
		            operacao = UI.menuPrincipal(sc, tipo);
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
