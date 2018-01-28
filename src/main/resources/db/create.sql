SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS artists (
    id int PRIMARY KEY auto_increment,
    name VARCHAR   
);

CREATE TABLE IF NOT EXISTS cds (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    artistid INTEGER
);

