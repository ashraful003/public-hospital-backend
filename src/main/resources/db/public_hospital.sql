CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    national_id VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50),
    address VARCHAR(255),
    dob DATE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    institute VARCHAR(255),
    degree VARCHAR(255),
    license VARCHAR(255),
    specialist VARCHAR(255),
    weight VARCHAR(50),
    image_url VARCHAR(255)
);
ALTER TABLE users DROP CONSTRAINT IF EXISTS users_role_check;
ALTER TABLE users ADD CONSTRAINT users_role_check
CHECK (
    role IN (
        'PATIENT',
        'DOCTOR',
        'NURSE',
        'DOCTOR_ASSISTANT',
        'CLEANER'
    )
);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_nid ON users(national_id);
ALTER TABLE users ADD COLUMN IF NOT EXISTS image_url VARCHAR(255);
ALTER TABLE users ADD COLUMN IF NOT EXISTS institute VARCHAR(255);
ALTER TABLE users ADD COLUMN IF NOT EXISTS degree VARCHAR(255);
ALTER TABLE users ADD COLUMN IF NOT EXISTS license VARCHAR(255);
ALTER TABLE users ADD COLUMN IF NOT EXISTS specialist VARCHAR(255);
ALTER TABLE users ADD COLUMN IF NOT EXISTS weight VARCHAR(50);

CREATE TABLE IF NOT EXISTS otp (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    code VARCHAR(10),
    expiration_time TIMESTAMP,
    verified BOOLEAN NOT NULL DEFAULT FALSE
);
UPDATE otp SET verified = false WHERE verified IS NULL;
ALTER TABLE otp
ALTER COLUMN verified SET NOT NULL;
ALTER TABLE otp
ALTER COLUMN verified SET DEFAULT FALSE;