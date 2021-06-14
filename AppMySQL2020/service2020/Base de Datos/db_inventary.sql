-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-11-2020 a las 08:03:20
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_inventary`
--
CREATE DATABASE IF NOT EXISTS `db_inventary` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `db_inventary`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_categorias`
--

CREATE TABLE `tb_categorias` (
  `id_categoria` int(11) NOT NULL,
  `nom_categoria` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `estado_categoria` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tb_categorias`
--

INSERT INTO `tb_categorias` (`id_categoria`, `nom_categoria`, `estado_categoria`) VALUES
(1, 'Cuero', 1),
(2, 'Tela', 1),
(3, 'Hierro', 1),
(4, 'Plastico', 0),
(5, 'Madera', 0),
(10, 'Prueba', 1),
(11, 'Celular', 1),
(12, 'Tablet', 1),
(15, 'Plumon', 1),
(20, 'Smart TV', 1),
(50, 'MARCADORES', 1),
(100, 'Zapato', 1),
(200, 'Cables', 1),
(234, 'Libros', 1),
(444, 'Sodas', 1),
(500, 'AUDIFONOS', 1),
(555, 'Papel', 1),
(556, 'Fan', 1),
(987, 'Bebidas', 1),
(7678, 'Libros', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_productos`
--

CREATE TABLE `tb_productos` (
  `id_producto` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nom_producto` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `des_producto` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `stock` decimal(5,2) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `unidad_medida` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado_producto` tinyint(1) NOT NULL,
  `categoria` int(11) NOT NULL,
  `fecha_entrada` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tb_productos`
--

INSERT INTO `tb_productos` (`id_producto`, `nom_producto`, `des_producto`, `stock`, `precio`, `unidad_medida`, `estado_producto`, `categoria`, `fecha_entrada`) VALUES
('10', 'PLUMON', 'Tinta permanente color Rojo', '600.00', '1.00', 'U', 1, 50, '2020-11-03 02:49:05'),
('2', 'Smartphone.', 'SAMSUNG', '0.00', '125.00', 'U', 1, 11, '2020-11-16 05:22:16'),
('2020', 'Zapato Caterpilar', 'Cuero fino, color negro, talla 8, doble suela.', '10.00', '120.00', 'U', 1, 100, '2020-10-21 04:25:05'),
('700', 'Soda', 'PEPSI LITE', '12.00', '1.35', 'U', 1, 987, '2020-11-16 05:30:50'),
('701', 'Seven UP', 'Transparente', '25.00', '5.00', 'U', 1, 987, '2020-11-16 06:34:36'),
('900', 'Ventilador LG X', 'NA', '100.00', '55.98', 'U', 1, 555, '2020-10-22 11:27:59'),
('AUDIOS2020', 'SAMSUNG', 'COLOR BLANCO CON MICROFONO.', '10.00', '8.00', 'U', 1, 500, '2020-11-16 05:33:48'),
('CA2020', 'CABLE CAL 6 ', '2 FASE Y NEUTRO', '100.00', '80.99', 'U', 1, 200, '2020-10-21 05:13:14'),
('ZACUE2020', 'Zapato Lee', 'Cuerina sintético, color café, talla 7, suela normal.', '999.00', '25.00', 'U', 0, 100, '2020-10-21 04:30:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuarios`
--

CREATE TABLE `tb_usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(35) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `pregunta` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `respuesta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_categorias`
--
ALTER TABLE `tb_categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD CONSTRAINT `tb_productos_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `tb_categorias` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
