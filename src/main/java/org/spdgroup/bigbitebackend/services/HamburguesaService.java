package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.repositories.IHamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.HamburguesaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        String imagenUrl = storageService.uploadFile(imagen);

        hamburguesaDTO.setUrlImagen(imagenUrl);
        Hamburguesa hamburguesa = hamburguesaMapper.hamburguesaDTOToHamburguesa(hamburguesaDTO);

        hamburguesaRepo.save(hamburguesa);
    }

    public List<Hamburguesa> obtenerHamburguesas(){
        return hamburguesaRepo.findAll();
    }

    public void editarHamburguesa(HamburguesaDTO hamburguesaDTO, MultipartFile imagen, Long id) throws IOException {

        if (!hamburguesaRepo.existsById(id)) {
            throw new ProductNotFoundException("Hamburguesa no encontrada");
        }

        // Se guarda la imagen de la hamburguesa
        String imagenUrl = storageService.uploadFile(imagen);
        hamburguesaDTO.setUrlImagen(imagenUrl);

        Hamburguesa hamburguesa = hamburguesaMapper.hamburguesaDTOToHamburguesa(hamburguesaDTO);
        hamburguesa.setId(id);

        hamburguesaRepo.save(hamburguesa);

    }
}
