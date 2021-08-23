select avg(price) from devices;  /* avg price all devices */

select d.name, avg(d.price) from devices as d /* avg price all devices from name */
join devices_people as dp on dp.device_id = d.id
group by d.name;

select p.name, avg(d.price) from devices_people as dp /* avg price all devices from name from ever people */
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp /* avg price (>25000) all devices from name from ever people */
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 25000;


