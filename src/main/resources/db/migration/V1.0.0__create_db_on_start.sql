CREATE TABLE webhook (
                              id serial PRIMARY KEY,
                              hash VARCHAR (200) NOT NULL,
                              payload VARCHAR,
                              creation_time TIMESTAMP default current_timestamp
);
