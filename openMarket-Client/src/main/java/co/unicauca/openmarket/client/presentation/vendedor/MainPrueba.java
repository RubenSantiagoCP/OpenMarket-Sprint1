/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.IBuyAccess;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.domain.service.UserService;
import co.unicauca.openmarket.commons.domain.User;

/**
 *
 * @author juank
 */
public class MainPrueba {

    private static CategoryService categoryService;
    private static ProductService productService;
    private static BuyService buyService;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IProductAccess repositoryP = Factory.getInstance().getProductAccess();
        ICategoryAccess repositoryC = Factory.getInstance().getCategoryAccess();
        IBuyAccess repositoryB = Factory.getInstance().getBuyAccess();
        ProductService productService = new ProductService(repositoryP);
        CategoryService categoryService = new CategoryService(repositoryC);
        BuyService buyService = new BuyService(repositoryB);
        User user = new User("rscruz", "1234", "hola", 1, "vendedor");
        
        
        GUIVendedor vtnVendedor = new GUIVendedor(productService, categoryService, user);
        vtnVendedor.setVisible(true);
    }
    
}
