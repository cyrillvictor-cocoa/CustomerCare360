USE customercare360;

INSERT INTO User
(Name, Email, Phone, UserName, Password, CreatedBy, ModifiedBy)
VALUES
('Rahul Sharma','rahul@gmail.com',1000000001,'rahul01','pass123',NULL,NULL),
('Priya Nair','priya@gmail.com',1000000002,'priya01','pass123',1,1),
('Arun Kumar','arun@gmail.com',1000000003,'arun01','pass123',1,1),
('Sneha Patel','sneha@gmail.com',1000000004,'sneha01','pass123',1,1),
('Vikram Singh','vikram@gmail.com',1000000005,'vikram01','pass123',1,1);

-- CUSTOMER
INSERT INTO Customer
(CustomerType, ContactInfo, Status, UserId, CreatedBy, ModifiedBy)
VALUES
('RESIDENTIAL','rahul-contact','ACTIVE',1,1,1),
('COMMERCIAL','priya-contact','ACTIVE',2,1,1),
('INDUSTRIAL','arun-contact','ACTIVE',3,1,1),
('RESIDENTIAL','sneha-contact','ACTIVE',4,1,1),
('COMMERCIAL','vikram-contact','ACTIVE',5,1,1);

-- PREMISE
INSERT INTO Premise
(Address, Region, MeterId, Status, CustomerId, CreatedBy, ModifiedBy)
VALUES
('12 MG Road','Chennai',1001,'ENERGIZED',1,1,1),
('45 Anna Nagar','Chennai',1002,'ENERGIZED',2,1,1),
('78 OMR','Chennai',1003,'DISCONNECTED',3,1,1),
('23 Tambaram','Chennai',1004,'ENERGIZED',4,1,1),
('67 Velachery','Chennai',1005,'ENERGIZED',5,1,1);

-- SERVICEACCOUNT
INSERT INTO ServiceAccount
(CustomerId, StartDate, ServiceType, EndDate, Status, PremiseID, CreatedBy, ModifiedBy)
VALUES
(1,'2025-01-01','ELECTRICITY',NULL,'ENERGIZED',1,1,1),
(2,'2025-01-02','WATER',NULL,'ENERGIZED',2,1,1),
(3,'2025-01-03','GAS',NULL,'DISCONNECTED',3,1,1),
(4,'2025-01-04','ELECTRICITY',NULL,'ENERGIZED',4,1,1),
(5,'2025-01-05','WATER',NULL,'ENERGIZED',5,1,1);

-- NOTIFICATION
INSERT INTO Notification
(UserId, Message, Category, Status, CreatedDate, CreatedBy, ModifiedBy)
VALUES
(1,'Bill Generated','BILL','UNREAD',NOW(),1,1),
(2,'Service Restored','SERVICE','READ',NOW(),1,1),
(3,'Complaint Updated','COMPLAINT','UNREAD',NOW(),1,1),
(4,'Payment Reminder','BILL','DISMISSED',NOW(),1,1),
(5,'Maintenance Alert','SERVICE','READ',NOW(),1,1);

-- SERVICEREQUEST
INSERT INTO ServiceRequest
(CustomerId, RequestType, CreatedDate, Priority, Status, ServiceType, PremiseId, CreatedBy, ModifiedBy)
VALUES
(1,'NEWCONNECTION',NOW(),'HIGH','OPEN','ELECTRICITY',1,1,1),
(2,'DISCONNECT',NOW(),'LOW','RESOLVED','WATER',2,1,1),
(3,'COMPLAINT',NOW(),'MEDIUM','INPROGRESS','GAS',3,1,1),
(4,'INQUIRY',NOW(),'LOW','OPEN','ELECTRICITY',4,1,1),
(5,'COMPLAINT',NOW(),'HIGH','CLOSED','WATER',5,1,1);

-- SERVICEORDER
INSERT INTO ServiceOrder
(ServiceAccountID, PremiseId, OrderType, SchduledDate,
CompletionDate, Status, AssignedTo, CreatedBy, ModifiedBy)
VALUES
(1,1,'CONNECT',NOW(),NULL,'SCHEDULED',3,1,1),
(2,2,'INSPECTION',NOW(),NULL,'INPROGRESS',3,1,1),
(3,3,'DISCONNECT',NOW(),NOW(),'COMPLETED',3,1,1),
(4,4,'CONNECT',NOW(),NULL,'FAILED',3,1,1),
(5,5,'INSPECTION',NOW(),NULL,'SCHEDULED',3,1,1);

-- BILLINGCYCLE
INSERT INTO BillingCycle
(ServiceType, PeriodStart, PeriodEnd, CreatedBy, ModifiedBy)
VALUES
('ELECTRICITY','2025-01-01','2025-01-31',1,1),
('WATER','2025-01-01','2025-01-31',1,1),
('GAS','2025-01-01','2025-01-31',1,1),
('ELECTRICITY','2025-02-01','2025-02-28',1,1),
('WATER','2025-02-01','2025-02-28',1,1);

-- BILL
INSERT INTO Bill
(AccountId, CycleId, `Usage`, Amount, DueDate, Status, CreatedBy, ModifiedBy)
VALUES
(1,1,'250 Units',1250.50,'2025-02-10','GENERATED',1,1),
(2,2,'1000 Litres',500.00,'2025-02-10','CLOSED',1,1),
(3,3,'150 Units',700.00,'2025-02-10','ADJUSTED',1,1),
(4,4,'300 Units',1450.00,'2025-03-10','GENERATED',1,1),
(5,5,'1200 Litres',620.00,'2025-03-10','CLOSED',1,1);

-- ADJUSTMENT
INSERT INTO Adjustment
(BillId, Reason, AmountDelta, ApprovedBy, Status, CreatedBy, ModifiedBy)
VALUES
(1,'Wrong meter reading',-100,4,'APPROVED',1,1),
(2,'Late fee removal',-50,4,'APPROVED',1,1),
(3,'Usage correction',75,4,'REQUESTED',1,1),
(4,'Billing dispute',-120,4,'REJECTED',1,1),
(5,'Tariff correction',50,4,'APPROVED',1,1);

-- COMPLAINT
INSERT INTO Complaint
(CustomerId, Category, Description, LoggedDate, Status, CreatedBy, ModifiedBy)
VALUES
(1,'BILLING','Incorrect bill amount',NOW(),'OPEN',1,1),
(2,'SERVICE','Water supply issue',NOW(),'RESOLVED',1,1),
(3,'OUTAGE','Gas outage reported',NOW(),'INPROGRESS',1,1),
(4,'SERVICE','Frequent disruptions',NOW(),'CLOSED',1,1),
(5,'BILLING','Duplicate charge',NOW(),'OPEN',1,1);

-- CUSTOMERREPORT
INSERT INTO CustomerReport
(Scope, Metrics, GeneratedDate, CreatedBy, ModifiedBy)
VALUES
('REGION','COMPLAINTVOLUME',NOW(),1,1),
('SERVICETYPE','AVGRESOLUTIONTIME',NOW(),1,1),
('PERIOD','BILLADJUSTMENTSRATE',NOW(),1,1),
('REGION','AVGRESOLUTIONTIME',NOW(),1,1),
('PERIOD','COMPLAINTVOLUME',NOW(),1,1);
