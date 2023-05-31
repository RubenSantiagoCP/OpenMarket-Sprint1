
package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.commons.domain.BankAccount;
import co.unicauca.openmarket.server.access.IBankAccountRepository;


public class BankAccountService {
    IBankAccountRepository repository;

    public BankAccountService(IBankAccountRepository repository) {
        this.repository = repository;
    }
    
     public synchronized BankAccount findByIdUser(Long id) throws Exception{
         return this.repository.findByUser(id);
     }
     
      public synchronized boolean editBalanc(Long id, double balance) throws Exception{
          return this.repository.editBalance(id, balance);
      }
}
