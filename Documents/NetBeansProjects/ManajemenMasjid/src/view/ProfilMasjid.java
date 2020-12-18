/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Config;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import static view.Dashboard.idMasjid;

import static view.LoginMember.idLogin;
import static view.LoginMember.namaLengkapData;

/**
 *
 * @author Moch Billy Refanto
 */
public class ProfilMasjid extends javax.swing.JFrame {

    Dashboard dashboard = new Dashboard();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String dateToday = dateTimeFormatter.format(now);

    static String luasTanah, alamatMasjid, kelurahan, kecamatan, kabupaten, kodePos, sejarahSingkat, tahunBerdiri, kapasitasMasjid, queryUpdate, queryShow;
    static String idMasjidData, namaMasjidData, luasTanahData, alamatMasjidData, kelurahanData, kecamatanData, kabupatenData, kodePosData, sejarahSingkatData, tahunBerdiriData, kapasitasMasjidData;
    String namaMasjid = "";

    private void clearForm() {
        tfNamaMasjid.setText("");
        tfLuasTanah.setText("");
        tfAlamat.setText("");
        tfKelurahan.setText("");
        tfKecamatan.setText("");
        tfKabupaten.setText("");
        tfKodePos.setText("");
        tfSejarahSingkat.setText("");
        tfTahunBerdiri.setText("");
        tfKapasitasMasjid.setText("");
    }

    
    private void addProfilMasjid() {
        namaMasjid = tfNamaMasjid.getText();
        luasTanah = tfLuasTanah.getText();
        alamatMasjid = tfAlamat.getText();
        kelurahan = tfKelurahan.getText();
        kecamatan = tfKecamatan.getText();
        kabupaten = tfKabupaten.getText();
        kodePos = tfKodePos.getText();
        sejarahSingkat = tfSejarahSingkat.getText();
        tahunBerdiri = tfTahunBerdiri.getText();
        kapasitasMasjid = tfKapasitasMasjid.getText();

        if (namaMasjid.isEmpty() || luasTanah.isEmpty()
                || alamatMasjid.isEmpty() || kelurahan.isEmpty()
                || kecamatan.isEmpty() || kabupaten.isEmpty()
                || kodePos.isEmpty() || sejarahSingkat.isEmpty()
                || tahunBerdiri.isEmpty() || kapasitasMasjid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Update Informasi Masjid!");
            System.out.println("Data tidak boleh ada yang kosong!");
        } else {
//            queryInsert = "INSERT INTO m_masjid(id_m_users,nama_masjid,kapasitas_jamaah,alamat,kelurahan,kecamatan,kabupaten,kode_pos,luas_tanah,tahun_berdiri,sejarah_masjid,created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            queryUpdate = "UPDATE m_masjid SET nama_masjid =?,kapasitas_jamaah=?,alamat=?,kelurahan=?,kecamatan=?,kabupaten=?,kode_pos=?,luas_tanah=?,tahun_berdiri=?,sejarah_masjid=?,updated_at=? WHERE id = '" + idMasjid + "' ";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryUpdate);
                ps.setString(1, namaMasjid);
                ps.setString(2, kapasitasMasjid);
                ps.setString(3, alamatMasjid);
                ps.setString(4, kelurahan);
                ps.setString(5, kecamatan);
                ps.setString(6, kabupaten);
                ps.setString(7, kodePos);
                ps.setString(8, luasTanah);
                ps.setString(9, tahunBerdiri);
                ps.setString(10, sejarahSingkat);
                ps.setString(11, dateToday);
                int rowAffected = ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Berhasil Update Informasi Masjid!");
                System.out.println("Update Profil Berhasil");
//                clearForm();

            } catch (HeadlessException | SQLException e) {
                System.out.println("id_m_users :" + idLogin);
                System.out.println("id_m_masjid : " + idMasjid);
                JOptionPane.showMessageDialog(this, e.getMessage());
                System.out.println("Error : " + e.getMessage());

            }
        }

    }

    private void cekData() {
        queryShow = "SELECT * FROM m_masjid WHERE id = '" + idMasjid + "'";

        try {
//            String idMasjidData, namaMasjidData, luasTanahData, alamatMasjidData, kelurahanData, kecamatanData, kabupatenData, kodePosData, sejarahSingkatData, tahunBerdiriData, kapasitasMasjidData;
            Connection conn = (Connection) Config.configDB();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(queryShow);
            while (res.next()) {
                namaMasjidData = res.getString("nama_masjid");
                kapasitasMasjidData = res.getString("kapasitas_jamaah");
                alamatMasjidData = res.getString("alamat");
                kelurahanData = res.getString("kelurahan");
                kecamatanData = res.getString("kecamatan");
                kabupatenData = res.getString("kabupaten");
                kodePosData = res.getString("kode_pos");
                luasTanahData = res.getString("luas_tanah");
                tahunBerdiriData = res.getString("tahun_berdiri");
                sejarahSingkatData = res.getString("sejarah_masjid");

//                tfNamaMasjid.setText(namaMasjidData);
//                tfLuasTanah.setText(luasTanahData);
//                tfAlamat.setText(alamatMasjidData);
//                tfKelurahan.setText(kelurahanData);
//                tfKecamatan.setText(kecamatanData);
//                tfKabupaten.setText(kabupatenData);
//                tfKodePos.setText(kodePosData);
//                tfSejarahSingkat.setText(sejarahSingkatData);
//                tfTahunBerdiri.setText(tahunBerdiriData);
//                tfKapasitasMasjid.setText(kapasitasMasjidData);
//
//                System.out.println("id_m_masjid " + idMasjid + " Luas tanah " + luasTanahData + alamatMasjidData + kelurahanData + sejarahSingkatData);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        String cekNamaMasjid = tfNamaMasjid.getText();
        if (cekNamaMasjid.isEmpty()) {
            addProfilMasjid();
        } else {

        }

    }

    public static String getNamaMasjidData() {
        return namaMasjidData;
    }

    public static String getLuasTanahData() {
        return luasTanahData;
    }

    public static String getAlamatMasjidData() {
        return alamatMasjidData;
    }

    public static String getKelurahanData() {
        return kelurahanData;
    }

    public static String getKecamatanData() {
        return kecamatanData;
    }

    public static String getKabupatenData() {
        return kabupatenData;
    }

    public static String getKodePosData() {
        return kodePosData;
    }

    public static String getSejarahSingkatData() {
        return sejarahSingkatData;
    }

    public static String getTahunBerdiriData() {
        return tahunBerdiriData;
    }

    public static String getKapasitasMasjidData() {
        return kapasitasMasjidData;
    }

   
    
    
    private void showData() {
        jlNamaLengkap.setText(namaLengkapData);
        jlTanggal.setText(dateToday);

        tfNamaMasjid.setText(namaMasjidData);
        tfLuasTanah.setText(luasTanahData);
        tfAlamat.setText(alamatMasjidData);
        tfKelurahan.setText(kelurahanData);
        tfKecamatan.setText(kecamatanData);
        tfKabupaten.setText(kabupatenData);
        tfKodePos.setText(kodePosData);
        tfSejarahSingkat.setText(sejarahSingkatData);
        tfTahunBerdiri.setText(tahunBerdiriData);
        tfKapasitasMasjid.setText(kapasitasMasjidData);

        System.out.println("id_m_masjid " + idMasjid + " Luas tanah " + luasTanahData + alamatMasjidData + kelurahanData + sejarahSingkatData);

    }

    /**
     * Creates new form ProfilMasjid
     */
    public ProfilMasjid() {
        initComponents();
        showData();
        cekData();
//        clearForm();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfLuasTanah = new javax.swing.JTextField();
        tfAlamat = new javax.swing.JTextField();
        tfNamaMasjid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfKecamatan = new javax.swing.JTextField();
        tfKelurahan = new javax.swing.JTextField();
        tfKodePos = new javax.swing.JTextField();
        tfKabupaten = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfSejarahSingkat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfTahunBerdiri = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfKapasitasMasjid = new javax.swing.JTextField();
        btnReset = new java.awt.Button();
        btnSimpan = new java.awt.Button();
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
        jLabel1.setText("Informasi Masjid");

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
                .addGap(0, 0, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel4.setText("Nama Masjid");

        tfLuasTanah.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfLuasTanah.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfLuasTanah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfLuasTanahFocusGained(evt);
            }
        });
        tfLuasTanah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLuasTanahActionPerformed(evt);
            }
        });

        tfAlamat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfAlamat.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfAlamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfAlamatFocusGained(evt);
            }
        });
        tfAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAlamatActionPerformed(evt);
            }
        });

        tfNamaMasjid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNamaMasjid.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfNamaMasjid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNamaMasjidFocusGained(evt);
            }
        });
        tfNamaMasjid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaMasjidActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel5.setText("Alamat Lengkap");

        jLabel6.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel6.setText("Luas Tanah");

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel7.setText("Sejarah Singkat");

        tfKecamatan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKecamatan.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKecamatan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKecamatanFocusGained(evt);
            }
        });
        tfKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKecamatanActionPerformed(evt);
            }
        });

        tfKelurahan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKelurahan.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKelurahan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKelurahanFocusGained(evt);
            }
        });
        tfKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKelurahanActionPerformed(evt);
            }
        });

        tfKodePos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKodePos.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKodePos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKodePosFocusGained(evt);
            }
        });
        tfKodePos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodePosActionPerformed(evt);
            }
        });

        tfKabupaten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKabupaten.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKabupaten.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKabupatenFocusGained(evt);
            }
        });
        tfKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKabupatenActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel9.setText("Kecamatan");

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel10.setText("Kode Pos");

        jLabel11.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel11.setText("Kabupaten");

        tfSejarahSingkat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfSejarahSingkat.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfSejarahSingkat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfSejarahSingkatFocusGained(evt);
            }
        });
        tfSejarahSingkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSejarahSingkatActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel12.setText("Kelurahan");

        jLabel13.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel13.setText("Tahun Berdiri");

        tfTahunBerdiri.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfTahunBerdiri.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfTahunBerdiri.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfTahunBerdiriFocusGained(evt);
            }
        });
        tfTahunBerdiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTahunBerdiriActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel14.setText("Kapasitas Masjid");

        tfKapasitasMasjid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKapasitasMasjid.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKapasitasMasjid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKapasitasMasjidFocusGained(evt);
            }
        });
        tfKapasitasMasjid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKapasitasMasjidActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfSejarahSingkat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfNamaMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfLuasTanah, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))
                                    .addComponent(jLabel5)
                                    .addComponent(tfAlamat))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfKabupaten, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(tfKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel7)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfTahunBerdiri, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfKapasitasMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLuasTanah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNamaMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKabupaten, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSejarahSingkat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKapasitasMasjid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTahunBerdiri, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Profil Masjid");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 514, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 47, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jlInventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInventarisMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlInventarisMouseClicked

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addProfilMasjid();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
        JOptionPane.showMessageDialog(this, "Reset Berhasil!");
    }//GEN-LAST:event_btnResetActionPerformed

    private void tfKapasitasMasjidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKapasitasMasjidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKapasitasMasjidActionPerformed

    private void tfKapasitasMasjidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKapasitasMasjidFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKapasitasMasjidFocusGained

    private void tfTahunBerdiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTahunBerdiriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTahunBerdiriActionPerformed

    private void tfTahunBerdiriFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTahunBerdiriFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTahunBerdiriFocusGained

    private void tfSejarahSingkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSejarahSingkatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSejarahSingkatActionPerformed

    private void tfSejarahSingkatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSejarahSingkatFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSejarahSingkatFocusGained

    private void tfKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKabupatenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKabupatenActionPerformed

    private void tfKabupatenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKabupatenFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKabupatenFocusGained

    private void tfKodePosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodePosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodePosActionPerformed

    private void tfKodePosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKodePosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodePosFocusGained

    private void tfKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKelurahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKelurahanActionPerformed

    private void tfKelurahanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKelurahanFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKelurahanFocusGained

    private void tfKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKecamatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKecamatanActionPerformed

    private void tfKecamatanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKecamatanFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKecamatanFocusGained

    private void tfNamaMasjidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaMasjidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaMasjidActionPerformed

    private void tfNamaMasjidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaMasjidFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaMasjidFocusGained

    private void tfAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAlamatActionPerformed

    private void tfAlamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlamatFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAlamatFocusGained

    private void tfLuasTanahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLuasTanahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLuasTanahActionPerformed

    private void tfLuasTanahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLuasTanahFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLuasTanahFocusGained

    private void jlDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlDashboardMouseClicked
        this.dispose();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jlDashboardMouseClicked

    private void jlLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLogoMouseClicked
        this.dispose();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jlLogoMouseClicked

    private void jlKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlKeluarMouseClicked
        System.out.println("Keluar");
        System.exit(0);
    }//GEN-LAST:event_jlKeluarMouseClicked

    private void jlTambahPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTambahPengurusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlTambahPengurusMouseClicked

    private void jlProfileMasjidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlProfileMasjidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlProfileMasjidMouseClicked

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
            java.util.logging.Logger.getLogger(ProfilMasjid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilMasjid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilMasjid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilMasjid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ProfilMasjid().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnReset;
    private java.awt.Button btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jlDaftarInventaris;
    private javax.swing.JLabel jlDaftarPengurus;
    private javax.swing.JLabel jlDashboard;
    private javax.swing.JLabel jlDetailKeuangan;
    private javax.swing.JLabel jlInformasiMasjid;
    private javax.swing.JLabel jlInventaris;
    private javax.swing.JLabel jlKeluar;
    private javax.swing.JLabel jlKepengurusan;
    private javax.swing.JLabel jlKeuangan;
    private javax.swing.JLabel jlLogo;
    private javax.swing.JLabel jlNamaLengkap;
    private javax.swing.JLabel jlProfileMasjid;
    private javax.swing.JLabel jlTambahKeuangan;
    private javax.swing.JLabel jlTambahPengurus;
    private javax.swing.JLabel jlTanggal;
    private javax.swing.JTextField tfAlamat;
    private javax.swing.JTextField tfKabupaten;
    private javax.swing.JTextField tfKapasitasMasjid;
    private javax.swing.JTextField tfKecamatan;
    private javax.swing.JTextField tfKelurahan;
    private javax.swing.JTextField tfKodePos;
    private javax.swing.JTextField tfLuasTanah;
    private javax.swing.JTextField tfNamaMasjid;
    private javax.swing.JTextField tfSejarahSingkat;
    private javax.swing.JTextField tfTahunBerdiri;
    // End of variables declaration//GEN-END:variables
}
