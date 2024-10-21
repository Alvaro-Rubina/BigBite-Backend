package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.utils.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    ICuentaRepository cuentaRepo;

    @Autowired
    private CuentaMapper CuentaMapper; // Inyectar el mapper de Cuenta (si lo tienes definido)

    public void registrarCuenta(CuentaDTO cuentaDTO) throws IOException {
        Cuenta cuenta = CuentaMapper.toEntity(cuentaDTO); // Usar el mapper para la conversión
        cuentaRepo.save(cuenta); // Guardar la entidad
    }

    public List<Cuenta> obtenerCuentas() {
        return cuentaRepo.findAll(); // Retornar la lista de cuentas
    }
}
