package com.locadora.locadora_automoveis.Models;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Locacao {
    private int id;
    private LocalDate startData;
    private LocalDate endDate;
    private Cliente cliente;
    private Automovel automovel;
    private double valorTotal;
    private int quantDias;

    public Locacao(int id,LocalDate startData,Cliente cliente, Automovel automovel, double valorTotal, int quantDias) {
        this.id = id;
        this.startData = startData;
        this.endDate = getEndDate();
        this.cliente = cliente;
        this.automovel = automovel;
        this.valorTotal = valorTotal;
        this.quantDias = quantDias;
    }

    public double calcularValor() {
        double valor = quantDias * automovel.getValorDiaria();
        if (quantDias > 7) {
            valor *= 0.95;
        }
        return valor;
    }

    public LocalDate getEndDate(){
        return startData.plusDays(quantDias);
    }
}
