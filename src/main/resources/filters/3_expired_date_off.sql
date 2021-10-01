select p.name, p.expired_date from product p join type on p.type_id = type.id 
where expired_date < current_date;