CREATE TABLE shifts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    location_id INT not null,
    job_id INT not null,
    emp_id INT not null,
    startTime TIMESTAMP not null,
    endTime TIMESTAMP not null,
    duration INT not null,
    description VARCHAR(MAX)
 );