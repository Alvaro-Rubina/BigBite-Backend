package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.utils.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    @Autowired
    ICuentaRepository cuentaRepo;

    @Autowired
    private CuentaMapper cuentaMapper;

    public void registrarCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        cuentaRepo.save(cuenta);
    }

    public List<Cuenta> obtenerCuentas() {
        return cuentaRepo.findAll();
    }

    public double obtenerCantidadDeCuentas() {
        return cuentaRepo.count();
    }
}
