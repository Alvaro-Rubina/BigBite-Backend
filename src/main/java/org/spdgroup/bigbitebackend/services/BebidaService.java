package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.repositories.BebidaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.BebidaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BebidaService {

    @Autowired
    BebidaRepository bebidaRepo;

    @Autowired
    BebidaMapper bebidaMapper;

    public void registrarBebida(BebidaDTO bebidaDTO) {
        Bebida bebida = bebidaMapper.toEntity(bebidaDTO);
        bebida.setCantItems(1L);
        bebidaRepo.save(bebida);
    }

    public List<Bebida> obtenerBebidas(){
        return bebidaRepo.findAll();
    }

    public Bebida obtenerBebidaPorId(Long id) {
        return bebidaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Bebida no encontrada"));
    }

    public void editarBebida(BebidaDTO bebidaDTO, Long id) {
        Bebida bebidaExistente = bebidaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Bebida no encontrada"));

        Bebida bebida = bebidaMapper.toEntity(bebidaDTO);
        bebida.setCantItems(1L);
        bebida.setId(id);

        bebidaRepo.save(bebida);
    }
}
