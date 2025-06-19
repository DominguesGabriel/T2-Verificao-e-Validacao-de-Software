package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Automovel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroAutomovel {
    private List<Automovel> automoveis;
    private int nextId;

    private CadastroAutomovel() {
        this.automoveis = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    public Automovel cadastrarAutomovel(String placa, int ano, double valorDiaria) {
        Automovel automovel = new Automovel(nextId++, placa, ano, valorDiaria);
        automoveis.add(automovel);
        return automovel;
    }

    public Automovel getAutomovel(int id) {
        return automoveis.stream()
                .filter(automovel -> automovel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Automovel> listarAutomoveis() {
        return new ArrayList<>(automoveis); // Retorna uma cópia da lista para evitar modificações externas
    }


    public boolean isDisponivelPorId(int id) {
        for (Automovel automovel : automoveis)
            if (automovel.getId() == id) {
                return automovel.isDisponivel();
            }
        return false;
    }
}
