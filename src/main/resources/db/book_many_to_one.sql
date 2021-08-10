create table book(
    id serial primary key,
    name varchar(255)
);

create table author(
    id serial primary key,
    firstName varchar(255),
	lastName varchar(255),
	book_id int references book(id)
);

insert into book(name) values ('Struktury dannykh i algoritmy');
insert into book(name) values ('Filosofia_Java');
insert into author(firstName, lastName, book_id) VALUES ('Robert', 'Lafore', 1);
insert into author(firstName, lastName, book_id) VALUES ('Bryus', 'Ekkel', 2);

select * from book;
select * from author where id in (select id from  book);
