-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2014 a las 07:01:45
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mybase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE IF NOT EXISTS `personas` (
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `edad` int(11) NOT NULL,
  `modo` varchar(100) NOT NULL,
`id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`nombre`, `apellido`, `edad`, `modo`, `id`) VALUES
('pedro', 'perez', 22, 'GET', 1),
('fabian', 'varon', 21, 'GET', 2),
('maria', 'lopez', 19, 'GET', 3),
('Jose', 'Salgado', 35, 'GET', 4),
('Andres', 'Garcia', 40, 'POST', 5),
('cristian', 'castro', 16, 'POST', 6),
('ajh', 'ajmk', 2, 'GET', 7),
('roberto', 'vargas', 19, 'POST', 8),
('sadf', 'sadf', 456, 'POST', 9),
('sadf', 'sadf', 456, 'GET', 10),
('s', 'ss', 12, 'POST', 11),
('des', 'aw', 33, 'POST', 12),
('ee', 'aa', 35, 'POST', 13),
('ss', 'ss', 222, 'GET', 14),
('aa', 'eee', 12, 'POST', 15),
('aa', 'eee', 12, 'POST', 16),
('aa', 'eee', 12, 'GET', 17),
('aa', 'eee', 12, 'POST', 18),
('www', 'qqq', 18, 'POST', 19),
('qqq', 'www', 16, 'GET', 20),
('lol', 'test', 1, 'POST', 21),
('envio', 'porPost', 13, 'POST', 22),
('envio', 'PorGet', 16, 'GET', 23),
('send', 'via', 25, 'GET', 24),
('via', 'send', 26, 'POST', 25),
('viia', 'dos', 14, 'POST', 26),
('dos', 'viia', 18, 'GET', 27),
('darto', 'dato', 28, 'POST', 28),
('el', 'gato', 29, 'POST', 29),
('daro', 'dataa', 63, 'POST', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
`id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `username`, `password`) VALUES
(1, 'FABIAN', 'VARON'),
(2, 'JORGE', 'MARTINEZ'),
(3, 'JORGE ELIECER', 'MARTINEZ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tbl_user`
--
ALTER TABLE `tbl_user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `tbl_user`
--
ALTER TABLE `tbl_user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
