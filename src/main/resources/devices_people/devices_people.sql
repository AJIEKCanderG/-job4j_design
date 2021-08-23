create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Phone', 9000), ('Tablet', 20000), ('Notebook', 40000);
insert into devices(name, price) values ('Phone', 10000), ('Tablet', 30000), ('Notebook', 50000);
insert into devices(name, price) values ('Phone', 11000), ('Tablet', 40000), ('Notebook', 60000)
insert into people(name) values ('Petr'), ('Ivan'), ('Kate');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people(device_id, people_id) values (4, 2), (5, 2), (6, 2);
insert into devices_people(device_id, people_id) values (7, 3), (8, 3), (9, 3);

select * from devices;
select * from people;
select d.name, d.price from devices as d join devices_people as dp on  dp.device_id = d.id;

select p.name, d.name, d.price from devices_people as dp join devices as d on dp.device_id = d.id join people as p on dp.people_id = p.id;

select avg(price) from devices;  /* средняя цена всех устройств */

select d.name, avg(d.price) from devices as d /* средняя цена устройств по типам */
join devices_people as dp on dp.device_id = d.id
group by d.name;

select p.name, avg(d.price) from devices_people as dp /* средняя цена всех устройств каждого владельца */
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp /* средняя цена всех устройств каждого владельца больше 25000 */
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 25000;
