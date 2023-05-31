/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.comprador;

import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.Buy;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hewlett Packard
 */
public class JPTusCompras extends javax.swing.JPanel {
    private BuyService buyService;
    private ProductService productService;
    private User user;
    
    public JPTusCompras(BuyService buyService, ProductService productService, User user) throws Exception {
        initComponents();
        this.buyService = buyService;
        this.productService = productService;
        llenarTablaEntregas(buyService.findBuyByCom(user.getId()));
    }

    private void initializeTable() {
        tblEntregas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Nombre Producto", "Precio", "Fecha entrega"
                }
        ));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodo para llenar la tabla de confirmacion">
    public void llenarTablaEntregas(List<Buy> lstBuys)throws Exception{
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblEntregas.getModel();
        
        Object rowData[] = new Object[4];
        for(int i = 0; i < lstBuys.size(); i++){
            String estado = lstBuys.get(i).getEstado();
            if(estado.equals("Entregada")){
                //Se obtiene el producto de la venta
                Product product = productService.findProductById(lstBuys.get(i).getProductoId());
                
                rowData[0] = lstBuys.get(i).getId();
                rowData[1] = product.getName();
                rowData[2] = product.getPrice();
                rowData[3] = lstBuys.get(i).getFechaCompra();
                
                model.addRow(rowData);
            }
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

        JPCentralTusCompras = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntregas = new javax.swing.JTable();
        lblTabla = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPCentralTusCompras.setBackground(new java.awt.Color(61, 64, 91));
        JPCentralTusCompras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(242, 204, 143));
        lblTitulo.setText("CONSULTAR TUS COMPRAS");
        JPCentralTusCompras.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        tblEntregas.setBackground(new java.awt.Color(255, 255, 255));
        tblEntregas.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre Producto", "Precio", "Fecha Entrega"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntregas.setGridColor(new java.awt.Color(255, 255, 255));
        tblEntregas.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblEntregas);

        JPCentralTusCompras.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 640, 200));

        lblTabla.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        lblTabla.setForeground(new java.awt.Color(255, 255, 255));
        lblTabla.setText("Compras realizadas en la plataforma:");
        JPCentralTusCompras.add(lblTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        add(JPCentralTusCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 600));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPCentralTusCompras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTabla;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblEntregas;
    // End of variables declaration//GEN-END:variables
}
