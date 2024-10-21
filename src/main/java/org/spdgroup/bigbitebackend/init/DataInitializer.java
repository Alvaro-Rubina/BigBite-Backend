package org.spdgroup.bigbitebackend.init;

import jakarta.annotation.PostConstruct;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final ICuentaRepository cuentaRepository;

    @Autowired
    public DataInitializer(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @PostConstruct
    public void init() {
        if (cuentaRepository.count() == 0) {
            List<Cuenta> planDeCuentas = Arrays.asList(
                    new Cuenta("1.1.1.01", "Efectivo", "Activo"),
                    new Cuenta("1.1.1.02", "Banco XX", "Activo"),
                    new Cuenta("1.1.1.03", "Plataformas de Pago Mercado Pago", "Activo"),
                    new Cuenta("1.1.1.04", "Plataformas de Pago Binance", "Activo"),
                    new Cuenta("1.1.2.01", "Deudores por venta", "Activo"),
                    new Cuenta("1.1.3.01", "Materias Primas", "Activo"),
                    new Cuenta("1.1.3.02", "Productos Terminados", "Activo"),
                    new Cuenta("2.1.1.01", "Proveedores", "Pasivo"),
                    new Cuenta("2.1.1.02", "Acreedores Varios", "Pasivo"),
                    new Cuenta("2.1.2.01", "IVA a Pagar", "Pasivo"),
                    new Cuenta("2.1.2.02", "Ingresos Brutos a pagar", "Pasivo"),
                    new Cuenta("2.2.1.01", "Préstamos Bancarios", "Pasivo"),
                    new Cuenta("3.1.1.01", "Capital Social", "Patrimonio"),
                    new Cuenta("3.2.1.01", "Resultado del Ejercicio", "Patrimonio"),
                    new Cuenta("3.2.1.02", "Resultados Acumulados", "Patrimonio"),
                    new Cuenta("4.1.1.01", "Ventas de Hamburguesas", "Ingreso"),
                    new Cuenta("4.1.1.02", "Ventas de Bebidas", "Ingreso"),
                    new Cuenta("4.1.1.03", "Ventas de Otros Productos (Postres, Extras)", "Ingreso"),
                    new Cuenta("5.1.1", "Costo de Mercadería Vendida", "Egreso"),
                    new Cuenta("5.2.1", "Sueldos del Personal de Cocina", "Egreso"),
                    new Cuenta("5.2.2", "Cargas sociales", "Egreso"),
                    new Cuenta("5.2.3", "Consumo de Energía, Agua, Gas", "Egreso"),
                    new Cuenta("5.2.4", "Depreciación Bienes de Uso", "Egreso"),
                    new Cuenta("5.3.1", "Sueldos Personal (Cocina y Administración)", "Egreso"),
                    new Cuenta("5.3.2", "Cargas sociales", "Egreso"),
                    new Cuenta("5.3.3", "Alquiler", "Egreso"),
                    new Cuenta("5.3.4", "Servicios Públicos (Internet, Agua, Gas, Electricidad)", "Egreso"),
                    new Cuenta("6.1", "Comisiones de Plataformas de Delivery (PedidosYa, Rappi)", "Egreso")
            );
            cuentaRepository.saveAll(planDeCuentas);
        }
    }
}



