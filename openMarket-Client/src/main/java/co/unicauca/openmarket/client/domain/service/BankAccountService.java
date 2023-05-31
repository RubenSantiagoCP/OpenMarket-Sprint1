
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
    
    public boolean editBankAccount(Long idBank, double balance) throws Exception{
        boolean result = repository.editBalance(idBank, balance);
        return result;
    }
    
    public BankAccount findByIdUser(Long id) throws Exception{
        return repository.findByUser(id);
    }
    
    
}
