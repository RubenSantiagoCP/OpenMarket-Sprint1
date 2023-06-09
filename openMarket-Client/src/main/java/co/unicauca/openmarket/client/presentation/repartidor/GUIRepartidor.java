/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.repartidor;

import co.unicauca.openmarket.client.domain.service.BuyService;
import java.awt.BorderLayout;

/**
 *
 * @author SANTIAGO
 */
public class GUIRepartidor extends javax.swing.JFrame {

    /**
     * Creates new form GUIRepartidor
     */
    private BuyService buyService; 
    public GUIRepartidor(BuyService buyService) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.buyService = buyService;
        jpRecogerProducto vtnRecogerProducto = new jpRecogerProducto(buyService);
        vtnRecogerProducto .setSize(700, 600);
        vtnRecogerProducto .setLocation(0,0);

        pnlCentral.removeAll();
        pnlCentral.add(vtnRecogerProducto , BorderLayout.CENTER);
        pnlCentral.revalidate();
        pnlCentral.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlLateral = new javax.swing.JPanel();
        btnRecoger = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnlCentral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlLateral.setBackground(new java.awt.Color(224, 122, 95));

        btnRecoger.setBackground(new java.awt.Color(242, 204, 143));
        btnRecoger.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnRecoger.setForeground(new java.awt.Color(255, 255, 255));
        btnRecoger.setText("Recoger productos");
        btnRecoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecogerActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Repartidor");

        javax.swing.GroupLayout pnlLateralLayout = new javax.swing.GroupLayout(pnlLateral);
        pnlLateral.setLayout(pnlLateralLayout);
        pnlLateralLayout.setHorizontalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnRecoger)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLateralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(63, 63, 63))
        );
        pnlLateralLayout.setVerticalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(btnRecoger, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );

        getContentPane().add(pnlLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 600));

        pnlCentral.setBackground(new java.awt.Color(61, 64, 91));

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(pnlCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 660, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecogerActionPerformed
        jpRecogerProducto vtnRecoger = new jpRecogerProducto(buyService);

        vtnRecoger.setSize(700, 600);
        vtnRecoger.setLocation(0,0);

        pnlCentral.removeAll();
        pnlCentral.add(vtnRecoger, BorderLayout.CENTER);
        pnlCentral.revalidate();
        pnlCentral.repaint();
    }//GEN-LAST:event_btnRecogerActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecoger;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlLateral;
    // End of variables declaration//GEN-END:variables
}
