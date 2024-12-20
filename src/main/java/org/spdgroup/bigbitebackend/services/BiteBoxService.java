package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.BiteBoxDTO;
import org.spdgroup.bigbitebackend.model.entities.BiteBox;
import org.spdgroup.bigbitebackend.repositories.BebidaRepository;
import org.spdgroup.bigbitebackend.repositories.BiteBoxRepository;
import org.spdgroup.bigbitebackend.repositories.HamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.BiteBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BiteBoxService {

    @Autowired
    BiteBoxRepository biteBoxRepo;

    @Autowired
    HamburguesaRepository hamburguesaRepo;

    @Autowired
    BebidaRepository bebidaRepository;

    @Autowired
    BiteBoxMapper biteBoxMapper;

    public void registrarBiteBox(BiteBoxDTO biteBoxDTO) throws IOException {

        BiteBox biteBox = biteBoxMapper.toEntity(biteBoxDTO);
        biteBox.setHamburguesa(hamburguesaRepo.findById((long) biteBoxDTO.getHamburguesa())
                .orElseThrow(() -> new RuntimeException("Hamburguesa no encontrada")));
        biteBox.setBebida(bebidaRepository.findById((long) biteBoxDTO.getBebida())
                .orElseThrow(() -> new RuntimeException("Bebida no encontrada")));

        biteBox.setCantItems(1L);

        biteBoxRepo.save(biteBox);
    }

    public List<BiteBox> obtenerBiteBoxes(){
        return biteBoxRepo.findAll();
    }

    public BiteBox obtenerBiteBoxPorId(Long id){
        return biteBoxRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("BiteBox no encontrado"));
    }


    public void editarBiteBox(BiteBoxDTO biteBoxDTO, Long id) throws IOException {

        // Obtener el BiteBox existente
        BiteBox biteBoxExistente = biteBoxRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("BiteBox no encontrado"));

        BiteBox biteBox = biteBoxMapper.toEntity(biteBoxDTO);
        biteBox.setId(id);

        biteBox.setHamburguesa(hamburguesaRepo.findById((long) biteBoxDTO.getHamburguesa())
                .orElseThrow(() -> new ProductNotFoundException("Hamburguesa no encontrada")));

        biteBox.setBebida(bebidaRepository.findById((long) biteBoxDTO.getBebida())
                .orElseThrow(() -> new ProductNotFoundException("Bebida no encontrada")));

        biteBox.setCantItems(1L);

        biteBoxRepo.save(biteBox);
    }

}
