
package co.unicauca.openmarket.commons.domain;

/**
 * Representa un producto recibido.
 */
public class Product {
    private Long productId;

    private String name;
    
    private String description;
    private Long categoryId;
    private double price;
    private Long vendedorId;

    /**
     * Constructor de la clase Product.
     * @param productId Identificador único del producto.
     * @param name Nombre del producto.
     * @param description Descripción del producto.
     * @param price Precio del producto.
     * @param categoryId Identificador de la categoría del producto.
     * @param vendedorId Identificador del vendedor del producto.
     */
    public Product(Long productId, String name, String description, double price,Long categoryId, Long vendedorId ) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.categoryId=categoryId;
        this.price=price;
        this.vendedorId = vendedorId;
    }

     /**
     * Obtiene el identificador del vendedor del producto.
     * @return El identificador del vendedor del producto.
     */
    public Long getVendedorId() {
        return vendedorId;
    }

    /**
     * Establece el identificador del vendedor del producto.
     * @param vendedorId El identificador del vendedor del producto.
     */
    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }
    
     /**
     * Constructor sin argumentos de la clase Product.
     * Inicializa el atributo categoryId como null.
     */
    public Product(){
        this.categoryId=null;
    }

     /**
     * Obtiene el identificador único del producto.
     * @return El identificador único del producto.
     */
    public Long getProductId() {
        return productId;
    }

     /**
     * Establece el identificador único del producto.
     * @param productId El identificador único del producto.
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

     /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

      /**
     * Establece el nombre del producto.
     * @param name El nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

     /**
     * Obtiene la descripción del producto.
     * @return La descripción del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del producto.
     * @param description La descripción del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

     /**
     * Obtiene el identificador de la categoría del producto.
     * @return El identificador de la categoría del producto.
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Establece el identificador de la categoría del producto.
     * @param categoryId El identificador de la categoría del producto.
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrice() {
        return price;
    }

      /**
     * Establece el precio del producto.
     * @param price El precio del producto.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
