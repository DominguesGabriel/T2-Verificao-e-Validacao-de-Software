package com.locadora.locadora_automoveis.Models;

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

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void alocar(boolean comAcidente) {
        if (comAcidente || (2023 - ano) > 5) {
            disponivel = false;
        } else {
            disponivel = true;
        }
    }

    public void devolver() {
        disponivel = true;
    }
}
