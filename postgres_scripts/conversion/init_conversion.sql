CREATE TABLE conversion (
    id        bigint primary key,
    offer_id  bigint,
    affiliate bigint,
    timestamp timestamp,
    payout    float(4),
    received  float(4)
);