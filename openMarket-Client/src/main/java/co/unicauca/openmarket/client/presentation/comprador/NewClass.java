/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.comprador;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;

/**
 *
 * @author Hewlett Packard
 */
public class NewClass {
    public static void main(String[] args) {
        IProductAccess repositoryP = Factory.getInstance().getProductAccess();
        ICategoryAccess repositoryC = Factory.getInstance().getCategoryAccess();
        ProductService ProductService = new ProductService(repositoryP);
        CategoryService categoryService = new CategoryService(repositoryC);
        
        
        GUIComprador jp = new GUIComprador(ProductService, categoryService);
        jp.setVisible(true);
    }
}
