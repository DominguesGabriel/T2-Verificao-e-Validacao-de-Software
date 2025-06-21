package com.locadora.locadora_automoveis.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ACMERentControllerIT {

    @Autowired
    private MockMvc mockMvc;
    private MockMvcWebClientAutoConfiguration mockMvcWebClientAutoConfiguration;

    @Test
    public void TI01_deveRetornarListaComPeloMenos3Clientes() throws Exception {
        mockMvc.perform(get("/acmerent/listaclientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", isA(java.util.List.class)))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].nome", notNullValue()))
                .andExpect(jsonPath("$[0].cpf", notNullValue()));
    }

    @Test
    public void TI02_deveRetornarListaComPeloMenos2Locacoes() throws Exception {
        mockMvc.perform(get("/acmerent/listalocacoes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", isA(java.util.List.class)))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].cliente", notNullValue()))
                .andExpect(jsonPath("$[0].automovel", notNullValue()))
                .andExpect(jsonPath("$[0].valorTotal", notNullValue()));
    }

    @Test
    public void TI03_deveRetornarListaComPeloMenos10Automoveis() throws Exception {
        mockMvc.perform(get("/acmerent/listaautomoveis"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", isA(java.util.List.class)))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(10))))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].placa", notNullValue()))
                .andExpect(jsonPath("$[0].ano", greaterThan(2000)))
                .andExpect(jsonPath("$[0].valorDiaria", greaterThan(0.0)));
    }

    @Test
    public void TI04_deveRetornarClientePorIdOuNullSeNaoExistir() throws Exception {
        // Teste com cliente que DEVE existir
        // Teste TRUE - cliente existe (código 1)
        mockMvc.perform(get("/acmerent/consultacliente?codigo=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        // Teste FALSE - cliente não existe (código 69)
        mockMvc.perform(get("/acmerent/consultacliente?codigo=69"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void TI05_deveValidarAutomovelPorStatus() throws Exception {
        //Teste com o automovel disponível
        mockMvc.perform(post("/acmerent/validaautomovel")
                        .content("3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(post("/acmerent/validaautomovel")
                        .content("1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void TI06_deveCadastrarLocacao() throws Exception {
        String locacaoValida = """
                {
                    "idLocacao": 3,
                    "idAutomovel": 3,
                    "idCliente": 3,
                    "dataInicial": "2024-05-05T10:15:30Z"
                }""";

        mockMvc.perform(post("/acmerent/atendimento/cadlocacao")
                .content(locacaoValida)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        String locacaoInvalida = """
                {
                    "idLocacao": 1,
                    "idAutomovel": 1,
                    "idCliente": 1,
                    "dataInicial": "1981-03-02T10:15:30Z"
                }""";

        mockMvc.perform(post("/acmerent/atendimento/cadlocacao")
                .content(locacaoInvalida)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void TI07_deveAtualizarAutomovel() throws Exception{
        //Teste atualizando automovel
        mockMvc.perform(post("/acmerent/atendimento/atualizaautomovel/{id}/estado/{status}",1,false))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.placa").value("ABC1234"))
                .andExpect(jsonPath("$.ano").value(2020))
                .andExpect(jsonPath("$.valorDiaria").value(150.0))
                .andExpect(jsonPath("$.disponivel").value("false"));

        //Teste atualizando automovel inexistente
        mockMvc.perform(post("/acmerent/atendimento/atualizaautomovel/{id}/estado/{status}",50,false))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void TI08_deveFinalizarLocacao() throws Exception{
        mockMvc.perform(post("/acmerent/atendimento/finalizalocacao")
                .content("1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


        mockMvc.perform(post("/acmerent/atendimento/finalizalocacao")
                .content("20")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

}
