/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DataKeuangan;
import static view.Dashboard.idMasjid;
import static view.LoginMember.namaLengkapData;

/**
 *
 * @author Moch Billy Refanto
 */
public class Keuangan extends javax.swing.JFrame {
    ArrayList<DataKeuangan> datakeuangan = new ArrayList<>();
    
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String dateToday = dateTimeFormatter.format(now);
    
    DefaultTableModel model;

    private void showData() {
        jlNamaLengkap.setText(namaLengkapData);
        jlTanggal.setText(dateToday);
        System.out.println("Id m_masjid " + idMasjid);

    }

    private void addKeuangan() {
        String kategori, keterangan, nominal, tanggal, queryInsert;
        nominal = tfNominal.getText();
        tanggal = tfTanggal.getText();
        keterangan = tfKeterangan.getText();
        kategori = cbKategori.getSelectedItem().toString();

        if (nominal.isEmpty() || tanggal.isEmpty()
                || keterangan.isEmpty() || kategori.isEmpty()) {
            System.out.println("Tidak tidak boleh ada yang kosong!");
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");
        } else {
            queryInsert = "INSERT INTO m_keuangan (id_m_masjid,kategori,keterangan,nominal,tanggal,created_at) VALUES (?,?,?,?,?,?)";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryInsert);
                ps.setString(1, idMasjid);
                ps.setString(2, kategori);
                ps.setString(3, keterangan);
                ps.setString(4, nominal);
                ps.setString(5, tanggal);
                ps.setString(6, dateToday);

                int rowAffected = ps.executeUpdate();
                System.out.println(kategori + " Berhasil Disimpan!");
                JOptionPane.showMessageDialog(this, "Berhasil Disimpan!");

                clearForm();
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }

    }
    
    private void dataKeuangan() {
        int nominalData,idKeuanganData;
        String queryShow,keterangan,kategori,tanggal;
        queryShow = "SELECT * FROM m_keuangan WHERE id_m_masjid = '" + idMasjid + "'";

        try {
            Connection conn = (Connection) Config.configDB();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(queryShow);
            while (res.next()) {
                idKeuanganData = res.getInt("id");
                nominalData = res.getInt("nominal");
                keterangan = res.getString("keterangan");
                kategori = res.getString("kategori");
                tanggal = res.getString("tanggal");
                Object data[] = {
                    idKeuanganData,nominalData,keterangan,kategori,tanggal
                };

                datakeuangan.add(new DataKeuangan(idKeuanganData,nominalData,keterangan,kategori,tanggal));
                int index = datakeuangan.size() - 1;

                model.addRow(new Object[]{
                    datakeuangan.get(index).getNominal(),
                    datakeuangan.get(index).getKategoriData(),
                    datakeuangan.get(index).getKeteranganData(),
                    datakeuangan.get(index).getTanggalData()
                    

                });
                System.out.println("dataKeuangan()" + idKeuanganData + nominalData + kategori);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    
    private void deleteKeuangan(){
        String idKeuangan,queryDelete;
        idKeuangan = tfIdData.getText();
 
        if (idKeuangan.isEmpty()) {
            System.out.println("Pilih data di tabel");
            JOptionPane.showMessageDialog(this, "Pilih data di tabel");
        } else {
            queryDelete = "DELETE FROM m_keuangan WHERE id =?";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryDelete);
                ps.setString(1, idKeuangan);
    

                int rowAffected = ps.executeUpdate();
                System.out.println(idKeuangan + " Berhasil Dihapus");
                JOptionPane.showMessageDialog(this, idKeuangan + " Berhasil Dihapus");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }
    }

    private void clearForm() {
        tfNominal.setText("");
        tfTanggal.setText("");
        tfKeterangan.setText("");

    }

    public Keuangan() {
        initComponents();
        showData();
        clearForm();
        
        String[] header = {"Nominal", "Kategori", "Keterangan", "Tanggal"};
        model = new DefaultTableModel(header, 0);
        tableDaftarPengurus.setModel(model);
        
        dataKeuangan();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTanggal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlLogo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jlKeuangan = new javax.swing.JLabel();
        jlProfileMasjid = new javax.swing.JLabel();
        jlInventaris = new javax.swing.JLabel();
        jlInformasiMasjid = new javax.swing.JLabel();
        jlKepengurusan = new javax.swing.JLabel();
        jlTambahPengurus = new javax.swing.JLabel();
        jlTambahKeuangan = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jlKeluar = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jlDashboard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlNamaLengkap = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfJabatan = new javax.swing.JPanel();
        jlNamaLengkapPengurus = new javax.swing.JLabel();
        tfKeterangan = new javax.swing.JTextField();
        tfNominal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jlJabatan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnReset = new java.awt.Button();
        btnSimpan = new java.awt.Button();
        tfTanggal = new javax.swing.JTextField();
        cbKategori = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tfIdData = new javax.swing.JTextField();
        btnDelete = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaftarPengurus = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTanggal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlTanggal.setText("Tanngal Sekarang");

        jPanel1.setBackground(new java.awt.Color(7, 17, 44));

        jlLogo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlLogo.setForeground(new java.awt.Color(255, 255, 255));
        jlLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_mosque_48px.png"))); // NOI18N
        jlLogo.setText("DKMNATION");
        jlLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlLogoMouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(7, 17, 44));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(224, 224, 224)));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(90, 92, 105));
        jLabel8.setText("MASJID");

        jlKeuangan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlKeuangan.setForeground(new java.awt.Color(255, 255, 255));
        jlKeuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_wallet_24px_1.png"))); // NOI18N
        jlKeuangan.setText("Keuangan");

        jlProfileMasjid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlProfileMasjid.setForeground(new java.awt.Color(255, 255, 255));
        jlProfileMasjid.setText("       Profil Masjid");
        jlProfileMasjid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlProfileMasjidMouseClicked(evt);
            }
        });

        jlInventaris.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlInventaris.setForeground(new java.awt.Color(255, 255, 255));
        jlInventaris.setText("       Inventaris");
        jlInventaris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlInventarisMouseClicked(evt);
            }
        });

        jlInformasiMasjid.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlInformasiMasjid.setForeground(new java.awt.Color(255, 255, 255));
        jlInformasiMasjid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_moon_star_24px.png"))); // NOI18N
        jlInformasiMasjid.setText("Informasi Masjid");

        jlKepengurusan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlKepengurusan.setForeground(new java.awt.Color(255, 255, 255));
        jlKepengurusan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_user_male_24px_1.png"))); // NOI18N
        jlKepengurusan.setText("Kepengurusan");

        jlTambahPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlTambahPengurus.setForeground(new java.awt.Color(255, 255, 255));
        jlTambahPengurus.setText("       Tambah Pengurus");
        jlTambahPengurus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlTambahPengurusMouseClicked(evt);
            }
        });

        jlTambahKeuangan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlTambahKeuangan.setForeground(new java.awt.Color(255, 255, 255));
        jlTambahKeuangan.setText("       Tambah Keuangan");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_drop_down_24px.png"))); // NOI18N
        jLabel19.setText("jLabel19");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_drop_down_24px.png"))); // NOI18N
        jLabel20.setText("jLabel19");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_drop_down_24px.png"))); // NOI18N
        jLabel21.setText("jLabel19");

        jlKeluar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlKeluar.setForeground(new java.awt.Color(255, 255, 255));
        jlKeluar.setText("Keluar");
        jlKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlKeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlInventaris, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlProfileMasjid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTambahPengurus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlInformasiMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlTambahKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlKepengurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlInformasiMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(3, 3, 3)
                .addComponent(jlProfileMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlKepengurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTambahPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTambahKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(7, 17, 44));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(224, 224, 224)));

        jlDashboard.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlDashboard.setForeground(new java.awt.Color(255, 255, 255));
        jlDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_speedometer_24px.png"))); // NOI18N
        jlDashboard.setText(" Dashboard");
        jlDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlDashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jlDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 115, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(424, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel1.setText("Keuangan");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jlNamaLengkap.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jlNamaLengkap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlNamaLengkap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_arab_30px.png"))); // NOI18N
        jlNamaLengkap.setText("Nama Lengkap");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(684, Short.MAX_VALUE)
                .addComponent(jlNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(78, 115, 223));
        jPanel3.setToolTipText("");

        tfJabatan.setBackground(new java.awt.Color(255, 255, 255));
        tfJabatan.setToolTipText("");

        jlNamaLengkapPengurus.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlNamaLengkapPengurus.setText("Nominal");

        tfKeterangan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKeterangan.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKeterangan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKeteranganFocusGained(evt);
            }
        });
        tfKeterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKeteranganActionPerformed(evt);
            }
        });

        tfNominal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNominal.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfNominal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNominalFocusGained(evt);
            }
        });
        tfNominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNominalActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel5.setText("Keterangan");

        jlJabatan.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlJabatan.setText("Kategori ");

        jLabel12.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel12.setText("Tanggal");

        btnReset.setActionCommand("Registrasi");
        btnReset.setBackground(new java.awt.Color(244, 182, 25));
        btnReset.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setLabel("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSimpan.setActionCommand("Registrasi");
        btnSimpan.setBackground(new java.awt.Color(28, 200, 138));
        btnSimpan.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setLabel("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        tfTanggal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfTanggal.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfTanggal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfTanggalFocusGained(evt);
            }
        });
        tfTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTanggalActionPerformed(evt);
            }
        });

        cbKategori.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pemasukan", "Pengeluaran" }));

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel10.setText("ID");

        tfIdData.setEditable(false);
        tfIdData.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfIdData.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfIdData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfIdDataFocusGained(evt);
            }
        });
        tfIdData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdDataActionPerformed(evt);
            }
        });

        btnDelete.setActionCommand("Registrasi");
        btnDelete.setBackground(new java.awt.Color(255, 51, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tableDaftarPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tableDaftarPengurus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nominal", "Kategori", "Keterangan", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDaftarPengurus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDaftarPengurusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDaftarPengurus);

        javax.swing.GroupLayout tfJabatanLayout = new javax.swing.GroupLayout(tfJabatan);
        tfJabatan.setLayout(tfJabatanLayout);
        tfJabatanLayout.setHorizontalGroup(
            tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfJabatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                            .addComponent(tfTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                        .addGap(459, 459, 459))
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKeterangan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tfJabatanLayout.createSequentialGroup()
                                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlNamaLengkapPengurus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlJabatan)
                                    .addComponent(cbKategori, 0, 436, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tfJabatanLayout.createSequentialGroup()
                                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tfJabatanLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfIdData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        tfJabatanLayout.setVerticalGroup(
            tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfJabatanLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNamaLengkapPengurus)
                    .addComponent(jlJabatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addComponent(tfTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfIdData, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tambah Keuangan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tfJabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 536, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 57, Short.MAX_VALUE)
                    .addComponent(tfJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jlTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLogoMouseClicked
        this.dispose();
    }//GEN-LAST:event_jlLogoMouseClicked

    private void jlProfileMasjidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlProfileMasjidMouseClicked
       ProfilMasjid profilmasjid = new ProfilMasjid();
       this.dispose();
       profilmasjid.setVisible(true);
    }//GEN-LAST:event_jlProfileMasjidMouseClicked

    private void jlInventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInventarisMouseClicked
        Inventaris inventaris = new Inventaris();
        this.dispose();
        inventaris.setVisible(true);
    }//GEN-LAST:event_jlInventarisMouseClicked

    private void jlTambahPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTambahPengurusMouseClicked
        Pengurus pengurus = new Pengurus();
        this.dispose();
        pengurus.setVisible(true);
    }//GEN-LAST:event_jlTambahPengurusMouseClicked

    private void jlKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlKeluarMouseClicked
        System.out.println("Keluar");
        System.exit(0);
    }//GEN-LAST:event_jlKeluarMouseClicked

    private void jlDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlDashboardMouseClicked
        Dashboard dashboard = new Dashboard();
        this.dispose();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jlDashboardMouseClicked

    private void tfTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTanggalActionPerformed

    private void tfTanggalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTanggalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTanggalFocusGained

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addKeuangan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tfNominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNominalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNominalActionPerformed

    private void tfNominalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNominalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNominalFocusGained

    private void tfKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeteranganActionPerformed

    private void tfKeteranganFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKeteranganFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeteranganFocusGained

    private void tfIdDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIdDataFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdDataFocusGained

    private void tfIdDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdDataActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteKeuangan();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableDaftarPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDaftarPengurusMouseClicked

        int i = tableDaftarPengurus.getSelectedRow();
        int idKeuangan,nominal;
        String keterangan,kategori,tanggal;

        idKeuangan = datakeuangan.get(i).getIdData();
        kategori = datakeuangan.get(i).getKategoriData();
        nominal = datakeuangan.get(i).getNominal();
        keterangan = datakeuangan.get(i).getKeteranganData();
        tanggal = datakeuangan.get(i).getTanggalData();

        int kt = 0;
        switch (kategori) {
            case "Pemasukan":
            kt = 0;
            break;
            case "Pengeluaran":
            kt = 1;
            break;

        }


        cbKategori.setSelectedIndex(kt);
        tfIdData.setText(String.valueOf(idKeuangan));
        tfNominal.setText(String.valueOf(nominal));
        tfKeterangan.setText(keterangan);
        tfTanggal.setText(tanggal);

    }//GEN-LAST:event_tableDaftarPengurusMouseClicked

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
            java.util.logging.Logger.getLogger(Keuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Keuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Keuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Keuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Keuangan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnDelete;
    private java.awt.Button btnReset;
    private java.awt.Button btnSimpan;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlDashboard;
    private javax.swing.JLabel jlInformasiMasjid;
    private javax.swing.JLabel jlInventaris;
    private javax.swing.JLabel jlJabatan;
    private javax.swing.JLabel jlKeluar;
    private javax.swing.JLabel jlKepengurusan;
    private javax.swing.JLabel jlKeuangan;
    private javax.swing.JLabel jlLogo;
    private javax.swing.JLabel jlNamaLengkap;
    private javax.swing.JLabel jlNamaLengkapPengurus;
    private javax.swing.JLabel jlProfileMasjid;
    private javax.swing.JLabel jlTambahKeuangan;
    private javax.swing.JLabel jlTambahPengurus;
    private javax.swing.JLabel jlTanggal;
    private javax.swing.JTable tableDaftarPengurus;
    public javax.swing.JTextField tfIdData;
    private javax.swing.JPanel tfJabatan;
    private javax.swing.JTextField tfKeterangan;
    private javax.swing.JTextField tfNominal;
    private javax.swing.JTextField tfTanggal;
    // End of variables declaration//GEN-END:variables
}
