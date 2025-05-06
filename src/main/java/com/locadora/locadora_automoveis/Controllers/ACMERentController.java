package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.locadora.locadora_automoveis.Services.Cadastro.CadastroLocacao;

import lombok.AllArgsConstructor;

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

    @AllArgsConstructor
    private static class LocacaoRequest {
        private int idLocacao;
        private int idAutomovel;
        private int idCliente;
        private String dataInicial;

        public Locacao toLocacao() {
            CadastroAutomovel cadastroAutomovel = CadastroAutomovel.getInstance();
            CadastroCliente cadastroCliente = CadastroCliente.getInstance();

            Automovel automovel = cadastroAutomovel.getAutomovel(idAutomovel);
            Cliente cliente = cadastroCliente.getCliente(idCliente);

            return new Locacao(idLocacao, Date.from(Instant.parse(dataInicial)), null, cliente, automovel);
        }
    }

    @PostMapping("/atendimento/cadlocacao")
    private boolean cadastraLocacao(@RequestBody LocacaoRequest locacaoRequest) {
        CadastroLocacao cadastroLocacao = CadastroLocacao.getInstance();

        return cadastroLocacao.cadastrarLocacao(locacaoRequest.toLocacao());
    }

    @PostMapping("/atendimento/atualizaautomovel/{id}/estado/{status}")
    private Automovel atualizaAutomovel(@PathVariable int id, @PathVariable boolean status) {
        CadastroAutomovel cadastroAutomovel = CadastroAutomovel.getInstance();
        Automovel automovel = cadastroAutomovel.getAutomovel(id);

        if (automovel == null) return null; // Retorna null se o automóvel não for encontrado
        
        automovel.setDisponivel(status);

        return automovel;
    }

    @PostMapping("/atendimento/finalizalocacao")
    private boolean finalizaLocacao(@RequestBody int id){
        return CadastroLocacao.getInstance().finalizarLocacao(id);
    }
}
