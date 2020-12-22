-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2020 at 07:11 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dkmnation`
--

-- --------------------------------------------------------

--
-- Table structure for table `m_inventari_masjid`
--

CREATE TABLE `m_inventari_masjid` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_m_masjid` int(10) UNSIGNED NOT NULL,
  `nama_barang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `merek_barang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `keterangan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `qty` int(10) NOT NULL,
  `satuan` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kondisi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `harga_barang` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `m_inventari_masjid`
--

INSERT INTO `m_inventari_masjid` (`id`, `id_m_masjid`, `nama_barang`, `merek_barang`, `keterangan`, `qty`, `satuan`, `kondisi`, `harga_barang`, `created_at`, `updated_at`) VALUES
(2, 13, 'Speaker', 'Rode', 'Speaker Indoor', 3, 'Items', 'Baru', '130000', '21/12/2020 13:52:03', '21/12/2020 21:19:51'),
(3, 13, 'Microphone', 'Rode', 'Microphone Mimbar', 4, 'Unit', 'Normal', '150000', '21/12/2020 21:22:36', NULL),
(4, 15, 'Microphonee', 'Rode', 'Microphone Azan', 2, 'Unit', 'Baru', '200000', '22/12/2020 00:12:56', '22/12/2020 00:25:17');

-- --------------------------------------------------------

--
-- Table structure for table `m_keuangan`
--

CREATE TABLE `m_keuangan` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_m_masjid` int(10) UNSIGNED NOT NULL,
  `kategori` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `keterangan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nominal` int(10) NOT NULL,
  `tanggal` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `m_keuangan`
--

