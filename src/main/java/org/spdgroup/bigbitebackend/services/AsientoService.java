package org.spdgroup.bigbitebackend.services;

import jakarta.transaction.Transactional;
import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.spdgroup.bigbitebackend.repositories.AsientoRepository;
import org.spdgroup.bigbitebackend.repositories.CuentaAsientoRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.AsientoMapper;
import org.spdgroup.bigbitebackend.utils.mapper.CuentaAsientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepo;

    @Autowired
    private CuentaAsientoService cuentaAsientoService;

    @Autowired
    private AsientoMapper asientoMapper;


    @Transactional
    public void registrarAsiento(AsientoDTO asientoDTO) {
        Asiento asiento = asientoMapper.toEntity(asientoDTO);
        asiento = asientoRepo.save(asiento);  // Guardar el asiento primero

        List<CuentaAsiento> cuentaAsientos = new ArrayList<>();
        for (CuentaAsientoDTO cuentaAsientoDTO : asientoDTO.getCuentaAsientoDTO()) {
            CuentaAsiento cuentaAsiento = cuentaAsientoService.registrarCuentaAsiento(cuentaAsientoDTO);
            cuentaAsiento.setAsiento(asiento);  // Establecer la referencia de Asiento en CuentaAsiento
            cuentaAsientos.add(cuentaAsiento);
        }
        asiento.setCuentasAsiento(cuentaAsientos);
        asientoRepo.save(asiento);
    }


    public Asiento obtenerAsientoPorId(Long id) {
        return asientoRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Asiento no encontrado"));
    }

    public List<Asiento> obtenerAsientos() {
        return asientoRepo.findAll();
    }
}