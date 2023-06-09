/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.inicioSesion;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.IBankAccountAccess;
import co.unicauca.openmarket.client.access.IBuyAccess;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.client.domain.service.BankAccountService;
import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.domain.service.UserService;
import co.unicauca.openmarket.client.infra.Messages;
import co.unicauca.openmarket.client.presentation.comprador.GUIComprador;
import co.unicauca.openmarket.client.presentation.repartidor.GUIRepartidor;
import co.unicauca.openmarket.client.presentation.vendedor.GUIVendedor;
import co.unicauca.openmarket.commons.domain.User;
import javax.swing.JOptionPane;

/**
 *
 * @author SANTIAGO
 */
public class GUILogin extends javax.swing.JFrame {

    /**
     * Creates new form GUILogin
     */
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private BuyService buyService;
    private BankAccountService bankService;
            
    public GUILogin() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        IProductAccess repository = Factory.getInstance().getProductAccess();
        ICategoryAccess repository2 =  Factory.getInstance().getCategoryAccess();
        IUserAccess repository3 = Factory.getInstance().getUserAccess();
        IBuyAccess repository4 = Factory.getInstance().getBuyAccess();
        IBankAccountAccess repository5 = Factory.getInstance().getBankAccountAccess();
        this.productService = new ProductService(repository);
        this.categoryService=new CategoryService(repository2);
        this.userService = new UserService(repository3);
        this.buyService = new BuyService(repository4);
        this.bankService = new BankAccountService(repository5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCentral = new javax.swing.JPanel();
        txtPassword = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        lblEmail = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblIniciarSesion = new javax.swing.JLabel();
        btnIngresarSinCuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(61, 64, 91));
        setForeground(new java.awt.Color(61, 64, 91));

        pnlCentral.setBackground(new java.awt.Color(61, 64, 91));

        txtEmail.setToolTipText("");

        btnIngresar.setBackground(new java.awt.Color(224, 122, 95));
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Ingrese el correo electrónico: ");

        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Contraseña:");

        lblIniciarSesion.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblIniciarSesion.setForeground(new java.awt.Color(242, 204, 143));
        lblIniciarSesion.setText("INICIAR SESIÓN");

        btnIngresarSinCuenta.setBackground(new java.awt.Color(224, 122, 95));
        btnIngresarSinCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresarSinCuenta.setText("Ingresar sin cuenta");
        btnIngresarSinCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarSinCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(lblImagen))
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword)
                            .addComponent(lblEmail)
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnIngresarSinCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblImagen)
                .addGap(58, 58, 58)
                .addComponent(lblIniciarSesion)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addGap(15, 15, 15)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addGap(9, 9, 9)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar)
                    .addComponent(btnIngresarSinCuenta))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String login = this.txtEmail.getText().trim();
        String password = this.txtPassword.getText().trim();
        
        if(login == "" || password == ""){
           Messages.showMessageDialog("Debe llenar todos los datos", "Atención");
           this.txtEmail.requestFocus();
           return; 
        }
        
        try {
            //Agregar
            User user = validarDatos(login, password);
            if(user!=null){
                 permitirAcceso(user);
            }else{
                Messages.showMessageDialog("El usuario no existe", "Atención");
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresarSinCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarSinCuentaActionPerformed
          GUIComprador vtnComprador = new GUIComprador(productService, categoryService,buyService, null, null);
           vtnComprador.setVisible(true);
    }//GEN-LAST:event_btnIngresarSinCuentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIngresarSinCuenta;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblIniciarSesion;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables

    private User validarDatos(String login, String password) throws Exception {
        User user = userService.findUserByLogin(login);
        if(user==null){
            return null;
        }
        if(user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }

    private void permitirAcceso(User user) {
        if(user.getTipo().equalsIgnoreCase("vendedor")){
            GUIVendedor vtnVendedor = new GUIVendedor(productService, categoryService, user);
            vtnVendedor.setVisible(true);
        }else if(user.getTipo().equalsIgnoreCase("comprador")){
            GUIComprador vtnComprador = new GUIComprador(productService, categoryService,buyService, user, bankService);
            vtnComprador.setVisible(true);
        }else if(user.getTipo().equalsIgnoreCase("repartidor")){
            GUIRepartidor vtnRepartidor = new GUIRepartidor(buyService);
            vtnRepartidor.setVisible(true);
        }else{
           Messages.showMessageDialog("No se han implementado otros tipos de usuario", "Atención");
        }
    }
}
