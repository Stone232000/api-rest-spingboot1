package com.ntt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntt.demo.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
  
}
