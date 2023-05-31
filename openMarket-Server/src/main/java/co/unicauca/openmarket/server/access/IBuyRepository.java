package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Buy;
import java.util.List;

/**
 *
 * @author nimbachi
 */
public interface IBuyRepository {
    //Metodos de edicion
    String createBuy(Buy newBuy)throws Exception;
    
    Boolean edit(Long id, Buy buy)throws Exception;
    
    Boolean delete(Long id)throws Exception;
    
    Buy findById(Long id)throws Exception;
    
    //Metodos de busqueda
    
    List<Buy> findByComp(Long idComp)throws Exception;
    
    List<Buy> findAll() throws Exception;
}
