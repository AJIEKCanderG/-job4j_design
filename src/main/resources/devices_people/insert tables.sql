
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
