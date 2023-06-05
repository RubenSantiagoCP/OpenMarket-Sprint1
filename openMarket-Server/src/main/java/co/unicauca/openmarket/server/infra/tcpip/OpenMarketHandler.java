/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;

import co.unicauca.openmarket.commons.domain.BankAccount;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.infra.*;
import co.unicauca.openmarket.server.domain.services.CategoryService;
import co.unicauca.openmarket.server.domain.services.ProductService;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.domain.services.BankAccountService;
import co.unicauca.openmarket.server.domain.services.BuyService;
import co.unicauca.openmarket.server.domain.services.UserService;
import co.unicauca.openmarket.server.infra.Context;
import co.unicauca.openmarket.server.infra.ErrorResponse;
import co.unicauca.strategyserver.infra.ServerHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author brayan
 */
public class OpenMarketHandler extends ServerHandler {
    private static ProductService productService;
    private static CategoryService categoryService;
    private static BuyService buyService;
    private static UserService userService;
    private static BankAccountService bankService;


    public OpenMarketHandler() {

    }

    @Override
    public String processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();

        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response = "";
        switch (protocolRequest.getResource()) {
            case "product" -> {
                if (protocolRequest.getAction().equals("get")) {
                    response = processGetProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("deleteProduct")) {
                    response = String.valueOf(processDeleteProduct(protocolRequest));
                }
                if (protocolRequest.getAction().equals("getProductsByName")) {
                    response = processGetProductsByName(protocolRequest);
                }
                if (protocolRequest.getAction().equals("getProductsByCategory")) {
                    response = processGetProductsByCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    response = processPostProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("getListProducts")) {
                    response = processGetListProductos();
                }
                if (protocolRequest.getAction().equals("edit")) {
                    // Editar un producto
                    response = processEditProduct(protocolRequest);
                }
                if(protocolRequest.getAction().equals("getProductByPrice")){
                    response = processGetProductsByPrice(protocolRequest);
                }
            }
            case "category" -> {
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar una categoria
                    response = processGetCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    // Agregar una categoria
                    response = processPostCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("edit")) {
                    // Editar categoria
                    response = processEditCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("delete")) {
                    //Eliminar categoria
                    response = processDeleteCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listCategory")) {
                    //Lista de las categoria
                    response = processListCategory();
                }
                if (protocolRequest.getAction().equals("getListCategoryByName")) {
                    //Listar categorias por nombre
                    response = processGetListCategoryByName(protocolRequest);
                }
                if (protocolRequest.getAction().equals("getListCategory")) {
                    //Listar categorias por nombre
                    response = processGetListCategory();
                }
            }
             case "buy" -> {
                 if (protocolRequest.getAction().equals("post")) {
                     try {
                         response = processSaveBuy(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("edit")) {
                     try {
                         response = String.valueOf( processEditBuy(protocolRequest));
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("deleteBuy")) {
                     try {
                         response = processDeleteBuy(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("get")) {
                     try {
                         response = processFindByIdBuy(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("getBuyByComprador")) {
                     try {
                         response = processFindBuyComp(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("getListBuys")) {
                     try {
                         response = processFindAllBuysBuy(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
             case "user" -> {
                 if (protocolRequest.getAction().equals("get")) {
                     try {
                         response = processFindUserLogin(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("getUserByType")) {
                     try {
                         response = processFindUserType(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("getListUsers")) {
                     try {
                         response = processFindAllUsers();
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
             
             case "bankAccount" ->{
                 if (protocolRequest.getAction().equals("get")) {
                     try {
                         response = processFindBankAccount(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
                 if (protocolRequest.getAction().equals("edit")) {
                     try {
                         response = processEditBankType(protocolRequest);
                     } catch (Exception ex) {
                         Logger.getLogger(OpenMarketHandler.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
             
        }


        return response;

    }

    private String processGetProductsByPrice(Protocol protocolRequest) {
        Double minPrice = Double.valueOf(protocolRequest.getParameters().get(0).getValue());
        Double maxPrice = Double.valueOf(protocolRequest.getParameters().get(1).getValue());
        List<Product> products = getProductService().findByPrice(minPrice, maxPrice);
        return objectToJSON(products);
    }
    
    private String processGetProductsByCategory(Protocol protocolRequest) {
        Long categoryId = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        List<Product> products=getProductService().findByCategory(categoryId);
        return objectToJSON(products);
    }
    
    private String processGetProductsByName(Protocol protocolRequest) {
        String productName = protocolRequest.getParameters().get(0).getValue();
        List<Product> products=getProductService().findByName(productName);
        return objectToJSON(products);
    }

    private String processGetListCategory() {
        List<Category> categories;
        categories = getCategoryService().findAll();
        return objectToJSON(categories);
    }

    private String processDeleteProduct(Protocol protocolRequest) {
        String productId = protocolRequest.getParameters().get(0).getValue();
        System.out.println("borrado");
        Product product = getProductService().findById(Long.valueOf(productId));
        if (product == null) {
            return generateNotFoundErrorJson(Context.PRODUCT);
        }
        getProductService().delete(Long.valueOf(productId));

        return objectToJSON(true);

    }

    private String processGetListProductos() {
        List<Product> products;
        products = getProductService().findAll();
        return objectToJSON(products);
    }

    private String processEditProduct(Protocol protocolRequest) {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        Product product = getProductService().findById(id);
        if (product == null) {
            return generateNotFoundErrorJson(Context.PRODUCT);
        }
        Long catId= Long.valueOf(protocolRequest.getParameters().get(4).getValue());
        Category category = getCategoryService().findById(catId);

        if(category==null){

            generateNotFoundErrorJson(Context.CATEGORY);
        }
        product = new Product();
        product.setProductId(Long.valueOf(protocolRequest.getParameters().get(0).getValue()));
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
        product.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        product.setCategoryId(Long.valueOf(protocolRequest.getParameters().get(4).getValue()));


        boolean response = getProductService().edit(Long.valueOf(protocolRequest.getParameters().get(0).getValue()),product);
        return String.valueOf(response);
    }

    /**
     * Procesa la solicitud de consultar un customer
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetProduct(Protocol protocolRequest) {
        // Extraer el del primer parámetro
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());

        Product product = getProductService().findById(id);
        if (product == null) {
            return generateNotFoundErrorJson(Context.PRODUCT);
        } else {
            return objectToJSON(product);
        }
    }


    private String processListCategory() {
        // Lista de todas las categorias
        List<Category> category;
        category = getCategoryService().findAll();
        return objectToJSON(category);
    }

    private String processPostProduct(Protocol protocolRequest) {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        Long catId = Long.valueOf(protocolRequest.getParameters().get(4).getValue());
        Long vendedorId = Long.valueOf(protocolRequest.getParameters().get(5).getValue());
        
       Product product = getProductService().findById(id);

        if(!(product == null)){
            return generateBadRequestJson(Context.PRODUCT);
        }

        Category category= getCategoryService().findById(catId);
        if(category==null){
            List<JsonError> errors = new ArrayList<>();
            JsonError error = new JsonError();
            error.setCode("400");
            error.setError("BAD_REQUEST");
            error.setMessage("Error, no existe una categoria con ese id");
            errors.add(error);
            Gson gson = new Gson();
            return gson.toJson(errors);
        }
        product = new Product();
        
        // Reconstruir el producto a partir de lo que viene en los parámetros
        product.setProductId(Long.valueOf(protocolRequest.getParameters().get(0).getValue()));
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
        product.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        product.setCategoryId(Long.valueOf(protocolRequest.getParameters().get(4).getValue()));
        product.setVendedorId(Long.valueOf(protocolRequest.getParameters().get(5).getValue()));

        getProductService().createProduct(product);

        return objectToJSON(product);
    }

    private String processGetListCategoryByName(Protocol protocolRequest) {
        String name = protocolRequest.getParameters().get(0).getValue();
        List<Category> categories;
        categories = getCategoryService().findByName(name);
        return objectToJSON(categories);
    }
    private String processGetCategory(Protocol protocolRequest) {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        Category category = getCategoryService().findById(id);
        if (category == null) {
            return generateNotFoundErrorJson(Context.CATEGORY);
        } else {
            return objectToJSON(category);
        }
    }
    private String processDeleteCategory(Protocol protocolRequest) {
        // Eliminar una categoria
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        boolean response = getCategoryService().delete(id);
        return String.valueOf(response);
    }

    private String processEditCategory(Protocol protocolRequest) {
        // Editar el name de la categoria
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        Category category = getCategoryService().findById(id);
        if (category == null) {
            return generateNotFoundErrorJson(Context.CATEGORY);
        }

        category = new Category();
        category.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        category.setName(protocolRequest.getParameters().get(1).getValue());
        boolean response = getCategoryService().edit(Long.parseLong(protocolRequest.getParameters().get(0).getValue()),category);
        return String.valueOf(response);
    }

    private String processPostCategory(Protocol protocolRequest) {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());

        Category category= getCategoryService().findById(id);

        if(!(category == null)){
            return generateBadRequestJson(Context.CATEGORY);
        }

         category = new Category();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        category.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        category.setName(protocolRequest.getParameters().get(1).getValue());
        boolean response = getCategoryService().save(category);
        return String.valueOf(response);
    }

    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */




    private String generateNotFoundErrorJson(Context context) {
        String message;
            switch (context) {
            case PRODUCT -> message="Error, un producto con ese id no existe";
            case CATEGORY ->message= "Error, una categoria con ese id no existe";
            case BANK ->message= "Error, una cuenta con ese id no existe";
            // Añade más casos aquí en el futuro
            default -> throw new IllegalArgumentException("Contexto desconocido: " + context);
        }

        return new ErrorResponse("404", "NOT_FOUND", message).toJson();
    }


    private String generateBadRequestJson(Context context) {
        String message;
        switch (context) {
            case PRODUCT -> message = "Error, un producto con ese id ya existe";
            case CATEGORY -> message = "Error, una categoria con ese id ya existe";
            case BUY -> message = "Error, una compra con ese id ya existe";

            default -> throw new IllegalArgumentException("Contexto desconocido: " + context);
        }


        return new ErrorResponse("400", "BAD_REQUEST", message).toJson();
    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }
    
    public BuyService getBuyService() {
        return buyService;
    }

    /**
     * @param service the service to set
     */
    public void setProductService(ProductService service) {
        productService = service;
    }

    public void setCategoryService(CategoryService service) {
        categoryService = service;
    }
    
    public UserService getUserService(){
       return userService;
    }

    public  BankAccountService getBankService() {
        return bankService;
    }

    public  void setBankService(BankAccountService bankService) {
        OpenMarketHandler.bankService = bankService;
    }
    

    public void setBuyService(BuyService buyService) {
        this.buyService = buyService;
    }

    public  void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    

    private String processSaveBuy(Protocol protocolRequest) throws Exception {
          Buy buy = new Buy();
        // Reconstruir el producto a partir de lo que viene en los parámetros
        buy.setId(Long.valueOf(protocolRequest.getParameters().get(0).getValue()));
        buy.setCompradorId(Long.valueOf(protocolRequest.getParameters().get(1).getValue()));
        buy.setProductoId(Long.valueOf(protocolRequest.getParameters().get(2).getValue()));
        buy.setEstado(protocolRequest.getParameters().get(3).getValue());
        buy.setFechaCompra(protocolRequest.getParameters().get(4).getValue());


        getBuyService().save(buy);

        return objectToJSON(buy);
    }

    private String processEditBuy(Protocol protocolRequest) throws Exception {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        Buy buy = getBuyService().findById(id);
        if (buy == null) {
            return generateNotFoundErrorJson(Context.BUY);
        }

        buy = new Buy();
         buy.setId(Long.valueOf(protocolRequest.getParameters().get(0).getValue()));
        buy.setCompradorId(Long.valueOf(protocolRequest.getParameters().get(1).getValue()));
        buy.setProductoId(Long.valueOf(protocolRequest.getParameters().get(2).getValue()));
        buy.setEstado(protocolRequest.getParameters().get(3).getValue());
        buy.setFechaCompra(protocolRequest.getParameters().get(4).getValue());

        boolean response = getBuyService().edit(id, buy);
        return String.valueOf(response);
    }

    private String processDeleteBuy(Protocol protocolRequest) throws Exception {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        boolean response = getBuyService().delete(id);
        return String.valueOf(response);
    }

    private String processFindByIdBuy(Protocol protocolRequest) throws Exception {
         Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());

        Buy buy = getBuyService().findById(id);
        if (buy == null) {
            return generateNotFoundErrorJson(Context.BUY);
        } else {
            return objectToJSON(buy);
        }
    }

    private String processFindBuyComp(Protocol protocolRequest) throws Exception {
        Long idComp = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        List<Buy> buys = getBuyService().findByComp(idComp);
        return objectToJSON(buys);
    }

    private String processFindAllBuysBuy(Protocol protocolRequest) throws Exception {
        List<Buy> buys = getBuyService().findAll();
        return objectToJSON(buys);
    }

    private String processFindUserLogin(Protocol protocolRequest) throws Exception {
        String login = protocolRequest.getParameters().get(0).getValue();
        User user = getUserService().findByLogin(login);
        return objectToJSON(user);
    }

    private String processFindUserType(Protocol protocolRequest) throws Exception {
        String type = protocolRequest.getParameters().get(0).getValue();
        List<User> users = getUserService().findByType(type);
        return objectToJSON(users);
    }

    private String processFindAllUsers() throws Exception {
        List<User> users = getUserService().findAll();
        return objectToJSON(users);
    }
    

    private String processFindBankAccount(Protocol protocolRequest) throws Exception {
        Long id = Long.valueOf(protocolRequest.getParameters().get(0).getValue());
        BankAccount bank = getBankService().findByIdUser(id);
        return objectToJSON(bank);
    }

    private String processEditBankType(Protocol protocolRequest) throws Exception {
        Long id = Long.valueOf(protocolRequest.getParameters().get(1).getValue());
         BankAccount bank = getBankService().findByIdUser(id);
        if (bank== null) {
            return generateNotFoundErrorJson(Context.BANK);
        }

        double saldo = Double.valueOf(protocolRequest.getParameters().get(0).getValue());

        boolean response = getBankService().editBalanc(id,saldo);
        return String.valueOf(response);
    }

    
   
}