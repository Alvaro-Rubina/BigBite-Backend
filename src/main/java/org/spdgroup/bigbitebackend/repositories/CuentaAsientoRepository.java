package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaAsientoRepository extends JpaRepository<CuentaAsiento, Long> {
}
