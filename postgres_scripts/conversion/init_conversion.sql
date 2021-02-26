CREATE TABLE conversion (
    id        BIGINT PRIMARY KEY,
    offer_idd BIGINT,
    affiliate BIGINT,
    timestamp TIMESTAMP,
    payout    FLOAT(4),
    received  FLOAT(4)
);