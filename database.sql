-- DROP TABLE users CASCADE;
-- DROP TABLE authorities CASCADE;
DROP TABLE contract;

-- CREATE TABLE users
-- (
--     username VARCHAR(50)  NOT NULL,
--     password VARCHAR(100) NOT NULL,
--     enabled  BOOLEAN      NOT NULL DEFAULT TRUE,
--     PRIMARY KEY (username)
--  );

-- CREATE TABLE authorities
-- (
--     username  VARCHAR(50) NOT NULL,
--     authority VARCHAR(50) NOT NULL,
--     FOREIGN KEY (username) REFERENCES users (username)
-- );

CREATE TABLE contract
(
    "id"          serial NOT NULL,
    due_date      date NOT NULL,
    end_date      date NULL,
    bounty        int NULL,
    description   VARCHAR(1000) NULL,
    status        int NOT NULL DEFAULT 0,
    hunter_id     VARCHAR(50) NULL,
    contractor_id VARCHAR(50) NOT NULL,
    CONSTRAINT PK_contract PRIMARY KEY ( "id" ),
    CONSTRAINT FK_33 FOREIGN KEY ( hunter_id ) REFERENCES users ( "username" ),
    CONSTRAINT FK_64 FOREIGN KEY ( contractor_id ) REFERENCES users ( "username" )
);

-- Inserts (After users are added)

INSERT INTO contract (due_date, bounty, status, hunter_id, contractor_id, description) VALUES
('2020-02-01', 3000, 1, 'StatusH', 'StatusC', 'The status should be "Ongoing, Overdue"'),
('2022-02-01', 4000, 1, 'StatusH', 'StatusC', 'The status should be "Ongoing"'),
('2021-06-30', 5, 1, '7kocierz', 'A.D.', 'Final project for ZTI'),
('2222-02-22', 100, 1, '7kocierz', 'A.D.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec magna at risus tempus suscipit. Nullam blandit nulla vitae felis pretium vehicula. Duis mi felis, tincidunt id venenatis a, egestas quis urna. In porttitor convallis semper. Morbi a augue quis ipsum vulputate auctor. Sed elementum a purus id congue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In ornare, justo lobortis ornare sollicitudin, erat sapien hendrerit quam, non viverra ligula quam molestie neque. Vivamus non odio porta, consequat nisl vel, consequat mauris. Sed nulla lacus, aliquam vel enim id, aliquet tincidunt erat. Praesent est leo, sollicitudin at sollicitudin condimentum, molestie quis diam. Etiam scelerisque felis in lectus dapibus scelerisque. Sed egestas feugiat tempus.Quisque ut feugiat mi. Suspendisse pellentesque venenatis metus, bibendum fringilla tellus gravida ac. Nunc rutrum suscipit tortor, sed rutrum mauris malesuada quis. Vestibulum ante ipsum donec. ');
INSERT INTO contract (due_date, bounty, status, contractor_id, description) VALUES
('2222-02-01', 1000, 0, 'WaylandCorp', 'Journey to the planet ZX-435-1 and eliminate the xenmorph population along with every possible means of its regrowth.'),
('2020-02-01', 2000, 0, 'StatusC', 'The status should be "Posted"');
INSERT INTO contract (due_date, end_date, bounty, status, hunter_id, contractor_id, description) VALUES
('1456-02-10', '1456-01-12', 5500, 2, 'Geralt', 'Redania', 'End the suffering of our people by slaing the mighty dragon living on the top of the mountain. Gold and Glory awaits your success.'),
('2020-02-01', '2020-03-10', 5, 3, 'AwfulHunter', 'Gardener', 'HELP! A duck is terrorizing my crops.'),
('2020-03-01', '2020-10-12', 10, 3, 'AwfulHunter', 'Gardener', 'A cat is drinking all my milk'),
('2020-10-01', '2020-12-11', 20, 3, 'AwfulHunter', 'Gardener', 'Get rid of the snails eating my precious zuchinis.'),
('2020-09-01', '2020-06-10', 30, 2, 'AwfulHunter', 'Gardener', 'My plants have gained sentience and are out for blood.'),
('2020-08-01', '2020-04-16', 15, 2, 'AwfulHunter', 'Gardener', 'My garden is hounted. Please lay to rest the souls of gardeners before me.'),
('2020-07-01', '2020-07-20', 3, 2, 'AwfulHunter', 'Gardener', 'Stomp the spider beneath my bed.'),
('2020-02-01', '2020-01-10', 5000, 2, 'StatusH', 'StatusC', 'The status should be "Success"'),
('2020-02-01', '2020-01-10', 6000, 3, 'StatusH', 'StatusC', 'The status should be "Failed"'),
('2020-02-01', '2020-03-10', 7000, 2, 'StatusH', 'StatusC', 'The status should be "Success, Overdue"'),
('2020-02-01', '2020-03-10', 8000, 3, 'StatusH', 'StatusC', 'The status should be "Failed, Overdue"');
