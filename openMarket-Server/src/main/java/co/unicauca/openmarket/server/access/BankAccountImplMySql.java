
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
        createBankAccounts();
    }

    private void initDatabase() {
         // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS bankAccount (\n"
                + "  bank_id integer AUTO_INCREMENT PRIMARY KEY,\n"
                + "  user_id integer NOT NULL,\n"
                + "  bank_saldo integer NOT NULL"
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
         String[] insertStatements = {  
               "INSERT INTO BANKACCOUNT(bank_id, user_id, bank_saldo)  values (1,2,10000)",
               "INSERT INTO BANKACCOUNT(bank_id, user_id, bank_saldo) values (2,4,1000000)"};

          try {

            for (String statement : insertStatements) {
                PreparedStatement pstmt = conn.prepareStatement(statement);
                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
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
                    + " WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                BankAccount newBank = new BankAccount();
                newBank.setBankId(res.getLong("bank_id"));
                newBank.setSaldo(res.getLong("bank_saldo"));
                newBank.setUserId(res.getLong("user_id"));
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
                    + "WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

  
    
}
