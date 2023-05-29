package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.presentation.GUICategory;
import co.unicauca.openmarket.client.presentation.GUIProducts;

/**
 *
 * @author juank
 */
public class MainPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       IProductAccess repository = Factory.getInstance().getProductAccess();
        ICategoryAccess repository2 =  Factory.getInstance().getCategoryAccess();
        ProductService productService = new ProductService(repository);
        CategoryService categoryService=new CategoryService(repository2);
        
        
        
        GUIVendedor instance2 = new GUIVendedor(productService,categoryService);
        instance2.setVisible(true);
        instance2.setResizable(false);
    }
    
}
