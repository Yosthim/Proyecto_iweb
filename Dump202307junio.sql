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
INSERT INTO `comentarios` VALUES (1,'Es un juego divertido y entretenido, aunque algunos aspectos podrían haber sido pulidos para alcanzar su máximo potencial.',4,12,2),(2,'El juego ofrece una experiencia sólida y satisfactoria, aunque se echa en falta un poco más de innovación en ciertas áreas.',5,12,4),(3,'Aunque es un juego sólido y bien realizado, algunos pequeños problemas impiden que alcance la excelencia absoluta.',4,12,6),(4,'Aunque tiene elementos decentes, el juego carece de la profundidad y la calidad necesarias para destacar en su género.',3,6,8),(5,'A pesar de tener algunas ideas prometedoras, el juego se queda corto en su ejecución y no logra alcanzar su máximo potencial.',3,6,9),(6,'El juego tiene sus momentos interesantes, pero se ve la falta de pulido y algunos elementos que no terminan de encajar.',2,6,7),(7,'No puedo dejar de elogiar este juego. Su mundo abierto expansivo, las misiones emocionantes y la atención al detalle en cada rincón hacen que sea una experiencia perfecta. Es un título que todo jugador debería experimentar al menos una vez en su vida.',5,4,7),(8,'Este juego es una joya absoluta, con una historia cautivadora, personajes memorables y una jugabilidad impecable. Es una experiencia que te absorbe por completo y te deja con ganas de más.',5,4,4),(9,'Cada aspecto de este juego está meticulosamente diseñado, desde los gráficos impresionantes hasta la música envolvente y los controles precisos. Es una obra maestra que demuestra la dedicación y el talento del equipo de desarrollo.',5,4,6);
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
INSERT INTO `descuentos` VALUES (1,120,'2023-06-04',2,3,1),(2,170,'2023-06-05',4,4,3),(3,90,'2023-06-10',7,5,5),(4,110,'2023-06-07',7,8,1);
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
INSERT INTO `imagenes` VALUES (1,NULL,'predeterminado','recursos/img/avatar1.png'),(2,NULL,'predeterminado','recursos/img/avatar2.png'),(3,NULL,'predeterminado','recursos/img/avatar3.png'),(4,NULL,'predeterminado','recursos/img/avatar4.png'),(5,NULL,'predeterminado','recursos/img/avatar5.png'),(6,NULL,'predeterminado','recursos/img/avatar6.png'),(7,NULL,'predeterminado','recursos/img/avatar7.png'),(8,NULL,'predeterminado','recursos/img/avatar8.png'),(9,NULL,'juego','recursos/img/Legend_of_Zelda.jpg'),(10,NULL,'juego','recursos/img/ResidentEvil4.jpeg'),(11,NULL,'juego','recursos/img/HogwartsLegacy.jpeg'),(12,NULL,'juego','recursos/img/EldenRing.jpg'),(13,NULL,'juego','recursos/img/TheMortuaryAssistant.jpeg'),(14,NULL,'juego','recursos/img/MirrorForge.jpg'),(15,NULL,'juego','recursos/img/FIFA23.jpg'),(16,NULL,'juego','recursos/img/MarioKart8.jpg'),(17,NULL,'juego','recursos/img/Minecraft.jpeg'),(18,NULL,'juego','recursos/img/CitiesSkylines.jpg'),(19,NULL,'juego','recursos/img/Monopoly.jpeg'),(20,NULL,'juego','recursos/img/Uncharted3.jpg'),(21,NULL,'perfil','recursos/img/perfil1.jpg'),(22,NULL,'perfil','recursos/img/perfil2.jpg'),(23,NULL,'perfil','recursos/img/perfil3.jpg'),(24,NULL,'perfil','recursos/img/perfil4.jpg'),(25,NULL,'perfil','recursos/img/perfil5.jpg'),(26,NULL,'perfil','recursos/img/perfil6.jpg'),(27,NULL,'perfil','recursos/img/perfil7.jpg'),(28,NULL,'perfil','recursos/img/perfil8.jpg'),(29,NULL,'perfil','recursos/img/perfil9.jpg'),(30,NULL,'perfil','recursos/img/perfil10.jpg');
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
INSERT INTO `juegos` VALUES (1,'Legend of Zelda',6,120.00,'Activo','Legend of Zelda es una aclamada saga de videojuegos que combina elementos de acción, aventura y rompecabezas en un mundo abierto y fantástico. Los jugadores se sumergen en la piel de Link, el valiente héroe destinado a enfrentarse a terribles enemigos y superar desafiantes obstáculos para rescatar a la princesa Zelda y restaurar la paz en el reino de Hyrule. Con una historia rica en mitología, paisajes impresionantes, personajes memorables y mecánicas de juego innovadoras, Legend of Zelda ofrece una experiencia inmersiva y emocionante que ha dejado una huella perdurable en la industria de los videojuegos.',9,'AVN'),(2,'Resident Evil 4',4,130.00,'Activo','Resident Evil 4 es un juego de terror y supervivencia que te sumerge en una escalofriante historia llena de suspense y peligros. Encarnando a Leon S. Kennedy, deberás enfrentarte a hordas de horripilantes criaturas y resolver acertijos para rescatar a la hija del presidente. Con una atmósfera tensa, controles precisos y una jugabilidad intensa, Resident Evil 4 es una experiencia inolvidable para los amantes de los juegos de terror.',10,'ACC'),(3,'Hogwarts Legacy',8,170.00,'Activo','Hogwarts Legacy es un emocionante juego de rol de mundo abierto ambientado en el mágico universo de Harry Potter. Los jugadores se sumergirán en la vida de un estudiante de Hogwarts en el siglo XIX, explorando los icónicos lugares del castillo y sus alrededores, aprendiendo poderosos hechizos, participando en emocionantes duelos y descubriendo secretos antiguos. Con una narrativa rica, una atención meticulosa al detalle y la posibilidad de moldear tu propio destino mágico, Hogwarts Legacy promete una experiencia mágica y envolvente para los fans de la saga y los amantes de los juegos de rol.',11,'AVN'),(4,'Elden Ring',9,200.00,'Activo','Elden Ring es un esperado juego de rol de acción desarrollado en colaboración entre FromSoftware y George R.R. Martin. Sumergiéndote en un vasto mundo de fantasía, te enfrentarás a desafiantes combates contra criaturas imponentes y jefes épicos. Explorarás paisajes hermosos y peligrosos mientras descubres una trama envolvente y llena de misterios. Con un estilo artístico impresionante, una jugabilidad desafiante y una narrativa intrigante, Elden Ring promete ser una experiencia épica para los fanáticos de los juegos de rol y la fantasía.',12,'AVN'),(5,'The Mortuary Assistant',4,100.00,'Activo','The Mortuary Assistant es un juego de terror y misterio en el que te conviertes en el asistente de una morgue. Explorarás sombríos escenarios, resolverás enigmas y descubrirás oscuros secretos mientras investigas extraños sucesos en torno a los cuerpos que llegan a la morgue. Con una atmósfera inquietante, una narrativa cautivadora y una jugabilidad basada en la toma de decisiones, The Mortuary Assistant te sumergirá en una experiencia intrigante y llena de suspense.',13,'TER'),(6,'Mirror Forge',11,70.00,'Activo','Mirror Forge es un juego de terror y aventuras que te sumerge en un mundo mágico y enigmático. Como valiente guerrero, deberás explorar peligrosas mazmorras, resolver desafiantes rompecabezas y enfrentarte a feroces enemigos utilizando un arma mística capaz de reflejar y manipular la luz. Con un estilo visual impresionante, una mecánica de juego única y una narrativa fascinante, Mirror Forge te transportará a una emocionante odisea llena de sorpresas y descubrimientos.',14,'TER'),(7,'FIFA 23',10,120.00,'Activo','FIFA 23 es el último título de la aclamada franquicia de videojuegos de fútbol. Con gráficos realistas y una jugabilidad mejorada, los jugadores podrán experimentar la emoción de competir en los principales torneos y ligas de fútbol del mundo. Desde partidos rápidos hasta modos de juego de carrera y multijugador en línea, FIFA 23 ofrece una experiencia auténtica y envolvente para los amantes del fútbol, con equipos actualizados, estadios icónicos y una amplia gama de opciones tácticas. ¡Prepárate para vivir la pasión del fútbol como nunca antes!',15,'DEP'),(8,'Mario Kart 8',3,120.00,'Activo','Mario Kart 8 es un emocionante juego de carreras lleno de acción y diversión para todas las edades. Los jugadores pueden elegir entre una amplia variedad de personajes icónicos de Nintendo y competir en coloridos circuitos, utilizando poderosos objetos y habilidades para ganar la carrera. Con gráficos vibrantes, controles intuitivos y modos de juego multijugador, Mario Kart 8 ofrece una experiencia de carreras emocionante y adictiva.',16,'DEP'),(9,'Minecraft',5,110.00,'Activo','Minecraft es un juego de construcción y exploración que te sumerge en un vasto mundo abierto lleno de infinitas posibilidades. Los jugadores pueden recolectar recursos, construir estructuras, explorar cuevas y enfrentarse a peligrosos enemigos en un entorno generado proceduralmente. Con su estilo visual pixelado distintivo, la libertad creativa y la capacidad de jugar en modo individual o multijugador, Minecraft ofrece una experiencia única y adictiva donde la imaginación es el único límite. ¡Prepárate para construir, sobrevivir y explorar en un mundo lleno de aventuras sin fin!',17,'SIM'),(10,'Cities Skylines',7,90.00,'Activo','Cities Skylines es un simulador de construcción de ciudades que te permite ser el alcalde y diseñar tu propio metrópolis desde cero. Con una amplia gama de herramientas y opciones de personalización, podrás construir infraestructuras, gestionar servicios públicos y satisfacer las necesidades de tus ciudadanos. Con gráficos detallados y un enfoque realista, Cities Skylines te desafía a crear una ciudad próspera y vibrante mientras enfrentas desafíos urbanos y tomas decisiones estratégicas. ¡Conviértete en el arquitecto de tu propio sueño urbano!',18,'GES'),(11,'Monopoly',5,50.00,'Activo','Monopoly el videojuego te sumerge en un entorno virtual donde puedes disfrutar de la emoción de hacer transacciones inmobiliarias estratégicas, construir un imperio y desafiar a amigos o jugadores de todo el mundo. Con gráficos modernos, opciones de personalización y modos de juego variados, es una experiencia digital que cautiva a los fanáticos del clásico juego de mesa.',19,'CLA'),(12,'Uncharted 3',9,110.00,'Activo','Uncharted 3 es un apasionante juego de acción y aventuras que sigue las hazañas del intrépido cazador de tesoros Nathan Drake. Con impresionantes gráficos y una narrativa envolvente, los jugadores se embarcarán en una emocionante búsqueda global llena de peligros, tesoros ocultos y traiciones. Con combates intensos, acrobacias emocionantes y puzzles desafiantes, Uncharted 3 ofrece una experiencia cinematográfica que te dejará sin aliento y te mantendrá pegado a tu control hasta el final.',20,'ACC');
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
INSERT INTO `juegos_por_consolas` VALUES (1,'SWI',1),(1,'WIU',2),(1,'XB3',3),(2,'PS4',2),(2,'PS5',2),(3,'PS4',3),(3,'PS5',2),(3,'XBO',3),(4,'PS5',2),(4,'SWI',3),(4,'XBO',4),(5,'XB3',1),(5,'XBO',3),(6,'PS4',5),(6,'SWI',3),(6,'WIU',2),(6,'XB3',1),(7,'PS4',1),(7,'PS5',4),(7,'SWI',3),(7,'XBO',2),(8,'SWI',2),(8,'WIU',1),(9,'SWI',3),(9,'XB3',2),(10,'XB3',3),(10,'XBO',4),(11,'SWI',2),(11,'WIU',3),(12,'PS4',3),(12,'PS5',4),(12,'XBO',2);
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
INSERT INTO `juegoscompradosreservados` VALUES (1,'2023-05-01','Comprado',NULL,2,1,12,'PS4'),(2,'2023-06-03','Reservado',NULL,4,1,12,'PS5'),(3,'2023-05-04','Comprado',NULL,6,3,12,'XBO'),(4,'2023-06-05','Comprado',NULL,7,3,4,'PS5'),(5,'2023-04-06','Reservado',NULL,4,5,4,'SWI'),(6,'2023-05-11','Reservado',NULL,6,5,4,'XBO'),(7,'2023-05-12','Comprado',NULL,8,5,6,'SWI'),(8,'2023-06-04','Comprado',NULL,9,1,6,'WIU'),(9,'2023-07-01','Reservado',NULL,7,3,6,'XB3'),(10,'2023-04-23','Comprado',NULL,2,5,1,'WIU'),(11,'2023-05-24','Reservado',NULL,9,1,2,'PS5'),(12,'2023-04-25','Comprado',NULL,8,3,3,'PS5'),(13,'2023-06-06','Comprado',NULL,7,5,5,'XB3'),(14,'2023-06-05','Comprado',NULL,2,3,7,'SWI'),(15,'2023-04-28','Comprado',NULL,6,3,8,'WIU'),(16,'2023-05-29','Reservado',NULL,4,5,9,'SWI'),(17,'2023-07-03','Comprado',NULL,8,1,10,'XBO'),(18,'2023-05-04','Comprado',NULL,9,1,11,'WIU'),(19,'2023-04-25','Comprado',NULL,2,3,1,'XB3'),(20,'2023-03-26','Comprado',NULL,6,5,2,'PS4'),(21,'2023-06-03','Comprado',NULL,7,1,7,'PS5'),(22,'2023-05-17','Comprado',NULL,9,3,8,'SWI');
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
INSERT INTO `objetivosmanager` VALUES (1,200.00,150.00,15,10,'2023-01-01'),(2,225.00,160.00,20,10,'2023-02-01'),(3,250.00,170.00,25,10,'2023-03-01'),(4,275.00,180.00,30,10,'2023-04-01'),(5,300.00,185.00,35,10,'2023-05-01'),(6,325.00,190.00,40,10,'2023-06-01'),(7,350.00,195.00,45,10,'2023-07-01'),(8,375.00,200.00,50,10,'2023-08-01'),(9,400.00,210.00,55,10,'2023-09-01'),(10,425.00,225.00,60,10,'2023-10-01'),(11,450.00,250.00,65,10,'2023-11-01'),(12,475.00,260.00,70,10,'2023-12-01');
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
  `contrasenia` varchar(45) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'joselin@pucp.edu.pe','Ingweb1!','Joselin','Raymundo','2001-10-15',75634321,'Femenino','Terror','2023-06-05','activo','ADM',21),(2,'julio@pucp.edu.pe','Ingweb2!','Julio','Aliaga','2002-06-03',77832984,'Masculino','Acción','2023-06-05','activo','USR',22),(3,'david@pucp.edu.pe','Ingweb3!','David','Llanos','2002-05-23',45632778,'Masculino','Deportivo','2023-06-05','activo','ADM',23),(4,'aracely@pucp.edu.pe','Ingweb4!','Aracely','La Rosa','2002-04-21',74533212,'Femenino','Aventura','2023-06-05','activo','USR',24),(5,'luis@pucp.edu.pe','Ingweb5!','Luis','Quispe','2002-11-10',45689332,'Masculino','Aventura','2023-06-05','activo','ADM',25),(6,'pablo@pucp.edu.pe','Ingweb6!','Pablo','Flores','2002-11-17',75688415,'Masculino','Aventura','2023-06-05','activo','USR',26),(7,'cesar@pucp.edu.pe','Ingweb7!','Cesar','Santivañez','1970-10-13',32133456,'Masculino','Acción','2023-06-05','activo','USR',27),(8,'manuel@pucp.edu.pe','Ingweb8!','Manuel','Yarlequé','1970-04-02',10956323,'Masculino','Terror','2023-06-05','activo','USR',28),(9,'gumer@pucp.edu.pe','Ingweb9!','Gumer','Bartra','1963-03-30',75632989,'Masculino','Clasico','2023-06-05','activo','USR',29),(10,'yoshtim@pucp.edu.pe','Ingweb10!','Yoshtim','Enciso','2002-12-15',56478313,'Masculino','Deportivo','2023-06-05','activo','MNG',30);
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
  PRIMARY KEY (`idVenta`),
  KEY `fk_VentaJuegosGeneral_Persona1_idx` (`id_usuario`),
  KEY `fk_VentaJuegosGeneral_Persona2_idx` (`id_administrador`),
  KEY `fk_VentaJuegosGeneral_Juegos_has_Consolas1_idx` (`id_juego`,`id_consola`),
  CONSTRAINT `fk_VentaJuegosGeneral_Juegos_has_Consolas1` FOREIGN KEY (`id_juego`, `id_consola`) REFERENCES `juegos_por_consolas` (`id_juego`, `id_consola`),
  CONSTRAINT `fk_VentaJuegosGeneral_Persona1` FOREIGN KEY (`id_usuario`) REFERENCES `personas` (`idPersona`),
  CONSTRAINT `fk_VentaJuegosGeneral_Persona2` FOREIGN KEY (`id_administrador`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventajuegosgeneral`
--

LOCK TABLES `ventajuegosgeneral` WRITE;
/*!40000 ALTER TABLE `ventajuegosgeneral` DISABLE KEYS */;
INSERT INTO `ventajuegosgeneral` VALUES (1,'Aceptado',NULL,'2023-06-03',150.00,'Habilitado',150,2,1,4,'PS5'),(2,'No Aceptado',NULL,'2023-06-04',130.00,'Habilitado',110,2,3,2,'PS4'),(3,'Rechazado','Dudosa procedencia.','2023-05-12',100.00,'Deshabilitado',NULL,2,5,6,'SWI'),(4,'Retirado',NULL,'2023-06-02',105.00,'Habilitado',NULL,2,1,2,'PS5'),(5,'Aceptado',NULL,'2023-05-11',100.00,'Habilitado',NULL,4,1,1,'XB3'),(6,'No Aceptado',NULL,'2023-05-17',120.00,'Habilitado',90,4,3,9,'SWI'),(7,'Aceptado',NULL,'2023-06-02',30.00,'Habilitado',NULL,6,3,11,'WIU'),(8,'Aceptado',NULL,'2023-06-05',90.00,'Habilitado',NULL,6,5,12,'XBO'),(9,'Aceptado',NULL,'2023-05-11',80.00,'Habilitado',NULL,7,5,10,'XB3'),(10,'Aceptado',NULL,'2023-06-06',35.00,'Habilitado',NULL,8,3,11,'SWI'),(11,'Retirado',NULL,'2023-05-23',80.00,'Habilitado',NULL,8,1,12,'PS5'),(12,'Aceptado',NULL,'2023-04-27',150.00,'Habilitado',NULL,9,5,3,'PS4');
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

-- Dump completed on 2023-06-07  2:22:25
