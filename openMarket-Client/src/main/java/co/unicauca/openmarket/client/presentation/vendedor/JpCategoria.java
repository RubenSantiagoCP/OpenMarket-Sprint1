package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.client.presentation.GUICategoriesFind;
import co.unicauca.openmarket.commons.domain.Category;
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
public class JpCategoria extends javax.swing.JPanel implements Observer{
    
    private javax.swing.JPanel jpContent;
    private ProductService productService;
    private CategoryService categoryService;
    private Invoker invoker;
    
    /**
     * Creates new form jpAgregarCategoria
     */
    public JpCategoria(javax.swing.JPanel parent, ProductService productService, CategoryService categoryService, Invoker invoker) {
        initComponents();  
        initializeTable();
        this.productService = productService;
        this.categoryService = categoryService;
        this.invoker = new Invoker();
        this.invoker.registerObserver(this);
        this.jpContent = parent;
        
        update();
        stateInitial();
    }
    
    private void initializeTable() {
        tblAgregarCategoria.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name"
                }
        ));
    }
  
    
    private void fillTable(List<Category> listCategories) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblAgregarCategoria.getModel();

        Object rowData[] = new Object[2];//No columnas
        for (int i = 0; i < listCategories.size(); i++) {
            rowData[0] = listCategories.get(i).getCategoryId();
            rowData[1] = listCategories.get(i).getName();
            
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

        lbIdCategoria = new javax.swing.JLabel();
        lbNombreCategoria = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        txtNombreCategoria = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgregarCategoria = new javax.swing.JTable();
        btnEditarCategoria = new javax.swing.JButton();
        btnGuardarEdicion = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setBackground(new java.awt.Color(61, 64, 91));
        setPreferredSize(new java.awt.Dimension(700, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbIdCategoria.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbIdCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lbIdCategoria.setText("ID");
        add(lbIdCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        lbNombreCategoria.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lbNombreCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lbNombreCategoria.setText("Nombre");
        add(lbNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));
        add(txtIdCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 170, -1));
        add(txtNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 170, -1));

        btnGuardar.setBackground(new java.awt.Color(224, 122, 95));
        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 100, -1));

        btnDeshacer.setBackground(new java.awt.Color(224, 122, 95));
        btnDeshacer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnDeshacer.setForeground(new java.awt.Color(255, 255, 255));
        btnDeshacer.setText("Deshacer");
        add(btnDeshacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 100, -1));

        tblAgregarCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAgregarCategoria);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 490, 240));

        btnEditarCategoria.setBackground(new java.awt.Color(224, 122, 95));
        btnEditarCategoria.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnEditarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCategoria.setText("Editar");
        btnEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCategoriaActionPerformed(evt);
            }
        });
        add(btnEditarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 100, -1));

        btnGuardarEdicion.setBackground(new java.awt.Color(224, 122, 95));
        btnGuardarEdicion.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnGuardarEdicion.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEdicion.setText("Terminar");
        btnGuardarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionActionPerformed(evt);
            }
        });
        add(btnGuardarEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 100, -1));

        btnVolver.setBackground(new java.awt.Color(224, 122, 95));
        btnVolver.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        addCategory();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionActionPerformed
        try {
            if (txtIdCategoria.getText().trim().equals("")) {
                Messages.showMessageDialog("Debe ingresar el id de la categoria", "Atención");
                txtIdCategoria.requestFocus();
                return;
            }
            if(txtNombreCategoria.getText().trim().equals("")){
                Messages.showMessageDialog("Debe ingresar el nombre de la categoria", "Atención");
                txtNombreCategoria.requestFocus();
                return;
            }
            //Editar
            editCategory();
        }catch(Exception e){
           
              JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarEdicionActionPerformed

    private void btnEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCategoriaActionPerformed
        stateEdit();
        txtIdCategoria.requestFocus();
    }//GEN-LAST:event_btnEditarCategoriaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        stateInitial();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEditarCategoria;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarEdicion;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIdCategoria;
    private javax.swing.JLabel lbNombreCategoria;
    private javax.swing.JTable tblAgregarCategoria;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNombreCategoria;
    // End of variables declaration//GEN-END:variables

    //agregar Categorias
    private void addCategory() {
        try{
            String name = this.txtNombreCategoria.getText().trim();
            Long id = Long.valueOf(this.txtIdCategoria.getText());
            if(categoryService.saveCategory(id, name)){
                Messages.showMessageDialog("Se grabo con exito","Atencion");
                update();
                cleanControls();
            }else{
                Messages.showMessageDialog("Error al grabar, lo siento mucho","Atencion");
            }
        }catch(Exception ex){
           successMessage(ex.getMessage(), "Atención");   
        }
     
    }
    
    //editar categorias
    private void editCategory() throws Exception {
        String id=this.txtIdCategoria.getText().trim();
        Long categoryId=Long.valueOf(id);
        Category cat=new Category();
        cat.setName(this.txtNombreCategoria.getText().trim());
        cat.setCategoryId(categoryId);
        if(categoryService.editCategory(categoryId,cat)){
            Messages.showMessageDialog("Se editó con éxito", "Atención");
            update();
            cleanControls();
        }else{
            Messages.showMessageDialog("Error al editar, lo siento mucho", "Atención");
        }
    }
    
    @Override
    public void update() {
        try {
            fillTable(categoryService.findAllCategories());
        } catch (Exception ex) {
            Logger.getLogger(GUICategoriesFind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   //para visibilizar los componentes
     private void stateInitial() {
        btnGuardar.setVisible(true);
        btnEditarCategoria.setVisible(true);
        btnDeshacer.setVisible(true);
        btnGuardarEdicion.setVisible(false);
        btnVolver.setVisible(false);
    }
    
     private void stateEdit() {
        btnGuardar.setVisible(false);
        btnEditarCategoria.setVisible(false);
        btnDeshacer.setVisible(false);
        btnGuardarEdicion.setVisible(true);
        btnVolver.setVisible(true);
     }
     
    //limpiar las cajas de texto
    private void cleanControls() {
        txtIdCategoria.setText("");
        txtNombreCategoria.setText("");
    }
    
}
