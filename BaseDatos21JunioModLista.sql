CREATE DATABASE  IF NOT EXISTS `japyld` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `japyld`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: japyld
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategorias` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategorias`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES ('ACC','Acción'),('ARC','Arcade'),('AVN','Aventura'),('CLA','Clásico'),('DEP','Deportivo'),('EST','Estrategia'),('GES','Gestión'),('INF','Infantil'),('MUS','Musical'),('SIM','Simulación'),('TER','Terror');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `idComentarios` int NOT NULL AUTO_INCREMENT,
  `comentario` text NOT NULL,
  `fecha_comentario` date NOT NULL,
  `ratingComentario` int DEFAULT NULL,
  `Juegos_idJuegos` int NOT NULL,
  `Persona_idPersona` int NOT NULL,
  PRIMARY KEY (`idComentarios`),
  KEY `fk_Comentarios_Juegos1_idx` (`Juegos_idJuegos`),
  KEY `fk_Comentarios_Persona1_idx` (`Persona_idPersona`),
  CONSTRAINT `fk_Comentarios_Juegos1` FOREIGN KEY (`Juegos_idJuegos`) REFERENCES `juegos` (`idJuegos`),
  CONSTRAINT `fk_Comentarios_Persona1` FOREIGN KEY (`Persona_idPersona`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (1,'Los gráficos y la jugabilidad son increíbles, pero la trama podría haber sido más profunda.','2023-01-14',4,12,2),(2,'Los escenarios son impresionantes y las secuencias de acción son emocionantes, aunque la duración del juego podría haber sido un poco más larga.','2023-04-10',5,12,4),(3,'La exploración de los entornos es fascinante y los personajes tienen una gran profundidad, pero algunos de los rompecabezas podrían haber sido más desafiantes. ','2022-10-12',4,12,6),(4,'El concepto del juego es interesante, pero la ejecución carece de originalidad y la jugabilidad se vuelve repetitiva rápidamente.','2023-01-14',3,6,8),(5,'El diseño visual es atractivo, pero la falta de profundidad en la mecánica de juego y la falta de contenido adicional hacen que la experiencia sea mediocre.','2023-06-14',3,6,9),(6,'Mirror Forge tiene potencial con su sistema de creación de espejos, pero la falta de variedad en los desafíos y la falta de una narrativa convincente hacen que pierda su atractivo rápidamente.','2023-07-10',2,6,7),(7,'Elden Ring es una obra maestra del género de acción y rol. Su vasto mundo abierto, combate desafiante y narrativa profunda se combinan para ofrecer una experiencia de juego inolvidable. Con su impresionante diseño artístico y una jugabilidad envolvente, es simplemente perfecto.','2023-02-15',5,4,7),(8,'Elden Ring redefine los límites del género con su inmersiva y vasta exploración. La atención al detalle en cada aspecto del juego, desde la narrativa hasta los combates épicos, es excepcional. Una experiencia sin igual que merece el reconocimiento completo.','2023-02-19',5,4,4),(9,'Elden Ring es un logro magistral en el mundo de los videojuegos. La combinación perfecta de desafío, ambientación y narrativa envuelve al jugador en un viaje épico lleno de misterios y descubrimientos. Cada aspecto, desde el diseño de niveles hasta la banda sonora, está cuidadosamente elaborado para brindar una experiencia inigualable.','2023-05-23',5,4,6);
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consolas`
--

DROP TABLE IF EXISTS `consolas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consolas` (
  `idConsolas` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idConsolas`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  UNIQUE KEY `idConsolas_UNIQUE` (`idConsolas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consolas`
--

LOCK TABLES `consolas` WRITE;
/*!40000 ALTER TABLE `consolas` DISABLE KEYS */;
INSERT INTO `consolas` VALUES ('SWI','NintendoSwitch'),('PS4','Playstation4'),('PS5','Playstation5'),('WIU','WIIU'),('XB3','Xbox360'),('XBO','XboxOne');
/*!40000 ALTER TABLE `consolas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuentos`
--

DROP TABLE IF EXISTS `descuentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `descuentos` (
  `idDescuentos` int NOT NULL,
  `precio_nuevo` decimal(10,0) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `duracion` int NOT NULL,
  `id_juego` int NOT NULL,
  `id_administrador` int NOT NULL,
  PRIMARY KEY (`idDescuentos`),
  KEY `fk_Descuentos_Juegos1_idx` (`id_juego`),
  KEY `fk_Descuentos_Persona1_idx` (`id_administrador`),
  CONSTRAINT `fk_Descuentos_Juegos1` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`idJuegos`),
  CONSTRAINT `fk_Descuentos_Persona1` FOREIGN KEY (`id_administrador`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuentos`
--

LOCK TABLES `descuentos` WRITE;
/*!40000 ALTER TABLE `descuentos` DISABLE KEYS */;
INSERT INTO `descuentos` VALUES (1,40,'2023-06-20',30,11,1),(2,70,'2023-06-20',30,9,5);
/*!40000 ALTER TABLE `descuentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `idImagenes` int NOT NULL AUTO_INCREMENT,
  `imagen` mediumblob,
  `tipo` varchar(45) NOT NULL,
  `direccion_archivo` varchar(45) NOT NULL,
  PRIMARY KEY (`idImagenes`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (1,NULL,'predeterminado','recursos/img/avatar1.png'),(2,NULL,'predeterminado','recursos/img/avatar2.png'),(3,NULL,'predeterminado','recursos/img/avatar3.png'),(4,NULL,'predeterminado','recursos/img/avatar4.png'),(5,NULL,'predeterminado','recursos/img/avatar5.png'),(6,NULL,'predeterminado','recursos/img/avatar6.png'),(7,NULL,'predeterminado','recursos/img/avatar7.png'),(8,NULL,'predeterminado','recursos/img/avatar8.png'),(9,NULL,'juego','recursos/img/Legend_of_Zelda.jpg'),(10,NULL,'juego','recursos/img/ResidentEvil4.jpg'),(11,NULL,'juego','recursos/img/HogwartsLegacy.jpeg'),(12,NULL,'juego','recursos/img/EldenRing.jpg'),(13,NULL,'juego','recursos/img/TheMortuaryAssistant.jpeg'),(14,NULL,'juego','recursos/img/MirrorForge.jpg'),(15,NULL,'juego','recursos/img/FIFA23.jpg'),(16,NULL,'juego','recursos/img/MarioKart8.jpg'),(17,NULL,'juego','recursos/img/Minecraft.jpeg'),(18,NULL,'juego','recursos/img/CitiesSkylines.jpg'),(19,NULL,'juego','recursos/img/Monopoly.jpeg'),(20,NULL,'juego','recursos/img/Uncharted3.jpg'),(21,NULL,'perfil','recursos/img/perfil1.jpg'),(22,NULL,'perfil','recursos/img/perfil2.jpg'),(23,NULL,'perfil','recursos/img/perfil3.jpg'),(24,NULL,'perfil','recursos/img/perfil4.jpg'),(25,NULL,'perfil','recursos/img/perfil5.jpg'),(26,NULL,'perfil','recursos/img/perfil6.jpg'),(27,NULL,'perfil','recursos/img/perfil7.jpg'),(28,NULL,'perfil','recursos/img/perfil8.jpg'),(29,NULL,'perfil','recursos/img/perfil9.jpg'),(30,NULL,'perfil','recursos/img/perfil10.jpg');
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegos`
--

DROP TABLE IF EXISTS `juegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegos` (
  `idJuegos` int NOT NULL AUTO_INCREMENT,
  `nombreJuegos` varchar(45) NOT NULL,
  `stock` int NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `estadoJuego` varchar(45) NOT NULL,
  `descripcion` text NOT NULL,
  `id_imagen` int NOT NULL,
  `id_categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idJuegos`),
  KEY `fk_Juegos_Imagenes1_idx` (`id_imagen`),
  KEY `fk_Juegos_Categorias1_idx` (`id_categoria`),
  CONSTRAINT `fk_Juegos_Categorias1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`idCategorias`),
  CONSTRAINT `fk_Juegos_Imagenes1` FOREIGN KEY (`id_imagen`) REFERENCES `imagenes` (`idImagenes`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos`
--

LOCK TABLES `juegos` WRITE;
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` VALUES (1,'Legend of Zelda',6,120.00,'Activo','The Legend of Zelda es una famosa franquicia de videojuegos de acción y aventura creada por Nintendo. Los juegos siguen las épicas aventuras de Link para rescatar a la Princesa Zelda y derrotar al malvado Ganon. Destaca por su jugabilidad emocionante, mundos inmersivos y personajes memorables.',9,'AVN'),(2,'Resident Evil 4',4,130.00,'Activo','Resident Evil 4 es un exitoso videojuego de acción y supervivencia desarrollado por Capcom. La historia sigue a Leon S. Kennedy en su misión para rescatar a la hija del presidente en un pueblo plagado de criaturas infectadas. Destaca por su innovador sistema de juego, atmósfera tensa y combates intensos.',10,'ACC'),(3,'Hogwarts Legacy',8,170.00,'Activo','Hogwarts Legacy es un esperado videojuego de rol de acción ambientado en el universo de Harry Potter. Los jugadores se sumergen en la experiencia de ser un estudiante de Hogwarts, explorando el castillo, aprendiendo hechizos y participando en emocionantes aventuras. Con una narrativa envolvente y gráficos impresionantes, ofrece una nueva y emocionante forma de explorar el mundo mágico de Harry Potter.',11,'AVN'),(4,'Elden Ring',9,200.00,'Activo','Elden Ring es un anticipado videojuego de acción y rol desarrollado por FromSoftware en colaboración con el escritor George R.R. Martin. Ambientado en un vasto mundo de fantasía, ofrece una experiencia desafiante y envolvente. Con su atmósfera oscura, combate estratégico y exploración de un mundo abierto, Elden Ring promete cautivar a los jugadores con su intrincada narrativa y diseño de juego característico de FromSoftware.',12,'AVN'),(5,'The Mortuary Assistant',4,100.00,'Activo','The Mortuary Assistant es un juego de aventuras y misterio en el que los jugadores asumen el papel de un asistente en un sombrío y misterioso depósito de cadáveres. Deberán resolver enigmas y descubrir secretos ocultos mientras desentrañan una trama intrigante. Con una atmósfera inquietante y una narrativa cautivadora, The Mortuary Assistant ofrece una experiencia única y envolvente en el oscuro mundo de los mortuorios.',13,'TER'),(6,'Mirror Forge',11,70.00,'Activo','Mirror Forge es un emocionante juego de construcción y exploración donde los jugadores se sumergen en un mundo mágico lleno de espejos encantados. Los jugadores deben utilizar su ingenio y habilidades para crear y modificar espejos mágicos con diferentes propiedades y poderes. A medida que exploran el mundo, descubrirán desafiantes rompecabezas y secretos ocultos. Con un estilo artístico impresionante y una jugabilidad única, Mirror Forge ofrece una experiencia fascinante y creativa.',14,'TER'),(7,'FIFA 23',10,120.00,'Activo','FIFA 23 es el último título de la reconocida serie de videojuegos de fútbol. Con mejoras visuales y una jugabilidad refinada, ofrece una experiencia aún más realista. Los jugadores pueden disfrutar de modos como Carrera, Ultimate Team y partidos en línea, compitiendo con equipos y jugadores reales de todo el mundo. FIFA 23 promete cautivar a los fanáticos del fútbol con su emocionante y auténtica representación del deporte.',15,'DEP'),(8,'Mario Kart 8',3,120.00,'Activo','Mario Kart 8 es un divertido juego de carreras desarrollado por Nintendo. Con una amplia selección de personajes icónicos de la franquicia de Mario, los jugadores compiten en emocionantes pistas llenas de obstáculos y trampas. El juego ofrece modos para un jugador y multijugador, permitiendo competir con amigos localmente u online. Con gráficos vibrantes, controles intuitivos y emocionantes power-ups, Mario Kart 8 ofrece una experiencia de carreras llena de diversión y emoción para jugadores de todas las edades.',16,'DEP'),(9,'Minecraft',5,90.00,'Oferta','Minecraft es un popular videojuego de construcción y aventura en un mundo abierto. Los jugadores pueden explorar, recolectar recursos, construir estructuras y enfrentarse a diversos desafíos. Con su estética pixelada y creatividad ilimitada, Minecraft ofrece una experiencia única y personalizable donde la imaginación es el límite.',17,'SIM'),(10,'Cities Skylines',3,80.00,'Eliminado','Cities Skylines es un juego de simulación de construcción de ciudades desarrollado por Colossal Order. Los jugadores asumen el rol de alcalde y tienen el desafío de construir y gestionar una ciudad próspera. Desde planificar la infraestructura hasta administrar el tráfico y satisfacer las necesidades de los ciudadanos, los jugadores tienen el control total sobre el crecimiento y el desarrollo de su ciudad. Con una gran cantidad de opciones y detalles realistas, Cities Skylines ofrece una experiencia profunda y envolvente para los amantes de los juegos de simulación urbana.',18,'GES'),(11,'Monopoly',4,50.00,'Oferta','Monopoly el videojuego digital es una adaptación del popular juego de mesa. Compra propiedades, construye y cobra alquileres en un entorno virtual. Disfruta de la competencia y la diversión en línea o en solitario. ¡Conviértete en el magnate inmobiliario más exitoso en Monopoly!',19,'CLA'),(12,'Uncharted 3',3,120.00,'Activo','Uncharted 3 es un emocionante videojuego de acción y aventura desarrollado por Naughty Dog. Los jugadores asumen el papel del intrépido cazador de tesoros Nathan Drake, quien se embarca en una búsqueda épica llena de peligros y misterios. Con gráficos impresionantes, una narrativa envolvente y emocionantes secuencias de acción, Uncharted 3 ofrece una experiencia inmersiva y llena de adrenalina. Acompaña a Nathan en su viaje por escenarios exóticos y descubre los secretos que se esconden detrás de antiguos tesoros perdidos.',20,'ACC');
/*!40000 ALTER TABLE `juegos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegos_por_consolas`
--

DROP TABLE IF EXISTS `juegos_por_consolas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegos_por_consolas` (
  `id_juego` int NOT NULL,
  `id_consola` varchar(45) NOT NULL,
  `stock_consola` int NOT NULL,
  PRIMARY KEY (`id_juego`,`id_consola`),
  KEY `fk_Juegos_has_Consolas_Consolas1_idx` (`id_consola`),
  KEY `fk_Juegos_has_Consolas_Juegos1_idx` (`id_juego`),
  CONSTRAINT `fk_Juegos_has_Consolas_Consolas1` FOREIGN KEY (`id_consola`) REFERENCES `consolas` (`idConsolas`),
  CONSTRAINT `fk_Juegos_has_Consolas_Juegos1` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`idJuegos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos_por_consolas`
--

LOCK TABLES `juegos_por_consolas` WRITE;
/*!40000 ALTER TABLE `juegos_por_consolas` DISABLE KEYS */;
INSERT INTO `juegos_por_consolas` VALUES (1,'SWI',2),(1,'WIU',2),(1,'XB3',2),(2,'PS4',1),(2,'PS5',3),(3,'PS4',4),(3,'PS5',3),(3,'XBO',1),(4,'PS5',7),(4,'SWI',2),(5,'XB3',1),(5,'XBO',3),(6,'PS4',3),(6,'SWI',6),(6,'WIU',2),(7,'PS4',4),(7,'PS5',4),(7,'SWI',2),(8,'SWI',2),(8,'WIU',1),(9,'SWI',1),(9,'XB3',4),(10,'XBO',3),(11,'SWI',4),(12,'PS4',5);
/*!40000 ALTER TABLE `juegos_por_consolas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegoscompradosreservados`
--

DROP TABLE IF EXISTS `juegoscompradosreservados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegoscompradosreservados` (
  `idJuegosCompradosReservados` int NOT NULL AUTO_INCREMENT,
  `fechaCompraJuego` date NOT NULL,
  `estadoCompraJuego` varchar(45) NOT NULL,
  `rating` int DEFAULT NULL,
  `id_usuario` int NOT NULL,
  `id_administrador` int NOT NULL,
  `id_juego` int NOT NULL,
  `id_consola` varchar(45) NOT NULL,
  `precio_compra` decimal(6,2) NOT NULL,
  PRIMARY KEY (`idJuegosCompradosReservados`),
  KEY `fk_JuegosCompradosReservados_Persona1_idx` (`id_usuario`),
  KEY `fk_JuegosCompradosReservados_Persona2_idx` (`id_administrador`),
  KEY `fk_JuegosCompradosReservados_Juegos_has_Consolas1_idx` (`id_juego`,`id_consola`),
  CONSTRAINT `fk_JuegosCompradosReservados_Juegos_has_Consolas1` FOREIGN KEY (`id_juego`, `id_consola`) REFERENCES `juegos_por_consolas` (`id_juego`, `id_consola`),
  CONSTRAINT `fk_JuegosCompradosReservados_Persona1` FOREIGN KEY (`id_usuario`) REFERENCES `personas` (`idPersona`),
  CONSTRAINT `fk_JuegosCompradosReservados_Persona2` FOREIGN KEY (`id_administrador`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegoscompradosreservados`
--

LOCK TABLES `juegoscompradosreservados` WRITE;
/*!40000 ALTER TABLE `juegoscompradosreservados` DISABLE KEYS */;
INSERT INTO `juegoscompradosreservados` VALUES (1,'2023-06-14','Reservado',NULL,7,1,1,'WIU',120.00),(2,'2023-06-25','Reservado',NULL,2,3,4,'PS5',200.00),(3,'2023-04-13','Comprado',NULL,8,3,2,'PS4',130.00),(4,'2023-05-14','Comprado',NULL,9,1,2,'PS5',130.00),(5,'2023-06-25','Reservado',NULL,4,5,4,'SWI',200.00),(6,'2023-06-13','Reservado',NULL,7,3,3,'XBO',170.00),(7,'2023-05-12','Comprado',NULL,8,1,5,'XB3',100.00),(8,'2023-05-25','Comprado',NULL,6,1,4,'SWI',200.00),(9,'2023-06-01','Comprado',NULL,4,5,6,'PS4',70.00),(10,'2023-05-13','Comprado',NULL,7,5,7,'PS5',120.00),(11,'2023-06-12','Reservado',NULL,2,1,7,'SWI',120.00),(12,'2023-06-09','Comprado',NULL,7,3,4,'SWI',200.00),(13,'2023-04-06','Comprado',NULL,6,1,8,'WIU',120.00),(14,'2023-05-19','Comprado',NULL,2,3,10,'XBO',80.00),(15,'2023-06-11','Reservado',NULL,9,1,9,'XB3',70.00),(16,'2023-06-03','Comprado',NULL,8,5,12,'PS4',120.00),(17,'2023-05-12','Comprado',NULL,4,1,12,'PS4',120.00),(18,'2023-06-06','Comprado',NULL,7,5,12,'PS4',120.00),(19,'2023-05-15','Comprado',NULL,4,1,11,'SWI',40.00),(20,'2023-04-13','Comprado',NULL,9,5,1,'XB3',120.00),(21,'2023-06-03','Comprado',NULL,7,3,3,'XBO',170.00),(22,'2023-06-15','Reservado',NULL,6,3,7,'SWI',120.00);
/*!40000 ALTER TABLE `juegoscompradosreservados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivosmanager`
--

DROP TABLE IF EXISTS `objetivosmanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivosmanager` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `ventasPorMesJuego` decimal(6,2) DEFAULT NULL,
  `gastosPorMesJuego` decimal(6,2) DEFAULT NULL,
  `usariosPorMes` int DEFAULT NULL,
  `id_manager` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  KEY `fk_ObjetivosManager_Persona1_idx` (`id_manager`),
  CONSTRAINT `fk_ObjetivosManager_Persona1` FOREIGN KEY (`id_manager`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivosmanager`
--

LOCK TABLES `objetivosmanager` WRITE;
/*!40000 ALTER TABLE `objetivosmanager` DISABLE KEYS */;
INSERT INTO `objetivosmanager` VALUES (1,300.00,150.00,16,10,'2023-07-01');
/*!40000 ALTER TABLE `objetivosmanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) NOT NULL,
  `contrasenia` varchar(250) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `fechaDeNacimiento` date NOT NULL,
  `dni` int NOT NULL,
  `genero` varchar(20) NOT NULL,
  `categoriaJuegoPreferida` varchar(45) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `id_roles` varchar(45) NOT NULL,
  `id_perfil` int NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `fk_Persona_roles1_idx` (`id_roles`),
  KEY `fk_Persona_Imagenes1_idx` (`id_perfil`),
  CONSTRAINT `fk_Persona_Imagenes1` FOREIGN KEY (`id_perfil`) REFERENCES `imagenes` (`idImagenes`),
  CONSTRAINT `fk_Persona_roles1` FOREIGN KEY (`id_roles`) REFERENCES `roles` (`idroles`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'joselin@pucp.edu.pe','12e22f23962137d28a3ad5a3e3a75e9fa423390bba6f82099fdbe0417dfe5da6','Joselin','Raymundo','2001-10-15',75634321,'Femenino','Terror','2023-06-05','Activo','ADM',2),(2,'julio@pucp.edu.pe','0eb1faeedd06bfa0707be855a9ee83e25cf012cf66e5b611c9030fc059970061','Julio','Aliaga','2002-06-03',77832984,'Masculino','Acción','2023-06-05','Activo','USR',3),(3,'david@pucp.edu.pe','820dfa5686867361023816c5a0deecb8f5975de321c3066a10ff4a356ca96059','David','Llanos','2002-05-23',45632778,'Masculino','Deportivo','2023-06-05','Activo','ADM',4),(4,'aracely@pucp.edu.pe','02e5f0b3062f5b22883ab0b9e6b85055f1188006f1676f347ab087d05d3faccf','Aracely','La Rosa','2002-04-21',74533212,'Femenino','Aventura','2023-06-05','Activo','USR',8),(5,'luis@pucp.edu.pe','417dd8fc436c8d46da4d1f8a0b1cfb4dc157903f1be994b44af3b98750aebef9','Luis','Quispe','2002-11-10',45689332,'Masculino','Aventura','2023-06-05','Activo','ADM',6),(6,'pablo@pucp.edu.pe','8e4fd3bce54a1a817c2c4b3aa8c8dfe50a4dcbb711b24be6f82a922ca1f28033','Pablo','Flores','2002-11-17',75688415,'Masculino','Aventura','2023-06-05','Activo','USR',7),(7,'cesar@pucp.edu.pe','058595a16998b053e93a62228f9339d05ac490a29466102c6fd9077ab531a9d5','Cesar','Santivañez','1970-10-13',32133456,'Masculino','Acción','2023-06-05','Activo','USR',1),(8,'manuel@pucp.edu.pe','d7d0e8b1bc42ee54d3dad4133ad4dd8cdddc9ce678b566df21caf1de98e78158','Manuel','Yarlequé','1970-04-02',10956323,'Masculino','Terror','2023-06-05','Activo','USR',2),(9,'gumer@pucp.edu.pe','659a56123de3e9b3335ecbc6616e33332e254e27ae11295f65c8cba269d54cdb','Gumer','Bartra','1963-03-30',75632989,'Masculino','Clasico','2023-06-05','Activo','USR',5),(10,'yoshtim@pucp.edu.pe','45ed822f6ce9ebbed92987c98333b739cb850c20f47ac67f6478e4cc8b530270','Yoshtim','Enciso','2002-12-15',56478313,'Masculino','Deportivo','2023-06-05','Activo','MNG',3),(11,'cafg1570@gmail.com','954909e120074efe2ea487247b5695b8e6822eb17582128798c4c73af418ccd0','Carlos','Flores','1970-10-15',10212103,'masculino','accion','2023-06-21','Activo','USR',1),(12,'rrevi_7@hotmail.com','fb7a5be2db202e70f581c5bf03bb0ec10e8c5c82b45a437b78233508e434ffad','Rocío ','Revilla','1967-04-02',9327162,'Femenino','Musical','2023-06-21','Activo','USR',3);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idroles` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  PRIMARY KEY (`idroles`),
  UNIQUE KEY `rol_UNIQUE` (`rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADM','Administrador'),('MNG','Manager'),('USR','Usuario');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventajuegosgeneral`
--

DROP TABLE IF EXISTS `ventajuegosgeneral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventajuegosgeneral` (
  `idVenta` int NOT NULL AUTO_INCREMENT,
  `estadoVenta` varchar(45) NOT NULL,
  `razonRechazo` text,
  `fechaPublicacion` date NOT NULL,
  `precio_usuario` decimal(6,2) NOT NULL,
  `disponibilidad` varchar(45) NOT NULL,
  `precio_admin` decimal(10,0) DEFAULT NULL,
  `id_usuario` int NOT NULL,
  `id_administrador` int NOT NULL,
  `id_juego` int DEFAULT NULL,
  `id_consola` varchar(45) NOT NULL,
  `descripcion_nueva` text,
  `nombre_nuevo` varchar(45) DEFAULT NULL,
  `imagen` blob,
  `cantidad` int NOT NULL,
  `idCategoria` VARCHAR(45) NULL,
  PRIMARY KEY (`idVenta`),
  KEY `fk_VentaJuegosGeneral_Persona1_idx` (`id_usuario`),
  KEY `fk_VentaJuegosGeneral_Persona2_idx` (`id_administrador`),
  KEY `fk_VentaJuegosGeneral_Juegos_has_Consolas1_idx` (`id_juego`,`id_consola`),
  CONSTRAINT `fk_VentaJuegosGeneral_Juegos_has_Consolas1` FOREIGN KEY (`id_juego`, `id_consola`) REFERENCES `juegos_por_consolas` (`id_juego`, `id_consola`),
  CONSTRAINT `fk_VentaJuegosGeneral_Persona1` FOREIGN KEY (`id_usuario`) REFERENCES `personas` (`idPersona`),
  CONSTRAINT `fk_VentaJuegosGeneral_Persona2` FOREIGN KEY (`id_administrador`) REFERENCES `personas` (`idPersona`),
  CONSTRAINT `fk_ventajuegosgeneral_categorias1` FOREIGN KEY (`idCategoria`) REFERENCES `japyld`.`categorias` (`idCategorias`)
    
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventajuegosgeneral`
--

LOCK TABLES `ventajuegosgeneral` WRITE;
/*!40000 ALTER TABLE `ventajuegosgeneral` DISABLE KEYS */;
INSERT INTO `ventajuegosgeneral` VALUES (1,'Aceptado',NULL,'2023-06-01',150.00,'Habilitado',150,2,1,4,'PS5',NULL,NULL,NULL,1,NULL),(2,'No Aceptado',NULL,'2023-05-29',130.00,'Habilitado',110,2,3,2,'PS4',NULL,NULL,NULL,1,NULL),(3,'Rechazado','Dudosa procedencia.','2023-06-11',100.00,'Deshabilitado',NULL,2,5,6,'WIU',NULL,NULL,NULL,1,NULL),(4,'Retirado',NULL,'2023-05-28',105.00,'Habilitado',NULL,2,1,2,'PS5',NULL,NULL,NULL,1,NULL),(5,'Aceptado',NULL,'2023-06-09',100.00,'Habilitado',NULL,4,1,1,'XB3',NULL,NULL,NULL,1,NULL),(6,'No Aceptado',NULL,'2023-06-07',120.00,'Habilitado',90,4,3,9,'XB3',NULL,NULL,NULL,1,NULL),(7,'Aceptado',NULL,'2023-06-08',30.00,'Habilitado',NULL,6,3,11,'SWI',NULL,NULL,NULL,1,NULL),(8,'Aceptado',NULL,'2023-06-12',90.00,'Habilitado',NULL,6,5,12,'PS4',NULL,NULL,NULL,1,NULL),(9,'Aceptado',NULL,'2023-05-25',80.00,'Habilitado',NULL,7,5,10,'XBO',NULL,NULL,NULL,1,NULL),(10,'Retirado',NULL,'2023-06-05',35.00,'Habilitado',NULL,8,3,11,'SWI',NULL,NULL,NULL,1,NULL),(11,'Retirado',NULL,'2023-05-21',80.00,'Habilitado',NULL,8,1,12,'PS4',NULL,NULL,NULL,1,NULL),(12,'Aceptado',NULL,'2023-06-04',150.00,'Habilitado',NULL,9,5,3,'XBO',NULL,NULL,NULL,1,NULL),(13,'Aceptado',NULL,'2023-05-20',200.00,'Deshabilitado',NULL,7,3,2,'PS5',NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `ventajuegosgeneral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'japyld'
--

--
-- Dumping routines for database 'japyld'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
