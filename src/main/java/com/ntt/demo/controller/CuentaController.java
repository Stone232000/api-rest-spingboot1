package com.ntt.demo.controller;

import com.ntt.demo.model.Cuenta;
import com.ntt.demo.services.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // CREATE - Crear una nueva cuenta
    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        Cuenta savedCuenta = cuentaService.saveCuenta(cuenta);
        return new ResponseEntity<>(savedCuenta, HttpStatus.CREATED);
    }

    // READ - Obtener todas las cuentas
    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.findAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    // READ - Obtener una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.findCuentaById(id);
        if (cuenta.isPresent()) {
            return new ResponseEntity<>(cuenta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE - Actualizar una cuenta por ID
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        Optional<Cuenta> cuenta = cuentaService.findCuentaById(id);
        if (cuenta.isPresent()) {
            Cuenta updatedCuenta = cuenta.get();
            updatedCuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
            updatedCuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
            updatedCuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
            updatedCuenta.setEstado(cuentaDetails.getEstado());
            cuentaService.saveCuenta(updatedCuenta);
            return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar una cuenta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCuenta(@PathVariable Long id) {
        try {
            cuentaService.deleteCuentaById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
