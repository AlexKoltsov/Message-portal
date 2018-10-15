-- CREATE DATABASE "message-portal";

drop table if exists users;
drop table if exists user_session;
drop table if exists message;

CREATE TABLE users(
id SERIAL PRIMARY KEY,
first_name VARCHAR(100),
second_name VARCHAR(100),
last_name VARCHAR(100),
email VARCHAR(100),
login VARCHAR(100),
password VARCHAR(100)
);

CREATE TABLE user_session(
id SERIAL PRIMARY KEY,
user_id integer
);

CREATE TABLE message(
id SERIAL PRIMARY KEY,
user_id integer,
subject VARCHAR(100),
content VARCHAR(256),
from_user integer,
to_user integer,
date_time TIMESTAMP
);