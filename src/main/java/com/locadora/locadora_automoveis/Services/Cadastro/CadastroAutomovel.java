package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Automovel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroAutomovel {
    private List<Automovel> automoveis;
    private int nextId;
    private static CadastroAutomovel instance;

    private CadastroAutomovel() {
        this.automoveis = new ArrayList<>();
        this.nextId = 1; // ID inicial
    }

    // Singleton
    public static CadastroAutomovel getInstance() {
        if (instance == null) {
            instance = new CadastroAutomovel();
        }
        return instance;
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

    public boolean apagarAutomovel(int id) {
        return automoveis.removeIf(automovel -> automovel.getId() == id);
    }
}
