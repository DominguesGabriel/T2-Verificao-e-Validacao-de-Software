package com.locadora.locadora_automoveis.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Locacao {
    private int id;
    private Date startData;
    private Date endDate;
    private Cliente cliente;
    private Automovel automovel;

    public int getDias() {
        if (endDate == null) {
            return -1; // Se a locação não foi finalizada, retorna 0 dias
        }

        long diff = endDate.getTime() - startData.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}
