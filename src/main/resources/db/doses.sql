CREATE TABLE doses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dose VARCHAR(255) NOT NULL,
    national_id VARCHAR(50) NOT NULL
);
CREATE INDEX idx_doses_national_id ON doses(national_id);
CREATE INDEX idx_doses_dose ON doses(dose);