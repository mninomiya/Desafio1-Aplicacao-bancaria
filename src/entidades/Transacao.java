package entidades;

import java.time.LocalDateTime;

public class Transacao {
    private double quantidade;
    private ContaBancaria contaOrigem;
    private ContaBancaria contaDestino;
    private LocalDateTime dataHora;

    public Transacao(double quantidade, ContaBancaria contaOrigem, ContaBancaria contaDestino) {
        this.quantidade = quantidade;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataHora = LocalDateTime.now();
    }

    public double getQuantidade() {
        return quantidade;
    }

    public ContaBancaria getContaOrigem() {
        return contaOrigem;
    }

    public ContaBancaria getContaDestino() {
        return contaDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
