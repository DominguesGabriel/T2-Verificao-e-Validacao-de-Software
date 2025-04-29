package com.locadora.locadora_automoveis;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.locadora.locadora_automoveis.Models.Automovel;
import com.locadora.locadora_automoveis.Services.Cadastro.CadastroAutomovel;
import com.locadora.locadora_automoveis.Services.InicializacaoDados.InicializaAutomoveis;
import com.locadora.locadora_automoveis.Services.InicializacaoDados.InicializaClientes;

@SpringBootApplication
public class LocadoraAutomoveisApplication {
	CadastroAutomovel cadastroAutomovel = CadastroAutomovel.getInstance();

	public static void main(String[] args) {
		InicializaAutomoveis.inicializar();
		InicializaClientes.inicializar();

		SpringApplication.run(LocadoraAutomoveisApplication.class, args);
	}

	@GetMapping("/")
	public List<Automovel> listaAutomoveis() {
		return cadastroAutomovel.listarAutomoveis();
	}
}
