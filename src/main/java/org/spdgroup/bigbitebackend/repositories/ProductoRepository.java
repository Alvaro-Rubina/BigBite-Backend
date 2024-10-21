package org.spdgroup.bigbitebackend.repositories;

import org.spdgroup.bigbitebackend.model.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
