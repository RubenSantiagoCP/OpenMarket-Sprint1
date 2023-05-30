/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Category;
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
 *
 * @author juan
 */
public class CategoryRepositoryImplMysql implements ICategoryRepository{
    /**
     * Conección con Mysql
     */
    private Connection conn;

    public CategoryRepositoryImplMysql() {
        initDataBase();
        crearCategorias();
    }
    
     private void initDataBase() {
         // SQL statement for creating a new table
        String sql = "CREATE TABLE category (\n"
                + "	cat_id integer PRIMARY KEY,\n"
                + "	cat_name text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearCategorias() {
        String[] insertStatements = {
            "INSERT INTO category (cat_id,cat_name) VALUES(1,'Bebidas')",
            "INSERT INTO category (cat_id,cat_name) VALUES(2,'Paquetes')",
            "INSERT INTO category (cat_id,cat_name) VALUES(3,'Enlatados')"
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
    public boolean save(Category newCategory) {
          try {
            this.connect();
            String sql = "INSERT INTO category ( cat_name)"
                    + "VALUES (?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCategory.getName());
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean edit(Long id, Category category) {
         try {
            this.connect();
            String sql = "UPDATE  category "
                    + "SET cat_name = ?"
                    + "WHERE cat_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
          try {
            this.connect();
            String sql = "DELETE FROM category "
                    + "WHERE cat_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }    

    @Override
    public Category findById(Long id) {
        Category category = null;

        try {
            this.connect();

            String sql = "SELECT * FROM category  "
                    + "WHERE cat_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(res.getLong("cat_id"));
                newCategory.setName(res.getString("cat_name"));
   
                return newCategory;
            }
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories= new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM category";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
               Category newCategory = new Category();
                newCategory.setCategoryId(res.getLong("cat_id"));
                newCategory.setName(res.getString("cat_name"));
                categories.add(newCategory);
            }
            pstmt.executeUpdate();
            pstmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();

        try {

            this.connect();
            String sql = "SELECT * FROM category"
                    + " WHERE cat_name = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(res.getLong("cat_id"));
                newCategory.setName(res.getString("name"));

                categories.add(newCategory);
            }

            stmt.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepositoryImplMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }
}
