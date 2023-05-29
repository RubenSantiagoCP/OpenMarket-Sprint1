
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositoryImplMySql implements IUserRepository{
    
      private Connection conn;

    public UserRepositoryImplMySql() {
        initDatabase();
    }

    @Override
    public User findByLogin(String login) throws Exception {
        User user = null;
        
     
        try {
                this.connect();

            String sql = "SELECT * FROM users  "
                    + "WHERE user_login = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                User newUser = new User();
                newUser.setId(res.getLong("user_id"));
                newUser.setLogin(res.getString("user_login"));
                newUser.setPassword(res.getString("user_password"));
                newUser.setTipo(res.getString("user_tipo"));
                newUser.setUsername(res.getString("user_username"));
                return newUser;
            }
            pstmt.close();
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
      private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " user_id integer PRIMARY KEY,\n"
                + "	user_password text NOT NULL,\n"
                + "	user_username text NOT NULL,\n"
                + "	user_login text NOT NULL,\n"
                + "	user_tipo text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<User> findAll() throws Exception {
         List<User> users = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM users";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                User newUser = new User();
                newUser.setId(res.getLong("user_id"));
                newUser.setLogin(res.getString("user_login"));
                newUser.setPassword(res.getString("user_password"));
                newUser.setTipo(res.getString("user_tipo"));
                newUser.setUsername(res.getString("user_username"));

                users.add(newUser);
            }
              pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public List<User> findByType(String type) throws Exception {
        List<User> users = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM users where user_tipo = ?";
           
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                User newUser = new User();
                newUser.setId(res.getLong("user_id"));
                newUser.setLogin(res.getString("user_login"));
                newUser.setPassword(res.getString("user_password"));
                newUser.setTipo(res.getString("user_tipo"));
                newUser.setUsername(res.getString("user_username"));

                users.add(newUser);
            }
              pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
     public int connect() {
        try {
            Class.forName(Utilities.loadProperty("server.db.driver"));
            //crea una instancia de la controlador de la base de datos
            String url = Utilities.loadProperty("server.db.url");
            String username = Utilities.loadProperty("server.db.username");
            String pwd = Utilities.loadProperty("server.db.password");
            conn = DriverManager.getConnection(url, username, pwd);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, "Error al consultar Customer de la base de datos", ex);
        }
        return -1;
    }

    /**
     * Cierra la conexion con la base de datos
     *
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }
    
}
