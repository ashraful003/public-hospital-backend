CREATE TABLE duration (
    id BIGSERIAL PRIMARY KEY,
    national_id VARCHAR(100) NOT NULL,
    duration VARCHAR(255) NOT NULL
);

CREATE INDEX idx_duration_national_id
ON duration(national_id);