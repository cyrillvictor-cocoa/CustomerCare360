-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema customercare360
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema customercare360
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `customercare360` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `customercare360` ;

-- -----------------------------------------------------
-- Table `customercare360`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`user` (
                                                        `UserId` INT NOT NULL AUTO_INCREMENT,
                                                        `name` VARCHAR(255) NULL DEFAULT NULL,
    `email` VARCHAR(255) NULL DEFAULT NULL,
    `phone` VARCHAR(20) NULL DEFAULT NULL,
    `username` VARCHAR(255) NULL DEFAULT NULL,
    `password` VARCHAR(255) NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    `Role` ENUM('ADMIN', 'USER', 'AGENT', 'BILL_OPT') NOT NULL,
    PRIMARY KEY (`UserId`),
    UNIQUE INDEX `UserName_UNIQUE` (`username` ASC) VISIBLE,
    UNIQUE INDEX `Phone_UNIQUE` (`phone` ASC) VISIBLE,
    UNIQUE INDEX `Email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `CreatedBy_User_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `Modified_User_FK_idx` (`ModifiedBy` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`billingcycle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`billingcycle` (
                                                                `CycleId` INT NOT NULL AUTO_INCREMENT,
                                                                `ServiceType` ENUM('ELECTRICITY', 'WATER', 'GAS') NOT NULL,
    `PeriodStart` DATETIME NOT NULL,
    `PeriodEnd` DATETIME NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`CycleId`),
    INDEX `CreatedBy_Cycle_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Cycle_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Cycle_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Cycle_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`customer` (
                                                            `CustomerType` ENUM('RESIDENTIAL', 'COMMERCIAL', 'INDUSTRIAL') NOT NULL,
    `ContactInfo` VARCHAR(45) NULL DEFAULT NULL,
    `Status` ENUM('ACTIVE', 'INACTVIE') NOT NULL,
    `UserId` INT NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    INDEX `ModifiedBy_Customer_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    INDEX `CreatedBy_Customer_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `fk_Customer_User1` (`UserId` ASC) VISIBLE,
    PRIMARY KEY (`UserId`),
    UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Customer_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `fk_Customer_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Customer_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`premise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`premise` (
                                                           `PremiseId` INT NOT NULL AUTO_INCREMENT,
                                                           `Address` VARCHAR(45) NOT NULL,
    `Region` VARCHAR(45) NOT NULL,
    `MeterId` INT NULL DEFAULT NULL,
    `Status` ENUM('ENERGIZED', 'DISCONNECTED') NULL DEFAULT NULL,
    `CustomerId` INT NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`PremiseId`),
    INDEX `CreatedBy_Premise_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Premise_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    INDEX `Customer_Premise_FK_idx` (`CustomerId` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Premise_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Premise_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `Customer_Premise_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `customercare360`.`customer` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`serviceaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`serviceaccount` (
                                                                  `AccountId` INT NOT NULL AUTO_INCREMENT,
                                                                  `CustomerId` INT NOT NULL,
                                                                  `StartDate` DATETIME NOT NULL,
                                                                  `ServiceType` ENUM('ELECTRICITY', 'WATER', 'GAS') NOT NULL,
    `EndDate` DATETIME NULL DEFAULT NULL,
    `Status` ENUM('ACTIVE', 'SUSPENDED', 'CLOSED') NULL DEFAULT NULL,
    `PremiseID` INT NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`AccountId`),
    INDEX `ServiceAccount_Premise_FK_idx` (`PremiseID` ASC) VISIBLE,
    INDEX `CreatedBy_Account_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Account_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    INDEX `Customer_Account_FK_idx` (`CustomerId` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Account_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Account_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ServiceAccount_Premise_FK`
    FOREIGN KEY (`PremiseID`)
    REFERENCES `customercare360`.`premise` (`PremiseId`),
    CONSTRAINT `Customer_Account_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `customercare360`.`customer` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`bill` (
                                                        `BillId` INT NOT NULL AUTO_INCREMENT,
                                                        `AccountId` INT NOT NULL,
                                                        `CycleId` INT NOT NULL,
                                                        `Usage` VARCHAR(45) NOT NULL,
    `Amount` DOUBLE NOT NULL,
    `DueDate` DATETIME NOT NULL,
    `Status` ENUM('GENERATED', 'ADJUSTED', 'CLOSED') NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`BillId`),
    INDEX `ServiceAccount_Bill_FK_idx` (`AccountId` ASC) VISIBLE,
    INDEX `BillingCycle_Bill_FK_idx` (`CycleId` ASC) VISIBLE,
    INDEX `CreatedBy_Bill_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Bill_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `BillingCycle_Bill_FK`
    FOREIGN KEY (`CycleId`)
    REFERENCES `customercare360`.`billingcycle` (`CycleId`),
    CONSTRAINT `CreatedBy_Bill_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Bill_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ServiceAccount_Bill_FK`
    FOREIGN KEY (`AccountId`)
    REFERENCES `customercare360`.`serviceaccount` (`AccountId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`adjustment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`adjustment` (
                                                              `AdjustmentId` INT NOT NULL AUTO_INCREMENT,
                                                              `BillId` INT NOT NULL,
                                                              `Reason` VARCHAR(300) NOT NULL,
    `AmountDelta` DOUBLE NOT NULL,
    `ApprovedBy` INT NOT NULL,
    `Status` ENUM('REQUESTED', 'APPROVED', 'REJECTED') NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`AdjustmentId`),
    INDEX `Bill_Adjustment_FK_idx` (`BillId` ASC) VISIBLE,
    INDEX `BillingAgent_Adjustment_FK_idx` (`ApprovedBy` ASC) VISIBLE,
    INDEX `CreatedBy_Adjustment_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Adjustment_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `Bill_Adjustment_FK`
    FOREIGN KEY (`BillId`)
    REFERENCES `customercare360`.`bill` (`BillId`),
    CONSTRAINT `BillingAgent_Adjustment_FK`
    FOREIGN KEY (`ApprovedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `CreatedBy_Adjustment_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Adjustment_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`complaint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`complaint` (
                                                             `ComplaintId` INT NOT NULL AUTO_INCREMENT,
                                                             `CustomerId` INT NOT NULL,
                                                             `Category` ENUM('BILLING', 'SERVICE', 'OUTAGE') NOT NULL,
    `Description` VARCHAR(150) NOT NULL,
    `LoggedDate` DATETIME NOT NULL,
    `Status` ENUM('OPEN', 'INPROGRESS', 'RESOLVED', 'CLOSED') NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`ComplaintId`),
    INDEX `CreatedBy_Complaint_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Complaint_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    INDEX `Customer_Complaint_FK_idx` (`CustomerId` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Complaint_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Complaint_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `Customer_Complaint_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `customercare360`.`customer` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`customerreport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`customerreport` (
                                                                  `ReportId` INT NOT NULL AUTO_INCREMENT,
                                                                  `Scope` ENUM('REGION', 'SERVICETYPE', 'PERIOD') NOT NULL,
    `Metrics` ENUM('AVGRESOLUTIONTIME', 'BILLADJUSTMENTSRATE', 'COMPLAINTVOLUME') NOT NULL,
    `GeneratedDate` DATETIME NULL DEFAULT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`ReportId`),
    INDEX `CreatedBy_Report_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Report_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Report_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Report_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`notification` (
                                                                `NotificationId` INT NOT NULL AUTO_INCREMENT,
                                                                `UserId` INT NOT NULL,
                                                                `Message` VARCHAR(45) NOT NULL,
    `Category` ENUM('BILL', 'SERVICE', 'COMPLAINT') NOT NULL,
    `Status` ENUM('UNREAD', 'READ', 'DISMISSED') NOT NULL,
    `CreatedDate` DATETIME NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`NotificationId`),
    INDEX `Notification_user_FK_idx` (`UserId` ASC) VISIBLE,
    INDEX `CreatedBy_Notification_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifeidBy_Notification_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Notification_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifeidBy_Notification_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `Notification_user_FK`
    FOREIGN KEY (`UserId`)
    REFERENCES `customercare360`.`user` (`UserId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`provider` (
                                                            `provider_id` INT NOT NULL AUTO_INCREMENT,
                                                            `provider_name` VARCHAR(100) NOT NULL,
    `contact` VARCHAR(100) NOT NULL,
    `address` VARCHAR(300) NOT NULL,
    `region` VARCHAR(100) NOT NULL,
    `status` TINYINT NOT NULL,
    PRIMARY KEY (`provider_id`),
    UNIQUE INDEX `provider_name_UNIQUE` (`provider_name` ASC) VISIBLE,
    UNIQUE INDEX `contact_UNIQUE` (`contact` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`utilitytype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`utilitytype` (
                                                               `id` INT NOT NULL AUTO_INCREMENT,
                                                               `utilitytype_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `utilitytype_id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `ProviderUtility_utility_fk_idx` (`utilitytype_name` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`providerutility`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`providerutility` (
                                                                   `providerutility_id` INT NOT NULL AUTO_INCREMENT,
                                                                   `provider_id` INT NOT NULL,
                                                                   `utilitytype_id` INT NOT NULL,
                                                                   PRIMARY KEY (`providerutility_id`),
    UNIQUE INDEX `providerutility_id_UNIQUE` (`providerutility_id` ASC) VISIBLE,
    INDEX `providerutility_provider_fk_idx` (`provider_id` ASC) VISIBLE,
    INDEX `providerutility_utilitytype_fk_idx` (`utilitytype_id` ASC) VISIBLE,
    CONSTRAINT `providerutility_provider_fk`
    FOREIGN KEY (`provider_id`)
    REFERENCES `customercare360`.`provider` (`provider_id`),
    CONSTRAINT `providerutility_utilitytype_fk`
    FOREIGN KEY (`utilitytype_id`)
    REFERENCES `customercare360`.`utilitytype` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`service` (
                                                           `service_id` INT NOT NULL AUTO_INCREMENT,
                                                           `service_name` VARCHAR(100) NOT NULL,
    `provider_id` INT NOT NULL,
    `pricepercycle` DOUBLE NOT NULL,
    `cycleperiod` INT NOT NULL,
    PRIMARY KEY (`service_id`),
    UNIQUE INDEX `service_id_UNIQUE` (`service_id` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`serviceorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`serviceorder` (
                                                                `OrderId` INT NOT NULL AUTO_INCREMENT,
                                                                `ServiceAccountID` INT NOT NULL,
                                                                `PremiseId` INT NOT NULL,
                                                                `OrderType` ENUM('CONNECT', 'DISCONNECT', 'INSPECTION') NOT NULL,
    `ScheduledDate` DATETIME NULL DEFAULT NULL,
    `CompletionDate` DATETIME NULL DEFAULT NULL,
    `Status` ENUM('SCHEDULED', 'INPROGRESS', 'COMPLETED', 'FAILED') NOT NULL COMMENT 'FOR THE FIELD AGENT TO VIEW THE ORDERS',
    `AssignedTo` INT NULL DEFAULT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`OrderId`),
    INDEX `ServiceAccount_Order_FK_idx` (`ServiceAccountID` ASC) VISIBLE,
    INDEX `Premise_Order_FK_idx` (`PremiseId` ASC) VISIBLE,
    INDEX `FieldAgent_Order_FK_idx` (`AssignedTo` ASC) VISIBLE,
    INDEX `CreatedBy_Order_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Order_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Order_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `FieldAgent_Order_FK`
    FOREIGN KEY (`AssignedTo`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Order_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `Premise_Order_FK`
    FOREIGN KEY (`PremiseId`)
    REFERENCES `customercare360`.`premise` (`PremiseId`),
    CONSTRAINT `ServiceAccount_Order_FK`
    FOREIGN KEY (`ServiceAccountID`)
    REFERENCES `customercare360`.`serviceaccount` (`AccountId`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `customercare360`.`servicerequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customercare360`.`servicerequest` (
                                                                  `RequestId` INT NOT NULL AUTO_INCREMENT,
                                                                  `CustomerId` INT NOT NULL,
                                                                  `RequestType` ENUM('NEWCONNECTION', 'DISCONNECT', 'COMPLAINT', 'INQUIRY') NOT NULL,
    `CreatedDate` DATETIME NOT NULL,
    `Priority` ENUM('HIGH', 'LOW', 'MEDIUM') NULL DEFAULT NULL,
    `Status` ENUM('OPEN', 'INPROGRESS', 'RESOLVED', 'CLOSED') NOT NULL,
    `ServiceType` ENUM('ELECTRICITY', 'GAS', 'WATER') NOT NULL,
    `PremiseId` INT NOT NULL,
    `CreatedBy` INT NULL DEFAULT NULL,
    `ModifiedBy` INT NULL DEFAULT NULL,
    PRIMARY KEY (`RequestId`),
    INDEX `Premise_Request_FK_idx` (`PremiseId` ASC) VISIBLE,
    INDEX `CreatedBy_Request_FK_idx` (`CreatedBy` ASC) VISIBLE,
    INDEX `ModifiedBy_Request_FK_idx` (`ModifiedBy` ASC) VISIBLE,
    INDEX `Customer_Request_FK_idx` (`CustomerId` ASC) VISIBLE,
    CONSTRAINT `CreatedBy_Request_FK`
    FOREIGN KEY (`CreatedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `ModifiedBy_Request_FK`
    FOREIGN KEY (`ModifiedBy`)
    REFERENCES `customercare360`.`user` (`UserId`),
    CONSTRAINT `Premise_Request_FK`
    FOREIGN KEY (`PremiseId`)
    REFERENCES `customercare360`.`premise` (`PremiseId`),
    CONSTRAINT `Customer_Request_FK`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `customercare360`.`customer` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
