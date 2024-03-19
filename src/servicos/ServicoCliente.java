package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServicoCliente {

	   private final String ARQUIVO_CLIENTE = "./acessos/Clientes.txt";
	   private final String DELIMITADOR = ";";
	   String[] parts = null;
	
	
	public String verCliente(int idCliente) {
	       try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CLIENTE))){
	    	   
	           String linha;
	           int contador = 0;
	           while ((linha = reader.readLine()) != null) {
	               parts = linha.split(DELIMITADOR);
	               if (idCliente == Integer.parseInt(parts[1])) {
	            	   if(parts.length == 7) {
	            		   return linha;	 
	            	   }
	            	   else {
	                       throw new ServicoExcecao("Arquivo de contas mal formatado.");
	                   } 
	               } 
	           }
	           reader.close();
	           return "";
	           
	       } catch (IOException e) {
	           throw new ServicoExcecao("Erro ao ler o arquivo de credenciais: " + e.getMessage());
	       }
	   }
}
