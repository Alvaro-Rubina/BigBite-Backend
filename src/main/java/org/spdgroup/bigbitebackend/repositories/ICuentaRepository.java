package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNombre(String nombre);
}
