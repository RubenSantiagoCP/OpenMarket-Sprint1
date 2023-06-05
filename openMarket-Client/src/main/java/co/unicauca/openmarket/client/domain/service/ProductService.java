package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.commons.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.observer.Subject;

/**
 * Servicio de productos
 */
public class ProductService implements Subject {

    private List<Observer> observers = new ArrayList<>();

    public ProductService() {

    }

    private IProductAccess repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductAccess
     */
    public ProductService(IProductAccess repository) {
        this.repository = repository;
    }

    /**
     * Guarda un nuevo producto en el repositorio
     *
     * @param id identificador del producto
     * @param name nombre del producto
     * @param description descripción del producto
     * @param price precio del producto
     * @param categoryId identificador de la categoría del producto
     * @param vendedorId identificador del vendedor del producto
     * @return true si el producto se guarda correctamente, false de lo
     * contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean saveProduct(Long id, String name, String description, Double price, Long categoryId, Long vendedorId) throws Exception {

        Product newProduct = new Product();
        newProduct.setProductId(id);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setCategoryId(categoryId);
        newProduct.setVendedorId(vendedorId);

        boolean result = repository.save(newProduct);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;

    }

    /**
     * Busca productos por rango de precio en el repositorio
     *
     * @param minPrice precio mínimo
     * @param maxPrice precio máximo
     * @return lista de productos que se encuentran en el rango de precio
     * especificado
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Product> findProductsByPrice(Long minPrice, Long maxPrice) throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.finByPrice(minPrice, maxPrice);
        return products;
    }

     /**
     * Obtiene todos los productos almacenados en el repositorio
     * @return lista de todos los productos
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Product> findAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();

        return products;
    }

    /**
     * Busca un producto por su identificador en el repositorio
     * @param id identificador del producto
     * @return objeto Product correspondiente al identificador, null si no se encuentra
     * @throws Exception si ocurre algún error en la operación
     */
    public Product findProductById(Long id) throws Exception {
        return repository.findById(id);
    }

     /**
     * Busca productos por su nombre en el repositorio
     * @param name nombre del producto
     * @return lista de productos que coinciden con el nombre
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Product> findProductsByName(String name) throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.findByName(name);
        return products;
    }

    /**
     * Busca productos por su categoría en el repositorio
     * @param categoryId identificador de la categoría
     * @return lista de productos que pertenecen a la categoría especificada
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Product> findProductsByCategory(Long categoryId) throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(categoryId);

        return products;
    }

     /**
     * Elimina un producto del repositorio
     * @param id identificador del producto a eliminar
     * @return true si el producto se elimina correctamente, false de lo contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean deleteProduct(Long id) throws Exception {
        boolean result = repository.delete(id);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;

    }

     /**
     * Edita un producto en el repositorio
     * @param productId identificador del producto a editar
     * @param newProd nuevo objeto Product con los datos actualizados del producto
     * @return true si el producto se edita correctamente, false de lo contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean editProduct(Long productId, Product newProd) throws Exception {

        //Validate product
        if (newProd == null || newProd.getName().isBlank() || newProd.getDescription().isBlank()) {
            return false;
        }

        boolean result = repository.edit(productId, newProd);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;

    }

    @Override
    public void registerObserver(Observer catGui) {
        observers.add(catGui);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
