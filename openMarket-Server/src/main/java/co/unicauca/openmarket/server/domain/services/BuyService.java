/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.server.access.IBuyRepository;
import java.util.List;
import javax.sql.rowset.spi.SyncResolver;


public class BuyService {
    IBuyRepository repository;

    public BuyService(IBuyRepository repository) {
        this.repository = repository;
    }
    
    public synchronized String save(Buy newBuy) throws Exception {
        return repository.createBuy(newBuy);
    }
    
    public synchronized boolean edit(Long id, Buy buy) throws Exception{
        return repository.edit(id, buy);
    }
    
    public synchronized  boolean delete(Long id) throws Exception{
        return repository.delete(id);
    }
    
    public synchronized Buy findById(Long id) throws Exception{
        return repository.findById(id);
    }
    
    public synchronized List<Buy> findByComp(Long idComp) throws Exception{
        return repository.findByComp(idComp);
    }
    
    public synchronized  List<Buy> findAll() throws Exception{
        return repository.findAll();
    }
            
    
    
    
}
