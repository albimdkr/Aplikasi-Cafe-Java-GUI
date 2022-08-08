/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author albin
 */
public class stokMenu extends javax.swing.JFrame {
    DefaultTableModel table = new DefaultTableModel();
    ArrayList<varian> arrVarian = new ArrayList<>();

    /**
     * Creates new form daftarMenu
     */
    public stokMenu() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        updateJcomboboxVarian();
        
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_menu.setModel(table);
        table.addColumn("Kode Menu");
        table.addColumn("Nama Menu");
        table.addColumn("Harga");
        table.addColumn("Stok");
        
        tampilData();
    }
    
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_menu.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_menu` ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_menu");
                    String nama = rslt.getString("nama_menu");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_menu.setModel(table);
            
        }catch(SQLException e){
            System.out.println(e);
        }
       
    }
    
    
    private void updateJcomboboxVarian(){
//           String query = "SELECT * FROM tb_varian";
//           
//           try{
//            Connection connect = koneksi.getKoneksi();//memanggil koneksi
//            Statement sttmnt = connect.createStatement();//membuat statement
//            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
//                while (rslt.next()){
//                    jComboBoxPilihVarian.addItem(rslt.getString("nama_varian"));
//                }
//           
//           }catch(SQLException e){
//        }
            String query = "SELECT no, nama_varian, harga FROM tb_varian";
            try {
                
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            //ResultSet rs = DB.read( "select id_jabatan, jabatan, gaji_pokok from jabatan" );
            
            //masukkan kedalam class Divisi ( tampung )
            while( rslt.next() ){
                arrVarian.add( new varian (Integer.parseInt(rslt.getString("no") ),
                                        Integer.parseInt (rslt.getString("harga") ),
                                        rslt.getString("nama_varian")));
            }
            
            //ambil dari class divisi dan munculkan pada combo box cbx_divisi
            for( int i = 0; i < arrVarian.size(); i++ ){
                jComboBoxPilihVarian.addItem( arrVarian.get( i ).getVarian());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }


            
    }
    
    
    private void cari(){
        int row = table_menu.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = txtFieldCari21552011235.getText();
        
        String query = "SELECT * FROM `tb_menu` WHERE `kode_menu`  LIKE '%"+cari+"%' OR `nama_menu` LIKE '%"+cari+"%' ";
                
       try{
           Connection connect = koneksi.getKoneksi();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_menu");
                    String nama = rslt.getString("nama_menu");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_menu.setModel(table);
           
        
    }catch(SQLException e){
           System.out.println(e);
    }
    }
    
    private void hitungVarian(){
        //int hargaMenu, hargaVarian, total;
        
        //String hargaMenu = txtFieldHarga21552011235.getText();
        //String hargaVarian = jComboBoxPilihVarian.getText();
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param hover
     * @param rand
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
        DaftarMenu = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        PanelCari21552011235 = new javax.swing.JPanel();
        jPanelTranskasi1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        BtnCari21552011235 = new javax.swing.JLabel();
        line6 = new javax.swing.JLabel();
        line8 = new javax.swing.JLabel();
        PanelReset = new javax.swing.JPanel();
        BtnReset = new javax.swing.JLabel();
        PanelRefresh21552011235 = new javax.swing.JPanel();
        BtnRefresh21552011235 = new javax.swing.JLabel();
        PanelAddTransaksi = new javax.swing.JPanel();
        BtnAddTransaksi = new javax.swing.JLabel();
        txtFieldCari21552011235 = new javax.swing.JTextField();
        txtFieldNamaMenu21552011235 = new javax.swing.JTextField();
        NamaMenu5 = new javax.swing.JLabel();
        jenisVariant = new javax.swing.JLabel();
        line10 = new javax.swing.JLabel();
        txtFieldHarga21552011235 = new javax.swing.JTextField();
        jenisVariant1 = new javax.swing.JLabel();
        line11 = new javax.swing.JLabel();
        txFieldKodeMenu21552011235 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_menu = new javax.swing.JTable();
        jComboBoxPilihVarian = new javax.swing.JComboBox<>();
        NamaMenu7 = new javax.swing.JLabel();

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

        DaftarMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DaftarMenu.setForeground(new java.awt.Color(255, 255, 255));
        DaftarMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaftarMenu.setText("Daftar Menu");
        Navbar.add(DaftarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 280, 60));

        jPanel2.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 60));

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line.setForeground(new java.awt.Color(255, 255, 255));
        line.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line.setText("____________________________");
        jPanel2.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 270, 20));

        jPanel3.setBackground(new java.awt.Color(64, 49, 33));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelCari21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelCari21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelCari21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTranskasi1.setBackground(new java.awt.Color(64, 49, 33));
        jPanelTranskasi1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanelTranskasi1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(64, 49, 33));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel12.setLayout(new java.awt.GridBagLayout());
        jPanelTranskasi1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cari ");
        jPanelTranskasi1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        PanelCari21552011235.add(jPanelTranskasi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 160, 140, 50));

        BtnCari21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnCari21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnCari21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnCari21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-search-24.png"))); // NOI18N
        BtnCari21552011235.setText("Cari ");
        BtnCari21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCari21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCari21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnCari21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnCari21552011235MouseExited(evt);
            }
        });
        PanelCari21552011235.add(BtnCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        jPanel3.add(PanelCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 140, 50));

        line6.setBackground(new java.awt.Color(255, 255, 255));
        line6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line6.setForeground(new java.awt.Color(255, 255, 255));
        line6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line6.setText("____________________________");
        jPanel3.add(line6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 270, 20));

        line8.setBackground(new java.awt.Color(255, 255, 255));
        line8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line8.setForeground(new java.awt.Color(255, 255, 255));
        line8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line8.setText("____________________________");
        jPanel3.add(line8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 270, 20));

        PanelReset.setBackground(new java.awt.Color(64, 49, 33));
        PanelReset.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelReset.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnReset.setForeground(new java.awt.Color(255, 255, 255));
        BtnReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-reset-24.png"))); // NOI18N
        BtnReset.setText("Reset");
        BtnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnResetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnResetMouseExited(evt);
            }
        });
        PanelReset.add(BtnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 50));

        jPanel3.add(PanelReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, 200, 50));

        PanelRefresh21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelRefresh21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelRefresh21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnRefresh21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnRefresh21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnRefresh21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnRefresh21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-available-updates-24.png"))); // NOI18N
        BtnRefresh21552011235.setText("Refresh");
        BtnRefresh21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRefresh21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefresh21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnRefresh21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRefresh21552011235MouseExited(evt);
            }
        });
        PanelRefresh21552011235.add(BtnRefresh21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 50));

        jPanel3.add(PanelRefresh21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 600, 160, 50));

        PanelAddTransaksi.setBackground(new java.awt.Color(64, 49, 33));
        PanelAddTransaksi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelAddTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnAddTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAddTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnAddTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-plus-+-24.png"))); // NOI18N
        BtnAddTransaksi.setText("Masuk Transaksi");
        BtnAddTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAddTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddTransaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAddTransaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAddTransaksiMouseExited(evt);
            }
        });
        PanelAddTransaksi.add(BtnAddTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        jPanel3.add(PanelAddTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 250, 50));

        txtFieldCari21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldCari21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFieldCari21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCari21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldCari21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldCari21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 250, 50));

        txtFieldNamaMenu21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldNamaMenu21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldNamaMenu21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldNamaMenu21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldNamaMenu21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldNamaMenu21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 260, 40));

        NamaMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu5.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu5.setText("Nama Menu");
        jPanel3.add(NamaMenu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 120, 40));

        jenisVariant.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jenisVariant.setForeground(new java.awt.Color(255, 255, 255));
        jenisVariant.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jenisVariant.setText("Pilih Varian/Toping");
        jPanel3.add(jenisVariant, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 160, 40));

        line10.setBackground(new java.awt.Color(255, 255, 255));
        line10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line10.setForeground(new java.awt.Color(255, 255, 255));
        line10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line10.setText("____________________________");
        jPanel3.add(line10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 270, 20));

        txtFieldHarga21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldHarga21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldHarga21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldHarga21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldHarga21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldHarga21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 260, 40));

        jenisVariant1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jenisVariant1.setForeground(new java.awt.Color(255, 255, 255));
        jenisVariant1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jenisVariant1.setText("Harga Total");
        jPanel3.add(jenisVariant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 120, 40));

        line11.setBackground(new java.awt.Color(255, 255, 255));
        line11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line11.setForeground(new java.awt.Color(255, 255, 255));
        line11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line11.setText("_______");
        jPanel3.add(line11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, 20));

        txFieldKodeMenu21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txFieldKodeMenu21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txFieldKodeMenu21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txFieldKodeMenu21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txFieldKodeMenu21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txFieldKodeMenu21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txFieldKodeMenu21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 60, 40));

        table_menu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        table_menu.setModel(new javax.swing.table.DefaultTableModel(
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
        table_menu.setSelectionBackground(new java.awt.Color(64, 49, 33));
        table_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_menuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_menu);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 920, 380));

        jComboBoxPilihVarian.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel3.add(jComboBoxPilihVarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 250, 40));

        NamaMenu7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu7.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu7.setText("Kode");
        jPanel3.add(NamaMenu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 60, 40));

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
        new transaksi.pageTransaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_BtnBack21552011235MouseClicked

    private void BtnBack21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseEntered
        changecolor(PanelBack21552011235, new Color (255,24,24));
    }//GEN-LAST:event_BtnBack21552011235MouseEntered

    private void BtnBack21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseExited
        changecolor(PanelBack21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnBack21552011235MouseExited

    private void BtnCari21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseClicked
       cari();
    }//GEN-LAST:event_BtnCari21552011235MouseClicked

    private void BtnCari21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseEntered
        changecolor(PanelCari21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnCari21552011235MouseEntered

    private void BtnCari21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseExited
        changecolor(PanelCari21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnCari21552011235MouseExited

    private void BtnRefresh21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefresh21552011235MouseExited
        changecolor(PanelRefresh21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnRefresh21552011235MouseExited

    private void BtnRefresh21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefresh21552011235MouseEntered
        changecolor(PanelRefresh21552011235, new Color (32,83,117));
    }//GEN-LAST:event_BtnRefresh21552011235MouseEntered

    private void BtnRefresh21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefresh21552011235MouseClicked
        tampilData();
    }//GEN-LAST:event_BtnRefresh21552011235MouseClicked

    private void table_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_menuMouseClicked
        int row = table_menu.getSelectedRow();
        //pageTransaksi menu = new pageTransaksi();

        String kode = table.getValueAt(row, 0).toString();
        txFieldKodeMenu21552011235.setText(kode);

        String nama = table.getValueAt(row, 1).toString();
        txtFieldNamaMenu21552011235.setText(nama);

        String harga = table.getValueAt(row, 2).toString();
        txtFieldHarga21552011235.setText(harga);
    
//        menu.setVisible(true);
//        menu.pack();
//        menu.setDefaultCloseOperation(pageTransaksi.DISPOSE_ON_CLOSE);
//        dispose();
        
        
//        int row = table_menu.getSelectedRow();
//        pageTransaksi menu = new pageTransaksi();
//
//        String kode = table.getValueAt(row, 0).toString();
//        menu.txFieldKodeMenu21552011235.setText(kode);
//
//        String nama = table.getValueAt(row, 1).toString();
//        menu.txtFieldNamaMenu21552011235.setText(nama);
//
//        String harga = table.getValueAt(row, 2).toString();
//        menu.txtFieldHarga21552011235.setText(harga);
//    
//        menu.setVisible(true);
//        menu.pack();
//        menu.setDefaultCloseOperation(pageTransaksi.DISPOSE_ON_CLOSE);
//        dispose();

    }//GEN-LAST:event_table_menuMouseClicked

    private void BtnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnResetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResetMouseClicked

    private void BtnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnResetMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResetMouseEntered

    private void BtnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnResetMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResetMouseExited

    private void BtnAddTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAddTransaksiMouseClicked

    private void BtnAddTransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddTransaksiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAddTransaksiMouseEntered

    private void BtnAddTransaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddTransaksiMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAddTransaksiMouseExited

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
            java.util.logging.Logger.getLogger(stokMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stokMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stokMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stokMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new stokMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAddTransaksi;
    private javax.swing.JLabel BtnBack21552011235;
    private javax.swing.JLabel BtnCari21552011235;
    private javax.swing.JLabel BtnRefresh21552011235;
    private javax.swing.JLabel BtnReset;
    private javax.swing.JLabel DaftarMenu;
    private javax.swing.JLabel NamaMenu5;
    private javax.swing.JLabel NamaMenu7;
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel PanelAddTransaksi;
    private javax.swing.JPanel PanelBack21552011235;
    private javax.swing.JPanel PanelCari21552011235;
    private javax.swing.JPanel PanelRefresh21552011235;
    private javax.swing.JPanel PanelReset;
    private javax.swing.JComboBox<String> jComboBoxPilihVarian;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelTranskasi1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jenisVariant;
    private javax.swing.JLabel jenisVariant1;
    private javax.swing.JLabel line;
    private javax.swing.JLabel line10;
    private javax.swing.JLabel line11;
    private javax.swing.JLabel line6;
    private javax.swing.JLabel line8;
    public javax.swing.JTable table_menu;
    public javax.swing.JTextField txFieldKodeMenu21552011235;
    private javax.swing.JTextField txtFieldCari21552011235;
    public javax.swing.JTextField txtFieldHarga21552011235;
    public javax.swing.JTextField txtFieldNamaMenu21552011235;
    // End of variables declaration//GEN-END:variables
}
