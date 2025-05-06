package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CadastroCliente {
    private List<Cliente> clientes;
    private int nextId;

    private CadastroCliente() {
        this.clientes = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    public Cliente cadastrarCliente(String cpf, String nome, String telefone) {
        Cliente cliente = new Cliente(cpf, nome, telefone, nextId++);
        clientes.add(cliente);
        return cliente;
    }

    public Cliente getCliente(int id) {
        return clientes.stream()
                       .filter(cliente -> cliente.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista para evitar modificações externas
    }

    public boolean apagarCliente(int id) {
        return clientes.removeIf(cliente -> cliente.getId() == id);
    }
}
