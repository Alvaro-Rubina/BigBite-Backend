package org.spdgroup.bigbitebackend.init;

import jakarta.annotation.PostConstruct;
import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private CuentaService cuentaService;

    @PostConstruct
    private void init() {
        double cantidadCuentas = cuentaService.obtenerCantidadDeCuentas();

        if (cantidadCuentas == 0) {
            List<CuentaDTO> planDeCuentas = Arrays.asList(
                    new CuentaDTO("1.1.1.01", "Efectivo", "Activo"),
                    new CuentaDTO("1.1.1.02", "Banco XX", "Activo"),
                    new CuentaDTO("1.1.1.03", "Plataformas de Pago Mercado Pago", "Activo"),
                    new CuentaDTO("1.1.1.04", "Plataformas de Pago Binance", "Activo"),
                    new CuentaDTO("1.1.2.01", "Deudores por venta", "Activo"),
                    new CuentaDTO("1.1.3.01", "Materias Primas", "Activo"),
                    new CuentaDTO("1.1.3.02", "Productos Terminados", "Activo"),
                    new CuentaDTO("2.1.1.01", "Proveedores", "Pasivo"),
                    new CuentaDTO("2.1.1.02", "Acreedores Varios", "Pasivo"),
                    new CuentaDTO("2.1.2.01", "IVA a Pagar", "Pasivo"),
                    new CuentaDTO("2.1.2.02", "Ingresos Brutos a pagar", "Pasivo"),
                    new CuentaDTO("2.2.1.01", "Préstamos Bancarios", "Pasivo"),
                    new CuentaDTO("3.1.1.01", "Capital Social", "Patrimonio"),
                    new CuentaDTO("3.2.1.01", "Resultado del Ejercicio", "Patrimonio"),
                    new CuentaDTO("3.2.1.02", "Resultados Acumulados", "Patrimonio"),
                    new CuentaDTO("4.1.1.01", "Ventas de Hamburguesas", "Ingreso"),
                    new CuentaDTO("4.1.1.02", "Ventas de Bebidas", "Ingreso"),
                    new CuentaDTO("4.1.1.03", "Ventas de Otros Productos (Postres, Extras)", "Ingreso"),
                    new CuentaDTO("5.1.1", "Costo de Mercadería Vendida", "Egreso"),
                    new CuentaDTO("5.2.1", "Sueldos del Personal de Cocina", "Egreso"),
                    new CuentaDTO("5.2.2", "Cargas sociales", "Egreso"),
                    new CuentaDTO("5.2.3", "Consumo de Energía, Agua, Gas", "Egreso"),
                    new CuentaDTO("5.2.4", "Depreciación Bienes de Uso", "Egreso"),
                    new CuentaDTO("5.3.1", "Sueldos Personal (Cocina y Administración)", "Egreso"),
                    new CuentaDTO("5.3.3", "Alquiler", "Egreso"),
                    new CuentaDTO("5.3.4", "Servicios Públicos (Internet, Agua, Gas, Electricidad)", "Egreso"),
                    new CuentaDTO("6.1", "Comisiones de Plataformas de Delivery (PedidosYa, Rappi)", "Egreso")
            );

            for (CuentaDTO cuentaDTO: planDeCuentas) {
                cuentaService.registrarCuenta(cuentaDTO);
            }
        }
    }
}
