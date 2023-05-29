
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
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
 *
 * @author SANTIAGO
 */
public class UserAccessImpSockets implements IUserAccess{

    private OpenMarketSocket mySocket;
    
    
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

    private String doFindAllUsersRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("getListUsers");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private List<User> jsonToUserList(String jsonUserList) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(jsonUserList, userListType);
        return userList;
    }    
    
     private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
     
     private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    private String doFindUserRequestJson(String login) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("get");
        protocol.addParameter("login", login);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    private User jsonToUser(String jsonResponse) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonResponse, User.class);
        return user;
    }

    private String doFindByTypeUsersRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("getUserByType");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
}
