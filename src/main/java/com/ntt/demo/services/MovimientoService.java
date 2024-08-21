package com.ntt.demo.services;

import org.springframework.stereotype.Service;

import com.ntt.demo.exception.SaldoInsuficienteException;
import com.ntt.demo.model.Cuenta;
import com.ntt.demo.model.Movimiento;
import com.ntt.demo.repository.CuentaRepository;
import com.ntt.demo.repository.MovimientoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository=cuentaRepository;
    }

    public Movimiento saveMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> findAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> findMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public void deleteMovimientoById(Long id) {
        movimientoRepository.deleteById(id);
    }
        public Movimiento createMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // Si es un retiro y el saldo es insuficiente, lanza la excepción
        if (movimiento.getValor() < 0 && cuenta.getSaldoInicial() < Math.abs(movimiento.getValor())) {
          throw new SaldoInsuficienteException("Saldo no disponible");
      }

        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }
}
