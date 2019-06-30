-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 30, 2019 alle 12:02
-- Versione del server: 10.1.39-MariaDB
-- Versione PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `listinor`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `categorie`
--

CREATE TABLE `categorie` (
  `Categoria` varchar(50) NOT NULL,
  `Min` int(10) UNSIGNED NOT NULL,
  `Max` int(10) UNSIGNED NOT NULL,
  `Proporzione` double UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `categorie`
--

INSERT INTO `categorie` (`Categoria`, `Min`, `Max`, `Proporzione`) VALUES
('Cuscino culla', 0, 1, 1),
('Materasso culla', 0, 1, 1),
('Copertina lettino', 0, 10, 1),
('Copri materasso culla', 0, 10, 1),
('Piumotto culla', 0, 10, 1),
('Vestitino ', 0, 10, 1),
('Copertina culla', 0, 20, 2),
('Lenzuolino culla', 0, 20, 2),
('Fascia ombellicale', 1, 10, 1),
('Cuffia', 2, 20, 2),
('Lenzuolino lettino', 2, 20, 2),
('Tutina', 2, 20, 2),
('Body', 3, 30, 3),
('Calzini', 3, 30, 3),
('Ghettina', 3, 30, 3),
('Bavetta', 4, 40, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `listino`
--

CREATE TABLE `listino` (
  `idnum` varchar(16) NOT NULL,
  `name` varchar(150) NOT NULL,
  `category` varchar(150) NOT NULL,
  `price` double NOT NULL,
  `season` varchar(10) DEFAULT NULL,
  `sellerprice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `listino`
--

INSERT INTO `listino` (`idnum`, `name`, `category`, `price`, `season`, `sellerprice`) VALUES
('A1', 'tutina ciniglia ellepì', 'Tutina', 7.5, 'Inverno', 4.5),
('A101', 'copripanno ricamato ', 'Ghettina', 4.5, 'Estate', 2.93),
('A106', 'calzini caldo cotone ', 'Calzini', 3.5, 'Primavera', 1.75),
('A107', 'calzini caldi multicolor', 'Calzini', 1.8, 'Inverno', 0.9),
('A108', 'calzini cotone traforato', 'Calzini', 2, 'Estate', 1),
('A111', 'cuffia felpa rigato', 'Cuffia', 7.5, 'Inverno', 3.75),
('A113', 'cuffia cotone bianco', 'Cuffia', 4, 'Primavera', 2),
('A116', 'cuffia cotone leggero', 'Cuffia', 5, 'Estate', 2.5),
('A120', 'fascia ombellicale rete', 'Fascia ombellicale', 4.5, '', 2.48),
('A122', 'lenzuolino culla alberi', 'Lenzuolino culla', 13, '', 8.45),
('A140', 'copertina culla lana merinos', 'Copertina culla', 35, 'Inverno', 22.75),
('A144', 'copri materasso culla bianco', 'Copri materasso culla', 9.9, '', 6.44),
('A146', 'cuscino culla spugna', 'Cuscino culla', 8, '', 4),
('A148', 'materasso culla lattice', 'Materasso culla', 25, '', 12.5),
('A157', 'piumotto culla estate', 'Piumotto culla', 15, 'Inverno', 9.75),
('A171', 'bavetta farfalla', 'Bavetta', 3, '', 1.5),
('A175', 'bavetta con bottone', 'Bavetta', 0.8, '', 0.4),
('A194', 'vestito lana cerimonia', 'Vestitino', 45.9, 'Inverno', 22.95),
('A205', 'scamiciato con camicina', 'Vestitino', 22.9, 'Primavera', 11.45),
('A210', 'vestito s/l con copripanno', 'Vestitino', 6.5, 'Estate', 3.25),
('A215', 'copertina pique giallo', 'Copertina culla ', 9.9, 'Estate', 4.95),
('A226', 'copertina ciniglia  cielo', 'Copertina culla ', 12.6, 'Primavera', 6.3),
('A256', 'lenzuolino lettino zoo', 'Lenzuolino lettino ', 7.5, 'Estate', 3.75),
('A282', 'lenzuolino lettino gufo cielo', 'Lenzuolino lettino ', 19.2, 'Primavera', 9.6),
('A283', 'lenzuolino lettino tinta unita', 'Lenzuolino lettino ', 10.9, 'Inverno', 5.45),
('A285', 'copertina lettino con stelle', 'Copertina lettino', 24.3, 'Inverno', 12.15),
('A286', 'copertina lettino con ricami', 'Copertina lettino', 36.9, 'Estate', 18.45),
('A288', 'copertina lettino arcobaleno', 'Copertina lettino', 15.9, 'Primavera', 7.95),
('A42', 'tutina cotone fiori', 'Tutina', 5.5, 'Primavera', 3.3),
('A54', 'pagliaccetto zucchi erba', 'Tutina', 9.9, 'Estate', 5.94),
('A65', 'body felpa Disney ', 'Body', 4.5, 'Inverno', 2.25),
('A73', 'body cotone m/m ', 'Body', 4.5, 'Primavera', 2.25),
('A82', 'body cotone s/l ricamato ', 'Body', 6.5, 'Estate', 3.25),
('A93', 'ghettina felpa fantasia fiori', 'Ghettina', 3.8, 'Inverno', 2.47),
('A99', 'ghettina con pizzo ', 'Ghettina', 6.8, 'Primavera', 4.42);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`Categoria`),
  ADD KEY `Min` (`Min`,`Max`,`Proporzione`),
  ADD KEY `Categoria` (`Categoria`,`Min`,`Max`,`Proporzione`);

--
-- Indici per le tabelle `listino`
--
ALTER TABLE `listino`
  ADD PRIMARY KEY (`idnum`),
  ADD KEY `idnum` (`idnum`,`name`,`category`,`price`,`season`,`sellerprice`),
  ADD KEY `category` (`category`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `listino`
--
ALTER TABLE `listino`
  ADD CONSTRAINT `listino_ibfk_1` FOREIGN KEY (`category`) REFERENCES `categorie` (`Categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
