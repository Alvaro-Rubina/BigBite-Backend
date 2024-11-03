package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.spdgroup.bigbitebackend.repositories.CuentaAsientoRepository;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.CuentaAsientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaAsientoService {
    @Autowired
    private CuentaAsientoRepository cuentaAsientoRepository;

    @Autowired
    CuentaAsientoMapper cuentaAsientoMapper;

    @Autowired
    private ICuentaRepository cuentaRepository;

    public CuentaAsiento registrarCuentaAsiento(CuentaAsientoDTO cuentaAsientoDTO) {
        CuentaAsiento cuentaAsiento = cuentaAsientoMapper.toEntity(cuentaAsientoDTO);

        // Obtener la cuenta utilizando el ID proporcionado
        Cuenta cuenta = cuentaRepository.findById(cuentaAsientoDTO.getCuentaId())
                .orElseThrow(() -> new ProductNotFoundException("Cuenta no encontrada"));

        cuentaAsiento.setCuenta(cuenta);
        cuentaAsiento.setTipo(cuentaAsientoDTO.getTipo());

        return cuentaAsientoRepository.save(cuentaAsiento);
    }

    public CuentaAsiento obtenerCuentaAsientoPorId(Long id) {
        return cuentaAsientoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Cuenta asiento no encontrada"));
    }

    public List<CuentaAsiento> obtenerCuentasAsientos() {
        return cuentaAsientoRepository.findAll();
    }
}
