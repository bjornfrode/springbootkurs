CREATE SEQUENCE IF NOT EXISTS SEQ_APPOINTMENT_ID;
CREATE TABLE IF NOT EXISTS APPOINTMENT
(
    APPOINTMENT_ID     INTEGER DEFAULT nextval('SEQ_APPOINTMENT_ID'),
    TITLE              VARCHAR(100) NOT NULL,
    TIME               TIMESTAMP NOT NULL,
    PLACE              VARCHAR(100),
    STATUS             VARCHAR(20)
);