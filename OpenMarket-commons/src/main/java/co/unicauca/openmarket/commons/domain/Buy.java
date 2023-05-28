/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.commons.domain;

import java.util.Date;

/**
 *
 * @author nimbachi
 */
public class Buy {
    //Atributos
    private Long id;
    private Long compradorId;
    private Long productoId;
    private String estado;
    private String fechaCompra;
    
    //Contructores
    public Buy() {}

    public Buy(Long id, Long compradorId, Long productoId, String estado, String fechaCompra) {
        this.id = id;
        this.compradorId = compradorId;
        this.productoId = productoId;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompradorId() {
        return compradorId;
    }
    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    //</editor-fold>
    
    
}
