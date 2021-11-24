select c.name as Company, count(p.company_id) as max_person_in_company from person p join company c on c.id = p.company_id
group by c.name
order by count(p.company_id) desc limit 1; 