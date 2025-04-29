package com.locadora.locadora_automoveis.Services.InicializacaoDados;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

public class InicializaClientes {

    public static void inicializar() {
        CadastroCliente cadastroCliente = CadastroCliente.getInstance();

        cadastroCliente.cadastrarCliente("12345678900", "João Silva", "5551999999999");
        cadastroCliente.cadastrarCliente("98765432100", "Maria Oliveira", "5551988888888");
        cadastroCliente.cadastrarCliente("45678912300", "Carlos Souza", "5551977777777");
        cadastroCliente.cadastrarCliente("78912345600", "Ana Lima", "5551966666666");
        cadastroCliente.cadastrarCliente("32165498700", "Paulo Mendes", "5551955555555");
        cadastroCliente.cadastrarCliente("65498732100", "Fernanda Costa", "5551944444444");
        cadastroCliente.cadastrarCliente("14725836900", "Ricardo Alves", "5551933333333");
        cadastroCliente.cadastrarCliente("25836914700", "Juliana Pereira", "5551922222222");
        cadastroCliente.cadastrarCliente("36914725800", "Marcos Santos", "5551911111111");
        cadastroCliente.cadastrarCliente("74185296300", "Patrícia Nunes", "5551900000000");
    }
}
