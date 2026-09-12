USE customercare360;

-- =========================
-- USER
-- =========================
INSERT INTO user
(name,email,phone,UserName,password,CreatedBy,ModifiedBy,Role)
VALUES
    ('John Smith','john@example.com','9876543210','johnsmith','pass123',NULL,NULL,'ADMIN'),
    ('Alice Brown','alice@example.com','9876543211','alicebrown','pass123',1,1,'USER'),
    ('Michael Lee','michael@example.com','9876543212','michaellee','pass123',1,1,'AGENT'),
    ('Sarah Davis','sarah@example.com','9876543213','sarahdavis','pass123',2,2,'BILL_OPT'),
    ('David Wilson','david@example.com','9876543214','davidwilson','pass123',3,3,'USER');

-- =========================
-- PROVIDER
-- =========================
INSERT INTO provider
(provider_name,contact,address,region,status)
VALUES
    ('Tamil Nadu Electricity Board',
     'tneb@provider.com',
     '144 Anna Salai, Chennai',
     'Tamil Nadu',
     1),

    ('Metro Water Supply Board',
     'metro@provider.com',
     '28 EVR Road, Chennai',
     'Tamil Nadu',
     1),

    ('Tamil Nadu Gas Corporation',
     'tngas@provider.com',
     '45 GST Road, Chennai',
     'Tamil Nadu',
     1);

-- =========================
-- UTILITYTYPE
-- =========================
INSERT INTO utilitytype
(utilitytype_name)
VALUES
    ('ELECTRICITY'),
    ('WATER'),
    ('GAS');

-- =========================
-- PROVIDERUTILITY
-- =========================
INSERT INTO providerutility
(provider_id,utilitytype_id)
VALUES
    (1,1),
    (2,2),
    (3,3);

-- =========================
-- SERVICE
-- =========================
INSERT INTO service
(service_name,provider_id,pricepercycle,cycleperiod)
VALUES
    ('Domestic Electricity Connection',1,1200.00,30),
    ('Domestic Water Supply',2,700.00,30),
    ('Domestic Gas Supply',3,950.00,30);

-- =========================
-- BILLINGCYCLE
-- =========================
INSERT INTO billingcycle
(ServiceType,PeriodStart,PeriodEnd,CreatedBy,ModifiedBy)
VALUES
    ('ELECTRICITY','2025-01-01 00:00:00','2025-01-31 23:59:59',1,1),
    ('WATER','2025-02-01 00:00:00','2025-02-28 23:59:59',2,2),
    ('GAS','2025-03-01 00:00:00','2025-03-31 23:59:59',3,3),
    ('ELECTRICITY','2025-04-01 00:00:00','2025-04-30 23:59:59',4,4),
    ('WATER','2025-05-01 00:00:00','2025-05-31 23:59:59',5,5);

-- =========================
-- CUSTOMER
-- =========================
INSERT INTO customer
(CustomerType,ContactInfo,Status,UserId,CreatedBy,ModifiedBy)
VALUES
    ('RESIDENTIAL','john@example.com','ACTIVE',1,1,1),
    ('COMMERCIAL','alice@example.com','ACTIVE',2,2,2),
    ('INDUSTRIAL','michael@example.com','ACTIVE',3,3,3),
    ('RESIDENTIAL','sarah@example.com','ACTIVE',4,4,4),
    ('COMMERCIAL','david@example.com','ACTIVE',5,5,5);

-- =========================
-- PREMISE
-- =========================
INSERT INTO premise
(Address,Region,MeterId,Status,CustomerId,CreatedBy,ModifiedBy)
VALUES
    ('12 Main Street','North',101,'ENERGIZED',1,1,1),
    ('24 Lake Road','South',102,'ENERGIZED',2,2,2),
    ('36 Park Avenue','East',103,'DISCONNECTED',3,3,3),
    ('48 River Street','West',104,'ENERGIZED',4,4,4),
    ('60 Central Road','Central',105,'ENERGIZED',5,5,5);

-- =========================
-- SERVICEACCOUNT
-- =========================
INSERT INTO serviceaccount
(CustomerId,StartDate,ServiceType,EndDate,Status,PremiseID,CreatedBy,ModifiedBy)
VALUES
    (1,'2025-01-01 00:00:00','ELECTRICITY',NULL,'ACTIVE',1,1,1),
    (2,'2025-01-02 00:00:00','WATER',NULL,'ACTIVE',2,2,2),
    (3,'2025-01-03 00:00:00','GAS',NULL,'SUSPENDED',3,3,3),
    (4,'2025-01-04 00:00:00','ELECTRICITY',NULL,'ACTIVE',4,4,4),
    (5,'2025-01-05 00:00:00','WATER',NULL,'ACTIVE',5,5,5);

