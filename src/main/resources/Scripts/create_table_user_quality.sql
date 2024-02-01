create table user_quality(
id_user uuid references "user"(id),
id_quality int references quality(id),
level double precision
);