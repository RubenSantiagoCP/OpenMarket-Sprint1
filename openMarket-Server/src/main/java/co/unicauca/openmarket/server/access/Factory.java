package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }


    public IProductRepository getRepository() {
        String type = Utilities.loadProperty("product.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        IProductRepository result = null;

        switch (type) {
            case "default":
                result = new ProductRepositoryImplMysql();
                break;
        }
        return result;
    }
    
    public ICategoryRepository getCatRepository() {
        String type = Utilities.loadProperty("category.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        ICategoryRepository result = null;

        switch (type) {
            case "default":
                result = new CategoryRepositoryImplMysql();
                break;
        }
        return result;
        
    }
    
    public IUserRepository getUserRepository() {
        String type = Utilities.loadProperty("user.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        IUserRepository result = null;

        switch (type) {
            case "default":
                result = new UserRepositoryImplMySql();
                break;
        }
        return result;
    }
    
    public IBuyRepository getBuyRepository() {
        String type = Utilities.loadProperty("user.repository");
        if (type.isEmpty()) {
            type = "default";
        }
        IBuyRepository result = null;

        switch (type) {
            case "default":
                result = new BuyRepositoryImplMySql();
                break;
        }
        return result;
    }
}
