package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Buy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nimbachi
 */
public class BuyRepositoryImplArrays implements IBuyRepository{

    private static List<Buy> buys;
    private Connection conn;
    private IBuyRepository buyRepository;

    public BuyRepositoryImplArrays(IBuyRepository buyRepository) {
        this.buyRepository = buyRepository;
        
        if(buys == null){
            buys = new ArrayList<>();
        }
        
        if(buys.isEmpty()){
            
        }
    }
    
    public void inicializar(){
        //Datos quemados de la forma 
        //buys.add(new Buy(idCompra, idComprador, idProducto, estadoCompra, fechaCompra));
    }
    
    
    @Override
    public String createBuy(Buy newBuy) throws Exception {
        buys.add(newBuy);
        return newBuy.getId().toString();
    }

    @Override
    public Boolean edit(Long id, Buy buy) throws Exception {
        Buy buyToEsdit = finById(id);
        
        buyToEsdit.setEstado(buy.getEstado());
        
        return true;
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        Buy buyToDelete = findById(id);
        if(buyToDelete == null){
            return false;
        }
        
        buys.remove(buyToDelete);
        
        return true;
    }

    @Override
    public Buy findById(Long id) throws Exception {
        for(Buy buy : buys){
            if(buy.getId().equals(id)){
                return buy;
            }
        }
        return null;
    }

    @Override
    public Buy finById(Long id) throws Exception {
        for(Buy buy : buys){
            if(buy.getClass().equals(id)){
                return buy;
            }
        }
        return null;
    }

    @Override
    public List<Buy> findByComp(String nombreComprador) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Buy> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
