package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.repositories.AsientoRepository;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.utils.mapper.AsientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    AsientoRepository asientoRepo;

    @Autowired
    ICuentaRepository cuentaRepo;

    @Autowired
    AsientoMapper asientoMapper;

    public void registrarAsiento(AsientoDTO asientoDTO) {
        Asiento asiento = asientoMapper.toEntity(asientoDTO);
        asiento.setCuenta(cuentaRepo.findById(asientoDTO.getCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));

        asientoRepo.save(asiento);
    }

    // TODO: VER SI LO OCUPAMOS DESPUES
    public Asiento obtenerAsientoPorId(Long id) {
        return asientoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));
    }

    public List<Asiento> obtenerAsientosPorCuenta(Long cuentaId) {
        return asientoRepo.findByCuentaId(cuentaId);
    }

    public List<Asiento> obtenerAsientos() {
        return asientoRepo.findAll();
    }
}
