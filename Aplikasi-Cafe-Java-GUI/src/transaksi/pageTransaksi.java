/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

/**
 *
 * @author albin
 */
public class pageTransaksi extends javax.swing.JFrame {
    DefaultTableModel table = new DefaultTableModel();
    ArrayList<varian> arrVarian = new ArrayList<>();
  

    /**
     * Creates new form daftarMenu
     */
    public pageTransaksi() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        koneksi.getKoneksi();
        totalnya();
        tanggal();
        BtnHitungDiskon.setEnabled(false);
        BtnBayar21552011235.setEnabled(false);
        BtnPrint21552011235.setEnabled(false);
        BtnAdd21552011235.setEnabled(false);
        //PanelBayar21552011235.setEnabled(false);
        //BtnPrint21552011235.setEnabled(false);
        //koneksi conn = new koneksi();

        
        tb_keranjang.setModel(table);
        table.addColumn("ID");
        table.addColumn("Menu");
        table.addColumn("Varian");
        table.addColumn("Jumlah");
        table.addColumn("Harga");
        table.addColumn("Total Harga");
        tampilData();
    }
     private void tanggal(){
        Date now = new Date();  
        tgl_transaksi.setDate(now);
    }
     
//    
     
     private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = tb_keranjang.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        //String menuvarian = "SELECT * FROM tb_varian ";
        //SELECT no_varian, nama_varian, harga FROM tb_varian
        //String sql = "SELECT id_transaksi, kode_menu, nama_menu, nama_varian, harga, jumlah, total_harga FROM keranjang ORDER BY id_transaksi ";
        String sql = "SELECT * FROM `keranjang` ORDER BY id_transaksi  ";
        String procedures = "CALL `total_harga_transaksi`()";
        try{
            Connection  connect = koneksi.getKoneksi();//memanggil koneksi
            Statement s = connect.createStatement();//membuat statement
            ResultSet rslt = s.executeQuery(sql);//menjalanakn query
            while (rslt.next()){
                //menampung data sementara
                    String id = rslt.getString("id_transaksi");
                    //String kode_menu = rslt.getString("kode_menu");
                    String menu = rslt.getString("nama_menu");
                    //String no_varian =  rslt.getString("no_varian");
                    String nama_varian =  rslt.getString("nama_varian");
                    String jumlah = rslt.getString("jumlah");
                    String harga = rslt.getString("harga");
                    String total = rslt.getString("total_harga");
                    //String tgl = rslt.getString("tgl_transaksi");
                    
                //masukan semua data kedalam array
                String[] data = {id,menu,nama_varian,jumlah,harga,total};
                
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
            tb_keranjang.setModel(table);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
     
    private void keranjang(){
        String kode = txFieldKodeMenu21552011235.getText();
        //String namaPelanggan = txFieldNamaPelanggan.getText();
        String namaMenu = txtFieldNamaMenu21552011235.getText();
        String noVarian = txFieldNoVarian21552011235.getText();
        String varian = txtFieldVarian21552011235.getText();
        String harga = txtFieldHarga21552011235.getText();
        String jumlah = txtFieldJumlah21552011235.getText();
        String total = txtFieldTotalHarga21552011235.getText();
        String umkm = txFieldUMKM21552011235.getText();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(tgl_transaksi.getDate()));
        //panggil koneksi
        Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        
        String query = "INSERT INTO `transaksi` (`tgl_transaksi`, `id_transaksi`, `kode_menu`, `nama_menu`,`no_varian`, `nama_varian`, `harga`, `jumlah_menu`, `total_harga`, `umkm`) "
                + "VALUES ('"+tanggal+"', NULL, '"+kode+"', '"+namaMenu+"','"+noVarian+"', '"+varian+"', '"+harga+"', '"+jumlah+"', '"+total+"', '"+umkm+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Pesanan Berhasil Masuk");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Ulangi! pastikan stok menu ada atau input jumlah pesanan terlebih dahulu.", "PESANAN GAGAL !", JOptionPane.ERROR_MESSAGE);
            
        }finally{
            tampilData();
            clear();
        }
        totalnya();
        
    }
    
    private void total(){
        String harga = txtFieldHarga21552011235.getText();
        String jumlah = txtFieldJumlah21552011235.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int total = hargaa * jumlahh;
        String total_harga = Integer.toString(total);
        
        txtFieldTotalHarga21552011235.setText(total_harga);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Ulangi! pastikan input nilai dalam jumlah ini hanya angka.", "PERHATIKAN GUNAKAN ANGKA !", JOptionPane.WARNING_MESSAGE);
            txtFieldJumlah21552011235.setText(null);
        }
    }
     
    private void hitungDiskon(){
        int harga, total, diskon, totaldiskon;
        
        try{
        harga = Integer.parseInt(txtFieldTotalBayar21552011235.getText());
        diskon = Integer.parseInt(txtFieldDiskon.getText());
        totaldiskon = (diskon * harga)/100;
        total = harga - totaldiskon;
        txtFieldTotalBayar21552011235.setText(String.valueOf(total));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Pastikan input nilai dalam field ini hanya angka.", "PERHATIKAN GUNAKAN ANGKA !", JOptionPane.WARNING_MESSAGE);
            txtFieldDiskon.setText(null);;
        }
    }
     
    private void totalnya(){
        String procedures = "CALL `total_harga_transaksi`()";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
                while(rslt.next()){
                txtFieldTotalBayar21552011235.setText(rslt.getString(1));
              }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
  
    private void kembalian(){
        String total = txtFieldTotalBayar21552011235.getText();
        String uang = txtFieldMasukanUang21552011235.getText();
        
        int totals = Integer.parseInt(total);
        try{
            int uangs = Integer.parseInt(uang);     
            int kembali = (uangs - totals);
            String fix = Integer.toString(kembali);
            txtFieldUangKembali21552011235.setText(fix);
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil");
        }catch(NumberFormatException | HeadlessException e){
            JOptionPane.showMessageDialog(rootPane, "Ulangi! Pastikan input uang bayar dengan benar.", "PERINGATAN TRANSAKSI GAGAL !", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    private void reset(){
        txtFieldMasukanUang21552011235.setText(null);
    }
    
    private void clear(){
        txFieldKodeMenu21552011235.setText(null);
        txtFieldNamaMenu21552011235.setText(null);
        txtFieldHarga21552011235.setText(null);
        txtFieldJumlah21552011235.setText(null);
        txtFieldTotalHarga21552011235.setText(null);
        txtFieldVarian21552011235.setText(null);
        txtFieldDiskon.setText(null);
        txFieldNamaPelanggan.setText(null);
        txFieldUMKM21552011235.setText(null);
    }
     
    private void hapusData(){
        int i = tb_keranjang.getSelectedRow();
        int ok = JOptionPane.showConfirmDialog (null," Apakah anda yakin ingin "
            + "mengahapus pesanan dalam keranjang tersebut ?","Konfirmasi Hapus Pesanan Dalam Keranjang ", JOptionPane.YES_NO_OPTION);
        
        if (ok==0){
            String kode = table.getValueAt(i, 0).toString();
            Connection connect = koneksi.getKoneksi();
            String sql = "DELETE FROM keranjang WHERE id_transaksi = "+kode+"";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.execute();
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Ulangi! Pastikan pilih list lalu klik sesuai diinginkan.", "PERINGATAN DATA GAGAL TERHAPUS !", JOptionPane.ERROR_MESSAGE);
        }finally{
            tampilData();
            clear();
        }
        totalnya();
        //hitungDiskon();
        //subtotal();
    }
}
     
    
    private void pelanggan(){
        //String id = 
        String nama = txFieldNamaPelanggan.getText();
        String diskon = txtFieldDiskon.getText();
        String grandtotal = txtFieldTotalBayar21552011235.getText();
        String kembalian = txtFieldUangKembali21552011235.getText();
      
        //panggil koneksi
        Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `tb_pelanggan` (`no_pelanggan`,`nama`,`diskon`, `grand_total`, `uang_kembali`) "
                + "VALUES ( NULL, '"+nama+"', '"+diskon+"', '"+grandtotal+"', '"+kembalian+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
        }finally{
        }
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        line5 = new javax.swing.JLabel();
        PanelBayar21552011235 = new javax.swing.JPanel();
        BtnBayar21552011235 = new javax.swing.JLabel();
        PanelAdd21552011235 = new javax.swing.JPanel();
        BtnAdd21552011235 = new javax.swing.JLabel();
        NamaMenu2 = new javax.swing.JLabel();
        line12 = new javax.swing.JLabel();
        line6 = new javax.swing.JLabel();
        txFieldKodeMenu21552011235 = new javax.swing.JTextField();
        Kode = new javax.swing.JLabel();
        line8 = new javax.swing.JLabel();
        txtFieldNamaMenu21552011235 = new javax.swing.JTextField();
        line2 = new javax.swing.JLabel();
        txtFieldJumlah21552011235 = new javax.swing.JTextField();
        Jumlah = new javax.swing.JLabel();
        PaneHitungDiskon = new javax.swing.JPanel();
        BtnHitungDiskon = new javax.swing.JLabel();
        PaneDelete21552011235 = new javax.swing.JPanel();
        BtnDelete21552011235 = new javax.swing.JLabel();
        line11 = new javax.swing.JLabel();
        PanelPrint21552011235 = new javax.swing.JPanel();
        BtnPrint21552011235 = new javax.swing.JLabel();
        PanelReset21552011235 = new javax.swing.JPanel();
        BtnReset21552011235 = new javax.swing.JLabel();
        line4 = new javax.swing.JLabel();
        txtFieldHarga21552011235 = new javax.swing.JTextField();
        txtFieldTotalHarga21552011235 = new javax.swing.JTextField();
        line3 = new javax.swing.JLabel();
        totalHarga = new javax.swing.JLabel();
        txtFieldMasukanUang21552011235 = new javax.swing.JTextField();
        MasukanUang = new javax.swing.JLabel();
        txtFieldUangKembali21552011235 = new javax.swing.JTextField();
        uangKembali = new javax.swing.JLabel();
        tgl_transaksi = new com.toedter.calendar.JDateChooser();
        MasukanUang1 = new javax.swing.JLabel();
        txtFieldTotalBayar21552011235 = new javax.swing.JTextField();
        PanelCari21552011235 = new javax.swing.JPanel();
        BtnCari21552011235 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_keranjang = new javax.swing.JTable();
        Navbar = new javax.swing.JPanel();
        PanelBack21552011235 = new javax.swing.JPanel();
        BtnBack21552011235 = new javax.swing.JLabel();
        Transaksi = new javax.swing.JLabel();
        Harga = new javax.swing.JLabel();
        txtFieldDiskon = new javax.swing.JTextField();
        NamaMenu5 = new javax.swing.JLabel();
        line7 = new javax.swing.JLabel();
        line13 = new javax.swing.JLabel();
        Keranjang = new javax.swing.JLabel();
        NamaUMKM = new javax.swing.JLabel();
        NamaMenu3 = new javax.swing.JLabel();
        line9 = new javax.swing.JLabel();
        namaPelanggan = new javax.swing.JLabel();
        txFieldNamaPelanggan = new javax.swing.JTextField();
        line10 = new javax.swing.JLabel();
        txFieldUMKM21552011235 = new javax.swing.JTextField();
        Kode1 = new javax.swing.JLabel();
        txtFieldVarian21552011235 = new javax.swing.JTextField();
        txFieldNoVarian21552011235 = new javax.swing.JTextField();
        Kode2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(64, 49, 33));

        jPanel2.setBackground(new java.awt.Color(64, 49, 33));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(64, 49, 33));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        line5.setBackground(new java.awt.Color(255, 255, 255));
        line5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line5.setForeground(new java.awt.Color(255, 255, 255));
        line5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line5.setText("____________________________");
        jPanel3.add(line5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 270, 20));

        PanelBayar21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelBayar21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelBayar21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBayar21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnBayar21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnBayar21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnBayar21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-credit-card-20.png"))); // NOI18N
        BtnBayar21552011235.setText("Bayar");
        BtnBayar21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBayar21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBayar21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnBayar21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnBayar21552011235MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BtnBayar21552011235MousePressed(evt);
            }
        });
        PanelBayar21552011235.add(BtnBayar21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jPanel3.add(PanelBayar21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 540, 160, 60));

        PanelAdd21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelAdd21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelAdd21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnAdd21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAdd21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnAdd21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnAdd21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-plus-+-24.png"))); // NOI18N
        BtnAdd21552011235.setText("Masukan Pesanan Ke Keranjang ");
        BtnAdd21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAdd21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAdd21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAdd21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAdd21552011235MouseExited(evt);
            }
        });
        PanelAdd21552011235.add(BtnAdd21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 50));

        jPanel3.add(PanelAdd21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, 590, 50));

        NamaMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu2.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NamaMenu2.setText("%");
        jPanel3.add(NamaMenu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 350, 30, 50));

        line12.setBackground(new java.awt.Color(255, 255, 255));
        line12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line12.setForeground(new java.awt.Color(255, 255, 255));
        line12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line12.setText("______________________________________");
        jPanel3.add(line12, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 670, 350, 20));

        line6.setBackground(new java.awt.Color(255, 255, 255));
        line6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line6.setForeground(new java.awt.Color(255, 255, 255));
        line6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line6.setText("_________");
        jPanel3.add(line6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 90, 20));

        txFieldKodeMenu21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txFieldKodeMenu21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txFieldKodeMenu21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txFieldKodeMenu21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txFieldKodeMenu21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txFieldKodeMenu21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txFieldKodeMenu21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 80, 40));

        Kode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Kode.setForeground(new java.awt.Color(255, 255, 255));
        Kode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Kode.setText("Kode Menu");
        jPanel3.add(Kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 90, 40));

        line8.setBackground(new java.awt.Color(255, 255, 255));
        line8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line8.setForeground(new java.awt.Color(255, 255, 255));
        line8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line8.setText("____________________________");
        jPanel3.add(line8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 270, 20));

        txtFieldNamaMenu21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldNamaMenu21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldNamaMenu21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldNamaMenu21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldNamaMenu21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldNamaMenu21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 260, 40));

        line2.setBackground(new java.awt.Color(255, 255, 255));
        line2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line2.setForeground(new java.awt.Color(255, 255, 255));
        line2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line2.setText("____________________________");
        jPanel3.add(line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 270, 20));

        txtFieldJumlah21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldJumlah21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldJumlah21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldJumlah21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldJumlah21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldJumlah21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldJumlah21552011235ActionPerformed(evt);
            }
        });
        txtFieldJumlah21552011235.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldJumlah21552011235KeyReleased(evt);
            }
        });
        jPanel3.add(txtFieldJumlah21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 260, 40));

        Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Jumlah.setForeground(new java.awt.Color(255, 255, 255));
        Jumlah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Jumlah.setText("Jumlah Pesanan /Porsi/Cup/Biji");
        jPanel3.add(Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 230, 40));

        PaneHitungDiskon.setBackground(new java.awt.Color(64, 49, 33));
        PaneHitungDiskon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PaneHitungDiskon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnHitungDiskon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnHitungDiskon.setForeground(new java.awt.Color(255, 255, 255));
        BtnHitungDiskon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnHitungDiskon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-discount-24.png"))); // NOI18N
        BtnHitungDiskon.setText("Hitung");
        BtnHitungDiskon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnHitungDiskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnHitungDiskonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnHitungDiskonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnHitungDiskonMouseExited(evt);
            }
        });
        PaneHitungDiskon.add(BtnHitungDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 50));

        jPanel3.add(PaneHitungDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 350, 90, 50));

        PaneDelete21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PaneDelete21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PaneDelete21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnDelete21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnDelete21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnDelete21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnDelete21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-trash-24.png"))); // NOI18N
        BtnDelete21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDelete21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDelete21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnDelete21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnDelete21552011235MouseExited(evt);
            }
        });
        PaneDelete21552011235.add(BtnDelete21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel3.add(PaneDelete21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 130, 60, 50));

        line11.setBackground(new java.awt.Color(255, 255, 255));
        line11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line11.setForeground(new java.awt.Color(255, 255, 255));
        line11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line11.setText("______________________________________");
        jPanel3.add(line11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 580, 350, 20));

        PanelPrint21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelPrint21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelPrint21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnPrint21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnPrint21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnPrint21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnPrint21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-print-24.png"))); // NOI18N
        BtnPrint21552011235.setText("Print Struk");
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
        BtnPrint21552011235.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnPrint21552011235KeyReleased(evt);
            }
        });
        PanelPrint21552011235.add(BtnPrint21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jPanel3.add(PanelPrint21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 630, 160, 60));

        PanelReset21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelReset21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelReset21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnReset21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnReset21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnReset21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnReset21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-reset-24.png"))); // NOI18N
        BtnReset21552011235.setText("Clear Form");
        BtnReset21552011235.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnReset21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnReset21552011235MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnReset21552011235MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnReset21552011235MouseExited(evt);
            }
        });
        PanelReset21552011235.add(BtnReset21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 50));

        jPanel3.add(PanelReset21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 720, 540, 50));

        line4.setBackground(new java.awt.Color(255, 255, 255));
        line4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line4.setForeground(new java.awt.Color(255, 255, 255));
        line4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line4.setText("_________________________________________________________________");
        jPanel3.add(line4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 590, 20));

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
        jPanel3.add(txtFieldHarga21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 260, 40));

        txtFieldTotalHarga21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldTotalHarga21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldTotalHarga21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldTotalHarga21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldTotalHarga21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtFieldTotalHarga21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 590, 40));

        line3.setBackground(new java.awt.Color(255, 255, 255));
        line3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line3.setForeground(new java.awt.Color(255, 255, 255));
        line3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line3.setText("______");
        jPanel3.add(line3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 370, 60, 20));

        totalHarga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalHarga.setForeground(new java.awt.Color(255, 255, 255));
        totalHarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalHarga.setText("Total Harga Pesanan");
        jPanel3.add(totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 170, 40));

        txtFieldMasukanUang21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldMasukanUang21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldMasukanUang21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldMasukanUang21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldMasukanUang21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldMasukanUang21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldMasukanUang21552011235.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldMasukanUang21552011235KeyReleased(evt);
            }
        });
        jPanel3.add(txtFieldMasukanUang21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 560, 340, 40));

        MasukanUang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MasukanUang.setForeground(new java.awt.Color(255, 255, 255));
        MasukanUang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MasukanUang.setText("Total Bayar Pemesan ");
        jPanel3.add(MasukanUang, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 220, 40));

        txtFieldUangKembali21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldUangKembali21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldUangKembali21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldUangKembali21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldUangKembali21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldUangKembali21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldUangKembali21552011235.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldUangKembali21552011235KeyReleased(evt);
            }
        });
        jPanel3.add(txtFieldUangKembali21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 650, 340, 40));

        uangKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        uangKembali.setForeground(new java.awt.Color(255, 255, 255));
        uangKembali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uangKembali.setText("Uang Kembalian");
        jPanel3.add(uangKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, 140, 40));

        tgl_transaksi.setDateFormatString("dd-MM-yyyy");
        tgl_transaksi.setEnabled(false);
        tgl_transaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel3.add(tgl_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 130, 140, 50));

        MasukanUang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MasukanUang1.setForeground(new java.awt.Color(255, 255, 255));
        MasukanUang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MasukanUang1.setText("Masukan Uang Bayar");
        jPanel3.add(MasukanUang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 520, 160, 40));

        txtFieldTotalBayar21552011235.setEditable(false);
        txtFieldTotalBayar21552011235.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtFieldTotalBayar21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldTotalBayar21552011235.setEnabled(false);
        txtFieldTotalBayar21552011235.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtFieldTotalBayar21552011235MouseReleased(evt);
            }
        });
        txtFieldTotalBayar21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldTotalBayar21552011235ActionPerformed(evt);
            }
        });
        jPanel3.add(txtFieldTotalBayar21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, 540, 50));

        PanelCari21552011235.setBackground(new java.awt.Color(64, 49, 33));
        PanelCari21552011235.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        PanelCari21552011235.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCari21552011235.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnCari21552011235.setForeground(new java.awt.Color(255, 255, 255));
        BtnCari21552011235.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnCari21552011235.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconOutline/icons8-activity-feed-24.png"))); // NOI18N
        BtnCari21552011235.setText("Daftar Menu");
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
        PanelCari21552011235.add(BtnCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 70));

        jPanel3.add(PanelCari21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, 70));

        tb_keranjang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_keranjang);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 540, 130));

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

        Transaksi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Transaksi.setForeground(new java.awt.Color(255, 255, 255));
        Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Transaksi.setText("Transaksi");
        Navbar.add(Transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 280, 60));

        jPanel3.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1370, -1));

        Harga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Harga.setForeground(new java.awt.Color(255, 255, 255));
        Harga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Harga.setText("Harga /Porsi/Cup/Biji");
        jPanel3.add(Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 150, 40));

        txtFieldDiskon.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldDiskon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldDiskon.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldDiskon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFieldDiskon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldDiskon.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldDiskonActionPerformed(evt);
            }
        });
        txtFieldDiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldDiskonKeyReleased(evt);
            }
        });
        jPanel3.add(txtFieldDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 350, 60, 40));

        NamaMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu5.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu5.setText("Nama Menu");
        jPanel3.add(NamaMenu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 120, 40));

        line7.setBackground(new java.awt.Color(255, 255, 255));
        line7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line7.setForeground(new java.awt.Color(255, 255, 255));
        line7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line7.setText("____________________________");
        jPanel3.add(line7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 270, 20));

        line13.setBackground(new java.awt.Color(255, 255, 255));
        line13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line13.setForeground(new java.awt.Color(255, 255, 255));
        line13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line13.setText("_________");
        jPanel3.add(line13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 90, 20));

        Keranjang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Keranjang.setForeground(new java.awt.Color(255, 255, 255));
        Keranjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Keranjang.setText("Keranjang Pesanan");
        jPanel3.add(Keranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 140, 40));

        NamaUMKM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaUMKM.setForeground(new java.awt.Color(255, 255, 255));
        NamaUMKM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaUMKM.setText("Varian");
        jPanel3.add(NamaUMKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 150, 40));

        NamaMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NamaMenu3.setForeground(new java.awt.Color(255, 255, 255));
        NamaMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NamaMenu3.setText("Diskon");
        jPanel3.add(NamaMenu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 320, 60, 40));

        line9.setBackground(new java.awt.Color(255, 255, 255));
        line9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line9.setForeground(new java.awt.Color(255, 255, 255));
        line9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line9.setText("______________________________________");
        jPanel3.add(line9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 350, 20));

        namaPelanggan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        namaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        namaPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        namaPelanggan.setText("Nama Pelanggan");
        jPanel3.add(namaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 150, 40));

        txFieldNamaPelanggan.setBackground(new java.awt.Color(64, 49, 33));
        txFieldNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txFieldNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txFieldNamaPelanggan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txFieldNamaPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txFieldNamaPelanggan.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txFieldNamaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 340, 40));

        line10.setBackground(new java.awt.Color(255, 255, 255));
        line10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        line10.setForeground(new java.awt.Color(255, 255, 255));
        line10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        line10.setText("____________________________");
        jPanel3.add(line10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 260, 20));

        txFieldUMKM21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txFieldUMKM21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txFieldUMKM21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txFieldUMKM21552011235.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txFieldUMKM21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txFieldUMKM21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txFieldUMKM21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 250, 40));

        Kode1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Kode1.setForeground(new java.awt.Color(255, 255, 255));
        Kode1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Kode1.setText("Nama UMKM");
        jPanel3.add(Kode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 90, 40));

        txtFieldVarian21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txtFieldVarian21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFieldVarian21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldVarian21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFieldVarian21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        txtFieldVarian21552011235.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldVarian21552011235ActionPerformed(evt);
            }
        });
        jPanel3.add(txtFieldVarian21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 260, 40));

        txFieldNoVarian21552011235.setBackground(new java.awt.Color(64, 49, 33));
        txFieldNoVarian21552011235.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txFieldNoVarian21552011235.setForeground(new java.awt.Color(255, 255, 255));
        txFieldNoVarian21552011235.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txFieldNoVarian21552011235.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txFieldNoVarian21552011235.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txFieldNoVarian21552011235, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 80, 40));

        Kode2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Kode2.setForeground(new java.awt.Color(255, 255, 255));
        Kode2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Kode2.setText("No Varian");
        jPanel3.add(Kode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 90, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 900));

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1343, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1359, 895));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBack21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseClicked
        new barista.dashboardBarista().setVisible(true);
        dispose();
    }//GEN-LAST:event_BtnBack21552011235MouseClicked

    private void BtnBack21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseEntered
        changecolor(PanelBack21552011235, new Color (255,24,24));
    }//GEN-LAST:event_BtnBack21552011235MouseEntered

    private void BtnBack21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBack21552011235MouseExited
        changecolor(PanelBack21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnBack21552011235MouseExited

    private void BtnCari21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseClicked
//     new transaksi.stokMenu().setVisible(true);
//     dispose(); 
     new transaksi.stokMenu().setVisible(true);
     dispose();
    }//GEN-LAST:event_BtnCari21552011235MouseClicked

    private void BtnCari21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseEntered
        changecolor(PanelCari21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnCari21552011235MouseEntered

    private void BtnCari21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCari21552011235MouseExited
        changecolor(PanelCari21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnCari21552011235MouseExited

    private void BtnReset21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnReset21552011235MouseClicked

        int ok = JOptionPane.showConfirmDialog (null," Apakah anda yakin ingin "
            + "Menbersihkan Halaman Transaksi?","Konfirmasi Clear Form Transaksi ", JOptionPane.YES_NO_OPTION);
        
        if (ok==0){
            String clear = "TRUNCATE `keranjang`";
            Connection connect = koneksi.getKoneksi();
        try{ 
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();
  
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Ulangi! Pastikan sudah benar.", "PERINGATAN FORM HALAMAN TRANSAKSI GAGAL DIBERSIHKAN !", JOptionPane.ERROR_MESSAGE);
        }finally{
            tampilData();
            totalnya();
            //hitungDiskon();
            txFieldNamaPelanggan.setText(null);
            txtFieldDiskon.setText(null);
            txtFieldMasukanUang21552011235.setText(null);
            txtFieldUangKembali21552011235.setText(null);
        }
      }
    }//GEN-LAST:event_BtnReset21552011235MouseClicked

    private void BtnReset21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnReset21552011235MouseEntered
        changecolor(PanelReset21552011235, new Color (224,77,1));
    }//GEN-LAST:event_BtnReset21552011235MouseEntered

    private void BtnReset21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnReset21552011235MouseExited
        changecolor(PanelReset21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnReset21552011235MouseExited

    private void BtnAdd21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdd21552011235MouseClicked
        keranjang();
    }//GEN-LAST:event_BtnAdd21552011235MouseClicked

    private void BtnAdd21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdd21552011235MouseEntered
       changecolor(PanelAdd21552011235, new Color (78,159,61));
    }//GEN-LAST:event_BtnAdd21552011235MouseEntered

    private void BtnAdd21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdd21552011235MouseExited
       changecolor(PanelAdd21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnAdd21552011235MouseExited

    private void BtnPrint21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseClicked
      try{
            String file = "/struk/struk.jasper";
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            HashMap param = new HashMap();
            
            param.put("nama",txFieldNamaPelanggan.getText());
            param.put("diskon",txtFieldDiskon.getText());
            param.put("total",txtFieldTotalBayar21552011235.getText());
            param.put("uang",txtFieldMasukanUang21552011235.getText());
            param.put("kembalian",txtFieldUangKembali21552011235.getText());
            
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),param,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);
            
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | JRException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_BtnPrint21552011235MouseClicked

    private void BtnPrint21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseEntered
      changecolor(PanelPrint21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnPrint21552011235MouseEntered

    private void BtnPrint21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnPrint21552011235MouseExited
       changecolor(PanelPrint21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnPrint21552011235MouseExited

    private void BtnBayar21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBayar21552011235MouseClicked
        kembalian();
        pelanggan();
//        String namaPelanggan = txFieldNamaPelanggan.getText();
//
//        String query = "INSERT INTO `transaksi` (`nama`) "
//                + "VALUES ('"+namaPelanggan+"')";
//        Connection connect = koneksi.getKoneksi();
//        try{
//            //menyiapkan statement untuk di eksekusi
//            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
//            ps.executeUpdate(query);
//            
//        }catch(SQLException | HeadlessException e){
//            System.out.println(e);
//        }finally{
//        }
    }//GEN-LAST:event_BtnBayar21552011235MouseClicked

    private void BtnBayar21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBayar21552011235MouseEntered
        changecolor(PanelBayar21552011235, new Color (45,35,23));
    }//GEN-LAST:event_BtnBayar21552011235MouseEntered

    private void BtnBayar21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBayar21552011235MouseExited
        changecolor(PanelBayar21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnBayar21552011235MouseExited

    private void BtnBayar21552011235MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBayar21552011235MousePressed
        
    }//GEN-LAST:event_BtnBayar21552011235MousePressed

    private void txtFieldJumlah21552011235KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldJumlah21552011235KeyReleased
        total();
        BtnAdd21552011235.setEnabled(true);
    }//GEN-LAST:event_txtFieldJumlah21552011235KeyReleased

    private void txtFieldTotalBayar21552011235MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFieldTotalBayar21552011235MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldTotalBayar21552011235MouseReleased

    private void txtFieldTotalBayar21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldTotalBayar21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldTotalBayar21552011235ActionPerformed

    private void txtFieldHarga21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldHarga21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldHarga21552011235ActionPerformed

    private void txtFieldJumlah21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldJumlah21552011235ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldJumlah21552011235ActionPerformed

    private void BtnDelete21552011235MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDelete21552011235MouseExited
        changecolor(PaneDelete21552011235, new Color (64,49,33));
    }//GEN-LAST:event_BtnDelete21552011235MouseExited

    private void BtnDelete21552011235MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDelete21552011235MouseEntered
        changecolor(PaneDelete21552011235, new Color (255,24,24));
    }//GEN-LAST:event_BtnDelete21552011235MouseEntered

    private void BtnDelete21552011235MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDelete21552011235MouseClicked
        hapusData();
        txtFieldDiskon.setText(null);
        txtFieldMasukanUang21552011235.setText(null);
        txtFieldUangKembali21552011235.setText(null);
    }//GEN-LAST:event_BtnDelete21552011235MouseClicked

    private void txtFieldDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldDiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldDiskonActionPerformed

    private void txtFieldDiskonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldDiskonKeyReleased
        BtnHitungDiskon.setEnabled(true);
    }//GEN-LAST:event_txtFieldDiskonKeyReleased

    private void BtnHitungDiskonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHitungDiskonMouseClicked
       hitungDiskon();
    }//GEN-LAST:event_BtnHitungDiskonMouseClicked

    private void BtnHitungDiskonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHitungDiskonMouseEntered
        changecolor(PaneHitungDiskon, new Color (32,83,117));
    }//GEN-LAST:event_BtnHitungDiskonMouseEntered

    private void BtnHitungDiskonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHitungDiskonMouseExited
        changecolor(PaneHitungDiskon, new Color (64,49,33));
    }//GEN-LAST:event_BtnHitungDiskonMouseExited

    private void txtFieldMasukanUang21552011235KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldMasukanUang21552011235KeyReleased
        BtnBayar21552011235.setEnabled(true);
        BtnPrint21552011235.setEnabled(true);
        txtFieldDiskon.setEnabled(true);
        //PanelBayar21552011235.setEnabled(true);
    }//GEN-LAST:event_txtFieldMasukanUang21552011235KeyReleased

    private void BtnPrint21552011235KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint21552011235KeyReleased
       
    }//GEN-LAST:event_BtnPrint21552011235KeyReleased

    private void txtFieldUangKembali21552011235KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldUangKembali21552011235KeyReleased
         //BtnPrint21552011235.setEnabled(true);
    }//GEN-LAST:event_txtFieldUangKembali21552011235KeyReleased

    private void txtFieldVarian21552011235ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldVarian21552011235ActionPerformed
