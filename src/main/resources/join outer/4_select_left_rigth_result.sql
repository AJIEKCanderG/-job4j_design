select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;