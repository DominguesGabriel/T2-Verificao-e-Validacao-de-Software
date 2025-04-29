package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private CadastroCliente cadastroCliente;

    @GetMapping()
    private void getAllClientes(){
        //EXEMPLO
        cadastroCliente.listarClientes();
    }
}