//        int no_varian, harga;
//        String nama_varian;
//        
//        int idx = txtFieldVarian21552011235.getText();
//        
//        if ( arrVarian.size() > 0 ){
//            no_varian = arrVarian.get(idx).getNo();
//            harga = arrVarian.get(idx).getHarga();
//            nama_varian = arrVarian.get(idx).toString();
//        }
    }//GEN-LAST:event_txtFieldVarian21552011235ActionPerformed

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
            java.util.logging.Logger.getLogger(pageTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pageTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pageTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pageTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pageTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAdd21552011235;
    private javax.swing.JLabel BtnBack21552011235;
    private javax.swing.JLabel BtnBayar21552011235;
    private javax.swing.JLabel BtnCari21552011235;
    private javax.swing.JLabel BtnDelete21552011235;
    private javax.swing.JLabel BtnHitungDiskon;
    private javax.swing.JLabel BtnPrint21552011235;
    private javax.swing.JLabel BtnReset21552011235;
    private javax.swing.JLabel Harga;
    private javax.swing.JLabel Jumlah;
    private javax.swing.JLabel Keranjang;
    private javax.swing.JLabel Kode;
    private javax.swing.JLabel Kode1;
    private javax.swing.JLabel Kode2;
    private javax.swing.JLabel MasukanUang;
    private javax.swing.JLabel MasukanUang1;
    private javax.swing.JLabel NamaMenu2;
    private javax.swing.JLabel NamaMenu3;
    private javax.swing.JLabel NamaMenu5;
    private javax.swing.JLabel NamaUMKM;
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel PaneDelete21552011235;
    private javax.swing.JPanel PaneHitungDiskon;
    private javax.swing.JPanel PanelAdd21552011235;
    private javax.swing.JPanel PanelBack21552011235;
    private javax.swing.JPanel PanelBayar21552011235;
    private javax.swing.JPanel PanelCari21552011235;
    private javax.swing.JPanel PanelPrint21552011235;
    private javax.swing.JPanel PanelReset21552011235;
    private javax.swing.JLabel Transaksi;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel line10;
    private javax.swing.JLabel line11;
    private javax.swing.JLabel line12;
    private javax.swing.JLabel line13;
    private javax.swing.JLabel line2;
    private javax.swing.JLabel line3;
    private javax.swing.JLabel line4;
    private javax.swing.JLabel line5;
    private javax.swing.JLabel line6;
    private javax.swing.JLabel line7;
    private javax.swing.JLabel line8;
    private javax.swing.JLabel line9;
    private javax.swing.JLabel namaPelanggan;
    public javax.swing.JTable tb_keranjang;
    private com.toedter.calendar.JDateChooser tgl_transaksi;
    private javax.swing.JLabel totalHarga;
    public javax.swing.JTextField txFieldKodeMenu21552011235;
    public javax.swing.JTextField txFieldNamaPelanggan;
    public javax.swing.JTextField txFieldNoVarian21552011235;
    public javax.swing.JTextField txFieldUMKM21552011235;
    public javax.swing.JTextField txtFieldDiskon;
    public javax.swing.JTextField txtFieldHarga21552011235;
    public javax.swing.JTextField txtFieldJumlah21552011235;
    public static javax.swing.JTextField txtFieldMasukanUang21552011235;
    public javax.swing.JTextField txtFieldNamaMenu21552011235;
    public static javax.swing.JTextField txtFieldTotalBayar21552011235;
    public javax.swing.JTextField txtFieldTotalHarga21552011235;
    public static javax.swing.JTextField txtFieldUangKembali21552011235;
    public javax.swing.JTextField txtFieldVarian21552011235;
    private javax.swing.JLabel uangKembali;
    // End of variables declaration//GEN-END:variables

    private static class r {
        
    }
}
