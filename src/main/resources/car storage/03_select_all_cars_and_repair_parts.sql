select c.name as car, b.name as body, e.name as engine, t.name as transmission from car c
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id;