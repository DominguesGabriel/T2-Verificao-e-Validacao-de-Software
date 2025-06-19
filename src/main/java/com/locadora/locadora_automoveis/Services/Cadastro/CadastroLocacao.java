package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Automovel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroLocacao {
    private List<Locacao> locacoes;
    private int nextId;

    private CadastroLocacao() {
        this.locacoes = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    public boolean cadastraLocacao(LocalDate dataInicial, int quantDias, Cliente cliente, Automovel automovel) {
        if (!automovel.isDisponivel()) {
            return false;
        }

        Locacao locacao = new Locacao(nextId++, dataInicial, cliente, automovel, 0, quantDias);
        locacao.setValorTotal(calcularValor(locacao));

        automovel.setDisponivel(false);
        return locacoes.add(locacao);
    }

    public double calcularValor(Locacao locacao) {
      return locacao.calcularValor();
    }

    public List<Locacao> listarLocacoes() {
        return new ArrayList<>(locacoes);
    }

    public boolean finalizaLocacao(int id) {
        for (Locacao locacao : locacoes) {
            if (locacao.getId() == id) {
                locacao.getAutomovel().setDisponivel(true);
                return true;
            }
        }

        return false;
    }
}