/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Config;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moch Billy Refanto
 */
public class Register extends javax.swing.JFrame {

    static String namaLengkap, username, email, password, konfirmasiPassword, jenisKelamin, kontak, alamat, queryReg, validasiUsername, queryShow, usernameData, emailData, idData, queryInsertMasjid, queryShowData;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String dateToday = dateTimeFormatter.format(now);

    private void cleanForm() {
        tfNamaLengkap.setText(null);
        tfUsername.setText(null);
        tfEmail.setText(null);
        tfKontak.setText(null);
        taAlamat.setText(null);
        tfEmail.setText(null);
        tpPassword.setText(null);
        tpKonfirmasiPassword.setText(null);

    }

//    private void showData() throws SQLException {
//
//        try {
//            ResultSet res = statement.executeQuery(queryShow);
//            while (res.next()) {
//                usernameData = res.getString("username");
//                emailData = res.getString("email");
//                idData = res.getString("id");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error " + e.getMessage());
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//
//    }
    private void registrasiAkun() throws SQLException {
        namaLengkap = tfNamaLengkap.getText();
        username = tfUsername.getText().trim();
        email = tfEmail.getText().trim();
        password = String.valueOf(tpPassword.getPassword());
        konfirmasiPassword = String.valueOf(tpKonfirmasiPassword.getPassword());
        jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
        kontak = tfKontak.getText();
        alamat = taAlamat.getText();
        validasiUsername = username.toLowerCase();

        //valisasi jika text field kosong maka tidak boleh insert data
        if (namaLengkap.isEmpty()
                || validasiUsername.isEmpty() || email.isEmpty()
                || password.isEmpty() || password.isEmpty()
                || konfirmasiPassword.isEmpty() || jenisKelamin.isEmpty()
                || kontak.isEmpty() || alamat.isEmpty()) {
            System.out.println("Silahkan isi semua data!");
            JOptionPane.showMessageDialog(null, "Silahkan isi semua data!");
        } else {
            queryReg = "INSERT INTO m_users(nama_lengkap,username,email,password,jenis_kelamin,kontak,alamat,level_user,created_at) VALUES (?,?,?,?,?,?,?,?,?)";
            queryInsertMasjid = "INSERT INTO m_masjid (id_m_users,created_at) VALUES (?,?)";
            queryShowData = "SELECT * FROM m_users";
            try {
                Connection conn = (Connection) Config.configDB();
                Statement statement = conn.createStatement();
                ResultSet res = statement.executeQuery(queryShowData);

                //mengambil data username & email dari tabel m_users
                while (res.next()) {
                    usernameData = res.getString("username");
                    emailData = res.getString("email");
                }

                //validasi jika usernam & email sudah terdaftar maka tidak boleh mengunakannya lagi
                if (validasiUsername.equals(usernameData)) {
                    System.out.println("Username sudah terdaftar");
                    JOptionPane.showMessageDialog(null, "Username sudah terdaftar!");
                } else if (email.equals(emailData)) {
                    System.out.println("Email Sudah terdaftar!");
                    JOptionPane.showMessageDialog(null, "Email Sudah terdaftar!");
                    //validasi jika password dan konfirmasi password tidak sama
                } else if (!password.equals(konfirmasiPassword)) {
                    JOptionPane.showMessageDialog(null, "Password tidak sama!");
                    System.out.println("Password tidak sama!");
                } else {
                    PreparedStatement ps = conn.prepareStatement(queryReg);
                    ps.setString(1, namaLengkap);
                    ps.setString(2, validasiUsername);
                    ps.setString(3, email);
                    ps.setString(4, password);
                    ps.setString(5, jenisKelamin);
                    ps.setString(6, kontak);
                    ps.setString(7, alamat);
                    ps.setString(8, "Pengurus");
                    ps.setString(9, dateToday);

                    int rowAffected = ps.executeUpdate();
                    System.out.println("[Pesan Register]");
                    System.out.println("Registrasi Berhasil! "
                            + "\nUsername : " + validasiUsername
                    );

                    //mengambil data data id dari tabel m_users berdasarkan username
                    queryShow = "SELECT * FROM m_users WHERE username = '" + validasiUsername + "'";
                    res = statement.executeQuery(queryShow);

                    while (res.next()) {
                        idData = res.getString("id");
                    }

                    //Insert data pada tabel m_masjid untuk membuat masjid dengan parent id_m_users.
                    PreparedStatement ps2 = conn.prepareStatement(queryInsertMasjid);
                    ps2.setString(1, idData);
                    ps2.setString(2, dateToday);
                    rowAffected = ps2.executeUpdate();
                    System.out.println("Berhasil membuat masjid"
                            + "\nid_m_users : "
                            + idData);
                    JOptionPane.showMessageDialog(this, "Registrasi Akun Berhasil!");
                    cleanForm();
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }
    }

    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
        
         Dimension screenSize = 
         Toolkit.getDefaultToolkit().getScreenSize();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNamaLengkap = new javax.swing.JTextField();
        btnRegistrasi = new java.awt.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        tfUsername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfKontak = new javax.swing.JTextField();
        cbJenisKelamin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbSyarat = new java.awt.Checkbox();
        tpPassword = new javax.swing.JPasswordField();
        tpKonfirmasiPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrasi Akun");
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrasi Akun");

        tfNamaLengkap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNamaLengkap.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfNamaLengkap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNamaLengkapFocusGained(evt);
            }
        });
        tfNamaLengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaLengkapActionPerformed(evt);
            }
        });

        btnRegistrasi.setActionCommand("Registrasi");
        btnRegistrasi.setBackground(new java.awt.Color(38, 83, 212));
        btnRegistrasi.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnRegistrasi.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrasi.setLabel("Registrasi");
        btnRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrasiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel4.setText("Nama Lengkap");

        jLabel6.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel6.setText("Username");

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel7.setText("Email Akrif");

        tfEmail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfEmail.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEmailFocusGained(evt);
            }
        });
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        tfUsername.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfUsername.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfUsernameFocusGained(evt);
            }
        });
        tfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsernameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel9.setText("Konfirmasi Password");

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel10.setText("Jenis Kelamin");

        jLabel11.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel11.setText("Kontak");

        tfKontak.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfKontak.setMargin(new java.awt.Insets(3, 3, 3, 3));
        tfKontak.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfKontakFocusGained(evt);
            }
        });
        tfKontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKontakActionPerformed(evt);
            }
        });

        cbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        jLabel12.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel12.setText("Alamat");

        cbSyarat.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cbSyarat.setLabel("saya mengisi data dengan benar dan setuju dengan \nsyarat & ketentuan yang berlaku");

        tpPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tpPassword.setMargin(new java.awt.Insets(3, 3, 3, 3));

        tpKonfirmasiPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tpKonfirmasiPassword.setMargin(new java.awt.Insets(3, 3, 3, 3));

        taAlamat.setColumns(20);
        taAlamat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        taAlamat.setRows(5);
        taAlamat.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(taAlamat);

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Sudah punya akun?");

        labelLogin.setFont(new java.awt.Font("Tekton Pro", 0, 14)); // NOI18N
        labelLogin.setForeground(new java.awt.Color(0, 51, 255));
        labelLogin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelLogin.setText("Login");
        labelLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(tfNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(tpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(tpKonfirmasiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(cbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfKontak, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))))
                    .addComponent(cbSyarat, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnRegistrasi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpKonfirmasiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKontak, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSyarat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelLogin))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(7, 17, 44));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_mosque_48px.png"))); // NOI18N
        jLabel5.setText("DKMNATION");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(74, 74, 74)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrasiActionPerformed
        try {
            registrasiAkun();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrasiActionPerformed

    private void tfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailFocusGained

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfUsernameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsernameFocusGained

    private void tfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsernameActionPerformed

    private void tfNamaLengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaLengkapActionPerformed

    }//GEN-LAST:event_tfNamaLengkapActionPerformed

    private void tfNamaLengkapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaLengkapFocusGained

    }//GEN-LAST:event_tfNamaLengkapFocusGained

    private void tfKontakFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfKontakFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKontakFocusGained

    private void tfKontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKontakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKontakActionPerformed

    private void labelLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLoginMouseClicked
        LoginMember login = new LoginMember();
        this.dispose();
        login.setVisible(true);

    }//GEN-LAST:event_labelLoginMouseClicked

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnRegistrasi;
    private javax.swing.JComboBox<String> cbJenisKelamin;
    private java.awt.Checkbox cbSyarat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfKontak;
    private javax.swing.JTextField tfNamaLengkap;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JPasswordField tpKonfirmasiPassword;
    private javax.swing.JPasswordField tpPassword;
    // End of variables declaration//GEN-END:variables
}
