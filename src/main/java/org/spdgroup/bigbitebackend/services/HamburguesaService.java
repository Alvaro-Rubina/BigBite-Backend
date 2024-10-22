package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleInsumoDTO;
import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.repositories.HamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.HamburguesaMapper;
import org.spdgroup.bigbitebackend.utils.mapper.InsumoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HamburguesaService {

    @Autowired
    private HamburguesaRepository hamburguesaRepo;

    @Autowired
    private HamburguesaMapper hamburguesaMapper;

    @Autowired
    private DetalleInsumoService detalleInsumoService;

    @Autowired
    private GoogleCloudStorageService storageService;

    //
    public void registrarHamburguesa(HamburguesaDTO hamburguesaDTO, MultipartFile imagen) throws IOException {

        Hamburguesa hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);

        // Se guarda la imagen de la hamburguesa
        String imagenUrl = storageService.uploadFile(imagen);

        // Se guardan los insumos
        List<DetalleInsumo> detalleInsumos = new ArrayList<>();
        for (DetalleInsumoDTO detalleInsumoDTO : hamburguesaDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumoService.registrarDetalleInsumo(detalleInsumoDTO);
            detalleInsumos.add(detalleInsumo);
        }

        hamburguesa.setInsumos(detalleInsumos);
        hamburguesa.setUrlImagen(imagenUrl);

        hamburguesaRepo.save(hamburguesa);
    }

    //
    public Hamburguesa obtenerHamburguesaPorId(Long id){
        return hamburguesaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Hamburguesa no encontrada"));
    }

    //
    public List<Hamburguesa> obtenerHamburguesas(){
        return hamburguesaRepo.findAll();
    }

    //
    public void editarHamburguesa(HamburguesaDTO hamburguesaDTO, MultipartFile imagen, Long id) throws IOException {

        // Obtener la hamburguesa existente
        Hamburguesa hamburguesaExistente = hamburguesaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Hamburguesa no encontrada"));

        Hamburguesa hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            hamburguesa.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            hamburguesa.setUrlImagen(hamburguesaExistente.getUrlImagen());
        }

        // Mapear el DTO a la entidad y conservar el ID de la hamburguesa existente
        hamburguesa.setId(id);

        // Guardar los cambios
        hamburguesaRepo.save(hamburguesa);
    }

    public void venderHamburguesa(Long id) {
        Hamburguesa hamburguesa = this.obtenerHamburguesaPorId(id);

        for (DetalleInsumo detalleInsumo : hamburguesa.getInsumos()) {
            detalleInsumoService.actualizarStockInsumo(detalleInsumo.getInsumo().getId(), detalleInsumo.getCantidad());
        }

        hamburguesaRepo.save(hamburguesa);
    }

}
