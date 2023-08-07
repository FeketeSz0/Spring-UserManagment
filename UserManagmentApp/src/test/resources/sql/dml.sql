INSERT INTO roles (name)
VALUES ('Admin'),
        ('User');



INSERT INTO users (login, password, email, firstname, lastname, birthday, role_id)
VALUES ('MichaelS', 'password123', 'Michael@example.com', 'Michael', 'Scott', '1990-05-15',1),
       ('DwightS', 'Dwight123', 'Dwight@example.com', 'Dwight', 'Schrute', '1992-09-22',1),
         ('CreedB', 'creed123', 'Creed@example.com', 'Creed', 'Branthon', '1940-11-21',2);

