select p.name as p, type.name as type_name
from product p join type  on p.type_id = type.id;