create table customer(
	id serial primary key,
	firstName varchar(255),
	lastName varchar(255),
	email varchar(255),
	phone varchar(255)
);

create table store(
	id serial primary key,
	name varchar(255),
	countOrder int,
	customer_id references customer(id)	
);

insert into customer(firstName, lastName, email, phone) values ('Ivan', 'Ivanov', 'ivanov@mail.ru', '+79110000001');
insert into customer(firstName, lastName, email, phone) values ('Sidor', 'Sidorov', 'sidorov@mail.ru', '+79110000002');

insert into store(name, countOrder, customer_id) values('SonyStore', 2, 1);
insert into store(name, countOrder, customer_id) values('SonyStore', 1, 2);

select s.name, s.countOrder, c.firstName || ' ' || c.lastName "full name", c.phone || ', ' || c.email "contact" from customer c join store s on s.customer_id = s.id;
select s.name, s.countOrder, c.firstName, c.lastName, c.phone, c.email from customer c join store s on s.customer_id = s.id;
select * from store join customer c on store.customer_id = c.id;