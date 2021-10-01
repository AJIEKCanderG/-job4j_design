select product.name 
from product 
join type on product.type_id = type.id 
where type.name = 'cheese'
or type.name = 'milk';