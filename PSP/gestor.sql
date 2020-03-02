-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-03-2020 a las 00:17:06
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestor`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacen`
--

CREATE TABLE `almacen` (
  `id_almacen` int(5) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `dimensiones` varchar(20) NOT NULL,
  `foto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `almacen`
--

INSERT INTO `almacen` (`id_almacen`, `nombre`, `ubicacion`, `dimensiones`, `foto`) VALUES
(1, 'alpha', '4.7', '', ''),
(2, 'beta', '4.8', '', ''),
(3, 'charlie', '4.9', '', ''),
(4, 'echo', '5.7', '', ''),
(5, 'A1', '6', '', ''),
(6, 'B2', '6', '', ''),
(7, 'C1', '6', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `correo` varchar(60) NOT NULL,
  `user` varchar(16) NOT NULL,
  `password` varchar(200) NOT NULL,
  `passFTP` varchar(20) DEFAULT 'admin1234'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`correo`, `user`, `password`, `passFTP`) VALUES
('correo@gmail.com', 'admin', '2ffc8a39deeee70117c89d5d239754e015f1f5c53a263314fd4eb07ea96cd1704b155ef72e61c581fcf347beb7b6ddaa024a724a38466c38e7a111069900df38', 'admin1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros`
--

CREATE TABLE `registros` (
  `id_objeto` int(11) NOT NULL,
  `id_almacen` int(11) DEFAULT NULL,
  `modificacion` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `registros`
--

INSERT INTO `registros` (`id_objeto`, `id_almacen`, `modificacion`) VALUES
(1, 3, '19/2/2020'),
(2, 1, NULL),
(3, 1, ''),
(4, 1, NULL),
(5, 1, NULL),
(6, 1, NULL),
(7, 1, NULL),
(8, 1, NULL),
(9, 1, NULL),
(10, 1, NULL),
(11, 1, NULL),
(12, 1, NULL),
(13, 1, NULL),
(14, 1, NULL),
(15, 1, NULL),
(16, 1, NULL),
(17, 1, NULL),
(18, 1, NULL),
(19, 1, NULL),
(20, 1, NULL),
(21, 2, NULL),
(22, 2, NULL),
(23, 2, NULL),
(24, 2, NULL),
(25, 2, NULL),
(26, 2, NULL),
(27, 2, NULL),
(28, 3, NULL),
(29, 3, NULL),
(30, 3, NULL),
(31, 3, NULL),
(32, 3, NULL),
(33, 3, NULL),
(34, 3, NULL),
(35, 3, NULL),
(36, 3, NULL),
(37, 3, NULL),
(38, 3, NULL),
(40, 4, NULL),
(41, 4, NULL),
(42, 4, NULL),
(43, 4, NULL),
(44, 4, NULL),
(45, 4, NULL),
(46, 4, NULL),
(47, 4, NULL),
(48, 4, NULL),
(49, 4, NULL),
(50, 4, NULL),
(51, 4, NULL),
(52, 4, NULL),
(53, 4, NULL),
(54, 4, NULL),
(55, 4, NULL),
(56, 4, NULL),
(57, 4, '19/2/2020'),
(60, 5, NULL),
(61, 5, NULL),
(62, 5, NULL),
(63, 5, NULL),
(64, 5, NULL),
(65, 6, NULL),
(66, 6, NULL),
(67, 6, NULL),
(68, 7, NULL),
(69, 7, NULL),
(70, 7, NULL),
(71, 7, NULL),
(333, 2, '121');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD PRIMARY KEY (`id_almacen`);

--
-- Indices de la tabla `registros`
--
ALTER TABLE `registros`
  ADD PRIMARY KEY (`id_objeto`),
  ADD KEY `id_almacen` (`id_almacen`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registros`
--
ALTER TABLE `registros`
  ADD CONSTRAINT `registros_ibfk_1` FOREIGN KEY (`id_almacen`) REFERENCES `almacen` (`id_almacen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
