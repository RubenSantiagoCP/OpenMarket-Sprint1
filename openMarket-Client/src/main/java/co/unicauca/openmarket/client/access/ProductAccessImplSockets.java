package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author Libardo, Julio
 */
public class ProductAccessImplSockets implements IProductAccess {

    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos implementados de IProductAccess">
    
    /**
     * Guarda un Product.Utiliza socket para pedir el servicio al servidor
     *
     * @param newProduct
     * @param categoryId
     * @param product producto de la tienda
     * @return devuelve verdadero
     * @throws Exception error crear el producto
     */
    @Override
    public boolean save (Product newProduct)throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doCreateProductRequestJson(newProduct);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve verdadero 
                bandera = true;
            }

        }
        return bandera;
    }

    /**
     * Edita un producto Utiliza socket para pedir el servicio al servidor
     *
     * @param id del producto y el producto
     * @return retorna true
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public boolean edit(Long id, Product product) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEditProductRequestJson(id, product);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve verdadero 
                bandera = true;
            }

        }
        return bandera;
    }

    /**
     * Elimina un producto por id. Utiliza socket para pedir el servicio al
     * servidor
     *
     * @param id del producto
     * @return retorna un verdadero
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public boolean delete(Long productId) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(productId);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //eliminó correctamente, devuelve verdadero 
                bandera = true;
            }

        }
        return bandera;
    }

    /**
     * Busca un producto por id. Utiliza socket para pedir el servicio al
     * servidor
     *
     * @param id del producto
     * @return Objeto producto
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public Product findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductRequestJson(id.toString());
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
                //Encontró el product
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return product;
            }
        }

    }

    /**
     * Busca productos por el nombre. Utiliza socket para pedir el servicio al
     * servidor
     *
     * @param nombre del producto
     * @return una lista de producto
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public List<Product> findByName(String pname) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductByNameRequestJson(pname);
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
                //Encontró el product
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
    }
    
    @Override
    public List<Product> finByPrice(Long minPrice, Long maxPrice) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductByPriceRequestJson(minPrice.toString(), maxPrice.toString());
        System.err.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        }else{
            if(jsonResponse.contains("error")){
                //Devolvio algun error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                //Encontro el product
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
    }

    /**
     * Busca productos por el nombre de la categoria. Utiliza socket para pedir
     * el servicio al servidor
     *
     * @param nombre de la categoria
     * @return una lista de producto
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public List<Product> findByCategory(Long categoryId) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductByCategoryRequestJson(categoryId);
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
                //Encontró el product
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
    }

    /**
     * Busca todos los productos. Utiliza socket para pedir el servicio al
     * servidor
     *
     * @return una lista de producto
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public List<Product> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllProductsRequestJson();
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
                //Encontró el product
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
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
    
    //</editor-fold>
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Creacion de peticiones en Json (do) ">

    /**
     * Crea la solicitud json de creación del product para ser enviado por el
     * socket
     *
     * @param product objeto Product
     * @return devulve algo como:
     * {"resource":"product","action":"post","parameters":[{"name":"productId","value":"1"},{"name":"name","value":"Leche"},...}]}
     */
    private String doCreateProductRequestJson(Product product) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        protocol.addParameter("productId", product.getProductId().toString());
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        protocol.addParameter("prod_price", String.valueOf(product.getPrice()));
        protocol.addParameter("cat_id", product.getCategoryId().toString());
        protocol.addParameter("user_id", product.getVendedorId().toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea la solicitud json de editar un product para ser enviado por el
     * socket
     *
     * @param prodId id del producto, product objeto Producto
     * @return devulve algo como:
     * {"resource":"product","action":"post","parameters":[{"name":"productId","value":"1"},{"name":"name","value":"Leche"},...}]}
     */
    private String doEditProductRequestJson(Long prodId, Product product) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("edit");
        protocol.addParameter("productId", product.getProductId().toString());
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        protocol.addParameter("prod_price", String.valueOf(product.getPrice()));
        protocol.addParameter("cat_id", product.getCategoryId().toString());
        protocol.addParameter("user_id", product.getVendedorId().toString());
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea la solicitud json de eliminacion del product para ser enviado por el
     * socket
     *
     * @param id del producto a eliminar
     * @return devulve algo como:
     * {"resource":"product","action":"deleteProduct","parameters":[{"name":"productId","value":"1"}]}
     */
    private String doDeleteProductRequestJson(Long productId) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("deleteProduct");
        protocol.addParameter("productId", productId.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea una solicitud json de busqueda por id del producto para ser enviada
     * por el socket
     *
     *
     * @param productId identificación del producto
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"productId","value":"1"}]}
     */
    private String doFindProductRequestJson(String productId) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("productId", productId);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }


    /**
     * Convierte jsonProduct, proveniente del server socket, de json a un
     * objeto Product
     *
     * @param pname nombre del producto
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"pname","value":"salsa"}]}
     */
    private String doFindProductByNameRequestJson(String pname) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("getProductsByName");
        protocol.addParameter("productName", pname);
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
     * Crea una peticion de busuqeda por rango de precio para ser enviada por el socket
     * @param minPrice Rango minimo seleccionado por el comprador 
     * @param maxPrice Rango maximo seleccionado por el comprador 
     * @return solicitud de consulta del producto en formato Json
     */
    private String doFindProductByPriceRequestJson(String minPrice, String maxPrice){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("getProductByPrice");
        protocol.addParameter("minPriceProduct", minPrice);
        protocol.addParameter("maxPriceProduct", maxPrice);
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea una solicitud json de busqueda de productos por nombre de categoria
     * para ser enviada por el socket
     *
     *
     * @param categoryName nombre de la categoria
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"pname","value":"salsa"}]}
     */
    private String doFindProductByCategoryRequestJson(Long categoryId) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("getProductsByCategory");
        protocol.addParameter("categoryId", categoryId.toString());
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea una solicitud json de busqueda de todos los productos para ser
     * enviada por el socket
     *
     *
     * @param pname nombre del producto
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"pname","value":"salsa"}]}
     */
    private String doFindAllProductsRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("getListProducts");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    //</editor-fold>
    
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
     * Convierte jsonProduct, proveniente del server socket, de json a un objeto
     * Product
     *
     * @param jsonProduct objeto producto en formato json
     */
    private Product jsonToProduct(String jsonProduct) {

        Gson gson = new Gson();
        Product product = gson.fromJson(jsonProduct, Product.class);
        return product;

    }

    /**
 * Convierte una cadena JSON en una lista de objetos Product.
 *
 * @param jsonProductList Cadena JSON que representa una lista de objetos Product
 * @return Lista de objetos Product generada a partir del JSON
 */
    private List<Product> jsonToProductList(String jsonProductList) {
        Gson gson = new Gson();
        Type productListType = new TypeToken<List<Product>>() {
        }.getType();
        List<Product> productList = gson.fromJson(jsonProductList, productListType);
        return productList;
    }

    

}
