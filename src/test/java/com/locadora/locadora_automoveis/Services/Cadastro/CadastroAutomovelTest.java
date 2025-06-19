package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Automovel;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class  CadastroAutomovelTest {


    @InjectMocks
    private CadastroAutomovel cadastroAutomovel;

    @Mock
    private Automovel automovel;

    @Nested
    class VerificaDisponibilidadeTest{
        @Test
        void shouldIsDisponivelByIdTrue(){
            cadastroAutomovel.cadastrarAutomovel(automovel.getPlaca(),automovel.getAno(),automovel.getId());
            var output = cadastroAutomovel.isDisponivelPorId(1);

            assertTrue(output);
        }

        @Test
        void shouldIsDisponivelByIdFalse(){
            cadastroAutomovel.cadastrarAutomovel(automovel.getPlaca(),automovel.getAno(),automovel.getId());
            List<Automovel> lista = cadastroAutomovel.listarAutomoveis();
            lista.getFirst().setDisponivel(false);
            var output = cadastroAutomovel.isDisponivelPorId(0);

            assertFalse(output);
        }
    }

}