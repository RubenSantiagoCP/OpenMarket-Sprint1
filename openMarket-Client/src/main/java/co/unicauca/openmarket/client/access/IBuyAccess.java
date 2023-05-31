/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.Buy;
import java.util.List;

/**
 *
 * @author nimbachi
 */
public interface IBuyAccess {
    boolean save(Buy newBuy) throws Exception;
    
    boolean edit(Long id, Buy buy) throws Exception;
    
    boolean delete(Long id) throws Exception;
    
    Buy findById(Long id) throws Exception;
    
    List<Buy> findByComp(Long idComp) throws Exception;
    
    List<Buy> findAll() throws Exception;
            
}
