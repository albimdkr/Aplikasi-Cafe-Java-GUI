/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import static barista.dashboardBarista.UserNameBarista21552011235;
import com.mysql.jdbc.Connection;
import koneksi.*;
import transaksi.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import koneksi.koneksi;

/**
 *
 * @author albi mudakar
 */
public class loginBarista extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public loginBarista() {
        initComponents();
        txtusernameBarista21552011235.setBackground(new java.awt.Color(0,0,0,1));
        txtpasswordBarista21552011235.setBackground(new java.awt.Color(0,0,0,1));
        koneksi.getKoneksi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void login(){
       
        try{
            Connection connect = (Connection) koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            String query = "SELECT * FROM `barista` WHERE `username` = '"+txtusernameBarista21552011235.getText()+"' && `password`= '"+txtpasswordBarista21552011235.getText()+"' ";
            
           
            
//            int row = 0;
//            while(rs.next()){
//                row = rs.getRow();
//            }
            ResultSet rs = sttmnt.executeQuery(query);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Login Success");
                UserNameBarista21552011235.setText(rs.getString(2));
                new barista.dashboardBarista().setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                txtusernameBarista21552011235.setText(null);
                txtpasswordBarista21552011235.setText(null);
            }
            
        }catch(SQLException | HeadlessException e ){
            System.out.println(e);
        }
    }
    
    public void changecolor(JPanel hover, Color rand){
        hover.setBackground(rand);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jpanelBarista21552011235 = new javax.swing.JPanel();
        BtnBack21552011235 = new javax.swing.JLabel();
        txtusernameBarista21552011235 = new javax.swing.JTextField();
        txtpasswordBarista21552011235 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        langkop = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PanelLoginBarista21552011235 = new javax.swing.JPanel();
        BtnLoginBarista21552011235 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanelBarista21552011235.setBackground(new java.awt.Color(30, 23, 15));
        jpanelBarista21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-forward-32.png"))); // NOI18N
        BtnBack21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBack21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBack21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnBack21552011235MouseEntered(evt);
            }
        });
        jpanelBarista21552011235.add(BtnBack21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        txtusernameBarista21552011235.setFont(txtusernameBarista21552011235.getFont().deriveFont(txtusernameBarista21552011235.getFont().getSize()+2f));
        txtusernameBarista21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtusernameBarista21552011235.setBorder(null);
        txtusernameBarista21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameBarista21552011235ActionPerformed(evt);
            }
        });
        jpanelBarista21552011235.add(txtusernameBarista21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 350, 40));

        txtpasswordBarista21552011235.setFont(txtpasswordBarista21552011235.getFont().deriveFont(txtpasswordBarista21552011235.getFont().getSize()+2f));
        txtpasswordBarista21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtpasswordBarista21552011235.setBorder(null);
        txtpasswordBarista21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordBarista21552011235ActionPerformed(evt);
            }
        });
        jpanelBarista21552011235.add(txtpasswordBarista21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 350, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");
        jpanelBarista21552011235.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 150, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("____________________________________________________________");
        jpanelBarista21552011235.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 420, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        jpanelBarista21552011235.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 360, 36));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-male-user-24.png"))); // NOI18N
        jpanelBarista21552011235.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, 28));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        jpanelBarista21552011235.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 360, 36));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("____________________________________________________________");
        jpanelBarista21552011235.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 420, 30));

        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eyewhite20.png"))); // NOI18N
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        jpanelBarista21552011235.add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, -1, 28));

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/showEye.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        jpanelBarista21552011235.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, -1, 28));

        langkop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/langkop120px.png"))); // NOI18N
        jpanelBarista21552011235.add(langkop, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 120, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/foto1.jpg"))); // NOI18N
        jpanelBarista21552011235.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 437));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/beansHorizonUp.png"))); // NOI18N
        jpanelBarista21552011235.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -10, 540, 120));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Barista");
        jpanelBarista21552011235.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 70, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-male-user-24.png"))); // NOI18N
        jpanelBarista21552011235.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, -1, 28));

        PanelLoginBarista21552011235.setBackground(new java.awt.Color(30, 23, 15));
        PanelLoginBarista21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelLoginBarista21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnLoginBarista21552011235.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        BtnLoginBarista21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnLoginBarista21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnLoginBarista21552011235.setText("Login");
        BtnLoginBarista21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLoginBarista21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnLoginBarista21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnLoginBarista21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnLoginBarista21552011235MouseExited(evt);
            }
        });
        PanelLoginBarista21552011235.add(BtnLoginBarista21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        jpanelBarista21552011235.add(PanelLoginBarista21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 140, 40));

        getContentPane().add(jpanelBarista21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 813, -1));

        setSize(new java.awt.Dimension(813, 437));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        txtpasswordBarista21552011235.setEchoChar((char)8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        show.setEnabled(false);
        show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    
    
    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        txtpasswordBarista21552011235.setEchoChar((char)0);
        disable.setVisible(false);
        disable.setEnabled(false);
        show.setEnabled(true);
        show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void BtnBack21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseClicked
      new login.selamatDatang().setVisible(true);
      dispose();
    }//GEN-LAST:event_BtnBack21552011235MouseClicked

    private void txtpasswordBarista21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordBarista21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordBarista21552011235ActionPerformed

    private void txtusernameBarista21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameBarista21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameBarista21552011235ActionPerformed

    private void BtnBack21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseEntered
       
    }//GEN-LAST:event_BtnBack21552011235MouseEntered

    private void BtnLoginBarista21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLoginBarista21552011235MouseClicked
        login();
    }//GEN-LAST:event_BtnLoginBarista21552011235MouseClicked

    private void BtnLoginBarista21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLoginBarista21552011235MouseEntered
        changecolor(PanelLoginBarista21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnLoginBarista21552011235MouseEntered

    private void BtnLoginBarista21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLoginBarista21552011235MouseExited
        changecolor(PanelLoginBarista21552011235, new Color (30,23,15));
    }//GEN-LAST:event_BtnLoginBarista21552011235MouseExited

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
            java.util.logging.Logger.getLogger(loginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginBarista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack21552011235;
    private javax.swing.JLabel BtnLoginBarista21552011235;
    private javax.swing.JPanel PanelLoginBarista21552011235;
    private javax.swing.JLabel disable;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpanelBarista21552011235;
    private javax.swing.JLabel langkop;
    private javax.swing.JLabel show;
    private javax.swing.JPasswordField txtpasswordBarista21552011235;
    private javax.swing.JTextField txtusernameBarista21552011235;
    // End of variables declaration//GEN-END:variables

    private void changecolor(JLabel CloseBtn21552011235, Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
