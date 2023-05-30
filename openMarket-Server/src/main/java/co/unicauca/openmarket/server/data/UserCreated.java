
package co.unicauca.openmarket.server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCreated {
    
    private Connection conn;

   public UserCreated() {
        connect();
        initDatabase();
        //crearUsuarios();
        //disconnect();
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
        Logger.getLogger(UserCreated.class.getName()).log(Level.SEVERE, null, ex);
    }
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
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserCreated.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserCreated.class.getName()).log(Level.SEVERE, null, ex);
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
