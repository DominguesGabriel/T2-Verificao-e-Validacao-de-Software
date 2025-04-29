package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/automovel")
public class AutomovelController {

    private CadastroAutomovel cadastroAutomovel;

    @GetMapping()
    private void getAllAutomoveis(){
        //EXEMPLO
        cadastroAutomovel.listarAutomoveis();
    }

}
