package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServicoLogin {
    private static final String ARQUIVO_CREDENCIAL = "C:\\Users\\mauricio.p.ninomiya\\eclipse-workspace\\Aplicacao_Bancaria\\src\\acessos\\Credenciais.txt";
    private static final String DELIMITADOR = ",";
    private static String Login;
    private static String Senha;
    private static String Tipo;

    public static int login(String usernameInput, String passwordInput) {
        loadCredentials();

        return Integer.parseInt(Tipo);
    }

    private static void loadCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CREDENCIAL))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(DELIMITADOR);
                if (parts.length == 3) {
                    Login = parts[0];
                    Senha = parts[1];
                    Tipo = parts[2];
                } else {
                	throw new ServicoExcecao("Arquivo de credenciais mal formatado.");
                }
            } else {
            	throw new ServicoExcecao("Arquivo de credenciais vazio.");
            }
        } catch (IOException e) {
        	throw new ServicoExcecao("Erro ao ler o arquivo de credenciais: " + e.getMessage());
        }
    }
}