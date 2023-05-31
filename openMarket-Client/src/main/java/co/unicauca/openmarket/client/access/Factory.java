package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar ProductAccessImplSockets o cualquier otro que
 se cree en el futuro.
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

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    
    public IProductAccess getProductAccess() {

        IProductAccess result = null;
        String type = Utilities.loadProperty("product.service");

        switch (type) {
            case "default":
                result = new ProductAccessImplSockets();
                break;
        }
        return result;
    }
    
    public ICategoryAccess getCategoryAccess() {

        ICategoryAccess result = null;
        String type = Utilities.loadProperty("category.service");

        switch (type) {
            case "default":
                result = new CategoryAccessImplSockets();
                break;
        }
        return result;
    }
    

    public IBuyAccess getBuyAccess(){
        IBuyAccess result = null;
        String type = Utilities.loadProperty("buy.service");
        
        switch(type){
            case "default":
                result = new BuyAccessImplSocket();
                break;
        }
        
              return result;
    }
        
      public IUserAccess getUserAccess() {

        IUserAccess result = null;
        String type = Utilities.loadProperty("user.service");

        switch (type) {
            case "default":
                result = new UserAccessImpSockets();
                break;
        }
        return result;
    }
      
     public IBankAccountAccess getBankAccountAccess() {

        IBankAccountAccess result = null;
        String type = Utilities.loadProperty("bank.service");

        switch (type) {
            case "default":
                result = new BankAccountImplSockets();
                break;
        }
        return result;
    }

}
