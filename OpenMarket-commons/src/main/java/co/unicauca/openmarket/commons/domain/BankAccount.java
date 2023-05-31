
package co.unicauca.openmarket.commons.domain;

public class BankAccount {
    private Long bankId;
    private User user;
    private double saldo;

    public BankAccount() {
    }

    
    public BankAccount(Long bankId, User user, double saldo) {
        this.bankId = bankId;
        this.user = user;
        this.saldo = saldo;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
