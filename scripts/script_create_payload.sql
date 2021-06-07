create database qualification;

create table address (
  id bigserial not null,
  city varchar(50), 
  complement varchar(255), 
  country varchar(50), 
  latitude float8, 
  longitude float8, 
  neighbourhood varchar(50), 
  number int4 not null,
  state varchar(50),
  street_name varchar(100),
  zipcode varchar(255),
  primary key (id)
);

insert into address (id, city, complement, country, latitude, longitude, neighbourhood, 
"number", state, street_name, zipcode)
values
  (1, 'Araras', 'Loja Cacau', 'Brasil', null, null, 'Centro', 542, 'Sao Paulo', 
'Rua Tiradentes', '13600-070');

