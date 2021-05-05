CREATE TABLE shifts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    location_id INT not null,
    user_id INT,
    startTime TIMESTAMP not null,
    endTime TIMESTAMP not null,
    duration INT not null,
    info VARCHAR(MAX),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES locations(id)
);