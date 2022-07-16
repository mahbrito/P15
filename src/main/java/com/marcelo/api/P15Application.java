package com.marcelo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.marcelo.api.entidades.Empresa;
import com.marcelo.api.repositories.EmpresaRepository;

import antlr.collections.List;

@SpringBootApplication
public class P15Application {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(P15Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("Pé de Rato IT");
			empresa.setCnpj("74645215000104");
			
			this.empresaRepository.save(empresa);
			
			List<Empresa> empresas = empresaRepository.findAll();
			empresas.forEach(System.out::println);
			
			Empresa empresaDb = (Empresa) empresaRepository.findOne(empresas);//aqui era 1L
			System.out.println("Empresa por Id: " + empresaDb);
			
			empresaDb.setRazaoSocial("Pé de Rato IT Web");
			this.empresaRepository.save(empresaDb);
			
			Empresa empresaCnpj = empresaRepository.findByCnpj("74645215000104");
			System.out.println("Empresa por CNPJ: " + empresaCnpj);
			
			this.empresaRepository.delete(empresaCnpj);//no do professor, não tem essa passagem por parametro ai
			empresas = empresaRepository.findAll();
			System.out.println("Empresas: " + empresas.size());
			
			this.empresaRepository.deleteAll();//aqui tinha 1L
			empresas = empresaRepository.findAll();
			System.out.println("Empresas: " + empresas.size());
			
		};
	}

}
