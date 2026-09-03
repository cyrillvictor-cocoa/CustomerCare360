USE customercare360;

-- USER
INSERT INTO user(name,email,phone,UserName,password,CreatedBy,ModifiedBy)
VALUES
('John Smith','john@example.com','9876543210','johnsmith','pass123',NULL,NULL),
('Alice Brown','alice@example.com','9876543211','alicebrown','pass123',1,1),
('Michael Lee','michael@example.com','9876543212','michaellee','pass123',1,1),
('Sarah Davis','sarah@example.com','9876543213','sarahdavis','pass123',2,2),
('David Wilson','david@example.com','9876543214','davidwilson','pass123',3,3);

-- BILLINGCYCLE
INSERT INTO billingcycle(ServiceType,PeriodStart,PeriodEnd,CreatedBy,ModifiedBy)
VALUES
('ELECTRICITY','2025-01-01','2025-01-31',1,1),
('WATER','2025-02-01','2025-02-28',2,2),
('GAS','2025-03-01','2025-03-31',3,3),
('ELECTRICITY','2025-04-01','2025-04-30',4,4),
('WATER','2025-05-01','2025-05-31',5,5);

-- CUSTOMER
INSERT INTO customer(CustomerType,ContactInfo,Status,UserId,CreatedBy,ModifiedBy)
VALUES
('RESIDENTIAL','john@example.com','ACTIVE',1,1,1),
('COMMERCIAL','alice@example.com','ACTIVE',2,2,2),
('INDUSTRIAL','michael@example.com','ACTIVE',3,3,3),
('RESIDENTIAL','sarah@example.com','ACTIVE',4,4,4),
('COMMERCIAL','david@example.com','ACTIVE',5,5,5);

-- PREMISE
INSERT INTO premise(Address,Region,MeterId,Status,CustomerId,CreatedBy,ModifiedBy)
VALUES
('12 Main Street','North',101,'ENERGIZED',1,1,1),
('24 Lake Road','South',102,'ENERGIZED',2,2,2),
('36 Park Avenue','East',103,'DISCONNECTED',3,3,3),
('48 River Street','West',104,'ENERGIZED',4,4,4),
('60 Central Road','Central',105,'ENERGIZED',5,5,5);

-- SERVICEACCOUNT
INSERT INTO serviceaccount(CustomerId,StartDate,ServiceType,EndDate,Status,PremiseID,CreatedBy,ModifiedBy)
VALUES
(1,'2025-01-01','ELECTRICITY',NULL,'ENERGIZED',1,1,1),
(2,'2025-01-02','WATER',NULL,'ENERGIZED',2,2,2),
(3,'2025-01-03','GAS',NULL,'DISCONNECTED',3,3,3),
(4,'2025-01-04','ELECTRICITY',NULL,'ENERGIZED',4,4,4),
(5,'2025-01-05','WATER',NULL,'ENERGIZED',5,5,5);

-- BILL
INSERT INTO bill(AccountId,CycleId,`Usage`,Amount,DueDate,Status,CreatedBy,ModifiedBy)
VALUES
(1,1,'250',1200.50,'2025-02-10','GENERATED',1,1),
(2,2,'180',850.25,'2025-03-10','GENERATED',2,2),
(3,3,'320',1750.00,'2025-04-10','ADJUSTED',3,3),
(4,4,'210',995.75,'2025-05-10','CLOSED',4,4),
(5,5,'170',745.60,'2025-06-10','GENERATED',5,5);

-- ADJUSTMENT
INSERT INTO adjustment(BillId,Reason,AmountDelta,ApprovedBy,Status,CreatedBy,ModifiedBy)
VALUES
(1,'Meter correction',-100,1,'APPROVED',1,1),
(2,'Usage review',50,2,'REQUESTED',2,2),
(3,'Billing error',-75,3,'APPROVED',3,3),
(4,'Late fee reversal',-25,4,'REJECTED',4,4),
(5,'Promotional discount',-150,5,'APPROVED',5,5);

-- COMPLAINT
INSERT INTO complaint(CustomerId,Category,Description,LoggedDate,Status,CreatedBy,ModifiedBy)
VALUES
(1,'BILLING','Incorrect bill amount','2025-01-15','OPEN',1,1),
(2,'SERVICE','Low water pressure','2025-01-16','INPROGRESS',2,2),
(3,'OUTAGE','Gas outage reported','2025-01-17','RESOLVED',3,3),
(4,'SERVICE','Meter malfunction','2025-01-18','CLOSED',4,4),
(5,'BILLING','Duplicate charge','2025-01-19','OPEN',5,5);

-- CUSTOMERREPORT
INSERT INTO customerreport(Scope,Metrics,GeneratedDate,CreatedBy,ModifiedBy)
VALUES
('REGION','AVGRESOLUTIONTIME','2025-01-31',1,1),
('SERVICETYPE','BILLADJUSTMENTSRATE','2025-02-28',2,2),
('PERIOD','COMPLAINTVOLUME','2025-03-31',3,3),
('REGION','COMPLAINTVOLUME','2025-04-30',4,4),
('SERVICETYPE','AVGRESOLUTIONTIME','2025-05-31',5,5);

-- NOTIFICATION
INSERT INTO notification(UserId,Message,Category,Status,CreatedDate,CreatedBy,ModifiedBy)
VALUES
(1,'Bill Generated','BILL','UNREAD','2025-01-20',1,1),
(2,'Service Scheduled','SERVICE','READ','2025-01-21',2,2),
(3,'Complaint Updated','COMPLAINT','UNREAD','2025-01-22',3,3),
(4,'Payment Reminder','BILL','DISMISSED','2025-01-23',4,4),
(5,'Service Completed','SERVICE','READ','2025-01-24',5,5);

-- SERVICEORDER
INSERT INTO serviceorder(ServiceAccountID,PremiseId,OrderType,SchduledDate,CompletionDate,Status,AssignedTo,CreatedBy,ModifiedBy)
VALUES
(1,1,'CONNECT','2025-02-01',NULL,'SCHEDULED',1,1,1),
(2,2,'INSPECTION','2025-02-02',NULL,'INPROGRESS',2,2,2),
(3,3,'DISCONNECT','2025-02-03','2025-02-05','COMPLETED',3,3,3),
(4,4,'CONNECT','2025-02-04',NULL,'FAILED',4,4,4),
(5,5,'INSPECTION','2025-02-05',NULL,'SCHEDULED',5,5,5);

-- SERVICEREQUEST
INSERT INTO servicerequest(CustomerId,RequestType,CreatedDate,Priority,Status,ServiceType,PremiseId,CreatedBy,ModifiedBy)
VALUES
(1,'NEWCONNECTION','2025-01-10','HIGH','OPEN','ELECTRICITY',1,1,1),
(2,'COMPLAINT','2025-01-11','MEDIUM','INPROGRESS','WATER',2,2,2),
(3,'INQUIRY','2025-01-12','LOW','RESOLVED','GAS',3,3,3),
(4,'DISCONNECT','2025-01-13','HIGH','OPEN','ELECTRICITY',4,4,4),
(5,'COMPLAINT','2025-01-14','MEDIUM','CLOSED','WATER',5,5,5);