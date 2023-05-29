/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.comprador;

import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import co.unicauca.openmarket.commons.domain.Buy;
import java.security.MessageDigest;
import java.util.Date;

/**
 *
 * @author Hewlett Packard
 */
public class JPConfirmarE extends javax.swing.JPanel {

    private BuyService buyService;
    private ProductService productService;
    private CategoryService categoryService;
    private Invoker invoker;
    
    //Constructor
    public JPConfirmarE(ProductService productService, CategoryService categoryService, BuyService buyService) {
        initComponents();
        this.productService = productService;
        this.categoryService = categoryService;
        this.buyService = buyService;
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo auxiliar para seleccionar producto">
    private void rowSelection(){
        try {
            int selection= tblConfirmarE.getSelectedRow();
            String id = tblConfirmarE.getValueAt(selection, 0).toString();
            String name = tblConfirmarE.getValueAt(selection, 1).toString();
            String price = tblConfirmarE.getValueAt(selection, 2).toString();
            lblInformacion.setText("¿Desea confirmar la entrega del producto "+name+" con id "+id+"?");
            
            //Creacion de la compra
            Buy newBuy = new Buy();
            newBuy.setCompradorId(Long.MIN_VALUE);
            newBuy.setProductoId(Long.parseLong(id));
            newBuy.setEstado("Realizada");
            Date fechaActual = new Date();
            newBuy.setFechaCompra(fechaActual.toString());
        } catch (Exception e) {
            lblInformacion.setText("Seleccione la compra que desea confirmar");
        }
        
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Metodo para editar estado de compra">   
    private void editBuy() throws Exception{
        int selection = tblConfirmarE.getSelectedRow();
        String id = tblConfirmarE.getValueAt(selection, 0).toString();
        
        //Obtener el producto seleccionado en la tabla con id
        Buy buy = buyService.findBuyById(Long.parseLong(id));
        //Se cambia el estado de compra a entregada para finalizar todo el proceso
        buy.setEstado("Entregada");
        
        if(buyService.editBuy(Long.parseLong(id), buy)){
            Messages.showMessageDialog("El proceso de compra finalizo con exito", "Gracias por su compra");
        }else{
            Messages.showMessageDialog("Hubo un problema al confirmar la compra", "Atencion");
        }
    }
    //</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPCentralConfirmarE = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConfirmarE = new javax.swing.JTable();
        lblInformacion = new javax.swing.JLabel();
        btnConfirmarE = new javax.swing.JButton();
        lblTabla1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPCentralConfirmarE.setBackground(new java.awt.Color(61, 64, 91));
        JPCentralConfirmarE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(242, 204, 143));
        lblTitulo.setText("CONFIRMAR ENTREGA");
        JPCentralConfirmarE.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        tblConfirmarE.setBackground(new java.awt.Color(255, 255, 255));
        tblConfirmarE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblConfirmarE.setGridColor(new java.awt.Color(255, 255, 255));
        tblConfirmarE.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tblConfirmarE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblConfirmarE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConfirmarEMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConfirmarE);

        JPCentralConfirmarE.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 590, 190));

        lblInformacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblInformacion.setForeground(new java.awt.Color(255, 255, 255));
        lblInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformacion.setText("Seleccione la compra que desea confirmar");
        JPCentralConfirmarE.add(lblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 330, -1));

        btnConfirmarE.setBackground(new java.awt.Color(242, 204, 143));
        btnConfirmarE.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        btnConfirmarE.setForeground(new java.awt.Color(224, 122, 95));
        btnConfirmarE.setText("Confirmar Entrega");
        btnConfirmarE.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConfirmarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarEActionPerformed(evt);
            }
        });
        JPCentralConfirmarE.add(btnConfirmarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 140, 30));

        lblTabla1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        lblTabla1.setForeground(new java.awt.Color(255, 255, 255));
        lblTabla1.setText("Seleccione los productos que ya le han sido entregados");
        JPCentralConfirmarE.add(lblTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        add(JPCentralConfirmarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarEActionPerformed

    private void tblConfirmarEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConfirmarEMouseClicked
        rowSelection();
    }//GEN-LAST:event_tblConfirmarEMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPCentralConfirmarE;
    private javax.swing.JButton btnConfirmarE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblTabla1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblConfirmarE;
    // End of variables declaration//GEN-END:variables
}
