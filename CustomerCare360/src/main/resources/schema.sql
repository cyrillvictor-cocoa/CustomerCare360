-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CustomerCare360
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CustomerCare360
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CustomerCare360` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema customercare360
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema customercare360
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `customercare360` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `CustomerCare360` ;

-- -----------------------------------------------------
-- Table `CustomerCare360`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` INT(15) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `Phone_UNIQUE` (`Phone` ASC) VISIBLE,
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC) VISIBLE,
  INDEX `CreatedBy_User_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `Modified_User_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `CreatedBy_User_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Modified_User_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Customer` (
  `CustomerId` INT NOT NULL AUTO_INCREMENT,
  `CustomerType` ENUM("RESIDENTIAL", "COMMERCIAL", "INDUSTRIAL") NOT NULL,
  `ContactInfo` VARCHAR(45) NULL,
  `Status` ENUM("ACTIVE", "INACTVIE") NOT NULL,
  `UserId` INT NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`CustomerId`),
  INDEX `fk_Customer_User1_idx` (`UserId` ASC) VISIBLE,
  UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Customer_FK`
    FOREIGN KEY (`UserId`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Customer_FK`
    FOREIGN KEY (`UserId`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Premise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Premise` (
  `PremiseId` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(45) NOT NULL,
  `Region` VARCHAR(45) NOT NULL,
  `MeterId` INT NULL,
  `Status` ENUM("ENERGIZED", "DISCONNECTED") NULL,
  `CustomerId` INT NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`PremiseId`),
  INDEX `Customer_Premise_FK_idx` (`CustomerId` ASC) VISIBLE,
  INDEX `CreatedBy_Premise_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Premise_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Customer_Premise_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `CustomerCare360`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Premise_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Premise_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`ServiceAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`ServiceAccount` (
  `AccountId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` INT NOT NULL,
  `StartDate` DATETIME NOT NULL,
  `ServiceType` ENUM("ELECTRICITY", "WATER", "GAS") NOT NULL,
  `EndDate` DATETIME NULL,
  `Status` ENUM("ENERGIZED", "DISCONNECTED") NULL,
  `PremiseID` INT NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`AccountId`),
  INDEX `Customer_ServiceAccount_FK_idx` (`CustomerId` ASC) VISIBLE,
  INDEX `ServiceAccount_Premise_FK_idx` (`PremiseID` ASC) VISIBLE,
  INDEX `CreatedBy_Account_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Account_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Customer_ServiceAccount_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `CustomerCare360`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ServiceAccount_Premise_FK`
    FOREIGN KEY (`PremiseID`)
    REFERENCES `CustomerCare360`.`Premise` (`PremiseId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Account_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Account_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Notification` (
  `NotificationId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `Message` VARCHAR(45) NOT NULL,
  `Category` ENUM("BILL", "SERVICE", "COMPLAINT") NOT NULL,
  `Status` ENUM("UNREAD", "READ", "DISMISSED") NOT NULL,
  `CreatedDate` DATETIME NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`NotificationId`),
  INDEX `Notification_user_FK_idx` (`UserId` ASC) VISIBLE,
  INDEX `CreatedBy_Notification_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifeidBy_Notification_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Notification_user_FK`
    FOREIGN KEY (`UserId`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Notification_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifeidBy_Notification_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`ServiceRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`ServiceRequest` (
  `RequestId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` INT NOT NULL,
  `RequestType` ENUM("NEWCONNECTION", "DISCONNECT", "COMPLAINT", "INQUIRY") NOT NULL,
  `CreatedDate` DATETIME NOT NULL,
  `Priority` ENUM("HIGH", "LOW", "MEDIUM") NULL,
  `Status` ENUM("OPEN", "INPROGRESS", "RESOLVED", "CLOSED") NOT NULL,
  `ServiceType` ENUM("ELECTRICITY", "GAS", "WATER") NOT NULL,
  `PremiseId` INT NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`RequestId`),
  INDEX `Customer_Request_FK_idx` (`CustomerId` ASC) VISIBLE,
  INDEX `Premise_Request_FK_idx` (`PremiseId` ASC) VISIBLE,
  INDEX `CreatedBy_Request_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Request_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Customer_Request_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `CustomerCare360`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Premise_Request_FK`
    FOREIGN KEY (`PremiseId`)
    REFERENCES `CustomerCare360`.`Premise` (`PremiseId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Request_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Request_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`ServiceOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`ServiceOrder` (
  `OrderId` INT NOT NULL AUTO_INCREMENT,
  `ServiceAccountID` INT NOT NULL,
  `PremiseId` INT NOT NULL,
  `OrderType` ENUM("CONNECT", "DISCONNECT", "INSPECTION") NOT NULL,
  `SchduledDate` DATETIME NULL,
  `CompletionDate` DATETIME NULL,
  `Status` ENUM("SCHEDULED", "INPROGRESS", "COMPLETED", "FAILED") NOT NULL COMMENT 'FOR THE FIELD AGENT TO VIEW THE ORDERS',
  `AssignedTo` INT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`OrderId`),
  INDEX `ServiceAccount_Order_FK_idx` (`ServiceAccountID` ASC) VISIBLE,
  INDEX `Premise_Order_FK_idx` (`PremiseId` ASC) VISIBLE,
  INDEX `FieldAgent_Order_FK_idx` (`AssignedTo` ASC) VISIBLE,
  INDEX `CreatedBy_Order_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Order_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `ServiceAccount_Order_FK`
    FOREIGN KEY (`ServiceAccountID`)
    REFERENCES `CustomerCare360`.`ServiceAccount` (`AccountId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Premise_Order_FK`
    FOREIGN KEY (`PremiseId`)
    REFERENCES `CustomerCare360`.`Premise` (`PremiseId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FieldAgent_Order_FK`
    FOREIGN KEY (`AssignedTo`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Order_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Order_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`BillingCycle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`BillingCycle` (
  `CycleId` INT NOT NULL AUTO_INCREMENT,
  `ServiceType` ENUM("ELECTRICITY", "WATER", "GAS") NOT NULL,
  `PeriodStart` DATETIME NOT NULL,
  `PeriodEnd` DATETIME NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`CycleId`),
  INDEX `CreatedBy_Cycle_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Cycle_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `CreatedBy_Cycle_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Cycle_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Bill` (
  `BillId` INT NOT NULL AUTO_INCREMENT,
  `AccountId` INT NOT NULL,
  `CycleId` INT NOT NULL,
  `Usage` VARCHAR(45) NOT NULL,
  `Amount` DOUBLE NOT NULL,
  `DueDate` DATETIME NOT NULL,
  `Status` ENUM("GENERATED", "ADJUSTED", "CLOSED") NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`BillId`),
  INDEX `ServiceAccount_Bill_FK_idx` (`AccountId` ASC) VISIBLE,
  INDEX `BillingCycle_Bill_FK_idx` (`CycleId` ASC) VISIBLE,
  INDEX `CreatedBy_Bill_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Bill_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `ServiceAccount_Bill_FK`
    FOREIGN KEY (`AccountId`)
    REFERENCES `CustomerCare360`.`ServiceAccount` (`AccountId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `BillingCycle_Bill_FK`
    FOREIGN KEY (`CycleId`)
    REFERENCES `CustomerCare360`.`BillingCycle` (`CycleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Bill_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Bill_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Adjustment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Adjustment` (
  `AdjustmentId` INT NOT NULL AUTO_INCREMENT,
  `BillId` INT NOT NULL,
  `Reason` VARCHAR(300) NOT NULL,
  `AmountDelta` DOUBLE NOT NULL,
  `ApprovedBy` INT NOT NULL,
  `Status` ENUM("REQUESTED", "APPROVED", "REJECTED") NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`AdjustmentId`),
  INDEX `Bill_Adjustment_FK_idx` (`BillId` ASC) VISIBLE,
  INDEX `BillingAgent_Adjustment_FK_idx` (`ApprovedBy` ASC) VISIBLE,
  INDEX `CreatedBy_Adjustment_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Adjustment_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Bill_Adjustment_FK`
    FOREIGN KEY (`BillId`)
    REFERENCES `CustomerCare360`.`Bill` (`BillId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `BillingAgent_Adjustment_FK`
    FOREIGN KEY (`ApprovedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Adjustment_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Adjustment_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`Complaint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`Complaint` (
  `ComplaintId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` INT NOT NULL,
  `Category` ENUM("BILLING", "SERVICE", "OUTAGE") NOT NULL,
  `Description` VARCHAR(150) NOT NULL,
  `LoggedDate` DATETIME NOT NULL,
  `Status` ENUM("OPEN", "INPROGRESS", "RESOLVED", "CLOSED") NOT NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`ComplaintId`),
  INDEX `Customer_Complaint_FK_idx` (`CustomerId` ASC) VISIBLE,
  INDEX `CreatedBy_Complaint_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Complaint_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `Customer_Complaint_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `CustomerCare360`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CreatedBy_Complaint_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Complaint_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CustomerCare360`.`CustomerReport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CustomerCare360`.`CustomerReport` (
  `ReportId` INT NOT NULL AUTO_INCREMENT,
  `Scope` ENUM("REGION", "SERVICETYPE", "PERIOD") NOT NULL,
  `Metrics` ENUM("AVGRESOLUTIONTIME", "BILLADJUSTMENTSRATE", "COMPLAINTVOLUME") NOT NULL,
  `GeneratedDate` DATETIME NULL,
  `CreatedBy` INT NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`ReportId`),
  INDEX `CreatedBy_Report_FK_idx` (`CreatedBy` ASC) VISIBLE,
  INDEX `ModifiedBy_Report_FK_idx` (`ModifiedBy` ASC) VISIBLE,
  CONSTRAINT `CreatedBy_Report_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ModifiedBy_Report_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `CustomerCare360`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `customercare360` ;

-- -----------------------------------------------------
-- Table `customercare360`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`user` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(15) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Role` ENUM('CUSTOMER', 'AGENT', 'BILLING', 'ADMIN') NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE INDEX `Phone_UNIQUE` (`Phone` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`customer` (
  `CustomerId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `CustomerType` ENUM('RESIDENTIAL', 'COMMERCIAL', 'INDUSTRIAL') NOT NULL,
  `ContactInfo` VARCHAR(45) NOT NULL,
  `Status` ENUM('ACTIVE', 'INACTIVE') NOT NULL,
  PRIMARY KEY (`CustomerId`),
  UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC) VISIBLE,
  CONSTRAINT `Customer_User_Id`
    FOREIGN KEY (`UserId`)
    REFERENCES `customercare360`.`user` (`UserId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`premise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`premise` (
  `PremiseId` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(45) NOT NULL,
  `Region` VARCHAR(45) NOT NULL,
  `MeterId` VARCHAR(45) NULL DEFAULT NULL,
  `Status` ENUM('ENERGIZED', 'DISCONNECTED') NULL DEFAULT NULL,
  `CustomerId` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`PremiseId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`serviceaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`serviceaccount` (
  `AccountId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` INT NOT NULL,
  `ServiceType` ENUM('ELECTRICITY', 'WATER', 'GAS') NULL DEFAULT NULL,
  `StartDate` DATETIME NOT NULL,
  `EndDate` DATETIME NULL DEFAULT NULL,
  `Status` ENUM('ACTIVE', 'SUSPENDED', 'CLOSED') NULL DEFAULT NULL,
  `PremiseId` INT NOT NULL,
  PRIMARY KEY (`AccountId`),
  INDEX `Customer_service_account_idx` (`CustomerId` ASC) VISIBLE,
  INDEX `Service_Premise_FK_idx` (`PremiseId` ASC) VISIBLE,
  CONSTRAINT `Customer_service_account`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `customercare360`.`customer` (`CustomerId`),
  CONSTRAINT `Service_Premise_FK`
    FOREIGN KEY (`PremiseId`)
    REFERENCES `customercare360`.`premise` (`PremiseId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
