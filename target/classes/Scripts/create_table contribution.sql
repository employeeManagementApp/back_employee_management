create table contribution(
id serial primary key,
date date,
description varchar(255),
id_user uuid references "user"(id),
id_project int references project(id)
);
