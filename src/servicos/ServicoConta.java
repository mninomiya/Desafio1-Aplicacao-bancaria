package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicoConta {
	
   private final String ARQUIVO_CONTA = "C:\\Users\\mauricio.p.ninomiya\\eclipse-workspace\\Aplicacao_Bancaria\\src\\acessos\\Contas.txt";
   private final String DELIMITADOR = ";";
   
   private String numeroDaConta;
   private String numeroAgencia;
   private String saldo;
   private String limite;
   private String tipo;
   String[] parts = null;
	  


   public String verConta(String numeroDaConta) {
       try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTA))) {
           String linha;
           int contador = 0;
           while ((linha = reader.readLine()) != null) {
               parts = linha.split(DELIMITADOR);
               if (numeroDaConta.equals(parts[0])) {
            	   if(parts.length == 5) {
            		   return linha;	 
            	   }
            	   else {
                       throw new ServicoExcecao("Arquivo de contas mal formatado.");
                   } 
               } 
           }
           return null;
       } catch (IOException e) {
           throw new ServicoExcecao("Erro ao ler o arquivo de credenciais: " + e.getMessage());
       }
   }


}
