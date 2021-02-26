create table offer (
    id BIGINT PRIMARY KEY,
    url VARCHAR(255),
    name VARCHAR(255)
);
COPY offer FROM '/tmp/offers.csv' WITH (FORMAT csv);