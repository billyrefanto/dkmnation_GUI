/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Config;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import static view.LoginMember.namaLengkapData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DataPengurus;
import static view.Dashboard.idMasjid;

/**
 *
 * @author Moch Billy Refanto
 */
public class Pengurus extends javax.swing.JFrame {

    ArrayList<DataPengurus> datapengurus = new ArrayList<>();

    static String query, namaLengkapPengurusData, jenisKelaminData, tanggalLahirData, kontakData, alamatData, jabatanData, statusData;
    static int idPengurusData;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String dateToday = dateTimeFormatter.format(now);

    DefaultTableModel model;

    private void showData() {
        jlNamaLengkap.setText(namaLengkapData);
        jlTanggal.setText(dateToday);
        System.out.println("Id m_masjid " + idMasjid);

    }

    private void clearForm() {

        tfNamaPengurus.setText("");
        tfJabatanPengurus.setText("");
        tfAlamatLengkap.setText("");
        tfKontakPengurus.setText("");
        tfTanggalLahir.setText("");
    }

    public void addPengurus() {
        String namaPengurus, jabatan, alamatLengkap, jenisKelamin, statusAnggota, kontak, tanggalLahir, queryInsert;
        namaPengurus = tfNamaPengurus.getText();
        jabatan = tfJabatanPengurus.getText();
        alamatLengkap = tfAlamatLengkap.getText();
        kontak = tfKontakPengurus.getText();
        tanggalLahir = tfTanggalLahir.getText();
        jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
        statusAnggota = cbStatus.getSelectedItem().toString();

        if (namaPengurus.isEmpty() || jabatan.isEmpty()
                || alamatLengkap.isEmpty() || kontak.isEmpty()
                || tanggalLahir.isEmpty() || jenisKelamin.isEmpty()
                || statusAnggota.isEmpty()) {
            System.out.println("Tidak tidak boleh ada yang kosong!");
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");
        } else {
            queryInsert = "INSERT INTO u_pengurus (id_m_masjid,nama_lengkap,jenis_kelamin,tanggal_lahir,kontak,alamat,jabatan,status,created_at) VALUES (?,?,?,?,?,?,?,?,?)";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryInsert);
                ps.setString(1, idMasjid);
                ps.setString(2, namaPengurus);
                ps.setString(3, jenisKelamin);
                ps.setString(4, tanggalLahir);
                ps.setString(5, kontak);
                ps.setString(6, alamatLengkap);
                ps.setString(7, jabatan);
                ps.setString(8, statusAnggota);
                ps.setString(9, dateToday);

                int rowAffected = ps.executeUpdate();
                System.out.println(namaPengurus + " Berhasil Disimpan!");
                JOptionPane.showMessageDialog(this, namaPengurus + " Berhasil Disimpan!");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }

    }

    private void dataPengurus() {

        query = "SELECT * FROM u_pengurus WHERE id_m_masjid = '" + idMasjid + "'";

        try {
            Connection conn = (Connection) Config.configDB();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                namaLengkapPengurusData = res.getString("nama_lengkap");
                jenisKelaminData = res.getString("jenis_kelamin");
                tanggalLahirData = res.getString("tanggal_lahir");
                kontakData = res.getString("kontak");
                alamatData = res.getString("alamat");
                jabatanData = res.getString("jabatan");
                statusData = res.getString("status");
                idPengurusData = res.getInt("id");
                String data[] = {
                    namaLengkapPengurusData, jenisKelaminData, tanggalLahirData, kontakData, alamatData, jabatanData, statusData
                };

                datapengurus.add(new DataPengurus(idPengurusData, namaLengkapPengurusData, jenisKelaminData, tanggalLahirData, kontakData, alamatData, jabatanData, statusData));
                int index = datapengurus.size() - 1;

                model.addRow(new Object[]{
                    datapengurus.get(index).getNamaPengurusData(),
                    datapengurus.get(index).getJenisKelaminData(),
                    datapengurus.get(index).getTanggalLahir(),
                    datapengurus.get(index).getKontak(),
                    datapengurus.get(index).getAlamatData(),
                    datapengurus.get(index).getStatusData()

                });
                System.out.println("datapengurus() " + namaLengkapPengurusData + jenisKelaminData + tanggalLahirData);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    private void updatePengurus(){
        String namaPengurus, jabatan, alamatLengkap, jenisKelamin, statusAnggota, kontak, tanggalLahir, queryUpdate;
        namaPengurus = tfNamaPengurus.getText();
        jabatan = tfJabatanPengurus.getText();
        alamatLengkap = tfAlamatLengkap.getText();
        kontak = tfKontakPengurus.getText();
        tanggalLahir = tfTanggalLahir.getText();
        jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
        statusAnggota = cbStatus.getSelectedItem().toString();

        if (namaPengurus.isEmpty() || jabatan.isEmpty()
                || alamatLengkap.isEmpty() || kontak.isEmpty()
                || tanggalLahir.isEmpty() || jenisKelamin.isEmpty()
                || statusAnggota.isEmpty()) {
            System.out.println("Tidak tidak boleh ada yang kosong!");
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");
        } else {
            queryUpdate = "UPDATE u_pengurus SET nama_lengkap =?,jenis_kelamin = ?,tanggal_lahir = ?,kontak = ?,alamat = ?,jabatan = ?,status = ? ,updated_at = ? WHERE id_m_masjid = '" + idMasjid + "'";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryUpdate);
                ps.setString(1, namaPengurus);
                ps.setString(2, jenisKelamin);
                ps.setString(3, tanggalLahir);
                ps.setString(4, kontak);
                ps.setString(5, alamatLengkap);
                ps.setString(6, jabatan);
                ps.setString(7, statusAnggota);
                ps.setString(8, dateToday);

                int rowAffected = ps.executeUpdate();
                System.out.println(namaPengurus + " Berhasil Update!");
                JOptionPane.showMessageDialog(this, namaPengurus + " Berhasil Update!");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }
    }

    public Pengurus() {
        initComponents();
        showData();
        clearForm();

        String[] header = {"Nama Lengkap", "Jenis Kelamin", "Tanggal Lahir", "Kontak", "Alamat", "Status"};
        model = new DefaultTableModel(header, 0);
        tableDaftarPengurus.setModel(model);

        dataPengurus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jlNamaLengkap = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlLogo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jlKeuangan = new javax.swing.JLabel();
        jlProfileMasjid = new javax.swing.JLabel();
        jlDaftarInventaris = new javax.swing.JLabel();
        jlInventaris = new javax.swing.JLabel();
        jlInformasiMasjid = new javax.swing.JLabel();
        jlDaftarPengurus = new javax.swing.JLabel();
        jlDetailKeuangan = new javax.swing.JLabel();
        jlKepengurusan = new javax.swing.JLabel();
        jlTambahPengurus = new javax.swing.JLabel();
        jlTambahKeuangan = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jlKeluar = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jlDashboard = new javax.swing.JLabel();
        jlTanggal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfJabatan = new javax.swing.JPanel();
        jlNamaLengkapPengurus = new javax.swing.JLabel();
        tfJabatanPengurus = new javax.swing.JTextField();
        tfAlamatLengkap = new javax.swing.JTextField();
        tfNamaPengurus = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jlJabatan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfTanggalLahir = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnReset = new java.awt.Button();
        btnSimpan = new java.awt.Button();
        cbJenisKelamin = new javax.swing.JComboBox<>();
        cbStatus = new javax.swing.JComboBox<>();
        tfKontakPengurus = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnReset1 = new java.awt.Button();
        btnSimpan1 = new java.awt.Button();
        tfIdPengurus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaftarPengurus = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(686, Short.MAX_VALUE)
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

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel1.setText("Kepengurusan");

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

        jlDaftarInventaris.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlDaftarInventaris.setForeground(new java.awt.Color(255, 255, 255));
        jlDaftarInventaris.setText("       Daftar Inventaris");

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

        jlDaftarPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlDaftarPengurus.setForeground(new java.awt.Color(255, 255, 255));
        jlDaftarPengurus.setText("       Daftar Pengurus");

        jlDetailKeuangan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlDetailKeuangan.setForeground(new java.awt.Color(255, 255, 255));
        jlDetailKeuangan.setText("       Detail Keuangan");

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
                    .addComponent(jlDaftarPengurus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDaftarInventaris, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlInventaris, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlProfileMasjid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTambahPengurus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDetailKeuangan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlKepengurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jlInformasiMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlTambahKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jlKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jlDaftarInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlKepengurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTambahPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlDaftarPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTambahKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlDetailKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jlTanggal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlTanggal.setText("Tanngal Sekarang");

        jPanel3.setBackground(new java.awt.Color(78, 115, 223));
        jPanel3.setToolTipText("");

        tfJabatan.setBackground(new java.awt.Color(255, 255, 255));
        tfJabatan.setToolTipText("");

        jlNamaLengkapPengurus.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlNamaLengkapPengurus.setText("Nama Lengkap");

        tfJabatanPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfJabatanPengurus.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfJabatanPengurus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJabatanPengurusFocusGained(evt);
            }
        });
        tfJabatanPengurus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJabatanPengurusActionPerformed(evt);
            }
        });

        tfAlamatLengkap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfAlamatLengkap.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfAlamatLengkap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfAlamatLengkapFocusGained(evt);
            }
        });
        tfAlamatLengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAlamatLengkapActionPerformed(evt);
            }
        });

        tfNamaPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNamaPengurus.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfNamaPengurus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNamaPengurusFocusGained(evt);
            }
        });
        tfNamaPengurus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaPengurusActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel5.setText("Alamat Lengkap");

        jlJabatan.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlJabatan.setText("Jabatan");

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel7.setText("Kontak");

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel9.setText("Status Anggota");

        tfTanggalLahir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfTanggalLahir.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfTanggalLahir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfTanggalLahirFocusGained(evt);
            }
        });
        tfTanggalLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTanggalLahirActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel12.setText("Jenis Kelamin");

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

        cbJenisKelamin.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        cbStatus.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif" }));

        tfKontakPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKontakPengurus.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKontakPengurus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKontakPengurusFocusGained(evt);
            }
        });
        tfKontakPengurus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKontakPengurusActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel15.setText("Tanggal Lahir");

        btnReset1.setActionCommand("Registrasi");
        btnReset1.setBackground(new java.awt.Color(255, 51, 0));
        btnReset1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnReset1.setForeground(new java.awt.Color(255, 255, 255));
        btnReset1.setLabel("Delete");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });

        btnSimpan1.setActionCommand("Registrasi");
        btnSimpan1.setBackground(new java.awt.Color(0, 51, 204));
        btnSimpan1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnSimpan1.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan1.setLabel("Update");
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });

        tfIdPengurus.setEditable(false);
        tfIdPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfIdPengurus.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfIdPengurus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfIdPengurusFocusGained(evt);
            }
        });
        tfIdPengurus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdPengurusActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel10.setText("ID");

        tableDaftarPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tableDaftarPengurus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama Lengkap", "Jenis Kelamin", "Tanggal Lahir", "Kontak", "Alamat", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tfJabatanLayout.createSequentialGroup()
                        .addComponent(tfKontakPengurus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfIdPengurus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tfJabatanLayout.createSequentialGroup()
                                    .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfNamaPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlNamaLengkapPengurus))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfJabatanPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlJabatan)))
                                .addComponent(jLabel5)
                                .addComponent(tfAlamatLengkap))
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(408, 408, 408)
                                .addComponent(jLabel15)))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tfJabatanLayout.setVerticalGroup(
            tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfJabatanLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNamaLengkapPengurus)
                    .addComponent(jlJabatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfJabatanPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNamaPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlamatLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(2, 2, 2)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTanggalLahir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKontakPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(5, 5, 5)
                        .addComponent(tfIdPengurus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tambah Pengurus");

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
                .addGap(0, 514, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jlProfileMasjidMouseClicked

    private void jlInventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInventarisMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlInventarisMouseClicked

    private void jlTambahPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTambahPengurusMouseClicked
        // TODO add your handling code here:
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

    private void tfJabatanPengurusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJabatanPengurusFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJabatanPengurusFocusGained

    private void tfJabatanPengurusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJabatanPengurusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJabatanPengurusActionPerformed

    private void tfAlamatLengkapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlamatLengkapFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAlamatLengkapFocusGained

    private void tfAlamatLengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAlamatLengkapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAlamatLengkapActionPerformed

    private void tfNamaPengurusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaPengurusFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaPengurusFocusGained

    private void tfNamaPengurusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaPengurusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaPengurusActionPerformed

    private void tfTanggalLahirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTanggalLahirFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTanggalLahirFocusGained

    private void tfTanggalLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTanggalLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTanggalLahirActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addPengurus();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tfKontakPengurusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKontakPengurusFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKontakPengurusFocusGained

    private void tfKontakPengurusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKontakPengurusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKontakPengurusActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
        updatePengurus();
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void tfIdPengurusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIdPengurusFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdPengurusFocusGained

    private void tfIdPengurusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdPengurusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdPengurusActionPerformed

    private void tableDaftarPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDaftarPengurusMouseClicked

        int i = tableDaftarPengurus.getSelectedRow();
        int idPengurus;
        String namaLengkapPengurus, jenisKelamin, tanggalLahir, kontak, alamat, jabatan, status;

        idPengurus = datapengurus.get(i).getIdData();
        namaLengkapPengurus = datapengurus.get(i).getNamaPengurusData();
        jenisKelamin = datapengurus.get(i).getJenisKelaminData();
        tanggalLahir = datapengurus.get(i).getTanggalLahir();
        kontak = datapengurus.get(i).getKontak();
        alamat = datapengurus.get(i).getAlamatData();
        jabatan = datapengurus.get(i).getJabatanData();
        status = datapengurus.get(i).getStatusData();

        int jk = 0;
        switch (jenisKelamin) {
            case "Laki-Laki":
                jk = 0;
                break;
            case "Perempuan":
                jk = 1;
                break;
                
        }
        
        int st = 0;
        switch (status) {
            case "Aktif":
                jk = 0;
                break;
            case "Non-Aktif":
                jk = 1;
                break;
                
        }
        
        cbJenisKelamin.setSelectedIndex(jk);
        cbStatus.setSelectedIndex(st);
        tfIdPengurus.setText(String.valueOf(idPengurus));
        tfNamaPengurus.setText(namaLengkapPengurus);
        tfJabatanPengurus.setText(jabatan);
        tfAlamatLengkap.setText(alamat);
        tfKontakPengurus.setText(kontak);
        tfTanggalLahir.setText(tanggalLahir);
        
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
            java.util.logging.Logger.getLogger(Pengurus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengurus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengurus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengurus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengurus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnReset;
    private java.awt.Button btnReset1;
    private java.awt.Button btnSimpan;
    private java.awt.Button btnSimpan1;
    private javax.swing.JComboBox<String> cbJenisKelamin;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlDaftarInventaris;
    private javax.swing.JLabel jlDaftarPengurus;
    private javax.swing.JLabel jlDashboard;
    private javax.swing.JLabel jlDetailKeuangan;
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
    private javax.swing.JTextField tfAlamatLengkap;
    public javax.swing.JTextField tfIdPengurus;
    private javax.swing.JPanel tfJabatan;
    private javax.swing.JTextField tfJabatanPengurus;
    private javax.swing.JTextField tfKontakPengurus;
    private javax.swing.JTextField tfNamaPengurus;
    private javax.swing.JTextField tfTanggalLahir;
    // End of variables declaration//GEN-END:variables
}
