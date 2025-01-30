package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.InsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.Insumo;
import org.spdgroup.bigbitebackend.repositories.InsumoRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.InsumoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService {  // TODO: Revisar este service completo!!!

    @Autowired
    private InsumoRepository insumoRepo;

    @Autowired
    private InsumoMapper insumoMapper;

    public void registrarInsumo(InsumoDTO insumoDTO) {
        Insumo insumo = insumoMapper.toEntity(insumoDTO);
        insumoRepo.save(insumo);
    }

    public Insumo obtenerInsumoPorId(Long id) {
        return insumoRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Insumo no encontrado"));
    }

    public List<Insumo> obtenerInsumos() {
        return insumoRepo.findAll();
    }

    public void editarInsumo(InsumoDTO insumoDTO, Long id) {
        // Obtener el insumo si existe
        if (!insumoRepo.existsById(id)) {
            throw new ProductNotFoundException("Insumo no encontrado");
        }

        Insumo insumo = insumoMapper.toEntity(insumoDTO);
        insumo.setId(id);
        insumoRepo.save(insumo);
    }
}
