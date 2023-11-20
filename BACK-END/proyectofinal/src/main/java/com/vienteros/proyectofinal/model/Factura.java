package com.vienteros.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @OneToMany(targetEntity = Venta.class,fetch = FetchType.LAZY, mappedBy = "factura")
    @JsonIgnore
    private List<Venta> ventas;

    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Factura() {
        LocalDateTime fechaHoraActual = LocalDateTime.now(); // Obtener la fecha y hora actual
        this.fecha = java.sql.Timestamp.valueOf(fechaHoraActual); // Convertir LocalDateTime a Timestamp y asignar a fecha
    }
}
