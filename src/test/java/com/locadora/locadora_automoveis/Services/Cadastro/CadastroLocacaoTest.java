package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Models.Cliente;
import com.locadora.locadora_automoveis.Models.Locacao;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastroLocacaoTest {

    @InjectMocks
    private CadastroLocacao cadastroLocacao;

    @Mock
    private Locacao locacao;

    @Mock
    private Automovel automovel;

    @Mock
    private Cliente cliente;


    @Nested
    class CalculaValorLocacaoTest{
        @Test
        void shouldCalcularValor(){
            locacao = new Locacao(1,LocalDate.now(),cliente,automovel,100,8);

            when(automovel.getValorDiaria()).thenReturn((double)100);


            var output = cadastroLocacao.calcularValor(locacao);

            assertEquals(760,output);
        }
    }


    @Nested
    class CadastraLocacaoTest{
        @Test
        void shouldCadastrarLocacaoSucesso(){
            when(automovel.isDisponivel()).thenReturn(true);
            when(locacao.getStartData()).thenReturn(LocalDate.now());

            var output = cadastroLocacao.cadastraLocacao(locacao.getStartData(),locacao.getQuantDias(),cliente,automovel);

            assertTrue(output);
        }

        @Test
        void shouldCadastrarLocacaoFalha(){
            when(automovel.isDisponivel()).thenReturn(false);
            when(locacao.getStartData()).thenReturn(LocalDate.now());

            var output = cadastroLocacao.cadastraLocacao(locacao.getStartData(),locacao.getQuantDias(),cliente,automovel);

            assertFalse(output);
        }
    }

    @Nested
   class FinalizaLocacaoTest{
        @Test
        void shouldFinalizarLocacao(){
            when(automovel.isDisponivel()).thenReturn(true);
            when(locacao.getStartData()).thenReturn(LocalDate.now());
            cadastroLocacao.cadastraLocacao(locacao.getStartData(),locacao.getQuantDias(),cliente,automovel);

            when(locacao.getId()).thenReturn(1);

            var output = cadastroLocacao.finalizaLocacao(locacao.getId());

            assertTrue(output);
        }
   }

   @Test
   void listarLocacoes(){

        when(automovel.isDisponivel()).thenReturn(true);
        when(locacao.getStartData()).thenReturn(LocalDate.now());

        cadastroLocacao.cadastraLocacao(locacao.getStartData(),locacao.getQuantDias(),cliente,automovel);

        List<Locacao> listaLocacao = cadastroLocacao.listarLocacoes();

        assertNotNull(listaLocacao);
        assertFalse(listaLocacao.isEmpty());
   }


}