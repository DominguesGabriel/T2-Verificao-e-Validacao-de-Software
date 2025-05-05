package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

import java.util.List;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acmerent")
public class ACMERentController {

    @GetMapping("/listaautomoveis")
    private List<Automovel> getAllAutomoveis(){
        return CadastroAutomovel.getInstance().listarAutomoveis();
    }

    @GetMapping("/listaclientes")
    private List<Cliente> getAllClientes(){
        return CadastroCliente.getInstance().listarClientes();
    }

    @GetMapping("/listalocacoes")
    private List<Locacao> getAllLocacoes() {
        return CadastroLocacao.getInstance().listarLocacoes();
    }

    @GetMapping("/consultacliente")
    private Cliente getConsultaCliente(@RequestParam(value = "codigo")int codigo){
        return CadastroCliente.getInstance().getCliente(codigo);
    }

    @PostMapping("/validaautomovel")
    private boolean validaAutomovel(@RequestBody int idAutomovel){
        return CadastroAutomovel.getInstance().isDisponivelPorId(idAutomovel);
    }

    //APARTIR DAQUI NÂO ESTÀ TOTALMENTE DESENVOLVIDO

    @PostMapping("/atendimento/cadlocacao")
    private boolean atendimentoCadastro(){
        // Precisa fazer a implementação e adicionar o @ResponseBody
        return false;
    }

    @PostMapping("/acmerent/atendimento/atualizaautomovel")
    private boolean atualizaAutomvel(){
        // Precisa fazer a implementação e adicionar o @ResponseBody e os @RequestParam
        return false;
    }

    @PostMapping("/acmerent/atendimento/finalizalocacao")
    private boolean finalizaLocacao(){
        // Precisa fazer a implementação e adicionar o @ResponseBody
        return false;
    }
}
