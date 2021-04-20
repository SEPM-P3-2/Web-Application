CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) not null unique,
    name VARCHAR(255) not null,
    password VARCHAR(60) not null,
    role VARCHAR(100) not null
 );
