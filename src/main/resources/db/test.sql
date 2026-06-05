CREATE TABLE IF NOT EXISTS test (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       test_name VARCHAR(255) NOT NULL,
       price DOUBLE NOT NULL,
       currency VARCHAR(255) NOT NULL,
       unit VARCHAR(100),
       reference_range VARCHAR(255),
       result VARCHAR(255)
   );
CREATE INDEX IF NOT EXISTS idx_test_name ON test(test_name);
CREATE INDEX IF NOT EXISTS idx_diagnostic_center_name
ON test(name);