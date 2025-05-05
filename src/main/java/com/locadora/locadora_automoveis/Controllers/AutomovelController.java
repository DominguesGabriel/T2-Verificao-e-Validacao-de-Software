package com.locadora.locadora_automoveis.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;

@RestController
@RequestMapping("/acmerent")
public class AutomovelController {
    @GetMapping("/listaautomoveis")
    private List<Automovel> getAllAutomoveis(){
        return CadastroAutomovel.getInstance().listarAutomoveis();
    }

}
