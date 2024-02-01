create table project(
id serial primary key,
name varchar(255),
duration time,
start_date timestamp with time zone default current_timestamp
);
