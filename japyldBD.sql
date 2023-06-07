-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Japyld
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Japyld
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Japyld` DEFAULT CHARACTER SET utf8 ;
USE `Japyld` ;

-- -----------------------------------------------------
-- Table `Japyld`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`roles` (
  `idroles` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idroles`),
  UNIQUE INDEX `rol_UNIQUE` (`rol` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Imagenes` (
  `idImagenes` INT NOT NULL AUTO_INCREMENT,
  `imagen` MEDIUMBLOB NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `direccion_archivo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idImagenes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Personas` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(100) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fechaDeNacimiento` DATE NOT NULL,
  `dni` INT(8) NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `categoriaJuegoPreferida` VARCHAR(45) NULL,
  `fechaRegistro` DATE NULL,
  `estado` VARCHAR(45) NULL,
  `id_roles` VARCHAR(45) NOT NULL,
  `id_perfil` INT NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `fk_Persona_roles1_idx` (`id_roles` ASC) VISIBLE,
  INDEX `fk_Persona_Imagenes1_idx` (`id_perfil` ASC) VISIBLE,
  CONSTRAINT `fk_Persona_roles1`
    FOREIGN KEY (`id_roles`)
    REFERENCES `Japyld`.`roles` (`idroles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Persona_Imagenes1`
    FOREIGN KEY (`id_perfil`)
    REFERENCES `Japyld`.`Imagenes` (`idImagenes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Categorias` (
  `idCategorias` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategorias`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Juegos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Juegos` (
  `idJuegos` INT NOT NULL AUTO_INCREMENT,
  `nombreJuegos` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  `precio` DECIMAL(6,2) NOT NULL,
  `estadoJuego` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `id_imagen` INT NOT NULL,
  `id_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idJuegos`),
  INDEX `fk_Juegos_Imagenes1_idx` (`id_imagen` ASC) VISIBLE,
  INDEX `fk_Juegos_Categorias1_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_Juegos_Imagenes1`
    FOREIGN KEY (`id_imagen`)
    REFERENCES `Japyld`.`Imagenes` (`idImagenes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Juegos_Categorias1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `Japyld`.`Categorias` (`idCategorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Consolas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Consolas` (
  `idConsolas` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idConsolas`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `idConsolas_UNIQUE` (`idConsolas` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Juegos_por_Consolas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Juegos_por_Consolas` (
  `id_juego` INT NOT NULL,
  `id_consola` VARCHAR(45) NOT NULL,
  `stock_consola` INT NOT NULL,
  PRIMARY KEY (`id_juego`, `id_consola`),
  INDEX `fk_Juegos_has_Consolas_Consolas1_idx` (`id_consola` ASC) VISIBLE,
  INDEX `fk_Juegos_has_Consolas_Juegos1_idx` (`id_juego` ASC) VISIBLE,
  CONSTRAINT `fk_Juegos_has_Consolas_Juegos1`
    FOREIGN KEY (`id_juego`)
    REFERENCES `Japyld`.`Juegos` (`idJuegos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Juegos_has_Consolas_Consolas1`
    FOREIGN KEY (`id_consola`)
    REFERENCES `Japyld`.`Consolas` (`idConsolas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`JuegosCompradosReservados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`JuegosCompradosReservados` (
  `idJuegosCompradosReservados` INT NOT NULL AUTO_INCREMENT,
  `fechaCompraJuego` DATE NOT NULL,
  `estadoCompraJuego` VARCHAR(45) NOT NULL,
  `rating` INT NULL,
  `id_usuario` INT NOT NULL,
  `id_administrador` INT NOT NULL,
  `id_juego` INT NOT NULL,
  `id_consola` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idJuegosCompradosReservados`),
  INDEX `fk_JuegosCompradosReservados_Persona1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_JuegosCompradosReservados_Persona2_idx` (`id_administrador` ASC) VISIBLE,
  INDEX `fk_JuegosCompradosReservados_Juegos_has_Consolas1_idx` (`id_juego` ASC, `id_consola` ASC) VISIBLE,
  CONSTRAINT `fk_JuegosCompradosReservados_Persona1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_JuegosCompradosReservados_Persona2`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_JuegosCompradosReservados_Juegos_has_Consolas1`
    FOREIGN KEY (`id_juego` , `id_consola`)
    REFERENCES `Japyld`.`Juegos_por_Consolas` (`id_juego` , `id_consola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`VentaJuegosGeneral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`VentaJuegosGeneral` (
  `idVenta` INT NOT NULL AUTO_INCREMENT,
  `estadoVenta` VARCHAR(45) NOT NULL,
  `razonRechazo` TEXT NULL,
  `fechaPublicacion` DATE NOT NULL,
  `precio_usuario` DECIMAL(6,2) NOT NULL,
  `disponibilidad` VARCHAR(45) NOT NULL,
  `precio_admin` DECIMAL NULL,
  `id_usuario` INT NOT NULL,
  `id_administrador` INT NOT NULL,
  `id_juego` INT NULL,
  `id_consola` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVenta`),
  INDEX `fk_VentaJuegosGeneral_Persona1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_VentaJuegosGeneral_Persona2_idx` (`id_administrador` ASC) VISIBLE,
  INDEX `fk_VentaJuegosGeneral_Juegos_has_Consolas1_idx` (`id_juego` ASC, `id_consola` ASC) VISIBLE,
  CONSTRAINT `fk_VentaJuegosGeneral_Persona1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VentaJuegosGeneral_Persona2`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VentaJuegosGeneral_Juegos_has_Consolas1`
    FOREIGN KEY (`id_juego` , `id_consola`)
    REFERENCES `Japyld`.`Juegos_por_Consolas` (`id_juego` , `id_consola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`ObjetivosManager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`ObjetivosManager` (
  `id_objetivo` INT NOT NULL AUTO_INCREMENT,
  `ventasPorMesJuego` DECIMAL(6,2) NULL,
  `gastosPorMesJuego` DECIMAL(6,2) NULL,
  `usariosPorMes` INT NULL,
  `id_manager` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  INDEX `fk_ObjetivosManager_Persona1_idx` (`id_manager` ASC) VISIBLE,
  CONSTRAINT `fk_ObjetivosManager_Persona1`
    FOREIGN KEY (`id_manager`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Comentarios` (
  `idComentarios` INT NOT NULL AUTO_INCREMENT,
  `comentario` TEXT NOT NULL,
  `ratingComentario` INT NULL,
  `Juegos_idJuegos` INT NOT NULL,
  `Persona_idPersona` INT NOT NULL,
  PRIMARY KEY (`idComentarios`),
  INDEX `fk_Comentarios_Juegos1_idx` (`Juegos_idJuegos` ASC) VISIBLE,
  INDEX `fk_Comentarios_Persona1_idx` (`Persona_idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Comentarios_Juegos1`
    FOREIGN KEY (`Juegos_idJuegos`)
    REFERENCES `Japyld`.`Juegos` (`idJuegos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentarios_Persona1`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Japyld`.`Descuentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Japyld`.`Descuentos` (
  `idDescuentos` INT NOT NULL,
  `precio_nuevo` DECIMAL NOT NULL,
  `fecha_publicacion` DATE NOT NULL,
  `duracion` INT NOT NULL,
  `id_juego` INT NOT NULL,
  `id_administrador` INT NOT NULL,
  PRIMARY KEY (`idDescuentos`),
  INDEX `fk_Descuentos_Juegos1_idx` (`id_juego` ASC) VISIBLE,
  INDEX `fk_Descuentos_Persona1_idx` (`id_administrador` ASC) VISIBLE,
  CONSTRAINT `fk_Descuentos_Juegos1`
    FOREIGN KEY (`id_juego`)
    REFERENCES `Japyld`.`Juegos` (`idJuegos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Descuentos_Persona1`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `Japyld`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
