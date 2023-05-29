
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;
import java.util.List;


public interface IUserRepository {
   User findByLogin(String login)throws Exception;
   List<User> findAll()throws Exception;
   public List<User> findByType(String type) throws Exception;
}
