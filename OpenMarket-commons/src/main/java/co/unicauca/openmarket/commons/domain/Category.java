
package co.unicauca.openmarket.commons.domain;

/**
 * Representa la categoría de un producto.
 */
public class Category {
    private Long categoryId;
    private String name;
    
     /**
     * Constructor de la clase Category.
     * @param categoryId Identificador único de la categoría.
     * @param name Nombre de la categoría.
     */
    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    
    /**
     * Constructor sin argumentos de la clase Category.
     */
    public Category() {
    }

     /**
     * Obtiene el identificador de la categoría.
     * @return El identificador de la categoría.
     */
    public Long getCategoryId() {
        return categoryId;
    }

     /**
     * Establece el identificador de la categoría.
     * @param categoryId El identificador de la categoría.
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

     /**
     * Obtiene el nombre de la categoría.
     * @return El nombre de la categoría.
     */
    public String getName() {
        return name;
    }

     /**
     * Establece el nombre de la categoría.
     * @param name El nombre de la categoría.
     */
    public void setName(String name) {
        this.name = name;
    }
     
}
