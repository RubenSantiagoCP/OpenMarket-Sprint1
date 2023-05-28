/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IBuyAccess;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.observer.Observer;
import co.unicauca.openmarket.commons.observer.Subject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nimbachi
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
    
    public boolean delete(Long idBuy) throws Exception{
        boolean result = repository.delete(idBuy);
        
        if(result){
            notifyObservers();
        }
        
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos de busqueda">
    public Buy findBuyById(Long idBuy) throws Exception{
        return repository.findById(idBuy);
    }
    
    public List<Buy> findBuyByCom(String nombreComprador) throws Exception{
        List<Buy> buys = new ArrayList<>();
        buys = repository.findByComp(nombreComprador);
        return buys;
    }
    
    public List<Buy> findAllBuys() throws Exception{
        List<Buy> buys = new ArrayList<>();
        buys = repository.findAll();
        return buys;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos abtractos Subject">
    @Override
    public void registerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
}
