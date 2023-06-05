
package co.unicauca.openmaket.client.command;

import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.Product;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comando concreto que representa la operación de agregar un producto.
 * Implementa la interfaz Command.
 */
public class AddProductCommand implements Command {
    
    private final ProductService productService;
    private final Product product;

     /**
     * Constructor de la clase AddProductCommand.
     * 
     * @param productService Servicio de productos que se utilizará para agregar el producto.
     * @param product        Producto a ser agregado.
     */
    public AddProductCommand(ProductService productService, Product product) {
        this.productService = productService;
        this.product = product;
    }

      /**
     * Ejecuta el comando para agregar un producto.
     * 
     * @return true si el producto se agregó correctamente, false en caso contrario.
     */
    @Override
    public boolean execute() {
        Long prodId = product.getProductId();
        String name= product.getName();
        String desc= product.getDescription();
        Double price = product.getPrice();
        Long catId=product.getCategoryId();
        Long vendedorId = product.getVendedorId();
        try {
            return productService.saveProduct(prodId, name, desc, price, catId, vendedorId);
        } catch (Exception ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    
    }

    /**
     * Deshace el comando eliminando el producto agregado.
     */
    @Override
    public void undo() {
        try {
            productService.deleteProduct(product.getProductId());
        } catch (Exception ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}



