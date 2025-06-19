package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Cliente;
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
public class CadastroClienteTest {

    @Mock
    Cliente cliente1;

    @Mock
    Cliente cliente2;

    @Mock
    CadastroCliente cadastroCliente;

    @Test
    void listarClientes(){
        when(cliente1.getCpf()).thenReturn("123456");
        when(cliente2.getCpf()).thenReturn("789101");

        cadastroCliente.cadastrarCliente(cliente1.getCpf(), "Luiz", "51999998888");
        cadastroCliente.cadastrarCliente(cliente2.getCpf(), "Cleber", "51955554444");

        List<Cliente> listaClientes = cadastroCliente.listarClientes();

        assertTrue(listaClientes.stream().anyMatch(c -> "123456".equals(c.getCpf())));
        assertTrue(listaClientes.stream().anyMatch(c -> "789101".equals(c.getCpf())));

        listaClientes.clear();

        List<Cliente> novaListaClientes = cadastroCliente.listarClientes();
        assertFalse(novaListaClientes.isEmpty());
        assertEquals(2,novaListaClientes.size());
    }

    void getClienteExistente (){
        cliente1.setId(1);
        cliente1.setNome("Luiz");

        cadastroCliente.cadastrarCliente("123456", cliente1.getNome(), "51999999999");

        Cliente resultado = cadastroCliente.getCliente(1);

        assertNotNull(resultado);

        assertEquals(1, resultado.getId());
    }

    void getClienteInexistente(){
        Cliente resultado = cadastroCliente.getCliente(999);//ID inexistente.

        assertNull(resultado);
    }
    
}
