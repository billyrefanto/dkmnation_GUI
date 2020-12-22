/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import model.DataInventaris;
import static view.Dashboard.idMasjid;
import static view.LoginMember.namaLengkapData;

/**
 *
 * @author Moch Billy Refanto
 */
public class Inventaris extends javax.swing.JFrame {

    Dashboard dashboard = new Dashboard();
    ArrayList<DataInventaris> dataInventaris = new ArrayList<>();
    DefaultTableModel model;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String dateToday = dateTimeFormatter.format(now);

    private void showData() {
        jlNamaLengkap.setText(namaLengkapData);
        jlTanggal.setText(dateToday);
        System.out.println("Id m_masjid " + idMasjid);

    }

    private void clearForm() {
        tfNamaBarang.setText("");
        tfMerek.setText("");
        tfKeterangan.setText("");
        tfJumlah.setText("");
        tfHarga.setText("");
        tfIdInventaris.setText("");
    }

    public void addInventaris() {
        String namaBarang, merek, keterangan, jumlah, satuan, kondisi, hargaBarang, queryInsert;
        namaBarang = tfNamaBarang.getText();
        merek = tfMerek.getText();
        keterangan = tfKeterangan.getText();
        jumlah = tfJumlah.getText();
        satuan = cbSatuan.getSelectedItem().toString();
        kondisi = cbKondisi.getSelectedItem().toString();
        hargaBarang = tfHarga.getText();

        if (namaBarang.isEmpty() || merek.isEmpty()
                || keterangan.isEmpty() || jumlah.isEmpty()
                || satuan.isEmpty() || kondisi.isEmpty()
                || hargaBarang.isEmpty()) {
            System.out.println("Tidak tidak boleh ada yang kosong!");
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");
        } else {
            queryInsert = "INSERT INTO m_inventari_masjid (id_m_masjid,nama_barang,merek_barang,keterangan,qty,satuan,kondisi,harga_barang,created_at) VALUES (?,?,?,?,?,?,?,?,?)";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryInsert);
                ps.setString(1, idMasjid);
                ps.setString(2, namaBarang);
                ps.setString(3, merek);
                ps.setString(4, keterangan);
                ps.setString(5, jumlah);
                ps.setString(6, satuan);
                ps.setString(7, kondisi);
                ps.setString(8, hargaBarang);
                ps.setString(9, dateToday);

                int rowAffected = ps.executeUpdate();
                System.out.println(namaBarang + " Berhasil Disimpan!");
                JOptionPane.showMessageDialog(this, "Berhasil Disimpan!");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }

    }

    private void dataInventaris() {
        int idInventaris, qtyData;
        String queryShow, namaBarang, merekBarang, keterangan, satuan, kondisi, hargaBarang;
        queryShow = "SELECT * FROM m_inventari_masjid WHERE id_m_masjid = '" + idMasjid + "'";

        try {
            Connection conn = (Connection) Config.configDB();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(queryShow);
            while (res.next()) {
                namaBarang = res.getString("nama_barang");
                merekBarang = res.getString("merek_barang");
                keterangan = res.getString("keterangan");
                satuan = res.getString("satuan");
                kondisi = res.getString("kondisi");
                hargaBarang = res.getString("harga_barang");
                idInventaris = res.getInt("id");
                qtyData = res.getInt("qty");
                Object data[] = {
                    idInventaris, qtyData, namaBarang, merekBarang, keterangan, satuan, kondisi, hargaBarang
                };

                dataInventaris.add(new DataInventaris(idInventaris, qtyData, namaBarang, merekBarang, keterangan, satuan, kondisi, hargaBarang));
                int index = dataInventaris.size() - 1;

                model.addRow(new Object[]{
                    dataInventaris.get(index).getNamaBarang(),
                    dataInventaris.get(index).getMerekBarang(),
                    dataInventaris.get(index).getKeterangan(),
                    dataInventaris.get(index).getQtyData(),
                    dataInventaris.get(index).getSatuan(),
                    dataInventaris.get(index).getKondisi(),
                    dataInventaris.get(index).getHargaBarang()

                });
                System.out.println("dataInventaris() " + namaBarang + merekBarang + satuan);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void updateInventaris() {
        int idInventaris, qtyData;
        String queryUpdate, namaBarang, merekBarang, keterangan, satuan, kondisi, hargaBarang;

        idInventaris = Integer.parseInt(tfIdInventaris.getText());
        qtyData = Integer.parseInt(tfJumlah.getText());
        namaBarang = tfNamaBarang.getText();
        merekBarang = tfMerek.getText();
        keterangan = tfKeterangan.getText();
        satuan = cbSatuan.getSelectedItem().toString();
        kondisi = cbKondisi.getSelectedItem().toString();
        hargaBarang = tfHarga.getText();

        if (namaBarang.isEmpty() || merekBarang.isEmpty()
                || keterangan.isEmpty() || hargaBarang.isEmpty()
                || kondisi.isEmpty() || satuan.isEmpty()) {
            System.out.println("Tidak boleh ada yang kosong!");
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");
        } else {
            queryUpdate = "UPDATE m_inventari_masjid SET nama_barang =?,merek_barang =?,keterangan =?,qty =?,satuan =?,kondisi =?,harga_barang =? ,updated_at =? WHERE id = '" + idInventaris + "'";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryUpdate);
                ps.setString(1, namaBarang);
                ps.setString(2, merekBarang);
                ps.setString(3, keterangan);
                ps.setString(4, String.valueOf(qtyData));
                ps.setString(5, satuan);
                ps.setString(6, kondisi);
                ps.setString(7, hargaBarang);
                ps.setString(8, dateToday);

                int rowAffected = ps.executeUpdate();
                System.out.println(namaBarang + " Berhasil Update!");
                JOptionPane.showMessageDialog(this, namaBarang + " Berhasil Update!");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }
    }

    private void deleteKeuangan() {
        String idInventaris, queryDelete;
        idInventaris = tfIdInventaris.getText();

        if (idInventaris.isEmpty()) {
            System.out.println("ID Tidak ada,Pilih Tabel Dulu!");
            JOptionPane.showMessageDialog(this, "ID Tidak ada,Pilih Tabel Dulu!");
        } else {
            queryDelete = "DELETE FROM m_inventari_masjid WHERE id =?";
            try {
                Connection conn = (Connection) Config.configDB();
                PreparedStatement ps = conn.prepareStatement(queryDelete);
                ps.setString(1, idInventaris);

                int rowAffected = ps.executeUpdate();
                System.out.println(idInventaris + " Berhasil Dihapus");
                JOptionPane.showMessageDialog(this, idInventaris + " Berhasil Dihapus");
            } catch (SQLException ex) {
                System.out.println("Gagal : " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal Simpan Data!" + ex.getMessage());
            }
        }
    }

    /**
     * Creates new form Inventaris
     */
    public Inventaris() {
        initComponents();
        clearForm();
        showData();

        String[] header = {"Nama Barang", "Merek", "Keterangan", "Qty", "Satuan", "Kondisi"};
        model = new DefaultTableModel(header, 0);
        tableDaftarPengurus.setModel(model);

        dataInventaris();
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

        jPanel3 = new javax.swing.JPanel();
        tfJabatan = new javax.swing.JPanel();
        jlNamaLengkapPengurus = new javax.swing.JLabel();
        tfMerek = new javax.swing.JTextField();
        tfKeterangan = new javax.swing.JTextField();
        tfNamaBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jlJabatan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnReset = new java.awt.Button();
        btnSimpan = new java.awt.Button();
        cbSatuan = new javax.swing.JComboBox<>();
        tfJumlah = new javax.swing.JTextField();
        cbKondisi = new javax.swing.JComboBox<>();
        tfHarga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnDelete = new java.awt.Button();
        btnUpdate = new java.awt.Button();
        tfIdInventaris = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaftarPengurus = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlNamaLengkap = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        jlTanggal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(78, 115, 223));
        jPanel3.setToolTipText("");

        tfJabatan.setBackground(new java.awt.Color(255, 255, 255));
        tfJabatan.setToolTipText("");

        jlNamaLengkapPengurus.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlNamaLengkapPengurus.setText("Nama Barang");

        tfMerek.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfMerek.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfMerek.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfMerekFocusGained(evt);
            }
        });
        tfMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMerekActionPerformed(evt);
            }
        });

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

        tfNamaBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNamaBarang.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfNamaBarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNamaBarangFocusGained(evt);
            }
        });
        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel5.setText("Keterangan");

        jlJabatan.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jlJabatan.setText("Merek ");

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel7.setText("Kondisi");

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel9.setText("Satuan");

        jLabel12.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel12.setText("Jumlah");

        btnReset.setActionCommand("Registrasi");
        btnReset.setBackground(new java.awt.Color(244, 182, 25));
        btnReset.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setLabel("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
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
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        cbSatuan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unit", "Items", "Pcs" }));

        tfJumlah.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfJumlah.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJumlahFocusGained(evt);
            }
        });
        tfJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahActionPerformed(evt);
            }
        });

        cbKondisi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbKondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Baru", "Bekas", "Rusak" }));

        tfHarga.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfHarga.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfHargaFocusGained(evt);
            }
        });
        tfHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel10.setText("Harga");

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

        btnUpdate.setActionCommand("Registrasi");
        btnUpdate.setBackground(new java.awt.Color(0, 51, 204));
        btnUpdate.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setLabel("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tfIdInventaris.setEditable(false);
        tfIdInventaris.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfIdInventaris.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfIdInventaris.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfIdInventarisFocusGained(evt);
            }
        });
        tfIdInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdInventarisActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel11.setText("ID");

        tableDaftarPengurus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tableDaftarPengurus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Barang", "Merek", "Keterangan", "Qty", "Satuan", "Kondisi", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE))
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(tfJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSatuan, 0, 436, Short.MAX_VALUE)
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 383, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tfJabatanLayout.createSequentialGroup()
                        .addComponent(cbKondisi, 0, 442, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfKeterangan)
                                .addComponent(jLabel5)
                                .addGroup(tfJabatanLayout.createSequentialGroup()
                                    .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlNamaLengkapPengurus))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlJabatan)
                                        .addComponent(tfMerek, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(410, 410, 410)
                                .addComponent(jLabel10)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tfJabatanLayout.createSequentialGroup()
                        .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfJabatanLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfIdInventaris, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
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
                    .addComponent(tfMerek, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(1, 1, 1)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(5, 5, 5)
                .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfIdInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tfJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Iventaris");

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
                .addContainerGap(688, Short.MAX_VALUE)
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
        jlTambahKeuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlTambahKeuanganMouseClicked(evt);
            }
        });

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
                .addGap(0, 93, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(424, Short.MAX_VALUE)))
        );

        jlTanggal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlTanggal.setText("Tanngal Sekarang");

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

    private void tfMerekFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMerekFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMerekFocusGained

    private void tfMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMerekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMerekActionPerformed

    private void tfKeteranganFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKeteranganFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeteranganFocusGained

    private void tfKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeteranganActionPerformed

    private void tfNamaBarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaBarangFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangFocusGained

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jlProfileMasjidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlProfileMasjidMouseClicked
        ProfilMasjid profilmasjid = new ProfilMasjid();
        this.dispose();
        profilmasjid.setVisible(true);
    }//GEN-LAST:event_jlProfileMasjidMouseClicked

    private void jlInventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInventarisMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlInventarisMouseClicked

    private void jlTambahPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTambahPengurusMouseClicked
        Pengurus pengurus = new Pengurus();
        this.dispose();;
        pengurus.setVisible(true);
    }//GEN-LAST:event_jlTambahPengurusMouseClicked

    private void jlKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlKeluarMouseClicked
        System.out.println("Keluar");
        System.exit(0);
    }//GEN-LAST:event_jlKeluarMouseClicked

    private void jlDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlDashboardMouseClicked
        System.out.println("Tes");
