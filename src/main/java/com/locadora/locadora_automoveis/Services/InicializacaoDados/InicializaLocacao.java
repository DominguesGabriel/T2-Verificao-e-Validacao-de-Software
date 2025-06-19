package com.locadora.locadora_automoveis.Services.InicializacaoDados;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

        cadastroLocacao.cadastraLocacao(
                Instant.ofEpochSecond(352398573).atZone(ZoneId.systemDefault()).toLocalDate(),
                7,
                cadastroCliente.getCliente(1),
                cadastroAutomovel.getAutomovel(1)
        );

        cadastroLocacao.cadastraLocacao(
                Instant.ofEpochSecond(352398573).atZone(ZoneId.systemDefault()).toLocalDate(),
                7,
                cadastroCliente.getCliente(2),
                cadastroAutomovel.getAutomovel(2)
        );

        System.out.println("cadastro de locação inicializado com sucesso!");
    }
}
