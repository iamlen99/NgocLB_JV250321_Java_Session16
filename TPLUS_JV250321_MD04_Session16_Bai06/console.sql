use md4_ss16_db;

create table employees
(
    id   int primary key auto_increment,
    name varchar(50)
);

create table projects
(
    id           int primary key auto_increment,
    project_name varchar(50)
);

create table employee_projects
(
    employee_id int,
    project_id  int,
    primary key (employee_id, project_id),
    constraint employee_id_fk
        foreign key (employee_id)
            references employees (id)
            on delete cascade,
    constraint project_id_fk
        foreign key (project_id)
            references projects (id)
            on delete cascade
);

DELIMITER $$
create procedure assign_employee_to_project(
    employee_id_in int,
    project_id_in int
)
begin
    insert into employee_projects
        values (employee_id_in, project_id_in);
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_employee(
    id_in int,
    out count_empl int
)
begin
    select count(*) into count_empl from employees
        where id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_project(
    id_in int,
    out count_project int
)
begin
    select count(*) into count_project from employees
    where id = id_in;
end $$
DELIMITER ;

