
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IBuyAccess;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.observer.Observer;
import co.unicauca.openmarket.commons.observer.Subject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servicio de compras
 */
public class BuyService implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private IBuyAccess repository;

    public BuyService() { }
    
    /**
     * Inyeccion de dependencias en el constructor
     * @param repository 
     */
    public BuyService(IBuyAccess repository) {
        this.repository = repository;
    }
    
      /**
     * Guarda una compra en el repositorio
     * @param idCompra identificador de la compra
     * @param estadoCompra estado de la compra
     * @param fechaCompra fecha de la compra
     * @param idComprador identificador del comprador
     * @param idProducto identificador del producto
     * @return true si la compra se guarda correctamente, false de lo contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean saveBuy(Long idCompra, String estadoCompra, String fechaCompra, Long idComprador, Long idProducto) throws Exception{
        Buy newBuy = new Buy();
        newBuy.setId(idCompra);
        newBuy.setEstado(estadoCompra);
        newBuy.setFechaCompra(fechaCompra);
        newBuy.setCompradorId(idComprador);
        newBuy.setProductoId(idProducto);
        
        boolean result = repository.save(newBuy);
        
        if(result){
            notifyObservers();
        }
        return result;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos de edicion">
      //<editor-fold defaultstate="collapsed" desc="Metodos de edicion">
    /**
     * Edita una compra existente en el repositorio
     * @param idBuy identificador de la compra a editar
     * @param newBuy objeto Buy con los nuevos datos de la compra
     * @return true si la compra se edita correctamente, false de lo contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean editBuy(Long idBuy, Buy newBuy) throws Exception{
        if(newBuy == null || newBuy.getEstado().isBlank()){
            return false;
        }
        
        boolean result = repository.edit(idBuy, newBuy);
        
        if(result){
            notifyObservers();
        }
        return result;
    }
    
    /**
     * Elimina una compra del repositorio
     * @param idBuy identificador de la compra a eliminar
     * @return true si la compra se elimina correctamente, false de lo contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean delete(Long idBuy) throws Exception{
        boolean result = repository.delete(idBuy);
        
        if(result){
            notifyObservers();
        }
        
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos de busqueda">
      /**
     * Busca una compra por su identificador en el repositorio
     * @param idBuy identificador de la compra
     * @return objeto Buy correspondiente al identificador, null si no se encuentra
     * @throws Exception si ocurre algún error en la operación
     */
    public Buy findBuyById(Long idBuy) throws Exception{
        return repository.findById(idBuy);
    }
    
     /**
     * Busca todas las compras realizadas por un comprador en el repositorio
     * @param idComp identificador del comprador
     * @return lista de compras realizadas por el comprador
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Buy> findBuyByCom(Long idComp) throws Exception{
        List<Buy> buys = new ArrayList<>();
        buys = repository.findByComp(idComp);
        return buys;
    }
    
    /**
     * Obtiene todas las compras almacenadas en el repositorio
     * @return lista de todas las compras
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Buy> findAllBuys() throws Exception{
        List<Buy> buys = new ArrayList<>();
        buys = repository.findAll();
        return buys;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos abtractos Subject">
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update();
        }
    }
    //</editor-fold>
}
