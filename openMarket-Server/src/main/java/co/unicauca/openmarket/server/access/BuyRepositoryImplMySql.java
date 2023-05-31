package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Buy;
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

public class BuyRepositoryImplMySql implements IBuyRepository {

    private Connection conn;

    public BuyRepositoryImplMySql() {
        initDatabase();
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS buys (\n"
                + "  buy_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "  user_id integer NOT NULL,\n"
                + "  productId integer NOT NULL,\n"
                + "  buy_estado text NOT NULL,\n"
                + "  buy_fecha text NOT NULL,\n"
                + "  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),\n"
                + "  CONSTRAINT fk_product FOREIGN KEY (productId) REFERENCES products (productId)\n"
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
    public String createBuy(Buy newBuy) throws Exception {
        try {
            this.connect();
            String sql = "INSERT INTO buys (user_id , productId, buy_estado, buy_fecha) "
                    + "VALUES ( ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newBuy.getCompradorId());
            pstmt.setLong(2, newBuy.getProductoId());
            pstmt.setString(3, newBuy.getEstado());
            pstmt.setString(4, newBuy.getFechaCompra());
            pstmt.executeUpdate();
            pstmt.close();
           // this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newBuy.getId().toString();
    }

    @Override
    public Boolean edit(Long id, Buy buy) throws Exception {
        try {
            this.connect();
            String sql = "UPDATE  buys "
                    + "SET buy_estado = ? "
                    + "WHERE buy_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, buy.getEstado());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        try {
            this.connect();
            String sql = "DELETE FROM buys "
                    + "WHERE buy_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Buy findById(Long id) throws Exception {
         Buy buy = null;
     
        try {
                this.connect();

            String sql = "SELECT * FROM buys  "
                    + "WHERE buy_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Buy newBuy = new Buy();
                newBuy.setId(res.getLong("buy_id"));
                newBuy.setProductoId(res.getLong("productId"));
                newBuy.setCompradorId(res.getLong("user_id"));
                newBuy.setFechaCompra(res.getString("buy_fecha"));
                newBuy.setEstado(res.getString("buy_estado"));
                return newBuy;
            }
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buy;
    }


    @Override
    public List<Buy> findByComp(Long idComp) throws Exception {
        List<Buy> buys= new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM buys "
                    + " where user_id = ?";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, idComp);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Buy newBuy = new Buy();
                newBuy.setId(res.getLong("buy_id"));
                newBuy.setProductoId(res.getLong("productId"));
                newBuy.setCompradorId(res.getLong("user_id"));
                newBuy.setFechaCompra(res.getString("buy_fecha"));
                newBuy.setEstado(res.getString("buy_estado"));
                buys.add(newBuy);
            }
              //pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(BuyRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buys;
    }

    @Override
    public List<Buy> findAll() throws Exception {
        List<Buy> buys= new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM buys";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Buy newBuy = new Buy();
                newBuy.setId(res.getLong("buy_id"));
                newBuy.setProductoId(res.getLong("productId"));
                newBuy.setCompradorId(res.getLong("user_id"));
                newBuy.setFechaCompra(res.getString("buy_fecha"));
                newBuy.setEstado(res.getString("buy_estado"));
                buys.add(newBuy);
            }
              pstmt.executeQuery();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImplMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buys;
    }

}
