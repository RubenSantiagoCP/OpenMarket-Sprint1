package co.unicauca.openmaket.client.command;

import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.Product;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase RemoveProductCommand que implementa la interfaz Command. Representa un
 * comando para eliminar un producto.
 */
public class RemoveProductCommand implements Command {

    private final ProductService productService;
    private final Product product;

    public RemoveProductCommand(ProductService productService, Product product) {
        this.productService = productService;
        this.product = product;
    }

    /**
     * Ejecuta el comando para eliminar el producto. Llama al método
     * deleteProduct del ProductService pasando el ID del producto. Retorna true
     * si el producto se eliminó correctamente, false en caso contrario.
     */
    @Override
    public boolean execute() {
        try {
            return productService.deleteProduct(product.getProductId());
        } catch (Exception ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Deshace el comando para agregar el producto nuevamente. Llama al método
     * saveProduct del ProductService pasando los datos del producto.
     */
    @Override
    public void undo() {
        Long prodId = product.getProductId();
        String name = product.getName();
        String desc = product.getDescription();
        Double price = product.getPrice();
        Long catId = product.getCategoryId();
        Long vendedorId = product.getVendedorId();
        try {
            productService.saveProduct(prodId, name, desc, price, catId, vendedorId);
        } catch (Exception ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
