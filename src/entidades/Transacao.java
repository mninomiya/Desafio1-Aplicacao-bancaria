package entidades;

import java.time.LocalDateTime;

public class Transacao {
    private double valor;
    private String contaOrigem;
    private String contaDestino;
    private String operacao;
    private LocalDateTime dataHora;

    public Transacao(double valor, String contaOrigem, String contaDestino, String operacao) {
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.operacao = operacao;
        this.dataHora = LocalDateTime.now();
    }

    public double getValor() {
        return valor;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }
    
    public String getOperacao() {
        return operacao;
    }
    public String getContaDestino() {
        return contaDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
