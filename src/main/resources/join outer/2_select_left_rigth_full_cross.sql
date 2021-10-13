select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;