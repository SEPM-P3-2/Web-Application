CREATE TABLE availabilities(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT not null,
    day VARCHAR(MAX) not null,
    startTime TIME not null,
    endTime TIME not null
);