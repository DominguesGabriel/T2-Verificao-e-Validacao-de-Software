package com.locadora.locadora_automoveis.Models;

import com.locadora.locadora_automoveis.Tools.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Automovel {
    private int id;
    private String placa;
    private int ano;
    private double valorDiaria;
    private boolean disponivel = true;

    public Automovel(int id, String placa, int ano, double valorDiaria) {
        this.id = id;
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
    }

    public void alocar() {
        disponivel = false;
    }

    public boolean devolver() {
        return devolver(false);
    }

    public boolean devolver(boolean comAcidente) {
        if (comAcidente || (Constants.ANO_ATUAL - ano) > 5) {
            disponivel = false;
        } else {
            disponivel = true;
        }

        return disponivel;
    }
}
