package com.locadora.locadora_automoveis.Services.Cadastro;

import com.locadora.locadora_automoveis.Models.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CadastroClienteTest {

    @Mock
    Cliente cliente;


    @InjectMocks
    CadastroCliente cadastroCliente;

    @Test
    void listarClientes(){

        cadastroCliente.cadastrarCliente("1234567890", "Luiz", "51999998888");
        cadastroCliente.cadastrarCliente( "1234567892", "Cleber", "51955554444");

        List<Cliente> listaClientes = cadastroCliente.listarClientes();

        assertNotNull(listaClientes);
        assertFalse(listaClientes.isEmpty());

    }

    @Test
    void getClienteExistente (){
        cliente.setId(1);
        cliente.setNome("Luiz");

        cadastroCliente.cadastrarCliente("123456", cliente.getNome(), "51999999999");

        Cliente resultado = cadastroCliente.getCliente(1);

        assertNotNull(resultado);

        assertEquals(1, resultado.getId());
    }
    @Test
    void getClienteInexistente(){
        Cliente resultado = cadastroCliente.getCliente(999);//ID inexistente.

        assertNull(resultado);
    }
    
}
