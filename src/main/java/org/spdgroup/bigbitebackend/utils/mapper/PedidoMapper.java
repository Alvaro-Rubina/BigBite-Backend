package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.Pedido;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface PedidoMapper {

    Pedido toEntity(PedidoDTO pedidoDTO);
}
