package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.observer.Observer;
import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmaket.client.command.RemoveProductCommand;
import co.unicauca.openmarket.client.infra.Messages;
import co.unicauca.openmarket.commons.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jsarabino
 */
public class JpPrincipal extends javax.swing.JPanel implements Observer{
    
    private javax.swing.JPanel jpContent;
    private ProductService productService;
    private CategoryService categoryService;
    private Invoker invoker;
    private static JpEditar jpEditar;
    private User vendedor;
    
    /**
     * Creates new form jpPrincipal
     */
    public JpPrincipal(javax.swing.JPanel parent, ProductService productService, CategoryService categoryService, Invoker invoker, User vendedor) {
        initComponents();  
        initializeTable();
        this.productService = productService;
        this.categoryService = categoryService;
        this.invoker = new Invoker();
        this.invoker.registerObserver(this);
        this.jpContent = parent;
        this.vendedor = vendedor;
        
        //Panel Editar producto
        jpEditar = new JpEditar(jpContent, this, productService, categoryService, invoker, this.vendedor);
        productService.registerObserver(jpEditar);
        categoryService.registerObserver(jpEditar);

        
        stateInitial();
    }
    
    /**
     * Inicializa la tabla principal de productos con un modelo de tabla vacío.
     * El modelo de tabla contiene las columnas "Id", "Name", "Description", "Precio" y "Categoria".
     */
    private void initializeTable() {
        tblPrincipal.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Description", "Precio", "Categoria"
                }
        ));
    }

    /**
     * Llena la tabla principal de productos con los datos de la lista de productos proporcionada.
     * Cada fila de la tabla representa un producto y contiene información como el Id, el Nombre, la Descripción,
     * el Precio y la Categoría del producto.
     *
     * @param listProducts La lista de productos a mostrar en la tabla.
     * @throws Exception Si ocurre algún error al obtener la información de la categoría de un producto.
     */
    private void fillTable(List<Product> listProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblPrincipal.getModel();
        Object rowData[] = new Object[5];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getProductId();
            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();
            Long catId = listProducts.get(i).getCategoryId();
            String catName = categoryService.findCategoryById(catId).getName();
            rowData[3] = listProducts.get(i).getPrice();
            rowData[4] = catName;

            model.addRow(rowData);
        }
    }
    
    /**
     * Llena la tabla principal de productos con los datos de un único producto.
     * La tabla mostrará la información del producto, incluyendo el Id, el Nombre, la Descripción,
     * el Precio y la Categoría.
     *
     * @param producto El producto a mostrar en la tabla.
     * @throws Exception Si ocurre algún error al obtener la información de la categoría del producto.
     */
    private void fillTableId(Product producto) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblPrincipal.getModel();

        Object rowData[] = new Object[5];//No columnas
        rowData[0] = producto.getProductId();
        rowData[1] = producto.getName();
        rowData[2] = producto.getDescription();

        Long catId = producto.getCategoryId();
        String catName = categoryService.findCategoryById(catId).getName();
        rowData[3] = producto.getPrice();
        rowData[4] = catName;

        model.addRow(rowData);
    }

    /**
     * Llena la tabla principal de productos con los datos de una lista de productos.
     * La tabla mostrará la información de cada producto, incluyendo el Id, el Nombre, la Descripción,
     * el Precio y la Categoría.
     *
     * @param listProducts La lista de productos a mostrar en la tabla.
     * @throws Exception Si ocurre algún error al obtener la información de la categoría de un producto.
     */
    private void fillTableName(List<Product> listProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblPrincipal.getModel();

        Object rowData[] = new Object[5];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getProductId();
            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();

            Long catId = listProducts.get(i).getCategoryId();
            String catName = categoryService.findCategoryById(catId).getName();
            rowData[3] = listProducts.get(i).getPrice();
            rowData[4] = catName;

            model.addRow(rowData);
        }
    }

    /**
     * Llena la tabla principal de productos con los datos de una lista de productos.
     * La tabla mostrará la información de cada producto, incluyendo el Id, el Nombre, la Descripción,
     * el Precio y la Categoría.
     * La categoría de cada producto se obtiene mediante la búsqueda en el servicio de categorías.
     *
     * @param listProducts La lista de productos a mostrar en la tabla.
     * @throws Exception Si ocurre algún error al obtener la información de la categoría de un producto.
     */
    private void fillTableCategory(List<Product> listProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblPrincipal.getModel();

        Object rowData[] = new Object[5];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {

            rowData[0] = listProducts.get(i).getProductId();

            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();
    
                Long catId = listProducts.get(i).getCategoryId();
                
                String catName = categoryService.findCategoryById(catId).getName();
                     rowData[3]=listProducts.get(i).getPrice();
                rowData[4] = catName;
           
            model.addRow(rowData);
        }
    }
    
    /**
     * Devuelve la referencia a la tabla principal de productos.
     *
     * @return La tabla principal de productos.
     */
    public javax.swing.JTable getTabla(){
        return this.tblPrincipal;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrincipal = new javax.swing.JTable();
        btnBuscarTodos = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpPrincipal.setBackground(new java.awt.Color(61, 64, 91));
        jpPrincipal.setPreferredSize(new java.awt.Dimension(700, 600));
        jpPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditar.setBackground(new java.awt.Color(224, 122, 95));
        btnEditar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 100, -1));

        btnBorrar.setBackground(new java.awt.Color(224, 122, 95));
        btnBorrar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 100, -1));

        btnBuscar.setBackground(new java.awt.Color(224, 122, 95));
        btnBuscar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 100, -1));
        jpPrincipal.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 179, -1));

        cmbBuscar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Nombre", "Id categoria" }));
        jpPrincipal.add(cmbBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        tblPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPrincipal);

        jpPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 235, 546, 316));

        btnBuscarTodos.setBackground(new java.awt.Color(224, 122, 95));
        btnBuscarTodos.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarTodos.setText("Todos");
        btnBuscarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTodosActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnBuscarTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 100, -1));

        btnEliminar.setBackground(new java.awt.Color(224, 122, 95));
        btnEliminar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 100, -1));

        btnVolver.setBackground(new java.awt.Color(224, 122, 95));
        btnVolver.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 560, 100, -1));

        add(jpPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 610));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Realiza la acción de volver al estado inicial de la interfaz.
     * Este método es llamado cuando se hace clic en el botón "Volver".
     *
     * @param evt El evento que desencadena la acción (clic en el botón "Volver").
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        stateInitial();
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * Realiza la acción de eliminar un producto.
     * Este método es llamado cuando se hace clic en el botón "Eliminar".
     *
     * @param evt El evento que desencadena la acción (clic en el botón "Eliminar").
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            String id = txtProducto.getText().trim();
            if (id.equals("")) {
                Messages.showMessageDialog("Debe ingresar el id del producto a eliminar", "Atención");
                txtProducto.requestFocus();
                return;
            }
            Long productId = Long.valueOf(id);
            if (Messages.showConfirmDialog("Está seguro que desea eliminar este producto?", "Confirmación") == JOptionPane.YES_NO_OPTION) {
                //Obtener producto
                Product product = productService.findProductById(productId);
                //Confirmar que el producto es del vendedor
                if (isProductVendedor(product)){
                    RemoveProductCommand comm = new RemoveProductCommand(productService, product);

                    if (invoker.executeCommand(comm)) {
                        Messages.showMessageDialog("Producto eliminado con éxito", "Atención");
                        stateInitial();
                        txtProducto.setText("");
                    } else {
                        Messages.showMessageDialog("Error al eliminar, lo siento mucho", "Atención");
                    }
                //Sino lanza error
                } else {
                    JOptionPane.showMessageDialog(null,
                         "No tienes permiso para eliminar este producto.",
                         "Errro product id",
                         JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "El id debe ser un numero",
                "Error",
                JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Realiza la acción de buscar y mostrar todos los productos del vendedor.
     * Este método es llamado cuando se hace clic en el botón "Buscar Todos".
     *
     * @param evt El evento que desencadena la acción (clic en el botón "Buscar Todos").
     */
    private void btnBuscarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTodosActionPerformed
        try {
            //Encontrar todos los productos
            List<Product> products = new ArrayList<>();
            products = productService.findAllProducts();
            
            //Filtrar los productos del vendedor
            List<Product> productsVendedor = new ArrayList<>();
            productsVendedor = getProductsVendedor(products);
            
            //Mostrar los productos del vendedor
            fillTable(productsVendedor);
            
        } catch (Exception ex) {
            Logger.getLogger(JpPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarTodosActionPerformed

    /**
    * Realiza la acción de buscar productos según el criterio seleccionado (Id, Categoría o Nombre).
    * Este método es llamado cuando se hace clic en el botón "Buscar".
    *
    * @param evt El evento que desencadena la acción (clic en el botón "Buscar").
    */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if (txtProducto.getText().trim().isBlank()) {
                JOptionPane.showMessageDialog(null,
                    "El texto no debe estar vacio",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Para encontrar todos los productos sin filtrar
            List<Product> products = new ArrayList<>();
            
            //Para obtener lista filtrada de productos del vendedor
            List<Product> productsVendedor = new ArrayList<>();
            
            //Para obtener producto filtrado del vendedor
            Product productVendedor = new Product();
            

            if (this.cmbBuscar.getSelectedItem().toString() == "Id") {
                //Obtener el producto
                productVendedor = productService.findProductById(Long.valueOf(this.txtProducto.getText()));
                //Filtrar producto
                if (isProductVendedor(productVendedor)) {
                    fillTableId(productVendedor);
                }else {
                    JOptionPane.showMessageDialog(null,
                        "Producto no encontrado",
                        "Errro product id",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (this.cmbBuscar.getSelectedItem().toString() == "Categoria") {
                //Obtener todos los productos
                products = productService.findProductsByCategory(Long.parseLong(this.txtProducto.getText()));
                //Obtener los productos del vendedor
                productsVendedor = getProductsVendedor(products);
                //Llenar tabla
                fillTableCategory(productsVendedor);
            } else if (this.cmbBuscar.getSelectedItem().toString() == "Nombre") {
                //Obtener todos los productos
                products = productService.findProductsByName(this.txtProducto.getText());
                //Obtener los productos del vendedor
                productsVendedor = getProductsVendedor(products);
                //Llenar tabla
                fillTableCategory(productsVendedor);
            }
            txtProducto.setText("");
            
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                "Envia la informacion correspondiente",
                "Error tipo de dato",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Realiza la acción de cambiar al estado de eliminación.
     * Este método es llamado cuando se hace clic en el botón "Borrar".
     *
     * @param evt El evento que desencadena la acción (clic en el botón "Borrar").
     */
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        stateDelete();
        txtProducto.requestFocus();
    }//GEN-LAST:event_btnBorrarActionPerformed

    /**
     * Realiza la acción de cambiar al estado de edición.
     * Este método es llamado cuando se hace clic en el botón "Editar".
     *
     * @param evt El evento que desencadena la acción (clic en el botón "Editar").
     */
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        productService.registerObserver(jpEditar);
        categoryService.registerObserver(jpEditar);
        
        jpEditar.setSize(700, 600);
        jpEditar.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(jpEditar, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarTodos;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JTable tblPrincipal;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Filtra la lista de productos para obtener solo los productos del vendedor.
     *
     * @param products La lista de productos.
     * @return Una lista que contiene solo los productos del vendedor.
     */
    private List<Product> getProductsVendedor(List<Product> products){
        List<Product> productsVendedor = new ArrayList<>(); 
        
        for(int i = 0; i < products.size();i++){
            if (products.get(i).getVendedorId() == vendedor.getId()) {
                productsVendedor.add(products.get(i));
            }
        }
        return productsVendedor;
    }
    
    /**
     * Valida si un producto pertenece al vendedor actual.
     *
     * @param productVendedor El producto a verificar.
     * @return true si el producto pertenece al vendedor actual, false en caso contrario.
     */
    private boolean isProductVendedor(Product productVendedor) {
        boolean bandera = false;
        if (productVendedor.getVendedorId() == vendedor.getId()) {
            bandera = true;
        }
        return bandera;
    }

    /**
     * Establece el estado inicial de la interfaz.
     */
    private void stateInitial() {
       
        btnEditar.setVisible(true);
        btnBuscar.setVisible(true);
        btnBuscarTodos.setVisible(true);
        btnBorrar.setVisible(true);
        btnEliminar.setVisible(false);
        btnVolver.setVisible(false);
        cmbBuscar.setSelectedItem("Id");
        cmbBuscar.setEnabled(true);

    }
    
    /**
     * Establece el estado de eliminación en la interfaz.
     */
    private void stateDelete() {
        
        btnEditar.setVisible(false);
        btnBuscar.setVisible(false);
        btnBorrar.setVisible(false);
        btnEliminar.setVisible(true);
        btnVolver.setVisible(true);
        cmbBuscar.setSelectedItem("Id");
        cmbBuscar.setEnabled(false);
    }
    
    /**
     * Actualiza la vista de la interfaz.
     */
    @Override
    public void update() {
        try {
            //Encontrar todos los productos
            List<Product> products = new ArrayList<>();
            products = productService.findAllProducts();
            
            //Filtrar los productos del vendedor
            List<Product> productsVendedor = new ArrayList<>();
            productsVendedor = getProductsVendedor(products);
            
            fillTable(productsVendedor);
        } catch (Exception ex) {
            Logger.getLogger(JpPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
