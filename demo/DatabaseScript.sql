-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema food_panda_delivery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema food_panda_delivery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `food_panda_delivery` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `food_panda_delivery` ;

-- -----------------------------------------------------
-- Table `food_panda_delivery`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`administrator` (
  `id_customer` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_customer`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`customer` (
  `id_customer` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NULL DEFAULT NULL,
  `last_name` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_customer`),
  UNIQUE INDEX `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_irnrrncatp2fvw52vp45j7rlw` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`delivery_zone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`delivery_zone` (
  `id_zone` INT NOT NULL AUTO_INCREMENT,
  `zone_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_zone`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`food_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`food_category` (
  `id_category` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_category`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`restaurant` (
  `id_restaurant` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `administrator_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_restaurant`),
  INDEX `FKflyva1hs3v89e4i54v5au2iv5` (`administrator_id` ASC) VISIBLE,
  CONSTRAINT `FKflyva1hs3v89e4i54v5au2iv5`
    FOREIGN KEY (`administrator_id`)
    REFERENCES `food_panda_delivery`.`administrator` (`id_customer`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`menu_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`menu_item` (
  `id_item` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `item_name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `id_category` INT NULL DEFAULT NULL,
  `id_restaurant` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  INDEX `FKip7fnbccshirjk6k3gubs15mo` (`id_category` ASC) VISIBLE,
  INDEX `FKjjgrgv21ksq5qrrbpjvoonhu9` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FKip7fnbccshirjk6k3gubs15mo`
    FOREIGN KEY (`id_category`)
    REFERENCES `food_panda_delivery`.`food_category` (`id_category`),
  CONSTRAINT `FKjjgrgv21ksq5qrrbpjvoonhu9`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `food_panda_delivery`.`restaurant` (`id_restaurant`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`order_status` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`orders` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `id_status` INT NULL DEFAULT NULL,
  `id_restaurant` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `FK624gtjin3po807j3vix093tlf` (`customer_id` ASC) VISIBLE,
  INDEX `FKtip56ai3loqf8dp9xbbmrpycw` (`id_status` ASC) VISIBLE,
  INDEX `FK35ax9lmqslluf49ebrcaxeo35` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FK35ax9lmqslluf49ebrcaxeo35`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `food_panda_delivery`.`restaurant` (`id_restaurant`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf`
    FOREIGN KEY (`customer_id`)
    REFERENCES `food_panda_delivery`.`customer` (`id_customer`),
  CONSTRAINT `FKtip56ai3loqf8dp9xbbmrpycw`
    FOREIGN KEY (`id_status`)
    REFERENCES `food_panda_delivery`.`order_status` (`id_status`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`order_menuitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`order_menuitem` (
  `id_order` INT NOT NULL,
  `item_id` INT NOT NULL,
  INDEX `FKrmijrofdemw78yqevd8n2wyqb` (`item_id` ASC) VISIBLE,
  INDEX `FKn69llibvbf0wly2pqfff50uic` (`id_order` ASC) VISIBLE,
  CONSTRAINT `FKn69llibvbf0wly2pqfff50uic`
    FOREIGN KEY (`id_order`)
    REFERENCES `food_panda_delivery`.`orders` (`id_order`),
  CONSTRAINT `FKrmijrofdemw78yqevd8n2wyqb`
    FOREIGN KEY (`item_id`)
    REFERENCES `food_panda_delivery`.`menu_item` (`id_item`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`restaurant_menuitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`restaurant_menuitem` (
  `id_restaurant` INT NOT NULL,
  `id_item` INT NOT NULL,
  INDEX `FK53iq9m3uuxlcex4p1cp39ra9k` (`id_item` ASC) VISIBLE,
  INDEX `FKh2egxlviv6ot93op1fgawoohe` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FK53iq9m3uuxlcex4p1cp39ra9k`
    FOREIGN KEY (`id_item`)
    REFERENCES `food_panda_delivery`.`menu_item` (`id_item`),
  CONSTRAINT `FKh2egxlviv6ot93op1fgawoohe`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `food_panda_delivery`.`restaurant` (`id_restaurant`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `food_panda_delivery`.`restaurant_zones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_panda_delivery`.`restaurant_zones` (
  `id_restaurant` INT NOT NULL,
  `id_zone` INT NOT NULL,
  INDEX `FKot9tcvmpcjdor2ppvvpdsm9s9` (`id_zone` ASC) VISIBLE,
  INDEX `FKkfqb9t5bvh2rt5878u72gw8il` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FKkfqb9t5bvh2rt5878u72gw8il`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `food_panda_delivery`.`restaurant` (`id_restaurant`),
  CONSTRAINT `FKot9tcvmpcjdor2ppvvpdsm9s9`
    FOREIGN KEY (`id_zone`)
    REFERENCES `food_panda_delivery`.`delivery_zone` (`id_zone`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
