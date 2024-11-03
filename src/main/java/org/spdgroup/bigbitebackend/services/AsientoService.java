package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.spdgroup.bigbitebackend.repositories.AsientoRepository;
import org.spdgroup.bigbitebackend.repositories.CuentaAsientoRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.AsientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepo;

    @Autowired
    private CuentaAsientoRepository cuentaAsientoRepo;

    @Autowired
    private AsientoMapper asientoMapper;

    public void registrarAsiento(AsientoDTO asientoDTO) {
        // Convertir DTO a entidad
        Asiento asiento = asientoMapper.toEntity(asientoDTO);

        // Validar si el asiento está balanceado (suma debe = suma haber)
        double totalDebe = asiento.getCuentaAsiento().stream()
                .filter(cuentaAsiento -> "debe".equalsIgnoreCase(cuentaAsiento.getTipo()))
                .mapToDouble(CuentaAsiento::getMonto)
                .sum();

        double totalHaber = asiento.getCuentaAsiento().stream()
                .filter(cuentaAsiento -> "haber".equalsIgnoreCase(cuentaAsiento.getTipo()))
                .mapToDouble(CuentaAsiento::getMonto)
                .sum();

        if (totalDebe != totalHaber) {
            throw new IllegalArgumentException("El asiento contable no está balanceado: el total del debe y haber deben ser iguales.");
        }

        // Guardar cada CuentaAsiento si no existe y asociarlo al asiento
        asiento.getCuentaAsiento().forEach(cuentaAsiento -> {
            if (cuentaAsiento.getId() == null || !cuentaAsientoRepo.existsById(cuentaAsiento.getId())) {
                cuentaAsientoRepo.save(cuentaAsiento);
            }
        });

        // Guardar el asiento balanceado
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
