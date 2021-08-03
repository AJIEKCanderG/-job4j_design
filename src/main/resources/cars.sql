create table cars(
id serial primary key,
	model varchar(255),
	color text,
	seatnumbers int
);
insert into cars(model, color, seatnumbers) values ('Audi', 'Red', '2');
select * from cars;
update cars set model = 'BMW';
select * from cars;
delete from cars;
select * from cars;