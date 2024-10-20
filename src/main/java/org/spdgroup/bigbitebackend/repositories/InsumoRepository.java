package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}
