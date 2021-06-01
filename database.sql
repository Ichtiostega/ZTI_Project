
CREATE TABLE hunter
(
 "id"      serial NOT NULL,
 name    varchar(50) NULL,
 surname varchar(50) NULL,
 score   int NOT NULL,
 CONSTRAINT PK_hunter PRIMARY KEY ( "id" )
);

CREATE TABLE target
(
 "id"     serial NOT NULL,
 name   varchar(50) NOT NULL,
 status int NOT NULL,
 CONSTRAINT PK_target PRIMARY KEY ( "id" )
);

CREATE TABLE contractor
(
 "id"      serial NOT NULL,
 name    varchar(50) NOT NULL,
 surname varchar(50) NOT NULL,
 CONSTRAINT PK_table_64 PRIMARY KEY ( "id" )
);

CREATE TABLE contract
(
 "id"            serial NOT NULL,
 due_date      date NULL,
 bounty        int NULL,
 hunterId     integer NOT NULL,
 status        int NOT NULL,
 contractorId integer NOT NULL,
 CONSTRAINT PK_contract PRIMARY KEY ( "id" ),
 CONSTRAINT FK_33 FOREIGN KEY ( hunterId ) REFERENCES hunter ( "id" ),
 CONSTRAINT FK_64 FOREIGN KEY ( contractorId ) REFERENCES contractor ( "id" )
);

CREATE TABLE contract_target
(
 "id"          serial NOT NULL,
 contractId integer NOT NULL,
 targetId   integer NOT NULL,
 CONSTRAINT PK_contract_target PRIMARY KEY ( "id" ),
 CONSTRAINT FK_39 FOREIGN KEY ( contractId ) REFERENCES contract ( "id" ),
 CONSTRAINT FK_46 FOREIGN KEY ( targetId ) REFERENCES target ( "id" )
);
