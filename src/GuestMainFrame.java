
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yayak Firmansyah
 */
public class GuestMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form GuestMainFrame
     */
    public GuestMainFrame() {
        initComponents();
        dataCBGuru();
        dataCBKelas();
        setBackground(new Color(0, 0, 0, 0));
        LoginExitPanel.setEnabled(false);
        LoginExitPanel.setVisible(false);
        BtnSettingBack.setEnabled(false);
        BtnSettingBack.setVisible(false);
    }
    
    private void load_tableguru(String findguru){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kelas");
        model.addColumn("Jam Mulai");
        model.addColumn("Jam Selesai");
        model.addColumn("Mata Pelajaran");
        
        try {
            int no = 1;
            String sql = "SELECT `tb_kelas`.`kelas`, `tb_jadwal`.`jam_mulai`, `tb_jadwal`.`jam_selesai`, "
                    + "`tb_mapel`.`singkatan` FROM `tb_jadwal` "
                    + "JOIN `tb_hari` ON `tb_jadwal`.`hari_id` = `tb_hari`.`hari_id` "
                    + "JOIN `tb_kelas` ON `tb_jadwal`.`kelas_id` = `tb_kelas`.`kelas_id` "
                    + "JOIN `tb_mapel` ON `tb_jadwal`.`mapel_id` = `tb_mapel`.`mapel_id` "
                    + "JOIN `tb_guru` ON `tb_jadwal`.`guru_id` = `tb_guru`.`guru_id` "
                    + "WHERE `tb_guru`.`guru` = '"+findguru+"' AND `tb_hari`.`hari` = '"+CBHariGuru.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            JadwalGuruTable.setModel(model);
        } catch (Exception e) {
        }
    }
    
    private void load_tablekelas(String findkelas){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jam Mulai");
        model.addColumn("Jam Selesai");
        model.addColumn("Mata Pelajaran");
        model.addColumn("Guru");
        
        try {
            int no = 1;
            String sql = "SELECT `tb_jadwal`.`jam_mulai`, `tb_jadwal`.`jam_selesai`, "
                    + "`tb_mapel`.`singkatan`, `tb_guru`.`guru` FROM `tb_jadwal` "
                    + "JOIN `tb_hari` ON `tb_jadwal`.`hari_id` = `tb_hari`.`hari_id` "
                    + "JOIN `tb_kelas` ON `tb_jadwal`.`kelas_id` = `tb_kelas`.`kelas_id` "
                    + "JOIN `tb_mapel` ON `tb_jadwal`.`mapel_id` = `tb_mapel`.`mapel_id` "
                    + "JOIN `tb_guru` ON `tb_jadwal`.`guru_id` = `tb_guru`.`guru_id` "
                    + "WHERE `tb_kelas`.`kelas` = '"+findkelas+"' AND `tb_hari`.`hari` = '"+CBHariJadwal.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),});
            }
            JadwalPelajaranTable.setModel(model);
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
                CBPilihGuru.addItem(res.getString("guru"));
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
                CBPilihKelas.addItem(res.getString("kelas"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (Exception e) {
        }
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
        BtnDashboard = new javax.swing.JPanel();
        BtnJadwal = new javax.swing.JPanel();
        BtnGuru = new javax.swing.JPanel();
        BtnSetting = new javax.swing.JPanel();
        BtnSettingBack = new javax.swing.JPanel();
        LoginExitPanel = new javax.swing.JPanel();
        LoginPanel = new javax.swing.JPanel();
        ExitPanel = new javax.swing.JPanel();
        LoginExitLabel = new javax.swing.JLabel();
        KiriLabel = new javax.swing.JLabel();
        KananPanel = new javax.swing.JPanel();
        DashboardPanel = new javax.swing.JPanel();
        DasboardLabel = new javax.swing.JLabel();
        JadwalPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JadwalPelajaranTable = new javax.swing.JTable();
        TxtKelas = new javax.swing.JTextField();
        CBPilihKelas = new javax.swing.JComboBox();
        BtnFindJadwal = new javax.swing.JPanel();
        CBHariJadwal = new javax.swing.JComboBox();
        btnReportJadwalS = new javax.swing.JButton();
        btnReportJadwal = new javax.swing.JButton();
        JadwalLabel = new javax.swing.JLabel();
        GuruPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JadwalGuruTable = new javax.swing.JTable();
        TxtNIP = new javax.swing.JTextField();
        TxtGuru = new javax.swing.JTextField();
        CBPilihGuru = new javax.swing.JComboBox();
        BtnFind = new javax.swing.JPanel();
        CBHariGuru = new javax.swing.JComboBox();
        btnReportGuru = new javax.swing.JButton();
        btnReportGuruS = new javax.swing.JButton();
        GuruLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KiriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        KiriPanel.add(BtnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 410, 60));

        BtnJadwal.setBackground(new java.awt.Color(0, 0, 0,0));
        BtnJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnJadwalLayout = new javax.swing.GroupLayout(BtnJadwal);
        BtnJadwal.setLayout(BtnJadwalLayout);
        BtnJadwalLayout.setHorizontalGroup(
            BtnJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnJadwalLayout.setVerticalGroup(
            BtnJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 410, 60));

        BtnGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnGuruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnGuruLayout = new javax.swing.GroupLayout(BtnGuru);
        BtnGuru.setLayout(BtnGuruLayout);
        BtnGuruLayout.setHorizontalGroup(
            BtnGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        BtnGuruLayout.setVerticalGroup(
            BtnGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        KiriPanel.add(BtnGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 410, 60));

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

        LoginExitPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            .addGap(0, 240, Short.MAX_VALUE)
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        LoginExitPanel.add(LoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 40));

        ExitPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        ExitPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ExitPanelLayout = new javax.swing.GroupLayout(ExitPanel);
        ExitPanel.setLayout(ExitPanelLayout);
        ExitPanelLayout.setHorizontalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        ExitPanelLayout.setVerticalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        LoginExitPanel.add(ExitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 40));

        LoginExitLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Login & Exit.png")); // NOI18N
        LoginExitPanel.add(LoginExitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KiriPanel.add(LoginExitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 264, 126));

        KiriLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Panel Kiri.png")); // NOI18N
        KiriPanel.add(KiriLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        getContentPane().add(KiriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 411, 768));

        KananPanel.setLayout(new java.awt.CardLayout());

        DashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DasboardLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Dashboard Frame.png")); // NOI18N
        DashboardPanel.add(DasboardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(DashboardPanel, "card2");

        JadwalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JadwalPelajaranTable.setModel(new javax.swing.table.DefaultTableModel(
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
        JadwalPelajaranTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JadwalPelajaranTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JadwalPelajaranTable);

        JadwalPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 550, 450));

        TxtKelas.setEditable(false);
        TxtKelas.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtKelas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtKelas.setBorder(null);
        TxtKelas.setOpaque(false);
        JadwalPanel.add(TxtKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 254, 270, 30));

        JadwalPanel.add(CBPilihKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 320, -1));

        BtnFindJadwal.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnFindJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnFindJadwal.setOpaque(false);
        BtnFindJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFindJadwalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnFindJadwalLayout = new javax.swing.GroupLayout(BtnFindJadwal);
        BtnFindJadwal.setLayout(BtnFindJadwalLayout);
        BtnFindJadwalLayout.setHorizontalGroup(
            BtnFindJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnFindJadwalLayout.setVerticalGroup(
            BtnFindJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        JadwalPanel.add(BtnFindJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 50, 40));

        CBHariJadwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat" }));
        CBHariJadwal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBHariJadwalItemStateChanged(evt);
            }
        });
        JadwalPanel.add(CBHariJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 270, -1));

        btnReportJadwalS.setBackground(new java.awt.Color(153, 153, 153));
        btnReportJadwalS.setForeground(new java.awt.Color(0, 0, 0));
        btnReportJadwalS.setText("Cetak Semua");
        btnReportJadwalS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportJadwalSActionPerformed(evt);
            }
        });
        JadwalPanel.add(btnReportJadwalS, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, 110, 40));

        btnReportJadwal.setBackground(new java.awt.Color(153, 153, 153));
        btnReportJadwal.setForeground(new java.awt.Color(0, 0, 0));
        btnReportJadwal.setText("Cetak");
        btnReportJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportJadwalActionPerformed(evt);
            }
        });
        JadwalPanel.add(btnReportJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 110, 40));

        JadwalLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Jadwal Pelajaran Frame.png")); // NOI18N
        JadwalPanel.add(JadwalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(JadwalPanel, "card3");

        GuruPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JadwalGuruTable.setModel(new javax.swing.table.DefaultTableModel(
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
        JadwalGuruTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JadwalGuruTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JadwalGuruTable);

        GuruPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 550, 450));

        TxtNIP.setEditable(false);
        TxtNIP.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtNIP.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtNIP.setBorder(null);
        TxtNIP.setOpaque(false);
        GuruPanel.add(TxtNIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 254, 270, 30));

        TxtGuru.setEditable(false);
        TxtGuru.setBackground(new java.awt.Color(0, 0, 0, 0));
        TxtGuru.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtGuru.setBorder(null);
        TxtGuru.setOpaque(false);
        GuruPanel.add(TxtGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 270, 30));

        GuruPanel.add(CBPilihGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 320, -1));

        BtnFind.setBackground(new java.awt.Color(0, 0, 0, 0));
        BtnFind.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnFind.setOpaque(false);
        BtnFind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFindMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BtnFindLayout = new javax.swing.GroupLayout(BtnFind);
        BtnFind.setLayout(BtnFindLayout);
        BtnFindLayout.setHorizontalGroup(
            BtnFindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        BtnFindLayout.setVerticalGroup(
            BtnFindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        GuruPanel.add(BtnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 50, 40));

        CBHariGuru.setBackground(new java.awt.Color(153, 153, 153));
        CBHariGuru.setForeground(new java.awt.Color(0, 0, 0));
        CBHariGuru.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat" }));
        CBHariGuru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBHariGuruItemStateChanged(evt);
            }
        });
        GuruPanel.add(CBHariGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 270, -1));

        btnReportGuru.setBackground(new java.awt.Color(153, 153, 153));
        btnReportGuru.setForeground(new java.awt.Color(0, 0, 0));
        btnReportGuru.setText("Cetak");
        btnReportGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportGuruActionPerformed(evt);
            }
        });
        GuruPanel.add(btnReportGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, 110, 40));

        btnReportGuruS.setBackground(new java.awt.Color(153, 153, 153));
        btnReportGuruS.setForeground(new java.awt.Color(0, 0, 0));
        btnReportGuruS.setText("Cetak Semua");
        btnReportGuruS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportGuruSActionPerformed(evt);
            }
        });
        GuruPanel.add(btnReportGuruS, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, 110, 40));

        GuruLabel.setIcon(new javax.swing.ImageIcon("D:\\Tugas\\TA Jadwal Pelajaran\\Design\\Jadwal Guru Frame.png")); // NOI18N
        GuruPanel.add(GuruLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        KananPanel.add(GuruPanel, "card4");

        getContentPane().add(KananPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 955, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnGuruMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        JadwalPanel.setVisible(false);
        GuruPanel.setVisible(true);
    }//GEN-LAST:event_BtnGuruMouseClicked

    private void BtnDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDashboardMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(true);
        JadwalPanel.setVisible(false);
        GuruPanel.setVisible(false);
    }//GEN-LAST:event_BtnDashboardMouseClicked

    private void BtnJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnJadwalMouseClicked
        // TODO add your handling code here:
        DashboardPanel.setVisible(false);
        JadwalPanel.setVisible(true);
        GuruPanel.setVisible(false);
    }//GEN-LAST:event_BtnJadwalMouseClicked

    private void BtnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSettingMouseClicked
        // TODO add your handling code here:
        LoginExitPanel.setVisible(true);
        LoginExitPanel.setEnabled(true);
        BtnSettingBack.setEnabled(true);
        BtnSettingBack.setVisible(true);
        BtnSetting.setEnabled(false);
        BtnSetting.setVisible(false);
    }//GEN-LAST:event_BtnSettingMouseClicked

    private void BtnSettingBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSettingBackMouseClicked
        // TODO add your handling code here:
        BtnSetting.setEnabled(true);
        BtnSetting.setVisible(true);
        LoginExitPanel.setVisible(false);
        LoginExitPanel.setEnabled(false);
        BtnSettingBack.setEnabled(false);
        BtnSettingBack.setVisible(false);
    }//GEN-LAST:event_BtnSettingBackMouseClicked

    private void ExitPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitPanelMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response==JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_ExitPanelMouseClicked

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

    private void LoginPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginPanelMouseClicked
        // TODO add your handling code here:
        LoginFrame main = new LoginFrame();
        main.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LoginPanelMouseClicked

    private void JadwalGuruTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JadwalGuruTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JadwalGuruTableMouseClicked

    private void BtnFindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFindMouseClicked
        // TODO add your handling code here:
        String findguru = CBPilihGuru.getSelectedItem().toString();
        try {
            String sql = "SELECT `nip`, `guru` FROM `tb_guru` WHERE `guru` = '"+findguru+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                TxtNIP.setText(res.getString("nip"));
                TxtGuru.setText(res.getString("guru"));
            }
        } catch (Exception e) {
        }
        load_tableguru(findguru);
    }//GEN-LAST:event_BtnFindMouseClicked

    private void CBHariGuruItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBHariGuruItemStateChanged
        // TODO add your handling code here:
        String findguru = CBPilihGuru.getSelectedItem().toString();
        load_tableguru(findguru);
    }//GEN-LAST:event_CBHariGuruItemStateChanged

    private void JadwalPelajaranTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JadwalPelajaranTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JadwalPelajaranTableMouseClicked

    private void BtnFindJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFindJadwalMouseClicked
        // TODO add your handling code here:
        String findkelas = CBPilihKelas.getSelectedItem().toString();
        try {
            String sql = "SELECT `kelas` FROM `tb_kelas` WHERE `kelas` = '"+findkelas+"'";
            java.sql.Connection conn=(Connection)Connect.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                TxtKelas.setText(res.getString("kelas"));
                load_tablekelas(findkelas);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_BtnFindJadwalMouseClicked

    private void CBHariJadwalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBHariJadwalItemStateChanged
        // TODO add your handling code here:
        String findkelas = CBPilihKelas.getSelectedItem().toString();
        load_tablekelas(findkelas);
    }//GEN-LAST:event_CBHariJadwalItemStateChanged

    private void btnReportJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportJadwalActionPerformed
        // TODO add your handling code here:
        String findkelas = CBPilihKelas.getSelectedItem().toString();
        try {
            HashMap parameter = new HashMap();
            java.sql.Connection conn=(Connection)Connect.configDB();
            JasperDesign report = JRXmlLoader.load("D:\\Project\\NetBeansProjects\\Jadwal Pelajaran Rev\\src\\reportjadwal.jrxml");
            String sql = "SELECT `tb_kelas`.`kelas`, `tb_hari`.`hari`, `tb_jadwal`.`jam_mulai`, `tb_jadwal`.`jam_selesai`, "
                    + "`tb_mapel`.`singkatan`, `tb_guru`.`guru` FROM `tb_jadwal` "
                    + "JOIN `tb_hari` ON `tb_jadwal`.`hari_id` = `tb_hari`.`hari_id` "
                    + "JOIN `tb_kelas` ON `tb_jadwal`.`kelas_id` = `tb_kelas`.`kelas_id` "
                    + "JOIN `tb_mapel` ON `tb_jadwal`.`mapel_id` = `tb_mapel`.`mapel_id` "
                    + "JOIN `tb_guru` ON `tb_jadwal`.`guru_id` = `tb_guru`.`guru_id` "
                    + "WHERE `tb_kelas`.`kelas` = '"+findkelas+"'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            report.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak! "+"\n"+e.getMessage(), "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReportJadwalActionPerformed

    private void btnReportJadwalSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportJadwalSActionPerformed
        // TODO add your handling code here:
        try {
            HashMap parameter = new HashMap();
            java.sql.Connection conn=(Connection)Connect.configDB();
            JasperDesign report = JRXmlLoader.load("D:\\Project\\NetBeansProjects\\Jadwal Pelajaran Rev\\src\\reportjadwal.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak! "+"\n"+e.getMessage(), "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReportJadwalSActionPerformed

    private void btnReportGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportGuruActionPerformed
        // TODO add your handling code here:
        String findguru = CBPilihGuru.getSelectedItem().toString();
        try {
            HashMap parameter = new HashMap();
            java.sql.Connection conn=(Connection)Connect.configDB();
            JasperDesign report = JRXmlLoader.load("D:\\Project\\NetBeansProjects\\Jadwal Pelajaran Rev\\src\\reportguru.jrxml");
            String sql = "SELECT * FROM `tb_jadwal` "
                    + "JOIN `tb_hari` ON `tb_jadwal`.`hari_id` = `tb_hari`.`hari_id` "
                    + "JOIN `tb_kelas` ON `tb_jadwal`.`kelas_id` = `tb_kelas`.`kelas_id` "
                    + "JOIN `tb_mapel` ON `tb_jadwal`.`mapel_id` = `tb_mapel`.`mapel_id` "
                    + "JOIN `tb_guru` ON `tb_jadwal`.`guru_id` = `tb_guru`.`guru_id` "
                    + "WHERE `tb_guru`.`guru` = '"+findguru+"'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            report.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak! "+"\n"+e.getMessage(), "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReportGuruActionPerformed

    private void btnReportGuruSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportGuruSActionPerformed
        // TODO add your handling code here:
        try {
            HashMap parameter = new HashMap();
            java.sql.Connection conn=(Connection)Connect.configDB();
            JasperDesign report = JRXmlLoader.load("D:\\Project\\NetBeansProjects\\Jadwal Pelajaran Rev\\src\\reportguru.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak! "+"\n"+e.getMessage(), "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReportGuruSActionPerformed

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
            java.util.logging.Logger.getLogger(GuestMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtnDashboard;
    private javax.swing.JPanel BtnFind;
    private javax.swing.JPanel BtnFindJadwal;
    private javax.swing.JPanel BtnGuru;
    private javax.swing.JPanel BtnJadwal;
    private javax.swing.JPanel BtnSetting;
    private javax.swing.JPanel BtnSettingBack;
    private javax.swing.JComboBox CBHariGuru;
    private javax.swing.JComboBox CBHariJadwal;
    private javax.swing.JComboBox CBPilihGuru;
    private javax.swing.JComboBox CBPilihKelas;
    private javax.swing.JLabel DasboardLabel;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JPanel ExitPanel;
    private javax.swing.JLabel GuruLabel;
    private javax.swing.JPanel GuruPanel;
    private javax.swing.JTable JadwalGuruTable;
    private javax.swing.JLabel JadwalLabel;
    private javax.swing.JPanel JadwalPanel;
    private javax.swing.JTable JadwalPelajaranTable;
    private javax.swing.JPanel KananPanel;
    private javax.swing.JLabel KiriLabel;
    private javax.swing.JPanel KiriPanel;
    private javax.swing.JLabel LoginExitLabel;
    private javax.swing.JPanel LoginExitPanel;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JTextField TxtGuru;
    private javax.swing.JTextField TxtKelas;
    private javax.swing.JTextField TxtNIP;
    private javax.swing.JButton btnReportGuru;
    private javax.swing.JButton btnReportGuruS;
    private javax.swing.JButton btnReportJadwal;
    private javax.swing.JButton btnReportJadwalS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private JasperReport JasperCompileManager(JasperDesign report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
