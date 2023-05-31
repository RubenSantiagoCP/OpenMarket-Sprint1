
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.domain.BankAccount;


public interface IBankAccountAccess {
    BankAccount findByUser(Long id)throws Exception ;
    boolean editBalance(Long id, double balance) throws Exception;
}
