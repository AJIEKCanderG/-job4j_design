select * from employees e left join departments d on e.departments_id = d.id
union
select * from employees e right join departments d on e.departments_id = d.id;