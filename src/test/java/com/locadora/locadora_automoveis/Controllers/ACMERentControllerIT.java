package com.locadora.locadora_automoveis.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ACMERentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TI01_deveRetornarListaComPeloMenos3Clientes() throws Exception {
        mockMvc.perform(get("/acmerent/listaclientes"))
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

// Teste FALSE - cliente não existe (código 69)
        mockMvc.perform(get("/acmerent/consultacliente?codigo=69"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

}
