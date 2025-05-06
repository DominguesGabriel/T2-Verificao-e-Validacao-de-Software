package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Automovel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CadastroLocacao {
    private List<Locacao> locacoes;
    private int nextId;

    private CadastroLocacao() {
        this.locacoes = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    public Locacao getLocacao(int id) {
        return locacoes.stream()
                       .filter(locacoes -> locacoes.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    public Locacao cadastrarLocacao(Date dataInicial, Cliente cliente, Automovel automovel) {
        if (!automovel.isDisponivel()) {
            throw new IllegalStateException("Automóvel não está disponível para locação");
        }

        automovel.setDisponivel(false);
        Locacao locacao = new Locacao(nextId++, dataInicial, null, cliente, automovel);
        
        cadastrarLocacao(locacao);

        return locacao;
    }

    public boolean cadastrarLocacao(Locacao locacao) {
        for (Locacao locacaoExistente : locacoes) {
            if (locacaoExistente.getId() == locacao.getId()) {
                throw new IllegalArgumentException("Locação com ID " + locacao.getId() + " já existe.");
            }
        }

        if (!locacao.getAutomovel().isDisponivel()) {
            throw new IllegalStateException("Automóvel não está disponível para locação");
        }

        locacao.getAutomovel().setDisponivel(false);

        return locacoes.add(locacao);
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

    public boolean finalizaLocacao(int id, Date dataFinal) {
        for (Locacao locacao : locacoes) {
            if (locacao.getId() != id) continue;

            locacao.setEndDate(dataFinal);
            locacao.getAutomovel().setDisponivel(true);

            return true;
        }

        return false;
    }

    public void finalizaLocacao(int id) {
        finalizaLocacao(id, new Date());
    }

    public boolean apagarLocacao(int id) {
        return locacoes.removeIf(locacao -> locacao.getId() == id);
    }

    public boolean finalizarLocacao(int id) {
        for (Locacao locacao : locacoes) {
            if (locacao.getId() == id) {
                locacao.getAutomovel().setDisponivel(true);
                
                return true;
            }
        }
        return false;

    }
}