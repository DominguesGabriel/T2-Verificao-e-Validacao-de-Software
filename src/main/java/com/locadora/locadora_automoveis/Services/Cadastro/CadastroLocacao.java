package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Automovel;
import org.springframework.stereotype.Service;

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

    public static CadastroLocacao getInstance() {
        if (instance == null) {
            instance = new CadastroLocacao();
        }
        return instance;
    }

    public Locacao cadastrarLocacao(int dias, Cliente cliente, Automovel automovel) {
        if (!automovel.isDisponivel()) {
            throw new IllegalStateException("Automóvel não está disponível para locação");
        }

        automovel.setDisponivel(false);
        Locacao locacao = new Locacao(nextId++, dias, cliente, automovel);
        locacoes.add(locacao);
        return locacao;
    }

    public double calcularValor(Locacao locacao) {
        double valor = locacao.getDias() * locacao.getAutomovel().getValorDiaria();
        if (locacao.getDias() > 7) {
            valor *= 0.95;
        }
        return valor;
    }

    public List<Locacao> listarLocacoes() {
        return new ArrayList<>(locacoes);
    }

    public boolean apagarLocacao(int id) {
        return locacoes.removeIf(locacao -> locacao.getId() == id);
    }

    public boolean finalizarLocacao(int id) {
        for (Locacao locacao : locacoes) {
            if (locacao.getId() == id) {
                locacao.getAutomovel().setDisponivel(true);
                locacoes.remove(locacao);
                return true;
            }
        }
        return false;

    }
}