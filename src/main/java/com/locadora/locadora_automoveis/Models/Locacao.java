package com.locadora.locadora_automoveis.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Locacao {
    private int id;
    private int dias;
    private Cliente cliente;
    private Automovel automovel;

    public double calcularValor() {
        double valor = this.dias * this.automovel.getValorDiaria();
        if (this.dias > 7) {
            valor *= 0.95; // 5% de desconto para locações acima de 7 dias
        }
        return valor;
    }
}
