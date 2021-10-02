select p.name from product p join type on p.type_id = type.id 
where type.name = 'мороженое';