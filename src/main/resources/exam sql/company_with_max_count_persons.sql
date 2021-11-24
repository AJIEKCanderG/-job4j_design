select c.name as company, count(p.company_id) as max_person from person p join company c on c.id = p.company_id
group by c.name
having count(p.name) = (select  count(p.company_id)
    as max_person from person p join company c on c.id = p.company_id
group by c.name
order by 1 desc limit 1);