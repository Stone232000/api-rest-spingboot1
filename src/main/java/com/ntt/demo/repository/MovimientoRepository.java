package com.ntt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntt.demo.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
  
}
