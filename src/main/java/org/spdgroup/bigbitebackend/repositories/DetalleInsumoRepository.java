package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleInsumoRepository extends JpaRepository<DetalleInsumo, Long> {
}
