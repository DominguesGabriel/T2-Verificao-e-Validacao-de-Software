package com.locadora.locadora_automoveis.Models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class LocacaoTest {
    private Locacao locacao;

    @Mock
    private Automovel automovel;

    @BeforeEach
    public void setup(){
        locacao = new Locacao(1, LocalDate.now(), null, automovel, 0, 0);
    }

    @Test
    public void testCalcularValor(){
        locacao.setQuantDias(8);
        when(automovel.getValorDiaria()).thenReturn(100.0);

        double resultado = locacao.calcularValor();
        
        assertEquals(760, resultado, 0.0001);
    }
}
