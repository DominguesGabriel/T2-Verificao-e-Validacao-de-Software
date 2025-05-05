package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmerent")
public class LocacaoController {

    @Autowired
    private CadastroLocacao cadastroLocacao;

    @GetMapping("/listalocacoes")
    private void getAllLocacoes() {
        //EXEMPLO
        cadastroLocacao.listarLocacoes();
    }
}