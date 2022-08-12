/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import laporan.*;

/**
 *
 * @author albin
 */
public class dataMenu extends javax.swing.JFrame {
    DefaultTableModel table = new DefaultTableModel();
    DefaultTableModel tablevarian = new DefaultTableModel();
    /**
     * Creates new form daftarMenu
     */
    @SuppressWarnings("empty-statement")
    public dataMenu() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        tanggal();
        
        
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        tableMenu21552011235.setModel(table);
        table.addColumn("Kode Menu");
        table.addColumn("Nama Menu");
        table.addColumn("Harga");
        table.addColumn("Stok");
        table.addColumn("UMKM");
        table.addColumn("Tanggal Masuk");
        tampilData();
        
       
        tableVarian.setModel(tablevarian);
        tablevarian.addColumn("No");
        tablevarian.addColumn("Nama Varian");
        tablevarian.addColumn("Harga");
        tampilDataVarian();
        
    }
    
    ////SECTION MENU
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = tableMenu21552011235.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_menu`";
        
        try{
            java.sql.Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_menu");
                    String nama = rslt.getString("nama_menu");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");
                    String umkm = rslt.getString("UMKM");
                    String tanggal = rslt.getString("tanggal");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok,umkm,tanggal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tableMenu21552011235.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    
    private void clear(){
        //txtFieldNamaUMKM.setText(null);
        txtFieldNamaMenu21552011235.setText(null);
        txtFieldHarga21552011235.setText(null);
        txtFieldStok21552011235.setText(null);
        jComboBoxUMKM.setSelectedIndex(0);
//        txt_tanggal.setDate(null);
        
    }
    
    
    private void tambahData(){
        //String kode = 
        String nama = txtFieldNamaMenu21552011235.getText();
        String harga = txtFieldHarga21552011235.getText();
        String stok = txtFieldStok21552011235.getText();
        String umkm = (String) jComboBoxUMKM.getSelectedItem();
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = date.format(txtTanggal21552011235.getDate());
        
        //panggil koneksi
        java.sql.Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `tb_menu` (kode_menu, `nama_menu`, `harga`, `stok`,`umkm`,`tanggal`) "
                     + "VALUES (NULL,'"+nama+"', '"+harga+"', '"+stok+"','"+umkm+"', '"+tanggal+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            tampilData();
            clear();
            
        }
    }
    
    public void tanggal(){
        Date now = new Date();  
        txtTanggal21552011235.setDate(now);    
    }
    
        private void editData(){
        int i = tableMenu21552011235.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        String nama = txtFieldNamaMenu21552011235.getText();
        String harga =txtFieldHarga21552011235.getText();
        String stok = txtFieldStok21552011235.getText();
        String umkm = (String) jComboBoxUMKM.getSelectedItem();
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(txtTanggal21552011235.getDate()));
        
        java.sql.Connection connect = koneksi.getKoneksi();
        
        String query = "UPDATE `tb_menu` SET `nama_menu` = '"+nama+"', `harga` = '"+harga+"', `stok` = '"+stok+"', `umkm` = '"+umkm+"', `tanggal` = '"+tanggal+"' "
                + "WHERE `tb_menu`.`kode_menu` = '"+kode+"';";

        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null , "Data Update");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Gagal Update");
        }finally{
            tampilData();
            clear();
        }
    }
    
    private void hapusData(){
        //ambill data no pendaftaran
        int i = tableMenu21552011235.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        java.sql.Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `tb_menu` WHERE `tb_menu`.`kode_menu` = "+kode+" ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(null , "Data Berhasil Dihapus");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilData();
            clear();
        }
    }
    
    private void cari(){
        int row = tableMenu21552011235.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = txtFieldCari21552011235.getText();
        
        String query = "SELECT * FROM `tb_menu` WHERE `kode_menu`  LIKE '%"+cari+"%' OR `nama_menu` LIKE '%"+cari+"%' ";
                
       try{
           java.sql.Connection connect = koneksi.getKoneksi();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_menu");
                    String nama = rslt.getString("nama_menu");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");
                    String tanggal = rslt.getString("tanggal");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok,tanggal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tableMenu21552011235.setModel(table);
           
        
    }catch(Exception e){
           System.out.println(e);
    }
    }
    ////////////////////////////////////////////////// END SECTION MENU
    
    
    
    //////////////////////////////////////////////////SECTION VARIAN
    private void tampilDataVarian(){
        //untuk mengahapus baris setelah input
        int row = tableVarian.getRowCount();
        for(int a = 0 ; a < row ; a++){
            tablevarian.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_varian`";
        
        try{
            java.sql.Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                    String no = rslt.getString("no_varian");
                    String nama = rslt.getString("nama_varian");
                    String harga = rslt.getString("harga");
                    
                //masukan semua data kedalam array
                String[] data = {no,nama,harga};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                tablevarian.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tableVarian.setModel(tablevarian);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    
        private void clearVarian(){
        txtFieldNamaVarian.setText(null);
        txtFieldHargaVarian.setText(null);
    }
        
        private void tambahDataVarian(){;
        String nama = txtFieldNamaVarian.getText();
        String harga = txtFieldHargaVarian.getText();
        
        //panggil koneksi
        java.sql.Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `tb_varian` (no_varian, `nama_varian`, `harga`) "
                     + "VALUES (NULL, '"+nama+"', '"+harga+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            tampilDataVarian();
            clearVarian();
        }
    }
    
        private void editDataVarian(){
        int i = tableVarian.getSelectedRow();
        
        String no = tableVarian.getValueAt(i, 0).toString();
        String nama = txtFieldNamaVarian.getText();
        String harga =txtFieldHargaVarian.getText();
        
        java.sql.Connection connect = koneksi.getKoneksi();
        
        String query = "UPDATE `tb_varian` SET `nama_varian` = '"+nama+"', `harga` = '"+harga+"' "
                + "WHERE `tb_varian`.`no` = '"+no+"';";

        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null , "Data Update");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Gagal Update");
        }finally{
            tampilDataVarian();
            clearVarian();
        }
    }
        
    private void hapusDataVarian(){
        //ambill data no pendaftaran
        int i = tableVarian.getSelectedRow();
        
        String no = tableVarian.getValueAt(i, 0).toString();
        
        java.sql.Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `tb_varian` WHERE `tb_varian`.`no_varian` = "+no+" ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(null , "Data Berhasil Dihapus");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilDataVarian();
            clearVarian();
        }
    }
    
    private void cariVarian(){
        int row = tableVarian.getRowCount();
        for(int a = 0 ; a < row ; a++){
            tablevarian.removeRow(0);
        }
        
        String cari = txtFieldCariVarian.getText();
        
        String query = "SELECT * FROM `tb_varian` WHERE `no_varian`  LIKE '%"+cari+"%' OR `nama_varian` LIKE '%"+cari+"%' ";
                
       try{
           java.sql.Connection connect = koneksi.getKoneksi();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String no = rslt.getString("no_varian");
                    String nama = rslt.getString("nama_varian");
                    String harga = rslt.getString("harga");
                    
                //masukan semua data kedalam array
                String[] data = {no,nama,harga};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                tablevarian.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tableVarian.setModel(tablevarian);
           
        
    }catch(Exception e){
           System.out.println(e);
    }
    }
    
    
    //// END SECTION VARIAN

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void changecolor(JPanel hover, Color rand){
        hover.setBackground(rand);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Navbar = new javax.swing.JPanel();
        PanelBack21552011235 = new javax.swing.JPanel();
        BtnBack21552011235 = new javax.swing.JLabel();
        DaftarMenu2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        line5 = new javax.swing.JLabel();
        Navbar1 = new javax.swing.JPanel();
        PanelBack77174756 = new javax.swing.JPanel();
        BtnBack77174756 = new javax.swing.JLabel();
        DaftarMenu1 = new javax.swing.JLabel();
        txtFieldStok21552011235 = new javax.swing.JTextField();
        PanelCariMenu = new javax.swing.JPanel();
        BtnCariMenu = new javax.swing.JLabel();
        PanelAddMenu = new javax.swing.JPanel();
        BtnAddMenu = new javax.swing.JLabel();
        Stok = new javax.swing.JLabel();
        PanelAddVarian = new javax.swing.JPanel();
        BtnAddVarian = new javax.swing.JLabel();
        NamaMenu3 = new javax.swing.JLabel();
        line8 = new javax.swing.JLabel();
        txtFieldNamaMenu21552011235 = new javax.swing.JTextField();
        line3 = new javax.swing.JLabel();
        line2 = new javax.swing.JLabel();
        txtFieldHarga21552011235 = new javax.swing.JTextField();
        Harga = new javax.swing.JLabel();
        PanelClearMenu = new javax.swing.JPanel();
        BtnClearMenu = new javax.swing.JLabel();
        PanelClearVarian = new javax.swing.JPanel();
        BtnClearVarian = new javax.swing.JLabel();
        PanelRefreshMenu = new javax.swing.JPanel();
        BtnRefreshMenu = new javax.swing.JLabel();
        PanelRefreshVarian = new javax.swing.JPanel();
        BtnRefreshVarian = new javax.swing.JLabel();
        PanelDeleteMenu = new javax.swing.JPanel();
        BtnDeleteMenu = new javax.swing.JLabel();
        PanelDeleteVarian = new javax.swing.JPanel();
        BtnDeleteVarian = new javax.swing.JLabel();
        PanelPrint21552011235 = new javax.swing.JPanel();
        BtnPrint21552011235 = new javax.swing.JLabel();
        PanelEditMenu = new javax.swing.JPanel();
        BtnEditMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMenu21552011235 = new javax.swing.JTable();
        line11 = new javax.swing.JLabel();
        line10 = new javax.swing.JLabel();
        txtFieldCari21552011235 = new javax.swing.JTextField();
        txtTanggal21552011235 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableVarian = new javax.swing.JTable();
        line9 = new javax.swing.JLabel();
        txtFieldNamaVarian = new javax.swing.JTextField();
        NamaMenu4 = new javax.swing.JLabel();
        txtFieldHargaVarian = new javax.swing.JTextField();
        NamaMenu5 = new javax.swing.JLabel();
        DaftarMenu = new javax.swing.JLabel();
        DaftarMenu3 = new javax.swing.JLabel();
        PanelCariVarian = new javax.swing.JPanel();
        BtnCariVarian = new javax.swing.JLabel();
        line12 = new javax.swing.JLabel();
        txtFieldCariVarian = new javax.swing.JTextField();
        PanelEditVarian = new javax.swing.JPanel();
        BtnEditVarian = new javax.swing.JLabel();
        KodeMenu = new javax.swing.JLabel();
        jComboBoxUMKM = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(64, 49, 33));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Navbar.setBackground(new java.awt.Color(45, 35, 23));
        Navbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBack21552011235.setBackground(new java.awt.Color(45, 35, 23));
        PanelBack21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack21552011235.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnBack21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnBack21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-back-48.png"))); // NOI18N
        BtnBack21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBack21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBack21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnBack21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnBack21552011235MouseExited(evt);
            }
        });
        PanelBack21552011235.add(BtnBack21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        Navbar.add(PanelBack21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        DaftarMenu2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DaftarMenu2.setForeground(new java.awt.Color(255, 255, 255));
        DaftarMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaftarMenu2.setText("Data Menu");
        Navbar.add(DaftarMenu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 280, 60));

        jPanel2.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 60));

        jPanel3.setBackground(new java.awt.Color(64, 49, 33));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        line5.setBackground(new java.awt.Color(255, 255, 255));
        line5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line5.setForeground(new java.awt.Color(255, 255, 255));
        line5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line5.setText("________________");
        jPanel3.add(line5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 160, 20));

        Navbar1.setBackground(new java.awt.Color(45, 35, 23));
        Navbar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBack77174756.setBackground(new java.awt.Color(45, 35, 23));
        PanelBack77174756.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack77174756.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnBack77174756.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack77174756.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnBack77174756.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-back-48.png"))); // NOI18N
        BtnBack77174756.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBack77174756MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnBack77174756MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnBack77174756MouseExited(evt);
            }
        });
        PanelBack77174756.add(BtnBack77174756, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        Navbar1.add(PanelBack77174756, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        DaftarMenu1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DaftarMenu1.setForeground(new java.awt.Color(255, 255, 255));
        DaftarMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaftarMenu1.setText("Transaksi");
        Navbar1.add(DaftarMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 280, 60));

        jPanel3.add(Navbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 60));

        txtFieldStok21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldStok21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldStok21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldStok21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldStok21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldStok21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldStok21552011235ActionPerformed(evt);
            }
        });
        jPanel3.add(txtFieldStok21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 150, 40));

        PanelCariMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelCariMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelCariMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCariMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnCariMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnCariMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnCariMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-search-24.png"))); // NOI18N
        BtnCariMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCariMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCariMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnCariMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnCariMenuMouseExited(evt);
            }
        });
        PanelCariMenu.add(BtnCariMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel3.add(PanelCariMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 110, -1, 50));

        PanelAddMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelAddMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelAddMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnAddMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAddMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnAddMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-plus-+-24.png"))); // NOI18N
        BtnAddMenu.setText("Menu");
        BtnAddMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAddMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAddMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAddMenuMouseExited(evt);
            }
        });
        PanelAddMenu.add(BtnAddMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 50));

        jPanel3.add(PanelAddMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 150, 50));

        Stok.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Stok.setForeground(new java.awt.Color(255, 255, 255));
        Stok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Stok.setText("Stok");
        jPanel3.add(Stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 90, 40));

        PanelAddVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelAddVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelAddVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnAddVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAddVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnAddVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-plus-+-24.png"))); // NOI18N
        BtnAddVarian.setText("Varian");
        BtnAddVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAddVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAddVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAddVarianMouseExited(evt);
            }
        });
        PanelAddVarian.add(BtnAddVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 50));

        jPanel3.add(PanelAddVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 330, 50));

        NamaMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu3.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu3.setText("Nama Menu");
        jPanel3.add(NamaMenu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 120, 40));

        line8.setBackground(new java.awt.Color(255, 255, 255));
        line8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line8.setForeground(new java.awt.Color(255, 255, 255));
        line8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line8.setText("________________");
        jPanel3.add(line8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 150, 20));

        txtFieldNamaMenu21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldNamaMenu21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldNamaMenu21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldNamaMenu21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldNamaMenu21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldNamaMenu21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 150, 40));

        line3.setBackground(new java.awt.Color(255, 255, 255));
        line3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line3.setForeground(new java.awt.Color(255, 255, 255));
        line3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line3.setText("________________");
        jPanel3.add(line3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 160, 20));

        line2.setBackground(new java.awt.Color(255, 255, 255));
        line2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line2.setForeground(new java.awt.Color(255, 255, 255));
        line2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line2.setText("_____________________________________________________________________________________________________________________________________");
        line2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 1210, 30));

        txtFieldHarga21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldHarga21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldHarga21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldHarga21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldHarga21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldHarga21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldHarga21552011235ActionPerformed(evt);
            }
        });
        jPanel3.add(txtFieldHarga21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 150, 40));

        Harga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Harga.setForeground(new java.awt.Color(255, 255, 255));
        Harga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Harga.setText("Harga");
        jPanel3.add(Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 100, 40));

        PanelClearMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelClearMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelClearMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnClearMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnClearMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnClearMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnClearMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-minus-sign-24.png"))); // NOI18N
        BtnClearMenu.setText("Clear");
        BtnClearMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnClearMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnClearMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnClearMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnClearMenuMouseExited(evt);
            }
        });
        PanelClearMenu.add(BtnClearMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 50));

        jPanel3.add(PanelClearMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 100, 50));

        PanelClearVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelClearVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelClearVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnClearVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnClearVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnClearVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnClearVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-minus-sign-24.png"))); // NOI18N
        BtnClearVarian.setText("Clear");
        BtnClearVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnClearVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnClearVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnClearVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnClearVarianMouseExited(evt);
            }
        });
        PanelClearVarian.add(BtnClearVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        jPanel3.add(PanelClearVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 110, 50));

        PanelRefreshMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelRefreshMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelRefreshMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnRefreshMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnRefreshMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnRefreshMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnRefreshMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-available-updates-24.png"))); // NOI18N
        BtnRefreshMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRefreshMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnRefreshMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRefreshMenuMouseExited(evt);
            }
        });
        PanelRefreshMenu.add(BtnRefreshMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jPanel3.add(PanelRefreshMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 50, 50));

        PanelRefreshVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelRefreshVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelRefreshVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnRefreshVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnRefreshVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnRefreshVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnRefreshVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-available-updates-24.png"))); // NOI18N
        BtnRefreshVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRefreshVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnRefreshVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRefreshVarianMouseExited(evt);
            }
        });
        PanelRefreshVarian.add(BtnRefreshVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        jPanel3.add(PanelRefreshVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 110, 50));

        PanelDeleteMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelDeleteMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelDeleteMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnDeleteMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnDeleteMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnDeleteMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnDeleteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-trash-24.png"))); // NOI18N
        BtnDeleteMenu.setText("Delete");
        BtnDeleteMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnDeleteMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnDeleteMenuMouseExited(evt);
            }
        });
        PanelDeleteMenu.add(BtnDeleteMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel3.add(PanelDeleteMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 340, 130, 50));

        PanelDeleteVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelDeleteVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelDeleteVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnDeleteVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnDeleteVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnDeleteVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnDeleteVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-trash-24.png"))); // NOI18N
        BtnDeleteVarian.setText("Delete");
        BtnDeleteVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnDeleteVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnDeleteVarianMouseExited(evt);
            }
        });
        PanelDeleteVarian.add(BtnDeleteVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel3.add(PanelDeleteVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 620, 130, 50));

        PanelPrint21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelPrint21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelPrint21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnPrint21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnPrint21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnPrint21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnPrint21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-print-24.png"))); // NOI18N
        BtnPrint21552011235.setText("Print");
        BtnPrint21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnPrint21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnPrint21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnPrint21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnPrint21552011235MouseExited(evt);
            }
        });
        PanelPrint21552011235.add(BtnPrint21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel3.add(PanelPrint21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 200, 130, 50));

        PanelEditMenu.setBackground(new java.awt.Color(64, 49, 33));
        PanelEditMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelEditMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnEditMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnEditMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-edit-24.png"))); // NOI18N
        BtnEditMenu.setText("Edit");
        BtnEditMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEditMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnEditMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnEditMenuMouseExited(evt);
            }
        });
        PanelEditMenu.add(BtnEditMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel3.add(PanelEditMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 270, 130, 50));

        tableMenu21552011235.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMenu21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableMenu21552011235.setSelectionBackground(new java.awt.Color(64, 49, 33));
        tableMenu21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMenu21552011235MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMenu21552011235);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 680, 250));

        line11.setBackground(new java.awt.Color(255, 255, 255));
        line11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line11.setForeground(new java.awt.Color(255, 255, 255));
        line11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line11.setText("______");
        jPanel3.add(line11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, -1, 20));

        line10.setBackground(new java.awt.Color(255, 255, 255));
        line10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line10.setForeground(new java.awt.Color(255, 255, 255));
        line10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line10.setText("______________________");
        jPanel3.add(line10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 210, 20));

        txtFieldCari21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldCari21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldCari21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCari21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldCari21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldCari21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 120, 60, 40));
        jPanel3.add(txtTanggal21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 150, 40));

        tableVarian.setModel(new javax.swing.table.DefaultTableModel(
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
        tableVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableVarian.setSelectionBackground(new java.awt.Color(64, 49, 33));
        tableVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVarianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableVarian);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 680, 200));

        line9.setBackground(new java.awt.Color(255, 255, 255));
        line9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line9.setForeground(new java.awt.Color(255, 255, 255));
        line9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line9.setText("______________________");
        jPanel3.add(line9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 200, 20));

        txtFieldNamaVarian.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldNamaVarian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldNamaVarian.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldNamaVarian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldNamaVarian.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldNamaVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 200, 40));

        NamaMenu4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu4.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu4.setText("Nama Varian/Toping");
        jPanel3.add(NamaMenu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 150, 40));

        txtFieldHargaVarian.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldHargaVarian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldHargaVarian.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldHargaVarian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldHargaVarian.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldHargaVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 200, 40));

        NamaMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu5.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu5.setText("Harga Varian");
        jPanel3.add(NamaMenu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 150, 40));

        DaftarMenu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DaftarMenu.setForeground(new java.awt.Color(255, 255, 255));
        DaftarMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DaftarMenu.setText("Tabel Varian");
        jPanel3.add(DaftarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 120, 50));

        DaftarMenu3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DaftarMenu3.setForeground(new java.awt.Color(255, 255, 255));
        DaftarMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DaftarMenu3.setText("Tabel Menu");
        jPanel3.add(DaftarMenu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 120, 50));

        PanelCariVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelCariVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelCariVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCariVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnCariVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnCariVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnCariVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-search-24.png"))); // NOI18N
        BtnCariVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCariVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCariVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnCariVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnCariVarianMouseExited(evt);
            }
        });
        PanelCariVarian.add(BtnCariVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel3.add(PanelCariVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 490, -1, 50));

        line12.setBackground(new java.awt.Color(255, 255, 255));
        line12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line12.setForeground(new java.awt.Color(255, 255, 255));
        line12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line12.setText("______");
        jPanel3.add(line12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 520, -1, 20));

        txtFieldCariVarian.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldCariVarian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldCariVarian.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCariVarian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldCariVarian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldCariVarian.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldCariVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 500, 60, 40));

        PanelEditVarian.setBackground(new java.awt.Color(64, 49, 33));
        PanelEditVarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelEditVarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnEditVarian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditVarian.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditVarian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnEditVarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-edit-24.png"))); // NOI18N
        BtnEditVarian.setText("Edit");
        BtnEditVarian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditVarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEditVarianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnEditVarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnEditVarianMouseExited(evt);
            }
        });
        PanelEditVarian.add(BtnEditVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel3.add(PanelEditVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 560, 130, 50));

        KodeMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        KodeMenu.setForeground(new java.awt.Color(255, 255, 255));
        KodeMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KodeMenu.setText("Penyedia");
        jPanel3.add(KodeMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 120, 40));

        jComboBoxUMKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxUMKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Langkah Kopi", "Angkringan99", "Kue Balok" }));
        jPanel3.add(jComboBoxUMKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 150, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1382, 807));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBack21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseClicked
        new admin.dashboardAdmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_BtnBack21552011235MouseClicked

    private void BtnBack21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseEntered
        changecolor(PanelBack21552011235, new Color (255,24,24));
    }//GEN-LAST:event_BtnBack21552011235MouseEntered

    private void BtnBack21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseExited
        changecolor(PanelBack21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnBack21552011235MouseExited

    private void BtnBack77174756MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack77174756MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBack77174756MouseClicked

    private void BtnBack77174756MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack77174756MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBack77174756MouseEntered

    private void BtnBack77174756MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack77174756MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBack77174756MouseExited

    private void BtnAddMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMenuMouseClicked
       tambahData();
    }//GEN-LAST:event_BtnAddMenuMouseClicked

    private void BtnAddMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMenuMouseEntered
       changecolor(PanelAddMenu, new Color (78,159,61));
    }//GEN-LAST:event_BtnAddMenuMouseEntered

    private void BtnAddMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMenuMouseExited
       changecolor(PanelAddMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnAddMenuMouseExited

    private void BtnClearMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearMenuMouseClicked
        clear();
    }//GEN-LAST:event_BtnClearMenuMouseClicked

    private void BtnClearMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearMenuMouseEntered
      changecolor(PanelClearMenu, new Color (224,77,1));
    }//GEN-LAST:event_BtnClearMenuMouseEntered

    private void BtnClearMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearMenuMouseExited
      changecolor(PanelClearMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnClearMenuMouseExited

    private void BtnPrint21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseClicked

       try{
            String file = "/laporan/report_menu.jasper";
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);
            
        }catch(JRException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
            
    }//GEN-LAST:event_BtnPrint21552011235MouseClicked

    private void BtnPrint21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseEntered
      changecolor(PanelPrint21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnPrint21552011235MouseEntered

    private void BtnPrint21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseExited
       changecolor(PanelPrint21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnPrint21552011235MouseExited

    private void txtFieldStok21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldStok21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldStok21552011235ActionPerformed

    private void BtnCariMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariMenuMouseClicked
       cari();
    }//GEN-LAST:event_BtnCariMenuMouseClicked

    private void BtnCariMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariMenuMouseEntered
         changecolor(PanelCariMenu, new Color (45,35,23));
    }//GEN-LAST:event_BtnCariMenuMouseEntered

    private void BtnCariMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariMenuMouseExited
        changecolor(PanelCariMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnCariMenuMouseExited

    private void BtnDeleteMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteMenuMouseClicked
        hapusData();
    }//GEN-LAST:event_BtnDeleteMenuMouseClicked

    private void BtnDeleteMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteMenuMouseEntered
      changecolor(PanelDeleteMenu, new Color (255,24,24));
    }//GEN-LAST:event_BtnDeleteMenuMouseEntered

    private void BtnDeleteMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteMenuMouseExited
       changecolor(PanelDeleteMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnDeleteMenuMouseExited

    private void BtnRefreshMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMenuMouseClicked
        tampilData();
    }//GEN-LAST:event_BtnRefreshMenuMouseClicked

    private void BtnRefreshMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMenuMouseEntered
       changecolor(PanelRefreshMenu, new Color (32,83,117));
    }//GEN-LAST:event_BtnRefreshMenuMouseEntered

    private void BtnRefreshMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMenuMouseExited
        changecolor(PanelRefreshMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnRefreshMenuMouseExited

    private void tableMenu21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMenu21552011235MouseClicked
        // TODO add your handling code here:
        int baris = tableMenu21552011235.getSelectedRow();
        
//        String kode = table.getValueAt(baris,0).toString();
//        txtFieldNamaUMKM.setText(kode);
        
        String nama = table.getValueAt(baris,1).toString();
        txtFieldNamaMenu21552011235.setText(nama);
        
        String harga = table.getValueAt(baris, 2).toString();
        txtFieldHarga21552011235.setText(harga);
        
        String stok = table.getValueAt(baris, 3).toString();
        txtFieldStok21552011235.setText(stok);
        
        String umkm = table.getValueAt(baris,4).toString();
          for (int i = 0; i <jComboBoxUMKM.getItemCount(); i++ ){
              if (jComboBoxUMKM.getItemAt(i).equalsIgnoreCase(umkm)){
                  jComboBoxUMKM.setSelectedIndex(i);
              }
          }
        
//        String tanggal = table.getValueAt(baris, ).toString();
//        
//        Date convert = null;
//        try{
//            convert = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);   
//        }catch(ParseException e){
//            System.out.println(e);
//        }
//        txtTanggal21552011235.setDate(convert);
    }//GEN-LAST:event_tableMenu21552011235MouseClicked

    private void txtTanggal21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTanggal21552011235MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggal21552011235MouseClicked

    private void txtFieldHarga21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldHarga21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldHarga21552011235ActionPerformed

    private void tableVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVarianMouseClicked
        int baris = tableVarian.getSelectedRow();
        
        String nama = tableVarian.getValueAt(baris,1).toString();
        txtFieldNamaVarian.setText(nama);
        
        String harga = tableVarian.getValueAt(baris, 2).toString();
        txtFieldHargaVarian.setText(harga);
       
    }//GEN-LAST:event_tableVarianMouseClicked

    private void BtnAddVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddVarianMouseClicked
        tambahDataVarian();
    }//GEN-LAST:event_BtnAddVarianMouseClicked

    private void BtnAddVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddVarianMouseEntered
       changecolor(PanelAddVarian, new Color (78,159,61));
    }//GEN-LAST:event_BtnAddVarianMouseEntered

    private void BtnAddVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddVarianMouseExited
        changecolor(PanelAddVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnAddVarianMouseExited

    private void BtnRefreshVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshVarianMouseClicked
        tampilDataVarian();
    }//GEN-LAST:event_BtnRefreshVarianMouseClicked

    private void BtnRefreshVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshVarianMouseEntered
        changecolor(PanelRefreshVarian, new Color (32,83,117));
    }//GEN-LAST:event_BtnRefreshVarianMouseEntered

    private void BtnRefreshVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshVarianMouseExited
        changecolor(PanelRefreshVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnRefreshVarianMouseExited

    private void BtnClearVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearVarianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnClearVarianMouseClicked

    private void BtnClearVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearVarianMouseEntered
      changecolor(PanelClearVarian, new Color (224,77,1));
    }//GEN-LAST:event_BtnClearVarianMouseEntered

    private void BtnClearVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnClearVarianMouseExited
      changecolor(PanelClearVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnClearVarianMouseExited

    private void BtnCariVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariVarianMouseClicked
      cariVarian();
    }//GEN-LAST:event_BtnCariVarianMouseClicked

    private void BtnCariVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariVarianMouseEntered
       changecolor(PanelCariVarian, new Color (45,35,23));
    }//GEN-LAST:event_BtnCariVarianMouseEntered

    private void BtnCariVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCariVarianMouseExited
        changecolor(PanelCariVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnCariVarianMouseExited

    private void BtnEditVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditVarianMouseClicked
        editDataVarian();
    }//GEN-LAST:event_BtnEditVarianMouseClicked

    private void BtnEditVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditVarianMouseEntered
        changecolor(PanelEditVarian, new Color (45,35,23));
    }//GEN-LAST:event_BtnEditVarianMouseEntered

    private void BtnEditVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditVarianMouseExited
        changecolor(PanelEditVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnEditVarianMouseExited

    private void BtnEditMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditMenuMouseExited
        changecolor(PanelEditMenu, new Color (64,49,33));
    }//GEN-LAST:event_BtnEditMenuMouseExited

    private void BtnEditMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditMenuMouseEntered
        changecolor(PanelEditMenu, new Color (45,35,23));
    }//GEN-LAST:event_BtnEditMenuMouseEntered

    private void BtnEditMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEditMenuMouseClicked
        editData();
    }//GEN-LAST:event_BtnEditMenuMouseClicked

    private void BtnDeleteVarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteVarianMouseClicked
        hapusDataVarian();
    }//GEN-LAST:event_BtnDeleteVarianMouseClicked

    private void BtnDeleteVarianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteVarianMouseEntered
        changecolor(PanelDeleteVarian, new Color (255,24,24));
    }//GEN-LAST:event_BtnDeleteVarianMouseEntered

    private void BtnDeleteVarianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteVarianMouseExited
        changecolor(PanelDeleteVarian, new Color (64,49,33));
    }//GEN-LAST:event_BtnDeleteVarianMouseExited

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
            java.util.logging.Logger.getLogger(dataMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new dataMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAddMenu;
    private javax.swing.JLabel BtnAddVarian;
    private javax.swing.JLabel BtnBack21552011235;
    private javax.swing.JLabel BtnBack77174756;
    private javax.swing.JLabel BtnCariMenu;
    private javax.swing.JLabel BtnCariVarian;
    private javax.swing.JLabel BtnClearMenu;
    private javax.swing.JLabel BtnClearVarian;
    private javax.swing.JLabel BtnDeleteMenu;
    private javax.swing.JLabel BtnDeleteVarian;
    private javax.swing.JLabel BtnEditMenu;
    private javax.swing.JLabel BtnEditVarian;
    private javax.swing.JLabel BtnPrint21552011235;
    private javax.swing.JLabel BtnRefreshMenu;
    private javax.swing.JLabel BtnRefreshVarian;
    private javax.swing.JLabel DaftarMenu;
    private javax.swing.JLabel DaftarMenu1;
    private javax.swing.JLabel DaftarMenu2;
    private javax.swing.JLabel DaftarMenu3;
    private javax.swing.JLabel Harga;
    private javax.swing.JLabel KodeMenu;
    private javax.swing.JLabel NamaMenu3;
    private javax.swing.JLabel NamaMenu4;
    private javax.swing.JLabel NamaMenu5;
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel Navbar1;
    private javax.swing.JPanel PanelAddMenu;
    private javax.swing.JPanel PanelAddVarian;
    private javax.swing.JPanel PanelBack21552011235;
    private javax.swing.JPanel PanelBack77174756;
    private javax.swing.JPanel PanelCariMenu;
    private javax.swing.JPanel PanelCariVarian;
    private javax.swing.JPanel PanelClearMenu;
    private javax.swing.JPanel PanelClearVarian;
    private javax.swing.JPanel PanelDeleteMenu;
    private javax.swing.JPanel PanelDeleteVarian;
    private javax.swing.JPanel PanelEditMenu;
    private javax.swing.JPanel PanelEditVarian;
    private javax.swing.JPanel PanelPrint21552011235;
    private javax.swing.JPanel PanelRefreshMenu;
    private javax.swing.JPanel PanelRefreshVarian;
    private javax.swing.JLabel Stok;
    private javax.swing.JComboBox<String> jComboBoxUMKM;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel line10;
    private javax.swing.JLabel line11;
    private javax.swing.JLabel line12;
    private javax.swing.JLabel line2;
    private javax.swing.JLabel line3;
    private javax.swing.JLabel line5;
    private javax.swing.JLabel line8;
    private javax.swing.JLabel line9;
    private javax.swing.JTable tableMenu21552011235;
    private javax.swing.JTable tableVarian;
    private javax.swing.JTextField txtFieldCari21552011235;
    private javax.swing.JTextField txtFieldCariVarian;
    private javax.swing.JTextField txtFieldHarga21552011235;
    private javax.swing.JTextField txtFieldHargaVarian;
    private javax.swing.JTextField txtFieldNamaMenu21552011235;
    private javax.swing.JTextField txtFieldNamaVarian;
    private javax.swing.JTextField txtFieldStok21552011235;
    private com.toedter.calendar.JDateChooser txtTanggal21552011235;
    // End of variables declaration//GEN-END:variables

   

    private static class Connection {

        public Connection() {
        }

        private Statement createStatement() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private Object getConnection() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
