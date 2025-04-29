package com.locadora.locadora_automoveis.Models;

import java.time.temporal.ChronoUnit;
public class Locacao {
    private int id;
    private int dias;
    private Cliente cliente;
    private Automovel automovel;

    public Locacao(int id, Cliente cliente, Automovel automovel, int dias) {
        this.id = id;
        this.cliente = cliente;
        this.automovel = automovel;
        this.dias = dias;
    }

    public double calcularValor() {
        
        return this.dias * this.automovel.getValorDiaria();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
}
