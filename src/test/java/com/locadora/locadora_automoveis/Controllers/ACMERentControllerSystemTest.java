package com.locadora.locadora_automoveis.Controllers;

import com.locadora.locadora_automoveis.DTO.LocacaoRequest;
import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroCliente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ACMERentControllerSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CadastroCliente cadastroCliente;

    @Autowired
    private CadastroAutomovel cadastroAutomovel;

    @LocalServerPort
    private int port;

    private String getBaseUrl(){
        return "http://localhost:" +port;
    }


    @Test
    void deveSimularJornadaDeUsuario(){
        //1 - Lista Cliente

        ResponseEntity<List<Cliente>> listaClientes = restTemplate.exchange(
                getBaseUrl() + "/acmerent/listaclientes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cliente>>() {}
        );

        assertEquals(HttpStatus.OK, listaClientes.getStatusCode());
        assertNotNull(listaClientes.getBody());
        assertFalse(listaClientes.getBody().isEmpty());

        assertEquals(3,listaClientes.getBody().size());

        int codigoCliente = listaClientes.getBody().getFirst().getId();

        //2 - Consulta Cliente

        ResponseEntity<Cliente> clienteConsultado = restTemplate.exchange(
                getBaseUrl() + "/acmerent/consultacliente?codigo="+codigoCliente,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Cliente>() {}
        );

        assertEquals(HttpStatus.OK,clienteConsultado.getStatusCode());
        assertNotNull(clienteConsultado.getBody());
        assertEquals(codigoCliente,clienteConsultado.getBody().getId());


        //3 - Lista os Automoveis

        ResponseEntity<List<Automovel>> listaAutomoveis = restTemplate.exchange(
                getBaseUrl() + "/acmerent/listaautomoveis",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Automovel>>() {}
        );

        assertEquals(HttpStatus.OK,listaAutomoveis.getStatusCode());
        assertNotNull(listaAutomoveis.getBody());
        assertFalse(listaAutomoveis.getBody().isEmpty());

        assertEquals(10,listaAutomoveis.getBody().size());


        //4 - Verifica Automovel Disponivel

        Automovel automovel = listaAutomoveis.getBody().getFirst();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Integer> requestValida = new HttpEntity<>(automovel.getId(), headers);



        ResponseEntity<Boolean> isDisponivel = restTemplate.exchange(
                getBaseUrl() + "/acmerent/validaautomovel",
                HttpMethod.POST,
                requestValida,
                Boolean.class
        );

        assertEquals(HttpStatus.OK,isDisponivel.getStatusCode());
        assertNotNull(isDisponivel.getBody());
        assertEquals(false,isDisponivel.getBody());

        //5 - Atualiza Status do Automovel para Disponivel

        ResponseEntity<Automovel> automovelAtualizado = restTemplate.exchange(
                getBaseUrl() + "/acmerent/atendimento/atualizaautomovel/" + automovel.getId() + "/estado/" + true,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<Automovel>() {}
        );

        assertEquals(HttpStatus.OK,automovelAtualizado.getStatusCode());
        assertNotNull(automovelAtualizado.getBody());
        assertTrue(automovelAtualizado.getBody().isDisponivel());

        //6 - Cadastra Locacao

        LocacaoRequest locacaoRequest = new LocacaoRequest(
                3,
                automovel.getId(),
                codigoCliente,
                Instant.ofEpochSecond(352398573).atZone(ZoneId.systemDefault()).toLocalDate().toString()+"T10:15:30Z",
                8);

        HttpEntity<LocacaoRequest> requestCadastroLocacao = new HttpEntity<>(locacaoRequest,headers);

        ResponseEntity<Boolean> cadastraLocacao = restTemplate.exchange(
                getBaseUrl() + "/acmerent/atendimento/cadlocacao",
                HttpMethod.POST,
                requestCadastroLocacao,
                Boolean.class

        );

        assertEquals(HttpStatus.OK,cadastraLocacao.getStatusCode());
        assertNotNull(cadastraLocacao.getBody());
        assertTrue(cadastraLocacao.getBody());

        //7 - Lista Locacoes

        ResponseEntity<List<Locacao>> listaLocacao = restTemplate.exchange(
                getBaseUrl() + "/acmerent/listalocacoes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Locacao>>() {}
        );

        assertEquals(HttpStatus.OK, listaLocacao.getStatusCode());
        assertNotNull(listaLocacao.getBody());
        assertFalse(listaLocacao.getBody().isEmpty());
        assertEquals(3,listaLocacao.getBody().size());

        assertEquals(locacaoRequest.toLocacao(cadastroAutomovel,cadastroCliente).getId(), listaLocacao.getBody().get(2).getId());

        //8 - Finaliza Locacao

        HttpEntity<Integer> requestFinalizaLocacao = new HttpEntity<>(locacaoRequest.getIdLocacao(), headers);

        ResponseEntity<Boolean> finalizaLocacao = restTemplate.exchange(
                getBaseUrl() +"/acmerent/atendimento/finalizalocacao",
                HttpMethod.POST,
                requestFinalizaLocacao,
                Boolean.class
        );

        assertEquals(HttpStatus.OK,finalizaLocacao.getStatusCode());
        assertNotNull(finalizaLocacao.getBody());
        assertTrue(finalizaLocacao.getBody());
    }
}
