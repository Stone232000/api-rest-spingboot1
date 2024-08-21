package com.ntt.demo.services;

import org.springframework.stereotype.Service;
import com.ntt.demo.model.Cuenta;
import com.ntt.demo.repository.CuentaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> findAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    public void deleteCuentaById(Long id) {
        cuentaRepository.deleteById(id);
    }
}
