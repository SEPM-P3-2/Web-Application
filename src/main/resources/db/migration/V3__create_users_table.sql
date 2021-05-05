CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) not null unique,
    name VARCHAR(255) not null,
    job_id INT NOT NULL,
    password VARCHAR(60) not null,
    role VARCHAR(100) not null,
    CONSTRAINT fk_job_id FOREIGN KEY (job_id) REFERENCES jobs(id)

 );
