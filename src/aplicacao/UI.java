package aplicacao;

import java.util.ArrayList;
import java.util.List;
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
	private static final String DELIMITADOR = ";";
	
	
	public static void bemVindoTela() {
		System.out.println(ANSI_YELLOW+"Bem-vindo ao BM!"+ANSI_RESET);
		System.out.println();
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
		System.out.println("Escolha uma das Operações abaixo:");
		switch(tipo) {
		  case 1:
			  //Gerente
			  System.out.println("1 - Ver dados da conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("4 - Transferir");
			  System.out.println("5 - Criar conta bancária");
			  System.out.println("6 - Alterar limite");
			  System.out.println("7 - Exportar dados de transações");			  
			  System.out.println("8 - Voltar ao menu principal");
			  System.out.println("9 - Sair");
		    break;
		  case 2:
			  // Caixa
			  System.out.println("1 - Ver dados da Conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("4 - Transferir");
			  System.out.println("5 - Criar conta bancária");
			  System.out.println("8 - Voltar ao menu principal");
			  System.out.println("9 - Sair");
		    break;
		  default:
			  // Cliente
			  System.out.println("1 - Ver dados da Conta");
			  System.out.println("2 - Depósito");
			  System.out.println("3 - Saque");
			  System.out.println("4 - Transferir");
			  System.out.println("8 - Voltar ao menu principal");
			  System.out.println("9 - Sair");
			  break;
		}
		return sc.nextInt();
	}
	
	public static String buscaConta(Scanner sc) {
		 System.out.println("Por favor digite o número da conta");
		 return sc.nextLine();
	}


	public static void mostrarConta(String verContaBancaria) {
		String[] dados = verContaBancaria.split(DELIMITADOR);
        StringBuilder sb = new StringBuilder();
        double saldo = Double.parseDouble(dados[2]);
        double limite = Double.parseDouble(dados[3]);
        
        sb.append("    ==============================================\n");
        sb.append("    |    ").append(ANSI_YELLOW).append(String.format("%-40s", "         Detalhes da conta")).append(ANSI_RESET).append("|\n");
        sb.append("    ==============================================\n");
        sb.append("    |  ").append(String.format("%-56s", "Agencia: " + ANSI_CYAN + dados[1]+ ANSI_RESET + " Numero da conta: " + ANSI_CYAN + dados[0])).append(ANSI_RESET).append("|\n");
        sb.append("    |  ").append(String.format("%-47s", "Tipo de conta: " + ANSI_CYAN + dados[4])).append(ANSI_RESET).append("|\n");
        sb.append("    |  ").append(String.format("%-47s", "Saldo da conta: " + (saldo < 0 ? ANSI_RED : ANSI_CYAN) + String.format("R$ %.2f", saldo))).append(ANSI_RESET).append("|\n");
        sb.append("    |  ").append(String.format("%-47s", "Limite da conta: " + ANSI_CYAN + String.format("R$ %.2f", limite))).append(ANSI_RESET).append("|\n");
        sb.append("    ==============================================\n\n\n");

        System.out.println(sb.toString());
		
	}
	
	public static int questaoMenu(Scanner sc) {
		System.out.println("Selecione uma das opções para continuar");
		  System.out.println("8 - Voltar ao menu principal");
		  System.out.println("9 - Sair");
		  return sc.nextInt();
		
	}
	public static double deposito(Scanner sc) {
		System.out.println("Digite o valor a ser depositado");
		return sc.nextDouble();
		
	}
	
	public static double saque(Scanner sc) {
		System.out.println("Digite o valor a ser sacado");
		return sc.nextDouble();
		
	}
	
	public static double alterarLimite(Scanner sc) {
		System.out.println("Digite o valor do novo Limite");
		return sc.nextDouble();
		
	}
	
	public static List<Object> cadastrarCliente(Scanner sc) {
		
		System.out.println("*****************************************");
		System.out.println("*        Cadastro de Cliente            *");
		System.out.println("*****************************************");
		sc.nextLine();
		
		List<Object> dados = new ArrayList<>();

		System.out.println("Nome: ");
		dados.add(sc.nextLine());

		System.out.println("Data de Nascimento (dd/mm/aaaa): ");
		dados.add(sc.nextLine());

		System.out.println("CPF: ");
		dados.add(sc.nextLine());

		System.out.println("Endereço: ");
		dados.add(sc.nextLine());

		System.out.println("Número: ");
		dados.add(Integer.toString(sc.nextInt()));

		// Consumir a nova linha pendente após o nextInt()
		sc.nextLine();

		System.out.println("Complemento: ");
		dados.add(sc.nextLine());


		return dados;
	}
	
public static List<Object> cadastrarConta(Scanner sc) {
		
		System.out.println("*****************************************");
		System.out.println("*        Cadastro de Conta            *");
		System.out.println("*****************************************");
		sc.nextLine();
		
		List<Object> dados = new ArrayList<>();

		System.out.println("Numero da agencia: ");
		dados.add(sc.nextLine());

		System.out.println("Saldo: ");
		dados.add(sc.nextLine());

		System.out.println("limite: ");
		dados.add(sc.nextLine());

		System.out.println("Tipo de conta: ");
		dados.add(sc.nextLine());

		return dados;
	}
	
	public static double transferencia(Scanner sc) {
		
			System.out.println("Digite o valor da transferencia");
			return sc.nextDouble();
		
	}
}

