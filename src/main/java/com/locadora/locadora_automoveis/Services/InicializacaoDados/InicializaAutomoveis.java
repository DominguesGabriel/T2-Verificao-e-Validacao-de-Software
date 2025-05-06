package com.locadora.locadora_automoveis.Services.InicializacaoDados;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializaAutomoveis implements CommandLineRunner {

    @Autowired
    private CadastroAutomovel cadastroAutomovel;

    @Override
    public void run(String... args) throws Exception {
        cadastroAutomovel.cadastrarAutomovel("ABC1234", 2020, 150.0);
        cadastroAutomovel.cadastrarAutomovel("DEF5678", 2019, 140.0);
        cadastroAutomovel.cadastrarAutomovel("GHI9012", 2021, 160.0);
        cadastroAutomovel.cadastrarAutomovel("JKL3456", 2018, 130.0);
        cadastroAutomovel.cadastrarAutomovel("MNO7890", 2022, 170.0);
        cadastroAutomovel.cadastrarAutomovel("PQR1234", 2017, 120.0);
        cadastroAutomovel.cadastrarAutomovel("STU5678", 2020, 155.0);
        cadastroAutomovel.cadastrarAutomovel("VWX9012", 2019, 145.0);
        cadastroAutomovel.cadastrarAutomovel("YZA3456", 2021, 165.0);
        cadastroAutomovel.cadastrarAutomovel("BCD7890", 2023, 180.0);
    }
}
