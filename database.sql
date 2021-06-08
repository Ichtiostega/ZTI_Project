
CREATE TABLE hunter
(
 "id"      serial NOT NULL,
 name    varchar(50) NULL,
 surname varchar(50) NULL,
 email   varchar(100) NOT NULL,
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
 email   varchar(100) NOT NULL,
 CONSTRAINT PK_table_64 PRIMARY KEY ( "id" )
);

CREATE TABLE contract
(
 "id"            serial NOT NULL,
 due_date      date NULL,
 bounty        int NULL,
 hunter_id     integer NULL,
 status        int NOT NULL,
 contractor_id integer NOT NULL,
 CONSTRAINT PK_contract PRIMARY KEY ( "id" ),
 CONSTRAINT FK_33 FOREIGN KEY ( hunter_id ) REFERENCES hunter ( "id" ),
 CONSTRAINT FK_64 FOREIGN KEY ( contractor_id ) REFERENCES contractor ( "id" )
);

CREATE TABLE contract_target
(
 "id"          serial NOT NULL,
 contract_id integer NOT NULL,
 target_id   integer NOT NULL,
 CONSTRAINT PK_contract_target PRIMARY KEY ( "id" ),
 CONSTRAINT FK_39 FOREIGN KEY ( contract_id ) REFERENCES contract ( "id" ),
 CONSTRAINT FK_46 FOREIGN KEY ( target_id ) REFERENCES target ( "id" )
);

INSERT INTO hunter(name, surname, email, score) VALUES ('Adam', 'Abacki', 'a.abacki@op.pl', 7), 
('Cadam', 'Cabacki', 'a.cabacki@op.pl', 3),
('Dadam', 'Dabacki', 'a.dabacki@op.pl', 5);

INSERT INTO contractor(name, surname, email) VALUES ('Badam', 'Babacki', 'a.babacki@op.pl'), 
('Radam', 'Rabacki', 'a.rabacki@op.pl'),
('Kadam', 'Kabacki', 'a.kabacki@op.pl');

INSERT INTO contract(due_date, bounty, status, hunter_id, contractor_id) VALUES ('10-10-2021', 2000, 1, 1, 1), 
('2-10-2021', 2500, 2, 2, 1), 
('5-10-2021', 6000, 1, 1, 2);

INSERT INTO contract(due_date, bounty, status, contractor_id) VALUES('10-11-2021', 21000, 0, 2), 
('10-15-2021', 75000, 0, 3), 
('10-20-2021', 1000000, 0, 1);