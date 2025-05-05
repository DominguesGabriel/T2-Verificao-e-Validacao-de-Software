package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmerent")
public class LocacaoController {
    @GetMapping("/listalocacoes")
    private List<Locacao> getAllLocacoes() {
        return CadastroLocacao.getInstance().listarLocacoes();
    }
}