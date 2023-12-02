-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Feb 2021 pada 17.28
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jadwal`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_guru`
--

CREATE TABLE `tb_guru` (
  `guru_id` int(100) NOT NULL,
  `nip` int(30) NOT NULL,
  `guru` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_guru`
--

INSERT INTO `tb_guru` (`guru_id`, `nip`, `guru`) VALUES
(1, 12345, 'Pak Jamal'),
(2, 12346, 'Bu Nindy'),
(3, 11111, 'Pak Romy'),
(4, 22222, 'Bu Rini'),
(6, 1234, 'Pak Mat'),
(8, 0, '-');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_hari`
--

CREATE TABLE `tb_hari` (
  `hari_id` int(11) NOT NULL,
  `hari` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_hari`
--

INSERT INTO `tb_hari` (`hari_id`, `hari`) VALUES
(1, 'Senin'),
(2, 'Selasa'),
(3, 'Rabu'),
(4, 'Kamis'),
(5, 'Jumat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `jadwal_id` int(11) NOT NULL,
  `hari_id` int(10) NOT NULL,
  `jam_mulai` varchar(50) NOT NULL,
  `jam_selesai` varchar(50) NOT NULL,
  `kelas_id` int(100) NOT NULL,
  `mapel_id` int(100) NOT NULL,
  `guru_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_jadwal`
--

INSERT INTO `tb_jadwal` (`jadwal_id`, `hari_id`, `jam_mulai`, `jam_selesai`, `kelas_id`, `mapel_id`, `guru_id`) VALUES
(4, 1, '07.00', '08.00', 4, 10, 1),
(5, 1, '08.00', '09.00', 4, 18, 8),
(7, 2, '10.00', '11.00', 4, 11, 2),
(9, 2, '07.00', '08.00', 4, 12, 3),
(10, 2, '08.00', '09.00', 4, 18, 8),
(11, 2, '09.00', '10.00', 4, 13, 4),
(12, 3, '07.00', '08.00', 8, 10, 1),
(13, 3, '08.00', '09.00', 8, 18, 8),
(15, 4, '07.00', '08.00', 8, 15, 3),
(28, 3, '09.00', '10.00', 8, 10, 1),
(29, 1, '09.00', '10.00', 4, 10, 1),
(30, 3, '11.00', '12.00', 8, 11, 2),
(37, 4, '08.00', '09.00', 8, 18, 8),
(41, 4, '09.00', '10.00', 8, 15, 3),
(43, 1, '08.00', '09.00', 13, 18, 8);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `kelas_id` int(100) NOT NULL,
  `kelas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_kelas`
--

INSERT INTO `tb_kelas` (`kelas_id`, `kelas`) VALUES
(4, 'X RPL A'),
(6, 'X RPL B'),
(7, 'X RPL C'),
(8, 'XI RPL A'),
(9, 'XI RPL B'),
(10, 'XI RPL C'),
(11, 'XII RPL A'),
(12, 'XII RPL B'),
(13, 'XII RPL C');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_mapel`
--

CREATE TABLE `tb_mapel` (
  `mapel_id` int(100) NOT NULL,
  `mapel` varchar(50) NOT NULL,
  `singkatan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_mapel`
--

INSERT INTO `tb_mapel` (`mapel_id`, `mapel`, `singkatan`) VALUES
(10, 'Ilmu Pengetahuan Alam', 'IPA'),
(11, 'Pendidikan Kewarganegaraan', 'PKN'),
(12, 'Pendidikan Keagamaan', 'Agama'),
(13, 'Fisika', 'Fisika'),
(14, 'Pemrograman Berbasis Objek', 'PBO'),
(15, 'Basis Data', 'BasDat'),
(16, 'Bahasa Indonesia', 'BInd'),
(17, 'Bahasa Inggris', 'Bing'),
(18, 'Istirahat Pelajaran', 'Istirahat'),
(20, 'Pemrograman Web dan Perangkat Bergerak', 'PWPB');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`user_id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_guru`
--
ALTER TABLE `tb_guru`
  ADD PRIMARY KEY (`guru_id`);

--
-- Indeks untuk tabel `tb_hari`
--
ALTER TABLE `tb_hari`
  ADD PRIMARY KEY (`hari_id`);

--
-- Indeks untuk tabel `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  ADD PRIMARY KEY (`jadwal_id`),
  ADD KEY `kelas_id` (`kelas_id`),
  ADD KEY `mapel_id` (`mapel_id`),
  ADD KEY `guru_id` (`guru_id`),
  ADD KEY `hari` (`hari_id`);

--
-- Indeks untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`kelas_id`);

--
-- Indeks untuk tabel `tb_mapel`
--
ALTER TABLE `tb_mapel`
  ADD PRIMARY KEY (`mapel_id`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_guru`
--
ALTER TABLE `tb_guru`
  MODIFY `guru_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `tb_hari`
--
ALTER TABLE `tb_hari`
  MODIFY `hari_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  MODIFY `jadwal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `kelas_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `tb_mapel`
--
ALTER TABLE `tb_mapel`
  MODIFY `mapel_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  ADD CONSTRAINT `fk_guru_id` FOREIGN KEY (`guru_id`) REFERENCES `tb_guru` (`guru_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_kelas_id` FOREIGN KEY (`kelas_id`) REFERENCES `tb_kelas` (`kelas_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mapel_id` FOREIGN KEY (`mapel_id`) REFERENCES `tb_mapel` (`mapel_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
