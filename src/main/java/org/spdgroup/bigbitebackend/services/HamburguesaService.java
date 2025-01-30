package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleInsumoDTO;
import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.repositories.HamburguesaRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.HamburguesaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HamburguesaService {  // TODO: Revisar este service completo!!!

    @Autowired
    private HamburguesaRepository hamburguesaRepo;

    @Autowired
    private HamburguesaMapper hamburguesaMapper;

    @Autowired
    private DetalleInsumoService detalleInsumoService;

    @Autowired
    private InsumoService insumoService;

    //
    public void registrarHamburguesa(HamburguesaDTO hamburguesaDTO) {
        Hamburguesa hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);

        // Se guardan los insumos
        List<DetalleInsumo> detalleInsumos = new ArrayList<>();
        for (DetalleInsumoDTO detalleInsumoDTO : hamburguesaDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumoService.registrarDetalleInsumo(detalleInsumoDTO);
            detalleInsumos.add(detalleInsumo);
        }

        hamburguesa.setInsumos(detalleInsumos);
        hamburguesa.setCantItems(1L);
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
    public void editarHamburguesa(HamburguesaDTO hamburguesaDTO, Long id) throws IOException {

        Hamburguesa hamburguesa = hamburguesaRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Hamburguesa no encontrada"));

        List<DetalleInsumo> detalleInsumosExistentes = hamburguesa.getInsumos();
        List<DetalleInsumo> detalleInsumosActualizados = new ArrayList<>();

        hamburguesa = hamburguesaMapper.toEntity(hamburguesaDTO);
        hamburguesa.setId(id);

        for (DetalleInsumoDTO detalleInsumoDTO : hamburguesaDTO.getDetalleInsumos()) {
            DetalleInsumo detalleInsumo = detalleInsumosExistentes.stream()
                    .filter(di -> di.getInsumo().getId().equals(detalleInsumoDTO.getInsumoId()))
                    .findFirst()
                    .orElse(new DetalleInsumo());

            detalleInsumo.setCantidad(detalleInsumoDTO.getCantidad());
            detalleInsumo.setInsumo(insumoService.obtenerInsumoPorId(detalleInsumoDTO.getInsumoId()));
            detalleInsumosActualizados.add(detalleInsumo);
        }

        hamburguesa.setInsumos(detalleInsumosActualizados);
        hamburguesa.setCantItems(1L);

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
