create schema student_db;
use student_db;

create table students (
  id int auto_increment primary key,
  name varchar(50),
  age int
);

DELIMITER $$
create procedure add_students (
    name_in varchar(50),
    age_in int
)
begin
    insert into students (name, age)
        values (name_in, age_in);
end $$
DELIMITER ;