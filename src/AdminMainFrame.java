
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yayak Firmansyah
 */
public class AdminMainFrame extends javax.swing.JFrame {
    int hari_id, kelas_id, mapel_id, guru_id;
    
    /**
     * Creates new form AdminMainFrame
     */
    public class tabelkelas {
        public String kelas;
                
                public void setKelas(String updatekelas){
                    kelas = updatekelas;
                }
        
    }
    
    public AdminMainFrame() {
        initComponents();
        load_tableGuru();
        load_tableMapel();
        load_tableKelas();
        load_tableJadwal();
        dataCBHari();
        dataCBMapel();
        dataCBGuru();
        dataCBKelas();
        JadwalTable.setAutoCreateRowSorter(true);
        setBackground(new Color(0, 0, 0, 0));
        AdminLogoutPanel.setEnabled(false);
        AdminLogoutPanel.setVisible(false);
        BtnSettingBack.setEnabled(false);
        BtnSettingBack.setVisible(false);
        
        CreateGuruPanel.setVisible(false);
        CreateGuruPanel.setEnabled(false);
        BtnBackGuru.setVisible(false);
        BtnBackGuru.setEnabled(false);
        
        CreateMapelPanel.setVisible(false);
        CreateMapelPanel.setEnabled(false);
        BtnBackMapel.setVisible(false);
        BtnBackMapel.setEnabled(false);
        
        CreateKelasPanel.setVisible(false);
        CreateKelasPanel.setEnabled(false);
        BtnBackKelas.setVisible(false);
        BtnBackKelas.setEnabled(false);
        
        CreateJadwalPanel.setVisible(false);
        CreateJadwalPanel.setEnabled(false);
        BtnBackJadwal.setVisible(false);
        BtnBackJadwal.setEnabled(false);
    }
    
    public void BackCRUDGuru(){
        CreateGuruPanel.setVisible(false);
        CreateGuruPanel.setEnabled(false);
        CRUDGuruPanel.setVisible(true);
        CRUDGuruPanel.setEnabled(true);
        BtnBackGuru.setEnabled(false);
        BtnBackGuru.setVisible(false);
        BtnCreateGuru.setEnabled(true);
        BtnCreateGuru.setVisible(true);
    }
    
    public void BackCRUDMapel(){
        CreateMapelPanel.setVisible(false);
        CreateMapelPanel.setEnabled(false);
        CRUDMapelPanel.setVisible(true);
        CRUDMapelPanel.setEnabled(true);
        BtnBackMapel.setEnabled(false);
        BtnBackMapel.setVisible(false);
        BtnCreateMapel.setEnabled(true);
        BtnCreateMapel.setVisible(true);
    }
    
    public void BackCRUDKelas(){
        CreateKelasPanel.setVisible(false);
        CreateKelasPanel.setEnabled(false);
        CRUDKelasPanel.setVisible(true);
        CRUDKelasPanel.setEnabled(true);
        BtnBackKelas.setEnabled(false);
        BtnBackKelas.setVisible(false);
        BtnCreateKelas.setEnabled(true);
        BtnCreateKelas.setVisible(true);
    }
    
    public void BackCRUDJadwal(){
        CreateJadwalPanel.setVisible(false);
        CreateJadwalPanel.setEnabled(false);
        CRUDJadwalPanel.setVisible(true);
        CRUDJadwalPanel.setEnabled(true);
        BtnBackJadwal.setEnabled(false);
        BtnBackJadwal.setVisible(false);
        BtnCreateJadwal.setEnabled(true);
        BtnCreateJadwal.setVisible(true);
    }
    
    private void load_tableGuru(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Guru");
        model.addColumn("NIP");
        model.addColumn("Nama Guru");
        
        try {
            int no = 1;
            String sql = "SELECT `guru_id`, `nip`, `guru` FROM `tb_guru`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),});
            }
            GuruTabel.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void load_tableMapel(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Mapel");
        model.addColumn("Mata Pelajaran");
        model.addColumn("Singkatan");
        
        try {
            int no = 1;
            String sql = "SELECT `mapel_id`, `mapel`, `singkatan` FROM `tb_mapel`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),});
            }
            MapelTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void load_tableKelas(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Kelas");
        model.addColumn("Kelas");
        
        try {
            int no = 1;
            String sql = "SELECT `kelas_id`, `kelas` FROM `tb_kelas`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2)});
            }
            KelasTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void load_tableJadwal(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Jadwal");
        model.addColumn("Hari");
        model.addColumn("Jam Mulai");
        model.addColumn("Jam Selesai");
        model.addColumn("Kelas");
        model.addColumn("Mata Pelajaran");
        model.addColumn("Guru");
        
        try {
            int no = 1;
            String sql = "SELECT `tb_jadwal`.`jadwal_id`, `tb_hari`.`hari`, `tb_jadwal`.`jam_mulai`, `tb_jadwal`.`jam_selesai`,"
                    + "`tb_kelas`.`kelas`, `tb_mapel`.`singkatan`, `tb_guru`.`guru` FROM `tb_jadwal` "
                    + "JOIN `tb_hari` ON `tb_jadwal`.`hari_id` = `tb_hari`.`hari_id`"
                    + "JOIN `tb_kelas` ON `tb_jadwal`.`kelas_id` = `tb_kelas`.`kelas_id`"
                    + "JOIN `tb_mapel` ON `tb_jadwal`.`mapel_id` = `tb_mapel`.`mapel_id`"
                    + "JOIN `tb_guru` ON `tb_jadwal`.`guru_id` = `tb_guru`.`guru_id` ORDER BY `tb_jadwal`.`jadwal_id`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
            }
            JadwalTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    public void searchdatacb(String kelas) {
        if (kelas != "ALL") {
           DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Kelas");
        model.addColumn("Kelas");
        model.addColumn("Jurusan");
        
        try {
            int no = 1;
            String sql = "SELECT `kelas_id`, `kelas`, `jurusan` FROM `tb_kelas` WHERE `kelas` = '"+kelas+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),});
            }
            KelasTable.setModel(model);
        } catch (Exception e) {
        } 
        } else {
            load_tableKelas();
        }    
    }

