package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    // Traer una lista de asientos buscando de cierta cuenta

    List<Asiento> findByCuentaCodigo(String codigo);

    List<Asiento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
