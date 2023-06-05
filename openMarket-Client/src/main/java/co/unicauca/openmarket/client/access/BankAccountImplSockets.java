package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.domain.BankAccount;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la interfaz IBankAccountAccess utilizando sockets para la
 * comunicación con el servidor.
 */
public class BankAccountImplSockets implements IBankAccountAccess {

    private OpenMarketSocket mySocket;

    public BankAccountImplSockets() {
        this.mySocket = new OpenMarketSocket();
    }

    /**
     * Busca una cuenta bancaria por el ID de usuario. Envía una solicitud al
     * servidor mediante el socket para buscar la cuenta bancaria
     * correspondiente al ID de usuario. Retorna la cuenta bancaria encontrada
     * si la operación fue exitosa, lanza una excepción en caso contrario.
     */
    @Override
    public BankAccount findByUser(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindByUserIdRequestJson(id.toString());
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException e) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }

        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar cpn els ervidor. Revise la red o que el servidor este escuchando");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvio algun error
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontro una compra
                BankAccount bank = jsonToBankAccount(jsonResponse);
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, "Lo que va en el Json: ({0})", jsonResponse);
                return bank;
            }
        }
    }

    //Extrae los mensajes de la lista de errores 
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msj = "";
        for (JsonError error : errors) {
            msj += error.getMessage();
        }
        return msj;
    }

    //Convierte el jsonError a un array de objetos jsonError
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    //Convierte jsonBuy proveniente del server Socket a un objeto Buy
    private BankAccount jsonToBankAccount(String jsonBankAccout) {
        Gson gson = new Gson();
        BankAccount bank = gson.fromJson(jsonBankAccout, BankAccount.class);
        return bank;
    }

    //Convierte json a una lista de objetos Buy
    /**
     * Edita el saldo de una cuenta bancaria. Envía una solicitud al servidor
     * mediante el socket para editar el saldo de la cuenta bancaria con el ID
     * proporcionado. Retorna true si la operación fue exitosa, lanza una
     * excepción en caso contrario.
     */
    @Override
    public boolean editBalance(Long id, double balance) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEditBankRequestJson(id, balance);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor.");
        }

        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar al servidor.");
        } else {
            if (jsonResponse.contains("error")) {
                Logger.getLogger(BuyAccessImplSocket.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                bandera = true;
            }
        }

        return bandera;
    }

    // Crea el JSON de solicitud para buscar una cuenta bancaria por ID de usuario
    private String doFindByUserIdRequestJson(String id) {
        Protocol protocol = new Protocol();
        protocol.setResource("bankAccount");
        protocol.setAction("get");
        protocol.addParameter("user_id", id);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(protocol);
        return jsonRequest;
    }

    // Crea el JSON de solicitud para editar el saldo de una cuenta bancaria
    private String doEditBankRequestJson(Long id, double balance) {
        Protocol protocol = new Protocol();
        protocol.setResource("bankAccount");
        protocol.setAction("edit");
        protocol.addParameter("bank_saldo", String.valueOf(balance));
        protocol.addParameter("bank_id", String.valueOf(id));
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(protocol);
        return jsonRequest;
    }
}
