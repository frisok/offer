CREATE TABLE conversion (
    id        bigint primary key,
    offer_id  bigint,
    affiliate bigint,
    timestamp timestamp,
    payout    float(4),
    received  float(4),
    published boolean default false
);

COPY conversion(id, offer_id,affiliate,timestamp,payout,received) FROM '/tmp/conversions.csv' WITH (FORMAT csv);