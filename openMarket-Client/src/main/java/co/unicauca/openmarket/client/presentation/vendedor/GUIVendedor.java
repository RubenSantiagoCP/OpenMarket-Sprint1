package co.unicauca.openmarket.client.presentation.vendedor;

import co.unicauca.openmaket.client.command.Invoker;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.observer.Observer;

/**
 *
 * @author juan
 */
public class GUIVendedor extends javax.swing.JFrame implements Observer{
    
    private ProductService productService;
    private static CategoryService categoryService;
    private static Invoker invoker;
    private static JpPrincipal jpPrincipal;
    private static JpAgregarProducto jpAgregar;
    private static JpCategoria jpCategoria;
    
    /** Creates new form GUIVendedor */
    public GUIVendedor(ProductService productService, CategoryService categoryService) {
        initComponents();
        
        this.productService = productService;
        this.categoryService = categoryService;
        invoker = new Invoker();
        invoker.registerObserver(this);
        
        
        //Panel principal
        jpPrincipal = new JpPrincipal(jpContent, productService, categoryService, invoker);
        productService.registerObserver(jpPrincipal);
        categoryService.registerObserver(jpPrincipal);
        jpPrincipal.setSize(700, 600);
        jpPrincipal.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(jpPrincipal, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
        
        //Panel Agregar producto
        jpAgregar = new JpAgregarProducto(jpContent, productService, categoryService, invoker);
        productService.registerObserver(jpAgregar);
        categoryService.registerObserver(jpAgregar);
        
        //Panel Agregar producto
        jpCategoria = new JpCategoria(jpContent, productService, categoryService, invoker);
        productService.registerObserver(jpAgregar);
        categoryService.registerObserver(jpAgregar);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpGestionVendedor = new javax.swing.JPanel();
        btnAgregarCategoria = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        btnAgregarUbicacion = new javax.swing.JButton();
        btnVerProductos = new javax.swing.JButton();
        lbVendedor = new javax.swing.JLabel();
        jpContent = new javax.swing.JPanel();
        btnPrincipal = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGestionVendedor.setBackground(new java.awt.Color(224, 122, 95));
        jpGestionVendedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarCategoria.setBackground(new java.awt.Color(242, 204, 143));
        btnAgregarCategoria.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAgregarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCategoria.setText("Categoria");
        btnAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCategoriaActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnAgregarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 140, -1));

        btnAgregarProducto.setBackground(new java.awt.Color(242, 204, 143));
        btnAgregarProducto.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProducto.setText("Añadir Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 140, -1));

        btnAgregarUbicacion.setBackground(new java.awt.Color(242, 204, 143));
        btnAgregarUbicacion.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAgregarUbicacion.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarUbicacion.setText("Añadir Locación");
        btnAgregarUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUbicacionActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnAgregarUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 140, -1));

        btnVerProductos.setBackground(new java.awt.Color(242, 204, 143));
        btnVerProductos.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnVerProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerProductos.setText("Ver mis productos");
        btnVerProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerProductosActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnVerProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 140, -1));

        lbVendedor.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lbVendedor.setForeground(new java.awt.Color(255, 255, 255));
        lbVendedor.setText("Vendedor");
        jpGestionVendedor.add(lbVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jpContent.setBackground(new java.awt.Color(61, 64, 91));

        javax.swing.GroupLayout jpContentLayout = new javax.swing.GroupLayout(jpContent);
        jpContent.setLayout(jpContentLayout);
        jpContentLayout.setHorizontalGroup(
            jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jpContentLayout.setVerticalGroup(
            jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jpGestionVendedor.add(jpContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 700, 600));

        btnPrincipal.setBackground(new java.awt.Color(242, 204, 143));
        btnPrincipal.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        btnPrincipal.setText("Inicio");
        btnPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 140, -1));

        btnRehacer.setText("Rehacer");
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnRehacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        btnDeshacer.setText("Deshacer");
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });
        jpGestionVendedor.add(btnDeshacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, -1, -1));

        getContentPane().add(jpGestionVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaActionPerformed

        productService.registerObserver(jpCategoria);
        categoryService.registerObserver(jpCategoria);
        
        jpCategoria.setSize(700, 600);
        jpCategoria.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(jpCategoria, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnAgregarCategoriaActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed

        productService.registerObserver(jpAgregar);
        categoryService.registerObserver(jpAgregar);
        
        jpAgregar.setSize(700, 600);
        jpAgregar.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(jpAgregar, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnAgregarUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUbicacionActionPerformed

    private void btnVerProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerProductosActionPerformed

    private void btnPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalActionPerformed

        productService.registerObserver(jpPrincipal);
        categoryService.registerObserver(jpPrincipal);
        
        jpPrincipal.setSize(700, 600);
        jpPrincipal.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(jpPrincipal, new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnPrincipalActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        invoker.redoLastCommand();
        if (!invoker.hasMoreRedoCommands()) {
            this.btnRehacer.setVisible(false);
        }
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        invoker.undoLastCommand();
        if (!invoker.hasMoreUndoCommands()) {
            this.btnDeshacer.setVisible(false);
        }
    }//GEN-LAST:event_btnDeshacerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCategoria;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAgregarUbicacion;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnPrincipal;
    private javax.swing.JButton btnRehacer;
    private javax.swing.JButton btnVerProductos;
    private javax.swing.JPanel jpContent;
    private javax.swing.JPanel jpGestionVendedor;
    private javax.swing.JLabel lbVendedor;
    // End of variables declaration//GEN-END:variables

    
    
    @Override
    public void update() {
  
            btnDeshacer.setVisible(invoker.hasMoreUndoCommands());
            btnRehacer.setVisible(invoker.hasMoreRedoCommands());
        
    }
}
