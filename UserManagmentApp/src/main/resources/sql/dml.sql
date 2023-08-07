INSERT INTO roles (name)
VALUES ('Admin'),
        ('User');



INSERT INTO users (login, password, email, firstname, lastname, birthday, role_id)
VALUES ('MichaelS', 'password123', 'Michael@example.com', 'Michael', 'Scott', '1990-05-15',1),
       ('DwightS', 'Dwight123', 'Dwight@example.com', 'Dwight', 'Schrute', '1992-09-22',1),
       ('Stanley', 'stanley123', 'Stanley@example.com', 'Stanley', 'Houdson', '1985-12-01',2),
        ('JimH', 'jim123', 'Jim@example.com', 'Jim', 'Halpert', '1998-12-21',2),
         ('CreedB', 'creed123', 'Creed@example.com', 'Creed', 'Branthon', '1940-11-21',2),
         ('user', '$2a$10$x.NRzWimGOaVB41tHr1MN.1Abq3LKhuXG7r2UkcVHYAC9XIeMXJrm', 'user@example.com', 'userFirstname', 'userLastname', '2000-01-01',2),
         ('admin', '$2a$10$x9.lk3NmB5HeUEPm7.6ppOc9AtG35JitFNC4YL9daGuId.1.BqYAq', 'admin@example.com', 'adminFirstname', 'adminLastname', '2000-01-01',1);

