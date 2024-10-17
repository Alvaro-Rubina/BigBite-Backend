package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.BiteBoxDTO;
import org.spdgroup.bigbitebackend.model.entities.BiteBox;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.repositories.IBiteBoxRepository;
import org.spdgroup.bigbitebackend.repositories.IHamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.BiteBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BiteBoxService {

    @Autowired
    IBiteBoxRepository biteBoxRepo;

    @Autowired
    IHamburguesaRepository hamburguesaRepo;

    @Autowired
    BiteBoxMapper biteBoxMapper;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarBiteBox(BiteBoxDTO biteBoxDTO, MultipartFile imagen) throws IOException {

        // Se guarda la imagen del BiteBox
        String imagenUrl = storageService.uploadFile(imagen);
        biteBoxDTO.setUrlImagen(imagenUrl);

        BiteBox biteBox = biteBoxMapper.toEntity(biteBoxDTO);
        biteBox.setHamburguesa(hamburguesaRepo.findById((long) biteBoxDTO.getHamburguesa())
                .orElseThrow(() -> new RuntimeException("Hamburguesa no encontrada")));


        biteBoxRepo.save(biteBox);
    }

    public List<BiteBox> obtenerBiteBoxes(){
        return biteBoxRepo.findAll();
    }


    public void editarBiteBox(BiteBoxDTO biteBoxDTO, MultipartFile imagen, Long id) throws IOException {

        // Verificar si el BiteBox existe
        if (!biteBoxRepo.existsById(id)) {
            throw new ProductNotFoundException("BiteBox no encontrado");
        }

        // Obtener el BiteBox existente
        BiteBox biteBoxExistente = biteBoxRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("BiteBox no encontrado"));

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            biteBoxDTO.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            biteBoxDTO.setUrlImagen(biteBoxExistente.getUrlImagen());
        }

        BiteBox biteBox = biteBoxMapper.toEntity(biteBoxDTO);
        biteBox.setHamburguesa(hamburguesaRepo.findById((long) biteBoxDTO.getHamburguesa())
                .orElseThrow(() -> new RuntimeException("Hamburguesa no encontrada")));
        biteBox.setId(id);

        biteBoxRepo.save(biteBox);
    }

}
