/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.Category;
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
 * @author brayan
 */
public class CategoryAccessImplSockets implements ICategoryAccess {

    private OpenMarketSocket mySocket;

    public CategoryAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    /**
     * Crea una Categoria. Utiliza socket para pedir el servicio al servidor
     *
     * @param newCategory id de la categoria
     * @return boolean bandera
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public boolean save(Category newCategory) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doCreateCategoryRequestJson(newCategory);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve true
                //Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+newCategory.getCategoryId().toString()+ ")");
                bandera = true;
            }
        }

        return bandera;
    }

    /**
     * Edita una Categoria. Utiliza socket para pedir el servicio al servidor
     *
     * @param id de la categoria a editar, category categoria
     * @return boolean bandera
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public boolean edit(Long id, Category category) throws Exception{
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEditCategoryRequestJson(id, category);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
 
                //Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+newCategory.getCategoryId().toString()+ ")");
                bandera = true;
            }
        }

        return bandera;
    }

    /**
     * Edita una Categoria. Utiliza socket para pedir el servicio al servidor
     *
     * @param id de la categoria a editar, category categoria
     * @return boolean bandera
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public boolean delete(Long id) throws Exception{
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doDeleteCategoryRequestJson(id);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve true
                //Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+newCategory.getCategoryId().toString()+ ")");
                bandera = true;
            }
        }

        return bandera;
    }

    /**
     * Busca una Categoria. Utiliza socket para pedir el servicio al servidor
     *
     * @param id id de la categoria
     * @return Objeto Category
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public Category findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindCategoyIdRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            //return null;
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                // throw new Exception(extractMessages(jsonResponse));
                return null;
            } else {
                //Encontró el category
                Category category = jsonToCategory(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return category;
            }
        }
    }

    /**
     * Busca todas las Categorias. Utiliza socket para pedir el servicio al servidor
     *
     * @return lista de Categorias
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public List<Category> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllCategoriesRequestJson(jsonResponse);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                List<Category> categories = jsonToCategoryList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return categories;
            }
        }
    }

    /**
     * Busca una Categoria por nombre. Utiliza socket para pedir el servicio al servidor
     *
     * @param cname nombre de la categoria
     * @return lista de Categorias
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public List<Category> findByName(String cname) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindCategoriesByNameRequestJson(cname);
        System.out.println(cname);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                List<Category> categories = jsonToCategoryList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return categories;
            }
        }
    }

    //Metodos adicionales
    /**
     * Extra los mensajes de la lista de errores
     *
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
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
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    /**
     * Crea la solicitud json de creación de la Category para ser enviado por el
     * socket
     *
     * @param category objeto categoria
     * @return devulve algo como:
     * {"resource":"category","action":"post","parameters":[{"name":"cat_id","value":"1"},{"name":"name","value":"lacteos"},...}]}
     */
    private String doCreateCategoryRequestJson(Category category) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("post");
        protocol.addParameter("cat_id", String.valueOf(category.getCategoryId()));
        protocol.addParameter("cat_name", category.getName());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
     * Crea una solicitud json de editar categoria para ser enviada por el socket
     *
     *
     * @param id de la categoria, cat_id identificación de la categoria
     * @return solicitud de consulta de la categoria en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"cat_id","value":"1"},...]}
     */
    private String doEditCategoryRequestJson(Long id, Category category) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("edit");
        protocol.addParameter("cat_id", category.getCategoryId().toString());
        protocol.addParameter("cat_name", category.getName());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
     * Crea una solicitud json de eliminar categoria para ser enviada por el socket
     *
     *
     * @param cat_id identificación de la categoria
     * @return solicitud de consulta de la categoria en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"cat_id","value":"1"},...]}
     */
    private String doDeleteCategoryRequestJson(Long cat_id) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("deleteCategory");
        protocol.addParameter("cat_id", cat_id.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea una solicitud json de buscar una categoria por id para ser enviada por el socket
     *
     *
     * @param cat_id identificación de la categoria
     * @return solicitud de consulta de la categoria en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"cat_id","value":"1"}]}
     */
    private String doFindCategoyIdRequestJson(Long cat_id) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("get");
        protocol.addParameter("cat_id", cat_id.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    /**
     * Crea una solicitud json de buscar todas las categorias para ser enviada por el socket
     *
     *
     * @param jsonProductList lista de categorias
     * @return solicitud de consulta de la categoria en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"cat_id","value":"1"}]}
     */
    private String doFindAllCategoriesRequestJson(String jsonProductList) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("getListCategory");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
     * Crea una solicitud json de buscar todas las categorias para ser enviada por el socket
     *
     *
     * @param cname nombre de la categoria
     * @return solicitud de consulta de la categoria en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"cat_id","value":"1"}]}
     */
    private String doFindCategoriesByNameRequestJson(String cname) {
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("getListCategoryByName");
        protocol.addParameter("categoryName", cname);
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }


    /**
     * Convierte jsonCategory, proveniente del server socket, de json a un
     * objeto Category
     *
     * @param jsonCustomer objeto cliente en formato json
     */
    private Category jsonToCategory(String jsonCategory) {

        Gson gson = new Gson();
        Category category = gson.fromJson(jsonCategory, Category.class);
        return category;

    }

    private List<Category> jsonToCategoryList(String jsonProductList) {
        Gson gson = new Gson();
        Type categoryListType = new TypeToken<List<Category>>() {
        }.getType();
        List<Category> categoryList = gson.fromJson(jsonProductList, categoryListType);
        return categoryList;
    }
}
