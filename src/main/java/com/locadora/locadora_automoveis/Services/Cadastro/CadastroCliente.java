package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CadastroCliente {
    private List<Cliente> clientes;
    private int nextId;
    private static CadastroCliente instance;

    private CadastroCliente() {
        this.clientes = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    // Singleton para garantir que haja apenas uma instância de CadastroCliente
    public static CadastroCliente getInstance() {
        if (instance == null) {
            instance = new CadastroCliente();
        }
        return instance;
    }

    public Cliente cadastrarCliente(String cpf, String nome, String telefone) {
        Cliente cliente = new Cliente(cpf, nome, telefone, nextId++);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista para evitar modificações externas
    }

    public boolean apagarCliente(int id) {
        return clientes.removeIf(cliente -> cliente.getId() == id);
    }
}
