package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entidades.Cliente;
import entidades.ContaBancaria;


public class ServicoConta {
	
   private final String ARQUIVO_CONTA = "./acessos/Contas.txt";
   private final String ARQUIVO_TEMP = "./acessos/Temporario.txt";
   private final String ARQUIVO_CLIENTE = "./acessos/Clientes.txt";

   private final String DELIMITADOR = ";";
   
   private String numeroDaConta;
   private String numeroAgencia;
   private String saldo;
   private String limite;
   private String tipo;
   String[] parts = null;
	  


   public String verConta(String numeroDaConta) {
       try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTA))){
    	   
           String linha;
           int contador = 0;
           while ((linha = reader.readLine()) != null) {
               parts = linha.split(DELIMITADOR);
               if (numeroDaConta.equals(parts[0])) {
            	   if(parts.length == 6) {
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
   
   public void atualizaConta(String numeroDaConta, double quantidade, int operacao) {
	    File arquivo = new File(ARQUIVO_CONTA);
	    File arquivoTemp = new File(ARQUIVO_TEMP);

	    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))){
	    	
	        String linha;
	        int contador = 0;

	        while ((linha = reader.readLine()) != null) {
	            contador++;

	            // Verifica se é a linha que queremos atualizar
	            String[] parts = linha.split(DELIMITADOR);
	            if (numeroDaConta.equals(parts[0])) {
	                switch(operacao) {
	                    case 2:
	                        //deposito
	                        double novoValor = Double.valueOf(parts[2]) + quantidade;
	                        linha = parts[0] + ";" + parts[1] + ";" + novoValor + ";" + parts[3] + ";" + parts[4]+ ";" +parts[5];
	                        break;
	                    case 3:
	                        //saque
	                        novoValor = Double.valueOf(parts[2]) - quantidade;
	                        linha = parts[0] + ";" + parts[1] + ";" + novoValor + ";" + parts[3] + ";" + parts[4]+ ";" +parts[5];
	                        break;
	                    case 6:
	                        //Alterar limite da conta
	                        linha = parts[0] + ";" + parts[1] + ";" + parts[2] + ";" + quantidade + ";" + parts[4]+ ";" + parts[5];
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
	        System.out.println("Erro ao atualizar o seu saldo.");
	    } else {
	        System.out.println("Saldo atualizado com sucesso.");
	    }
	}


	public void criarConta(Cliente cliente, ContaBancaria conta) {
		 try (BufferedWriter writerCliente = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTE, true));
				 BufferedWriter writerConta = new BufferedWriter(new FileWriter(ARQUIVO_CONTA, true))){

			 writerCliente.newLine();
			 String linhaNova = cliente.getNome()+";"+cliente.getIdCliente()+";"+cliente.getDataNascimento()+";"+cliente.getCpf()+";"+cliente.getEndereco()+";"+String.valueOf(cliente.getNumero())+";"+cliente.getComplemento();
			 writerCliente.write(linhaNova);
			 writerCliente.close();
	         
        	 writerConta.newLine(); 
			 linhaNova = conta.getNumeroDaConta()+";"+conta.getNumeroAgencia()+";"+conta.getSaldo()+";"+conta.getLimite()+";"+conta.getTipoConta()+";"+String.valueOf(conta.getIdCliente());
			 writerConta.write(linhaNova);
			 writerConta.close();
	         
	         System.out.println("Conta adicionada com sucesso.");
	         
	     } catch (IOException e) {
	    	 System.err.println("Erro ao adicionar nova conta: " + e.getMessage());
	     }
	 }

	public String gerarNumero() {
		String linha = "";
		int numero = 0;
		try(BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTA))) {
			
            
           
            while ((linha = reader.readLine()) != null) {
            	 parts = linha.split(DELIMITADOR);
       // Divida a linha em números com base em espaços em branco
                if (numero < Integer.valueOf(parts[0].replace("BM", "")))
                {
                	numero = Integer.valueOf(parts[0].replace("BM", ""));
                }
            }
           numero++;
           
           reader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
		linha = "BM"+String.format("%05d", numero);
        return linha;
	}
	
}



