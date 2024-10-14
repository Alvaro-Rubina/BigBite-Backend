package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.repositories.IHamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.mapper.HamburguesaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class HamburguesaService {

    @Autowired
    IHamburguesaRepository hamburguesaRepo;

    @Autowired
    HamburguesaMapper hamburguesaMapper;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarHamburguesa(HamburguesaDTO hamburguesaDTO, MultipartFile imagen) throws IOException {

        // Se guarda la imagen de la hamburguesa
        String imagenHamburguesaUrl = storageService.uploadFile(imagen);

        hamburguesaDTO.setUrlImage(imagenHamburguesaUrl);
        Hamburguesa hamburguesa = hamburguesaMapper.hamburguesaDTOToHamburguesa(hamburguesaDTO);

        hamburguesaRepo.save(hamburguesa);
    }
}
