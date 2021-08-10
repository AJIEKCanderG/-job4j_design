create table car(
    id serial primary key,
    model varchar(255),
    gosNumber varchar(255)
);

create table driver(
    id serial primary key,
    name varchar(255),
	car_id int references car(id) unique
);

insert into car(model, gosNumber) values ('AUDI', 'A177A199RUS');
insert into car(model, gosNumber) values ('BMW', 'B100P01RUS');
insert into car(model, gosNumber) values ('VW', 'X777K01RUS');
insert into driver(name, car_id) VALUES ('Ivan', 1);
insert into driver(name, car_id) VALUES ('Petr', 2);
insert into driver(name, car_id) VALUES ('Oleg', 3);

select * from driver;
select * from car where id in (1);
select * from car where id in (3);