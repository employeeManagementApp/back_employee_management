create table salary(
id serial primary key,
amount double precision,
riseup double precision,
paiement_date timestamp with time zone,
status boolean default false
);