package com.ntt.demo.controller;

import com.ntt.demo.exception.SaldoInsuficienteException;
import com.ntt.demo.model.Movimiento;
import com.ntt.demo.services.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    // CREATE - Crear un nuevo movimiento
    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento savedMovimiento = movimientoService.createMovimiento(movimiento);
            return new ResponseEntity<>(savedMovimiento, HttpStatus.CREATED);
        } catch (SaldoInsuficienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // READ - Obtener todos los movimientos
    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoService.findAllMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    // READ - Obtener un movimiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Optional<Movimiento> movimiento = movimientoService.findMovimientoById(id);
        if (movimiento.isPresent()) {
            return new ResponseEntity<>(movimiento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE - Actualizar un movimiento por ID
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        Optional<Movimiento> movimiento = movimientoService.findMovimientoById(id);
        if (movimiento.isPresent()) {
            Movimiento updatedMovimiento = movimiento.get();
            updatedMovimiento.setFecha(movimientoDetails.getFecha());
            updatedMovimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
            updatedMovimiento.setValor(movimientoDetails.getValor());
            updatedMovimiento.setSaldo(movimientoDetails.getSaldo());
            movimientoService.saveMovimiento(updatedMovimiento);
            return new ResponseEntity<>(updatedMovimiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar un movimiento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovimiento(@PathVariable Long id) {
        try {
            movimientoService.deleteMovimientoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