//        this.dispose();

//        dashboard.setVisible(true);
    }//GEN-LAST:event_jlDashboardMouseClicked

    private void tfJumlahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJumlahFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahFocusGained

    private void tfJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahActionPerformed

    private void tfHargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfHargaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaFocusGained

    private void tfHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaActionPerformed

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        addInventaris();
        clearForm();
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        clearForm();
    }//GEN-LAST:event_btnResetMouseClicked

    private void jlTambahKeuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTambahKeuanganMouseClicked
        Keuangan keuangan = new Keuangan();
        this.dispose();
        keuangan.setVisible(true);
    }//GEN-LAST:event_jlTambahKeuanganMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteKeuangan();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateInventaris();
        clearForm();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tfIdInventarisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIdInventarisFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdInventarisFocusGained

    private void tfIdInventarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdInventarisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdInventarisActionPerformed

    private void tableDaftarPengurusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDaftarPengurusMouseClicked

        int i = tableDaftarPengurus.getSelectedRow();
        int idInventaris, qtyData;
        String namaBarang, merekBarang, keterangan, satuan, kondisi, hargaBarang;

        idInventaris = dataInventaris.get(i).getIdInventaris();
        qtyData = dataInventaris.get(i).getQtyData();
        namaBarang = dataInventaris.get(i).getNamaBarang();
        merekBarang = dataInventaris.get(i).getMerekBarang();
        keterangan = dataInventaris.get(i).getKeterangan();
        satuan = dataInventaris.get(i).getSatuan();
        kondisi = dataInventaris.get(i).getKondisi();
        hargaBarang = dataInventaris.get(i).getHargaBarang();

        int satuanCb = 0;
        switch (satuan) {
            case "Unit":
                satuanCb = 0;
                break;
            case "Items":
                satuanCb = 1;
                break;
            case "Pcs":
                satuanCb = 2;
                break;
        }

        int kondisiCb = 0;
        switch (kondisi) {
            case "Normal":
                kondisiCb = 0;
                break;
            case "Baru":
                kondisiCb = 1;
                break;
            case "Bekas":
                kondisiCb = 2;
                break;
            case "Rusak":
                kondisiCb = 3;
                break;
        }

        cbKondisi.setSelectedIndex(kondisiCb);
        cbSatuan.setSelectedIndex(satuanCb);
        tfIdInventaris.setText(String.valueOf(idInventaris));
        tfNamaBarang.setText(namaBarang);
        tfMerek.setText(merekBarang);
        tfKeterangan.setText(keterangan);
        tfJumlah.setText(String.valueOf(qtyData));
        tfHarga.setText(hargaBarang);

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
            java.util.logging.Logger.getLogger(Inventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventaris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnDelete;
    private java.awt.Button btnReset;
    private java.awt.Button btnSimpan;
    private java.awt.Button btnUpdate;
    private javax.swing.JComboBox<String> cbKondisi;
    private javax.swing.JComboBox<String> cbSatuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField tfHarga;
    public javax.swing.JTextField tfIdInventaris;
    private javax.swing.JPanel tfJabatan;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JTextField tfKeterangan;
    private javax.swing.JTextField tfMerek;
    private javax.swing.JTextField tfNamaBarang;
    // End of variables declaration//GEN-END:variables
}
