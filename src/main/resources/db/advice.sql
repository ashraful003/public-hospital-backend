CREATE TABLE IF NOT EXISTS advice (
    id BIGSERIAL PRIMARY KEY,
    national_id VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    advice TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_advice_national_id
ON advice(national_id);

CREATE INDEX IF NOT EXISTS idx_advice_title
ON advice(title);