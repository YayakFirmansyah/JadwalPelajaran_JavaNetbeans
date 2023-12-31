/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yayak Firmansyah
 */
public class LoginFrame extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    
    public LoginFrame() {
        initComponents();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost/jadwal","root","");
            st=con.createStatement();
//            JOptionPane.showMessageDialog(null, "Connection Success");
            }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Connection failed because " + ex);
            }          
    }
    
    private void CekLogin(){
        int level = 0;
        try{
            if(txt_email.getText().equals("") || txt_pass.getPassword().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Data cannot be empty!", "Message", JOptionPane.ERROR_MESSAGE);
                txt_email.requestFocus();
                cleantxt();
            } else{
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_user WHERE username = '"+txt_email.getText()+"' AND password ='"+String.valueOf(txt_pass.getPassword())+"'");
                if (rs.next()){ 
                    
                    AdminMainFrame ad = new AdminMainFrame();
                    ad.setVisible(true);
                    dispose();
                    
                } else{
                    JOptionPane.showMessageDialog(rootPane, "username or password is incorrect / account not registered!", "Message", JOptionPane.ERROR_MESSAGE);
                    cleantxt();
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void login(){
        
    }
    
    private void cleantxt() {
        txt_email.setText("");
        txt_pass.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_email = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        BackPanel = new javax.swing.JPanel();
        LoginPanel = new javax.swing.JPanel();
        BtnShowPass = new javax.swing.JLabel();
        BtnHidePass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_email.setBackground(new java.awt.Color(0, 0, 0, 0));
        txt_email.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txt_email.setForeground(new java.awt.Color(255, 255, 255));
        txt_email.setBorder(null);
        txt_email.setOpaque(false);
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 330, 30));

        txt_pass.setBackground(new java.awt.Color(0, 0, 0, 0));
        txt_pass.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(255, 255, 255));
        txt_pass.setBorder(null);
        txt_pass.setOpaque(false);
        txt_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passActionPerformed(evt);
            }
        });
        getContentPane().add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 330, 30));

        BackPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BackPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BackPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BackPanelLayout = new javax.swing.GroupLayout(BackPanel);
        BackPanel.setLayout(BackPanelLayout);
        BackPanelLayout.setHorizontalGroup(
            BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BackPanelLayout.setVerticalGroup(
            BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(BackPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 80, 40));

        LoginPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        LoginPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(LoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, 150, 60));

        BtnShowPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnShowPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnShowPassMouseClicked(evt);
            }
        });
        getContentPane().add(BtnShowPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(844, 394, 30, 20));

        BtnHidePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnHidePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnHidePassMouseClicked(evt);
            }
        });
        getContentPane().add(BtnHidePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(844, 394, 30, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Login new.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        for (double i = 0.0; i <= 1.0; i = i + 0.1){
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void BackPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackPanelMouseClicked
        // TODO add your handling code here:
        GuestMainFrame main = new GuestMainFrame();
        main.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BackPanelMouseClicked

    private void LoginPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginPanelMouseClicked
        CekLogin();
    }//GEN-LAST:event_LoginPanelMouseClicked

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passActionPerformed

    private void BtnShowPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnShowPassMouseClicked
        // TODO add your handling code here:
        BtnHidePass.setVisible(true);
        BtnHidePass.setEnabled(true);
        txt_pass.setEchoChar((char)0);
        BtnShowPass.setVisible(false);
        BtnShowPass.setEnabled(false);
    }//GEN-LAST:event_BtnShowPassMouseClicked

    private void BtnHidePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHidePassMouseClicked
        // TODO add your handling code here:
        BtnShowPass.setVisible(true);
        BtnShowPass.setEnabled(true);
        txt_pass.setEchoChar((char)8226);
        BtnHidePass.setVisible(false);
        BtnHidePass.setEnabled(false);
    }//GEN-LAST:event_BtnHidePassMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackPanel;
    private javax.swing.JLabel BtnHidePass;
    private javax.swing.JLabel BtnShowPass;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables
}
