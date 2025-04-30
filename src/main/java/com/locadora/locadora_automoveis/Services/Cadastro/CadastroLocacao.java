package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Locacao;
import org.springframework.stereotype.Service;
import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroLocacao {
    private List<Locacao> locacoes;
    private int nextId;
    private static CadastroLocacao instance;

    private CadastroLocacao() {
        this.locacoes = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    // Singleton
    public static CadastroLocacao getInstance() {
        if (instance == null) {
            instance = new CadastroLocacao();
        }
        return instance;
    }

    public Locacao cadastrarLocacao(int dias, Cliente cliente, Automovel automovel) {
        Locacao locacao = new Locacao(nextId++, dias, cliente, automovel);
        locacoes.add(locacao);
        return locacao;
    }

    public List<Locacao> listarLocacoes() {
        return new ArrayList<>(locacoes);
    }

    public boolean apagarLocacao(int id) {
        return locacoes.removeIf(locacao -> locacao.getId() == id);
    }
    }