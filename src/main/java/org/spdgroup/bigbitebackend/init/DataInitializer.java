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
            if (cuentaRepository.count() == 0) {  // Si no hay cuentas en la base de datos
                List<Cuenta> planDeCuentas = Arrays.asList(
                        new Cuenta("1.1.1.01", "Efectivo", "Débito"),
                        new Cuenta("1.1.1.02", "Banco XX", "Débito"),
                        new Cuenta("1.1.1.03", "Plataformas de Pago Mercado Pago", "Débito"),
                        new Cuenta("1.1.1.04", "Plataformas de Pago Binance", "Débito"),
                        new Cuenta("1.1.2.01", "Deudores por venta", "Débito"),
                        new Cuenta("1.1.3.01", "Materias Primas", "Débito"),
                        new Cuenta("1.1.3.02", "Productos Terminados", "Débito"),
                        new Cuenta("1.2.1.1.01", "Equipos de Cocina", "Débito"),
                        new Cuenta("1.2.1.1.02", "Depreciación Acumulada Equipos de Cocina", "Crédito"),
                        new Cuenta("1.2.1.1.03", "Mobiliario", "Débito"),
                        new Cuenta("1.2.1.1.04", "Depreciación Acumulada Mobiliario", "Crédito"),
                        new Cuenta("1.2.1.2.01", "Vehículos para Delivery", "Débito"),
                        new Cuenta("1.2.1.2.02", "Depreciación Acumulada Vehículos delivery", "Crédito"),
                        new Cuenta("2.1.1.01", "Proveedores", "Crédito"),
                        new Cuenta("2.1.1.02", "Acreedores Varios", "Crédito"),
                        new Cuenta("2.1.2.01", "IVA a Pagar", "Crédito"),
                        new Cuenta("2.1.2.02", "Ingresos Brutos a pagar", "Crédito"),
                        new Cuenta("2.2.1.01", "Préstamos Bancarios", "Crédito"),
                        new Cuenta("3.1.1.01", "Capital Social", "Crédito"),
                        new Cuenta("3.2.1.01", "Resultado del Ejercicio", "Crédito"),
                        new Cuenta("3.2.1.02", "Resultados Acumulados", "Crédito"),
                        new Cuenta("4.1.1.01", "Ventas de Hamburguesas", "Crédito"),
                        new Cuenta("4.1.1.02", "Ventas de Bebidas", "Crédito"),
                        new Cuenta("4.1.1.03", "Ventas de Otros Productos (Postres, Extras)", "Crédito"),
                        new Cuenta("5.1.1", "Costo de Mercadería Vendida", "Débito"),
                        new Cuenta("5.2.1", "Sueldos del Personal de Cocina", "Débito"),
                        new Cuenta("5.2.2", "Cargas sociales", "Débito"),
                        new Cuenta("5.2.3", "Consumo de Energía, Agua, Gas", "Débito"),
                        new Cuenta("5.2.4", "Depreciación Bienes de Uso", "Débito"),
                        new Cuenta("5.3.1", "Sueldos Personal (Cocina y Administración)", "Débito"),
                        new Cuenta("5.3.2", "Cargas sociales", "Débito"),
                        new Cuenta("5.3.3", "Alquiler", "Débito"),
                        new Cuenta("5.3.4", "Servicios Públicos (Internet, Agua, Gas, Electricidad)", "Débito"),
                        new Cuenta("6.1", "Comisiones de Plataformas de Delivery (PedidosYa, Rappi)", "Débito")
                );
                cuentaRepository.saveAll(planDeCuentas);
            }
        }
    }