-- =========================
-- BILL
-- =========================
INSERT INTO bill
(AccountId,CycleId,`Usage`,Amount,DueDate,Status,CreatedBy,ModifiedBy)
VALUES
    (1,1,'250',1200.50,'2025-02-10 00:00:00','GENERATED',1,1),
    (2,2,'180',850.25,'2025-03-10 00:00:00','GENERATED',2,2),
    (3,3,'320',1750.00,'2025-04-10 00:00:00','ADJUSTED',3,3),
    (4,4,'210',995.75,'2025-05-10 00:00:00','CLOSED',4,4),
    (5,5,'170',745.60,'2025-06-10 00:00:00','GENERATED',5,5);

-- =========================
-- ADJUSTMENT
-- =========================
INSERT INTO adjustment
(BillId,Reason,AmountDelta,ApprovedBy,Status,CreatedBy,ModifiedBy)
VALUES
    (1,'Meter correction',-100.00,1,'APPROVED',1,1),
    (2,'Usage review',50.00,2,'REQUESTED',2,2),
    (3,'Billing error',-75.00,3,'APPROVED',3,3),
    (4,'Late fee reversal',-25.00,4,'REJECTED',4,4),
    (5,'Promotional discount',-150.00,5,'APPROVED',5,5);

-- =========================
-- COMPLAINT
-- =========================
INSERT INTO complaint
(CustomerId,Category,Description,LoggedDate,Status,CreatedBy,ModifiedBy)
VALUES
    (1,'BILLING','Incorrect bill amount','2025-01-15 10:00:00','OPEN',1,1),
    (2,'SERVICE','Low water pressure','2025-01-16 11:00:00','INPROGRESS',2,2),
    (3,'OUTAGE','Gas outage reported','2025-01-17 12:00:00','RESOLVED',3,3),
    (4,'SERVICE','Meter malfunction','2025-01-18 13:00:00','CLOSED',4,4),
    (5,'BILLING','Duplicate charge','2025-01-19 14:00:00','OPEN',5,5);

-- =========================
-- CUSTOMERREPORT
-- =========================
INSERT INTO customerreport
(Scope,Metrics,GeneratedDate,CreatedBy,ModifiedBy)
VALUES
    ('REGION','AVGRESOLUTIONTIME','2025-01-31 00:00:00',1,1),
    ('SERVICETYPE','BILLADJUSTMENTSRATE','2025-02-28 00:00:00',2,2),
    ('PERIOD','COMPLAINTVOLUME','2025-03-31 00:00:00',3,3),
    ('REGION','COMPLAINTVOLUME','2025-04-30 00:00:00',4,4),
    ('SERVICETYPE','AVGRESOLUTIONTIME','2025-05-31 00:00:00',5,5);

-- =========================
-- NOTIFICATION
-- =========================
INSERT INTO notification
(UserId,Message,Category,Status,CreatedDate,CreatedBy,ModifiedBy)
VALUES
    (1,'Bill Generated','BILL','UNREAD','2025-01-20 09:00:00',1,1),
    (2,'Service Scheduled','SERVICE','READ','2025-01-21 09:30:00',2,2),
    (3,'Complaint Updated','COMPLAINT','UNREAD','2025-01-22 10:00:00',3,3),
    (4,'Payment Reminder','BILL','DISMISSED','2025-01-23 11:00:00',4,4),
    (5,'Service Completed','SERVICE','READ','2025-01-24 12:00:00',5,5);

-- =========================
-- SERVICEORDER
-- =========================
INSERT INTO serviceorder
(ServiceAccountID,PremiseId,OrderType,ScheduledDate,
 CompletionDate,Status,AssignedTo,CreatedBy,ModifiedBy)
VALUES
    (1,1,'CONNECT','2025-02-01 08:00:00',NULL,'SCHEDULED',1,1,1),
    (2,2,'INSPECTION','2025-02-02 09:00:00',NULL,'INPROGRESS',2,2,2),
    (3,3,'DISCONNECT','2025-02-03 10:00:00','2025-02-05 15:00:00','COMPLETED',3,3,3),
    (4,4,'CONNECT','2025-02-04 11:00:00',NULL,'FAILED',4,4,4),
    (5,5,'INSPECTION','2025-02-05 12:00:00',NULL,'SCHEDULED',5,5,5);

-- =========================
-- SERVICEREQUEST
-- =========================
INSERT INTO servicerequest
(CustomerId,RequestType,CreatedDate,Priority,
 Status,ServiceType,PremiseId,CreatedBy,ModifiedBy)
VALUES
    (1,'NEWCONNECTION','2025-01-10 08:00:00','HIGH',
     'OPEN','ELECTRICITY',1,1,1),

    (2,'COMPLAINT','2025-01-11 09:00:00','MEDIUM',
     'INPROGRESS','WATER',2,2,2),

    (3,'INQUIRY','2025-01-12 10:00:00','LOW',
     'RESOLVED','GAS',3,3,3),

    (4,'DISCONNECT','2025-01-13 11:00:00','HIGH',
     'OPEN','ELECTRICITY',4,4,4),

    (5,'COMPLAINT','2025-01-14 12:00:00','MEDIUM',
     'CLOSED','WATER',5,5,5);