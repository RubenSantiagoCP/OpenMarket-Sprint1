
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.User;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */
public interface IUserAccess {
   User findByLogin(String login)throws Exception;
   List<User> findAll()throws Exception;
   public List<User> findByType(String type) throws Exception;
}
