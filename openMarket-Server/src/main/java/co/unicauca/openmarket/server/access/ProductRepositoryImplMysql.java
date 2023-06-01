package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.infra.Utilities;

import co.unicauca.openmarket.commons.domain.Product;
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

/**
 * Repositorio de Clientes en MySWL
 *
 * @author Libardo, Julio
 */
public class ProductRepositoryImplMysql implements IProductRepository {

    /**
     * Conección con Mysql
     */
    private Connection conn;

    public ProductRepositoryImplMysql() {
        initDatabase();
        crearProductos();
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	description text NULL,\n"
                + "	prod_price number NOT NULL,\n"
                + "	cat_id integer NOT NULL,\n"
                + "	user_id integer NOT NULL,\n"
                + "	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),\n"
                + "	CONSTRAINT fk_category FOREIGN KEY (cat_id) REFERENCES users (cat_id)\n"
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
    
    public void crearProductos() {
        String[] insertStatements = {
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(1,'Margarita limon','Paquete',2000,1,3)",
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(2,'Margarita picante','Paquete',3000,1,3)",
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(3,'Margarita pollo','Paquete',4000,2,3)",
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(4,'Margarita','Paquete',2400,1,5)",
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(5,'Margarita','Paquete',3500,1,5)",
            "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) VALUES(6,'Margarita','Paquete',4700,1,5)"
        };

        try {

            for (String statement : insertStatements) {
                PreparedStatement pstmt = conn.prepareStatement(statement);
                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException ex) {
                Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(res.getLong("productId"));
                newProduct.setName(res.getString("name"));
                newProduct.setDescription(res.getString("description"));
                newProduct.setPrice(res.getDouble("prod_price"));
                newProduct.setCategoryId(res.getLong("cat_id"));
                newProduct.setVendedorId(res.getLong("user_id"));
                products.add(newProduct);
            }
            //pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
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
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar Customer de la base de datos", ex);
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
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
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
        if (conn != null) {
            return; // Ya hay una conexión establecida, no es necesario conectarse nuevamente
        }
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
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
    public String createProduct(Product newProduct) {
        try {
            this.connect();
            String sql = "INSERT INTO products (productId,name,description,prod_price,cat_id,user_id) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newProduct.getProductId());
            pstmt.setString(2, newProduct.getName());
            pstmt.setString(3, newProduct.getDescription());
            pstmt.setDouble(4, newProduct.getPrice());
            pstmt.setLong(5, newProduct.getCategoryId());
            pstmt.setLong(6, newProduct.getVendedorId());
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newProduct.getProductId().toString();
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.connect();
            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;

        try {
            this.connect();

            String sql = "SELECT * FROM products  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(res.getLong("productId"));
                newProduct.setName(res.getString("name"));
                newProduct.setDescription(res.getString("description"));
                newProduct.setPrice(res.getDouble("prod_price"));
                newProduct.setCategoryId(res.getLong("cat_id"));
                newProduct.setVendedorId(res.getLong("user_id"));
                return newProduct;
            }
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public List<Product> findByName(String pname) {
        List<Product> products = new ArrayList<>();

        try {

            this.connect();
            String sql = "SELECT * FROM products"
                    + " WHERE name = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pname);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setPrice(rs.getDouble("prod_price"));
                newProduct.setCategoryId(rs.getLong("cat_id"));
                newProduct.setVendedorId(rs.getLong("user_id"));;

                products.add(newProduct);
            }

            stmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        List<Product> products = new ArrayList<>();

        try {
            this.connect();
            String sql = "SELECT * FROM products"
                    + " WHERE cat_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setPrice(rs.getDouble("prod_price"));
                newProduct.setCategoryId(rs.getLong("cat_id"));
                newProduct.setVendedorId(rs.getLong("user_id"));
                products.add(newProduct);
            }

            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    @Override
    public boolean edit(Long id, Product product) {

        try {
            this.connect();
            String sql = "UPDATE  products "
                    + "SET name = ?, description = ?, prod_price = ?, cat_id = ? "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setLong(4, product.getCategoryId());
            pstmt.setLong(5, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public List<Product> finByPrice(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products"
                    + " WHERE prod_price >= ? AND prod_price <= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, minPrice);
            pstm.setDouble(2, maxPrice);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setPrice(rs.getDouble("prod_price"));
                newProduct.setCategoryId(rs.getLong("cat_id"));
                newProduct.setVendedorId(rs.getLong("user_id"));
                products.add(newProduct);
            }
            
            pstm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

}
