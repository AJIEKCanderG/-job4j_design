select * from departments d left join employees e on d.id = e.departments_id  where e.departments_id is null;
