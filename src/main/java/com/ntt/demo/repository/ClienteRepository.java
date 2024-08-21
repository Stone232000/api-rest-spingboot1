package com.ntt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntt.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
