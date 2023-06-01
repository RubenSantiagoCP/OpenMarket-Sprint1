
package co.unicauca.openmarket.commons.domain;

/**
 * Clase que representa una compra.
 */

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
    
    /**
     * Constructor por defecto de la clase Buy.
     */
    public Buy() {}

     /**
     * Constructor de la clase Buy.
     * @param id Identificador de la compra.
     * @param compradorId Identificador del comprador.
     * @param productoId Identificador del producto comprado.
     * @param estado Estado de la compra.
     * @param fechaCompra Fecha de la compra.
     */
    public Buy(Long id, Long compradorId, Long productoId, String estado, String fechaCompra) {
        this.id = id;
        this.compradorId = compradorId;
        this.productoId = productoId;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
     /**
     * Obtiene el identificador de la compra.
     * @return Identificador de la compra.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Establece el identificador de la compra.
     * @param id Identificador de la compra.
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * Obtiene el identificador del comprador.
     * @return Identificador del comprador.
     */
    public Long getCompradorId() {
        return compradorId;
    }
    
     /**
     * Establece el identificador del comprador.
     * @param compradorId Identificador del comprador.
     */
    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

     /**
     * Obtiene el identificador del producto comprado.
     * @return Identificador del producto comprado.
     */
    public Long getProductoId() {
        return productoId;
    }
    
     /**
     * Establece el identificador del producto comprado.
     * @param productoId Identificador del producto comprado.
     */
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

     /**
     * Obtiene el estado de la compra.
     * @return Estado de la compra.
     */
    public String getEstado() {
        return estado;
    }
    
     /**
     * Establece el estado de la compra.
     * @param estado Estado de la compra.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha de la compra.
     * @return Fecha de la compra.
     */
    public String getFechaCompra() {
        return fechaCompra;
    }
    
    /**
     * Establece la fecha de la compra.
     * @param fechaCompra Fecha de la compra.
     */
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    //</editor-fold>
    
    
}
