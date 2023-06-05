package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.observer.Observer;
import co.unicauca.openmarket.commons.observer.Subject;
import java.util.ArrayList;

import java.util.List;

/**
 * Servicio de categorías
 */
public class CategoryService implements Subject {

    private List<Observer> observers = new ArrayList<>();

    public CategoryService() {

    }
    private ICategoryAccess repository;

    /**
     * Inyección de dependencias en el constructor
     *
     * @param repository repositorio de categorías
     */
    public CategoryService(ICategoryAccess repository) {
        this.repository = repository;
    }

    /**
     * Guarda una nueva categoría en el repositorio
     *
     * @param id identificador de la categoría
     * @param name nombre de la categoría
     * @return true si la categoría se guarda correctamente, false de lo
     * contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean saveCategory(Long id, String name) throws Exception {
        Category newCategory = new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);
        if (newCategory.getName().isBlank()) {
            return false;
        }
        boolean result = repository.save(newCategory);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;
    }

    /**
     * Edita una categoría existente en el repositorio
     *
     * @param categoryId identificador de la categoría a editar
     * @param cat objeto Category con los nuevos datos de la categoría
     * @return true si la categoría se edita correctamente, false de lo
     * contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean editCategory(Long categoryId, Category cat) throws Exception {

        //Validate product
        if (cat == null || cat.getName().isBlank()) {
            return false;
        }

        boolean result = repository.edit(categoryId, cat);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;
    }

    /**
     * Elimina una categoría del repositorio
     *
     * @param id identificador de la categoría a eliminar
     * @return true si la categoría se elimina correctamente, false de lo
     * contrario
     * @throws Exception si ocurre algún error en la operación
     */
    public boolean deleteCategory(Long id) throws Exception {

        boolean result = repository.delete(id);

        // Notificar a los observadores solo si la categoría se guardó correctamente
        if (result) {
            notifyObservers();
        }

        return result;
    }

    /**
     * Busca una categoría por su identificador en el repositorio
     *
     * @param id identificador de la categoría
     * @return objeto Category correspondiente al identificador, null si no se
     * encuentra
     * @throws Exception si ocurre algún error en la operación
     */
    public Category findCategoryById(Long id) throws Exception {
        return repository.findById(id);

    }

    /**
     * Obtiene todas las categorías almacenadas en el repositorio
     *
     * @return lista de todas las categorías
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Category> findAllCategories() throws Exception {
        return repository.findAll();
    }

    /**
     * Busca categorías por su nombre en el repositorio
     *
     * @param name nombre de la categoría
     * @return lista de categorías que coinciden con el nombre
     * @throws Exception si ocurre algún error en la operación
     */
    public List<Category> findCategoriesByName(String name) throws Exception {
        return repository.findByName(name);
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
