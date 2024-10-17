package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.PapasFritasDTO;
import org.spdgroup.bigbitebackend.model.entities.PapasFritas;
import org.spdgroup.bigbitebackend.repositories.IPapasFritasRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.PapasFritasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PapasFritasService {

    @Autowired
    private IPapasFritasRepository papasFritasRepo;

    @Autowired
    private PapasFritasMapper papasFritasMapper;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarPapasFritas(PapasFritasDTO papasFritasDTO, MultipartFile imagen) throws IOException {

        // Se guarda la imagen de la bebida
        String imagenUrl = storageService.uploadFile(imagen);

        papasFritasDTO.setUrlImagen(imagenUrl);
        PapasFritas papasFritas = papasFritasMapper.toEntity(papasFritasDTO);

        papasFritasRepo.save(papasFritas);
    }

    public List<PapasFritas> obtenerPapasFritas(){
        return papasFritasRepo.findAll();
    }

    public void editarPapasFritas(PapasFritasDTO papasFritasDTO, MultipartFile imagen, Long id) throws IOException {

        // Verificar si la bebida existe
        if (!papasFritasRepo.existsById(id)) {
            throw new ProductNotFoundException("PapasFritas no encontrada");
        }

        // Obtener la bebida existente
        PapasFritas papasFritasExistente = papasFritasRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("PapasFritas no encontrada"));

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            papasFritasDTO.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            papasFritasDTO.setUrlImagen(papasFritasExistente.getUrlImagen());
        }

        PapasFritas papasFritas = papasFritasMapper.toEntity(papasFritasDTO);
        papasFritas.setId(id);

        papasFritasRepo.save(papasFritas);
    }
}
