
package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IUserRepository;
import java.util.List;

public class UserService {
    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }
    

   public synchronized User findByLogin(String login)throws Exception{
       return repository.findByLogin(login);
   }
   
   public synchronized List<User>  findAll()throws Exception{
       return repository.findAll();
   }
   public synchronized List<User> findByType(String type) throws Exception{
       return repository.findByType(type);
   }
}
