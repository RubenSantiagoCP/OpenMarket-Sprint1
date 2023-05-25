package co.unicauca.openmarket.client.presentation.vendedor;

/**
 *
 * @author juan
 */
public class JpPrincipal extends javax.swing.JPanel {
    
    private javax.swing.JPanel jpContent;
    /**
     * Creates new form jpPrincipal
     */
    public JpPrincipal(javax.swing.JPanel jpContent) {
        this.jpContent = jpContent;
        initComponents();  
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
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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
        jpPrincipal.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 127, 100, -1));

        btnBorrar.setBackground(new java.awt.Color(224, 122, 95));
        btnBorrar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 127, 100, -1));

        btnBuscar.setBackground(new java.awt.Color(224, 122, 95));
        btnBuscar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jpPrincipal.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 127, 100, -1));
        jpPrincipal.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 191, 179, -1));

        cmbBuscar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Nombre" }));
        jpPrincipal.add(cmbBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 189, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jpPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 235, 546, 316));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        JpEditar jpE = new JpEditar(jpContent,this);
        jpE.setSize(700, 600);
        jpE.setLocation(0, 0);
        
        jpContent.removeAll();
        jpContent.add(jpE, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        JpBorrar jpB = new JpBorrar(jpContent,this);
        jpB.setSize(700, 600);
        jpB.setLocation(0, 0);
        
        jpContent.removeAll();
        jpContent.add(jpB, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
