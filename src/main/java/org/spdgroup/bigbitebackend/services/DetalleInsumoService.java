package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleInsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;
import org.spdgroup.bigbitebackend.model.entities.Insumo;
import org.spdgroup.bigbitebackend.repositories.DetalleInsumoRepository;
import org.spdgroup.bigbitebackend.utils.mapper.InsumoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleInsumoService {  // TODO: Revisar este service completo!!!

    @Autowired
    private DetalleInsumoRepository detalleInsumoRepo;

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private InsumoMapper insumoMapper;

    //
    public DetalleInsumo registrarDetalleInsumo(DetalleInsumoDTO detalleInsumoDTO){
        DetalleInsumo detalleInsumo = new DetalleInsumo();

        detalleInsumo.setInsumo(insumoService.obtenerInsumoPorId(detalleInsumoDTO.getInsumoId()));
        detalleInsumo.setCantidad(detalleInsumoDTO.getCantidad());

        return detalleInsumoRepo.save(detalleInsumo);
    }

    public void actualizarStockInsumo(Long insumoId, double cantidad){
        Insumo insumo = insumoService.obtenerInsumoPorId(insumoId);
        insumo.setStock(insumo.getStock() - cantidad);
        insumoService.editarInsumo(insumoMapper.toDTO(insumo), insumo.getId());
    }
}
