 create table visitors(
     id serial primary key,
     name varchar(255)
 );
 
 create table restaurants(
     id serial primary key,
     name varchar(255)
 );
 
 create table visitors_restorants(
     id serial primary key,
     visitor_id int references visitors(id),
     restaurant_id int references restaurants(id)
 );
 
insert into visitors(name) values ('Ivanon');
insert into visitors(name) values ('Petrov');
insert into visitors(name) values ('Sidorov');

insert into restaurants(name) values ('Birch');
insert into restaurants(name) values ('Bona Capona');
insert into restaurants(name) values ('Red. Steak & Wine');

insert into visitors_restorants(visitor_id, restaurant_id) values (1, 1);
insert into visitors_restorants(visitor_id, restaurant_id) values (2, 2);
insert into visitors_restorants(visitor_id, restaurant_id) values (1, 3);
insert into visitors_restorants(visitor_id, restaurant_id) values (3, 3);

select * from visitors;
select * from restaurants;
select * from visitors_restorants where id in (select id from  visitors);
