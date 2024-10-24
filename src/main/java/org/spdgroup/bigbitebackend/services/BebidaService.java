package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.repositories.BebidaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.BebidaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BebidaService {

    @Autowired
    BebidaRepository bebidaRepo;

    @Autowired
    BebidaMapper bebidaMapper;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarBebida(BebidaDTO bebidaDTO, MultipartFile imagen) throws IOException {

        // Se guarda la imagen de la bebida
        String imagenUrl = storageService.uploadFile(imagen);

        bebidaDTO.setUrlImagen(imagenUrl);
        Bebida bebida = bebidaMapper.toEntity(bebidaDTO);
        bebida.setRepeticion(1L);

        bebidaRepo.save(bebida);
    }

    public List<Bebida> obtenerBebidas(){
        return bebidaRepo.findAll();
    }

    public Bebida obtenerBebidaPorId(Long id) {
        return bebidaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Bebida no encontrada"));
    }

    public void editarBebida(BebidaDTO bebidaDTO, MultipartFile imagen, Long id) throws IOException {

        // Obtener la bebida existente
        Bebida bebidaExistente = bebidaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Bebida no encontrada"));

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            bebidaDTO.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            bebidaDTO.setUrlImagen(bebidaExistente.getUrlImagen());
        }

        Bebida bebida = bebidaMapper.toEntity(bebidaDTO);
        bebida.setRepeticion(1L);
        bebida.setId(id);

        bebidaRepo.save(bebida);
    }
}
