-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2016 at 07:38 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jademy`
--

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE IF NOT EXISTS `requests` (
  `requestid` int(11) NOT NULL AUTO_INCREMENT,
  `tipconcediu` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `datastart` date NOT NULL,
  `datafinal` date NOT NULL,
  `duration` int(11) NOT NULL,
  `status` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`requestid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=2 ;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`requestid`, `tipconcediu`, `datastart`, `datafinal`, `duration`, `status`, `userid`) VALUES
(1, 'odihna', '2016-04-05', '2016-04-14', 9, 'INITIATA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `lastname` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(100) COLLATE latin1_general_cs DEFAULT NULL,
  `regdate` date NOT NULL,
  `poza` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `username` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `password` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `rank` varchar(20) COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=6 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `firstname`, `lastname`, `email`, `regdate`, `poza`, `username`, `password`, `rank`) VALUES
(1, 'Dragos', 'Secara', 'dragos.secara@email.com', '2016-04-07', NULL, 'drasec', '1234', 'admin'),
(2, 'Ion', 'Popa', 'popa.ion@email.com', '2016-04-19', NULL, 'popion', '1234', 'user'),
(3, 'Gogu', 'Muncitoru', 'gm@email.com', '2016-04-03', NULL, 'mungog', '1234', 'user'),
(4, 'Gigel', 'Criminalul', 'crimeInc@email.com', '2016-04-26', 'skull.jpg', 'crigig', '1234', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `userToRequest` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
