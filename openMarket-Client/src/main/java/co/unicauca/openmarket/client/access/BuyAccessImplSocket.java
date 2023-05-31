/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.domain.Buy;
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
 * @author nimbachi
 */
public class BuyAccessImplSocket implements IBuyAccess{

    private OpenMarketSocket mySocket;

    public BuyAccessImplSocket() {
        this.mySocket = new OpenMarketSocket();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos abstractos">
    @Override
    public boolean save(Buy newBuy) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doCreateBuyRequestJson(newBuy);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor", ex);
        }
        
        if(jsonResponse == null){
            throw new Exception("No se puede conectar con el servidor");
        }else{
            if(jsonResponse.contains("error")){
                //Devolvio algun error
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                bandera = true;
            }
        }
        
        return bandera;
    }

    @Override
    public boolean edit(Long id, Buy buy) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEditBuyRequestJson(id, buy);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar al servidor.");
        }else{
            if(jsonResponse.contains("error")){
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                bandera = true;
            }
        }
        
        return bandera;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doDeleteBuyRequestJson(id);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException e) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar al servidor");
        }else{
            if(jsonResponse.contains("error")){
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                bandera = true;
            }
        }
        
        return bandera;
    }

    @Override
    public Buy findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindBuyIdRequestJson(id.toString());
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException e) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar cpn els ervidor. Revise la red o que el servidor este escuchando");
        }else{
            if(jsonResponse.contains("error")){
                //Devolvio algun error
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                //Encontro una compra
                Buy buy = jsonToBuy(jsonResponse);
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, "Lo que va en el Json: ({0})", jsonResponse);
                return buy;
            }
        }
    }

    @Override
    public List<Buy> findByComp(Long idComp) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindBuyCompJson(idComp);
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException e) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar cpn els ervidor. Revise la red o que el servidor este escuchando");
        }else{
            if(jsonResponse.contains("error")){
                //Devolvio algun error
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                List<Buy> buys = jsonToBuyList(jsonResponse);
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, "Lo que va en el Json: ({0})", jsonResponse);
                return buys;
            }
        }
    }

    @Override
    public List<Buy> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doFindAllBuysRequestJson();
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException e) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar cpn els ervidor. Revise la red o que el servidor este escuchando");
        }else{
            if(jsonResponse.contains("error")){
                //Devolvio algun error
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                //Encontro compras 
                List<Buy> buys = jsonToBuyList(jsonResponse);
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, "Lo que va en el Json: ({0})", jsonResponse);
                return buys;
            }
        }
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Metodos adicionales">
    //Extrae los mensajes de la lista de errores 
    private String extractMessages(String jsonResponse){
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msj = "";
        for(JsonError error : errors){
            msj += error.getMessage();
        }
        return msj;
    }
    
    //Convierte el jsonError a un array de objetos jsonError
    private JsonError[] jsonToErrors(String jsonError){
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    //Convierte jsonBuy proveniente del server Socket a un objeto Buy
    private Buy jsonToBuy(String jsonBuy){
        Gson gson = new Gson();
        Buy buy = gson.fromJson(jsonBuy, Buy.class);
        return buy;
    }
    
    //Convierte json a una lista de objetos Buy
    private List<Buy> jsonToBuyList(String jsonBuyList){
        Gson gson = new Gson();
        Type buyListType = new TypeToken<List<Buy>>(){}.getType();
        
        List<Buy> buyList = gson.fromJson(jsonBuyList, buyListType);
        return buyList;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Creacion de peticiones Json">
    private String doCreateBuyRequestJson(Buy buy){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("post");
        
        protocol.addParameter("comprId", buy.getId().toString());
        protocol.addParameter("compId", buy.getCompradorId().toString());
        protocol.addParameter("prodId", buy.getProductoId().toString());
        protocol.addParameter("comprEstado", buy.getEstado());
        protocol.addParameter("compFecha", buy.getFechaCompra());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doEditBuyRequestJson(Long buyId, Buy buy){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("edit");
        
        protocol.addParameter("comprId", buy.getId().toString());
        protocol.addParameter("compId", buy.getCompradorId().toString());
        protocol.addParameter("prodId", buy.getProductoId().toString());
        protocol.addParameter("comprEstado", buy.getEstado());
        protocol.addParameter("compFecha", buy.getFechaCompra().toString());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doDeleteBuyRequestJson(Long buyId){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("deleteBuy");
        protocol.addParameter("comprId", buyId.toString());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doFindBuyIdRequestJson(String buyId){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("get");
        protocol.addParameter("comprId", buyId);
        
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(protocol);
        return jsonRequest;
    }
    
    private String doFindBuyCompJson(Long  idComp){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("getBuyByComprador");
        protocol.addParameter("compName", idComp.toString());
        
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(protocol);
        return jsonRequest;
    }
    
    private String doFindAllBuysRequestJson(){
        Protocol protocol = new Protocol();
        protocol.setResource("buy");
        protocol.setAction("getListBuys");
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    //</editor-fold>
}
