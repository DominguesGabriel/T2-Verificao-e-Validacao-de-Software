package com.locadora.locadora_automoveis.Services.InicializacaoDados;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class InicializaClientes implements CommandLineRunner {

    @Autowired
    private CadastroCliente cadastroCliente;

    @Override
    public void run(String... args) throws Exception {
        cadastroCliente.cadastrarCliente("12345678900", "João Silva", "5551999999999");
        cadastroCliente.cadastrarCliente("98765432100", "Maria Oliveira", "5551988888888");
        cadastroCliente.cadastrarCliente("45678912300", "Carlos Souza", "5551977777777");
        
        System.out.println("cadastro de clientes inicializado com sucesso!");
    }
}
