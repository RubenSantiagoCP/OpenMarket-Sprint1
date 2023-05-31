package co.unicauca.openmarket.client.main;


import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.presentation.inicioSesion.GUILogin;
import co.unicauca.openmarket.client.presentation.inicioSesion.GUILogin;
import co.unicauca.openmarket.commons.observer.Observer;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       GUILogin vtnLogin = new GUILogin();
       vtnLogin.setVisible(true);
    }
    
}
