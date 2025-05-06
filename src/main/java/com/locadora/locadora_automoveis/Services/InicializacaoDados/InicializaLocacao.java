package com.locadora.locadora_automoveis.Services.InicializacaoDados;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;

@Component
@Order(30000)
public class InicializaLocacao implements CommandLineRunner {
    @Autowired
    private CadastroLocacao cadastroLocacao;
    
    @Autowired
    private CadastroAutomovel cadastroAutomovel;

    @Autowired
    private CadastroCliente cadastroCliente;

    @Override
    public void run(String... args) throws Exception {
        cadastroLocacao.cadastrarLocacao(
            Date.from(Instant.ofEpochSecond(352398573)),
            cadastroCliente.getCliente(1),
            cadastroAutomovel.getAutomovel(1)
        ).setEndDate(Date.from(Instant.ofEpochSecond(352498573)));

        cadastroLocacao.cadastrarLocacao(
            Date.from(Instant.ofEpochSecond(23423443)),
            cadastroCliente.getCliente(2),
            cadastroAutomovel.getAutomovel(2)
        ).setEndDate(Date.from(Instant.ofEpochSecond(33424443)));

        System.out.println("cadastro de locação inicializado com sucesso!");
    }
}
