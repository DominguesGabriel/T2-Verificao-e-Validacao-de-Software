package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.DTO.LocacaoRequest;
import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

import java.util.List;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acmerent")
public class ACMERentController {

    @Autowired
    private CadastroCliente cadastroCliente;
    @Autowired
    private CadastroAutomovel cadastroAutomovel;
    @Autowired
    private CadastroLocacao cadastroLocacao;

    @GetMapping("/listaautomoveis")
    private List<Automovel> getAllAutomoveis(){
        return cadastroAutomovel.listarAutomoveis();
    }

    @GetMapping("/listaclientes")
    private List<Cliente> getAllClientes(){
        return cadastroCliente.listarClientes();
    }

    @GetMapping("/listalocacoes")
    private List<Locacao> getAllLocacoes() {
        return cadastroLocacao.listarLocacoes();
    }

        @GetMapping("/consultacliente")
    private Cliente getConsultaCliente(@RequestParam(value = "codigo")int codigo){
        return cadastroCliente.getCliente(codigo);
    }

    @PostMapping("/validaautomovel")
    private boolean validaAutomovel(@RequestBody int idAutomovel){
        return cadastroAutomovel.isDisponivelPorId(idAutomovel);
    }

    @PostMapping("/atendimento/cadlocacao")
    private boolean cadastraLocacao(@RequestBody LocacaoRequest locacaoRequest) {
        return cadastroLocacao.cadastrarLocacao(locacaoRequest.toLocacao(cadastroAutomovel, cadastroCliente));
    }

    @PostMapping("/atendimento/atualizaautomovel/{id}/estado/{status}")
    private Automovel atualizaAutomovel(@PathVariable int id, @PathVariable boolean status) {
        Automovel automovel = cadastroAutomovel.getAutomovel(id);

        if (automovel == null) return null; // Retorna null se o automóvel não for encontrado
        
        automovel.setDisponivel(status);

        return automovel;
    }

    @PostMapping("/atendimento/finalizalocacao")
    private boolean finalizaLocacao(@RequestBody int id){
        return cadastroLocacao.finalizarLocacao(id);
    }
}
