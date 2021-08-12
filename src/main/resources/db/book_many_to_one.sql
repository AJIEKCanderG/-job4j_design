create table author(
    id serial primary key,
    firstName varchar(255),
	lastName varchar(255)	
);

create table book(
    id serial primary key,
    name varchar(255),
	author_id int references author(id)
);

insert into author(firstName, lastName) VALUES ('Robert', 'Lafore');
insert into author(firstName, lastName) VALUES ('Bryus', 'Ekkel');
insert into book(name, author_id) values ('Struktury dannykh i algoritmy', 1);
insert into book(name, author_id) values ('Filosofia_Java', 2);

select * from book;
select * from author where id in (select id from  book);