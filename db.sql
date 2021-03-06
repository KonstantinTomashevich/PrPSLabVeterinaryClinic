-- MySQL Script generated by MySQL Workbench
-- Sun May 12 22:46:27 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vetclinic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vetclinic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vetclinic` DEFAULT CHARACTER SET utf8 ;
USE `vetclinic` ;

-- -----------------------------------------------------
-- Table `vetclinic`.`species`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`species` (
  `species_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`species_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`genders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`genders` (
  `gender_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`gender_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`clients` (
  `client_id` INT NOT NULL,
  `name` VARCHAR(50) NULL,
  `address` VARCHAR(200) NULL,
  `phoneNumber` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `personalDiscount` DOUBLE NULL,
  PRIMARY KEY (`client_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`veterinary_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`veterinary_cards` (
  `card_id` INT NOT NULL,
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`patients` (
  `patient_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `species_id` INT NULL,
  `name` VARCHAR(45) NULL,
  `gender_id` INT NULL,
  `birthYear` INT NULL,
  `client_id` INT NULL,
  `card_id` INT NOT NULL,
  PRIMARY KEY (`patient_id`),
  INDEX `fk_patients_species_idx` (`species_id` ASC) VISIBLE,
  INDEX `fk_patients_genders1_idx` (`gender_id` ASC) VISIBLE,
  INDEX `fk_patients_clients1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_patients_veterinary_cards1_idx` (`card_id` ASC) VISIBLE,
  CONSTRAINT `fk_patients_species`
    FOREIGN KEY (`species_id`)
    REFERENCES `vetclinic`.`species` (`species_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_genders1`
    FOREIGN KEY (`gender_id`)
    REFERENCES `vetclinic`.`genders` (`gender_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `vetclinic`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_veterinary_cards1`
    FOREIGN KEY (`card_id`)
    REFERENCES `vetclinic`.`veterinary_cards` (`card_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`checks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`checks` (
  `check_id` INT NOT NULL,
  `paid` TINYINT NULL,
  `card_id` INT NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`check_id`),
  INDEX `fk_checks_veterinary_cards1_idx` (`card_id` ASC) VISIBLE,
  CONSTRAINT `fk_checks_veterinary_cards1`
    FOREIGN KEY (`card_id`)
    REFERENCES `vetclinic`.`veterinary_cards` (`card_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`service_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`service_types` (
  `service_type_id` INT NOT NULL,
  `description` VARCHAR(300) NULL,
  `cost` DOUBLE NULL,
  PRIMARY KEY (`service_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`services` (
  `service_id` INT NOT NULL,
  `service_type_id` INT NOT NULL,
  `check_id` INT NOT NULL,
  PRIMARY KEY (`service_id`),
  INDEX `fk_services_service_types1_idx` (`service_type_id` ASC) VISIBLE,
  INDEX `fk_services_checks1_idx` (`check_id` ASC) VISIBLE,
  CONSTRAINT `fk_services_service_types1`
    FOREIGN KEY (`service_type_id`)
    REFERENCES `vetclinic`.`service_types` (`service_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_services_checks1`
    FOREIGN KEY (`check_id`)
    REFERENCES `vetclinic`.`checks` (`check_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`medicine_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`medicine_types` (
  `medicine_type_id` INT NOT NULL,
  `description` VARCHAR(300) NULL,
  PRIMARY KEY (`medicine_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`medicines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`medicines` (
  `medicine_id` INT NOT NULL,
  `medicine_type_id` INT NOT NULL,
  `check_id` INT NOT NULL,
  PRIMARY KEY (`medicine_id`),
  INDEX `fk_medicines_medicine_types1_idx` (`medicine_type_id` ASC) VISIBLE,
  INDEX `fk_medicines_checks1_idx` (`check_id` ASC) VISIBLE,
  CONSTRAINT `fk_medicines_medicine_types1`
    FOREIGN KEY (`medicine_type_id`)
    REFERENCES `vetclinic`.`medicine_types` (`medicine_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicines_checks1`
    FOREIGN KEY (`check_id`)
    REFERENCES `vetclinic`.`checks` (`check_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`doctors` (
  `doctor_id` INT NOT NULL,
  `name` VARCHAR(50) NULL,
  `birthDate` DATE NULL,
  `experience` INT NULL,
  `phoneNumber` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`doctor_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`appointments` (
  `appointment_id` INT NOT NULL,
  `date` DATE NULL,
  `doctor_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `patient_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`appointment_id`),
  INDEX `fk_appointments_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_appointments_clients1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_appointments_patients1_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_appointments_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `vetclinic`.`doctors` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `vetclinic`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `vetclinic`.`patients` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vetclinic`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vetclinic`.`login` (
  `token` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`token`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
