
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la interfaz IUserAccess que utiliza sockets para la comunicación con el servidor.
 */

public class UserAccessImpSockets implements IUserAccess{

    private OpenMarketSocket mySocket;

     /**
     * Constructor de la clase UserAccessImpSockets.
     * Inicializa una instancia de OpenMarketSocket.
     */
    public UserAccessImpSockets() {
        this.mySocket = new OpenMarketSocket();
    }
  
    /**
     * Busca un usuario por su login en el servidor.
     *
     * @param login El login del usuario a buscar
     * @return El objeto User correspondiente al login especificado
     * @throws Exception Si ocurre un error durante la búsqueda
     */
    @Override
    public User findByLogin(String login) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindUserRequestJson(login);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el user
                User user = jsonToUser(jsonResponse);
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return user;
            }
        }

    }

     /**
     * Obtiene una lista de todos los usuarios del servidor.
     *
     * @return Lista de usuarios
     * @throws Exception Si ocurre un error al obtener la lista de usuarios
     */
    @Override
    public List<User> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllUsersRequestJson();
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el user
                List<User> users = jsonToUserList(jsonResponse);
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return users;
            }
        }
    }

     /**
     * Busca usuarios por su tipo en el servidor.
     *
     * @param type El tipo de usuario a buscar
     * @return Lista de usuarios del tipo especificado
     * @throws Exception Si ocurre un error durante la búsqueda
     */
    @Override
    public List<User> findByType(String type) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindByTypeUsersRequestJson();
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el user
                List<User> users = jsonToUserList(jsonResponse);
                Logger.getLogger(UserAccessImpSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return users;
            }
        }
    }

    /**
     * Crea una solicitud JSON para obtener la lista de todos los usuarios.
     *
     * @return Cadena JSON de la solicitud
     */
    private String doFindAllUsersRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("getListUsers");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

      /**
     * Convierte una cadena JSON en una lista de objetos User.
     *
     * @param jsonUserList Cadena JSON que representa una lista de usuarios
     * @return Lista de objetos User generada a partir del JSON
     */
    private List<User> jsonToUserList(String jsonUserList) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(jsonUserList, userListType);
        return userList;
    }    
    
    
    /**
     * Extrae los mensajes de error de una respuesta JSON.
     *
     * @param jsonResponse Respuesta JSON que contiene errores
     * @return Cadena que contiene los mensajes de error extraídos del JSON
     */
     private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
     
      /**
     * Convierte una cadena JSON en un arreglo de objetos JsonError.
     *
     * @param jsonError Cadena JSON que representa un arreglo de errores
     * @return Arreglo de objetos JsonError generados a partir del JSON
     */
     private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

      /**
     * Crea una solicitud JSON para obtener un usuario por su login.
     *
     * @param login El login del usuario a buscar
     * @return Cadena JSON de la solicitud
     */
    private String doFindUserRequestJson(String login) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("get");
        protocol.addParameter("login", login);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

     /**
     * Convierte una cadena JSON en un objeto User.
     *
     * @param jsonResponse Cadena JSON que representa un usuario
     * @return Objeto User generado a partir del JSON
     */
    private User jsonToUser(String jsonResponse) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonResponse, User.class);
        return user;
    }

      /**
     * Crea una solicitud JSON para obtener usuarios por su tipo.
     *
     * @return Cadena JSON de la solicitud
     */
    private String doFindByTypeUsersRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("getUserByType");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
}
