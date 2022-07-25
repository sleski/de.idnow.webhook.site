CREATE TABLE webhook (
                              id serial PRIMARY KEY,
                              hash VARCHAR (200) NOT NULL,
                              payload VARCHAR,
                              creation_time TIMESTAMP default current_timestamp
);

insert into webhook (hash, payload, creation_time) values ('DV1-ABCDE', '{\"status\":\"CANCEL\", \"internal_token\":\"DV1-ABCDE\", \"shortname\":\"ihrebank\"}', now());
insert into webhook (hash, payload, creation_time) values ('DV1-ABCDE', '{\"status\":\"START\", \"internal_token\":\"DV1-ABCDE\", \"shortname\":\"ihrebank\"}', now());
insert into webhook (hash, payload, creation_time) values ('DV1-DFRED', '{\"status\":\"CANCEL\", \"internal_token\":\"DV1-DFRED\", \"shortname\":\"ihrebank\"}', now());
insert into webhook (hash, payload, creation_time) values ('DV1-ASDAS', '{\"status\":\"CANCEL\", \"internal_token\":\"DV1-ASDAS\", \"shortname\":\"ihrebank\"}', now());
insert into webhook (hash, payload, creation_time) values ('DV1-THHJJ', '{\"status\":\"CANCEL\", \"internal_token\":\"DV1-THHJJ\", \"shortname\":\"ihrebank\"}', now());
