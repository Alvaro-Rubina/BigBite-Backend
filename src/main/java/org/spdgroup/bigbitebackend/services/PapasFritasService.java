package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleInsumoDTO;
import org.spdgroup.bigbitebackend.model.dtos.PapasFritasDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.model.entities.PapasFritas;
import org.spdgroup.bigbitebackend.repositories.PapasFritasRepository;
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
    private PapasFritasRepository papasFritasRepo;

    @Autowired
    private PapasFritasMapper papasFritasMapper;

    @Autowired
    private DetalleInsumoService detalleInsumoService;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarPapasFritas(PapasFritasDTO papasFritasDTO, MultipartFile imagen) throws IOException {

        PapasFritas papasFritas = papasFritasMapper.toEntity(papasFritasDTO);

        // Se guarda la imagen de la bebida
        String imagenUrl = storageService.uploadFile(imagen);

        // Se guardan los insumos
        List<DetalleInsumo> detalleInsumos = null;
        for (DetalleInsumoDTO detalleInsumoDTO : papasFritasDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumoService.registrarDetalleInsumo(detalleInsumoDTO);
            detalleInsumos.add(detalleInsumo);
        }

        papasFritas.setInsumos(detalleInsumos);
        papasFritas.setUrlImagen(imagenUrl);


        papasFritasRepo.save(papasFritas);
    }

    public PapasFritas obtenerPapasFritasPorId(Long id){
        return papasFritasRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("PapasFritas no encontrada"));
    }

    public List<PapasFritas> obtenerPapasFritas(){
        return papasFritasRepo.findAll();
    }

    public void editarPapasFritas(PapasFritasDTO papasFritasDTO, MultipartFile imagen, Long id) throws IOException {

        // Obtener las Papas fritas existentes
        PapasFritas papasFritasExistente = papasFritasRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("PapasFritas no encontrada"));

        PapasFritas papasFritas = papasFritasMapper.toEntity(papasFritasDTO);

        // Si se sube una nueva imagen, actualizarla, de lo contrario, mantener la URL existente
        if (imagen != null && !imagen.isEmpty()) {
            // Subir la nueva imagen y obtener su URL
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            papasFritas.setUrlImagen(nuevaImagenUrl);
        } else {
            // Mantener la URL de la imagen existente
            papasFritas.setUrlImagen(papasFritasExistente.getUrlImagen());
        }

        //
        papasFritas.setId(id);

        papasFritasRepo.save(papasFritas);
    }

    public void venderHamburguesa(Long id) {
        PapasFritas papasFritas = this.obtenerPapasFritasPorId(id);

        for (DetalleInsumo detalleInsumo : papasFritas.getInsumos()) {
            detalleInsumoService.actualizarStockInsumo(detalleInsumo.getInsumo().getId(), detalleInsumo.getCantidad());
        }

        papasFritasRepo.save(papasFritas);
    }
}
