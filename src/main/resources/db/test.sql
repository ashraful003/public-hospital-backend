CREATE TABLE IF NOT EXISTS test (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    test_name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    currency VARCHAR(50) NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_test_name ON test(test_name);
CREATE INDEX IF NOT EXISTS idx_diagnostic_center_name
ON test(name);