-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-11-2023 a las 22:10:16
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vienterosdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`) VALUES
(1, 'categoria'),
(2, 'pollos'),
(3, 'cerdos'),
(4, 'peces'),
(5, 'vacas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `documento`, `nombre`, `id_proyecto`) VALUES
(1, '1003783452', 'francisco', 1),
(2, '232324112', 'jose hernandez', 2),
(3, '1020923', 'jhon vargas', 3),
(4, '9843412', 'jose petro', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id`, `fecha`, `id_cliente`) VALUES
(1, '2023-11-25 00:31:29', 1),
(2, '2023-11-25 00:38:44', 1),
(3, '2023-11-25 00:40:54', 1),
(4, '2023-11-25 00:41:59', 1),
(5, '2023-11-25 00:47:30', 1),
(6, '2023-11-25 00:48:51', 1),
(7, '2023-11-25 00:55:03', 2),
(8, '2023-11-25 01:03:20', 1),
(9, '2023-11-25 01:06:35', 1),
(10, '2023-11-25 01:08:09', 2),
(11, '2023-11-25 01:16:24', 3),
(12, '2023-11-25 01:21:45', 4),
(13, '2023-11-25 11:54:49', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE `insumos` (
  `id_insumo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`id_insumo`, `cantidad`, `fecha`, `nombre`, `precio`, `id_proyecto`) VALUES
(1, 2, '2023-11-25', 'engorde', 120000, 1),
(2, 3, '2023-11-25', 'crecedora', 150000, 1),
(3, 8, '2023-11-25', 'bebedero', 20000, 1),
(4, 5, '2023-11-25', 'alimento', 150000, 2),
(5, 4, '2023-11-25', 'bebederos', 20000, 2),
(6, 3, '2023-11-25', 'ponedero', 395000, 2),
(7, 3, '2023-11-25', 'alimento', 120000, 3),
(8, 1, '2023-11-25', 'filtro', 250000, 3),
(9, 3, '2023-11-25', 'crecedora', 160000, 4),
(10, 6, '2023-11-25', 'engorde', 130000, 4),
(11, 10, '2023-11-25', 'bebedero', 30000, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `cantidad_final` int(11) DEFAULT NULL,
  `cantidad_inicial` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `cantidad_final`, `cantidad_inicial`, `descripcion`, `fecha`, `nombre`, `precio`, `id_proyecto`) VALUES
(1, 98, 100, 'criollos', '2023-11-23', 'pollos', 1000, 1),
(2, 13, 20, 'ponedoras', '2023-11-23', 'gallinas', 30000, 2),
(3, 25, 43, 'criollo', '2023-11-23', 'huevo', 0, 2),
(4, 95, 100, '', '2023-11-24', 'bocachico', 2000, 3),
(5, 9, 10, 'cerdo belga', '2023-11-24', 'pietrain', 180000, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `id_proyecto` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`id_proyecto`, `descripcion`, `nombre`, `id_categoria`, `id_usuario`) VALUES
(1, 'pollos de engorde', 'galpon 1', 2, 1),
(2, 'gallinas ponedoras', 'galpon 2', 2, 1),
(3, 'bocachico', 'piscina 1', 4, 1),
(4, 'pietrain', 'chiquero 1', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `id` int(11) NOT NULL,
  `realizado` bit(1) NOT NULL,
  `tarea` varchar(255) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id`, `realizado`, `tarea`, `id_proyecto`) VALUES
(1, b'1', 'Comprar pollos.', 1),
(2, b'1', 'Adquirir una criadora o criadero.', 1),
(3, b'1', 'Comprar alimentos para el crecimiento y engorde.', 1),
(4, b'1', 'Obtener recipientes para agua.', 1),
(5, b'0', 'Establecer un programa de vacunación y cuidado veterinario.', 1),
(6, b'1', 'Comprar pollos.', 2),
(7, b'1', 'Adquirir una criadora o criadero.', 2),
(8, b'1', 'Comprar alimentos para el crecimiento y engorde.', 2),
(9, b'1', 'Obtener recipientes para agua.', 2),
(10, b'1', 'Establecer un programa de vacunación y cuidado veterinario.', 2),
(11, b'1', 'Seleccionar la especie de peces que deseas criar.', 3),
(12, b'1', 'Construir o adquirir un estanque o tanque adecuado para la acuicultura.', 3),
(13, b'1', 'Acondicionar el agua del estanque para que sea apropiada para la especie de peces elegida.', 3),
(14, b'1', 'Comprar alevines (peces jóvenes) de buena calidad.', 3),
(15, b'1', 'Proporcionar alimento adecuado para el crecimiento y desarrollo de los peces.', 3),
(16, b'1', 'Comprar cerdos.', 4),
(17, b'1', 'Adquirir una instalación o corral para cerdos.', 4),
(18, b'1', 'Comprar alimentos para el crecimiento y engorde.', 4),
(19, b'1', 'Obtener recipientes para agua.', 4),
(20, b'1', 'Establecer un programa de vacunación y cuidado veterinario.', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea_base`
--

CREATE TABLE `tarea_base` (
  `id` int(11) NOT NULL,
  `realizado` bit(1) NOT NULL,
  `tarea` varchar(255) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarea_base`
--

INSERT INTO `tarea_base` (`id`, `realizado`, `tarea`, `id_categoria`) VALUES
(1, b'0', 'Comprar pollos.', 2),
(2, b'0', 'Adquirir una criadora o criadero.', 2),
(3, b'0', 'Comprar alimentos para el crecimiento y engorde.', 2),
(4, b'0', 'Obtener recipientes para agua.', 2),
(5, b'0', 'Establecer un programa de vacunación y cuidado veterinario.', 2),
(6, b'0', 'Comprar cerdos.', 3),
(7, b'0', 'Adquirir una instalación o corral para cerdos.', 3),
(8, b'0', 'Comprar alimentos para el crecimiento y engorde.', 3),
(9, b'0', 'Obtener recipientes para agua.', 3),
(10, b'0', 'Establecer un programa de vacunación y cuidado veterinario.', 3),
(11, b'0', 'Seleccionar la especie de peces que deseas criar.', 4),
(12, b'0', 'Construir o adquirir un estanque o tanque adecuado para la acuicultura.', 4),
(13, b'0', 'Acondicionar el agua del estanque para que sea apropiada para la especie de peces elegida.', 4),
(14, b'0', 'Comprar alevines (peces jóvenes) de buena calidad.', 4),
(15, b'0', 'Proporcionar alimento adecuado para el crecimiento y desarrollo de los peces.', 4),
(16, b'0', 'Comprar vacas.', 5),
(17, b'0', 'Adquirir un área de pastoreo o corral.', 5),
(18, b'0', 'Obtener alimentos balanceados para el ganado.', 5),
(19, b'0', 'Proporcionar acceso a agua limpia.', 5),
(20, b'0', 'Establecer un programa de vacunación y cuidado veterinario.', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `email`, `nombre`, `password`) VALUES
(1, 'alex@gmail.com', 'alejandro', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `id_factura` int(11) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `cantidad`, `fecha`, `nombre`, `precio`, `id_factura`, `id_proyecto`) VALUES
(7, 6, '2023-11-25', 'huevo', 500, 7, 2),
(9, 1, '2023-11-25', 'pollos', 1000, 9, 1),
(10, 2, '2023-11-25', 'gallinas', 45000, 10, 2),
(11, 6, '2023-11-25', 'huevo', 500, 10, 2),
(12, 5, '2023-11-25', 'bocachico', 7000, 11, 3),
(13, 1, '2023-11-25', 'pietrain', 680000, 12, 4),
(14, 5, '2023-11-25', 'gallinas', 60000, 13, 2),
(15, 12, '2023-11-25', 'huevo', 700, 13, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4dksdskabh117ldoc0iq2cd8h` (`id_proyecto`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp2ab9l9b5y3b0pv1ag1hfiun7` (`id_cliente`);

--
-- Indices de la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD PRIMARY KEY (`id_insumo`),
  ADD KEY `FKsc3loygdbiljyoa295pc7rbsm` (`id_proyecto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `FKotte13xgxhqv7tdyb98wlhotd` (`id_proyecto`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`id_proyecto`),
  ADD KEY `FKjc9vdmea6wjqxv3jlyf7df4t2` (`id_categoria`),
  ADD KEY `FK2mxm4r5it9s1tpayg2nxbdxlq` (`id_usuario`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtquu0bj7xyj8gwcwg9jj6oy9j` (`id_proyecto`);

--
-- Indices de la tabla `tarea_base`
--
ALTER TABLE `tarea_base`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKipjfydaixw5jxiwrso7nvd2dh` (`id_categoria`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd5tt76k3hy5j10olssr40myse` (`id_factura`),
  ADD KEY `FK95o7d0i48mwo8ephbjdyulycs` (`id_proyecto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `insumos`
--
ALTER TABLE `insumos`
  MODIFY `id_insumo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id_proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `tarea_base`
--
ALTER TABLE `tarea_base`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FK4dksdskabh117ldoc0iq2cd8h` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `FKp2ab9l9b5y3b0pv1ag1hfiun7` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);

--
-- Filtros para la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD CONSTRAINT `FKsc3loygdbiljyoa295pc7rbsm` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FKotte13xgxhqv7tdyb98wlhotd` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD CONSTRAINT `FK2mxm4r5it9s1tpayg2nxbdxlq` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `FKjc9vdmea6wjqxv3jlyf7df4t2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);

--
-- Filtros para la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD CONSTRAINT `FKtquu0bj7xyj8gwcwg9jj6oy9j` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `tarea_base`
--
ALTER TABLE `tarea_base`
  ADD CONSTRAINT `FKipjfydaixw5jxiwrso7nvd2dh` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FK95o7d0i48mwo8ephbjdyulycs` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`),
  ADD CONSTRAINT `FKd5tt76k3hy5j10olssr40myse` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
