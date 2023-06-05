package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.Buy;
import java.util.List;

public interface IBuyAccess {

    /**
     * Guarda una nueva compra.
     *
     * @param newBuy Objeto Buy que representa la nueva compra a guardar
     * @return true si la operación de guardado se realiza correctamente, false
     * en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de guardado
     */
    boolean save(Buy newBuy) throws Exception;

    /**
     * Edita una compra existente.
     *
     * @param id Identificador de la compra a editar
     * @param buy Objeto Buy que representa la compra actualizada
     * @return true si la operación de edición se realiza correctamente, false
     * en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de edición
     */
    boolean edit(Long id, Buy buy) throws Exception;

    /**
     * Elimina una compra existente.
     *
     * @param id Identificador de la compra a eliminar
     * @return true si la operación de eliminación se realiza correctamente,
     * false en caso contrario
     * @throws Exception Si ocurre algún error durante el proceso de eliminación
     */
    boolean delete(Long id) throws Exception;

    /**
     * Busca una compra por su identificador.
     *
     * @param id Identificador de la compra a buscar
     * @return Objeto Buy que representa la compra encontrada
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    Buy findById(Long id) throws Exception;

    /**
     * Busca compras realizadas por una compañía específica.
     *
     * @param idComp Identificador de la compañía
     * @return Lista de objetos Buy que representan las compras realizadas por
     * la compañía
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    List<Buy> findByComp(Long idComp) throws Exception;

    /**
     * Obtiene todas las compras existentes.
     *
     * @return Lista de objetos Buy que representan todas las compras existentes
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    List<Buy> findAll() throws Exception;

}
