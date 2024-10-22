package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.dtos.AsientoPorDiaDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.repositories.AsientoRepository;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.utils.mapper.AsientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        asiento.setFecha(LocalDate.now());
        asientoRepo.save(asiento);
    }

    // TODO: VER SI LO OCUPAMOS DESPUES
    public Asiento obtenerAsientoPorId(Long id) {
        return asientoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));
    }

    public List<Asiento> obtenerAsientosPorCuenta(String codigo) {
        return asientoRepo.findByCuentaCodigo(codigo);
    }

    public List<Asiento> obtenerAsientos() {
        return asientoRepo.findAll();
    }
    public List<AsientoPorDiaDTO> obtenerSumaAsientosPorDia(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Asiento> asientos = asientoRepo.findByFechaBetween(fechaInicio, fechaFin);
        return asientos.stream()
                .collect(Collectors.groupingBy(Asiento::getFecha)) // Agrupar por fecha
                .entrySet().stream()
                .map(entry -> {
                    LocalDate fecha = entry.getKey();

                    // Filtrar por el tipo de cuenta (Ingreso o Egreso)
                    double totalIngresos = entry.getValue().stream()
                            .filter(asiento -> "Ingreso".equals(asiento.getCuenta().getTipoCuenta())) // Si es "Ingreso"
                            .mapToDouble(Asiento::getMonto)
                            .sum();

                    double totalEgresos = entry.getValue().stream()
                            .filter(asiento -> "Egreso".equals(asiento.getCuenta().getTipoCuenta())) // Si es "Egreso"
                            .mapToDouble(Asiento::getMonto)
                            .sum();

                    // Crear el DTO con las sumas
                    return new AsientoPorDiaDTO(fecha, totalIngresos, totalEgresos);
                })
                .collect(Collectors.toList());
    }
}


