package aplicacao;

import java.util.Scanner;

import servicos.ServicoLogin;

public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	public static void bemVindoTela() {
		System.out.println("Bem-vindo ao sistema bancário!");
	}
	
	
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	
	public static int doLogin(Scanner sc) {
			
	System.out.print("Digite seu login: ");
	String login = sc.nextLine();
	System.out.print("Digite sua senha: ");
	String senha = sc.nextLine();
	
	return ServicoLogin.login(login, senha);

	}
	
	public static int menuPrincipal(Scanner sc, int tipo) {
		bemVindoTela();
		System.out.println("Escolha uma das Operações abaixo:");
		switch(tipo) {
		  case 1:
			  //Gerente
			  System.out.println("1 - Ver dados da Conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("5 - Transferir");
			  System.out.println("5 - Alterar dados da Conta");
			  System.out.println("4 - Alterar limite do cartão");
			  System.out.println("5 - Exportar dados de transações");
		    break;
		  case 2:
			  // Caixa
			  System.out.println("1 - Ver dados da Conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("5 - Transferir");
			  System.out.println("5 - Alterar dados da Conta");
		    break;
		  default:
			  // Cliente
			  System.out.println("1 - Ver dados da Conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("5 - Transferir");
		}
		return sc.nextInt();
	}
}

