CREATE TABLE offer (
    id bigint primary key,
    url varchar(255),
    name varchar(255)
);

COPY offer FROM '/tmp/offers.csv' WITH (FORMAT csv);

CREATE TABLE offer_conversion (
    id bigint primary key,
    timestamp timestamp ,
    payout_total float(8),
    received_total float(8),
    offer_id bigint references offer(id)
);