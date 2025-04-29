package com.locadora.locadora_automoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.locadora.locadora_automoveis.Services.InicializacaoDados.InicializaAutomoveis;
import com.locadora.locadora_automoveis.Services.InicializacaoDados.InicializaClientes;

@SpringBootApplication
public class LocadoraAutomoveisApplication {

	public static void main(String[] args) {
		InicializaAutomoveis.inicializar();
		InicializaClientes.inicializar();

		SpringApplication.run(LocadoraAutomoveisApplication.class, args);
	}

}
