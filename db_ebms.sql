-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2019 at 06:05 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ebms`
--
CREATE DATABASE IF NOT EXISTS `db_ebms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_ebms`;

-- --------------------------------------------------------

--
-- Table structure for table `tb_admin`
--

CREATE TABLE `tb_admin` (
  `A_Name` varchar(32) NOT NULL,
  `A_Password` varchar(32) NOT NULL,
  `A_Contact` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_admin`
--

INSERT INTO `tb_admin` (`A_Name`, `A_Password`, `A_Contact`) VALUES
('Administrator', 'cin>>admin', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tb_customer`
--

CREATE TABLE `tb_customer` (
  `Meter_No` int(20) NOT NULL,
  `C_Name` varchar(32) NOT NULL,
  `C_Password` varchar(32) NOT NULL,
  `C_Balance` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_hash`
--

CREATE TABLE `tb_hash` (
  `Hash_Code` bigint(20) NOT NULL,
  `Amount` int(20) NOT NULL,
  `Status` varchar(10) NOT NULL DEFAULT 'Unused'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD UNIQUE KEY `Name` (`A_Name`,`A_Password`);

--
-- Indexes for table `tb_customer`
--
ALTER TABLE `tb_customer`
  ADD PRIMARY KEY (`C_Name`),
  ADD UNIQUE KEY `Meter_No` (`Meter_No`);

--
-- Indexes for table `tb_hash`
--
ALTER TABLE `tb_hash`
  ADD PRIMARY KEY (`Hash_Code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
