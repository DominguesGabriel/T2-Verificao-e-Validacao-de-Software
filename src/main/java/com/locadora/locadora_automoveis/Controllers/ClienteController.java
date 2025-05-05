package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmerent")
public class ClienteController {
    @GetMapping("/listaclientes")
    private List<Cliente> getAllClientes(){
        return CadastroCliente.getInstance().listarClientes();
    }
}
