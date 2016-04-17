SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Table structure for table `prj_cereri`
--

CREATE TABLE IF NOT EXISTS `prj_cereri` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `last_name` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `uname` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `tipconcediu` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `pass` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `datastart` date NOT NULL,
  `datafinal` date NOT NULL,
  `nrzile` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `status` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=3 ;

--
-- Dumping data for table `prj_cereri`
--

INSERT INTO `prj_cereri` (`id`, `first_name`, `last_name`, `email`, `uname`, `tipconcediu`, `pass`, `datastart`, `datafinal`, `nrzile`, `status`) VALUES
(1, 'Dragos', 'Secara', 'dragos@email.com', 'drasec', 'odihna', '1234', '2016-04-01', '2016-04-18', '10', NULL),
(2, 'Andrei', 'Paraschiv', 'andrei@email.com', 'andpar', 'odihna', '1234', '2016-04-01', '2016-04-18', '15', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prj_members`
--

CREATE TABLE IF NOT EXISTS `prj_members` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `last_name` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `uname` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `pass` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `regdate` date NOT NULL,
  `poza` varchar(100) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=2 ;

--
-- Dumping data for table `prj_members`
--

INSERT INTO `prj_members` (`id`, `first_name`, `last_name`, `email`, `uname`, `pass`, `regdate`, `poza`) VALUES
(1, 'Dragos', 'Secara', 'dragos@email.com', 'drasec', '1234', '2016-04-04', 'andrei.jpg'),
(2, 'Andrei', 'Paraschiv', 'andrei@email.com', 'andpar', '1234', '2016-04-04', 'andrei.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
