/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.comprador;

import co.unicauca.openmaket.client.command.AddBuyCommand;
import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hewlett Packard
 */
public class JPBuscarProductos extends javax.swing.JPanel {

    private BuyService buyService;
    private ProductService productService;
    private CategoryService categoryService;
    private User user;
    private Invoker invoker;

    //Constructor
    public JPBuscarProductos(ProductService productService, CategoryService categoryService, BuyService buyService, User user) {
        initComponents();
        this.productService = productService;
        this.categoryService = categoryService;
        this.buyService = buyService;
        this.user = user;
        this.invoker = new Invoker();

        //<editor-fold defaultstate="collapsed" desc="Metodo auxiliar para seleccionar filas">
        /*tblProductosO.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent mouseEvent){
                try {
                    JTable table = (JTable) mouseEvent.getSource();
                    Point point = mouseEvent.getPoint();    //permite conocer el punto del click
                    int numRow = table.rowAtPoint(point);
                    
                   //Condicional para contar numero de clicks en una fila
                    if(mouseEvent.getClickCount() == 1){
                    String id = tblProductosO.getValueAt(tblProductosO.getSelectedRow(), 0).toString();
                    productSelect = Long.parseLong(id);
                    String name = tblProductosO.getValueAt(tblProductosO.getSelectedRow(), 1).toString();
                    lblInformacion.setText("¿Desea comprar el producto "+name+" con id "+id+"?");
                    }
                } catch (Exception e) {
                    lblInformacion.setText("Seleccione un producto a comprar");
                }
                
            }
            
        });*/
        //</editor-fold>
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo auxiliar para seleccionar producto">
    private void rowSelection() {
        try {
            int selection = tblProductosO.getSelectedRow();
            String id = tblProductosO.getValueAt(selection, 0).toString();
            String name = tblProductosO.getValueAt(selection, 1).toString();
            lblInformacion.setText("¿Desea comprar el producto " + name + " con id " + id + "?");

            //Creacion de la compra
            Buy newBuy = new Buy();
            newBuy.setCompradorId(Long.MIN_VALUE);
            newBuy.setProductoId(Long.parseLong(id));
            newBuy.setEstado("Realizada");
            Date fechaActual = new Date();
            newBuy.setFechaCompra(fechaActual.toString());
        } catch (Exception e) {
            lblInformacion.setText("Seleccione un producto a comprar");
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para imprimir todos lo productos">
    private void fillTable(List<Product> lstProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProductosO.getModel();
        Object rowData[] = new Object[4];

        for (int i = 0; i < lstProducts.size(); i++) {
            rowData[0] = lstProducts.get(i).getProductId();
            rowData[1] = lstProducts.get(i).getName();
            rowData[2] = lstProducts.get(i).getPrice();
            //Para buscar nombre categoria
            Long idCat = lstProducts.get(i).getCategoryId();
            String nameCat = categoryService.findCategoryById(idCat).getName();
            rowData[3] = nameCat;

            model.addRow(rowData);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para imprimir con Id">
    private void fillTableId(Product product) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProductosO.getModel();
        Object rowData[] = new Object[4];

        rowData[0] = product.getProductId();
        rowData[1] = product.getName();
        rowData[2] = product.getPrice();
        //Para buscar nombre categoria
        Long idCat = product.getCategoryId();
        String nameCat = categoryService.findCategoryById(idCat).getName();
        rowData[3] = nameCat;

        model.addRow(rowData);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para imprimir con Categoria">
    private void fillTableCategory(List<Product> lstProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProductosO.getModel();
        Object rowData[] = new Object[4];

        for (int i = 0; i < lstProducts.size(); i++) {
            rowData[0] = lstProducts.get(i).getProductId();
            rowData[1] = lstProducts.get(i).getName();
            rowData[2] = lstProducts.get(i).getPrice();
            //Para buscar nombre categoria
            Long idCat = lstProducts.get(i).getCategoryId();
            String nameCat = categoryService.findCategoryById(idCat).getName();
            rowData[3] = nameCat;

            model.addRow(rowData);
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para imprimir con Nombre">
    private void fillTableName(List<Product> lstProducts) throws Exception {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProductosO.getModel();
        Object rowData[] = new Object[4];

        for (int i = 0; i < lstProducts.size(); i++) {
            rowData[0] = lstProducts.get(i).getProductId();
            rowData[1] = lstProducts.get(i).getName();
            rowData[2] = lstProducts.get(i).getPrice();
            //Para buscar nombre categoria
            Long idCat = lstProducts.get(i).getCategoryId();
            String nameCat = categoryService.findCategoryById(idCat).getName();
            rowData[3] = nameCat;

            model.addRow(rowData);
        }

    }
    //</editor-fold>

    private Long cantBuy() throws Exception {
        try {
            List<Buy> lstBuys = buyService.findAllBuys();
            
            return Long.valueOf(lstBuys.size());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Esta sera la primera compra que se realiza", JOptionPane.ERROR_MESSAGE);
        }
        return 0L;
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para crear compra">
    private void addBuy() {
        try {
            int selection = tblProductosO.getSelectedRow();

            //Creacion de la compra
            Buy newBuy = new Buy();
            newBuy.setId(cantBuy() + 1L);
            newBuy.setCompradorId(user.getId());
            String id = tblProductosO.getValueAt(selection, 0).toString();
            newBuy.setProductoId(Long.parseLong(id));
            newBuy.setEstado("Realizada");
            Date fechaActual = new Date();
            newBuy.setFechaCompra(fechaActual.toString());

            AddBuyCommand comm = new AddBuyCommand(buyService, newBuy);

            if (invoker.executeCommand(comm)) {
                Messages.showMessageDialog("La compra se grabo con exito", "Atencion");
            } else {
                Messages.showMessageDialog("No fue posible realizar la compra", "Cuidado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al grabar la compra", JOptionPane.ERROR_MESSAGE);
        }
    }
    //</editor-fold>

    private void initializeTable() {
        tblProductosO.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Nombre Producto", "Precio", "Categoria"
                }
        ));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPCentralBuscarP = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblBuscarPor = new javax.swing.JLabel();
        cbxTipoBusqueda = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosO = new javax.swing.JTable();
        btnCrearCompra = new javax.swing.JButton();
        lblInformacion = new javax.swing.JLabel();
        lblMensajeVI1 = new javax.swing.JLabel();
        lblMensajeVI2 = new javax.swing.JLabel();
        btnBuscarTodos = new javax.swing.JButton();
        txtValorIngresado1 = new javax.swing.JTextField();
        txtValorIngresado2 = new javax.swing.JTextField();
        btnBuscarP = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPCentralBuscarP.setBackground(new java.awt.Color(61, 64, 91));
        JPCentralBuscarP.setForeground(new java.awt.Color(61, 64, 91));
        JPCentralBuscarP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(242, 204, 143));
        lblTitulo.setText("BUSCAR PRODUCTOS");
        JPCentralBuscarP.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        lblBuscarPor.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        lblBuscarPor.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarPor.setText("Buscar por:");
        JPCentralBuscarP.add(lblBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        cbxTipoBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        cbxTipoBusqueda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxTipoBusqueda.setForeground(new java.awt.Color(224, 122, 95));
        cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Nombre", "Identificador", "Categoria", "Precio" }));
        cbxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoBusquedaActionPerformed(evt);
            }
        });
        JPCentralBuscarP.add(cbxTipoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 320, 30));

        tblProductosO.setBackground(new java.awt.Color(255, 255, 255));
        tblProductosO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Precio", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosO.setGridColor(new java.awt.Color(255, 255, 255));
        tblProductosO.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tblProductosO.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProductosO.setShowGrid(false);
        tblProductosO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosOMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductosO);

        JPCentralBuscarP.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 560, 200));

