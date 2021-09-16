DROP TABLE users CASCADE;
DROP TABLE authorities CASCADE;
DROP TABLE target CASCADE;
DROP TABLE contract CASCADE;
DROP TABLE contract_target CASCADE;

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (username)
 );

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE target
(
    "id"     serial NOT NULL,
    name   varchar(50) NOT NULL,
    status int NOT NULL,
    CONSTRAINT PK_target PRIMARY KEY ( "id" )
);

CREATE TABLE contract
(
    "id"            serial NOT NULL,
    due_date      date NOT NULL,
    end_date      date NULL,
    bounty        int NULL,
    description   VARCHAR(1000) NULL,
    hunter_id     VARCHAR(50) NULL,
    status        int NOT NULL DEFAULT 0,
    contractor_id VARCHAR(50) NOT NULL,
    CONSTRAINT PK_contract PRIMARY KEY ( "id" ),
    CONSTRAINT FK_33 FOREIGN KEY ( hunter_id ) REFERENCES users ( "username" ),
    CONSTRAINT FK_64 FOREIGN KEY ( contractor_id ) REFERENCES users ( "username" )
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

-- INSERT INTO contract(due_date, bounty, status, hunter_id, contractor_id) VALUES ('10-10-2021', 2000, 1, 1, 1), 
-- ('2-10-2021', 2500, 2, 2, 1), 
-- ('5-10-2021', 6000, 1, 1, 2);

-- INSERT INTO contract(due_date, bounty, status, contractor_id) VALUES('10-11-2021', 21000, 0, 2), 
-- ('10-15-2021', 75000, 0, 3), 
-- ('10-20-2021', 1000000, 0, 1);