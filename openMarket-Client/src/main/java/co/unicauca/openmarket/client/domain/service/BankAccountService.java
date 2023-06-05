
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IBankAccountAccess;
import co.unicauca.openmarket.commons.domain.BankAccount;


public class BankAccountService {
    private IBankAccountAccess repository;
    
    public BankAccountService(){}

    /**
     * Inyeccion de dependencias en el constructor
     * @param repository 
     */
    public BankAccountService(IBankAccountAccess repository) {
        this.repository = repository;
    }
    
    /**
     * Edita el saldo de una cuenta bancaria.
     *
     * @param idBank   El identificador de la cuenta bancaria
     * @param balance  El nuevo saldo de la cuenta bancaria
     * @return         true si se editó exitosamente, false en caso contrario
     * @throws Exception Si ocurre un error durante la edición
     */
    public boolean editBankAccount(Long idBank, double balance) throws Exception{
        boolean result = repository.editBalance(idBank, balance);
        return result;
    }
    
      /**
     * Busca una cuenta bancaria por el identificador de usuario.
     *
     * @param id  El identificador de usuario
     * @return    La cuenta bancaria encontrada
     * @throws Exception Si ocurre un error durante la búsqueda
     */
    public BankAccount findByIdUser(Long id) throws Exception{
        return repository.findByUser(id);
    }
    
    
}
