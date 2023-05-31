
package co.unicauca.openmarket.commons.domain;

public class BankAccount {
    private Long bankId;
    private Long userId;
    private double saldo;

    public BankAccount() {
    }

    public BankAccount(Long bankId, Long userId, double saldo) {
        this.bankId = bankId;
        this.userId = userId;
        this.saldo = saldo;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
