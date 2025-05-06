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
        long diff = endDate.getTime() - startData.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}
