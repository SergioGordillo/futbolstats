-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2019 a las 11:13:54
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `futbolstats`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

CREATE TABLE `equipos` (
  `id` int(3) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `escudo` varchar(50) COLLATE latin1_spanish_ci NOT NULL DEFAULT 'escudo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`id`, `nombre`, `escudo`) VALUES
(1, 'RCD Espanyol Femení', 'escudo'),
(2, 'Athletic Club', 'escudo'),
(3, 'Real Sociedad Femenino', 'escudo'),
(4, 'Atlético de Madrid Femenino', 'escudo'),
(5, 'Madrid CFF ', 'escudo'),
(6, 'FC Barcelona Femení', 'escudo'),
(7, 'Real Betis Féminas', 'escudo'),
(8, 'Rayo Vallecano Femenino', 'escudo'),
(9, 'RC Deportivo Abanca', 'escudo'),
(10, 'CD Tacón', 'escudo'),
(11, 'Levante Femenino', 'escudo'),
(12, 'Sevilla FC Femenino', 'escudo'),
(13, 'EDF Logroño', 'escudo'),
(14, 'Sporting de Huelva', 'escudo'),
(15, 'Valencia CF Femenino', 'escudo'),
(16, 'UDG Tenerife Egatesa', 'escudo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornadas`
--

CREATE TABLE `jornadas` (
  `idjornada` int(10) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `idtemporada` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `jornadas`
--

INSERT INTO `jornadas` (`idjornada`, `nombre`, `idtemporada`) VALUES
(1, '1', 1),
(2, '2', 1),
(3, '3', 1),
(4, '4', 1),
(5, '5', 1),
(6, '6', 1),
(7, '7', 1),
(8, '8', 1),
(9, '9', 1),
(10, '10', 1),
(11, '11', 1),
(12, '12', 1),
(13, '13', 1),
(14, '14', 1),
(15, '15', 1),
(16, '16', 1),
(17, '17', 1),
(18, '18', 1),
(19, '19', 1),
(20, '20', 1),
(21, '21', 1),
(22, '22', 1),
(23, '23', 1),
(24, '24', 1),
(25, '25', 1),
(26, '26', 1),
(27, '27', 1),
(28, '28', 1),
(29, '29', 1),
(30, '30', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liga`
--

CREATE TABLE `liga` (
  `idliga` int(5) NOT NULL,
  `nombreliga` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `escudoliga` varchar(100) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `liga`
--

INSERT INTO `liga` (`idliga`, `nombreliga`, `pais`, `escudoliga`) VALUES
(1, 'Primera Iberdrola', 'España', 'escudo'),
(2, 'Reto Iberdrola', 'España', 'escudo'),
(3, 'La Liga Smart Bank', 'España', 'escudo'),
(4, 'La Liga Santander', 'España', 'escudo'),
(5, 'Barclays FAWSL', 'Inglaterra', 'escudo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidos`
--

CREATE TABLE `partidos` (
  `idpartido` int(10) NOT NULL,
  `idequipolocal` int(10) NOT NULL,
  `idequipovisitante` int(10) NOT NULL,
  `golesequipolocal` int(2) NOT NULL DEFAULT 0,
  `golesequipovisitante` int(2) NOT NULL DEFAULT 0,
  `fechapartido` date NOT NULL,
  `idjornada` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `partidos`
--

INSERT INTO `partidos` (`idpartido`, `idequipolocal`, `idequipovisitante`, `golesequipolocal`, `golesequipovisitante`, `fechapartido`, `idjornada`) VALUES
(1, 12, 16, 4, 0, '2019-09-07', 1),
(2, 14, 4, 0, 1, '2019-09-07', 1),
(3, 6, 10, 9, 1, '2019-09-07', 1),
(4, 11, 2, 2, 0, '2019-09-08', 1),
(5, 13, 8, 4, 1, '2019-09-08', 1),
(6, 9, 1, 3, 1, '2019-09-08', 1),
(7, 5, 7, 1, 0, '2019-09-08', 1),
(8, 15, 3, 2, 2, '2019-09-08', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporada`
--

CREATE TABLE `temporada` (
  `idtemporada` int(5) NOT NULL,
  `nombretemporada` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `idliga` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `temporada`
--

INSERT INTO `temporada` (`idtemporada`, `nombretemporada`, `idliga`) VALUES
(1, '2019/2020', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `jornadas`
--
ALTER TABLE `jornadas`
  ADD PRIMARY KEY (`idjornada`),
  ADD KEY `idtemporada` (`idtemporada`);

--
-- Indices de la tabla `liga`
--
ALTER TABLE `liga`
  ADD PRIMARY KEY (`idliga`);

--
-- Indices de la tabla `partidos`
--
ALTER TABLE `partidos`
  ADD PRIMARY KEY (`idpartido`),
  ADD KEY `idequipolocal` (`idequipolocal`),
  ADD KEY `idequipovisitante` (`idequipovisitante`),
  ADD KEY `idjornada` (`idjornada`);

--
-- Indices de la tabla `temporada`
--
ALTER TABLE `temporada`
  ADD PRIMARY KEY (`idtemporada`),
  ADD KEY `idliga` (`idliga`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `jornadas`
--
ALTER TABLE `jornadas`
  MODIFY `idjornada` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `liga`
--
ALTER TABLE `liga`
  MODIFY `idliga` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `partidos`
--
ALTER TABLE `partidos`
  MODIFY `idpartido` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `temporada`
--
ALTER TABLE `temporada`
  MODIFY `idtemporada` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `jornadas`
--
ALTER TABLE `jornadas`
  ADD CONSTRAINT `jornadas_ibfk_1` FOREIGN KEY (`idtemporada`) REFERENCES `temporada` (`idtemporada`);

--
-- Filtros para la tabla `partidos`
--
ALTER TABLE `partidos`
  ADD CONSTRAINT `partidos_ibfk_1` FOREIGN KEY (`idequipolocal`) REFERENCES `equipos` (`id`),
  ADD CONSTRAINT `partidos_ibfk_2` FOREIGN KEY (`idequipovisitante`) REFERENCES `equipos` (`id`),
  ADD CONSTRAINT `partidos_ibfk_3` FOREIGN KEY (`idjornada`) REFERENCES `jornadas` (`idjornada`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
