package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    // Traer una lista de asientos buscando de cierta cuenta

    List<Asiento> findByCuentaId(Long cuentaId);

}
