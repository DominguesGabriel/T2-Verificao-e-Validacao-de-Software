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
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class  CadastroAutomovelTest {


    @InjectMocks
    private CadastroAutomovel cadastroAutomovel;

    @Mock
    private Automovel automovel;

    @Mock
    private Automovel automovel2;

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

    @Test
    void listarAutomoveisTest(){

        //Mockando valores para a placa dos automóveis.
        when(automovel.getPlaca()).thenReturn("ABC1234");
        when(automovel2.getPlaca()).thenReturn("DEF5678");

        cadastroAutomovel.cadastrarAutomovel(automovel.getPlaca(), 2020, 1);
        cadastroAutomovel.cadastrarAutomovel(automovel2.getPlaca(), 2021, 2);

        List<Automovel> lista = cadastroAutomovel.listarAutomoveis();

        //Verificando se a lista está com as placas cadastradas.
        assertTrue(lista.stream().anyMatch(a -> "ABC1234".equals(a.getPlaca())));
        assertTrue(lista.stream().anyMatch(a -> "DEF5678".equals(a.getPlaca())));

        //Modifica a lista.
        lista.clear();

        //Lista interna não deve ser afetada, chamamos a lista novamente e terá que conter os elementos.
        List<Automovel> novaLista = cadastroAutomovel.listarAutomoveis();
        assertFalse(novaLista.isEmpty());
        assertEquals(2, novaLista.size());
    }

}