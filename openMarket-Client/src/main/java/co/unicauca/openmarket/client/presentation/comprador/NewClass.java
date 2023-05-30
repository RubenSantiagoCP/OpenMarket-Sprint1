/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.comprador;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.IBuyAccess;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.User;

/**
 *
 * @author Hewlett Packard
 */
public class NewClass {
    public static void main(String[] args) {
        IProductAccess repositoryP = Factory.getInstance().getProductAccess();
        ICategoryAccess repositoryC = Factory.getInstance().getCategoryAccess();
        IBuyAccess repositoryB = Factory.getInstance().getBuyAccess();
        ProductService ProductService = new ProductService(repositoryP);
        CategoryService categoryService = new CategoryService(repositoryC);
        BuyService buyService = new BuyService(repositoryB);
        User user = new User("rscruz", "1234", "hola", 0, "comprador");
        
        GUIComprador jp = new GUIComprador(ProductService, categoryService, buyService, user);
        jp.setVisible(true);
    }
}
