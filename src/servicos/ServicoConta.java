package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicoConta {
	
   private final String ARQUIVO_CONTA = "C:\\Users\\mauricio.p.ninomiya\\eclipse-workspace\\Aplicacao_Bancaria\\bin\\acessos\\Contas.txt";
   private final String ARQUIVO_TEMP = "C:\\Users\\mauricio.p.ninomiya\\eclipse-workspace\\Aplicacao_Bancaria\\bin\\acessos\\Temporario.txt";
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
   
   public void atualizaConta(String numeroDaConta, double quantidade, int operacao) {
	   File arquivo = new File(ARQUIVO_CONTA);
       File arquivoTemp = new File(ARQUIVO_TEMP);
       
       try (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
               BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))) {

              String linha;
              int contador = 0;

              while ((linha = reader.readLine()) != null) {
                  contador++;

                  // Verifica se é a linha que queremos atualizar
                  parts = linha.split(DELIMITADOR);
                  if (numeroDaConta.equals(parts[0])) {
                	  switch(operacao) {
            		  	case 2:
        		  			//deposito
        		  			double novoValor = Double.valueOf(parts[2])+ quantidade;
        		  			linha = parts[0]+";"+parts[1]+";"+novoValor+";"+parts[3]+";"+parts[4];
            		  	break;
            		  	case 3:
        		  			//saque
            		  		novoValor = Double.valueOf(parts[2]) - quantidade;
        		  			linha = parts[0]+";"+parts[1]+";"+novoValor+";"+parts[3]+";"+parts[4];
            		  	break;
            		  	case 4:
        		  			//transferir
            		  	break;
            		  	case 5:
        		  			//Alterar dados
            		  		
                		break;
            		  	case 6:
        		  			//Alterar limite da conta
            		  		novoValor = Double.valueOf(parts[3]) - quantidade;
        		  			linha = parts[0]+";"+parts[1]+";"+parts[2]+";"+novoValor+";"+parts[4];
                		break; 
                		default:
                		break;
                	  }
                	  
                  }
                  writer.write(linha);
                  writer.newLine();
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
       
	       // Exclui o arquivo original
	       if (!arquivo.delete()) {
	           System.out.println("Não foi possível excluir o arquivo original.");
	           return;
	       }
       
	    // Renomeia o arquivo temporário para o nome original
	       if (!arquivoTemp.renameTo(arquivo)) {
	    	   System.out.println();
	           System.out.println("Erro ao atualizar o seu saldo.");
	           System.out.println();
	           System.out.println();
	       } else {
	    	   System.out.println();
	           System.out.println("Saldo atualizado com sucesso.");
	           System.out.println();
	           System.out.println();
	           
	       }
   }


}
