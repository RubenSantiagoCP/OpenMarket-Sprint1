package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.commons.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de usuarios
 */
public class UserService {

    private IUserAccess repository;

    /**
     * Constructor de UserService
     *
     * @param repository objeto que implementa la interfaz IUserAccess para
     * acceder a los usuarios
     */
    public UserService(IUserAccess repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los usuarios almacenados en el repositorio
     *
     * @return lista de todos los usuarios
     * @throws Exception si ocurre algún error en la operación
     */
    public List<User> findAllUsers() throws Exception {
        List<User> lstUsers = new ArrayList<>();
        lstUsers = repository.findAll();
        return lstUsers;
    }

    /**
     * Busca usuarios por tipo en el repositorio
     *
     * @param type tipo de usuario a buscar
     * @return lista de usuarios que coinciden con el tipo especificado
     * @throws Exception si ocurre algún error en la operación
     */
    public List<User> findUsersByType(String type) throws Exception {
        List<User> lstUsers = new ArrayList<>();
        lstUsers = repository.findByType(type);
        return lstUsers;
    }

    /**
     * Busca un usuario por su nombre de login en el repositorio
     *
     * @param login nombre de login del usuario a buscar
     * @return objeto User correspondiente al nombre de login, null si no se
     * encuentra
     * @throws Exception si ocurre algún error en la operación
     */
    public User findUserByLogin(String login) throws Exception {
        return repository.findByLogin(login);
    }

}
