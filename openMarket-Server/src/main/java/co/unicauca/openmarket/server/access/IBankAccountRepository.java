
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.BankAccount;

public interface IBankAccountRepository {
    BankAccount findByUser(Long id)throws Exception ;
    boolean editBalance(Long id, double balance) throws Exception;
}
