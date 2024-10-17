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
        Hamburguesa hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);

        hamburguesaRepo.save(hamburguesa);
    }

    public List<Hamburguesa> obtenerHamburguesas(){
        return hamburguesaRepo.findAll();
    }

    public void editarHamburguesa(HamburguesaDTO hamburguesaDTO, MultipartFile imagen, Long id) throws IOException {

        // Verificar si la hamburguesa existe
        if (!hamburguesaRepo.existsById(id)) {
            throw new ProductNotFoundException("Hamburguesa no encontrada");
        }

        // Obtener la hamburguesa existente
        Hamburguesa hamburguesaExistente = hamburguesaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Hamburguesa no encontrada"));

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            hamburguesaDTO.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            hamburguesaDTO.setUrlImagen(hamburguesaExistente.getUrlImagen());
        }

        // Mapear el DTO a la entidad y conservar el ID de la hamburguesa existente
        Hamburguesa hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);
        hamburguesa.setId(id);

        // Guardar los cambios
        hamburguesaRepo.save(hamburguesa);
    }

}
