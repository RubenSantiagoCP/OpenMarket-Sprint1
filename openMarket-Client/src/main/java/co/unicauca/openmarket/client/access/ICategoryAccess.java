package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.Category;
import java.util.List;

public interface ICategoryAccess {

    /**
     * Guarda una nueva categoría.
     *
     * @param newCategory Objeto Category que representa la nueva categoría a
     * guardar
     * @return true si la operación de guardado se realiza correctamente, false
     * en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de guardado
     */
    boolean save(Category newCategory) throws Exception;

    /**
     * Edita una categoría existente.
     *
     * @param id Identificador de la categoría a editar
     * @param category Objeto Category que representa la categoría actualizada
     * @return true si la operación de edición se realiza correctamente, false
     * en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de edición
     */
    boolean edit(Long id, Category category) throws Exception;

    /**
     * Elimina una categoría existente.
     *
     * @param id Identificador de la categoría a eliminar
     * @return true si la operación de eliminación se realiza correctamente,
     * false en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de eliminación
     */
    boolean delete(Long id) throws Exception;

    /**
     * Busca una categoría por su identificador.
     *
     * @param id Identificador de la categoría a buscar
     * @return Objeto Category que representa la categoría encontrada
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    Category findById(Long id) throws Exception;

    /**
     * Obtiene todas las categorías existentes.
     *
     * @return Lista de objetos Category que representan todas las categorías
     * existentes
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    List<Category> findAll() throws Exception;

    /**
     * Busca categorías por su nombre.
     *
     * @param name Nombre de la categoría a buscar
     * @return Lista de objetos Category que representan las categorías
     * encontradas
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    List<Category> findByName(String name) throws Exception;

}
