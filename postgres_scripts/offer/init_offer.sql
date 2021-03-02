CREATE SEQUENCE sequence_generator start 100000;

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

CREATE TABLE users (
  id bigint primary key,
  username varchar(255) not null,
  password varchar(255) not null
);

INSERT INTO users values(nextval('public.sequence_generator'), 'user', '123userabc');



