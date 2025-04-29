import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

package com.locadora.locadora_automoveis.Models;

public class Locacao {
    public class Locacao {
        private int id;
        private double valorDiaria;
        private LocalDate dataInicio;
        private LocalDate dataFim;
        private Cliente cliente;
        private Automovel automovel;

        public Locacao(int id, double valorDiaria, LocalDate dataInicio, LocalDate dataFim, Cliente cliente, Automovel automovel) {
            this.id = id;
            this.valorDiaria = valorDiaria;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
            this.cliente = cliente;
            this.automovel = automovel;
        }

        public double calcularValor() {
            long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);
            double valorTotal = valorDiaria * dias;
            if (dias > 7) {
                valorTotal *= 0.95; // Aplicar desconto de 5%
            }
            return valorTotal;
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getValorDiaria() {
            return valorDiaria;
        }

        public void setValorDiaria(double valorDiaria) {
            this.valorDiaria = valorDiaria;
        }

        public LocalDate getDataInicio() {
            return dataInicio;
        }

        public void setDataInicio(LocalDate dataInicio) {
            this.dataInicio = dataInicio;
        }

        public LocalDate getDataFim() {
            return dataFim;
        }

        public void setDataFim(LocalDate dataFim) {
            this.dataFim = dataFim;
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
}