        btnCrearCompra.setBackground(new java.awt.Color(242, 204, 143));
        btnCrearCompra.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnCrearCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCompra.setText("Comprar producto seleccionado");
        btnCrearCompra.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCrearCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCompraActionPerformed(evt);
            }
        });
        JPCentralBuscarP.add(btnCrearCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, 210, 30));

        lblInformacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblInformacion.setForeground(new java.awt.Color(255, 255, 255));
        lblInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformacion.setText("Seleccione un producto a comprar");
        JPCentralBuscarP.add(lblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 320, -1));

        lblMensajeVI1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        lblMensajeVI1.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajeVI1.setText("Ingresar Datos");
        JPCentralBuscarP.add(lblMensajeVI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 150, 30));

        lblMensajeVI2.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        lblMensajeVI2.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajeVI2.setText("Ingresar Datos");
        JPCentralBuscarP.add(lblMensajeVI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 150, 30));

        btnBuscarTodos.setBackground(new java.awt.Color(242, 204, 143));
        btnBuscarTodos.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnBuscarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarTodos.setText("Buscar Todos");
        btnBuscarTodos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTodosActionPerformed(evt);
            }
        });
        JPCentralBuscarP.add(btnBuscarTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 110, 30));

        txtValorIngresado1.setBackground(new java.awt.Color(255, 255, 255));
        txtValorIngresado1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JPCentralBuscarP.add(txtValorIngresado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 160, 30));

        txtValorIngresado2.setBackground(new java.awt.Color(255, 255, 255));
        txtValorIngresado2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JPCentralBuscarP.add(txtValorIngresado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 160, 30));

        btnBuscarP.setBackground(new java.awt.Color(242, 204, 143));
        btnBuscarP.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnBuscarP.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarP.setText("Buscar");
        btnBuscarP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });
        JPCentralBuscarP.add(btnBuscarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 110, 30));

        add(JPCentralBuscarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaActionPerformed
        //<editor-fold defaultstate="collapsed" desc="Logica para controlar entrada para metodos de busqueda">
        String seleccion = cbxTipoBusqueda.getSelectedItem().toString();

        if (seleccion.equals("Seleccionar")) {
            estadoInicial();
        } else {
            btnBuscarP.setVisible(true);
            if (seleccion.equals("Precio")) {
                txtValorIngresado1.setVisible(true);
                lblMensajeVI1.setVisible(true);
                lblMensajeVI1.setText("Ingrese rango inferior");

                txtValorIngresado2.setVisible(true);
                lblMensajeVI2.setVisible(true);
                lblMensajeVI2.setText("Ingrese rango superior");
            } else {
                txtValorIngresado1.setVisible(true);
                lblMensajeVI1.setVisible(true);
                lblMensajeVI1.setText("Ingrese " + seleccion);
                txtValorIngresado2.setVisible(false);
                lblMensajeVI2.setVisible(false);
            }
        }
        //</editor-fold>
    }//GEN-LAST:event_cbxTipoBusquedaActionPerformed

    private void btnCrearCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCompraActionPerformed
        try {
            //Agregar o crear compra
            addBuy();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearCompraActionPerformed

    private void btnBuscarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTodosActionPerformed
        try {
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            Logger.getLogger(JPBuscarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarTodosActionPerformed

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        String seleccion = cbxTipoBusqueda.getSelectedItem().toString();
        boolean band = false;

        try {
            //<editor-fold defaultstate="collapsed" desc="Validacion de campos de texto">
            if (seleccion.equals("Precio")) {
                if (!txtValorIngresado1.getText().trim().isBlank()
                        && !txtValorIngresado2.getText().trim().isBlank()) {
                    band = true;
                }
            } else {
                if (!txtValorIngresado1.getText().trim().isBlank()) {
                    band = true;
                }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Llamado de metodos segun Seleccion">
            if (band) {
                if (seleccion.equals("Identificador")) {

                    Long id = Long.valueOf(txtValorIngresado1.getText());
                    fillTableId(productService.findProductById(id));

                } else if (seleccion.equals("Nombre")) {

                    fillTableName(productService.findProductsByName(txtValorIngresado1.getText()));

                } else if (seleccion.equals("Categoria")) {

                    fillTableCategory(productService.findProductsByCategory(txtValorIngresado1.getText()));

                } else if (seleccion.equals("Precio")) {

                    Long minPrice = Long.parseLong(txtValorIngresado1.getText());
                    Long maxPrice = Long.parseLong(txtValorIngresado2.getText());
                    //Nota: se uso solo del metodo fillTable para imprimir y 
                    //ver si puede remplazar a fillTableName y fillTableCategory
                    fillTable(productService.findProductsByPrice(minPrice, maxPrice));

                }
            } else {
                JOptionPane.showMessageDialog(null, "El texto no debe estar vacio",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            //</editor-fold>
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Envia la informacion correspondiente",
                    "Error tipo de dato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void tblProductosOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosOMouseClicked
        rowSelection();
    }//GEN-LAST:event_tblProductosOMouseClicked

    private void estadoInicial() {
        txtValorIngresado1.setVisible(false);
        txtValorIngresado1.setText(" ");
        lblMensajeVI1.setVisible(false);

        txtValorIngresado2.setVisible(false);
        txtValorIngresado2.setText(" ");
        lblMensajeVI2.setVisible(false);

        btnBuscarP.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPCentralBuscarP;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnBuscarTodos;
    private javax.swing.JButton btnCrearCompra;
    private javax.swing.JComboBox<String> cbxTipoBusqueda;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuscarPor;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblMensajeVI1;
    private javax.swing.JLabel lblMensajeVI2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblProductosO;
    private javax.swing.JTextField txtValorIngresado1;
    private javax.swing.JTextField txtValorIngresado2;
    // End of variables declaration//GEN-END:variables
}
