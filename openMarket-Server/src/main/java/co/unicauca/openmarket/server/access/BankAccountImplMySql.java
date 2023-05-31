
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.BankAccount;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccountImplMySql implements IBankAccountRepository{

    private Connection conn;

    public BankAccountImplMySql() {
        initDatabase();
    }

    private void initDatabase() {
         // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS bankAccount (\n"
                + "  bank_id integer AUTO_INCREMENT PRIMARY KEY,\n"
                + "  user_id integer NOT NULL,\n"
                + "  bank_saldo integer NOT NULL,\n"
                + "  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createBankAccounts(){
         // SQL statement for creating a new table
        String sql = "INSERT INTO BANKACCOUNT(bank_id, user_id, bank_saldo) "
                + " values (1,2,1000000);"
                + "INSERT INTO BANKACCOUNT(bank_id, user_id, bank_saldo) "
                + " values (2,4,1000000);";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void connect() {
        if (conn != null) {
            return; // Ya hay una conexión establecida, no es necesario conectarse nuevamente
        }
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger( BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                conn = null; // Establecer a null después de cerrar la conexión
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public BankAccount findByUser(Long id) throws Exception {
         BankAccount bank = null;
     
        try {
                this.connect();

            String sql = "SELECT * FROM bankAccount "
                    + " inner join users"
                    + " on users.user_id = bankAccount.user_id  "
                    + "WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                BankAccount newBank = new BankAccount();
                newBank.setBankId(res.getLong("bank_id"));
                newBank.setSaldo(res.getLong("bank_saldo"));
                
                User newUser = new User();
                newUser.setId(res.getLong("users.user_id"));
                newUser.setLogin(res.getString("user_login"));
                newUser.setPassword(res.getString("user_password"));
                newUser.setTipo(res.getString("user_tipo"));
                newUser.setUsername(res.getString("user_username"));
                newBank.setUser(newUser);
                return newBank;
            }
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bank;
    }

    @Override
    public boolean editBalance(Long id, double balance) throws Exception {
        try {
            this.connect();
            String sql = "UPDATE  bankAccount "
                    + "SET bank_saldo = ? "
                    + "WHERE bank_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setLong(2, id);
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

  
    
}
