CREATE TABLE prescriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- ================= DOCTOR =================
    doctor_name VARCHAR(255),
    doctor_email VARCHAR(255),
    doctor_degree VARCHAR(255),
    doctor_specialist VARCHAR(255),
    doctor_institute VARCHAR(255),
    doctor_license VARCHAR(255),

    -- ================= PATIENT =================
    patient_id VARCHAR(255),
    patient_name VARCHAR(255),
    patient_age VARCHAR(255),
    patient_weight VARCHAR(255),

    -- ================= PRESCRIPTION =================
    weight VARCHAR(255),
    problems TEXT,
    blood_pressure VARCHAR(255),
    pulse VARCHAR(255),
    temperature VARCHAR(255),

    -- ================= MEDICINES =================
    medicines TEXT,

    -- ================= DOSE TIME =================
    advice VARCHAR(255),

    -- ================= TESTS =================
    tests TEXT,

    -- ================= OTHER =================
    next_meet VARCHAR(255),

    -- ================= DATE =================
    date DATETIME
);