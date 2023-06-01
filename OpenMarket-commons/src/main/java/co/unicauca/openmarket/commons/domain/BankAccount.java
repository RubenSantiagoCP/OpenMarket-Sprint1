
package co.unicauca.openmarket.commons.domain;

/**
 * Clase que representa una cuenta bancaria.
 */

public class BankAccount {
    private Long bankId;
    private Long userId;
    private double saldo;

     /**
     * Constructor por defecto de la clase BankAccount.
     */
    
    public BankAccount() {
    }
    
    /**
     * Constructor de la clase BankAccount.
     * @param bankId Identificador del banco.
     * @param userId Identificador del usuario.
     * @param saldo Saldo disponible en la cuenta.
     */

    public BankAccount(Long bankId, Long userId, double saldo) {
        this.bankId = bankId;
        this.userId = userId;
        this.saldo = saldo;
    }

    /**
     * Obtiene el identificador del banco.
     * @return Identificador del banco.
     */
    
    public Long getBankId() {
        return bankId;
    }

     /**
     * Establece el identificador del banco.
     * @param bankId Identificador del banco.
     */
    
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

      /**
     * Obtiene el identificador del usuario.
     * @return Identificador del usuario.
     */
    public Long getUserId() {
        return userId;
    }

      /**
     * Establece el identificador del usuario.
     * @param userId Identificador del usuario.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

     /**
     * Obtiene el saldo disponible en la cuenta.
     * @return Saldo disponible en la cuenta.
     */
    public double getSaldo() {
        return saldo;
    }

      /**
     * Establece el saldo disponible en la cuenta.
     * @param saldo Saldo disponible en la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
