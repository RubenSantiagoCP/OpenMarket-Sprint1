
package co.unicauca.openmarket.client.access;


import co.unicauca.openmarket.commons.domain.Product;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductAccess {
    boolean save(Product newProduct) throws Exception;
    
    boolean edit(Long id, Product product) throws Exception;
    
    boolean delete(Long id) throws Exception;

    Product findById(Long id) throws Exception;
    
    List<Product> findByName(String pname) throws Exception;
    List<Product> findByCategory(Long categoryId) throws Exception;
    List<Product> findAll() throws Exception;

    List<Product> finByPrice(Long minPrice, Long maxPrice) throws Exception;
    
}

