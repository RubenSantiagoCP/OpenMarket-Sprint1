
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.commons.domain.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private IUserAccess repository;

    public UserService(IUserAccess repository) {
        this.repository = repository;
    }
    
    
    public List<User> findAllUsers() throws Exception{
        List<User> lstUsers = new ArrayList<>();
        lstUsers = repository.findAll();
        return lstUsers;
    }
    
    public List<User> findUsersByType(String type) throws Exception{
        List<User> lstUsers = new ArrayList<>();
        lstUsers = repository.findByType(type);
        return lstUsers;
    }
    
    public User findUserByLogin(String login) throws Exception{
        return repository.findByLogin(login);
    }
    
    
    
}
