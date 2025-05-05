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
}