    public void dataCBHari(){
        try {
            String sql = "SELECT `hari` FROM `tb_hari`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {                
                CBHari.addItem(res.getString("hari"));
                CBHariUpdate.addItem(res.getString("hari"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (Exception e) {
        }
    }
    
    public void dataCBMapel(){
        try {
            String sql = "SELECT `singkatan` FROM `tb_mapel`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {                
                CBMapel.addItem(res.getString("singkatan"));
                CBMapelUpdate.addItem(res.getString("singkatan"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (Exception e) {
        }
    }
    
    public void dataCBKelas(){
        try {
            String sql = "SELECT `kelas` FROM `tb_kelas`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {                
                CBKelas.addItem(res.getString("kelas"));
                CBKelasUpdate.addItem(res.getString("kelas"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (Exception e) {
        }
    }
    
    public void dataCBGuru(){
        try {
            String sql = "SELECT `guru` FROM `tb_guru`";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {                
                CBGuru.addItem(res.getString("guru"));
                CBGuruUpdate.addItem(res.getString("guru"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (Exception e) {
        }
    }
    
    public void getHari(String Hari){
        try {
            String sql = "SELECT `hari_id` FROM `tb_hari` WHERE `hari` = '"+Hari+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                hari_id = res.getInt(1);
            }
            
        } catch (Exception e) {
        }
    }
    
    public void getKelas(String Kelas){
        try {
            String sql = "SELECT `kelas_id` FROM `tb_kelas` WHERE `kelas` = '"+Kelas+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                kelas_id = res.getInt(1);
            }
            
        } catch (Exception e) {
        }
    }
    
    public void getMapel(String Mapel){
        try {
            String sql = "SELECT `mapel_id` FROM `tb_mapel` WHERE `singkatan` = '"+Mapel+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                mapel_id = res.getInt(1);
            }
            
        } catch (Exception e) {
        }
    }
    
    public void getGuru(String Guru){
        try {
            String sql = "SELECT `guru_id` FROM `tb_guru` WHERE `guru` = '"+Guru+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                guru_id = res.getInt(1);
            }
            
        } catch (Exception e) {
        }
    }
    
    private void cariDataMapel(String key){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Mapel");
        model.addColumn("Mata Pelajaran");
        model.addColumn("Singkatan");
        
        try {
            int no = 1;
            String sql = "SELECT `mapel_id`, `mapel`, `singkatan` FROM `tb_mapel` WHERE `mapel` LIKE '%"+key+"%' OR `singkatan` LIKE '%"+key+"%'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),});
            }
            MapelTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void cariDataKelas(String key){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Kelas");
        model.addColumn("Kelas");
        
        try {
            int no = 1;
            String sql = "SELECT `kelas_id`, `kelas` FROM `tb_kelas` WHERE `kelas` LIKE '%"+key+"%'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2)});
            }
            KelasTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void cariDataGuru(String key){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Guru");
        model.addColumn("NIP");
        model.addColumn("Guru");
        
        try {
            int no = 1;
            String sql = "SELECT `guru_id`, `nip`, `guru` FROM `tb_guru` WHERE `nip` LIKE '%"+key+"%' OR `guru` LIKE '%"+key+"%'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),});
            }
            GuruTabel.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void Clean(){
        TxtKodeGuru.setText(null);
        TxtNIPUpdate.setText(null);
        TxtNIP.setText(null);
        TxtNamaGuru.setText(null);  
        TxtNamaGuruUpdate.setText(null);
        
        TxtKodeMapel.setText(null);
        TxtMapelUpdate.setText(null);
        TxtMapel.setText(null);
        TxtSingkat.setText(null);  
        TxtSingkatUpdate.setText(null);
        
        TxtKodeKelas.setText(null);
        TxtCreateKelas.setText(null);  
        TxtKelas.setText(null);
        
        TxtJamMulai.setText("00.00");
        TxtJamSelesai.setText("00.00");
        TxtJamMulaiUpdate.setText("00.00");
        TxtJamSelesaiUpdate.setText("00.00");
        TxtKodeJadwal.setText(null);
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        KiriPanel = new javax.swing.JPanel();
        AdminLogoutPanel = new javax.swing.JPanel();
        LogoutPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BtnDashboard = new javax.swing.JPanel();
        BtnManMapel = new javax.swing.JPanel();
        BtnManGuru = new javax.swing.JPanel();
        BtnManKelas = new javax.swing.JPanel();
        BtnManJadPel = new javax.swing.JPanel();
        BtnSetting = new javax.swing.JPanel();
        BtnSettingBack = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        KananPanel = new javax.swing.JPanel();
        DashboardPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ManMapelPanel = new javax.swing.JPanel();
        BtnCreateMapel = new javax.swing.JPanel();
        CRUDMapelPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MapelTable = new javax.swing.JTable();
        TxtKodeMapel = new javax.swing.JTextField();
        BtnSaveMapel = new javax.swing.JPanel();
        TxtMapelUpdate = new javax.swing.JTextField();
        BtnDeleteMapel = new javax.swing.JPanel();
        TxtSingkatUpdate = new javax.swing.JTextField();
        TxtSearchMapel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnBackMapel = new javax.swing.JPanel();
        CreateMapelPanel = new javax.swing.JPanel();
        TxtMapel = new javax.swing.JTextField();
        TxtSingkat = new javax.swing.JTextField();
        BtnCreateDataMapel = new javax.swing.JPanel();
        CreateMapelLabel = new javax.swing.JLabel();
        ManGuruPanel = new javax.swing.JPanel();
        BtnCreateGuru = new javax.swing.JPanel();
        CRUDGuruPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GuruTabel = new javax.swing.JTable();
        TxtKodeGuru = new javax.swing.JTextField();
        TxtNIPUpdate = new javax.swing.JTextField();
        TxtNamaGuruUpdate = new javax.swing.JTextField();
        BtnDeleteGuru = new javax.swing.JPanel();
        BtnSaveGuru = new javax.swing.JPanel();
        TxtSearchGuru = new javax.swing.JTextField();
        CRUDGuruLabel = new javax.swing.JLabel();
        CreateGuruPanel = new javax.swing.JPanel();
        TxtNIP = new javax.swing.JTextField();
        TxtNamaGuru = new javax.swing.JTextField();
        BtnCreateDataGuru = new javax.swing.JPanel();
        CreateGuruLabel = new javax.swing.JLabel();
        BtnBackGuru = new javax.swing.JPanel();
        ManKelasPanel = new javax.swing.JPanel();
        BtnCreateKelas = new javax.swing.JPanel();
        CRUDKelasPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        KelasTable = new javax.swing.JTable();
        TxtKelas = new javax.swing.JTextField();
        TxtKodeKelas = new javax.swing.JTextField();
        BtnDeleteKelas = new javax.swing.JPanel();
        BtnSaveKelas = new javax.swing.JPanel();
        TxtSearchKelas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BtnBackKelas = new javax.swing.JPanel();
        CreateKelasPanel = new javax.swing.JPanel();
        TxtCreateKelas = new javax.swing.JTextField();
        BtnCreateDataKelas = new javax.swing.JPanel();
        CreateKelasLabel = new javax.swing.JLabel();
        ManJadGurPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ManJadPelPanel = new javax.swing.JPanel();
        BtnCreateJadwal = new javax.swing.JPanel();
        CRUDJadwalPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JadwalTable = new javax.swing.JTable();
        TxtKodeJadwal = new javax.swing.JTextField();
        CBHariUpdate = new javax.swing.JComboBox();
        CBKelasUpdate = new javax.swing.JComboBox();
        CBMapelUpdate = new javax.swing.JComboBox();
        CBGuruUpdate = new javax.swing.JComboBox();
        TxtJamMulaiUpdate = new javax.swing.JTextField();
        TxtJamSelesaiUpdate = new javax.swing.JTextField();
        BtnDeleteJadwal = new javax.swing.JPanel();
        BtnSaveJadwal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        BtnBackJadwal = new javax.swing.JPanel();
        CreateJadwalPanel = new javax.swing.JPanel();
        CBKelas = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        CBMapel = new javax.swing.JComboBox();
        BtnCreateDataJadwal = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        CBGuru = new javax.swing.JComboBox();
        CBHari = new javax.swing.JComboBox();
        TxtJamSelesai = new javax.swing.JTextField();
        TxtJamMulai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CreateJadwalLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KiriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdminLogoutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoutPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        LogoutPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LogoutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LogoutPanelLayout = new javax.swing.GroupLayout(LogoutPanel);
        LogoutPanel.setLayout(LogoutPanelLayout);
        LogoutPanelLayout.setHorizontalGroup(
            LogoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        LogoutPanelLayout.setVerticalGroup(
            LogoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        AdminLogoutPanel.add(LogoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Login & Exit Admin.png")); // NOI18N
        AdminLogoutPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KiriPanel.add(AdminLogoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 264, 126));

        BtnDashboard.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnDashboardLayout = new javax.swing.GroupLayout(BtnDashboard);
        BtnDashboard.setLayout(BtnDashboardLayout);
        BtnDashboardLayout.setHorizontalGroup(
            BtnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnDashboardLayout.setVerticalGroup(
            BtnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 410, 60));

        BtnManMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnManMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnManMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnManMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnManMapelLayout = new javax.swing.GroupLayout(BtnManMapel);
        BtnManMapel.setLayout(BtnManMapelLayout);
        BtnManMapelLayout.setHorizontalGroup(
            BtnManMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnManMapelLayout.setVerticalGroup(
            BtnManMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnManMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 410, 60));

        BtnManGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnManGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnManGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnManGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnManGuruLayout = new javax.swing.GroupLayout(BtnManGuru);
        BtnManGuru.setLayout(BtnManGuruLayout);
        BtnManGuruLayout.setHorizontalGroup(
            BtnManGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnManGuruLayout.setVerticalGroup(
            BtnManGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnManGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 410, 60));

        BtnManKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnManKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnManKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnManKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnManKelasLayout = new javax.swing.GroupLayout(BtnManKelas);
        BtnManKelas.setLayout(BtnManKelasLayout);
        BtnManKelasLayout.setHorizontalGroup(
            BtnManKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnManKelasLayout.setVerticalGroup(
            BtnManKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnManKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 410, 60));

        BtnManJadPel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnManJadPel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnManJadPel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnManJadPelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnManJadPelLayout = new javax.swing.GroupLayout(BtnManJadPel);
        BtnManJadPel.setLayout(BtnManJadPelLayout);
        BtnManJadPelLayout.setHorizontalGroup(
            BtnManJadPelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnManJadPelLayout.setVerticalGroup(
            BtnManJadPelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnManJadPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 410, 60));

        BtnSetting.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSettingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSettingLayout = new javax.swing.GroupLayout(BtnSetting);
        BtnSetting.setLayout(BtnSettingLayout);
        BtnSettingLayout.setHorizontalGroup(
            BtnSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        BtnSettingLayout.setVerticalGroup(
            BtnSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 100, 60));

        BtnSettingBack.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSettingBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSettingBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSettingBackLayout = new javax.swing.GroupLayout(BtnSettingBack);
        BtnSettingBack.setLayout(BtnSettingBackLayout);
        BtnSettingBackLayout.setHorizontalGroup(
            BtnSettingBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        BtnSettingBackLayout.setVerticalGroup(
            BtnSettingBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnSettingBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 100, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Panel Kiri Admin.png")); // NOI18N
        KiriPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(KiriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 412, 768));

        KananPanel.setLayout(new java.awt.CardLayout());

        DashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Dashboard Frame Admin.png")); // NOI18N
        DashboardPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(DashboardPanel, "card2");

        ManMapelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCreateMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateMapelLayout = new javax.swing.GroupLayout(BtnCreateMapel);
        BtnCreateMapel.setLayout(BtnCreateMapelLayout);
        BtnCreateMapelLayout.setHorizontalGroup(
            BtnCreateMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnCreateMapelLayout.setVerticalGroup(
            BtnCreateMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ManMapelPanel.add(BtnCreateMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 50, 50));

        CRUDMapelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MapelTable.setModel(new javax.swing.table.DefaultTableModel(
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
        MapelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapelTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(MapelTable);

        CRUDMapelPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 550, 450));

        TxtKodeMapel.setEditable(false);
        TxtKodeMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKodeMapel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKodeMapel.setToolTipText("");
        TxtKodeMapel.setBorder(null);
        TxtKodeMapel.setOpaque(false);
        CRUDMapelPanel.add(TxtKodeMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 270, 30));

        BtnSaveMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSaveMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSaveMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSaveMapelLayout = new javax.swing.GroupLayout(BtnSaveMapel);
        BtnSaveMapel.setLayout(BtnSaveMapelLayout);
        BtnSaveMapelLayout.setHorizontalGroup(
            BtnSaveMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnSaveMapelLayout.setVerticalGroup(
            BtnSaveMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDMapelPanel.add(BtnSaveMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 680, 120, 40));

        TxtMapelUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtMapelUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtMapelUpdate.setToolTipText("");
        TxtMapelUpdate.setBorder(null);
        TxtMapelUpdate.setOpaque(false);
        CRUDMapelPanel.add(TxtMapelUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 270, 30));

        BtnDeleteMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnDeleteMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnDeleteMapelLayout = new javax.swing.GroupLayout(BtnDeleteMapel);
        BtnDeleteMapel.setLayout(BtnDeleteMapelLayout);
        BtnDeleteMapelLayout.setHorizontalGroup(
            BtnDeleteMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnDeleteMapelLayout.setVerticalGroup(
            BtnDeleteMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDMapelPanel.add(BtnDeleteMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 120, 40));

        TxtSingkatUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtSingkatUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtSingkatUpdate.setToolTipText("");
        TxtSingkatUpdate.setBorder(null);
        TxtSingkatUpdate.setOpaque(false);
        CRUDMapelPanel.add(TxtSingkatUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 270, 30));

        TxtSearchMapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSearchMapelActionPerformed(evt);
            }
        });
        TxtSearchMapel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchMapelKeyReleased(evt);
            }
        });
        CRUDMapelPanel.add(TxtSearchMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 380, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Mapel Frame.png")); // NOI18N
        CRUDMapelPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManMapelPanel.add(CRUDMapelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        BtnBackMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnBackMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBackMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnBackMapelLayout = new javax.swing.GroupLayout(BtnBackMapel);
        BtnBackMapel.setLayout(BtnBackMapelLayout);
        BtnBackMapelLayout.setHorizontalGroup(
            BtnBackMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        BtnBackMapelLayout.setVerticalGroup(
            BtnBackMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        ManMapelPanel.add(BtnBackMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 20, 30));

        CreateMapelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtMapel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtMapel.setBorder(null);
        TxtMapel.setOpaque(false);
        CreateMapelPanel.add(TxtMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 490, 30));

        TxtSingkat.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtSingkat.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtSingkat.setBorder(null);
        TxtSingkat.setOpaque(false);
        CreateMapelPanel.add(TxtSingkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 490, 30));

        BtnCreateDataMapel.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateDataMapel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateDataMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateDataMapelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateDataMapelLayout = new javax.swing.GroupLayout(BtnCreateDataMapel);
        BtnCreateDataMapel.setLayout(BtnCreateDataMapelLayout);
        BtnCreateDataMapelLayout.setHorizontalGroup(
            BtnCreateDataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        BtnCreateDataMapelLayout.setVerticalGroup(
            BtnCreateDataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CreateMapelPanel.add(BtnCreateDataMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 670, 140, 40));

        CreateMapelLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Mapel Frame Add.png")); // NOI18N
        CreateMapelPanel.add(CreateMapelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManMapelPanel.add(CreateMapelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(ManMapelPanel, "card3");

        ManGuruPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCreateGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateGuruLayout = new javax.swing.GroupLayout(BtnCreateGuru);
        BtnCreateGuru.setLayout(BtnCreateGuruLayout);
        BtnCreateGuruLayout.setHorizontalGroup(
            BtnCreateGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnCreateGuruLayout.setVerticalGroup(
            BtnCreateGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ManGuruPanel.add(BtnCreateGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 50, 50));

        CRUDGuruPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GuruTabel.setModel(new javax.swing.table.DefaultTableModel(
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
        GuruTabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuruTabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(GuruTabel);

        CRUDGuruPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 550, 450));

        TxtKodeGuru.setEditable(false);
        TxtKodeGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKodeGuru.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKodeGuru.setToolTipText("");
        TxtKodeGuru.setBorder(null);
        TxtKodeGuru.setOpaque(false);
        CRUDGuruPanel.add(TxtKodeGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 270, 30));

        TxtNIPUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtNIPUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtNIPUpdate.setToolTipText("");
        TxtNIPUpdate.setBorder(null);
        TxtNIPUpdate.setOpaque(false);
        CRUDGuruPanel.add(TxtNIPUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 270, 30));

        TxtNamaGuruUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtNamaGuruUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtNamaGuruUpdate.setToolTipText("");
        TxtNamaGuruUpdate.setBorder(null);
        TxtNamaGuruUpdate.setOpaque(false);
        CRUDGuruPanel.add(TxtNamaGuruUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 270, 30));

        BtnDeleteGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnDeleteGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnDeleteGuruLayout = new javax.swing.GroupLayout(BtnDeleteGuru);
        BtnDeleteGuru.setLayout(BtnDeleteGuruLayout);
        BtnDeleteGuruLayout.setHorizontalGroup(
            BtnDeleteGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnDeleteGuruLayout.setVerticalGroup(
            BtnDeleteGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDGuruPanel.add(BtnDeleteGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 120, 40));

        BtnSaveGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSaveGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSaveGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSaveGuruLayout = new javax.swing.GroupLayout(BtnSaveGuru);
        BtnSaveGuru.setLayout(BtnSaveGuruLayout);
        BtnSaveGuruLayout.setHorizontalGroup(
            BtnSaveGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnSaveGuruLayout.setVerticalGroup(
            BtnSaveGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDGuruPanel.add(BtnSaveGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 680, 120, 40));

        TxtSearchGuru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchGuruKeyReleased(evt);
            }
        });
        CRUDGuruPanel.add(TxtSearchGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 380, 30));

        CRUDGuruLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Guru Frame.png")); // NOI18N
        CRUDGuruPanel.add(CRUDGuruLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManGuruPanel.add(CRUDGuruPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CreateGuruPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtNIP.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtNIP.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtNIP.setBorder(null);
        TxtNIP.setOpaque(false);
        CreateGuruPanel.add(TxtNIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 490, 30));

        TxtNamaGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtNamaGuru.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtNamaGuru.setBorder(null);
        TxtNamaGuru.setOpaque(false);
        CreateGuruPanel.add(TxtNamaGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 490, 30));

        BtnCreateDataGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateDataGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateDataGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateDataGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateDataGuruLayout = new javax.swing.GroupLayout(BtnCreateDataGuru);
        BtnCreateDataGuru.setLayout(BtnCreateDataGuruLayout);
        BtnCreateDataGuruLayout.setHorizontalGroup(
            BtnCreateDataGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        BtnCreateDataGuruLayout.setVerticalGroup(
            BtnCreateDataGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CreateGuruPanel.add(BtnCreateDataGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 670, 140, 40));

        CreateGuruLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Guru Frame Add.png")); // NOI18N
        CreateGuruPanel.add(CreateGuruLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManGuruPanel.add(CreateGuruPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        BtnBackGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnBackGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBackGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnBackGuruLayout = new javax.swing.GroupLayout(BtnBackGuru);
        BtnBackGuru.setLayout(BtnBackGuruLayout);
        BtnBackGuruLayout.setHorizontalGroup(
            BtnBackGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        BtnBackGuruLayout.setVerticalGroup(
            BtnBackGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        ManGuruPanel.add(BtnBackGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 20, 30));

        KananPanel.add(ManGuruPanel, "card4");

        ManKelasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCreateKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateKelasLayout = new javax.swing.GroupLayout(BtnCreateKelas);
        BtnCreateKelas.setLayout(BtnCreateKelasLayout);
        BtnCreateKelasLayout.setHorizontalGroup(
            BtnCreateKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnCreateKelasLayout.setVerticalGroup(
            BtnCreateKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ManKelasPanel.add(BtnCreateKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 50, 50));

        CRUDKelasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KelasTable.setModel(new javax.swing.table.DefaultTableModel(
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
        KelasTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KelasTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(KelasTable);

        CRUDKelasPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 550, 450));

        TxtKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKelas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKelas.setToolTipText("");
        TxtKelas.setBorder(null);
        TxtKelas.setOpaque(false);
        CRUDKelasPanel.add(TxtKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 270, 30));

        TxtKodeKelas.setEditable(false);
        TxtKodeKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKodeKelas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKodeKelas.setToolTipText("");
        TxtKodeKelas.setBorder(null);
        TxtKodeKelas.setOpaque(false);
        CRUDKelasPanel.add(TxtKodeKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 270, 30));

        BtnDeleteKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnDeleteKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnDeleteKelasLayout = new javax.swing.GroupLayout(BtnDeleteKelas);
        BtnDeleteKelas.setLayout(BtnDeleteKelasLayout);
        BtnDeleteKelasLayout.setHorizontalGroup(
            BtnDeleteKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnDeleteKelasLayout.setVerticalGroup(
            BtnDeleteKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDKelasPanel.add(BtnDeleteKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 120, 40));

        BtnSaveKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSaveKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSaveKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSaveKelasLayout = new javax.swing.GroupLayout(BtnSaveKelas);
        BtnSaveKelas.setLayout(BtnSaveKelasLayout);
        BtnSaveKelasLayout.setHorizontalGroup(
            BtnSaveKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnSaveKelasLayout.setVerticalGroup(
            BtnSaveKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDKelasPanel.add(BtnSaveKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 680, 120, 40));

        TxtSearchKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchKelasKeyReleased(evt);
            }
        });
        CRUDKelasPanel.add(TxtSearchKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 380, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Kelas Frame.png")); // NOI18N
        CRUDKelasPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManKelasPanel.add(CRUDKelasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        BtnBackKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnBackKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBackKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnBackKelasLayout = new javax.swing.GroupLayout(BtnBackKelas);
        BtnBackKelas.setLayout(BtnBackKelasLayout);
        BtnBackKelasLayout.setHorizontalGroup(
            BtnBackKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        BtnBackKelasLayout.setVerticalGroup(
            BtnBackKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        ManKelasPanel.add(BtnBackKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 20, 30));

        CreateKelasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtCreateKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtCreateKelas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtCreateKelas.setBorder(null);
        TxtCreateKelas.setOpaque(false);
        CreateKelasPanel.add(TxtCreateKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 490, 30));

        BtnCreateDataKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateDataKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateDataKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateDataKelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateDataKelasLayout = new javax.swing.GroupLayout(BtnCreateDataKelas);
        BtnCreateDataKelas.setLayout(BtnCreateDataKelasLayout);
        BtnCreateDataKelasLayout.setHorizontalGroup(
            BtnCreateDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        BtnCreateDataKelasLayout.setVerticalGroup(
            BtnCreateDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CreateKelasPanel.add(BtnCreateDataKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 670, 140, 40));

        CreateKelasLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Kelas Frame Add.png")); // NOI18N
        CreateKelasPanel.add(CreateKelasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManKelasPanel.add(CreateKelasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(ManKelasPanel, "card5");

        ManJadGurPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManJadGurPanelMouseClicked(evt);
            }
        });
        ManJadGurPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Jadwal Guru Frame.png")); // NOI18N
        ManJadGurPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(ManJadGurPanel, "card6");

        ManJadPelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCreateJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateJadwalLayout = new javax.swing.GroupLayout(BtnCreateJadwal);
        BtnCreateJadwal.setLayout(BtnCreateJadwalLayout);
        BtnCreateJadwalLayout.setHorizontalGroup(
            BtnCreateJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnCreateJadwalLayout.setVerticalGroup(
            BtnCreateJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ManJadPelPanel.add(BtnCreateJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 50, 50));

        CRUDJadwalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JadwalTable.setModel(new javax.swing.table.DefaultTableModel(
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
        JadwalTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JadwalTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(JadwalTable);

        CRUDJadwalPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 550, 450));

        TxtKodeJadwal.setEditable(false);
        TxtKodeJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKodeJadwal.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKodeJadwal.setToolTipText("");
        TxtKodeJadwal.setBorder(null);
        TxtKodeJadwal.setOpaque(false);
        CRUDJadwalPanel.add(TxtKodeJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 270, 30));

        CBHariUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBHariUpdateItemStateChanged(evt);
            }
        });
        CRUDJadwalPanel.add(CBHariUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 346, 100, 30));

        CBKelasUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBKelasUpdateItemStateChanged(evt);
            }
        });
        CRUDJadwalPanel.add(CBKelasUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 270, 30));

        CBMapelUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBMapelUpdateItemStateChanged(evt);
            }
        });
        CRUDJadwalPanel.add(CBMapelUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, 270, 30));

        CBGuruUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBGuruUpdateItemStateChanged(evt);
            }
        });
        CRUDJadwalPanel.add(CBGuruUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 270, 30));

        TxtJamMulaiUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtJamMulaiUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtJamMulaiUpdate.setText("00.00");
        TxtJamMulaiUpdate.setToolTipText("");
        TxtJamMulaiUpdate.setBorder(null);
        TxtJamMulaiUpdate.setOpaque(false);
        CRUDJadwalPanel.add(TxtJamMulaiUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 350, 50, 30));

        TxtJamSelesaiUpdate.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtJamSelesaiUpdate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtJamSelesaiUpdate.setText("00.00");
        TxtJamSelesaiUpdate.setToolTipText("");
        TxtJamSelesaiUpdate.setBorder(null);
        TxtJamSelesaiUpdate.setOpaque(false);
        CRUDJadwalPanel.add(TxtJamSelesaiUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, 50, 30));

        BtnDeleteJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnDeleteJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeleteJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnDeleteJadwalLayout = new javax.swing.GroupLayout(BtnDeleteJadwal);
        BtnDeleteJadwal.setLayout(BtnDeleteJadwalLayout);
        BtnDeleteJadwalLayout.setHorizontalGroup(
            BtnDeleteJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnDeleteJadwalLayout.setVerticalGroup(
            BtnDeleteJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDJadwalPanel.add(BtnDeleteJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 120, 40));

        BtnSaveJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnSaveJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSaveJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnSaveJadwalLayout = new javax.swing.GroupLayout(BtnSaveJadwal);
        BtnSaveJadwal.setLayout(BtnSaveJadwalLayout);
        BtnSaveJadwalLayout.setHorizontalGroup(
            BtnSaveJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        BtnSaveJadwalLayout.setVerticalGroup(
            BtnSaveJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CRUDJadwalPanel.add(BtnSaveJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 680, 120, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("-");
        CRUDJadwalPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 10, -1));
        CRUDJadwalPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 380, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Jadwal Pelajaran Frame.png")); // NOI18N
        CRUDJadwalPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManJadPelPanel.add(CRUDJadwalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        BtnBackJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnBackJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBackJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnBackJadwalLayout = new javax.swing.GroupLayout(BtnBackJadwal);
        BtnBackJadwal.setLayout(BtnBackJadwalLayout);
        BtnBackJadwalLayout.setHorizontalGroup(
            BtnBackJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        BtnBackJadwalLayout.setVerticalGroup(
            BtnBackJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        ManJadPelPanel.add(BtnBackJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 20, 30));

        CreateJadwalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CBKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBKelasItemStateChanged(evt);
            }
        });
        CBKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBKelasActionPerformed(evt);
            }
        });
        CreateJadwalPanel.add(CBKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 170, -1));

        jLabel13.setText("Mapel :");
        CreateJadwalPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, -1, -1));

        jLabel15.setText("Kelas :");
        CreateJadwalPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        CBMapel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBMapelItemStateChanged(evt);
            }
        });
        CBMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBMapelMouseClicked(evt);
            }
        });
        CreateJadwalPanel.add(CBMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 180, -1));

        BtnCreateDataJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnCreateDataJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCreateDataJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateDataJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnCreateDataJadwalLayout = new javax.swing.GroupLayout(BtnCreateDataJadwal);
        BtnCreateDataJadwal.setLayout(BtnCreateDataJadwalLayout);
        BtnCreateDataJadwalLayout.setHorizontalGroup(
            BtnCreateDataJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        BtnCreateDataJadwalLayout.setVerticalGroup(
            BtnCreateDataJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        CreateJadwalPanel.add(BtnCreateDataJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 670, 140, 40));

        jLabel12.setText("Jam Selesai :");
        CreateJadwalPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, -1));

        jLabel11.setText("Jam Mulai :");
        CreateJadwalPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        jLabel10.setText("Hari :");
        CreateJadwalPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel16.setText("Guru :");
        CreateJadwalPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, -1));

        CBGuru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBGuruItemStateChanged(evt);
            }
        });
        CreateJadwalPanel.add(CBGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 230, -1));

        CBHari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBHariItemStateChanged(evt);
            }
        });
        CreateJadwalPanel.add(CBHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 90, -1));

        TxtJamSelesai.setHorizontalAlignment(JTextField.CENTER);
        TxtJamSelesai.setText("00.00");
        CreateJadwalPanel.add(TxtJamSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 90, -1));

        TxtJamMulai.setHorizontalAlignment(JTextField.CENTER);
        TxtJamMulai.setText("00.00");
        CreateJadwalPanel.add(TxtJamMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 90, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("-");
        CreateJadwalPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 10, -1));

        CreateJadwalLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Manage Jadwal Pelajaran Frame Add.png")); // NOI18N
        CreateJadwalPanel.add(CreateJadwalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ManJadPelPanel.add(CreateJadwalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(ManJadPelPanel, "card7");

        getContentPane().add(KananPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 955, 768));

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

    private void BtnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSettingMouseClicked
        // TODO add your handling code here:
        AdminLogoutPanel.setVisible(true);
        AdminLogoutPanel.setEnabled(true);
        BtnSettingBack.setEnabled(true);
        BtnSettingBack.setVisible(true);
        BtnSetting.setEnabled(false);
        BtnSetting.setVisible(false);
    }//GEN-LAST:event_BtnSettingMouseClicked

    private void BtnSettingBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSettingBackMouseClicked
        // TODO add your handling code here:
        BtnSetting.setEnabled(true);
        BtnSetting.setVisible(true);
        AdminLogoutPanel.setVisible(false);
        AdminLogoutPanel.setEnabled(false);
        BtnSettingBack.setEnabled(false);
        BtnSettingBack.setVisible(false);
    }//GEN-LAST:event_BtnSettingBackMouseClicked

    private void ManJadGurPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManJadGurPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ManJadGurPanelMouseClicked

    private void LogoutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutPanelMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response==JOptionPane.YES_OPTION) {
            GuestMainFrame main = new GuestMainFrame();
            main.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_LogoutPanelMouseClicked

    private void BtnDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDashboardMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(true);
        ManMapelPanel.setVisible(false);
        ManGuruPanel.setVisible(false);
        ManKelasPanel.setVisible(false);
        ManJadGurPanel.setVisible(false);
        ManJadPelPanel.setVisible(false);
    }//GEN-LAST:event_BtnDashboardMouseClicked

    private void BtnManMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnManMapelMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        ManMapelPanel.setVisible(true);
        ManGuruPanel.setVisible(false);
        ManKelasPanel.setVisible(false);
        ManJadPelPanel.setVisible(false);
    }//GEN-LAST:event_BtnManMapelMouseClicked

    private void BtnManGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnManGuruMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        ManMapelPanel.setVisible(false);
        ManGuruPanel.setVisible(true);
        ManKelasPanel.setVisible(false);
        ManJadPelPanel.setVisible(false);
    }//GEN-LAST:event_BtnManGuruMouseClicked

    private void BtnManKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnManKelasMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        ManMapelPanel.setVisible(false);
        ManGuruPanel.setVisible(false);
        ManKelasPanel.setVisible(true);
        ManJadPelPanel.setVisible(false);
    }//GEN-LAST:event_BtnManKelasMouseClicked

    private void BtnManJadPelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnManJadPelMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        ManMapelPanel.setVisible(false);
        ManGuruPanel.setVisible(false);
        ManKelasPanel.setVisible(false);
        ManJadPelPanel.setVisible(true);
        load_tableJadwal();
    }//GEN-LAST:event_BtnManJadPelMouseClicked

    private void BtnCreateGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateGuruMouseClicked
        // TODO add your handling code here:
        CreateGuruPanel.setVisible(true);
        CreateGuruPanel.setEnabled(true);
        CRUDGuruPanel.setVisible(false);
        CRUDGuruPanel.setEnabled(false);
        BtnBackGuru.setEnabled(true);
        BtnBackGuru.setVisible(true);
        BtnCreateGuru.setEnabled(false);
        BtnCreateGuru.setVisible(false);
    }//GEN-LAST:event_BtnCreateGuruMouseClicked

    private void BtnBackGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackGuruMouseClicked
        // TODO add your handling code here:
        CreateGuruPanel.setVisible(false);
        CreateGuruPanel.setEnabled(false);
        CRUDGuruPanel.setVisible(true);
        CRUDGuruPanel.setEnabled(true);
        BtnBackGuru.setEnabled(false);
        BtnBackGuru.setVisible(false);
        BtnCreateGuru.setEnabled(true);
        BtnCreateGuru.setVisible(true);
    }//GEN-LAST:event_BtnBackGuruMouseClicked

    private void BtnCreateDataGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateDataGuruMouseClicked
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO `tb_guru`(`nip`, `guru`) VALUES ('"+TxtNIP.getText()+"','"+TxtNamaGuru.getText()+"')";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Created");
            load_tableGuru();
            Clean();
            BackCRUDGuru();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnCreateDataGuruMouseClicked

    private void GuruTabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuruTabelMouseClicked
        // TODO add your handling code here:
        int row = GuruTabel.rowAtPoint(evt.getPoint());
        String guru_id =GuruTabel.getValueAt(row, 0).toString();
        TxtKodeGuru.setText(guru_id);
        String NIP = GuruTabel.getValueAt(row,1).toString();
        TxtNIPUpdate.setText(NIP);
        String nama =GuruTabel.getValueAt(row, 2).toString();
        TxtNamaGuruUpdate.setText(nama);
    }//GEN-LAST:event_GuruTabelMouseClicked

    private void BtnSaveGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveGuruMouseClicked
        // TODO add your handling code here:
        try {
            String sql ="UPDATE tb_guru SET guru_id = '"+TxtKodeGuru.getText()+"', nip = '"+TxtNIPUpdate.getText()+"', guru = '"+TxtNamaGuruUpdate.getText()+"' WHERE guru_id = '"+TxtKodeGuru.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Edited");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Edit Data Failed"+e.getMessage());
        }
        load_tableGuru();
        Clean(); 
    }//GEN-LAST:event_BtnSaveGuruMouseClicked

    private void BtnDeleteGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteGuruMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response==JOptionPane.YES_OPTION) {
            try {
            String sql ="delete from tb_guru where guru_id='"+TxtKodeGuru.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_tableGuru();
        Clean();
        }
        
    }//GEN-LAST:event_BtnDeleteGuruMouseClicked

    private void BtnCreateMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateMapelMouseClicked
        // TODO add your handling code here:
        CreateMapelPanel.setVisible(true);
        CreateMapelPanel.setEnabled(true);
        CRUDMapelPanel.setVisible(false);
        CRUDMapelPanel.setEnabled(false);
        BtnBackMapel.setEnabled(true);
        BtnBackMapel.setVisible(true);
        BtnCreateMapel.setEnabled(false);
        BtnCreateMapel.setVisible(false);
    }//GEN-LAST:event_BtnCreateMapelMouseClicked

    private void BtnBackMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMapelMouseClicked
        // TODO add your handling code here:
        CreateMapelPanel.setVisible(false);
        CreateMapelPanel.setEnabled(false);
        CRUDMapelPanel.setVisible(true);
        CRUDMapelPanel.setEnabled(true);
        BtnBackMapel.setEnabled(false);
        BtnBackMapel.setVisible(false);
        BtnCreateMapel.setEnabled(true);
        BtnCreateMapel.setVisible(true);
    }//GEN-LAST:event_BtnBackMapelMouseClicked

    private void MapelTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapelTableMouseClicked
        // TODO add your handling code here:
        int row = MapelTable.rowAtPoint(evt.getPoint());
        String mapel_id =MapelTable.getValueAt(row, 0).toString();
        TxtKodeMapel.setText(mapel_id);
        String mapel = MapelTable.getValueAt(row,1).toString();
        TxtMapelUpdate.setText(mapel);
        String singkat =MapelTable.getValueAt(row, 2).toString();
        TxtSingkatUpdate.setText(singkat);
    }//GEN-LAST:event_MapelTableMouseClicked

    private void BtnDeleteMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteMapelMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response==JOptionPane.YES_OPTION) {
            try {
            String sql ="delete from tb_mapel where mapel_id='"+TxtKodeMapel.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_tableMapel();
        Clean();
        }
        
    }//GEN-LAST:event_BtnDeleteMapelMouseClicked

    private void BtnSaveMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMapelMouseClicked
        // TODO add your handling code here:
        try {
            String sql ="UPDATE tb_mapel SET mapel_id = '"+TxtKodeMapel.getText()+"', mapel = '"+TxtMapelUpdate.getText()+"', singkatan = '"+TxtSingkatUpdate.getText()+"' WHERE mapel_id = '"+TxtKodeMapel.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Edited");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Edit Data Failed"+e.getMessage());
        }
        load_tableMapel();
        Clean(); 
    }//GEN-LAST:event_BtnSaveMapelMouseClicked

    private void BtnCreateDataMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateDataMapelMouseClicked
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO `tb_mapel`(`mapel`, `singkatan`) VALUES ('"+TxtMapel.getText()+"','"+TxtSingkat.getText()+"')";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Created");
            load_tableMapel();
            Clean();
            BackCRUDMapel();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnCreateDataMapelMouseClicked

    private void BtnCreateKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateKelasMouseClicked
        // TODO add your handling code here:
        CreateKelasPanel.setVisible(true);
        CreateKelasPanel.setEnabled(true);
        CRUDKelasPanel.setVisible(false);
        CRUDKelasPanel.setEnabled(false);
        BtnBackKelas.setEnabled(true);
        BtnBackKelas.setVisible(true);
        BtnCreateKelas.setEnabled(false);
        BtnCreateKelas.setVisible(false);
    }//GEN-LAST:event_BtnCreateKelasMouseClicked

    private void BtnDeleteKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteKelasMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response==JOptionPane.YES_OPTION) {
            try {
            String sql ="delete from tb_kelas where kelas_id='"+TxtKodeKelas.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_tableKelas();
        load_tableJadwal();
        dataCBKelas();
        Clean();
        }
    }//GEN-LAST:event_BtnDeleteKelasMouseClicked

    private void BtnSaveKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveKelasMouseClicked
        // TODO add your handling code here:
        try {
            String sql ="UPDATE tb_kelas SET kelas_id = '"+TxtKodeKelas.getText()+"', kelas = '"+TxtKelas.getText()+"' WHERE kelas_id = '"+TxtKodeKelas.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Edited");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Edit Data Failed"+e.getMessage());
        }
        load_tableKelas();
        Clean();
    }//GEN-LAST:event_BtnSaveKelasMouseClicked

    private void BtnBackKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackKelasMouseClicked
        // TODO add your handling code here:
        CreateKelasPanel.setVisible(false);
        CreateKelasPanel.setEnabled(false);
        CRUDKelasPanel.setVisible(true);
        CRUDKelasPanel.setEnabled(true);
        BtnBackKelas.setEnabled(false);
        BtnBackKelas.setVisible(false);
        BtnCreateKelas.setEnabled(true);
        BtnCreateKelas.setVisible(true);
    }//GEN-LAST:event_BtnBackKelasMouseClicked

    private void BtnCreateDataKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateDataKelasMouseClicked
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO `tb_kelas`(`kelas`) VALUES ('"+TxtCreateKelas.getText()+"')";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Created");
            load_tableKelas();
            Clean();
            BackCRUDKelas();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnCreateDataKelasMouseClicked

    private void KelasTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelasTableMouseClicked
        // TODO add your handling code here:
        int row = KelasTable.rowAtPoint(evt.getPoint());
        String kelas_id =KelasTable.getValueAt(row, 0).toString();
        TxtKodeKelas.setText(kelas_id);
        String kelas =KelasTable.getValueAt(row, 1).toString();
        TxtKelas.setText(kelas);
    }//GEN-LAST:event_KelasTableMouseClicked

    private void BtnCreateJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateJadwalMouseClicked
        // TODO add your handling code here:
        CreateJadwalPanel.setVisible(true);
        CreateJadwalPanel.setEnabled(true);
        CRUDJadwalPanel.setVisible(false);
        CRUDJadwalPanel.setEnabled(false);
        BtnBackJadwal.setEnabled(true);
        BtnBackJadwal.setVisible(true);
        BtnCreateJadwal.setEnabled(false);
        BtnCreateJadwal.setVisible(false);
    }//GEN-LAST:event_BtnCreateJadwalMouseClicked

    private void JadwalTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JadwalTableMouseClicked
        // TODO add your handling code here:
        int row = JadwalTable.rowAtPoint(evt.getPoint());
        String jadwal_id =JadwalTable.getValueAt(row, 0).toString();
        TxtKodeJadwal.setText(jadwal_id);
        String hari =JadwalTable.getValueAt(row, 1).toString();
        CBHariUpdate.setSelectedItem(hari);
        String jam_mulai =JadwalTable.getValueAt(row, 2).toString();
        TxtJamMulaiUpdate.setText(jam_mulai);
        String jam_selesai =JadwalTable.getValueAt(row, 3).toString();
        TxtJamSelesaiUpdate.setText(jam_selesai);
        String kelas =JadwalTable.getValueAt(row, 4).toString();
        CBKelasUpdate.setSelectedItem(kelas);
        String mapel =JadwalTable.getValueAt(row, 5).toString();
        CBMapelUpdate.setSelectedItem(mapel);
        String guru =JadwalTable.getValueAt(row, 6).toString();
        CBGuruUpdate.setSelectedItem(guru);
        
    }//GEN-LAST:event_JadwalTableMouseClicked

    private void BtnDeleteJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDeleteJadwalMouseClicked
        // TODO add your handling code here:
        try {
            String sql ="delete from tb_jadwal where jadwal_id='"+TxtKodeJadwal.getText()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_tableJadwal();
        Clean();
    }//GEN-LAST:event_BtnDeleteJadwalMouseClicked

    private void BtnSaveJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveJadwalMouseClicked
        // TODO add your handling code here:
        String jam = TxtJamMulaiUpdate.getText();
        try {
            String sqls = "SELECT COUNT(*) FROM `tb_jadwal` WHERE"
                    + " `hari_id` = '"+hari_id+"' AND `jam_mulai` = '"+jam+"' AND `guru_id` = '"+guru_id+"'"
                    + " AND NOT `jam_mulai` = '08.00' AND NOT `guru_id` = '8'";
            java.sql.Connection con=(Connection)Connect.configDB();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sqls);
            while(res.next()){
                if (res.getInt(1) > 0 ) {
                    JOptionPane.showMessageDialog(null, "Data Cannot Duplicate");
                } else {
                    try {
                        String sql = "UPDATE tb_jadwal SET jadwal_id = '"+TxtKodeJadwal.getText()+"', hari_id = '"+hari_id+"', "
                    + "jam_mulai = '"+TxtJamMulaiUpdate.getText()+"', jam_selesai = '"+TxtJamSelesaiUpdate.getText()+"', "
                    + "kelas_id = '"+kelas_id+"', mapel_id = '"+mapel_id+"', guru_id = '"+guru_id+"' "
                    + "WHERE jadwal_id = '"+TxtKodeJadwal.getText()+"'";
                        java.sql.Connection conn=(Connection)Connect.configDB();
                        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Data Edited");
                        load_tableJadwal();
                        Clean();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnSaveJadwalMouseClicked

    private void BtnBackJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackJadwalMouseClicked
        // TODO add your handling code here:
        CreateJadwalPanel.setVisible(false);
        CreateJadwalPanel.setEnabled(false);
        CRUDJadwalPanel.setVisible(true);
        CRUDJadwalPanel.setEnabled(true);
        BtnBackJadwal.setEnabled(false);
        BtnBackJadwal.setVisible(false);
        BtnCreateJadwal.setEnabled(true);
        BtnCreateJadwal.setVisible(true);
    }//GEN-LAST:event_BtnBackJadwalMouseClicked

    private void BtnCreateDataJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCreateDataJadwalMouseClicked
        // TODO add your handling code here:
        String jam = TxtJamMulai.getText();
        try {
            String sqls = "SELECT COUNT(*) FROM `tb_jadwal` WHERE"
                    + " `hari_id` = '"+hari_id+"' AND `jam_mulai` = '"+jam+"' AND `guru_id` = '"+guru_id+"'"
                    + " AND NOT `jam_mulai` = '08.00' AND NOT `guru_id` = '8'";
            java.sql.Connection con=(Connection)Connect.configDB();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sqls);
            while(res.next()){
                if (res.getInt(1) > 0 ) {
                    JOptionPane.showMessageDialog(null, "Data Cannot Duplicate");
                } else {
                    try {
                        String sql = "INSERT INTO `tb_jadwal`"
                                + "(`hari_id`, `jam_mulai`, `jam_selesai`, `kelas_id`, `mapel_id`, `guru_id`) "
                                + "VALUES ('"+hari_id+"', '"+TxtJamMulai.getText()+"', '"+TxtJamSelesai.getText()+"', '"+kelas_id+"', '"+mapel_id+"', '"+guru_id+"')";
                        java.sql.Connection conn=(Connection)Connect.configDB();
                        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Data Created");
                        load_tableJadwal();
                        Clean();
                        BackCRUDJadwal();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnCreateDataJadwalMouseClicked

    private void CBKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBKelasActionPerformed

    private void CBKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBKelasItemStateChanged
        // TODO add your handling code here:
        String Kelas = CBKelas.getSelectedItem().toString();
        
        getKelas(Kelas);
    }//GEN-LAST:event_CBKelasItemStateChanged

    private void CBMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBMapelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CBMapelMouseClicked

    private void CBGuruItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBGuruItemStateChanged
        // TODO add your handling code here:
        String Guru = CBGuru.getSelectedItem().toString();
        
        getGuru(Guru);
    }//GEN-LAST:event_CBGuruItemStateChanged

    private void CBMapelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBMapelItemStateChanged
        // TODO add your handling code here:
        String Mapel = CBMapel.getSelectedItem().toString();
        
        getMapel(Mapel);
    }//GEN-LAST:event_CBMapelItemStateChanged

    private void CBKelasUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBKelasUpdateItemStateChanged
        // TODO add your handling code here:
        String Kelas = CBKelasUpdate.getSelectedItem().toString();
        
        getKelas(Kelas);
    }//GEN-LAST:event_CBKelasUpdateItemStateChanged

    private void CBMapelUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBMapelUpdateItemStateChanged
        // TODO add your handling code here:
        String Mapel = CBMapelUpdate.getSelectedItem().toString();
        
        getMapel(Mapel);
    }//GEN-LAST:event_CBMapelUpdateItemStateChanged

    private void CBGuruUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBGuruUpdateItemStateChanged
        // TODO add your handling code here:
        String Guru = CBGuruUpdate.getSelectedItem().toString();
        
        getGuru(Guru);
    }//GEN-LAST:event_CBGuruUpdateItemStateChanged

    private void CBHariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBHariItemStateChanged
        // TODO add your handling code here:
        String Hari = CBHari.getSelectedItem().toString();
        
        getHari(Hari);
    }//GEN-LAST:event_CBHariItemStateChanged

    private void CBHariUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBHariUpdateItemStateChanged
        // TODO add your handling code here:
        String Hari = CBHariUpdate.getSelectedItem().toString();
        
        getHari(Hari);
    }//GEN-LAST:event_CBHariUpdateItemStateChanged

    private void TxtSearchMapelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchMapelKeyReleased
        // TODO add your handling code here:
        String key = TxtSearchMapel.getText();
        
        if(key!=""){
            cariDataMapel(key);
        }else{
            load_tableMapel();
        }
    }//GEN-LAST:event_TxtSearchMapelKeyReleased

    private void TxtSearchMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSearchMapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSearchMapelActionPerformed

    private void TxtSearchGuruKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchGuruKeyReleased
        // TODO add your handling code here:
        String key = TxtSearchGuru.getText(); 
        
        if(key!=""){
            cariDataGuru(key);
        }else{
            load_tableGuru();
        }
    }//GEN-LAST:event_TxtSearchGuruKeyReleased

    private void TxtSearchKelasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKelasKeyReleased
        // TODO add your handling code here:
        String key = TxtSearchKelas.getText(); 
        
        if(key!=""){
            cariDataKelas(key);
        }else{
            load_tableKelas();
        }
    }//GEN-LAST:event_TxtSearchKelasKeyReleased

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
            java.util.logging.Logger.getLogger(AdminMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminLogoutPanel;
    private javax.swing.JPanel BtnBackGuru;
    private javax.swing.JPanel BtnBackJadwal;
    private javax.swing.JPanel BtnBackKelas;
    private javax.swing.JPanel BtnBackMapel;
    private javax.swing.JPanel BtnCreateDataGuru;
    private javax.swing.JPanel BtnCreateDataJadwal;
    private javax.swing.JPanel BtnCreateDataKelas;
    private javax.swing.JPanel BtnCreateDataMapel;
    private javax.swing.JPanel BtnCreateGuru;
    private javax.swing.JPanel BtnCreateJadwal;
    private javax.swing.JPanel BtnCreateKelas;
    private javax.swing.JPanel BtnCreateMapel;
    private javax.swing.JPanel BtnDashboard;
    private javax.swing.JPanel BtnDeleteGuru;
    private javax.swing.JPanel BtnDeleteJadwal;
    private javax.swing.JPanel BtnDeleteKelas;
    private javax.swing.JPanel BtnDeleteMapel;
    private javax.swing.JPanel BtnManGuru;
    private javax.swing.JPanel BtnManJadPel;
    private javax.swing.JPanel BtnManKelas;
    private javax.swing.JPanel BtnManMapel;
    private javax.swing.JPanel BtnSaveGuru;
    private javax.swing.JPanel BtnSaveJadwal;
    private javax.swing.JPanel BtnSaveKelas;
    private javax.swing.JPanel BtnSaveMapel;
    private javax.swing.JPanel BtnSetting;
    private javax.swing.JPanel BtnSettingBack;
    private javax.swing.JComboBox CBGuru;
    private javax.swing.JComboBox CBGuruUpdate;
    private javax.swing.JComboBox CBHari;
    private javax.swing.JComboBox CBHariUpdate;
    private javax.swing.JComboBox CBKelas;
    private javax.swing.JComboBox CBKelasUpdate;
    private javax.swing.JComboBox CBMapel;
    private javax.swing.JComboBox CBMapelUpdate;
    private javax.swing.JLabel CRUDGuruLabel;
    private javax.swing.JPanel CRUDGuruPanel;
    private javax.swing.JPanel CRUDJadwalPanel;
    private javax.swing.JPanel CRUDKelasPanel;
    private javax.swing.JPanel CRUDMapelPanel;
    private javax.swing.JLabel CreateGuruLabel;
    private javax.swing.JPanel CreateGuruPanel;
    private javax.swing.JLabel CreateJadwalLabel;
    private javax.swing.JPanel CreateJadwalPanel;
    private javax.swing.JLabel CreateKelasLabel;
    private javax.swing.JPanel CreateKelasPanel;
    private javax.swing.JLabel CreateMapelLabel;
    private javax.swing.JPanel CreateMapelPanel;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JTable GuruTabel;
    private javax.swing.JTable JadwalTable;
    private javax.swing.JPanel KananPanel;
    private javax.swing.JTable KelasTable;
    private javax.swing.JPanel KiriPanel;
    private javax.swing.JPanel LogoutPanel;
    private javax.swing.JPanel ManGuruPanel;
    private javax.swing.JPanel ManJadGurPanel;
    private javax.swing.JPanel ManJadPelPanel;
    private javax.swing.JPanel ManKelasPanel;
    private javax.swing.JPanel ManMapelPanel;
    private javax.swing.JTable MapelTable;
    private javax.swing.JTextField TxtCreateKelas;
    private javax.swing.JTextField TxtJamMulai;
    private javax.swing.JTextField TxtJamMulaiUpdate;
    private javax.swing.JTextField TxtJamSelesai;
    private javax.swing.JTextField TxtJamSelesaiUpdate;
    private javax.swing.JTextField TxtKelas;
    private javax.swing.JTextField TxtKodeGuru;
    private javax.swing.JTextField TxtKodeJadwal;
    private javax.swing.JTextField TxtKodeKelas;
    private javax.swing.JTextField TxtKodeMapel;
    private javax.swing.JTextField TxtMapel;
    private javax.swing.JTextField TxtMapelUpdate;
    private javax.swing.JTextField TxtNIP;
    private javax.swing.JTextField TxtNIPUpdate;
    private javax.swing.JTextField TxtNamaGuru;
    private javax.swing.JTextField TxtNamaGuruUpdate;
    private javax.swing.JTextField TxtSearchGuru;
    private javax.swing.JTextField TxtSearchKelas;
    private javax.swing.JTextField TxtSearchMapel;
    private javax.swing.JTextField TxtSingkat;
    private javax.swing.JTextField TxtSingkatUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