INSERT INTO `m_keuangan` (`id`, `id_m_masjid`, `kategori`, `keterangan`, `nominal`, `tanggal`, `created_at`, `updated_at`) VALUES
(1, 13, 'Pemasukan', 'Kas Masjid', 100000, '10/11/2020', '20/12/2020 15:27:20', NULL),
(2, 13, 'Pengeluaran', 'Pembayaran Listrik Bulan Desember', 10000, '11/12/2020', '20/12/2020 15:27:20', NULL),
(5, 15, 'Pemasukan', 'Infaq', 250000, '10/11/2020', '22/12/2020 00:44:39', NULL),
(6, 15, 'Pengeluaran', 'Listrik Bulan Desember', 50000, '06/12/2020', '22/12/2020 00:44:39', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `m_masjid`
--

CREATE TABLE `m_masjid` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_m_users` int(10) UNSIGNED NOT NULL,
  `nama_masjid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kapasitas_jamaah` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alamat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kelurahan` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kecamatan` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kabupaten` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kode_pos` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `luas_tanah` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tahun_berdiri` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sejarah_masjid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `m_masjid`
--

INSERT INTO `m_masjid` (`id`, `id_m_users`, `nama_masjid`, `kapasitas_jamaah`, `alamat`, `kelurahan`, `kecamatan`, `kabupaten`, `kode_pos`, `luas_tanah`, `tahun_berdiri`, `sejarah_masjid`, `created_at`, `updated_at`) VALUES
(13, 20, 'Masjid Nurul Islam', '1000', 'Blok Cimerta- Ciluwung', 'Kedungbunder', 'Gempol', 'Cirebon', '45161', '1000 m', '1990', 'Masjid ini di bangun oleh masyarakat sekitar', '18/12/2020 14:01:56', '21/12/2020 23:51:20'),
(14, 21, 'Masjid Al-Jabar', '250', 'Jl Raya Plumbon', 'Plumpon', 'Plumbon', 'Cirebon', '45660', '250 m', '2015', 'Masjid umum yang di bangun oleh masyarakat sekitar untuk tempat ibadah', '18/12/2020 14:01:56', '21/12/2020 21:37:30'),
(15, 22, 'Masjid Raya At-Taqwa', '5500', 'Jl. Kartini No.mor. 2', 'Kebonbaru', 'Kejaksan', 'Cirebon', '45121', '6500 m', '1918', 'Masjid ini dibangun pada 1480 didirikan dengan mengunakna tenaga manusia yang memilakal', '21/12/2020 22:20:16', '21/12/2020 23:58:39');

-- --------------------------------------------------------

--
-- Table structure for table `m_users`
--

CREATE TABLE `m_users` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama_lengkap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jenis_kelamin` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `kontak` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `level_user` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `m_users`
--

INSERT INTO `m_users` (`id`, `nama_lengkap`, `username`, `email`, `password`, `jenis_kelamin`, `kontak`, `alamat`, `level_user`, `created_at`) VALUES
(20, 'Moch Billy Refanto', 'billyrefanto', 'mochbillyrefanto@dkmnation.com', 'Billyrefanto123', 'Laki-Laki', '081221082799', 'Jl Pondok Pesantren Kempek BTN Bumi Abdi Negara Blok B5 Nomer 01, Desa Pegagan, Kecamatan Palimana, Kabupaten Cirebon, Jawa Barat', 'Pengurus', '18/12/2020 14:01:56'),
(21, 'Jhon Doe', 'jhondoe', 'Hello@jhondoe.com', '123', 'Laki-Laki', '08912210827', 'Cirebon', 'Pengurus', '18/12/2020 14:01:56'),
(22, 'Jane Doe', 'janedoe', 'hello@janedoe.com', 'Sementara123', 'Perempuan', '083823358402', 'BTN Bumi Abdi Negara Blok B5 Nomer 1, Cirebon', 'Pengurus', '21/12/2020 22:20:16');

-- --------------------------------------------------------

--
-- Table structure for table `u_pengurus`
--

CREATE TABLE `u_pengurus` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_m_masjid` int(10) UNSIGNED NOT NULL,
  `nama_lengkap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jenis_kelamin` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `tanggal_lahir` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kontak` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jabatan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `u_pengurus`
--

INSERT INTO `u_pengurus` (`id`, `id_m_masjid`, `nama_lengkap`, `jenis_kelamin`, `tanggal_lahir`, `kontak`, `alamat`, `jabatan`, `status`, `created_at`, `updated_at`) VALUES
(3, 13, 'Billy', 'Laki-Laki', '24/09/2001', '081221082799', 'Palimanan', 'Ketua', 'Aktif', '19/12/2020 13:21:29', NULL),
(4, 13, 'Ust Imron', 'Laki-Laki', '10/11/1989', '0892238394', 'Gempol', 'Ketua Umum', 'Aktif', '19/12/2020 16:56:04', NULL),
(5, 13, 'Billy Refanto', 'Laki-Laki', '24/09/2001', '0899999999', 'Palimanan', 'Sekretaris', 'Non-Aktif', '21/12/2020 14:48:21', NULL),
(6, 13, 'Jane Doe', 'Perempuan', '10/11/2020', '089123131231', 'Palimanan', 'Bendahara', 'Non-Aktif', '21/12/2020 16:13:51', NULL),
(7, 13, 'Jhon Doe', 'Laki-Laki', '23/12/1999', '34324324', 'Cirebon', 'Anggota', 'Aktif', '21/12/2020 16:53:51', NULL),
(8, 13, 'Ust Imron', 'Perempuan', '10/11/1989', '0892238394', 'Gempol', 'Ketua Umumd', 'Aktif', '21/12/2020 17:01:20', '21/12/2020 18:49:21'),
(9, 15, 'Ust Imron', 'Laki-Laki', '10/11/1980', '08991212121', 'Blok Ciluwung, Desa Gempol', 'Ketua', 'Aktif', '22/12/2020 00:30:22', '22/12/2020 00:31:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `m_inventari_masjid`
--
ALTER TABLE `m_inventari_masjid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_m_masjid` (`id_m_masjid`);

--
-- Indexes for table `m_keuangan`
--
ALTER TABLE `m_keuangan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_m_masjid_keuangan_2` (`id_m_masjid`);

--
-- Indexes for table `m_masjid`
--
ALTER TABLE `m_masjid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_m_users` (`id_m_users`);

--
-- Indexes for table `m_users`
--
ALTER TABLE `m_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `u_pengurus`
--
ALTER TABLE `u_pengurus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_m_masjid_u_pengurus` (`id_m_masjid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `m_inventari_masjid`
--
ALTER TABLE `m_inventari_masjid`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `m_keuangan`
--
ALTER TABLE `m_keuangan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `m_masjid`
--
ALTER TABLE `m_masjid`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `m_users`
--
ALTER TABLE `m_users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `u_pengurus`
--
ALTER TABLE `u_pengurus`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `m_inventari_masjid`
--
ALTER TABLE `m_inventari_masjid`
  ADD CONSTRAINT `fk_id_m_masjid` FOREIGN KEY (`id_m_masjid`) REFERENCES `m_masjid` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `m_keuangan`
--
ALTER TABLE `m_keuangan`
  ADD CONSTRAINT `fk_id_m_masjid_keuangan_2` FOREIGN KEY (`id_m_masjid`) REFERENCES `m_masjid` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `m_masjid`
--
ALTER TABLE `m_masjid`
  ADD CONSTRAINT `fk_id_m_users` FOREIGN KEY (`id_m_users`) REFERENCES `m_users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `u_pengurus`
--
ALTER TABLE `u_pengurus`
  ADD CONSTRAINT `fk_id_m_masjid_u_pengurus` FOREIGN KEY (`id_m_masjid`) REFERENCES `m_masjid` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
