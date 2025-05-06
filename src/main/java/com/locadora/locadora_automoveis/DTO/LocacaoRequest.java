package com.locadora.locadora_automoveis.DTO;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
public class LocacaoRequest {
    private int idLocacao;
    private int idAutomovel;
    private int idCliente;
    private String dataInicial;

    public Locacao toLocacao(CadastroAutomovel cadastroAutomovel, CadastroCliente cadastroCliente) {
        Automovel automovel = cadastroAutomovel.getAutomovel(idAutomovel);
        Cliente cliente = cadastroCliente.getCliente(idCliente);

        return new Locacao(idLocacao, Date.from(Instant.parse(dataInicial)), null, cliente, automovel);
    }
}