select b.name as body_not_use from body b left join car c on b.id = c.body_id where c.id is null;
select e.name as engine_not_use from engine e left join car c on e.id = c.engine_id where c.id is null;
select t.name as transmission_not_use from transmission t left join car c on t.id = c.transmission_id where c.id is null;