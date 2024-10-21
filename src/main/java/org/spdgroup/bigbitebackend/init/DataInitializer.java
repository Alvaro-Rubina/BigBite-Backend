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
                        new Cuenta("1.1.1.01", "Efectivo"),
                        new Cuenta("1.1.1.02", "Banco XX"),
                        new Cuenta("1.1.1.03", "Plataformas de Pago Mercado Pago"),
                        new Cuenta("1.1.1.04", "Plataformas de Pago Binance"),
                        new Cuenta("1.1.2.01", "Deudores por venta"),
                        new Cuenta("1.1.3.01", "Materias Primas"),
                        new Cuenta("1.1.3.02", "Productos Terminados"),
                        new Cuenta("1.2.1.1.01", "Equipos de Cocina"),
                        new Cuenta("1.2.1.1.02", "Depreciación Acumulada Equipos de Cocina"),
                        new Cuenta("1.2.1.1.03", "Mobiliario"),
                        new Cuenta("1.2.1.1.04", "Depreciación Acumulada Mobiliario"),
                        new Cuenta("1.2.1.2.01", "Vehículos para Delivery"),
                        new Cuenta("1.2.1.2.02", "Depreciación Acumulada Vehículos delivery"),
                        new Cuenta("2.1.1.01", "Proveedores"),
                        new Cuenta("2.1.1.02", "Acreedores Varios"),
                        new Cuenta("2.1.2.01", "IVA a Pagar"),
                        new Cuenta("2.1.2.02", "Ingresos Brutos a pagar"),
                        new Cuenta("2.2.1.01", "Préstamos Bancarios"),
                        new Cuenta("3.1.1.01", "Capital Social"),
                        new Cuenta("3.2.1.01", "Resultado del Ejercicio"),
                        new Cuenta("3.2.1.02", "Resultados Acumulados"),
                        new Cuenta("4.1.1.01", "Ventas de Hamburguesas"),
                        new Cuenta("4.1.1.02", "Ventas de Bebidas"),
                        new Cuenta("4.1.1.03", "Ventas de Otros Productos (Postres, Extras)"),
                        new Cuenta("5.1.1", "Costo de Mercadería Vendida"),
                        new Cuenta("5.2.1", "Sueldos del Personal de Cocina"),
                        new Cuenta("5.2.2", "Cargas sociales"),
                        new Cuenta("5.2.3", "Consumo de Energía, Agua, Gas"),
                        new Cuenta("5.2.4", "Depreciación Bienes de Uso"),
                        new Cuenta("5.3.1", "Sueldos Personal (Cocina y Administración)"),
                        new Cuenta("5.3.2", "Cargas sociales"),
                        new Cuenta("5.3.3", "Alquiler"),
                        new Cuenta("5.3.4", "Servicios Públicos (Internet, Agua, Gas, Electricidad)"),
                        new Cuenta("6.1", "Comisiones de Plataformas de Delivery (PedidosYa, Rappi)")
                );
                cuentaRepository.saveAll(planDeCuentas);
            }
        }
    }


