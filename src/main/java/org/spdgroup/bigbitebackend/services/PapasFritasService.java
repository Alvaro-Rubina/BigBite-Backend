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
import java.util.ArrayList;
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
    private InsumoService insumoService;

    @Autowired
    private GoogleCloudStorageService storageService;

    public void registrarPapasFritas(PapasFritasDTO papasFritasDTO, MultipartFile imagen) throws IOException {

        PapasFritas papasFritas = papasFritasMapper.toEntity(papasFritasDTO);

        // Se guarda la imagen de la bebida
        String imagenUrl = storageService.uploadFile(imagen);

        // Se guardan los insumos
        List<DetalleInsumo> detalleInsumos = new ArrayList<>();
        for (DetalleInsumoDTO detalleInsumoDTO : papasFritasDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumoService.registrarDetalleInsumo(detalleInsumoDTO);
            detalleInsumos.add(detalleInsumo);
        }

        papasFritas.setInsumos(detalleInsumos);
        papasFritas.setUrlImagen(imagenUrl);
        papasFritas.setRepeticion(1L);

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

        PapasFritas papasFritas = papasFritasRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Papas Fritas no encontradas"));

        List<DetalleInsumo> detalleInsumosExistentes = papasFritas.getInsumos();
        List<DetalleInsumo> detalleInsumosActualizados = new ArrayList<>();
        String urlImagenExistente = papasFritas.getUrlImagen();

        papasFritas = papasFritasMapper.toEntity(papasFritasDTO);
        papasFritas.setId(id);

        if (imagen != null && !imagen.isEmpty()) {
            String nuevaImagenUrl = storageService.uploadFile(imagen);
            papasFritas.setUrlImagen(nuevaImagenUrl);
        } else {
            papasFritas.setUrlImagen(urlImagenExistente);
        }

        for (DetalleInsumoDTO detalleInsumoDTO : papasFritasDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumosExistentes.stream()
                    .filter(di -> di.getInsumo().getId().equals(detalleInsumoDTO.getInsumoId()))
                    .findFirst()
                    .orElse(new DetalleInsumo());

            detalleInsumo.setCantidad(detalleInsumoDTO.getCantidad());
            detalleInsumo.setInsumo(insumoService.obtenerInsumoPorId(detalleInsumoDTO.getInsumoId()));
            detalleInsumosActualizados.add(detalleInsumo);
        }

        papasFritas.setInsumos(detalleInsumosActualizados);
        papasFritas.setRepeticion(1L);

        papasFritasRepo.save(papasFritas);
    }

    public void venderPapasFritas(Long id) {
        PapasFritas papasFritas = this.obtenerPapasFritasPorId(id);

        for (DetalleInsumo detalleInsumo : papasFritas.getInsumos()) {
            detalleInsumoService.actualizarStockInsumo(detalleInsumo.getInsumo().getId(), detalleInsumo.getCantidad());
        }

        papasFritasRepo.save(papasFritas);
    }
}
