DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;

CREATE TABLE Roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    birthday DATE,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES Roles(id)
  );

