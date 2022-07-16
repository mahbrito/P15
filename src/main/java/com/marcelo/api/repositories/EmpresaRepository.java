package com.marcelo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.api.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	Empresa findByCnpj(String cnpn);
}
