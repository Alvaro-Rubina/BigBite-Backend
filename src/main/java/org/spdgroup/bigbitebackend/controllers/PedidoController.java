package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.Pedido;
import org.spdgroup.bigbitebackend.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/agregar")

    public ResponseEntity<?> registrarPedido(@RequestBody PedidoDTO pedidoDTO) {
        pedidoService.registrarPedido(pedidoDTO);
        return ResponseEntity.ok("Pedido registrado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping()
    public ResponseEntity<?> obtenerPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerPedidos());
    }

    @GetMapping("/factura/{id}")
    public ResponseEntity<?> obtenerFactura(Long id) {
        return ResponseEntity.ok(pedidoService.emitirFactura(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarPedido(@RequestBody PedidoDTO pedidoDTO, @PathVariable Long id) {
        pedidoService.editarPedido(pedidoDTO, id);
        return ResponseEntity.ok("Pedido editado correctamente");
    }


}
