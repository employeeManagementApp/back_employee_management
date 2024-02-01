CREATE TABLE "user"(
id uuid primary key DEFAULT gen_random_uuid(),
firstname varchar(200),
lastname varchar(200),
email varchar(200),
password varchar(200),
birthdate date,
profil varchar(200),
address varchar(200),
gender varchar(1),
CIN varchar(200),
role varchar(200),
start_work_date timestamp with time zone default current_timestamp,
last_work_date timestamp with time zone,
id_salary int references salary(id)
);