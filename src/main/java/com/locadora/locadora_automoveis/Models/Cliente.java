package com.locadora.locadora_automoveis.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    private String cpf;
    private String nome;
    private String telefone;
    private int id;
}
