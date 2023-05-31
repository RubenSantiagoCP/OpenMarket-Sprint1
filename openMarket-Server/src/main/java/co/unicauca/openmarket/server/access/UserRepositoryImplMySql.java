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

public class UserRepositoryImplMySql implements IUserRepository {

    private Connection conn;

    public UserRepositoryImplMySql() {
        initDatabase();
        crearUsuarios();
    }

    public void crearUsuarios() {
        String[] insertStatements = {
            "INSERT INTO users (user_id, user_password, user_username, user_login, user_tipo) VALUES(1, '12345', 'rscruz', 'ruben@unicauca.co', 'repartidor')",
            "INSERT INTO users (user_id, user_password, user_username, user_login, user_tipo) VALUES(2, 'naren', 'naren', 'naren@unicauca.co', 'comprador')",
            "INSERT INTO users (user_id, user_password, user_username, user_login, user_tipo) VALUES(3, 'juan', 'juan camilo', 'juan@unicauca.co', 'vendedor')",
            "INSERT INTO users (user_id, user_password, user_username, user_login, user_tipo) VALUES(4, 'laura', 'laura i', 'laura@unicauca.co', 'comprador')",
            "INSERT INTO users (user_id, user_password, user_username, user_login, user_tipo) VALUES(5, 'maria', 'ana maria', 'ana@unicauca.co', 'vendedor')"
        };

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
            //this.disconnect();

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
            //this.disconnect();

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
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /*
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
 /*
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }*/
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
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
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

}
