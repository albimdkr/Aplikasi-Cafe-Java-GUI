-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2022 at 11:08 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_aplikasi_penjualan`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `total_harga_transaksi` ()  BEGIN
SELECT
SUM(penjualan.jumlah*penjualan.harga) AS 
total_harga
FROM penjualan;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(30) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'ajay', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `barista`
--

CREATE TABLE `barista` (
  `id_barista` int(11) NOT NULL,
  `nama_barista` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `tanggal_pendaftaran` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barista`
--

INSERT INTO `barista` (`id_barista`, `nama_barista`, `email`, `alamat`, `tanggal_pendaftaran`, `username`, `password`) VALUES
(1, 'Regi Sukma', 'regi@gmail.com', 'Kp. Bojong Reungas', '2022-05-09', 'regi', '12345'),
(3, 'Hari Wiharna', 'hari@gmail.com', 'Kp. Warusatangkal', '2022-05-09', 'hari', '54321'),
(5, '', '', '', '2022-05-26', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_transaksi` int(11) NOT NULL,
  `kode_menu` int(10) NOT NULL,
  `nama_menu` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `total_harga` int(10) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_transaksi`, `kode_menu`, `nama_menu`, `harga`, `jumlah`, `total_harga`, `tgl_transaksi`) VALUES
(6, 2, 'v60', 12000, 2, 24000, '2022-05-27'),
(7, 2, 'v60', 12000, 1, 12000, '2022-05-27');

--
-- Triggers `penjualan`
--
DELIMITER $$
CREATE TRIGGER `cancel` AFTER DELETE ON `penjualan` FOR EACH ROW BEGIN
UPDATE tb_menu SET
stok = stok + OLD.jumlah
WHERE kode_menu = OLD.kode_menu;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `cancel2` AFTER DELETE ON `penjualan` FOR EACH ROW BEGIN
DELETE FROM transaksi
WHERE kode_menu = OLD.kode_menu;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_habis` AFTER INSERT ON `penjualan` FOR EACH ROW BEGIN
DELETE FROM tb_menu
WHERE stok = 0
AND
kode_menu = NEW.kode_menu;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_menu`
--

CREATE TABLE `tb_menu` (
  `kode_menu` int(50) NOT NULL,
  `nama_menu` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `stok` int(10) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_menu`
--

INSERT INTO `tb_menu` (`kode_menu`, `nama_menu`, `harga`, `stok`, `tanggal`) VALUES
(1, 'Japanese Ice', 15000, 0, '2022-05-26'),
(2, 'v60', 12000, 0, '2022-05-27');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `tgl_transaksi` date NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `kode_menu` int(50) NOT NULL,
  `nama_menu` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_menu` int(10) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`tgl_transaksi`, `id_transaksi`, `kode_menu`, `nama_menu`, `harga`, `jumlah_menu`, `total_harga`) VALUES
('2022-05-27', 1, 1, 'Japanese Ice', 15000, 2, 30000),
('2022-05-27', 2, 1, 'Japanese Ice', 15000, 2, 30000),
('2022-05-27', 3, 2, 'v60', 12000, 3, 36000),
('2022-05-27', 4, 2, 'v60', 12000, 2, 24000),
('2022-05-27', 5, 2, 'v60', 12000, 2, 24000),
('2022-05-27', 6, 2, 'v60', 12000, 2, 24000),
('2022-05-27', 7, 2, 'v60', 12000, 1, 12000);

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `penjualan` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
INSERT INTO penjualan SET
id_transaksi = NEW.id_transaksi,
kode_menu = NEW.kode_menu,
nama_menu = NEW.nama_menu,
harga = NEW.harga,
jumlah = NEW.jumlah_menu,
total_harga = NEW.total_harga,
tgl_transaksi = NEW.tgl_transaksi;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `transaksi` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
UPDATE tb_menu SET
stok = stok - NEW.jumlah_menu
WHERE kode_menu = NEW.kode_menu;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barista`
--
ALTER TABLE `barista`
  ADD PRIMARY KEY (`id_barista`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `kode_menu` (`kode_menu`);

--
-- Indexes for table `tb_menu`
--
ALTER TABLE `tb_menu`
  ADD PRIMARY KEY (`kode_menu`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `kode_menu` (`kode_menu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `barista`
--
ALTER TABLE `barista`
  MODIFY `id_barista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_menu`
--
ALTER TABLE `tb_menu`
  MODIFY `kode_menu` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`kode_menu`) REFERENCES `tb_menu` (`kode_menu`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`kode_menu`) REFERENCES `tb_menu` (`kode_menu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
