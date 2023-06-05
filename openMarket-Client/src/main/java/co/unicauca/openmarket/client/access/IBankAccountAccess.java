package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.BankAccount;

public interface IBankAccountAccess {

    /**
     * Busca una cuenta bancaria asociada a un usuario específico.
     *
     * @param id Identificador del usuario
     * @return Objeto BankAccount que representa la cuenta bancaria encontrada
     * @throws Exception Si ocurre algún error durante la búsqueda
     */
    BankAccount findByUser(Long id) throws Exception;

    /**
     * Edita el saldo de una cuenta bancaria.
     *
     * @param id Identificador de la cuenta bancaria
     * @param balance Nuevo saldo de la cuenta
     * @return true si la edición del saldo se realiza correctamente, false en
     * caso contrario
     * @throws Exception Si ocurre algún error durante la edición del saldo
     */
    boolean editBalance(Long id, double balance) throws Exception;
}
