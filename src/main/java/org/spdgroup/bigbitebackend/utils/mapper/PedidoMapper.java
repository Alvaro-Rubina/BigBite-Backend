package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.Pedido;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface PedidoMapper {

    @Mapping(source = "price", target = "subTotal")
    @Mapping(source = "title", target = "titulo")
    Pedido toEntity(PedidoDTO pedidoDTO);
}
