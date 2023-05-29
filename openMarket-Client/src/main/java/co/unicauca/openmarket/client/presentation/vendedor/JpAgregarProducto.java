package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmaket.client.command.AddProductCommand;
import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.observer.Observer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan
 */
public class JpAgregarProducto extends javax.swing.JPanel implements Observer{
    
    private javax.swing.JPanel jpContent;
    private ProductService productService;
    private CategoryService categoryService;
    private Invoker invoker;
    private Long vendedorId;
    
    /**
     * Creates new form JpAgregarProducto
     */
    public JpAgregarProducto(javax.swing.JPanel parent, ProductService productService, CategoryService categoryService, Invoker invoker, Long vendedorId) {
        initComponents();  
        initializeTable();
        this.productService = productService;
        this.categoryService = categoryService;
        this.invoker = new Invoker();
        this.invoker.registerObserver(this);
        this.jpContent = parent;
        this.vendedorId = vendedorId;
    }

    
    private void initializeTable() {
        tblAgregarProducto.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Description", "Precio", "Categoria"
                }
        ));
    }
    
    private void fillTable(List<Product> listProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblAgregarProducto.getModel();
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIdProducto = new javax.swing.JLabel();
        lbNombreProducto = new javax.swing.JLabel();
        lbPrecio = new javax.swing.JLabel();
        lbIdCategoria = new javax.swing.JLabel();
        lbDescripcion = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtIdCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtADescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAgregarProducto = new javax.swing.JTable();

        setBackground(new java.awt.Color(61, 64, 91));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbIdProducto.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        lbIdProducto.setText("ID");
        add(lbIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        lbNombreProducto.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbNombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        lbNombreProducto.setText("Nombre");
        add(lbNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        lbPrecio.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lbPrecio.setText("Precio");
        add(lbPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        lbIdCategoria.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbIdCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lbIdCategoria.setText("Id Categoria");
        add(lbIdCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        lbDescripcion.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lbDescripcion.setText("Descripción");
        add(lbDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));
        add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 37, 223, -1));
        add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 83, 223, -1));
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 131, 223, -1));
        add(txtIdCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 178, 223, -1));

        txtADescripcion.setColumns(20);
        txtADescripcion.setRows(5);
        jScrollPane1.setViewportView(txtADescripcion);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, 74));

        btnGuardar.setBackground(new java.awt.Color(224, 122, 95));
        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 131, 100, -1));

        btnDeshacer.setBackground(new java.awt.Color(224, 122, 95));
        btnDeshacer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnDeshacer.setForeground(new java.awt.Color(255, 255, 255));
        btnDeshacer.setText("Deshacer");
        add(btnDeshacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 210, 100, -1));

        tblAgregarProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblAgregarProducto);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 362, 547, 225));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNombreProducto.getText().trim().equals("")
                || txtIdCategoria.getText().trim().equals("")
                || txtADescripcion.getText().trim().equals("")
                || txtPrecio.getText().trim().equals("")
                || txtIdProducto.getText().trim().equals("")) {
            Messages.showMessageDialog("Debe llenar todos los datos", "Atención");
            txtNombreProducto.requestFocus();
            return;
        }

        try {
            //Agregar
            addProduct();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbIdCategoria;
    private javax.swing.JLabel lbIdProducto;
    private javax.swing.JLabel lbNombreProducto;
    private javax.swing.JLabel lbPrecio;
    private javax.swing.JTable tblAgregarProducto;
    private javax.swing.JTextArea txtADescripcion;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

        private void addProduct() {
        try {
            String id = txtIdProducto.getText().trim();
            String name = txtNombreProducto.getText().trim();
            String description = txtADescripcion.getText().trim();
            Long categoryId = Long.valueOf(this.txtIdCategoria.getText().trim());
            Double price = Double.valueOf(txtPrecio.getText().trim());
            Product prod = new Product(Long.valueOf(id), name, description, price, categoryId, vendedorId);

            AddProductCommand comm = new AddProductCommand(productService, prod);

            if (invoker.executeCommand(comm)) {
                Messages.showMessageDialog("Se grabó con éxito", "Atención");

                cleanControls();

            } else {
                Messages.showMessageDialog("El id ya existe,ingrese otro", "Error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
        
    private void cleanControls() {
        txtIdProducto.setText("");
        txtNombreProducto.setText("");
        txtADescripcion.setText("");
        txtIdCategoria.setText("");
        txtPrecio.setText("");
    }

    @Override
    public void update() {
        try {
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            Logger.getLogger(GUIVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
